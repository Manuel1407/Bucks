package com.decagon.swisspay.domain.dao;

import com.decagon.swisspay.domain.entities.AppUserEntity;
import com.decagon.swisspay.domain.entities.SchoolEntity;
import com.decagon.swisspay.domain.entities.TeacherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface TeacherDao extends CrudDao<TeacherEntity, Long> {

    Page<TeacherEntity> findTeacherEntitiesBySchool(List<SchoolEntity> school, Pageable pageable);

    Page<TeacherEntity> findAllTeachers(Pageable pageable);
    TeacherEntity getTeacherEntityByAppUserEntity(AppUserEntity appUserEntity);
    Optional<TeacherEntity> findTeacherEntityByPhoneNumber(String phoneNumber);
    List<TeacherEntity> findTeacherEntitiesByNameIsContainingIgnoreCase(String name);
    Optional<TeacherEntity> findTeacherEntityByNin(String nin);
    Optional<List<TeacherEntity>> findTeacherEntitiesByName(String name);
    TeacherEntity getTeacherByNameAndPhoneNumber(String name, String phoneNumber);
}
