package com.yunwoo.cybershop.business.impl;

import com.yunwoo.cybershop.business.ProductCategoryService;
import com.yunwoo.cybershop.command.ProductCommand;
import com.yunwoo.cybershop.command.ProductUpdateCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    CommandGateway gateway;

    @Override
    public void create(ProductCommand productCommand) {
        gateway.send(productCommand);
    }

    @Override
    public void update(ProductUpdateCommand productUpdateCommand) {
        gateway.send(productUpdateCommand);
    }


}
