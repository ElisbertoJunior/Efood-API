package com.efood.infrastructure.repository;

import com.efood.domain.repository.RestaurantRepository;
import com.efood.model.Restaurant;
import com.efood.model.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class RestaurantRepositoryJpa implements RestaurantRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> list() {
        return manager.createQuery("FROM Restaurant", Restaurant.class)
                .getResultList();
    }

    @Override
    public Restaurant search(Long id) {
        return manager.find(Restaurant.class, id);
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant Restaurant) {
        return manager.merge(Restaurant);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Restaurant restaurant = search(id);

        if (restaurant == null) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(restaurant);
    }
}
