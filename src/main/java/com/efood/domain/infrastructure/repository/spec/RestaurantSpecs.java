package com.efood.domain.infrastructure.repository.spec;

import com.efood.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {

    public static Specification<Restaurant> freeShipping() {
        return (root, query, builder) -> builder.equal(root.get("shippingPrice"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> similarName(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }
}
