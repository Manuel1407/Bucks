package com.decagon.swisspay.infrastructure.persistence.repository;

import com.decagon.swisspay.domain.entities.StudentEntity;
import com.decagon.swisspay.domain.entities.TeacherEntity;
import com.decagon.swisspay.domain.entities.transact.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
    Optional<WalletEntity> findWalletEntityByTeacher(TeacherEntity teacher);

    WalletEntity findWalletEntityByStudent(StudentEntity student);
}
