package com.decagon.reward_your_teacher.infrastructure.controllers;


import com.decagon.reward_your_teacher.usecase.payload.request.FundWalletRequest;
import com.decagon.reward_your_teacher.usecase.payload.response.ApiResponse;
import com.decagon.reward_your_teacher.usecase.payload.response.PaymentResponse;
import com.decagon.reward_your_teacher.usecase.payload.response.WalletResponse;
import com.decagon.reward_your_teacher.usecase.services.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/wallet")
public class WalletController {

    private final WalletService walletService;
    @PostMapping("/fund")
    public ResponseEntity<ApiResponse<PaymentResponse>> fundWallet(@RequestBody FundWalletRequest fundWalletRequest) throws Exception {
       PaymentResponse paymentResponse = walletService.fundWallet(fundWalletRequest);
        return new ResponseEntity<>(new ApiResponse<>("success",true,paymentResponse), HttpStatus.OK);
    }

    @GetMapping("student/balance")
    public ResponseEntity<ApiResponse<WalletResponse>> getStudentBalance() {
       WalletResponse walletResponse = walletService.getStudentWalletBalance();
        return new ResponseEntity<>(new ApiResponse<>("success",true,walletResponse),HttpStatus.OK);
    }

    @GetMapping("teacher/balance")
    public ResponseEntity<ApiResponse<WalletResponse>> getTeacherBalance() {
        WalletResponse walletResponse = walletService.getTeacherWalletBalance();
        return new ResponseEntity<>(new ApiResponse<>("success",true,walletResponse),HttpStatus.OK);
    }



}
