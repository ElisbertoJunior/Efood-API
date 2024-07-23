package com.efood.domain.service;

import com.efood.domain.exception.EntityInUseException;
import com.efood.domain.exception.EntityNotFoundException;
import com.efood.domain.repository.KitchenRepository;
import com.efood.domain.repository.RestaurantRepository;
import com.efood.model.Kitchen;
import com.efood.model.Restaurant;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    private final KitchenRepository kitchenRepositoryJPA;


    public RestaurantService(RestaurantRepository repository, KitchenRepository kitchenRepositoryJPA) {
        this.repository = repository;
        this.kitchenRepositoryJPA = kitchenRepositoryJPA;
    }

    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepositoryJPA.findById(kitchenId)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                String.format("Nao existe cadastro de cozinha com codigo: %d", kitchenId)
                        ));


        restaurant.setKitchen(kitchen);

        return repository.save(restaurant);
    }

    public Restaurant update(Long id, Restaurant restaurant) {
        Restaurant restaurantDB = getById(id);

        if(restaurantDB == null) {
            throw new EntityNotFoundException(
                    String.format("Nao existe cadastro de restaurente com codigo: %d", id)
            );
        }

        restaurantDB.setName(restaurant.getName());
        restaurantDB.setShippingPrice(restaurant.getShippingPrice());
        restaurantDB.setKitchen(restaurant.getKitchen());

        return repository.save(restaurantDB);
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public Restaurant getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Nao existe cadastro de restaurante com codigo: %d", id)
                ));
    }

    public void remove(Long stateId) {
        try {
            repository.deleteById(stateId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Não existe um cadastro de restaurante com código %d", stateId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Restaurante de código %d não pode ser removido, pois está em uso", stateId));
        }
    }
}
