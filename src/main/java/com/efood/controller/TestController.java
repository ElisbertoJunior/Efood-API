package com.efood.controller;

import com.efood.domain.repository.RestaurantRepository;
import com.efood.model.Restaurant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.efood.domain.infrastructure.repository.spec.RestaurantSpecs.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final RestaurantRepository repository;


    public TestController(RestaurantRepository repository) {
        this.repository = repository;
    }

    /*@GetMapping("/nome-frete")
    public ResponseEntity<List<Restaurant>> listPerShipping(String name, BigDecimal initialTax, BigDecimal finalTax) {
        return ResponseEntity.ok().body(repository.find(name, initialTax, finalTax));
    }*/

    @GetMapping("/nome-frete")
    public ResponseEntity<List<Restaurant>> listPerFreeShipping(String name) {
        return ResponseEntity.ok().body(repository.findFreeShipping(name));
    }

    @GetMapping("/primeiro")
    public ResponseEntity<Optional<Restaurant>> findFirst() {
        return ResponseEntity.ok().body(repository.findFirst());
    }
}
