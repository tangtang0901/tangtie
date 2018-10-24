package com.yunwoo.cybershop.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * XML返回结果处理工具类
 *
 */
public class XMLUtil {

	/**
	 * @param parmas
	 * @return
	 */
	public static String map2Xml(Map<String, String> parmas) {
		StringBuffer xml = new StringBuffer();
		for (Map.Entry<String, String> en : parmas.entrySet()) {
			String value = en.getValue();
			xml.append("<" + en.getKey() + ">" + value + "</" + en.getKey()+ ">");
		}
		return xml.toString();
	}

	/**
	 * @param xml
	 * @return
	 */
	public static Map<String, String> xml2Map(String xml) {
		Map<String, String> result = new HashMap<String, String>();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
			Element rootEle = doc.getRootElement();
			@SuppressWarnings("unchecked")
			Iterator<Element> iter = rootEle.elements().iterator();
			while (iter.hasNext()) {
				Element ele = iter.next();
				String name = ele.getName();
				String value = ele.getText();
				result.put(name, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("123", "fddsfsdff");
		map.put("2123", "fddsfsdfdsff");
		map.put("123", "fdd44554sfsdff");
//		List<String> list = new ArrayList<String>();
		System.out.println(map2Xml(map));
	}
}
