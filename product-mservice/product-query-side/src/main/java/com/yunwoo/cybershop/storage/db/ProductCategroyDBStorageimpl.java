package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.DaoSupport;
import com.yunwoo.cybershop.dto.ProductCategoryDTO;
import com.yunwoo.cybershop.storage.po.ProductCategroyPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ProductCategroyDBStorageimpl implements  ProductCategroyDBStorage {
    private static final Logger log = LoggerFactory.getLogger(ProductCategroyDBStorageimpl.class);
    @Autowired
    private DaoSupport dao;


    @Override
    public boolean add(ProductCategoryDTO productCategoryDTO) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(ProductCategoryDTO productCategoryDTO) {
        return false;
    }

    @Override
    public ProductCategoryDTO get(int id) {
        return dao.get(ProductCategoryDTO.class,id);
    }



    @Override
    public List<Integer> getAllId(int id) {
        String sql ="select id from category where pid = ?";
        return dao.queryIntegers(sql,id);
    }

    @Override
    public ProductCategroyPO getById(int id) {
          return dao.get(ProductCategroyPO.class,id);
    }

    @Override
    public void deleteCategoryById(int id) {
        String sql="update category set status = 0 where id = ?";
        dao.update(sql,id);
    }

    @Override
    public boolean productAddList(ProductCategroyPO ProductCategroyPO) {
        return dao.add(ProductCategroyPO);
    }

    @Override
    public boolean productCategoryUpdate(ProductCategroyPO productCategroyPO) {
        return dao.update(productCategroyPO);
    }


}
