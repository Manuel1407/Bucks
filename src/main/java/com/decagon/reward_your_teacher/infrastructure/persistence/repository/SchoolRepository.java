package com.decagon.reward_your_teacher.infrastructure.persistence.repository;

import com.decagon.reward_your_teacher.domain.entities.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {
    Optional<SchoolEntity> findSchoolEntityBySchoolName(String schoolName);

    Optional<List<SchoolEntity>> findBySchoolNameContainingIgnoreCase(String school);

}
