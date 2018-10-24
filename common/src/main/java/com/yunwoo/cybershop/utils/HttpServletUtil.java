package com.yunwoo.cybershop.utils;

import org.apache.commons.httpclient.NameValuePair;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * HttpServlet工具类
 *
 */
public class HttpServletUtil {

	/**
	 * 将HttpServletRequest参数转换成Map<String, String>
	 * 
	 * @param request
	 * @return Map<String, String>
	 */
	public static Map<String, String> getRequestParameterMap(
			HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Map<?, ?> requestParams = request.getParameterMap();
		for (Iterator<?> iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		return params;
	}

	/**
	 * 将Map<String,String>中所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

	/**
	 * 将请求参数转换成字符串
	 */
	public static String getRequestParameterString(HttpServletRequest request) {
		return createLinkString(getRequestParameterMap(request));
	}

	/**
	 * Map类型数组转换成NameValuePair类型
	 * 
	 * @param properties
	 *            Map类型数组
	 * @return NameValuePair类型数组
	 */
	public static NameValuePair[] generatNameValuePair(
			Map<String, String> properties) {
		NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
		int i = 0;
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			nameValuePair[i++] = new NameValuePair(entry.getKey(),
					entry.getValue());
		}
		return nameValuePair;
	}
}
