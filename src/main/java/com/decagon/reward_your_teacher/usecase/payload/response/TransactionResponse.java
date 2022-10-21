package com.decagon.reward_your_teacher.usecase.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private String transactionType;
    private String description;
    private BigDecimal amount;
    private String createdAt;
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private String studentPhone;
    private String studentSchool;
    private Long transactionId;
}
