package com.decagon.swisspay.usecase.services;

import com.decagon.swisspay.usecase.payload.request.PayStackTransactionRequest;
import com.decagon.swisspay.usecase.payload.response.PayStackTransactionResponse;


public interface PaymentService {
     PayStackTransactionResponse initTransaction(PayStackTransactionRequest request) throws Exception;
}

