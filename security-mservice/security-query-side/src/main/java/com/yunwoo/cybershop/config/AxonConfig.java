package com.yunwoo.cybershop.config;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by water on 2016/4/15.
 */

@Configuration
@Slf4j
@ImportResource({"spring-axon-infrastructure.xml"})
public class AxonConfig {


    @Autowired
    CommandBus commandBus;

    @Bean
    public CommandGateway commandGateWay() {
        return new DefaultCommandGateway(commandBus);
    }

    @Autowired
    private EventBusTerminal terminal;

    @Bean
    ClusterSelector autowiringClusterSelector(){
        return new AutowiringClusterSelector();
    }


    @Bean
    public EventBus eventBus(){
        EventBus bus = new ClusteringEventBus(autowiringClusterSelector(),terminal);
        return bus;
    }


}
