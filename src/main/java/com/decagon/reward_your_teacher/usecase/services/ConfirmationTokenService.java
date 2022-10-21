package com.decagon.reward_your_teacher.usecase.services;

import com.decagon.reward_your_teacher.domain.entities.email.ConfirmationTokenEntity;

public interface ConfirmationTokenService {
     void saveConfirmationToken(ConfirmationTokenEntity token);
     ConfirmationTokenEntity getToken(String token);
     int setConfirmedAt(String token);
}
