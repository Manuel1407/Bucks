package com.decagon.reward_your_teacher.usecase.services.impl;

import com.decagon.reward_your_teacher.domain.dao.SchoolDao;
import com.decagon.reward_your_teacher.domain.dao.TeacherDao;
import com.decagon.reward_your_teacher.domain.entities.SchoolEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.infrastructure.error_handler.CustomNotFoundException;
import com.decagon.reward_your_teacher.usecase.payload.response.SchoolSearchResponse;
import com.decagon.reward_your_teacher.usecase.payload.response.TeacherSearchResponse;
import com.decagon.reward_your_teacher.usecase.services.SearchService;
import com.decagon.reward_your_teacher.utils.ResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SchoolDao schoolDao;
    private final TeacherDao teacherDao;

    private final ResponseMapper responseMapper;

    @Override
    public Set<SchoolSearchResponse> findAllSchools(int offSet, int pageSize) {
        Pageable pageable = PageRequest.of(offSet, pageSize);
        Page<SchoolEntity> schoolEntityList = schoolDao.getAllSchools(pageable);

        Set<SchoolSearchResponse> listOfSchoolName = new HashSet<>();

        schoolEntityList.forEach(schoolEntity -> {
            SchoolSearchResponse response =new SchoolSearchResponse();
            response.setSchoolName(schoolEntity.getSchoolName());
            response.setId((schoolEntity.getId()));
            listOfSchoolName.add(response);
        });

        return listOfSchoolName;
    }

    private List<TeacherSearchResponse> getTeacherSearchResponses(Page<TeacherEntity> teachersList) {
        List<TeacherSearchResponse> teacherSearchResponseList = new ArrayList<>();

        teachersList.forEach(teacherEntity -> {
           TeacherSearchResponse response = responseMapper.teacherSearchResponseToTeacherEntityMapper(teacherEntity);
            teacherSearchResponseList.add(response);
        });

        return teacherSearchResponseList;
    }

    @Override
    public List<TeacherSearchResponse> findAllTeachersInASchool(int offset, int pageSize, String school) {

        List<SchoolEntity> schoolEntity = schoolDao.findAllSchools(school).orElseThrow(
                () -> new CustomNotFoundException("School not found"));
        Pageable pageable = PageRequest.of(offset, pageSize);

        Page<TeacherEntity> teachersList = teacherDao.findTeacherEntitiesBySchool(schoolEntity,pageable);

        return getTeacherSearchResponses(teachersList);
    }

    @Override
    public List<TeacherSearchResponse> getAllTeachers(int offset, int pageSize) {
        Pageable pageable = PageRequest.of(offset, pageSize);

        Page<TeacherEntity> teachersList = teacherDao.findAllTeachers(pageable);
        return getTeacherSearchResponses(teachersList);
    }

    @Override
    public List<TeacherSearchResponse> searchTeacherByName(String keyword) {
        List<TeacherEntity> teacher = teacherDao.findTeacherEntitiesByNameIsContainingIgnoreCase(keyword);
        List<TeacherSearchResponse> teacherSearchResponseList = new ArrayList<>();
        teacher.forEach(teacher1 -> {
          TeacherSearchResponse teacherSearchResponse =  responseMapper.teacherSearchResponseToTeacherEntityMapper(teacher1);
          teacherSearchResponseList.add(teacherSearchResponse);
        });
      return teacherSearchResponseList;
    }
}
