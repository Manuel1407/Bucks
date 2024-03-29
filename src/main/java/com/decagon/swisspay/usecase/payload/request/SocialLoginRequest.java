package com.decagon.swisspay.usecase.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@Builder
public class SocialLoginRequest {
    @Pattern(regexp = "^[A-Za-z]*$", message = "Invalid FirstName")
    private String firstName;
    @Pattern(regexp = "^[A-Za-z]*$", message = "Invalid LastName")
    private String lastName;
    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Enter a valid email address")
    private String email;
    private String displayPicture;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Minimum eight characters, at least one letter and one number")
    private String password;
}
