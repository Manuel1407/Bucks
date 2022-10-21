package com.decagon.reward_your_teacher.usecase.services.impl;

import com.decagon.reward_your_teacher.domain.dao.*;
import com.decagon.reward_your_teacher.domain.entities.AppUserEntity;
import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.domain.entities.enums.NotificationType;
import com.decagon.reward_your_teacher.domain.entities.message.NotificationEntity;
import com.decagon.reward_your_teacher.domain.entities.transact.TransactionEntity;
import com.decagon.reward_your_teacher.infrastructure.configuration.security.UserDetails;
import com.decagon.reward_your_teacher.infrastructure.error_handler.CustomNotFoundException;
import com.decagon.reward_your_teacher.usecase.payload.request.TransactionRequest;
import com.decagon.reward_your_teacher.usecase.payload.response.NotificationResponse;
import com.decagon.reward_your_teacher.usecase.services.NotificationService;
import com.decagon.reward_your_teacher.utils.LocalDateTimeConverter;
import com.decagon.reward_your_teacher.utils.ScheduledTasks;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private NotificationDao notificationDao;
    private StudentDao studentDao;
    private TeacherDao teacherDao;

    private ScheduledTasks scheduledTasks;
    private AppUserDao appuser;
private TransactionDao transactionDao;

    @Override
    public NotificationEntity studentSendMoneyNotification(TransactionRequest transactionRequest) {
        Long teacherId = transactionRequest.getTeacherId();
        TeacherEntity teacher = teacherDao.findById(teacherId).orElse(null);
        NotificationEntity notification = new NotificationEntity();

        try {
            if (teacher == null) {
                throw new CustomNotFoundException("Id " + teacherId + " is not valid");
            }

            String message = "Successfully transferred ₦" + transactionRequest.getAmount() + " to " + teacher.getName();
            notification.setCreatedAt(LocalDateTime.now());
            notification.setMessage(message);
            notification.setTeacher(teacher);
            notificationDao.saveRecord(notification);
            return notification;
        } catch (CustomNotFoundException e) {
            System.out.println(e.getMessage());
        }
        notification.setMessage("Transaction failed!!!. Invalid user.");
        notification.setCreatedAt(LocalDateTime.now());
        return notification;
    }

    @Override
    public NotificationEntity walletFundingNotification(TransactionRequest transactionRequest) {
        Long studentId = transactionRequest.getStudentId();
        StudentEntity studentEntity = studentDao.findById(studentId).orElse(null);

        if (studentEntity == null) {
            throw new CustomNotFoundException("Student with Id " + studentId + " is not valid");
        }

        NotificationEntity notification = new NotificationEntity();
        String message = "You have successfully funded your wallet with ₦" + transactionRequest.getAmount();
        notification.setCreatedAt(transactionRequest.getCreatedAt());
        notification.setMessage(message);
        notification.setStudent(studentEntity);
        return notificationDao.saveRecord(notification);
    }


    @Override
    public NotificationEntity teacherReceivedNotification(TransactionRequest transactionRequest) {
        NotificationEntity notification = new NotificationEntity();
        Long teacherId = transactionRequest.getTeacherId();
        TeacherEntity teacher = teacherDao.findById(teacherId).orElse(null);
        if (teacher == null) {
            throw new CustomNotFoundException(" Id " + teacherId + " is not valid");
        }
        String message = "You received " + transactionRequest.getAmount();
        notification.setCreatedAt(LocalDateTime.now());
        notification.setMessage(message);
        notification.setTeacher(teacher);
        return notificationDao.saveRecord(notification);
    }

    @Override
    public List<NotificationResponse> allNotificationsOfA_StudentById() {
        String email = UserDetails.getLoggedInUserDetails();
        AppUserEntity userEntity = appuser.findAppUserEntityByEmail(email);
        StudentEntity student = studentDao.getStudentEntityByAppUserEntity(userEntity);
        List<NotificationEntity> notificationEntity = notificationDao.findNotificationEntitiesByStudentOrderByCreatedAtDesc(student);

        if (notificationEntity.isEmpty()) {
            throw new CustomNotFoundException("Notification is empty");
        }
        return notificationEntity.stream()
                .map(n -> new NotificationResponse(n.getMessage(), LocalDateTimeConverter.localDateTimeConverter(n.getCreatedAt())))
                .toList();
    }

    @Override
    public List<NotificationResponse> allNotificationsOfA_TeacherById() {
        String email = UserDetails.getLoggedInUserDetails();
        AppUserEntity appUserEntity = appuser.findAppUserEntityByEmail(email);
        TeacherEntity teacher = teacherDao.getTeacherEntityByAppUserEntity(appUserEntity);
        List<NotificationEntity> notificationEntity = notificationDao.findNotificationEntitiesByTeacherOrderByCreatedAtDesc(teacher);
        if (notificationEntity.isEmpty()) {
            throw new CustomNotFoundException("Notification is empty");
        }
        return notificationEntity.stream()
                .map(n -> new NotificationResponse(n.getMessage(),LocalDateTimeConverter.localDateTimeConverter(n.getCreatedAt())))
                .toList();
    }

    @Override
    public NotificationResponse studentAppreciatedNotification(Long transactionId) {

        TransactionEntity transaction = transactionDao.getRecordById(transactionId);
        NotificationEntity notificationEntity = notificationDao.findNotificationEntityByTransactionOrderByCreatedAtDesc(transaction);

        if (notificationEntity.isAppreciated()){
            throw new CustomNotFoundException("You have already appreciated this student");
        }
          notificationEntity.setAppreciated(true);
        String grinningFace = "\uD83D\uDC4D";
        NotificationEntity notification =new NotificationEntity();
        notification.setStudent(transaction.getStudent());
        notification.setNotificationType(NotificationType.APPRECIATION_NOTIFICATION);
        notification.setMessage(notificationEntity.getTeacher().getName()+" Appreciated you "+grinningFace);
        notificationDao.saveRecord(notification);
        notificationDao.saveRecord(notificationEntity);
        return new NotificationResponse("success",LocalDateTimeConverter.localDateTimeConverter(notification.getCreatedAt()));
    }

}
