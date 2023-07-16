package com.decagon.swisspay.domain.dao;


import com.decagon.swisspay.domain.entities.AppUserEntity;
import com.decagon.swisspay.domain.entities.StudentEntity;

public interface StudentDao extends CrudDao<StudentEntity, Long> {
    StudentEntity getStudentEntityByAppUserEntity(AppUserEntity appUserEntity);

}
