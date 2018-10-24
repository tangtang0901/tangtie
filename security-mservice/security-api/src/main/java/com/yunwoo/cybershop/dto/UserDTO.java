package com.yunwoo.cybershop.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/5 下午4:57
 */
@Getter
@Setter
public class UserDTO implements Serializable{
    private int id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
}
