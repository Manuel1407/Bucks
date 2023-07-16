package com.decagon.swisspay.usecase.services;

import com.decagon.swisspay.usecase.payload.request.StudentProfileRequest;
import com.decagon.swisspay.usecase.payload.request.TeacherProfileRequest;
import com.decagon.swisspay.usecase.payload.response.EditProfileResponse;
import com.decagon.swisspay.usecase.payload.response.ViewTeacherProfileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProfileService {
   EditProfileResponse editStudentProfile(StudentProfileRequest studentProfileRequest);
   EditProfileResponse editTeacherProfile(TeacherProfileRequest teacherProfileRequest, MultipartFile file) throws IOException;
   List<ViewTeacherProfileResponse> viewTeacherByName(String name);
}
