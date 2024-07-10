package com.efood.domain.service;

import com.efood.domain.exception.EntityInUseException;
import com.efood.domain.exception.EntityNotFoundException;
import com.efood.infrastructure.repository.CityRepositoryJpa;
import com.efood.infrastructure.repository.StateRepositoryJpa;
import com.efood.model.City;
import com.efood.model.Restaurant;
import com.efood.model.State;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    private final StateRepositoryJpa stateRepositoryJpa;

    private final CityRepositoryJpa cityRepositoryJpa;


    public StateService(StateRepositoryJpa stateRepositoryJpa, CityRepositoryJpa cityRepositoryJpa) {
        this.stateRepositoryJpa = stateRepositoryJpa;
        this.cityRepositoryJpa = cityRepositoryJpa;
    }

    public State save(State state) {
        long cityId = state.getCity().getId();
        City city = cityRepositoryJpa.search(cityId);

        if(city == null)
            throw new EntityNotFoundException(
                    String.format("Nao existe cadastro de cidade com codigo: %d", cityId)
            );

        state.setCity(city);

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
        stateDB.setCity(state.getCity());


        return stateRepositoryJpa.save(stateDB);
    }

    public List<State> getAll() {
        return stateRepositoryJpa.list();
    }

    public State getById(Long id) {
        return stateRepositoryJpa.search(id);
    }

    public void remove(Long stateId) {
        try {
            stateRepositoryJpa.remove(stateId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Não existe um cadastro de estado com código %d", stateId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Estado de código %d não pode ser removido, pois está em uso", stateId));
        }
    }
}
