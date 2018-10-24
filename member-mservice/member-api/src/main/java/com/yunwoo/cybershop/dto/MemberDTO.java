package com.yunwoo.cybershop.dto;

import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

@Data
public class MemberDTO implements Serializable{
    private Integer id;
    /**
     * '用户名'
     */
    private String username;
    /**
     * '密码'
     */
    private String password;
    /**
     * '昵称'
     */
    private String nickname;
    /**
     * '生日'
     */
    private Date birthday;
    /**
     * '1男 0女'
     */
    private Integer gender;
    /**
     * '手机'
     */
    private String phonenumber;
    /**
     * '邮箱'
     */
    private String email;
    /**
     * '头像图片ID'
     */
    private Integer avatar;
    /**
     * '是否启用'
     */
    private Boolean isEnable;
    /**
     * '创建时间'
     */
    private Date createTime;
    /**
     * '最后修改时间'
     */
    private Date lastModifyTime;
    /**
     * 头像地址
     */
    private String avatarUrl;
}
