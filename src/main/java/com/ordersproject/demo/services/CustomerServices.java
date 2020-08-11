package com.ordersproject.demo.services;

import com.ordersproject.demo.models.Customer;

import java.util.List;

public interface CustomerServices {

    List<Customer> findAllCustomersAndOrders();

    Customer findCustomerById(long id);

    List<Customer> findCustomersWithName(String name);

    Customer save(Customer customer);
}
