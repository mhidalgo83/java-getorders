package com.ordersproject.demo.services;

import com.ordersproject.demo.models.Customer;
import com.ordersproject.demo.views.OrderCounts;

import java.util.List;

public interface CustomerServices {

    List<Customer> findAllCustomersAndOrders();

    Customer findCustomerById(long id);

    List<Customer> findCustomersWithName(String name);

    List<OrderCounts> getOrderCounts();

    Customer save(Customer customer);
}
