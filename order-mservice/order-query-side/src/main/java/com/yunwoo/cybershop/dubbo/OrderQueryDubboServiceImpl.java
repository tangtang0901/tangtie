package com.yunwoo.cybershop.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.yunwoo.cybershop.business.OrderService;
import com.yunwoo.cybershop.dto.OrderDTO;
import com.yunwoo.cybershop.business.OrderService;
import com.yunwoo.cybershop.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0",timeout = 20000)
public class OrderQueryDubboServiceImpl implements OrderQueryDubboService{
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO getById(int id) {
        return orderService.getById(id);
    }
}
