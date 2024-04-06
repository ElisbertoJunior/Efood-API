package com.efood.domain.repository;

import com.efood.model.Permission;

import java.util.List;

public interface PermissionRepository {

    List<Permission> list();

    Permission search(Long id);

    Permission save(Permission permission);

    void remove(Permission permission);
}
