package com.decagon.reward_your_teacher.domain.entities.transact;

import com.decagon.reward_your_teacher.domain.entities.AbstractEntity;
import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "wallet")
public class WalletEntity extends AbstractEntity {

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 9, fraction = 2)
    @Column(
            name = "balance",
            nullable = false,
            columnDefinition = "NUMERIC(11,2) DEFAULT 0.0"
    )
    private BigDecimal balance;

    @Column(
            name = "totalMoneySent",
            nullable = false,
            columnDefinition = "NUMERIC(11,2) DEFAULT 0.0"
    )
    private BigDecimal totalMoneySent;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity student;
    @OneToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private TeacherEntity teacher;

}
