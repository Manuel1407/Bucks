package com.decagon.swisspay.infrastructure.controllers;

import com.decagon.swisspay.usecase.payload.response.ApiResponse;
import com.decagon.swisspay.usecase.payload.response.TransactionResponse;
import com.decagon.swisspay.usecase.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/transaction")
public class TransactionController {


    private final TransactionService transactionService;

    @GetMapping("/student")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getStudentTransactions(
            @RequestParam("offset") int offset,
            @RequestParam("pageSize") int pageSize
    ){
       List<TransactionResponse> transactionEntities = transactionService.getStudentTransactions(offset,pageSize);
        return new ResponseEntity<>(new ApiResponse<>("success",true,transactionEntities), HttpStatus.OK);
    }
    @GetMapping("teacher")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getTeacherTransactions(
            @RequestParam("offset") int offset,
            @RequestParam("pageSize") int pageSize
    ){
        List<TransactionResponse> transactionEntities = transactionService.getTeacherTransactions(offset,pageSize);
        return new ResponseEntity<>(new ApiResponse<>("success",true,transactionEntities),HttpStatus.OK);
    }

}
