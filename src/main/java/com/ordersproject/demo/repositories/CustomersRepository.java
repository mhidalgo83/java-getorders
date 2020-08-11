package com.ordersproject.demo.repositories;

import com.ordersproject.demo.models.Customer;
import com.ordersproject.demo.views.OrderCounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepository extends CrudRepository <Customer, Long> {
    List<Customer> findByCustnameContainingIgnoringCase(String name);



    @Query(value = "SELECT c.custname AS name, COUNT(ordnum) AS ordercounts " +
            "FROM customers c LEFT JOIN orders o " +
            "ON c.custcode = o.custcode " +
            "GROUP BY c.custname " +
            "ORDER BY ordercounts DESC")
    List<OrderCounts> findOrderCounts();
}
