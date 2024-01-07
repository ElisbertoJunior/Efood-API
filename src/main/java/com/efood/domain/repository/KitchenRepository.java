package com.efood.domain.repository;

import com.efood.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> list();

    Kitchen search(Long id);

    Kitchen save(Kitchen kitchen);

    void remove(Kitchen kitchen);

}
