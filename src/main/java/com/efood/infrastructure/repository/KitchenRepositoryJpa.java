package com.efood.infrastructure.repository;

import com.efood.domain.repository.KitchenRepository;
import com.efood.model.Kitchen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class KitchenRepositoryJpa implements KitchenRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Kitchen> list() {
        return manager.createQuery("FROM Kitchen", Kitchen.class)
                .getResultList();
    }

    @Override
    public Kitchen search(Long id) {
        return manager.find(Kitchen.class, id);
    }

    @Override
    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return manager.merge(kitchen);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Kitchen kitchen = search(id);

        if(kitchen == null)
            throw new EmptyResultDataAccessException(1);

        manager.remove(kitchen);
    }
}
