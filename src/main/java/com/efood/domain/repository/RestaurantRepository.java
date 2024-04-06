package com.efood.domain.repository;

import com.efood.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {



    List<Restaurant> list();

    Restaurant search(Long id);

    Restaurant save(Restaurant kitchen);

    void remove(Restaurant kitchen);

}
