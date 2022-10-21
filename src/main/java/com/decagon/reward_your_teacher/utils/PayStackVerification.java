package com.decagon.reward_your_teacher.utils;

import com.decagon.reward_your_teacher.usecase.payload.response.VerifyTransactionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;


@RequiredArgsConstructor
@Service
public class PayStackVerification extends VerifyTransactionResponse {
    @Value("${paystack_secret_key:paystack}")
    private String payStackSecretKey;

    public PayStackVerification verifyTransaction(String reference) throws Exception {

        PayStackVerification payStackResponse;

        try {

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("https://api.paystack.co/transaction/verify/" + reference);
            request.addHeader("Content-type", "application/json");
            request.addHeader("Authorization", "Bearer "+payStackSecretKey);

            StringBuilder result = new StringBuilder();

            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == 200) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

            } else {
                throw new Exception("Error Occurred while connecting to pay-stack url");
            }

            ObjectMapper mapper = new ObjectMapper();

            try {
                payStackResponse = mapper.readValue(result.toString(), PayStackVerification.class);


                if (payStackResponse.getStatus().equals("success"))
                    return payStackResponse;

            } catch (JsonProcessingException e) {
                System.err.println("You've already made payment or An error occurred while verifying payment ");
                return null;
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Internal server error");
        }

        return payStackResponse;
    }
}