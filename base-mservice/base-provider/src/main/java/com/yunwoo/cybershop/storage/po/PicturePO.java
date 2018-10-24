package com.yunwoo.cybershop.storage.po;

import com.yunwoo.cybershop.annotation.table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 图片PO
 * @author ALI
 *         at 2017/4/3 0003 22:27
 */
@Getter
@Setter
@table(name="picture")
public class PicturePO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	/**
	 * 图片地址
	 */
	private String url;
	/**
	 * 图片类型
	 */
	private String type;

}
