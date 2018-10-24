package com.yunwoo.cybershop.storage.po;

import com.yunwoo.cybershop.annotation.table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * '短信发送日志'PO
 */
@Getter
@Setter
@table(name="msg_log")
public class MsgLogPO implements Serializable {
	/**
	 * '短信配置ID'
	 */
	private Integer msgConfig;
	/**
	 * '短信模板ID'
	 */
	private Integer msgTemplate;
	/**
	 * '手机号'
	 */
	private String phonenumber;
	/**
	 * '参数'
	 */
	private String params;
	/**
	 * '业务ID'
	 */
	private String bizId;
	/**
	 * '响应码'
	 */
	private String code;
	/**
	 * '响应信息'
	 */
	private String message;
	/**
	 * '生成时间'
	 */
	private Date createTime;

}
