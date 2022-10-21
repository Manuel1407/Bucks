package com.decagon.reward_your_teacher.domain.entities;

import com.decagon.reward_your_teacher.domain.entities.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "app_user")
public class AppUserEntity extends AbstractEntity{
    @Column(unique = true,nullable = false,columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(name = "password",nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = "boolean default false")
    private boolean isVerified;

    @JsonManagedReference
    @OneToMany(mappedBy = "appUserEntity")
    private List<TeacherEntity> teacherEntities;

    @JsonManagedReference
    @OneToMany(mappedBy = "appUserEntity")
    private List<StudentEntity> studentEntities;
}
