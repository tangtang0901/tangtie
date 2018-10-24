package com.yunwoo.cybershop.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 分析url工具类
 * @author ALI 2017年3月21日
 */
public class URLUtil {
	/**
	 * 对url的参数进行编码
	 * @param url  待处理的url
	 * @return   编码后的url
	 * @throws UnsupportedEncodingException 
	 */
	public static String encodeUrl(String url) throws UnsupportedEncodingException {
		StringBuffer resultSb = new StringBuffer();
		if (StringUtils.isNotEmpty(url)) {
			String paramsStr = "";
			int start = url.indexOf("?");
			
			if (start == -1) {
				return url;
			}
			paramsStr = url.substring(start + 1);
			resultSb.append(url.substring(0, start + 1));
			String[] params = paramsStr.split("\\&");
			String key = "";
			String value = "";
			String[] paramArr = null;
			for (String param : params) {
				paramArr = param.split("=");
				
				if (paramArr.length == 2) {
					key = paramArr[0];
					value = paramArr[1];
					value = URLEncoder.encode(value, "utf-8");
					resultSb.append(key).append("=").append(value).append("&");
				}
			}
		}
		return resultSb.length() > 0 ? resultSb.substring(0, resultSb.length() - 1) : "";
	}
	
	/**
	 * 解码url
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeUrl(String url) throws UnsupportedEncodingException {
		return URLDecoder.decode(url, "utf-8");
	}
	
	/**
	 * 根据url解析参数集合
	 * @param url
	 * @return
	 */
	public static Map<String, String> getParamsByURL(String url) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		if (StringUtils.isNotEmpty(url)) {
			int start = url.indexOf("?");
			
			if (start > -1) {
				String paramsStr = url.substring(start + 1);
				String[] paramsArr = paramsStr.split("\\&");
				String[] paramArr = null;
				for (String param : paramsArr) {
					paramArr = param.split("=");
					
					if (paramArr.length == 2) {
						paramsMap.put(paramArr[0], paramArr[1]);
					}
				}
			}
		}
		return paramsMap;
	}
	
}
