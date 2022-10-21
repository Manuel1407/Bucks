//package com.decagon.reward_your_teacher.controllers;
//
//import com.decagon.reward_your_teacher.SecurityConfigTest.TestSecurityConfig;
//import com.decagon.reward_your_teacher.domain.entities.enums.TransactionType;
//import com.decagon.reward_your_teacher.usecase.payload.response.TransactionResponse;
//import com.decagon.reward_your_teacher.usecase.services.TransactionService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestSecurityConfig.class)
//@AutoConfigureMockMvc
//class TransactionControllerTest {
//    @MockBean
//    private TransactionService transactionService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        List<TransactionResponse> transactionResponses = new ArrayList<>();
//        TransactionResponse transactionResponse = TransactionResponse.builder()
//                .transactionType(TransactionType.DEBIT.name())
//                .amount(BigDecimal.valueOf(23000))
//                .description("Money sent")
//                .build();
//        TransactionResponse transactionResponse1 = TransactionResponse.builder()
//                .transactionType(TransactionType.DEBIT.name())
//                .amount(BigDecimal.valueOf(200))
//                .description("Money sent")
//                .build();
//        transactionResponses.add(transactionResponse);
//        transactionResponses.add(transactionResponse1);
//        int offset = 0;
//        int pageSize = 10;
//
//        Mockito.when(transactionService.getStudentTransactions(offset,pageSize)).thenReturn(transactionResponses);
//        Mockito.when(transactionService.getTeacherTransactions(offset,pageSize)).thenReturn(transactionResponses);
//    }
//
//    @Test
//    void getStudentTransactions() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/transaction/student?offset=0&pageSize=10"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{}, {}]"));
//    }
//    @Test
//    void getTeacherTransaction() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/transaction/teacher?offset=0&pageSize=10"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{}, {}]"));
//    }
//}