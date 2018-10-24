package com.yunwoo.cybershop.storage.po;

import com.yunwoo.cybershop.annotation.table;
import com.yunwoo.cybershop.dto.ProductCategoryDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@table(name="category")
@Getter
@Setter
public class ProductCategroyPO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;// 表id
    private Integer pid;// 父节点id
    private String name;// 节点内容
    private Integer picId;/* 图片 */
    private Integer level;
    private Integer status;
    private String icon;
    private Date createTime;
    private Date lastModifyTime;

}
