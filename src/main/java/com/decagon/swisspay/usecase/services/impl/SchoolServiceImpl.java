package com.decagon.swisspay.usecase.services.impl;

import com.decagon.swisspay.domain.dao.SchoolDao;
import com.decagon.swisspay.domain.entities.SchoolEntity;
import com.decagon.swisspay.usecase.services.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolDao schoolDao;

    @Override
    public String saveSchool(List<SchoolEntity> schoolEntities) {
        schoolDao.saveAllRecord(schoolEntities);
        return "success";
    }

    @Override
    public int getSchoolCount() {
        return schoolDao.getAllRecords().size();
    }

}
