package com.yunwoo.cybershop.storage.po;

import com.yunwoo.cybershop.annotation.table;
import com.yunwoo.cybershop.annotation.table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 后台用户
 * @author Fox
 */
@Getter
@Setter
@table(name="user")
public class UserPO implements Serializable{

	private static final long serialVersionUID = 1L;
	
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
