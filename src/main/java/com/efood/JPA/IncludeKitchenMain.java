package com.efood.JPA;

import com.efood.EfoodApplication;
import com.efood.domain.repository.KitchenRepository;
import com.efood.model.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


public class IncludeKitchenMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(EfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);

        Kitchen kitchenOne = new Kitchen();
        kitchenOne.setName("Brasileira");

        Kitchen kitchenTwo = new Kitchen();
        kitchenTwo.setName("Japonesa");

        kitchenRepository.save(kitchenOne);
        kitchenRepository.save(kitchenTwo);

    }
}
