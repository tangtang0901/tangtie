package com.yunwoo.cybershop.domain;

import com.yunwoo.cybershop.command.ProductCommand;
import com.yunwoo.cybershop.command.ProductUpdateCommand;
import com.yunwoo.cybershop.event.product.ProductCategoryCreateEvent;
import com.yunwoo.cybershop.event.product.ProductCategoryUpdateEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.Date;

/**
 * Created by water on 2016/4/13.
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Slf4j
public class ProductCategory extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Integer id;// 表id
    private Integer pid;// 父节点id
    private String name;// 节点内容
    private Integer picId;/* 图片 */
    private Integer level;
    private Integer status;
    private String   icon;
    private Date createTime;
    private Date lastModifyTime;


    public ProductCategory(ProductCommand productCommand){
        ProductCategoryCreateEvent event = new ProductCategoryCreateEvent(productCommand.getId(),
                productCommand.getPid(),productCommand.getName(),productCommand.getPicId(),
                productCommand.getLevel(),productCommand.getStatus(),
                new Date(),new Date());
        apply(event);

    }

    public  void   update(ProductUpdateCommand productUpdateCommand){
        ProductCategoryUpdateEvent event=new ProductCategoryUpdateEvent(productUpdateCommand.getId(),productUpdateCommand.getPid(),productUpdateCommand.getName(),productUpdateCommand.getPicId(),productUpdateCommand.getLevel(),
                productUpdateCommand.getStatus(),new Date());
        apply(event);
    }


     @EventSourcingHandler
     public void handle(ProductCategoryUpdateEvent event){
        this.id=event.getId();
        this.name=event.getName();
        this.picId=event.getPicId();
        this.pid=event.getPid();
        this.level=event.getLevel();
        this.status=event.getStatus();
        this.lastModifyTime=event.getLastModifyTime();
         log.warn("product.hanndle.ProductCategoryCreateEvent");
     }


    @EventSourcingHandler
    public  void hanndle(ProductCategoryCreateEvent event){
        this.id=event.getId();
        this.level=event.getLevel();
        this.name=event.getName();
        this.picId=event.getPicId();
        this.pid=event.getPid();
        this.status=event.getStatus();
        this.createTime=event.getCreateTime();
        this.lastModifyTime=event.getLastModifyTime();
        log.warn("product.hanndle.ProductCategoryCreateEvent");
    }

}
