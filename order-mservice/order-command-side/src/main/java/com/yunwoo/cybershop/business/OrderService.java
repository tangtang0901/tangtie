package com.yunwoo.cybershop.business;

import com.yunwoo.cybershop.command.OrderCreateCommand;

public interface OrderService {
    void create(OrderCreateCommand command);

}
