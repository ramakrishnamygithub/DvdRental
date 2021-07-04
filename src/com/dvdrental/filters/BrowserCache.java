package com.dvdrental.filters;

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
 * Servlet Filter implementation class BrowserCache
 */
public class BrowserCache implements Filter {

	private FilterConfig filterConfig;
	private FilterConfig getFilterConfig() {
		return this.filterConfig;
	}

	public BrowserCache() {
	}


	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String cacheControlValue=getFilterConfig().getInitParameter("Cache-control");
		String pragmaValue=getFilterConfig().getInitParameter("Pragma");
		String expiresValue=getFilterConfig().getInitParameter("Expires");
		
		if(cacheControlValue!=null) {
			((HttpServletResponse) response).setHeader("Cache-control",cacheControlValue);
		}
		if(pragmaValue!=null) {
			((HttpServletResponse) response).setHeader("Pragma", pragmaValue);
		}
		if(expiresValue!=null) {
			((HttpServletResponse) response).setDateHeader("Expires",Integer.parseInt(expiresValue) );
		}
		try {
			chain.doFilter(((HttpServletRequest) request), ((HttpServletResponse) response));
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig= fConfig;


	}

}
