package com.decagon.reward_your_teacher.usecase.services;

import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.usecase.payload.request.SendRewardRequest;
import com.decagon.reward_your_teacher.usecase.payload.response.SendRewardResponse;

import java.math.BigDecimal;

public interface SendRewardService {

    SendRewardResponse sendRewardResponse(SendRewardRequest sendRewardRequest) throws Exception;

    BigDecimal getStudentWalletBalance(StudentEntity studentEntity);

}
