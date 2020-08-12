package com.ordersproject.demo.services;

import com.ordersproject.demo.models.Order;

public interface OrderServices {

    Order findOrderById(long id);

    Order save(Order order);
}
