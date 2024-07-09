package com.efood.controller;

import com.efood.domain.exception.EntityNotFoundException;
import com.efood.domain.service.RestaurantService;
import com.efood.model.Restaurant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService service;


    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
        try {
            restaurant = service.save(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurant);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        try {
            restaurant = service.update(id, restaurant);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(restaurant);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {

        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getById(@PathVariable  Long id) {
        Restaurant restaurant = service.getById(id);

        if(restaurant != null)
            return ResponseEntity.ok().body(restaurant);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
}
