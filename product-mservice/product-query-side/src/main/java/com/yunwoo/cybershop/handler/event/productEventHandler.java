package com.yunwoo.cybershop.handler.event;


import com.yunwoo.cybershop.business.ProductCateQueryService;
import com.yunwoo.cybershop.dto.ProductCategoryDTO;
import com.yunwoo.cybershop.event.product.ProductCategoryCreateEvent;
import com.yunwoo.cybershop.event.product.ProductCategoryUpdateEvent;
import com.yunwoo.cybershop.utils.BeanConverter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class productEventHandler {

    @Autowired
    private ProductCateQueryService productCateQueryService;

    @EventHandler
    public void handle(ProductCategoryCreateEvent event){

        ProductCategoryDTO PCD= BeanConverter.to(event,ProductCategoryDTO.class);
        if (!productCateQueryService.ProductAddList(PCD)){
            new Exception("商品列表信息保存失败").printStackTrace();
        }

    }

    @EventHandler
    public void handle(ProductCategoryUpdateEvent event){
        ProductCategoryDTO PCD= BeanConverter.to(event,ProductCategoryDTO.class);
        if(!productCateQueryService.productCategoryUpdate(PCD)){
            new Exception("商品列表信息更新失败").printStackTrace();
        }


    }





}
