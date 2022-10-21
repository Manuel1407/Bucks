//package com.decagon.reward_your_teacher.controllers;
//
//import com.decagon.reward_your_teacher.SecurityConfigTest.TestSecurityConfig;
//import com.decagon.reward_your_teacher.usecase.payload.request.FundWalletRequest;
//import com.decagon.reward_your_teacher.usecase.payload.response.PaymentResponse;
//import com.decagon.reward_your_teacher.usecase.payload.response.WalletResponse;
//import com.decagon.reward_your_teacher.usecase.services.WalletService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.math.BigDecimal;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestSecurityConfig.class)
//@AutoConfigureMockMvc
//class WalletControllerTest {
//    @MockBean
//    private WalletService walletService;
//
//    @Autowired
//    private MockMvc mockMvc;
//   private static FundWalletRequest fundWalletRequest;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        PaymentResponse paymentResponse = PaymentResponse.builder()
//                .message("payment successful").build();
//        WalletResponse walletResponse = WalletResponse.builder()
//                .balance(BigDecimal.valueOf(24200)).totalMoneySent(BigDecimal.valueOf(500000)).build();
//         fundWalletRequest = FundWalletRequest.builder().amount("24200").build();
//
//        Mockito.when(walletService.fundWallet(fundWalletRequest)).thenReturn(paymentResponse);
//        Mockito.when(walletService.getTeacherWalletBalance()).thenReturn(walletResponse);
//        Mockito.when(walletService.getStudentWalletBalance()).thenReturn(walletResponse);
//    }
//
//    @Test
//    void fundWallet() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/wallet/fund")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(fundWalletRequest)))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    void getStudentBalance() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/wallet/student/balance"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{}"));
//    }
//
//    @Test
//    void getTeacherBalance() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/wallet/teacher/balance"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{}"));
//    }
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}