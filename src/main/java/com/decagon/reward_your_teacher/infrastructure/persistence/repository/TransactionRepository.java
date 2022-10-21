package com.decagon.reward_your_teacher.infrastructure.persistence.repository;

import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.domain.entities.transact.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    Page<TransactionEntity> findTransactionEntitiesByStudentOrderByCreatedAtDesc(Pageable pageable, StudentEntity student);
    Page<TransactionEntity> findTransactionEntitiesByTeacherOrderByCreatedAtDesc(Pageable pageable, TeacherEntity teacher);
}
