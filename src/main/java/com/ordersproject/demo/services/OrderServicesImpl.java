package com.ordersproject.demo.services;

import com.ordersproject.demo.models.Agent;
import com.ordersproject.demo.models.Customer;
import com.ordersproject.demo.models.Order;
import com.ordersproject.demo.models.Payment;
import com.ordersproject.demo.repositories.CustomersRepository;
import com.ordersproject.demo.repositories.OrdersRepository;
import com.ordersproject.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service ( value = "orderServices")
public class OrderServicesImpl implements OrderServices{
    @Autowired
    OrdersRepository ordersrepos;

    @Autowired
    PaymentRepository paymentrepos;

    @Autowired
    CustomersRepository custrepos;

    @Override
    public Order save(Order order) {

        Order newOrder = new Order();

        if(order.getOrdnum() != 0) {
            ordersrepos.findById(order.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + order.getOrdnum() + " Not Found"));
        }

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setCustomer(order.getCustomer());
        newOrder.setOrderdescription(order.getOrderdescription());

        // many to many payments
        newOrder.getPayments().clear();
        for (Payment p : order.getPayments()) {
            Payment newPayment = paymentrepos.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + "Not Found"));
        newOrder.getPayments().add(newPayment);
    }

        // many to one customers
        Customer newCustomer = custrepos.findById(newOrder.getCustomer().getCustcode())
                .orElseThrow(() -> new EntityNotFoundException("Customer " + newOrder.getCustomer().getCustcode() + " Not Found"));
        newOrder.setCustomer(newCustomer);

        return ordersrepos.save(newOrder);
    }

    @Override
    public Order findOrderById(long id) {
        return ordersrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found"));
    }
}
