package com.yunwoo.cybershop.dubbo;

import com.yunwoo.cybershop.command.OrderCreateCommand;
import com.yunwoo.cybershop.dto.OrderDTO;

/**
 * 城市业务 Dubbo 服务层
 *
 * Created by bysocket on 28/02/2017.
 */
public interface OrderDubboService {

    void create(OrderCreateCommand createCommand);

}
