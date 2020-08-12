package com.ordersproject.demo.controllers;

import com.ordersproject.demo.models.Customer;
import com.ordersproject.demo.models.Order;
import com.ordersproject.demo.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/orders")

public class OrderController {
    @Autowired
    private OrderServices orderServices;

    // http://localhost:2019/orders/order/id
    @GetMapping(value = "/order/{ordnum}", produces = "application/json")
    public ResponseEntity<?> findOrderById(@PathVariable long ordnum ) {
        Order order = orderServices.findOrderById(ordnum);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // POST
    // http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes = "application/json")
    public ResponseEntity<?> addOrder(@Valid @RequestBody Order order) {
        order.setOrdnum(0);
        order = orderServices.save(order);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI orderURI = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/" + order.getOrdnum())
                .build()
                .toUri();
        responseHeaders.setLocation(orderURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
