package com.efood.domain.service;

import com.efood.domain.exception.EntityInUseException;
import com.efood.domain.exception.EntityNotFoundException;
import com.efood.infrastructure.repository.KitchenRepositoryJpa;
import com.efood.model.Kitchen;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {

    private KitchenRepositoryJpa repository;

    public KitchenService(KitchenRepositoryJpa repository) {
        this.repository = repository;
    }

    public Kitchen addKitchen(Kitchen kitchen) {
        return repository.save(kitchen);
    }

    public void delete(Long id) {
        try {
            repository.remove(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Nao existe um cadastro de cozinha com este codigo %d", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Cozinha de codigo %d nao pode ser removida pois esta em uso", id)
            );
        }

    }

}
