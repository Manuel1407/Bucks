package com.decagon.swisspay.infrastructure.persistence.repository;

import com.decagon.swisspay.domain.entities.AppUserEntity;
import com.decagon.swisspay.domain.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity getStudentEntityByAppUserEntity(AppUserEntity appUserEntity);
}
