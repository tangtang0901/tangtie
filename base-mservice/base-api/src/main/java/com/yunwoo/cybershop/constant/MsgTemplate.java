package com.yunwoo.cybershop.constant;

import lombok.Getter;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/12 上午11:40
 */
@Getter
public enum  MsgTemplate {
    REGISTER(1,"注册短信模板"),
    CHANGE_PASSWORD(2,"修改/忘记密码");
    private Integer internalCode;
    private String desc;

    MsgTemplate(Integer internalCode, String desc) {
        this.internalCode = internalCode;
        this.desc = desc;
    }

}
