package com.yunwoo.cybershop.utils;

import org.apache.log4j.Logger;

import java.security.MessageDigest;

public class MD5Util {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MD5Util.class);

	/**
	 *
	 * @param s
	 * @return
	 */
	public final static String MD5(String s) {

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			String returnString = new String(str);
			return returnString;
		} catch (Exception e) {
			logger.error("MD5(String)", e); 
			e.printStackTrace();
			return null;
		}
	}
}
