package com.decagon.reward_your_teacher.infrastructure.persistence.daoImpl;

import com.decagon.reward_your_teacher.domain.dao.TeacherDao;
import com.decagon.reward_your_teacher.domain.entities.AppUserEntity;
import com.decagon.reward_your_teacher.domain.entities.SchoolEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.infrastructure.error_handler.CustomNotFoundException;
import com.decagon.reward_your_teacher.infrastructure.persistence.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherDaoImpl extends CrudDaoImpl<TeacherEntity, Long> implements TeacherDao {
    private final TeacherRepository teacherRepository;

    protected TeacherDaoImpl(TeacherRepository teacherRepository) {
        super(teacherRepository);
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Page<TeacherEntity> findTeacherEntitiesBySchool(List<SchoolEntity> school, Pageable pageable) {
        return teacherRepository.findTeacherEntitiesBySchoolIn(school, pageable);
    }

    @Override
    public Page<TeacherEntity> findAllTeachers(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }


    @Override
    public TeacherEntity getTeacherEntityByAppUserEntity(AppUserEntity appUserEntity) {
        return teacherRepository.getTeacherEntityByAppUserEntity(appUserEntity);
    }

    @Override
    public Optional<TeacherEntity> findTeacherEntityByPhoneNumber(String phoneNumber) {
        return teacherRepository.findTeacherEntityByPhoneNumber(phoneNumber);
    }

    @Override
    public List<TeacherEntity> findTeacherEntitiesByNameIsContainingIgnoreCase(String name) {
        return teacherRepository.findTeacherEntitiesByNameIsContainingIgnoreCase(name).orElseThrow(()->new CustomNotFoundException("Teacher not found"));
    }

    @Override
    public Optional<TeacherEntity> findTeacherEntityByNin(String nin) {
        return teacherRepository.findTeacherEntityByNin(nin);
    }

    @Override
    public Optional<List<TeacherEntity>> findTeacherEntitiesByName(String name) {
        return teacherRepository.findTeacherEntitiesByName(name);
    }

    @Override
    public TeacherEntity getTeacherByNameAndPhoneNumber(String name, String phoneNumber) {
        return teacherRepository.getTeacherEntityByNameContainingIgnoreCaseAndPhoneNumber(name, phoneNumber);
    }
}
