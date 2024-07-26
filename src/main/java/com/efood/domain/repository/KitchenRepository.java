package com.efood.domain.repository;

import com.efood.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

    //dessa forma o spring ja vai entender e criar metodo para buscar pelo nome
    //tbm e possivel escrever qualquer coisa entre o find e By
    List<Kitchen> findByName(String name);

    //Com containing na ele busca usando o like
    List<Kitchen> findByNameContaining(String name);


}
