package com.yunwoo.cybershop.utils;

import java.math.BigDecimal;

/**
 * 对BigDecimal类型的数据截取小数点后指定位数
 * 默认截取两位
 */
public class BigDecimalUtil {
	/**默认小数点后2位**/
	private static final Integer DEFAULT_SCALE = 2;
	
	public static BigDecimal scale(int scale,BigDecimal in){
		return in.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}
	
	public static BigDecimal scale(BigDecimal in){
		if(null == in){
			return BigDecimal.valueOf(0);
		}
		return scale(DEFAULT_SCALE,in);
	}
	
}
