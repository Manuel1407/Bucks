package com.decagon.reward_your_teacher.infrastructure.persistence.repository;

import com.decagon.reward_your_teacher.domain.entities.AppUserEntity;
import com.decagon.reward_your_teacher.domain.entities.SchoolEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

    Page<TeacherEntity> findTeacherEntitiesBySchoolIn(List<SchoolEntity> school, Pageable pageable);

    Page<TeacherEntity> findAll(Pageable pageable);

    Optional<List<TeacherEntity>> findTeacherEntitiesByNameIsContainingIgnoreCase(String name);

    TeacherEntity getTeacherEntityByAppUserEntity(AppUserEntity appUserEntity);
    Optional<TeacherEntity> findTeacherEntityByPhoneNumber(String phoneNumber);

    Optional<TeacherEntity> findTeacherEntityByNin(String nin);
    Optional<List<TeacherEntity>> findTeacherEntitiesByName(String name);
    TeacherEntity getTeacherEntityByNameContainingIgnoreCaseAndPhoneNumber(String name, String phoneNumber);

}
