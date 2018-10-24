package com.yunwoo.cybershop.business;

import com.yunwoo.cybershop.dto.OrderDTO;

public interface OrderService {
    OrderDTO getById(int id);
    boolean save(OrderDTO dto);
}
