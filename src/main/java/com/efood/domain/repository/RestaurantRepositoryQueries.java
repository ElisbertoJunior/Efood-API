package com.efood.domain.repository;

import com.efood.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {
    List<Restaurant> find(String name, BigDecimal initialTax, BigDecimal finalTax);

    List<Restaurant> findFreeShipping(String name);
}
