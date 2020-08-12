package com.ordersproject.demo.repositories;

import com.ordersproject.demo.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Order, Long> {
}
