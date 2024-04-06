package com.efood.domain.repository;

import com.efood.model.FormPayment;

import java.util.List;

public interface FormPaymentRepository {
    List<FormPayment> list();

    FormPayment search(Long id);

    FormPayment save(FormPayment formPayment);

    void delete(FormPayment formPayment);

}
