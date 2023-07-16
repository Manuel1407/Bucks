package com.decagon.swisspay.domain.dao;

import com.decagon.swisspay.domain.entities.StudentEntity;
import com.decagon.swisspay.domain.entities.TeacherEntity;
import com.decagon.swisspay.domain.entities.message.NotificationEntity;
import com.decagon.swisspay.domain.entities.transact.TransactionEntity;

import java.util.List;


public interface NotificationDao extends CrudDao<NotificationEntity, Long> {
  List<NotificationEntity> findNotificationEntitiesByStudentOrderByCreatedAtDesc(StudentEntity student);

  List<NotificationEntity> findNotificationEntitiesByTeacherOrderByCreatedAtDesc(TeacherEntity teacher);
  NotificationEntity findNotificationEntityByTeacherAndStudentOrderByCreatedAtDesc(TeacherEntity teacher, StudentEntity student);
  NotificationEntity findNotificationEntityByTransactionOrderByCreatedAtDesc(TransactionEntity transaction);


}
