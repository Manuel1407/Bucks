package com.decagon.reward_your_teacher.infrastructure.persistence.daoImpl;

import com.decagon.reward_your_teacher.domain.dao.SchoolDao;
import com.decagon.reward_your_teacher.domain.entities.SchoolEntity;
import com.decagon.reward_your_teacher.infrastructure.persistence.repository.SchoolRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolDaoImpl extends CrudDaoImpl<SchoolEntity, Long> implements SchoolDao {
    private final SchoolRepository schoolRepository;

    public SchoolDaoImpl(SchoolRepository schoolRepository) {
        super(schoolRepository);
        this.schoolRepository = schoolRepository;
    }

    @Override
    public Optional<SchoolEntity> findSchool(String schoolName) {
        return schoolRepository.findSchoolEntityBySchoolName(schoolName);
    }

    @Override
    public Optional<List<SchoolEntity>> findAllSchools(String school) {
        return schoolRepository.findBySchoolNameContainingIgnoreCase(school);
    }

    @Override
    public Page<SchoolEntity> getAllSchools(Pageable pageable) {
        return schoolRepository.findAll(pageable);
    }
}
