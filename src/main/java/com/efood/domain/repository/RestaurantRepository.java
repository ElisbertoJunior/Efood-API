package com.efood.domain.repository;

import com.efood.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>,
        RestaurantRepositoryQueries,
        JpaSpecificationExecutor<Restaurant> {

    List<Restaurant> findByShippingPriceBetween(BigDecimal initialTax, BigDecimal finalTax);

    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchen);

    //Verifica se existe uma cozinha com o nome
    Boolean existsByName(String name);

    //conta quantos restaurentes existem com o id de cozinha
    int countByKitchenId(Long kitchen);

    //query com jpql
    //@Query("from Restaurant where nome like %:nome% and kitchen.id = :id")
    List<Restaurant> consultPerName(String name, @Param("id") Long kitchen);

}
