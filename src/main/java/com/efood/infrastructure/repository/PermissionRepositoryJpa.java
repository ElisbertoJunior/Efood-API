package com.efood.infrastructure.repository;

import com.efood.domain.repository.PermissionRepository;
import com.efood.model.Permission;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionRepositoryJpa implements PermissionRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permission> list() {
        return manager.createQuery("FROM Permission", Permission.class)
                .getResultList();
    }

    @Override
    public Permission search(Long id) {
        return manager.find(Permission.class, id);
    }

    @Override
    @Transactional
    public Permission save(Permission permission) {
        return manager.merge(permission);
    }

    @Override
    @Transactional
    public void remove(Permission permission) {
        permission = search(permission.getId());
        manager.remove(permission);
    }

}
