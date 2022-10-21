package com.decagon.reward_your_teacher.domain.dao;

import com.decagon.reward_your_teacher.domain.entities.SchoolEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface SchoolDao extends CrudDao<SchoolEntity, Long> {
    Optional<SchoolEntity> findSchool(String schoolName);

    Optional<List<SchoolEntity>> findAllSchools(String school);

    Page<SchoolEntity> getAllSchools(Pageable pageable);
}
