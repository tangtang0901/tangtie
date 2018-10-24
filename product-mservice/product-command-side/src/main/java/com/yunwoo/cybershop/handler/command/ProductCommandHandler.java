package com.yunwoo.cybershop.handler.command;

import com.yunwoo.cybershop.command.ProductCommand;
import com.yunwoo.cybershop.command.ProductUpdateCommand;
import com.yunwoo.cybershop.domain.ProductCategory;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProductCommandHandler {
    @Autowired
    @Qualifier("productRepository")
    private Repository<ProductCategory> productRepository;

    @CommandHandler
    public  void hannd(ProductCommand productCommand){
        ProductCategory productCategory = new ProductCategory(productCommand);
        productRepository.add(productCategory);

    }


    @CommandHandler
    public  void hannd(ProductUpdateCommand productUpdateCommand){
        ProductCategory productCategory = productRepository.load(productUpdateCommand.getId());
        productCategory.update(productUpdateCommand);
    }




}
