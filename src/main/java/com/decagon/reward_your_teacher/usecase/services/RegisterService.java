package com.decagon.reward_your_teacher.usecase.services;

import com.decagon.reward_your_teacher.usecase.payload.request.StudentRegistrationRequest;
import com.decagon.reward_your_teacher.usecase.payload.request.TeacherRegistrationRequest;
import com.decagon.reward_your_teacher.usecase.payload.response.RegistrationResponse;
import org.springframework.web.multipart.MultipartFile;

public interface RegisterService {
    RegistrationResponse registerStudent(StudentRegistrationRequest studentRegistrationRequest) throws Exception;

    RegistrationResponse registerTeacher(TeacherRegistrationRequest teacherRegistrationRequest,MultipartFile file) throws Exception;

    Object verifyUser(String userToken);
}
