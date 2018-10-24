package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.BaseStorage;
import com.yunwoo.cybershop.dto.ProductCategoryDTO;
import com.yunwoo.cybershop.storage.po.ProductCategroyPO;

import java.util.List;

public interface ProductCategroyDBStorage extends BaseStorage<ProductCategoryDTO>{


    public List<Integer> getAllId(int id);
    public ProductCategroyPO getById(int id);
    void deleteCategoryById(int id);
    boolean productAddList(ProductCategroyPO productCategroyPO);
    boolean productCategoryUpdate(ProductCategroyPO productCategroyPO);
}
