package com.decagon.reward_your_teacher.domain.entities.transact;

import com.decagon.reward_your_teacher.domain.entities.AbstractEntity;
import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "transaction")
public class TransactionEntity extends AbstractEntity {

    private String transactionType;
    private String description;
    private BigDecimal amount;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    private StudentEntity student;

    @ManyToOne
    private TeacherEntity teacher;

}
