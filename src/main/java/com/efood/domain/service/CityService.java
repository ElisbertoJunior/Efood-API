package com.efood.domain.service;

import com.efood.domain.exception.EntityInUseException;
import com.efood.domain.exception.EntityNotFoundException;
import com.efood.domain.repository.CityRepository;
import com.efood.domain.repository.StateRepository;
import com.efood.model.City;
import com.efood.model.State;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    private final StateRepository stateRepository;


    public CityService(CityRepository cityRepository, StateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    public City save(City city) {
        Long stateId = city.getState().getId();
        State state = stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Nao existe cadastro de estado com codigo: %d", stateId)));

        city.setState(state);

       return cityRepository.save(city);
    }

    public City getById(Long id) {
        return cityRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Nao existe cadastro de cidade com codigo: %d", id)
                ));
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public City update(Long id, City city) {
        City cityDB = getById(id);

        if(cityDB == null)
            throw new EntityNotFoundException(
              String.format("Nao existe cadastro de cidade com codigo: %d", id)
            );

        cityDB.setName(city.getName());

        return cityRepository.save(cityDB);
    }

    public void delete(Long id) {
        try {
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Nao existe um cadastro de cidade com este codigo %d", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Cidade de codigo %d nao pode ser removida pois esta em uso", id)
            );
        }

    }
}
