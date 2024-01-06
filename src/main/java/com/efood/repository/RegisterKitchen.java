package com.efood.repository;

import com.efood.model.Kitchen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RegisterKitchen {

    @PersistenceContext
    private EntityManager manager;

    public List<Kitchen> list() {
        return manager.createQuery("FROM Kitchen", Kitchen.class)
                .getResultList();
    }

    public Kitchen search(Long id) {
        return manager.find(Kitchen.class, id);
    }

    @Transactional
    public Kitchen add(Kitchen kitchen) {
        return manager.merge(kitchen);
    }

    @Transactional
    public void  remove(Kitchen kitchen) {
        kitchen = search(kitchen.getId());
        manager.remove(kitchen);
    }

}
