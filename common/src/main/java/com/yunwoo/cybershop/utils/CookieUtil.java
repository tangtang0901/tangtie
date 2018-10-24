package com.yunwoo.cybershop.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtil {

	private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);

	public static final String VIEWPRODUCT = "viewProduct";
	public static final String MEMBERID = "memberId";
	public static final String LOGINNAME = "loginName";
	public static final String SHOPPINGCART = "shoppingcart";
	public static final String LANGUAGE = "language";
	public static final String SUBSITE = "subsite";
	public static final String SUBREGIONID ="subregionId";

	public static final String DEFAULT_LANGUAGE = "ch";

	public static final String DEFAULT_SEPERATOR = "|";
	public static final String DEFAULT_CONCATEMER = ".";

	public static final String LOGIN_TRUE = "1";
	public static final String LOGIN_FALSE = "0";
	
	public static final String PROPERTYFILENAME = "cookieData.properties";

	/**
	 * 根据cookie的名字获得cookie的值
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request,
			String cookieName) {

		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if (cookies == null) {
			return null;   
		}
		for (Cookie c : cookies) {
			if (c.getName().equals(cookieName)) {
				cookie = c;
				break;
			}
		}
		if (cookie != null) {
			String cookieValue = "";
			try {
				cookieValue = URLDecoder.decode(cookie.getValue(), "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("CookieUtil.getCookieValue->Encoding Error", e); 
				e.printStackTrace();
			}
			return cookieValue;
		}
		return null;
	}

	/**
	 * 设置cookie
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 */
	public static void setCookieValue(HttpServletRequest request,
			HttpServletResponse response, String cookieName,
			String cookieValue, String domain) {
		
		if(StringUtils.isNotBlank(cookieName)){

			StringBuffer cookieNameBuffer = new StringBuffer();
			cookieNameBuffer.append(CookieUtil.LANGUAGE).append(CookieUtil.LOGINNAME).append(CookieUtil.VIEWPRODUCT)
			.append(CookieUtil.SHOPPINGCART).append(CookieUtil.SUBSITE).append(CookieUtil.MEMBERID);
			
			String cookieNames = cookieNameBuffer.toString();
			int expires = -1 * 24 * 60 * 60 ;
			String path = "/";
			boolean secure = false;
			if(cookieNames.contains(cookieName)){
				
				expires = Integer.parseInt(PropertiesUtil.getProperty(cookieName+".expires", PROPERTYFILENAME)) * 24 * 60 * 60;
				path = PropertiesUtil.getProperty(cookieName+".path", PROPERTYFILENAME);
				String secureValue = PropertiesUtil.getProperty(cookieName+".secure", PROPERTYFILENAME);
				if(StringUtils.isBlank(secureValue)||!secureValue.equals("false")){
					secure = true;
				}
			}
			Cookie cookie = null;
			if (cookieValue == null || cookieValue.trim().equals("")) {
				expires = 0;
				cookie = new Cookie(cookieName, "");
				cookieValue = "";
			}else{
				try {
					cookie = new Cookie(cookieName, URLEncoder.encode(
							cookieValue, "utf-8"));// 创建一个Cookie的键-值对
													// 保存cookie时中文转码
				} catch (UnsupportedEncodingException e) {
					logger.error("CookieUtil.setCookieValue->Encoding Error", e); //$NON-NLS-1$
					e.printStackTrace();
					cookie = new Cookie(cookieName, "");
					cookieValue = "";
				}
			}
			
			try {
				cookie.setMaxAge(expires);
			} catch (NumberFormatException e) {
				logger.error("CookieUtil.setCookieValue->Number Format Error", e); //$NON-NLS-1$

				cookie.setMaxAge(-1);
				e.printStackTrace();
			}
			if (domain != null)
				cookie.setDomain(domain);
			if (path != null)
				cookie.setPath(path);
			cookie.setSecure(secure);
			 
			response.addCookie(cookie);
			
		}
		
		/*if (cookieDTO != null) {
			//int expires = cookieDTO.getExpires() * 24 * 60 * 60;
			// domain=StringUtils.isNotEmpty(domain) ? domain :
			// request.getServerName();
			//String path = cookieDTO.getPath();
			//boolean secure = cookieDTO.isSecurity();
			Cookie cookie = null;
			if (cookieValue == null || cookieValue.trim().equals("")) {
				expires = 0;
				cookie = new Cookie(cookieDTO.getName(), "");
				cookieValue = "";
			} else {
				try {
					cookie = new Cookie(cookieDTO.getName(), URLEncoder.encode(
							cookieValue, "utf-8"));// 创建一个Cookie的键-值对
													// 保存cookie时中文转码
				} catch (UnsupportedEncodingException e) {
					logger.error("CookieUtil.setCookieValue->Encoding Error", e); //$NON-NLS-1$
					e.printStackTrace();
					cookie = new Cookie(cookieDTO.getName(), "");
					cookieValue = "";
				}
			}
			try {
				cookie.setMaxAge(expires);
			} catch (NumberFormatException e) {
				logger.error("CookieUtil.setCookieValue->Number Format Error", e); //$NON-NLS-1$

				cookie.setMaxAge(-1);
				e.printStackTrace();
			}
			if (domain != null)
				cookie.setDomain(domain);
			if (path != null)
				cookie.setPath(path);
			cookie.setSecure(secure);
			 
			response.addCookie(cookie);
		}*/
	}
	
	/**
	 * 移除cookie
	 * @param request
	 * @param response
	 * @param domain
	 * @param cookieName
	 */
	public static void removeCookie(HttpServletRequest request,
			HttpServletResponse response, String domain, String cookieName) {

		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		for (Cookie c : cookies) {
			if (c.getName().equals(cookieName) && c.getDomain() == null) {
				cookie = c;
				break;
			} else if (c.getName().equals(cookieName)
					&& StringUtils.isNotEmpty(c.getDomain())
					&& c.getDomain().equals(domain)) {
				cookie = c;
				break;
			}
		}
		if (cookie != null) {
			//cookie.setValue(null);
			cookie.setMaxAge(0);
			cookie.setPath( cookie.getPath() );
			response.addCookie(cookie);
		}
	}

	/**
	 * 设置浏览记录cookie
	 * 
	 * @param request
	 * @param response
	 * @param value
	 */
	public static void setViewProductCookie(HttpServletRequest request,
			HttpServletResponse response,String cookieName, String value,
			String domain) {

		String historyValue = getCookieValue(request, CookieUtil.VIEWPRODUCT);
		StringBuilder tmpValue = new StringBuilder();
		if (StringUtils.isNotEmpty(historyValue)) {
			String[] products = StringUtils.split(historyValue,
					DEFAULT_SEPERATOR);
			if (products != null && products.length < 40) {
				if( historyValue.indexOf( value ) > -1 ){
					tmpValue.append( value ).append( DEFAULT_SEPERATOR ).append( historyValue.replaceAll( value + "["+DEFAULT_SEPERATOR+"]" , "") );
				}else{
					tmpValue.append( value ).append( DEFAULT_SEPERATOR ).append( historyValue );
				}
			} else {
				tmpValue.append(value)
						.append(DEFAULT_SEPERATOR)
						.append(StringUtils
								.replace(historyValue,
										DEFAULT_SEPERATOR + value, "")
								.substring(
										0,
										historyValue
												.lastIndexOf(DEFAULT_SEPERATOR) > -1 ? historyValue
												.lastIndexOf(DEFAULT_SEPERATOR)
												: historyValue.length()));
				value = StringUtils.startsWithIgnoreCase(historyValue, value) ? historyValue
						: tmpValue.toString();
			}
		}else {
			tmpValue.append( value ).append( DEFAULT_SEPERATOR );
		}

		setCookieValue(request, response, cookieName, tmpValue.toString(), domain);
	}

	/**
	 * 设置登录信息cookie
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param userId
	 *            用户id
	 * @param isAutoLogin
	 *            是否自动登录
	 * @param domain
	 */
	public static void setUserInfoCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName, int userId,
			boolean isAutoLogin, String domain) {
		StringBuilder cookieValue = new StringBuilder();
		cookieValue.append(userId).append(DEFAULT_SEPERATOR)
				.append(isAutoLogin ? LOGIN_TRUE : LOGIN_FALSE);
		setCookieValue(request, response, cookieName, cookieValue.toString(),
				domain);
	}

	/**
	 * 设置语言cookie
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param language
	 * @param domain
	 */
	public static void setLanguageCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName, String language,
			String domain) {

		setCookieValue(request, response, cookieName, language, domain);
	}
	
	/**
	 * 设置分站cookie
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param subsiteId
	 * @param regionId
	 * @param subsiteName
	 * @param subsiteDomain
	 * @param cookieDomain
	 */
	public static void setSubsiteCookie( HttpServletRequest request,
			HttpServletResponse response, String cookieName,int subsiteId,int regionId, String subsiteName,
			String subsiteDomain , String cookieDomain ){
		StringBuilder cookieValue = new StringBuilder();
		cookieValue.append(subsiteId).append( DEFAULT_SEPERATOR ).append( regionId ).append( DEFAULT_SEPERATOR ).append(subsiteName)
				.append(DEFAULT_SEPERATOR).append(subsiteDomain);
		setCookieValue( request , response, cookieName, cookieValue.toString(), cookieDomain);

	}

	/**
	 * 设置购物车cookie
	 * @param request
	 * @param response
	 * @param CookieName
	 * @param subsiteId
	 * @param goodsId
	 * @param count
	 * @param isAppend
	 * @param domain
	 */
	public static void setCartCookie( HttpServletRequest request,
			HttpServletResponse response,String CookieName,int subsiteId,int goodsId,
			int count,boolean isAppend,String domain ){

		String cartCookie = CookieUtil.getCookieValue(request,
				CookieUtil.SHOPPINGCART);
		StringBuilder newGoods = new StringBuilder();
		newGoods.append( subsiteId ).append( DEFAULT_CONCATEMER ).append( goodsId ).append( DEFAULT_CONCATEMER );
		
		StringBuilder result = new StringBuilder();
		boolean hasGoods = false;
		if( StringUtils.isNotEmpty( cartCookie ) ){
			String[] cartItems = cartCookie.split("[|]");
			for( int i = 0 ; i < cartItems.length ; i++ ){
				 String cartItem = cartItems[i];
				 if( cartItem.indexOf( newGoods.toString() )<0 ){
					 result.append( cartItem ).append( DEFAULT_SEPERATOR );
				 }else{
					 String[] item = cartItem.split("[.]");
					 int cartCount = isAppend ? Integer.parseInt(item[2]) : (count+Integer.parseInt(item[2]));
					 cartItem = item[0] + DEFAULT_CONCATEMER + item[1] + DEFAULT_CONCATEMER + cartCount;
					 result.append( cartItem ).append( DEFAULT_SEPERATOR );
					 hasGoods = true;
				 }
			}
		}
		if( !hasGoods ){
			newGoods.append(count).append( DEFAULT_SEPERATOR );
			result.insert( 0 , newGoods.toString());
		}
		
		setCookieValue(request, response,CookieName,
				result.toString(),domain);
	}


	/**
	 * 删除购物车cookie中的商品
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param subsiteId
	 * @param goodsId
	 * @param domain
	 */
	public static void deleteCartItemForCookie( HttpServletRequest request,
			HttpServletResponse response, String cookieName,int subsiteId , int goodsId , String domain ){

		String cartCookie = CookieUtil.getCookieValue(request,
				CookieUtil.SHOPPINGCART);
		StringBuilder deleteItem = new StringBuilder();
		deleteItem.append( subsiteId ).append(DEFAULT_CONCATEMER).append( goodsId );
		StringBuilder result = new StringBuilder();
		if( StringUtils.isNotEmpty(cartCookie) ){
			String[] cartItems = cartCookie.split("[|]");
			 for( int i = 0 ; i < cartItems.length ; i++ ){
					if( cartItems[i].indexOf( deleteItem.toString() )<0 ){
						result.append( cartItems[i] ).append( DEFAULT_SEPERATOR );
				 }
			 }
		}
		setCookieValue(request, response,cookieName,
				result.toString(),domain);
	}
		
}
