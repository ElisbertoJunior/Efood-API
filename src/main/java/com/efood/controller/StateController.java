package com.efood.controller;

import com.efood.domain.exception.EntityNotFoundException;
import com.efood.domain.service.StateService;
import com.efood.infrastructure.repository.StateRepositoryJpa;
import com.efood.model.State;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/state")
public class StateController {

    private StateService service;

    public StateController(StateService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<State>> list() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> search(@PathVariable Long id) {
        State state = service.getById(id);

        if(state != null)
            return ResponseEntity.ok().body(state);

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody State state) {
        try {
            service.save(state);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(state);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


}
