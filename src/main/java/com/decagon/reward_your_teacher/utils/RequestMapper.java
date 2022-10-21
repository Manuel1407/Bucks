package com.decagon.reward_your_teacher.utils;

import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.usecase.payload.response.ViewTeacherProfileResponse;
import org.springframework.stereotype.Service;

@Service
public class RequestMapper {

    public ViewTeacherProfileResponse viewTeacherProfileResponseToTeacherEntityMapper(TeacherEntity teacher){
        ViewTeacherProfileResponse viewTeacherProfileResponse = new ViewTeacherProfileResponse();
        if(teacher.getName() != null){
            viewTeacherProfileResponse.setName(teacher.getName());
        }
        if(teacher.getAppUserEntity().getEmail() != null){
            viewTeacherProfileResponse.setEmail(teacher.getAppUserEntity().getEmail());
        }
        if(teacher.getSchool().getSchoolName() != null){
            viewTeacherProfileResponse.setSchoolName(teacher.getSchool().getSchoolName());
        }
        if(teacher.getDisplayPicture() != null){
            viewTeacherProfileResponse.setProfilePicture(teacher.getDisplayPicture());
        }
        if(teacher.getPhoneNumber() != null) {
            viewTeacherProfileResponse.setPhoneNumber(teacher.getPhoneNumber());
        }
        if(teacher.getAbout() != null){
            viewTeacherProfileResponse.setAbout(teacher.getAbout());
        }
        if(teacher.getPosition() != null) {
            viewTeacherProfileResponse.setPosition(teacher.getPosition().toString());
        }
        return viewTeacherProfileResponse;

    }



}
