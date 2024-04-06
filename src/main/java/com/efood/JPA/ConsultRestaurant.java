package com.efood.JPA;

import com.efood.EfoodApplication;
import com.efood.domain.repository.KitchenRepository;
import com.efood.domain.repository.RestaurantRepository;
import com.efood.model.Kitchen;
import com.efood.model.Restaurant;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class ConsultRestaurant {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(EfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);

        List<Restaurant> restaurants = restaurantRepository.list();

        for (Restaurant restaurant : restaurants) {
            System.out.println("Nome: " + restaurant.getName() + ", " + "Taxa : " + restaurant.getShippingPrice() + ", "
                   + "Tipo de cozinha: " + restaurant.getKitchen().getName()
                    );
        }

    }
}
