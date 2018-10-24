package com.yunwoo.cybershop.storage.po;

import com.yunwoo.cybershop.annotation.table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 短信配置PO
 */
@Getter
@Setter
@table(name="msg_config")
public class MsgConfigPO implements Serializable {

	private static final long serialVersionUID = 1L;

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
