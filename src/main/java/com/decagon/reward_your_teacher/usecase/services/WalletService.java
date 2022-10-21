package com.decagon.reward_your_teacher.usecase.services;

import com.decagon.reward_your_teacher.usecase.payload.request.FundWalletRequest;
import com.decagon.reward_your_teacher.usecase.payload.response.PaymentResponse;
import com.decagon.reward_your_teacher.usecase.payload.response.WalletResponse;


public interface WalletService {
    WalletResponse getStudentWalletBalance ();
    PaymentResponse fundWallet(FundWalletRequest fundWalletRequest) throws Exception;
    WalletResponse getTeacherWalletBalance();

}
