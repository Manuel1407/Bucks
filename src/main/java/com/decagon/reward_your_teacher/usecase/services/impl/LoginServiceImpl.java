package com.decagon.reward_your_teacher.usecase.services.impl;

import com.decagon.reward_your_teacher.domain.dao.AppUserDao;
import com.decagon.reward_your_teacher.domain.dao.StudentDao;
import com.decagon.reward_your_teacher.domain.dao.TeacherDao;
import com.decagon.reward_your_teacher.domain.dao.WalletDao;
import com.decagon.reward_your_teacher.domain.entities.AppUserEntity;
import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.domain.entities.enums.Role;
import com.decagon.reward_your_teacher.domain.entities.transact.WalletEntity;
import com.decagon.reward_your_teacher.infrastructure.configuration.security.JwtService;
import com.decagon.reward_your_teacher.infrastructure.error_handler.AuthenticationFailedException;
import com.decagon.reward_your_teacher.infrastructure.error_handler.CustomNotFoundException;
import com.decagon.reward_your_teacher.infrastructure.error_handler.EntityAlreadyExistException;
import com.decagon.reward_your_teacher.usecase.payload.request.LoginRequest;
import com.decagon.reward_your_teacher.usecase.payload.request.SocialLoginRequest;
import com.decagon.reward_your_teacher.usecase.payload.response.LoginResponse;
import com.decagon.reward_your_teacher.usecase.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@AllArgsConstructor
@Transactional
public class LoginServiceImpl implements LoginService {
    private final UserDetailsService userService;
    private final StudentDao studentDao;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TeacherDao teacherDao;
    private final AppUserDao appUserDao;
    private final PasswordEncoder passwordEncoder;
    private final WalletDao walletDao;

    @Override
    public LoginResponse loginStudent(LoginRequest studentLoginRequest)  {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(studentLoginRequest
                        .getEmail(), studentLoginRequest.getPassword()));
        if (!auth.isAuthenticated() ) {
            throw new AuthenticationFailedException("Wrong email or password");
        }
        AppUserEntity appUserEntity = appUserDao
                .findAppUserEntityByEmailAndRole(studentLoginRequest.getEmail(),Role.STUDENT);
        if(appUserEntity == null){
           throw new CustomNotFoundException("Invalid Email or password");
        }
        if(!appUserEntity.getRole().equals(Role.STUDENT)){
            throw new AuthenticationFailedException("Unauthorised");
        }
        if(!appUserEntity.isVerified()){
            throw new AuthenticationFailedException("User not verified");
        }
        StudentEntity student = studentDao.getStudentEntityByAppUserEntity(appUserEntity);
        String token = "Bearer " + jwtService
                .generateToken(new org.springframework.security.core.userdetails
                        .User(studentLoginRequest.getEmail(), studentLoginRequest.getPassword(), new ArrayList<>()));
        return new LoginResponse(token,student.getName(),student.getDisplayPicture());
    }

    @Override
    public LoginResponse studentSocialLogin(SocialLoginRequest socialLoginRequest) {
        if(socialLoginRequest.getEmail().equals("")){
            throw new CustomNotFoundException("Enter email");
        }
        socialLoginRequest.setPassword("");
        AppUserEntity appUser = appUserDao.findAppUserEntityByEmailAndRole(socialLoginRequest.getEmail(),Role.STUDENT);
        if (appUser == null) {
            AppUserEntity appUser1 =  appUserDao.findAppUserEntityByEmailAndRole(socialLoginRequest.getEmail(),Role.TEACHER);
            if(appUser1!= null){
                throw new CustomNotFoundException("Already Registered as a Teacher");
            }else {
                AppUserEntity appUserEntity = AppUserEntity.builder()
                        .password(passwordEncoder.encode(socialLoginRequest.getPassword()))
                        .email(socialLoginRequest.getEmail())
                        .isVerified(true)
                        .role(Role.STUDENT)
                        .build();
                AppUserEntity user = appUserDao.saveRecord(appUserEntity);

                String name = socialLoginRequest.getFirstName() + " " + socialLoginRequest.getLastName();
                StudentEntity studentEntity = StudentEntity.builder()
                        .name(name)
                        .displayPicture(socialLoginRequest.getDisplayPicture())
                        .appUserEntity(user).build();

                studentDao.saveRecord(studentEntity);
                WalletEntity wallet = new WalletEntity();
                wallet.setBalance(new BigDecimal("0.00"));
                wallet.setStudent(studentEntity);
                wallet.setTotalMoneySent(new BigDecimal("0.00"));
                walletDao.saveRecord(wallet);
                StudentEntity student = studentDao.getStudentEntityByAppUserEntity(appUserEntity);
                String token = "Bearer " + jwtService.generateToken(new org.springframework.security.core
                        .userdetails.User(socialLoginRequest.getEmail(), socialLoginRequest
                        .getFirstName(), new ArrayList<>()));
                return new LoginResponse(token,student.getName(),student.getDisplayPicture());
            }

        }
        StudentEntity student = studentDao.getStudentEntityByAppUserEntity(appUser);
        String token = "Bearer " + jwtService.generateToken(new org.springframework.security.core
                .userdetails.User(socialLoginRequest.getEmail(), socialLoginRequest
                .getFirstName(), new ArrayList<>()));
        return new LoginResponse(token,student.getName(),student.getDisplayPicture());
    }

    @Override
    public LoginResponse loginTeacher(LoginRequest teacherLoginRequest)  {


      UserDetails userDetailsService = userService.loadUserByUsername(teacherLoginRequest.getEmail());
      if(userDetailsService == null){
          throw new CustomNotFoundException("Wrong email");
      }

        if(!(passwordEncoder.matches(teacherLoginRequest.getPassword(),userDetailsService.getPassword()))){
            throw new CustomNotFoundException("Wrong password");
        }

        //SecurityContextHolder.getContext().setAuthentication(auth);


        AppUserEntity appUserEntity = appUserDao
                .findAppUserEntityByEmailAndRole(teacherLoginRequest.getEmail(),Role.TEACHER);
        if(appUserEntity == null){
           throw new CustomNotFoundException("User does not exist");
        }

        if(!appUserEntity.isVerified()){
            throw new AuthenticationFailedException("User not verified");
        }
        TeacherEntity teacher = teacherDao.getTeacherEntityByAppUserEntity(appUserEntity);
        String token = "Bearer " + jwtService
                .generateToken(new org.springframework.security.core.userdetails
                        .User(teacherLoginRequest.getEmail(), teacherLoginRequest.getPassword(), new ArrayList<>()));
        return new LoginResponse(token,teacher.getName(),teacher.getDisplayPicture());

    }

    @Override
    public LoginResponse teacherSocialLogin(SocialLoginRequest socialLoginRequest) {
        if(socialLoginRequest.getEmail().equals("")){
            throw new EntityAlreadyExistException("Enter email");
        }
        socialLoginRequest.setPassword("");

        AppUserEntity appUser = appUserDao.findAppUserEntityByEmailAndRole(socialLoginRequest.getEmail(),Role.TEACHER);
        if(appUser == null){
        AppUserEntity appUser1 =  appUserDao.findAppUserEntityByEmailAndRole(socialLoginRequest.getEmail(),Role.STUDENT);
            if(appUser1!= null){
                throw new CustomNotFoundException("Already Registered as a student");
            }
            else {
                AppUserEntity appUserEntity = AppUserEntity.builder()
                        .password(passwordEncoder.encode(socialLoginRequest.getPassword()))
                        .email(socialLoginRequest.getEmail())
                        .role(Role.TEACHER)
                        .isVerified(true)
                        .build();
                AppUserEntity user = appUserDao.saveRecord(appUserEntity);

                String name = socialLoginRequest.getFirstName() + " " + socialLoginRequest.getLastName();
                TeacherEntity teacherEntity = TeacherEntity.builder()
                        .name(name)
                        .appUserEntity(user)
                        .displayPicture(socialLoginRequest.getDisplayPicture()).build();
                teacherDao.saveRecord(teacherEntity);
                WalletEntity wallet = new WalletEntity();
                wallet.setBalance(new BigDecimal("0.00"));
                wallet.setTeacher(teacherEntity);
                wallet.setTotalMoneySent(new BigDecimal("0.00"));
                walletDao.saveRecord(wallet);
                TeacherEntity teacher = teacherDao.getTeacherEntityByAppUserEntity(appUserEntity);
                String token = "Bearer " + jwtService.generateToken(new org.springframework.security.core
                        .userdetails.User(socialLoginRequest.getEmail(), socialLoginRequest
                        .getFirstName(), new ArrayList<>()));
                return new LoginResponse(token,teacher.getName(),teacher.getDisplayPicture());
            }
        }

        TeacherEntity teacher = teacherDao.getTeacherEntityByAppUserEntity(appUser);
        String token = "Bearer " + jwtService.generateToken(new org.springframework.security.core
                .userdetails.User(socialLoginRequest.getEmail(), socialLoginRequest
                .getFirstName(), new ArrayList<>()));
        return new LoginResponse(token,teacher.getName(),teacher.getDisplayPicture());
    }
}
