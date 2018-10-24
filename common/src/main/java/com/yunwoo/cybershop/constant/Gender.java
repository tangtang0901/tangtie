package com.yunwoo.cybershop.constant;

import lombok.Getter;

/**
 * 性别
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/5 下午11:47
 */
@Getter
public enum Gender {
    MAN(1,"男"),
    FEMALE(0,"女");
    private Integer code;
    private String desc;

    Gender(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
