package com.yunwoo.cybershop.handler.event;

import com.yunwoo.cybershop.business.OrderService;
import com.yunwoo.cybershop.dto.OrderDTO;
import com.yunwoo.cybershop.event.order.OrderCreateEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventHandler {
    @Autowired
    private OrderService orderService;
    @EventHandler
    public  void handle(OrderCreateEvent event){
        log.warn("order-query-side receive:{}",event);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(event.getId());
        orderDTO.setRemark(event.getRemark());
        orderService.save(orderDTO);
    }


}
