package com.efood.infrastructure.repository;

import com.efood.domain.repository.StateRepository;
import com.efood.model.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateRepositoryJpa implements StateRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<State> list() {
        return manager.createQuery("FROM State", State.class)
                .getResultList();
    }

    @Override
    public State search(Long id) {
        return manager.find(State.class, id);
    }

    @Override
    @Transactional
    public State save(State state) {
        return manager.merge(state);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        State state = search(id);

        if (state == null) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(state);
    }
}
