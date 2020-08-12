package com.ordersproject.demo.services;

import com.ordersproject.demo.models.Customer;
import com.ordersproject.demo.views.OrderCount;

import java.util.List;

public interface CustomerServices {

    List<Customer> findAllCustomersAndOrders();

    Customer findCustomerById(long id);

    List<Customer> findCustomersWithName(String name);

    List<OrderCount> getOrderCount();

    Customer save(Customer customer);

    Customer update(Customer customer, long id);

    void delete(long id);
}
