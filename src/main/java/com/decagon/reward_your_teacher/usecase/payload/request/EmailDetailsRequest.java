package com.decagon.reward_your_teacher.usecase.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class EmailDetailsRequest {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}