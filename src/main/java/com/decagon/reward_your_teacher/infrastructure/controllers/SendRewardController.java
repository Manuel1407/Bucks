package com.decagon.reward_your_teacher.infrastructure.controllers;

import com.decagon.reward_your_teacher.usecase.payload.request.SendRewardRequest;
import com.decagon.reward_your_teacher.usecase.payload.response.ApiResponse;
import com.decagon.reward_your_teacher.usecase.payload.response.SendRewardResponse;
import com.decagon.reward_your_teacher.usecase.services.SendRewardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/student")
public class SendRewardController {

    private final SendRewardService sendRewardService;

    @PostMapping("/sendReward")
    public ResponseEntity<ApiResponse<SendRewardResponse>> sendReward(@RequestBody SendRewardRequest sendRewardRequest) throws Exception {
        return ResponseEntity.ok(new ApiResponse<>("Reward sent",true,sendRewardService.sendRewardResponse(sendRewardRequest)));
    }

}
