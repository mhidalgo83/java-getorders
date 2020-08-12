package com.ordersproject.demo.repositories;

import com.ordersproject.demo.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository <Payment, Long> {
}
