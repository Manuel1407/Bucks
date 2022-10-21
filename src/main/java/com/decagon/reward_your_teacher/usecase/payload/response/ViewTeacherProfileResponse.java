package com.decagon.reward_your_teacher.usecase.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ViewTeacherProfileResponse {
    private String name;
    private String email;
    private String schoolName;
    private String profilePicture;
    private String phoneNumber;
    private String about;
    private String position;

}
