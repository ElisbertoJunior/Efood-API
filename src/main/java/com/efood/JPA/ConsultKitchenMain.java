package com.efood.JPA;

import com.efood.EfoodApplication;
import com.efood.domain.repository.KitchenRepository;
import com.efood.model.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class ConsultKitchenMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(EfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);

        List<Kitchen> kitchens = kitchenRepository.list();

        for (Kitchen kitchen : kitchens) {
            System.out.println(kitchen.getName());
        }

    }
}
