package com.efood.infrastructure.repository;

import com.efood.domain.repository.FormPaymentRepository;
import com.efood.model.FormPayment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormPaymentRepositoryJpa implements FormPaymentRepository {


    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormPayment> list() {
        return manager.createQuery("FROM FormPayment", FormPayment.class)
                .getResultList();
    }

    @Override
    public FormPayment search(Long id) {
        return manager.find(FormPayment.class, id);
    }

    @Override
    @Transactional
    public FormPayment save(FormPayment formPayment) {
        return manager.merge(formPayment);
    }

    @Override
    @Transactional
    public void delete(FormPayment formPayment) {
        formPayment = search(formPayment.getId());
        manager.remove(formPayment);
    }
}
