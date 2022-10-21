package com.decagon.reward_your_teacher.usecase.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class SendRewardResponse {
    private boolean status;
    private String message;
    private LocalDateTime createdAt;
}
