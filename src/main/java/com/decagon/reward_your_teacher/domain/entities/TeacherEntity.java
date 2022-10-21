package com.decagon.reward_your_teacher.domain.entities;

import com.decagon.reward_your_teacher.domain.entities.enums.Position;
import com.decagon.reward_your_teacher.domain.entities.enums.Status;
import com.decagon.reward_your_teacher.domain.entities.message.NotificationEntity;
import com.decagon.reward_your_teacher.domain.entities.transact.TransactionEntity;
import com.decagon.reward_your_teacher.domain.entities.transact.WalletEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "teachers")
public class TeacherEntity extends AbstractEntity {

    @Column(nullable = false,columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(unique = true,columnDefinition = "VARCHAR(100)")
    private String phoneNumber;

    private String yearsOfTeaching;

    @Column(columnDefinition = "VARCHAR(100)")
    private String subjectsTaught;

    @Enumerated(value = EnumType.STRING)
    private Position position;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "teacher_status")
    private Status status;

    @Column(columnDefinition = "VARCHAR(250)")
    private String about;

    @Column(columnDefinition = "VARCHAR(100)")
    private String nin;

   @Column(columnDefinition = "VARCHAR(100)")
    private String displayPicture;

    @ManyToOne
    @JoinColumn
    private SchoolEntity school;


    @JsonManagedReference
    @OneToOne(mappedBy = "teacher", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private WalletEntity wallet;

    @JsonManagedReference
    @OneToMany(mappedBy = "teacher", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<TransactionEntity> transactionList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "teacher", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<NotificationEntity> notificationList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<StudentEntity> studentList = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    private AppUserEntity appUserEntity;

}
