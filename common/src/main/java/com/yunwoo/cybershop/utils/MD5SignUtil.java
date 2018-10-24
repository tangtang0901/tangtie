package com.yunwoo.cybershop.utils;

public class MD5SignUtil {
	public static String Sign(String content, String key) {
		String signStr = content + "&key=" + key;
		String returnString = MD5Util.MD5(signStr).toUpperCase();
		return returnString;

	}

	public static boolean VerifySignature(String content, String sign,
			String md5Key) {
		String signStr = content + "&key=" + md5Key;
		String calculateSign = MD5Util.MD5(signStr).toUpperCase();
		String tenpaySign = sign.toUpperCase();
		boolean returnboolean = (calculateSign == tenpaySign);
		return returnboolean;
	}
}
