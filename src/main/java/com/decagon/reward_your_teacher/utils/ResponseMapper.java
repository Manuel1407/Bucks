package com.decagon.reward_your_teacher.utils;

import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.usecase.payload.response.TeacherSearchResponse;
import org.springframework.stereotype.Service;

@Service
public class ResponseMapper {

    public TeacherSearchResponse teacherSearchResponseToTeacherEntityMapper(TeacherEntity teacher){
        TeacherSearchResponse teacherSearchResponse = new TeacherSearchResponse();
        if(teacher.getName() != null){
            teacherSearchResponse.setName(teacher.getName());
        }
        if(teacher.getSchool() != null){
            teacherSearchResponse.setSchool(teacher.getSchool().getSchoolName());
        }
        if(teacher.getYearsOfTeaching() != null){
            teacherSearchResponse.setYearsOfTeaching(teacher.getYearsOfTeaching());
        }
        if(teacher.getPosition() != null){
            teacherSearchResponse.setPosition(teacher.getPosition().toString());
        }
        if(teacher.getAppUserEntity().getEmail() != null){
            teacherSearchResponse.setEmail(teacher.getAppUserEntity().getEmail());
        }
        if(teacher.getPhoneNumber() != null){
            teacherSearchResponse.setPhoneNumber(teacher.getPhoneNumber());
        }
        if(teacher.getAbout() != null){
            teacherSearchResponse.setAbout(teacher.getAbout());
        }
        if(teacher.getDisplayPicture() != null){
            teacherSearchResponse.setDisplayPicture(teacher.getDisplayPicture());
        }
        return teacherSearchResponse;
    }

}
