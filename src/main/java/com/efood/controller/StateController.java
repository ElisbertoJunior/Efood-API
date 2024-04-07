package com.efood.controller;

import com.efood.infrastructure.repository.StateRepositoryJpa;
import com.efood.model.State;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/state")
public class StateController {

    private StateRepositoryJpa repository;

    public StateController(StateRepositoryJpa repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<State>> list() {
        return ResponseEntity.ok().body(repository.list());
    }
}
