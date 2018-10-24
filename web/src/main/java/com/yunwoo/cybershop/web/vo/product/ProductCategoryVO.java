package com.yunwoo.cybershop.web.vo.product;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Data
@Setter
@Getter
public class ProductCategoryVO implements Serializable {
    /**
     * ''
     */
    private Integer id;

    /**
     * '父级'
     */
    private Integer pid;
    /**
     * '名称'
     */
    private String name;
    /**
     * * 图片 II*
     */
    private Integer picId;
    /**
     * '级别'
     */
    private Integer level;

    /**
     * '状态码'
     */
    private Integer status;

    /**
     * '创建时间'
     */
    private Date createTime;
    /**
     * '创建时间'
     */
    private Date lastModifyTime;

    private String picIdUrl;
    private String icon;

}
