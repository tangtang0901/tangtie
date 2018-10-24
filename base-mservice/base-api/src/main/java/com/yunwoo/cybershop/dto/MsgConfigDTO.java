package com.yunwoo.cybershop.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 短信配置PO
 */
@Getter
@Setter
public class MsgConfigDTO implements Serializable {

	private int id;
	/**
	 * 内部编码
	 */
	private Integer internalCode;
	/**
	 * 签名
	 */
	private String signName;
	/**
	 * 访问密匙ID
	 */
	private String accessKeyId;
	/**
	 * 访问密匙串
	 */
	private String accessKeySecret;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;

}
