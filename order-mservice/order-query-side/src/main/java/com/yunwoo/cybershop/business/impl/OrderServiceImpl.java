package com.yunwoo.cybershop.business.impl;

import com.yunwoo.cybershop.business.OrderService;
import com.yunwoo.cybershop.dto.OrderDTO;
import com.yunwoo.cybershop.storage.db.OrderDBStorage;
import com.yunwoo.cybershop.storage.po.OrderPO;
import com.yunwoo.cybershop.storage.db.OrderDBStorage;
import com.yunwoo.cybershop.storage.po.OrderPO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    public static final String ORDER_CACHE_NAME = "order";
    public static final String ORDER_CACHE_KEY = "'order#'";
    @Autowired
    private OrderDBStorage orderDBStorage;
    @Override
    @Cacheable(value=ORDER_CACHE_NAME,key = ORDER_CACHE_KEY+"+#id.toString()")
    public OrderDTO getById(int id) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderDBStorage.get(id),orderDTO);
        return orderDTO;
    }
    @Override
    public boolean save(OrderDTO dto) {
        OrderPO po = new OrderPO();
        BeanUtils.copyProperties(dto,po);
        return orderDBStorage.add(po);
    }
}

