package com.efood.controller;

import com.efood.domain.exception.EntityInUseException;
import com.efood.domain.exception.EntityNotFoundException;
import com.efood.domain.service.KitchenService;
import com.efood.model.Kitchen;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitchen")
public class KitchenController {

    private KitchenRepositoryJpa repository;

    private KitchenService service;

    public KitchenController(KitchenRepositoryJpa repository, KitchenService service) {
        this.repository = repository;
        this.service = service;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addKitchen(kitchen));
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
    public ResponseEntity<?> remove(@PathVariable Long id) {

        try {
           service.delete(id);
           return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

}
