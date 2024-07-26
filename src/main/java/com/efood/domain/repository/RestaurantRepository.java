package com.efood.domain.repository;

import com.efood.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByShippingPriceBetween(BigDecimal initialTax, BigDecimal finalTax);

    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchen);

    //Verifica se existe uma cozinha com o nome
    Boolean existsByName(String name);

    //conta quantos restaurentes existem com o id de cozinha
    int countByKitchenId(Long kitchen);
}
