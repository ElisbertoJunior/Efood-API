package com.efood.domain.service;

import com.efood.domain.exception.EntityInUseException;
import com.efood.domain.exception.EntityNotFoundException;
import com.efood.domain.repository.CityRepository;
import com.efood.domain.repository.StateRepository;
import com.efood.model.State;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    private final StateRepository stateRepositoryJpa;

    private final CityRepository cityRepository;


    public StateService(StateRepository stateRepositoryJpa, CityRepository cityRepository) {
        this.stateRepositoryJpa = stateRepositoryJpa;
        this.cityRepository = cityRepository;
    }

    public State save(State state) {
        return stateRepositoryJpa.save(state);
    }

    public State update(Long id, State state) {
        State stateDB = getById(id);

        if(stateDB == null) {
            throw new EntityNotFoundException(
                    String.format("Nao existe cadastro de estado com codigo: %d", id)
            );
        }

        stateDB.setName(state.getName());


        return stateRepositoryJpa.save(stateDB);
    }

    public List<State> getAll() {
        return stateRepositoryJpa.findAll();
    }

    public State getById(Long id) {
        return stateRepositoryJpa.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                String.format("Nao existe cadastro de estado com codigo: %d", id)
                        ));
    }

    public void remove(Long stateId) {
        try {
            stateRepositoryJpa.deleteById(stateId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Não existe um cadastro de estado com código %d", stateId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Estado de código %d não pode ser removido, pois está em uso", stateId));
        }
    }
}
