package com.decagon.swisspay.usecase.services;

import com.decagon.swisspay.domain.entities.email.ConfirmationTokenEntity;

public interface ConfirmationTokenService {
     void saveConfirmationToken(ConfirmationTokenEntity token);
     ConfirmationTokenEntity getToken(String token);
     int setConfirmedAt(String token);
}
