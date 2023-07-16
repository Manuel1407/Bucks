package com.decagon.swisspay.usecase.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RegistrationResponse<T> {
    private String name;
    private String email;
    private long id;
}
