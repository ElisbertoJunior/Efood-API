package com.efood.controller;

import com.efood.domain.repository.RestaurantRepository;
import com.efood.model.Restaurant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final RestaurantRepository repository;


    public TestController(RestaurantRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/nome-frete")
    public ResponseEntity<List<Restaurant>> listPerShipping(String name, BigDecimal initialTax, BigDecimal finalTax) {
        return ResponseEntity.ok().body(repository.find(name, initialTax, finalTax));
    }
}
