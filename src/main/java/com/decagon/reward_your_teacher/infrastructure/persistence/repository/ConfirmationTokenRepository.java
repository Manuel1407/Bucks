package com.decagon.reward_your_teacher.infrastructure.persistence.repository;

import com.decagon.reward_your_teacher.domain.entities.email.ConfirmationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity,Long> {
    ConfirmationTokenEntity findByToken (String token);
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationTokenEntity c " + "SET c.confirmedAt = ?2 " + "WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime now);
}
