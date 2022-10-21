package com.decagon.reward_your_teacher.usecase.services;

import com.decagon.reward_your_teacher.usecase.payload.request.PayStackTransactionRequest;
import com.decagon.reward_your_teacher.usecase.payload.response.PayStackTransactionResponse;


public interface PaymentService {
     PayStackTransactionResponse initTransaction(PayStackTransactionRequest request) throws Exception;
}

