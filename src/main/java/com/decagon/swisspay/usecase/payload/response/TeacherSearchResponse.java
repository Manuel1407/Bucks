package com.decagon.swisspay.usecase.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherSearchResponse {

    private String name;
    private String school;
    private String yearsOfTeaching;
    private String  position;
    private String email;
    private String phoneNumber;
    private String about;
    private String displayPicture;
}
