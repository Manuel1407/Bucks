package com.decagon.swisspay.usecase.services;

import com.decagon.swisspay.usecase.payload.request.FundWalletRequest;
import com.decagon.swisspay.usecase.payload.response.PaymentResponse;
import com.decagon.swisspay.usecase.payload.response.WalletResponse;


public interface WalletService {
    WalletResponse getStudentWalletBalance ();
    PaymentResponse fundWallet(FundWalletRequest fundWalletRequest) throws Exception;
    WalletResponse getTeacherWalletBalance();

}
