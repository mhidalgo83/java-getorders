package com.ordersproject.demo.services;

import com.ordersproject.demo.models.Customer;
import com.ordersproject.demo.repositories.CustomersRepository;
import com.ordersproject.demo.views.OrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service (value = "customerServices")
public class CustomerServicesImpl implements CustomerServices {
    @Autowired
    CustomersRepository custrepos;

    @Override
    public List<Customer> findAllCustomersAndOrders() {
        List<Customer> custList = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(custList::add);
        return custList;
    }

    @Override
    public Customer findCustomerById(long id) {
        return custrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));
    }

    @Override
    public List<Customer> findCustomersWithName(String name) {
        List<Customer> custList = new ArrayList<>();
        custrepos.findByCustnameContainingIgnoringCase(name).iterator().forEachRemaining(custList::add);
        return custList;
    }

    @Override
    public List<OrderCount> getOrderCount() {
        List<OrderCount> list = custrepos.getOrderCount();
        return list;
    }

    @Override
    public Customer save(Customer customer) {
        return custrepos.save(customer);
    }
}
