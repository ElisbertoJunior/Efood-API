package com.efood.repository;

import com.efood.EfoodApplication;
import com.efood.model.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


public class IncludeKitchenMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(EfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RegisterKitchen registerKitchen = applicationContext.getBean(RegisterKitchen.class);

        Kitchen kitchenOne = new Kitchen();
        kitchenOne.setName("Brasileira");

        Kitchen kitchenTwo = new Kitchen();
        kitchenTwo.setName("Japonesa");

        registerKitchen.add(kitchenOne);
        registerKitchen.add(kitchenTwo);

    }
}
