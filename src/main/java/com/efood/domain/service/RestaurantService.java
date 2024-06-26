package com.efood.domain.service;

import com.efood.domain.exception.EntityNotFoundException;
import com.efood.infrastructure.repository.KitchenRepositoryJpa;
import com.efood.infrastructure.repository.RestaurantRepositoryJpa;
import com.efood.model.Kitchen;
import com.efood.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepositoryJpa repository;

    private final KitchenRepositoryJpa kitchenRepository;


    public RestaurantService(RestaurantRepositoryJpa repository, KitchenRepositoryJpa kitchenRepository) {
        this.repository = repository;
        this.kitchenRepository = kitchenRepository;
    }

    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.search(kitchenId);

        if(kitchen == null)
            throw new EntityNotFoundException(
                    String.format("Nao existe cadastro cozinha com codigo: %d", kitchenId)
            );

        restaurant.setKitchen(kitchen);

        return repository.save(restaurant);
    }

    public List<Restaurant> getAll() {
        return repository.list();
    }

    public Restaurant getById(Long id) {
        return repository.search(id);
    }
}
