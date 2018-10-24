package com.yunwoo.cybershop.dubbo;

import com.yunwoo.cybershop.dto.OrderDTO;

public interface OrderQueryDubboService {
    OrderDTO getById(int id);
}
