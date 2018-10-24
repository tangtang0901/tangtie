package com.yunwoo.cybershop.event.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProductCategoryCreateEvent implements Serializable {
    private static final long serialVersionUID = -6449731052912154892L;
    private Integer id;// 表id
    private Integer pid;// 父节点id
    private String name;// 节点内容
    private Integer picId;/* 图片 */
    private Integer level;
    private Integer status;
    private String icon;
    private Date createTime;
    private Date lastModifyTime;
    private String picIdUrl;

    public ProductCategoryCreateEvent(Integer id, Integer pid, String name, Integer picId, Integer level, Integer status, Date createTime, Date lastModifyTime) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.picId = picId;
        this.level = level;
        this.status = status;
        this.createTime = createTime;
        this.lastModifyTime = lastModifyTime;
    }
}
