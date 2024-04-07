package com.efood.controller;

import com.efood.infrastructure.repository.KitchenRepositoryJpa;
import com.efood.model.Kitchen;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/kitchen")
public class KitchenController {

    private KitchenRepositoryJpa repository;

    public KitchenController(KitchenRepositoryJpa repository) {
        this.repository = repository;
    }


    @GetMapping
    public ResponseEntity<List<Kitchen>> list() {
        return ResponseEntity.ok().body(repository.list());
    }

}
