package com.yunwoo.cybershop.command;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 启用'会员'命令
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEnableCommand implements Serializable{
    private Integer id;

}