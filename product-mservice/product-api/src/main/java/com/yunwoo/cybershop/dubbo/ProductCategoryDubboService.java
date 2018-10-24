package com.yunwoo.cybershop.dubbo;

import com.yunwoo.cybershop.command.ProductCommand;
import com.yunwoo.cybershop.command.ProductUpdateCommand;

/**
 * 城市业务 Dubbo 服务层
 *
 * Created by bysocket on 28/02/2017.
 */
public interface ProductCategoryDubboService {


    void create(ProductCommand productCommand);
    void update(ProductUpdateCommand productUpdateCommand);

}
