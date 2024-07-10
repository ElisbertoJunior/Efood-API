package com.efood.domain.repository;

import com.efood.model.State;

import java.util.List;

public interface StateRepository {

    List<State> list();

    State search(Long id);

    State save(State state);

    void remove(Long id);

}
