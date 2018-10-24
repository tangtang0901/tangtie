package com.yunwoo.cybershop.command;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 禁用'会员'命令
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberChangePasswordCommand implements Serializable{
    private Integer id;
    private String password;

}