package com.yunwoo.cybershop.constant;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/5 下午11:47
 */
public enum TableName {
    ORDER("order","订单"),
    MEMBER("member","会员"),
    PICTURE("picture","图片"),
    PRODUCT("product","商品");
    private String name;
    private String desc;

    TableName(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
