package com.decagon.reward_your_teacher.domain.dao;


import com.decagon.reward_your_teacher.domain.entities.AppUserEntity;
import com.decagon.reward_your_teacher.domain.entities.StudentEntity;

public interface StudentDao extends CrudDao<StudentEntity, Long> {
    StudentEntity getStudentEntityByAppUserEntity(AppUserEntity appUserEntity);

}
