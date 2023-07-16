package com.decagon.swisspay.domain.dao;


import com.decagon.swisspay.domain.entities.StudentEntity;
import com.decagon.swisspay.domain.entities.TeacherEntity;
import com.decagon.swisspay.domain.entities.transact.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionDao extends CrudDao<TransactionEntity, Long> {
    Page<TransactionEntity> findTransactionEntitiesByStudentOrderByCreatedAtDesc(Pageable pageable, StudentEntity student);
    Page<TransactionEntity> findTransactionEntitiesByTeacherOrderByCreatedAtDesc(Pageable pageable, TeacherEntity teacher);
}
