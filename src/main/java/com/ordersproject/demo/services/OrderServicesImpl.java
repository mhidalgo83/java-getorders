package com.ordersproject.demo.services;

import com.ordersproject.demo.models.Order;
import com.ordersproject.demo.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service ( value = "orderServices")
public class OrderServicesImpl implements OrderServices{
    @Autowired
    OrdersRepository ordersrepos;

    @Override
    public Order save(Order order) {
        return ordersrepos.save(order);
    }

    @Override
    public Order findOrderById(long id) {
        return ordersrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found"));
    }
}
