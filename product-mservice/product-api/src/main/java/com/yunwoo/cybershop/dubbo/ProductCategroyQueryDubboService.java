package com.yunwoo.cybershop.dubbo;



import com.yunwoo.cybershop.dto.ProductCategoryDTO;


import java.util.List;


public interface ProductCategroyQueryDubboService {


    public List<ProductCategoryDTO>  getNodesById(int id);
    public ProductCategoryDTO  getById(int id);
           void deleteCategoryById(int id);
}
