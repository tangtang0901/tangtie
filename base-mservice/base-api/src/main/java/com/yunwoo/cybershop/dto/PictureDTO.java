package com.yunwoo.cybershop.dto;

import com.yunwoo.cybershop.annotation.table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/5 下午6:14
 *
 */

@Setter
@Getter
public class PictureDTO implements Serializable {
    private int id;
    /**
     * 图片地址
     */
    private String url;
    /**
     * 图片类型
     */
    private String type;

}
