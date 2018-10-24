package com.yunwoo.cybershop.handler.event;

import com.yunwoo.cybershop.event.order.OrderCreateEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventHandler {
    @EventHandler
    public  void handle(OrderCreateEvent event){
        log.warn("order-query-side receive:{}",event);
    }

}
