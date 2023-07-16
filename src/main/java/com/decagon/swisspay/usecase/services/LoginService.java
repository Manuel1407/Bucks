package com.decagon.swisspay.usecase.services;

import com.decagon.swisspay.usecase.payload.request.LoginRequest;
import com.decagon.swisspay.usecase.payload.request.SocialLoginRequest;
import com.decagon.swisspay.usecase.payload.response.LoginResponse;

public interface LoginService {
    LoginResponse loginStudent(LoginRequest loginRequest);

    LoginResponse studentSocialLogin(SocialLoginRequest socialLoginRequest);

    LoginResponse loginTeacher(LoginRequest loginRequest);

    LoginResponse teacherSocialLogin(SocialLoginRequest socialLoginRequest);
}
