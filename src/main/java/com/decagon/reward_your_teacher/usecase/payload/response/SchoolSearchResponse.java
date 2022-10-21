package com.decagon.reward_your_teacher.usecase.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SchoolSearchResponse {
   private long id;
    private String schoolName;
}
