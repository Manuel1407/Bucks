package com.decagon.swisspay.infrastructure.persistence.repository;

import com.decagon.swisspay.domain.entities.AppUserEntity;
import com.decagon.swisspay.domain.entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository  extends JpaRepository<AppUserEntity,Long> {

    AppUserEntity findAppUserEntityByEmail(String email);

    AppUserEntity findAppUserEntityByEmailAndRole(String email, Role role);

}
