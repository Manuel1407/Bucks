package com.decagon.reward_your_teacher.domain.entities.message;

import com.decagon.reward_your_teacher.domain.entities.AbstractEntity;
import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chat")
public class ChatEntity extends AbstractEntity {

    @Column(
            name = "message",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String message;

    @ManyToOne
    @JoinColumn(name = "teacher_chat_id")
    private TeacherEntity teacher_id;

    @ManyToOne
    @JoinColumn(name = "student_chat_id")
    private StudentEntity student_id;

    @CreationTimestamp
    @Column(name = "send_date", nullable = false, updatable = false)
    private LocalDateTime sendDate;

    @CreationTimestamp
    @Column(name = "received_date", nullable = false, updatable = false)
    private LocalDateTime receivedDate;

}
