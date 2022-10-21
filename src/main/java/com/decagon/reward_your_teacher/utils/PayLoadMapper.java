package com.decagon.reward_your_teacher.utils;

import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.usecase.payload.response.EditProfileResponse;
import com.decagon.reward_your_teacher.usecase.payload.response.RegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public class PayLoadMapper {
    public RegistrationResponse teacherEntityMapper(TeacherEntity teacher) {
        RegistrationResponse teacher1 = new RegistrationResponse();

        if (teacher.getName() != null) {
            teacher1.setName(teacher.getName());
        }
        if (teacher.getAppUserEntity().getEmail() != null) {
            teacher1.setEmail((teacher.getAppUserEntity().getEmail()));
        }
        if (teacher.getId() != null) {
            teacher1.setId(teacher.getId());
        }
        return teacher1;

    }

    public RegistrationResponse studentEntityMapper(StudentEntity student) {
        RegistrationResponse student1 = new RegistrationResponse();

        if (student.getName() != null) {
            student1.setName(student.getName());
        }
        if (student.getAppUserEntity().getEmail() != null) {
            student1.setEmail((student.getAppUserEntity().getEmail()));
        }
        if (student.getId() != null) {
            student1.setId(student.getId());
        }
        return student1;

    }

    public EditProfileResponse studentEditMapper(StudentEntity student) {
        EditProfileResponse student1 = new EditProfileResponse();

        if (student.getName() != null) {
            student1.setName(student.getName());
        }
        if (student.getAppUserEntity().getEmail() != null) {
            student1.setEmail((student.getAppUserEntity().getEmail()));
        }
        if (student.getId() != null) {
            student1.setId(student.getId());
        }
        return student1;
    }

    public EditProfileResponse TeacherEditMapper(TeacherEntity teacher) {
        EditProfileResponse teacher2 = new EditProfileResponse();

        if (teacher.getName() != null) {
            teacher2.setName(teacher.getName());
        }
        if (teacher.getAppUserEntity().getEmail() != null) {
            teacher2.setEmail((teacher.getAppUserEntity().getEmail()));
        }
        if (teacher.getId() != null) {
            teacher2.setId(teacher.getId());
        }
        return teacher2;
    }
}