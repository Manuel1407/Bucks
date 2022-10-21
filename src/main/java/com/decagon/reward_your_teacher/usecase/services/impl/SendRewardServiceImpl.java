package com.decagon.reward_your_teacher.usecase.services.impl;

import com.decagon.reward_your_teacher.domain.dao.*;
import com.decagon.reward_your_teacher.domain.entities.AppUserEntity;
import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.domain.entities.enums.NotificationType;
import com.decagon.reward_your_teacher.domain.entities.enums.TransactionType;
import com.decagon.reward_your_teacher.domain.entities.message.NotificationEntity;
import com.decagon.reward_your_teacher.domain.entities.transact.TransactionEntity;
import com.decagon.reward_your_teacher.domain.entities.transact.WalletEntity;
import com.decagon.reward_your_teacher.infrastructure.error_handler.CustomNotFoundException;
import com.decagon.reward_your_teacher.infrastructure.error_handler.InsufficientBalanceException;
import com.decagon.reward_your_teacher.usecase.payload.request.EmailDetailsRequest;
import com.decagon.reward_your_teacher.usecase.payload.request.SendRewardRequest;
import com.decagon.reward_your_teacher.usecase.payload.response.SendRewardResponse;
import com.decagon.reward_your_teacher.usecase.services.SendRewardService;
import com.decagon.reward_your_teacher.utils.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Service
@Transactional
@AllArgsConstructor
public class SendRewardServiceImpl implements SendRewardService {

    private final StudentDao studentDao;

    private final TeacherDao teacherDao;

    private final WalletDao walletDao;

    private final AppUserDao appUserDao;
    private final EmailService emailService;
    private final TransactionDao transactionDao;
private final NotificationDao notificationDao;

    @Override
    public SendRewardResponse sendRewardResponse(SendRewardRequest sendRewardRequest) throws Exception {
        double amount = Double.parseDouble(sendRewardRequest.getAmount().toString());
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof UserDetails)) {
            throw new CustomNotFoundException("user not found");
        }

        String email = ((UserDetails) principal).getUsername();


        AppUserEntity appUserEntity = appUserDao.findAppUserEntityByEmail(email);

        if (appUserEntity == null){
            throw new CustomNotFoundException("user not found");
        }

        StudentEntity student = studentDao.getStudentEntityByAppUserEntity(appUserEntity);


        TeacherEntity teacher = teacherDao.getTeacherByNameAndPhoneNumber(sendRewardRequest.getTeacherName(), sendRewardRequest.getTeacherPhone());
        TransactionEntity teacherTransaction;
        if (teacher != null) {
            if (getStudentWalletBalance(student).compareTo(sendRewardRequest.getAmount()) >= 0) {

                if(sendRewardRequest.getAmount().compareTo(new BigDecimal("0.00")) <= 0){
                    throw new IllegalArgumentException("invalid amount");
                }

                student.getWallet().setBalance(student.getWallet().getBalance().subtract(sendRewardRequest.getAmount()));
                teacher.getWallet().setBalance(teacher.getWallet().getBalance().add(sendRewardRequest.getAmount()));
                studentDao.saveRecord(student);
                teacherDao.saveRecord(teacher);
                 teacherTransaction = TransactionEntity.builder()
                        .teacher(teacher)
                        .student(student)
                        .description(student.getName()+" Sent ₦"+formatter.format(amount)+" to you")
                        .transactionType(TransactionType.CREDIT.toString())
                        .amount(sendRewardRequest.getAmount()).build();
                transactionDao.saveRecord(teacherTransaction);



              BigDecimal tmptotalMoney =  student.getWallet().getTotalMoneySent();
             BigDecimal totalMoney  = tmptotalMoney.add(sendRewardRequest.getAmount());
             student.getWallet().setTotalMoneySent(totalMoney);
            } else {
                throw new InsufficientBalanceException("Unable to perform transaction because of an insufficient balance");
            }

        } else {
            throw new CustomNotFoundException("teacher not found");
        }


        NotificationEntity notification = new NotificationEntity();
               notification.setMessage(student.getName()+" sent you ₦"+formatter.format(amount)+" reward");
                notification.setNotificationType(NotificationType.CREDIT_NOTIFICATION);
                notification.setTeacher(teacher);
                notification.setTransaction(teacherTransaction);
                notificationDao.saveRecord(notification);

        NotificationEntity notification1 = new NotificationEntity();
        notification1.setMessage(student.getName()+", you just sent ₦" + formatter.format(amount)+" to "+teacher.getName());
        notification1.setNotificationType(NotificationType.CREDIT_NOTIFICATION);
        notification1.setStudent(student);
        notificationDao.saveRecord(notification1);

        EmailDetailsRequest emailDetailsRequest = EmailDetailsRequest.builder()
                .msgBody(emailService.receiveFundsEmail(teacher.getName(),student.getName(),sendRewardRequest.getAmount().toString()))
                .subject("PodA email")
                .recipient(teacher.getAppUserEntity().getEmail())
                .build();
        emailService.sendMailWithAttachment(emailDetailsRequest);

        EmailDetailsRequest emailDetailsRequest2 = EmailDetailsRequest.builder()
                .msgBody(emailService.sendFundsEmail(teacher.getName(),student.getName(),sendRewardRequest.getAmount().toString()))
                .subject("PodA email")
                .recipient(appUserEntity.getEmail())
                .build();
        emailService.sendMailWithAttachment(emailDetailsRequest2);

        return new SendRewardResponse(true,
                "money sent successfully to " + teacher.getName()
                        + "you now have " + student.getWallet().getBalance() + "in your wallet", LocalDateTime.now()
        );
    }

    @Override
    public BigDecimal getStudentWalletBalance(StudentEntity student) {


        WalletEntity wallet = walletDao.findWalletEntityByStudent(student);
        if (wallet == null){
            throw new CustomNotFoundException("user wallet does not exist");
        }
        return wallet.getBalance();
    }

}
