package com.decagon.swisspay.usecase.services;

import com.decagon.swisspay.domain.entities.SchoolEntity;

import java.util.List;

public interface SchoolService {

    String saveSchool(List<SchoolEntity> schoolEntities);

    int getSchoolCount();
}
