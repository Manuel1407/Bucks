package com.decagon.reward_your_teacher.usecase.services.impl;

import com.decagon.reward_your_teacher.domain.dao.*;
import com.decagon.reward_your_teacher.domain.entities.AppUserEntity;
import com.decagon.reward_your_teacher.domain.entities.SchoolEntity;
import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.domain.entities.email.ConfirmationTokenEntity;
import com.decagon.reward_your_teacher.domain.entities.enums.Position;
import com.decagon.reward_your_teacher.domain.entities.enums.Role;
import com.decagon.reward_your_teacher.domain.entities.enums.Status;
import com.decagon.reward_your_teacher.domain.entities.transact.WalletEntity;
import com.decagon.reward_your_teacher.infrastructure.error_handler.CustomNotFoundException;
import com.decagon.reward_your_teacher.infrastructure.error_handler.EntityAlreadyExistException;
import com.decagon.reward_your_teacher.usecase.payload.request.EmailDetailsRequest;
import com.decagon.reward_your_teacher.usecase.payload.request.StudentRegistrationRequest;
import com.decagon.reward_your_teacher.usecase.payload.request.TeacherRegistrationRequest;
import com.decagon.reward_your_teacher.usecase.payload.response.RegistrationResponse;
import com.decagon.reward_your_teacher.usecase.services.ConfirmationTokenService;
import com.decagon.reward_your_teacher.usecase.services.RegisterService;
import com.decagon.reward_your_teacher.utils.CloudinaryService;
import com.decagon.reward_your_teacher.utils.EmailService;
import com.decagon.reward_your_teacher.utils.PayLoadMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final StudentDao studentDao;
    private final SchoolDao schoolDao;

    private final PasswordEncoder passwordEncoder;
    private final TeacherDao teacherDao;
    private final CloudinaryService cloudinaryService;
    private final PayLoadMapper payLoadMapper;
    private final AppUserDao appUserDao;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;

    private final WalletDao walletDao;

    @Override
    public RegistrationResponse registerTeacher(@Valid TeacherRegistrationRequest teacherRegistrationRequest, MultipartFile file) throws Exception {
        AppUserEntity appUser = appUserDao.findAppUserEntityByEmail(teacherRegistrationRequest.getEmail());
        if (appUser != null) {
            throw new EntityAlreadyExistException("Email taken");
        }
        Optional<TeacherEntity> teacherPhoneNumber = teacherDao.findTeacherEntityByPhoneNumber(teacherRegistrationRequest.getPhoneNumber());
        if(teacherPhoneNumber.isPresent()){
            throw new EntityAlreadyExistException("Phone number already taken");
        }

        SchoolEntity school = schoolDao.findSchool(teacherRegistrationRequest.getSchool())
                .orElseThrow(() -> new CustomNotFoundException("School not found"));
        String fileUrl = cloudinaryService.uploadImage(file);
        AppUserEntity appUserEntity = AppUserEntity.builder()
                .email(teacherRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(teacherRegistrationRequest.getPassword()))
                .role((Role.TEACHER))
                .build();
       AppUserEntity appUserEntity1 = appUserDao.saveRecord(appUserEntity);

        TeacherEntity teacher1 = TeacherEntity.builder()
                .name(teacherRegistrationRequest.getName())
                .phoneNumber(teacherRegistrationRequest.getPhoneNumber())
                .school(school)
                .nin(fileUrl)
                .displayPicture(fileUrl)
                .yearsOfTeaching(teacherRegistrationRequest.getYearsOfTeaching())
                .subjectsTaught(teacherRegistrationRequest.getSubjectTaught())
                .appUserEntity(appUserEntity1)
                .position(Position.valueOf(teacherRegistrationRequest.getPosition().toUpperCase()))
                .status(Status.valueOf(teacherRegistrationRequest.getStatus().toUpperCase()))
                .about(teacherRegistrationRequest.getAbout())
                .build();

        RegistrationResponse response =payLoadMapper.teacherEntityMapper(teacherDao.saveRecord(teacher1));
        String token = UUID.randomUUID().toString();
        EmailDetailsRequest emailDetailsRequest = EmailDetailsRequest.builder()
                .msgBody(emailService.buildVerificationEmail(teacherRegistrationRequest.getName(),"http://localhost:9001/api/v1/register/verification?token=" + token))
                .subject("PodA email")
                .recipient(teacherRegistrationRequest.getEmail())
                .attachment(fileUrl).build();
        emailService.sendMailWithAttachment(emailDetailsRequest);

        ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(
                token,
                LocalDateTime.now().plusMinutes(15),
                appUserEntity1
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        WalletEntity wallet = new WalletEntity();
        wallet.setBalance(new BigDecimal("0.00"));
        wallet.setTeacher(teacher1);
        wallet.setTotalMoneySent(new BigDecimal("0.00"));
        walletDao.saveRecord(wallet);

        return response;

    }

    @Override
    public RegistrationResponse registerStudent(StudentRegistrationRequest studentRegistrationRequest) throws Exception {
        AppUserEntity appUser = appUserDao.findAppUserEntityByEmail(studentRegistrationRequest.getEmail());
        if (appUser != null ) {
            throw new EntityAlreadyExistException("Email already taken");
        }

        SchoolEntity school = schoolDao.findSchool(studentRegistrationRequest.getSchoolName())
                .orElseThrow(() -> new CustomNotFoundException("School not found"));

        AppUserEntity appUserEntity = AppUserEntity.builder()
                .email(studentRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(studentRegistrationRequest.getPassword()))
                .role(Role.STUDENT)
                .build();
        AppUserEntity appUserEntity1 = appUserDao.saveRecord(appUserEntity);
        StudentEntity student = StudentEntity
                .builder()
                .name(studentRegistrationRequest.getName())
                .phoneNumber(studentRegistrationRequest.getPhoneNumber())
                .school(school)
                .appUserEntity(appUserEntity1)
                .build();
        RegistrationResponse response =payLoadMapper.studentEntityMapper(studentDao.saveRecord(student));
        String token = UUID.randomUUID().toString();
        EmailDetailsRequest emailDetailsRequest = EmailDetailsRequest.builder()
                .msgBody(emailService.buildVerificationEmail(studentRegistrationRequest.getName(),"http://localhost:9001/api/v1/register/verification?token=" + token))
                .subject("PodA email")
                .recipient(studentRegistrationRequest.getEmail())
                .build();
        emailService.sendMailWithAttachment(emailDetailsRequest);

        ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUserEntity1
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        WalletEntity wallet = new WalletEntity();
        wallet.setBalance(new BigDecimal("0.00"));
        wallet.setStudent(student);
        wallet.setTotalMoneySent(new BigDecimal("0.00"));
        walletDao.saveRecord(wallet);

        return response;
    }

    @Override
    public Object verifyUser(String userToken) {
        ConfirmationTokenEntity confirmationToken = confirmationTokenService.getToken(userToken);
        if (confirmationToken == null) {
            throw new CustomNotFoundException("token not found");
        }
        if (confirmationToken.getConfirmedAt() != null) {
            throw new EntityAlreadyExistException("email already confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new CustomNotFoundException("token expired");
        }
        confirmationTokenService.setConfirmedAt(userToken);
        confirmationToken.getAppUser().setVerified(true);
        return "confirmed";
    }
}
