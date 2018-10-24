package com.yunwoo.cybershop.business;

import com.yunwoo.cybershop.command.ProductCommand;
import com.yunwoo.cybershop.command.ProductUpdateCommand;

public interface ProductCategoryService {

   void create(ProductCommand productCommand);
   void update(ProductUpdateCommand productUpdateCommand);

}
