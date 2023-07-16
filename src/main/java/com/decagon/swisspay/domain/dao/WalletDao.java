package com.decagon.swisspay.domain.dao;

import com.decagon.swisspay.domain.entities.StudentEntity;
import com.decagon.swisspay.domain.entities.TeacherEntity;
import com.decagon.swisspay.domain.entities.transact.WalletEntity;

import java.util.Optional;

public interface WalletDao extends CrudDao<WalletEntity, Long> {
    WalletEntity findWalletEntityByStudent(StudentEntity student);
    Optional<WalletEntity> findWalletEntityByTeacher(TeacherEntity teacher);
}
