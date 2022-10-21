package com.decagon.reward_your_teacher.domain.dao;


import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.domain.entities.transact.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionDao extends CrudDao<TransactionEntity, Long> {
    Page<TransactionEntity> findTransactionEntitiesByStudentOrderByCreatedAtDesc(Pageable pageable, StudentEntity student);
    Page<TransactionEntity> findTransactionEntitiesByTeacherOrderByCreatedAtDesc(Pageable pageable, TeacherEntity teacher);
}
