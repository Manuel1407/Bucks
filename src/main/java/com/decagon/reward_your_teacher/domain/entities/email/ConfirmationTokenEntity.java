package com.decagon.reward_your_teacher.domain.entities.email;

import com.decagon.reward_your_teacher.domain.entities.AbstractEntity;
import com.decagon.reward_your_teacher.domain.entities.AppUserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationTokenEntity extends AbstractEntity {

    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    @Column
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "app_user_id")
    private AppUserEntity appUser;

    public ConfirmationTokenEntity( String token, LocalDateTime expiresAt, AppUserEntity appUser) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }

    public ConfirmationTokenEntity( String token,LocalDateTime createdAt, LocalDateTime expiresAt, AppUserEntity appUser) {
        super(createdAt);
        this.token = token;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}
