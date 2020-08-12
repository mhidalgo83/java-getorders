package com.ordersproject.demo.repositories;

import com.ordersproject.demo.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentsRepository extends CrudRepository<Agent, Long> {
}
