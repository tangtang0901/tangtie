package com.yunwoo.cybershop.dubbo;


import com.alibaba.dubbo.config.annotation.Service;
import com.yunwoo.cybershop.business.ProductCateQueryService;
import com.yunwoo.cybershop.dto.ProductCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0",timeout = 20000)
public class ProductCategroyQueryDubboServiceimpl implements  ProductCategroyQueryDubboService {

    @Autowired
    private ProductCateQueryService   productCateQueryService;



    @Override
    public List<ProductCategoryDTO> getNodesById(int id) {
        return productCateQueryService.getNodesById(id);
    }

    @Override
    public ProductCategoryDTO getById(int id) {
        return productCateQueryService.getById(id);
    }

    @Override
    public void deleteCategoryById(int id) {
        productCateQueryService.deleteCategoryById(id);
    }


}
