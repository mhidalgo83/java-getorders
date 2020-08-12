package com.ordersproject.demo.services;

import com.ordersproject.demo.models.Agent;
import com.ordersproject.demo.models.Customer;
import com.ordersproject.demo.models.Order;
import com.ordersproject.demo.repositories.AgentsRepository;
import com.ordersproject.demo.repositories.CustomersRepository;
import com.ordersproject.demo.repositories.OrdersRepository;
import com.ordersproject.demo.views.OrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service (value = "customerServices")
public class CustomerServicesImpl implements CustomerServices {
    @Autowired
    CustomersRepository custrepos;

    @Autowired
    OrdersRepository ordersrepos;

    @Autowired
    AgentsRepository agentrepos;

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

    @Transactional
    @Override
    public Customer save(Customer customer) {

        Customer newCustomer = new Customer();

        if(customer.getCustcode() != 0) {
            custrepos.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found"));
        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        //One to many orders
        newCustomer.getOrders().clear();
        for(Order o : customer.getOrders()) {
            Order newOrder = new Order();
            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setAdvanceamount(o.getAdvanceamount());
            newOrder.setCustomer(newCustomer);
            newOrder.setOrderdescription(o.getOrderdescription());
            newCustomer.getOrders().add(newOrder);
        }


        //Many to one agent
        Agent newAgent = agentrepos.findById(newCustomer.getAgent().getAgentcode())
                .orElseThrow(()-> new EntityNotFoundException("Agent " + newCustomer.getAgent().getAgentcode() + " Not Found"));

        newCustomer.setAgent(newAgent);


        return custrepos.save(newCustomer);
    }

    @Transactional
    @Override
    public Customer update(Customer customer, long id) {

        Customer curCustomer = custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));

        if (customer.getCustname() != null) {
            curCustomer.setCustname(customer.getCustname());
        }

        if (customer.getCustcity() != null) {
            curCustomer.setCustcity(customer.getCustcity());
        }

        if (customer.getWorkingarea() != null) {
            curCustomer.setWorkingarea(customer.getWorkingarea());
        }

        if (customer.getCustcountry() != null) {
            curCustomer.setCustcountry(customer.getCustcountry());
        }

        if (customer.getGrade() != null) {
            curCustomer.setGrade(customer.getGrade());
        }

        if (customer.hasvalueforopeningamt) {
            curCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if (customer.hasvalueforreceiveamt) {
            curCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if (customer.hasvalueforpaymentamt) {
            curCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if (customer.hasvalueforoutstandingamt) {
            curCustomer.setOutstandingamt(customer.getOutstandingamt());
        }

        //One to many orders
        if (customer.getOrders().size() > 0) {
            curCustomer.getOrders().clear();
            for (Order o : customer.getOrders()) {
                Order newOrder = new Order();
//                this.ordamount = ordamount;
//                this.advanceamount = advanceamount;
//                this.customer = customer;
//                this.orderdescription = orderdescription;
                newOrder.setOrdamount(o.getOrdamount());
                newOrder.setAdvanceamount(o.getAdvanceamount());
                newOrder.setCustomer(curCustomer);
                newOrder.setOrderdescription(o.getOrderdescription());
                curCustomer.getOrders().add(newOrder);
            }
        }

        //Many to one agent
        if (customer.getAgent() != null) {
            Agent newAgent = agentrepos.findById(customer.getAgent().getAgentcode())
                    .orElseThrow(()-> new EntityNotFoundException("Agent " + customer.getAgent().getAgentcode() + " Not Found"));

            curCustomer.setAgent(newAgent);
        }

        return custrepos.save(curCustomer);
    }
}
