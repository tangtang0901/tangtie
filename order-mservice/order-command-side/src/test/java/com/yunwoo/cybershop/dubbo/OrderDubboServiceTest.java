package com.yunwoo.cybershop.dubbo;

import com.yunwoo.cybershop.OrderApplication;
import com.yunwoo.cybershop.command.OrderCreateCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrderApplication.class)
public class OrderDubboServiceTest {
    @Autowired
    private OrderDubboService orderDubboService;
    @Test
    public void testCreate(){
        OrderCreateCommand orderCreateCommand = new OrderCreateCommand(89898989,"remarkTest");
        orderDubboService.create(orderCreateCommand);
    }
}
