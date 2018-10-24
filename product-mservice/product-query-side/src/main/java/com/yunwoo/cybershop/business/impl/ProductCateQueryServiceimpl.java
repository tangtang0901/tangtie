package com.yunwoo.cybershop.business.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yunwoo.cybershop.business.ProductCateQueryService;
import com.yunwoo.cybershop.dto.PictureDTO;
import com.yunwoo.cybershop.dto.ProductCategoryDTO;
import com.yunwoo.cybershop.dubbo.PictureQueryDubboService;
import com.yunwoo.cybershop.storage.db.ProductCategroyDBStorage;
import com.yunwoo.cybershop.storage.po.ProductCategroyPO;
import com.yunwoo.cybershop.utils.BeanConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductCateQueryServiceimpl implements ProductCateQueryService {
    public static final String CACHE_NAME = "productCategory";
    public static final String CACHE_KEY = "'productCategory#'";


    @Autowired
    private ProductCategroyDBStorage productCategroyDBStorage;
    @Reference(version = "1.0.0",check = true)
    private PictureQueryDubboService pictureQueryDubboService;

    @Autowired
    private ProductCateQueryServiceimpl productCateQueryService;


    @Override
    @Cacheable(value = CACHE_NAME, key = CACHE_KEY + "+#id.toString()", unless = "#result==null")
    public List<ProductCategoryDTO> getNodesById(int id) {
        List<ProductCategoryDTO> listPCD =new ArrayList();
        List<Integer> listId =new ArrayList();
        listId=productCategroyDBStorage.getAllId(id);
        for(Integer PcdId:listId){
           listPCD.add(productCateQueryService.getById(PcdId));
       }
        return listPCD;
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = CACHE_KEY + "+#id.toString()", unless = "#result==null")
    public ProductCategoryDTO getById(int id) {
        ProductCategroyPO productCategroyPO = productCategroyDBStorage.getById(id);
        if (productCategroyPO != null) {
            ProductCategoryDTO productCategoryDTO = BeanConverter.to(productCategroyPO, ProductCategoryDTO.class);
            PictureDTO pictureDTO = pictureQueryDubboService.get(productCategoryDTO.getPicId());
            if(pictureDTO != null){
                productCategoryDTO.setPicIdUrl(pictureDTO.getUrl());
            }
            return productCategoryDTO;
        }
        return null;
    }

    @Override
    public void deleteCategoryById(int id) {
        productCategroyDBStorage.deleteCategoryById(id);
    }


    @Override
    public boolean ProductAddList(ProductCategoryDTO productCategoryDTO) {
        ProductCategroyPO productCategroyPO=new ProductCategroyPO();
        BeanUtils.copyProperties(productCategoryDTO,productCategroyPO);
        return   productCategroyDBStorage.productAddList(productCategroyPO);
    }

    @Override
    @CacheEvict(value = CACHE_NAME, key = CACHE_KEY + "+#productCategoryDTO.id.toString()",condition = "#productCategoryDTO != null")
    public boolean productCategoryUpdate(ProductCategoryDTO productCategoryDTO) {
        ProductCategroyPO productCategroyPO=new ProductCategroyPO();
        BeanUtils.copyProperties(productCategoryDTO,productCategroyPO);
        return productCategroyDBStorage.productCategoryUpdate(productCategroyPO);
    }
}
