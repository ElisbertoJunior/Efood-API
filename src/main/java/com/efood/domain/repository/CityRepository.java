package com.efood.domain.repository;

import com.efood.model.City;

import java.util.List;

public interface CityRepository {

    List<City> list();

    City search(Long id);

    City save(City city);

    void remove(City city);

}
