package com.decagon.swisspay.usecase.services;

import com.decagon.swisspay.domain.entities.StudentEntity;
import com.decagon.swisspay.usecase.payload.request.SendRewardRequest;
import com.decagon.swisspay.usecase.payload.response.SendRewardResponse;

import java.math.BigDecimal;

public interface SendRewardService {

    SendRewardResponse sendRewardResponse(SendRewardRequest sendRewardRequest) throws Exception;

    BigDecimal getStudentWalletBalance(StudentEntity studentEntity);

}
