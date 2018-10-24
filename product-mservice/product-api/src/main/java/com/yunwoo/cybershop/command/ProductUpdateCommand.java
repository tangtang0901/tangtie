package com.yunwoo.cybershop.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateCommand implements Serializable {

    private Integer id;// 表id
    private Integer pid;// 父节点id
    private String name;// 节点内容
    private Integer picId;/* 图片 */
    private Integer level;
    private Integer status;
    private Date createTime;
    private Date lastModifyTime;
    private String picIdUrl;

    public ProductUpdateCommand(Integer id, Integer pid, String name, Integer picId, Integer level, Integer status, Date createTime, Date lastModifyTime) {
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
