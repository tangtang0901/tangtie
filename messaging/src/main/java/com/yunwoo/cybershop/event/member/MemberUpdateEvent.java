package com.yunwoo.cybershop.event.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
public class MemberUpdateEvent implements Serializable {
    private static final long serialVersionUID = -6449731052912154892L;
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
    /**
     * '最后修改时间'
     */
    private Date lastModifyTime;
}
