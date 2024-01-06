package com.efood.repository;

import com.efood.EfoodApplication;
import com.efood.model.Kitchen;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class ConsultKitchenMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(EfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RegisterKitchen registerKitchen = applicationContext.getBean(RegisterKitchen.class);

        List<Kitchen> kitchens = registerKitchen.list();

        for (Kitchen kitchen : kitchens) {
            System.out.println(kitchen.getName());
        }

    }
}
