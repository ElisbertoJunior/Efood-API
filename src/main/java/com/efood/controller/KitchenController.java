package com.efood.controller;

import com.efood.infrastructure.repository.KitchenRepositoryJpa;
import com.efood.model.Kitchen;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> search(@PathVariable Long id) {
        Kitchen kitchen = repository.search(id);

        if (kitchen != null)
            return ResponseEntity.ok(kitchen);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Kitchen> add(@RequestBody Kitchen kitchen) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(kitchen));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
        Kitchen kitchenDB = repository.search(id);

        if(kitchenDB != null) {
            BeanUtils.copyProperties(kitchen, kitchenDB, "id");

            repository.save(kitchenDB);

            return ResponseEntity.ok(kitchenDB);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Kitchen> remove(@PathVariable Long id) {
        Kitchen kitchen = repository.search(id);
        try {
            if(kitchen != null) {
                repository.remove(kitchen);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

}
