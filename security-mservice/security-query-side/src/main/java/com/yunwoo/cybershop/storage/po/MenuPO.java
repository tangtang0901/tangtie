package com.yunwoo.cybershop.storage.po;

import com.yunwoo.cybershop.annotation.table;
import com.yunwoo.cybershop.annotation.table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 菜单
 * @author Fox
 */
@Getter
@Setter
@table(name="menu")
public class MenuPO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int pId;
	private String name;
	private int level;
	private int sequence;
	private String url;
	private String icon;

	
	
}
