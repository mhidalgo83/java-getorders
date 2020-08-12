package com.ordersproject.demo.services;

import com.ordersproject.demo.models.Agent;


public interface AgentServices {

    Agent findAgentById(long id);

    Agent save(Agent agent);
}
