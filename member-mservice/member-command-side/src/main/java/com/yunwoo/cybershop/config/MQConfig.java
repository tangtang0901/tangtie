package com.yunwoo.cybershop.config;

import org.axonframework.serializer.xml.XStreamSerializer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by water on 2016/4/13.
 */

@Configuration
public class MQConfig {

    @Value(value="${mq.address}")
    String address;
    @Value(value="${mq.password}")
    String password;
    @Value(value="${mq.username}")
    String username;
    @Value(value="${mq.queuename}")
    String queueName;
    @Value(value="${mq.exchange}")
    String exchange;

    @Bean(name = "serializer")
    XStreamSerializer xStreamSerializer(){
        return  new XStreamSerializer();
    }

    @Bean(name = "amqpConnection")
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory fc = new CachingConnectionFactory ();
        fc.setAddresses(address);
        fc.setPassword(password);
        fc.setUsername(username);
        return fc;
    }


    @Bean
    public AmqpAdmin amqpAdmin(){
        RabbitAdmin admin = new RabbitAdmin(connectionFactory());
        admin.declareExchange(exchange());
        admin.declareQueue(orderReBookQueue());
        admin.declareBinding(binding());
        return admin;
    }


    @Bean
    public Exchange exchange(){
        return new FanoutExchange(exchange);
    }


    @Bean(name="queue")
    public Queue orderReBookQueue() {
        Queue queue = new Queue(queueName,true,false,false,null);
        return queue;
    }

    @Bean
    public Binding binding(){
        FanoutExchange hlcbCoreExchange = (FanoutExchange) exchange();
        return  BindingBuilder.bind(orderReBookQueue()).to(hlcbCoreExchange);
    }


}
