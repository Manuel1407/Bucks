package com.decagon.swisspay.infrastructure.persistence.repository;

import com.decagon.swisspay.domain.entities.StudentEntity;
import com.decagon.swisspay.domain.entities.TeacherEntity;
import com.decagon.swisspay.domain.entities.message.NotificationEntity;
import com.decagon.swisspay.domain.entities.transact.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findNotificationEntitiesByStudentOrderByCreatedAtDesc(StudentEntity student);

    List<NotificationEntity> findNotificationEntitiesByTeacherOrderByCreatedAtDesc(TeacherEntity teacher);
    NotificationEntity findNotificationEntityByTeacherAndStudentOrderByCreatedAtDesc(TeacherEntity teacher, StudentEntity student);
    NotificationEntity findNotificationEntityByTransactionOrderByCreatedAtDesc(TransactionEntity transaction);
}
