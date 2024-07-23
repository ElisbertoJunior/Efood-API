package com.efood.domain.repository;

import com.efood.model.FormPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FormPaymentRepository extends JpaRepository<FormPayment, Long> {


}
