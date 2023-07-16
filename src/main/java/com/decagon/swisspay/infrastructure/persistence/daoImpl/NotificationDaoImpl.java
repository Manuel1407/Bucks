package com.decagon.swisspay.infrastructure.persistence.daoImpl;

import com.decagon.swisspay.domain.dao.NotificationDao;
import com.decagon.swisspay.domain.entities.StudentEntity;
import com.decagon.swisspay.domain.entities.TeacherEntity;
import com.decagon.swisspay.domain.entities.message.NotificationEntity;
import com.decagon.swisspay.domain.entities.transact.TransactionEntity;
import com.decagon.swisspay.infrastructure.persistence.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotificationDaoImpl extends CrudDaoImpl<NotificationEntity, Long> implements NotificationDao {
    private final NotificationRepository notificationRepository;

    protected NotificationDaoImpl(NotificationRepository notificationRepository) {
        super(notificationRepository);
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<NotificationEntity> findNotificationEntitiesByStudentOrderByCreatedAtDesc(StudentEntity student) {
        return notificationRepository.findNotificationEntitiesByStudentOrderByCreatedAtDesc(student);
    }

    @Override
    public List<NotificationEntity> findNotificationEntitiesByTeacherOrderByCreatedAtDesc(TeacherEntity teacher) {
        return notificationRepository.findNotificationEntitiesByTeacherOrderByCreatedAtDesc(teacher);
    }

    @Override
    public NotificationEntity findNotificationEntityByTeacherAndStudentOrderByCreatedAtDesc(TeacherEntity teacher, StudentEntity student) {
     return notificationRepository.findNotificationEntityByTeacherAndStudentOrderByCreatedAtDesc(teacher, student);
    }

    @Override
    public NotificationEntity findNotificationEntityByTransactionOrderByCreatedAtDesc(TransactionEntity transaction) {
        return notificationRepository.findNotificationEntityByTransactionOrderByCreatedAtDesc(transaction);
    }

}
