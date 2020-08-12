package com.ordersproject.demo.services;

import com.ordersproject.demo.models.Payment;
import com.ordersproject.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service (value = "paymentServices")
public class PaymentServicesImpl implements PaymentServices {
    @Autowired
    PaymentRepository paymentrepos;

    @Override
    public Payment save(Payment payment) {
        return paymentrepos.save(payment);
    }
}
