package com.yunwoo.cybershop.storage.po;

import com.yunwoo.cybershop.annotation.table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 元素PO
 */
@table(name="orders")
@Getter
@Setter
public class OrderPO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String remark;

}
