package com.decagon.swisspay.usecase.services;

import com.decagon.swisspay.usecase.payload.response.TransactionResponse;

import java.util.List;

public interface TransactionService {

    List<TransactionResponse> getStudentTransactions(int offset, int pageSize);

    List<TransactionResponse> getTeacherTransactions(int offset, int pageSize);
}
