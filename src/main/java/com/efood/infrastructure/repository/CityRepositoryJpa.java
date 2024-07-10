package com.efood.infrastructure.repository;

import com.efood.domain.repository.CityRepository;
import com.efood.model.City;
import com.efood.model.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityRepositoryJpa implements CityRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> list() {
        return manager.createQuery("FROM City", City.class)
                .getResultList();
    }

    @Override
    public City search(Long id) {
        return manager.find(City.class, id);
    }

    @Override
    @Transactional
    public City save(City city) {
        return manager.merge(city);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        City city = search(id);

        if (city == null) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(city);
    }
}
