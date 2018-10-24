package com.yunwoo.cybershop.business;

import com.yunwoo.cybershop.dto.ProductCategoryDTO;

import java.util.List;

public interface ProductCateQueryService {


    public List<ProductCategoryDTO> getNodesById(int id);

    public ProductCategoryDTO getById(int id);
    void  deleteCategoryById(int id);
    boolean ProductAddList(ProductCategoryDTO productCategoryDTO);
    boolean productCategoryUpdate(ProductCategoryDTO productCategoryDTO);

}
