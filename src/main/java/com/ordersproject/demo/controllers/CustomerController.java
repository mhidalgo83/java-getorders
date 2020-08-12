package com.ordersproject.demo.controllers;

import com.ordersproject.demo.models.Customer;
import com.ordersproject.demo.services.CustomerServices;
import com.ordersproject.demo.views.OrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")


public class CustomerController {
    @Autowired
    private CustomerServices customerServices;

    //GET
    // http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> findCustomersAndOrders() {
        List<Customer> custsAndOrders = customerServices.findAllCustomersAndOrders();
        return new ResponseEntity<>(custsAndOrders, HttpStatus.OK);
    }

    //GET
    // http://localhost:2019/customers/customer/{id}
    @GetMapping(value = "/customer/{custcode}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long custcode) {
        Customer customer = customerServices.findCustomerById(custcode);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    //GET
    // http://localhost:2019/customers/namelike/{likename}
    @GetMapping(value = "/namelike/{likename}", produces = "application/json")
    public ResponseEntity<?> findCustomerWithName (@PathVariable String likename) {
        List<Customer> custList = customerServices.findCustomersWithName(likename);
        return new ResponseEntity<>(custList, HttpStatus.OK);
    }

    //GET
    // http://customers/orders/count
    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?> getOrderCounts() {
        List<OrderCount> orderCountList = customerServices.getOrderCount();
        return new ResponseEntity<>(orderCountList, HttpStatus.OK);
    }

    //POST
    // http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes="application/json")
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customer newCustomer) {
       newCustomer.setCustcode(0);
       newCustomer = customerServices.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/" + newCustomer.getCustcode())
                .build()
                .toUri();
        responseHeaders.setLocation(newCustomerURI);
         return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //PUT
    // http://localhost:2019/customers/customer/{custcode}
    @PutMapping(value = "/customer/{custcode}", consumes = "application/json")
    public ResponseEntity<?> fullUpdateCustomer(@Valid @RequestBody Customer customer, @PathVariable long custcode) {
        customer.setCustcode(custcode);
        customerServices.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //PATCH
    // http://localhost:2019/customers/customer/{custcode}
    @PatchMapping(value = "/customer/{custcode}", consumes = "application/json")
    public ResponseEntity<?> partialUpdateCustomer(@Valid @RequestBody Customer customer, @PathVariable long custcode) {
        customerServices.update(customer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DELETE
    // http://localhost:2019/customers/customer/{custcode}
    @DeleteMapping(value = "/customer/{custcode}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long custcode) {
        customerServices.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
