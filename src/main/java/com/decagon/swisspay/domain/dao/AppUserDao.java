package com.decagon.swisspay.domain.dao;

import com.decagon.swisspay.domain.entities.AppUserEntity;
import com.decagon.swisspay.domain.entities.enums.Role;

public interface AppUserDao extends CrudDao<AppUserEntity, Long>{
    AppUserEntity findAppUserEntityByEmail(String email);
    AppUserEntity findAppUserEntityByEmailAndRole(String email, Role role);
}
