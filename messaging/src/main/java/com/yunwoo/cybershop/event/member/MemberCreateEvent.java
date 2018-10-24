package com.yunwoo.cybershop.event.member;







import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
public class MemberCreateEvent implements Serializable {
    private static final long serialVersionUID = -6449731052912154892L;
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
}
