package com.yunwoo.cybershop.saga;

import com.yunwoo.cybershop.command.OrderCloseCommand;
import com.yunwoo.cybershop.event.order.OrderCloseSagaEvent;
import com.yunwoo.cybershop.event.order.OrderCreateEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.annotation.Timestamp;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.EndSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class OrderSaga extends AbstractAnnotatedSaga {
    private Integer flag = 1;
    @Autowired
    private transient EventScheduler scheduler;
    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(OrderCreateEvent event, @Timestamp DateTime eventTime) {
        log.warn("---receive OrderCreateEvent");
        associateWith("id","abc");
        scheduler.schedule(new DateTime(System.currentTimeMillis()+20000L), new OrderCloseSagaEvent(event.getId(),event.getRemark()));
        flag = 2;
        log.warn("---scheduler OrderCloseSagaEvent");
    }
    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(OrderCloseSagaEvent event, @Timestamp DateTime eventTime) {
        log.warn("---receive OrderCloseSagaEvent:"+event.getId()+":"+event.getRemark());
        OrderCloseCommand command = new OrderCloseCommand(event.getId(),"关闭订单");
        commandGateway.send(command);
        log.warn("---flag ="+flag);

    }

}




