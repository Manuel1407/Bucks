package com.decagon.reward_your_teacher.infrastructure.controllers;

import com.decagon.reward_your_teacher.usecase.payload.request.LoginRequest;
import com.decagon.reward_your_teacher.usecase.payload.request.SocialLoginRequest;
import com.decagon.reward_your_teacher.usecase.payload.response.ApiResponse;
import com.decagon.reward_your_teacher.usecase.payload.response.LoginResponse;
import com.decagon.reward_your_teacher.usecase.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/student")
    public ResponseEntity<ApiResponse<LoginResponse>> loginStudent(@Valid @RequestBody LoginRequest loginRequest){
        LoginResponse response  = loginService.loginStudent(loginRequest);
        return new ResponseEntity<>(new ApiResponse<>("Login Successful",true,response), HttpStatus.OK);
    }

    @PostMapping(value = "/teacher")
    public ResponseEntity<ApiResponse<LoginResponse>> loginTeacher(@Valid @RequestBody LoginRequest loginRequest)  {
        LoginResponse response = loginService.loginTeacher(loginRequest);
        return new ResponseEntity<>(new ApiResponse<>("Login Successful",true,response), HttpStatus.OK);
    }

    @PostMapping("/social/student")
    public ResponseEntity<ApiResponse<LoginResponse>> studentSocialLogin(@Valid @RequestBody SocialLoginRequest socialLoginRequest) {
       LoginResponse response = loginService.studentSocialLogin(socialLoginRequest);
        return new ResponseEntity<>(new ApiResponse<>("Login Successful",true,response),HttpStatus.OK);

    }

    @PostMapping("/social/teacher")
    public ResponseEntity<ApiResponse<Object>> teacherSocialLogin(@Valid @RequestBody SocialLoginRequest socialLoginRequest) {
        LoginResponse response = loginService.teacherSocialLogin(socialLoginRequest);
        return new ResponseEntity<>(new ApiResponse<>("Login Successful",true,response), HttpStatus.OK);
    }
}
