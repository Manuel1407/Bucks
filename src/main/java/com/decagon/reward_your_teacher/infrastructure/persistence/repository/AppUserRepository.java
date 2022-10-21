package com.decagon.reward_your_teacher.infrastructure.persistence.repository;

import com.decagon.reward_your_teacher.domain.entities.AppUserEntity;
import com.decagon.reward_your_teacher.domain.entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository  extends JpaRepository<AppUserEntity,Long> {

    AppUserEntity findAppUserEntityByEmail(String email);

    AppUserEntity findAppUserEntityByEmailAndRole(String email, Role role);

}
