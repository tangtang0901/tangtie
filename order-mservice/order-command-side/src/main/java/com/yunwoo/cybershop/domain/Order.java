package com.yunwoo.cybershop.domain;

import com.yunwoo.cybershop.command.OrderCloseCommand;
import com.yunwoo.cybershop.command.OrderCreateCommand;
import com.yunwoo.cybershop.event.order.OrderCloseEvent;
import com.yunwoo.cybershop.event.order.OrderCreateEvent;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by water on 2016/4/13.
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Slf4j
public class Order extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Integer id;

    private String remark;




    public Order(OrderCreateCommand command){
        OrderCreateEvent event = new OrderCreateEvent(command.getId(),command.getRemark());
        apply(event);

    }

    public void close(OrderCloseCommand command){
        OrderCloseEvent event = new OrderCloseEvent(command.getId(),command.getRemark());
        apply(event);
    }


    @EventSourcingHandler
    public  void hanndle(OrderCreateEvent event){
        this.id = event.getId();
        this.remark = event.getRemark();
        log.warn("Order.hanndle.OrderCreateEvent");
    }
    @EventSourcingHandler
    public  void hanndle(OrderCloseEvent event){
        this.remark = event.getRemark();
        log.warn("Order.hanndle.OrderCloseEvent");
    }


}
