package com.yunwoo.cybershop.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.yunwoo.cybershop.business.ProductCategoryService;
import com.yunwoo.cybershop.command.ProductCommand;
import com.yunwoo.cybershop.command.ProductUpdateCommand;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 城市业务 Dubbo 服务层实现层
 *
 * Created by bysocket on 28/02/2017.
 */
// 注册为 Dubbo 服务
@Service(version = "1.0.0")
public class ProductCategoryDubboServiceImpl implements ProductCategoryDubboService {
    @Autowired
    private ProductCategoryService ProductCategoryService;

    @Override
    public void create(ProductCommand productCommand) {
        //添加商品子级信息
        ProductCategoryService.create(productCommand);
    }

    @Override
    public void update(ProductUpdateCommand productUpdateCommand) {
        ProductCategoryService.update(productUpdateCommand);
    }


}
