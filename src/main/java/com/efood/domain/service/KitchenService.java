package com.efood.domain.service;

import com.efood.domain.exception.EntityInUseException;
import com.efood.domain.exception.EntityNotFoundException;
import com.efood.domain.repository.KitchenRepository;
import com.efood.model.Kitchen;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenService {

    private KitchenRepository repository;

    public KitchenService(KitchenRepository repository) {
        this.repository = repository;
    }

    public Kitchen addKitchen(Kitchen kitchen) {
        return repository.save(kitchen);
    }

    public List<Kitchen> getAll() {
        return repository.findAll();
    }

    public Kitchen getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                String.format("Nao existe cadastro de cozinha com codigo: %d", id)
                        ));
    }

    public Kitchen update(Long id, Kitchen kitchen) {
        Kitchen kitchenDB = getById(id);

        if(kitchenDB == null)
            throw new EntityNotFoundException(
                    String.format("Nao existe cadastro de cozinha com codigo: %d", id)
            );

        kitchenDB.setName(kitchen.getName());

        return repository.save(kitchenDB);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
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
