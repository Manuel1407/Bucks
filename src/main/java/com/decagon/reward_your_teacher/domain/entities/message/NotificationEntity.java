package com.decagon.reward_your_teacher.domain.entities.message;

import com.decagon.reward_your_teacher.domain.entities.AbstractEntity;
import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.domain.entities.enums.NotificationType;
import com.decagon.reward_your_teacher.domain.entities.transact.TransactionEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "notification")

public class NotificationEntity extends AbstractEntity {

    @Column(
            name = "message",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @JsonBackReference
    @ManyToOne
    private StudentEntity student;

    @JsonBackReference
    @ManyToOne
    private TeacherEntity teacher;

    private boolean isAppreciated = false;

    @OneToOne
    private TransactionEntity transaction;
}
