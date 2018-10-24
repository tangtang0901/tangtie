package com.yunwoo.cybershop.web.service.product;

import com.yunwoo.cybershop.dto.ProductCategoryDTO;

import java.util.List;

public interface ProductCategoryService {


    public List<ProductCategoryDTO>  getNodesById(int id);
    public ProductCategoryDTO  getById(int id);
    public void  deleteCategoryById(int id);
    public boolean productCategoryAddList(ProductCategoryDTO productCategoryDTO);
    public boolean productCategoryUpdate(ProductCategoryDTO productCategoryDTO);

}
