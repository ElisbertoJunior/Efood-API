package com.efood.domain.repository;

import com.efood.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {



    //List<Kitchen> queryByName(String name);



}
