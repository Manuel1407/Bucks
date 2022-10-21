package com.decagon.reward_your_teacher.domain.dao;

import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.domain.entities.transact.WalletEntity;

import java.util.Optional;

public interface WalletDao extends CrudDao<WalletEntity, Long> {
    WalletEntity findWalletEntityByStudent(StudentEntity student);
    Optional<WalletEntity> findWalletEntityByTeacher(TeacherEntity teacher);
}
