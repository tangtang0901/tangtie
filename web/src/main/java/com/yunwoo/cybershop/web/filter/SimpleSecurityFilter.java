package com.yunwoo.cybershop.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 简单登陆过滤器
 */
public class SimpleSecurityFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		if(requestURI.indexOf("/assets") != 0 && !requestURI.equals("/loginPage") && !requestURI.equals("/login") && !requestURI.equals("/favicon.ico")){
			if(httpRequest.getSession().getAttribute("user") == null) {
				((HttpServletResponse)response).sendRedirect("/loginPage");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
