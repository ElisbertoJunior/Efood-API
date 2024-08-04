package com.efood.domain.repository;

import com.efood.model.PaymentMethods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormPaymentRepository extends JpaRepository<PaymentMethods, Long> {


}
