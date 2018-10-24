package com.yunwoo.cybershop.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Data
public class ProductCategoryDTO  implements Serializable{

    private Integer id;// 表id
    private Integer pid;// 父节点id
    private String name;// 节点内容
    private boolean open = true;
    private Integer picId;/* 图片 */
    private Integer level;
    private Integer status;
    private String icon;
    private Date createTime;
    private Date lastModifyTime;
    private String picIdUrl;
    private List<ProductCategoryDTO> children = new ArrayList<ProductCategoryDTO>();



}
