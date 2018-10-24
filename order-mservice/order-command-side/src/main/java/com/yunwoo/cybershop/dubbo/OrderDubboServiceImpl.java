package com.yunwoo.cybershop.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.yunwoo.cybershop.business.OrderService;
import com.yunwoo.cybershop.command.OrderCreateCommand;
import com.yunwoo.cybershop.business.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 城市业务 Dubbo 服务层实现层
 *
 * Created by bysocket on 28/02/2017.
 */
// 注册为 Dubbo 服务
@Service(version = "1.0.0",timeout = 20000)
public class OrderDubboServiceImpl implements OrderDubboService {
    @Autowired
    private OrderService orderService;



    @Override
    public void create(OrderCreateCommand createCommand) {
        orderService.create(createCommand);
    }
}
