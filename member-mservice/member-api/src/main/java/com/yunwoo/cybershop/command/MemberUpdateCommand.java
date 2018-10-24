package com.yunwoo.cybershop.command;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 新增'会员'命令
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateCommand implements Serializable{
    private Integer id;
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
     * '邮箱'
     */
    private String email;
    /**
     * '头像图片ID'
     */
    private Integer avatar;

}