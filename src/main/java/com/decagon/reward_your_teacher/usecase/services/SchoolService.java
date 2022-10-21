package com.decagon.reward_your_teacher.usecase.services;

import com.decagon.reward_your_teacher.domain.entities.SchoolEntity;

import java.util.List;

public interface SchoolService {

    String saveSchool(List<SchoolEntity> schoolEntities);

    int getSchoolCount();
}
