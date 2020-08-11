package com.ordersproject.demo.controllers;

import com.ordersproject.demo.models.Customer;
import com.ordersproject.demo.services.CustomerServices;
import com.ordersproject.demo.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/customers")


public class CustomerController {
    @Autowired
    private CustomerServices customerServices;

    // http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> findCustomersAndOrders() {
        List<Customer> custsAndOrders = customerServices.findAllCustomersAndOrders();
        return new ResponseEntity<>(custsAndOrders, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer/{id}
    @GetMapping(value = "/customer/{custcode}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long custcode) {
        Customer customer = customerServices.findCustomerById(custcode);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    // http://localhost:2019/customers/namelike/{likename}
    @GetMapping(value = "/namelike/{likename}", produces = "application/json")
    public ResponseEntity<?> findCustomerWithName (@PathVariable String likename) {
        List<Customer> custList = customerServices.findCustomersWithName(likename);
        return new ResponseEntity<>(custList, HttpStatus.OK);
    }

    // http://customer/orders/count
    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?> findOrderCounts() {
        List<OrderCounts> orderList = customerServices.getOrderCounts();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

}
