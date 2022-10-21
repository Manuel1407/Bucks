package com.decagon.reward_your_teacher.domain.dao;

import com.decagon.reward_your_teacher.domain.entities.email.ConfirmationTokenEntity;

import java.time.LocalDateTime;

public interface ConfirmationTokenDao extends CrudDao<ConfirmationTokenEntity, Long>{
    int updateConfirmedAt(String token, LocalDateTime now);
    ConfirmationTokenEntity findByToken (String token);
}
