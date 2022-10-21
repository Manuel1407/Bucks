package com.decagon.reward_your_teacher.domain.dao;

import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.domain.entities.message.NotificationEntity;
import com.decagon.reward_your_teacher.domain.entities.transact.TransactionEntity;

import java.util.List;


public interface NotificationDao extends CrudDao<NotificationEntity, Long> {
  List<NotificationEntity> findNotificationEntitiesByStudentOrderByCreatedAtDesc(StudentEntity student);

  List<NotificationEntity> findNotificationEntitiesByTeacherOrderByCreatedAtDesc(TeacherEntity teacher);
  NotificationEntity findNotificationEntityByTeacherAndStudentOrderByCreatedAtDesc(TeacherEntity teacher, StudentEntity student);
  NotificationEntity findNotificationEntityByTransactionOrderByCreatedAtDesc(TransactionEntity transaction);


}
