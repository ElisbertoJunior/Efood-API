package com.efood.domain.infrastructure.repository;

import com.efood.domain.repository.RestaurantRepositoryQueries;
import com.efood.model.Restaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    //metodo que abrange todos os tipos de find seja todos, por nome ou por taxa
   /* @Override
    public List<Restaurant> find(String name, BigDecimal initialTax, BigDecimal finalTax) {
        //var jpql = "FROM Restaurant WHERE name LIKE :name and shippingPrice between :initialTax and :finalTax";

        var jpql = new StringBuilder();
        jpql.append("FROM Restaurant WHERE 0 = 0 ");

        var parameters = new HashMap<String, Object>();

        if(StringUtils.hasLength(name)) {
            jpql.append("AND name LIKE :name ");
            parameters.put("name", "%" + name + "%");
        }

        if(initialTax != null) {
            jpql.append("AND shippingPrice >= :initialTax ");
            parameters.put("initialTax", initialTax);
        }

        if(finalTax != null) {
            jpql.append("AND shippingPrice <= :finalTax ");
            parameters.put("finalTax", finalTax);
        }

        TypedQuery<Restaurant> query = manager.createQuery(jpql.toString(), Restaurant.class);
        parameters.forEach((key, value) -> query.setParameter(key, value));
        return query.getResultList();
    }*/

    //Exemplo com criteria
    @Override
    public List<Restaurant> find(String name, BigDecimal initialTax, BigDecimal finalTax) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class);

        var predicates = new ArrayList<Predicate>();

        if(StringUtils.hasText(name)){
           predicates.add(builder.like(root.get("name"), "%" + name + "%"));
        }

        if(initialTax != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("shippingPrice"), initialTax));
        }


        if(finalTax != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("shippingPrice"), finalTax));
        }


        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurant> query = manager.createQuery(criteria);
        return query.getResultList();
    }
}
