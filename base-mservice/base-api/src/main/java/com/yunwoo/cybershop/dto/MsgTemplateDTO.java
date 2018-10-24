package com.yunwoo.cybershop.dto;

import com.yunwoo.cybershop.annotation.table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 短信模板PO
 */
@Getter
@Setter
public class MsgTemplateDTO implements Serializable {

	private int id;
	/**
	 * 内部短信模板代号
	 */
	private Integer internalCode;
	/**
	 * 第三方短信模板编码
	 */
	private String code;
	/**
	 * 短信模板描述
	 */
	private String desc;

}
