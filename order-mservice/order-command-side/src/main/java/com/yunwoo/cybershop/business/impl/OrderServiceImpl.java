package com.yunwoo.cybershop.business.impl;

import com.yunwoo.cybershop.business.OrderService;
import com.yunwoo.cybershop.command.OrderCreateCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CommandGateway gateway;

    @Override
    public void create(OrderCreateCommand command) {
        gateway.send(command);
    }


}
