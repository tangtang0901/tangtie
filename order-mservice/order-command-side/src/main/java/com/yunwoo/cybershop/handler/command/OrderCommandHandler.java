package com.yunwoo.cybershop.handler.command;

import com.yunwoo.cybershop.command.OrderCloseCommand;
import com.yunwoo.cybershop.command.OrderCreateCommand;
import com.yunwoo.cybershop.domain.Order;
import com.yunwoo.cybershop.domain.Order;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderCommandHandler {
    @Autowired
    @Qualifier("orderRepository")
    private Repository<Order> orderRepository;
    @CommandHandler
    public  void hannd(OrderCreateCommand command){
        Order order = new Order(command);
        orderRepository.add(order);

    }
    @CommandHandler
    public  void hannd(OrderCloseCommand command){
        Order order = orderRepository.load(command.getId());
        order.close(command);

    }
}
