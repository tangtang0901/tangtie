package com.yunwoo.cybershop.web.service.product;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yunwoo.cybershop.command.ProductCommand;
import com.yunwoo.cybershop.command.ProductUpdateCommand;
import com.yunwoo.cybershop.dto.ProductCategoryDTO;
import com.yunwoo.cybershop.dubbo.PrimaryKeyDubboService;
import com.yunwoo.cybershop.dubbo.ProductCategoryDubboService;
import com.yunwoo.cybershop.dubbo.ProductCategroyQueryDubboService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductCategoryServiceimpl  implements   ProductCategoryService {

    @Reference(version = "1.0.0",check = true)
    private ProductCategroyQueryDubboService productCategroyQueryDubboService;
    @Reference(version = "1.0.0",check = true)
    private ProductCategoryDubboService productCategoryDubboService;
    @Reference(version = "1.0.0",check = true)
    private ProductCategoryDubboService ProductCategoryDubboService;
    @Reference(version = "1.0.0",check = true)
    private PrimaryKeyDubboService primaryKeyDubboService;




    @Override
    public List<ProductCategoryDTO> getNodesById(int id) {
        return productCategroyQueryDubboService.getNodesById(id);
    }

    @Override
    public ProductCategoryDTO getById(int id) {
        return productCategroyQueryDubboService.getById(id);
    }

    @Override
    public void deleteCategoryById(int id) {
        productCategroyQueryDubboService.deleteCategoryById(id);
    }



    @Override
    public boolean productCategoryAddList(ProductCategoryDTO productCategoryDTO) {
        //增加商品子级信息
        ProductCommand pac =new ProductCommand(primaryKeyDubboService.generatePrimaryKey("category"),
                productCategoryDTO.getPid(),productCategoryDTO.getName(),productCategoryDTO.getPicId(),productCategoryDTO.getLevel(),
                productCategoryDTO.getStatus(),new Date(),new Date(),productCategoryDTO.getPicIdUrl());



        try {
            ProductCategoryDubboService.create(pac);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean productCategoryUpdate(ProductCategoryDTO productCategoryDTO) {
        ProductUpdateCommand puc =new ProductUpdateCommand(productCategoryDTO.getId(),
                productCategoryDTO.getPid(),
                productCategoryDTO.getName(),
                productCategoryDTO.getPicId(),
                productCategoryDTO.getLevel(),
                productCategoryDTO.getStatus(),
                new Date(),new Date());

        try {
            ProductCategoryDubboService.update(puc);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
