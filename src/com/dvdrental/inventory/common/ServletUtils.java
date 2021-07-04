package com.dvdrental.inventory.common;

import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;

public class ServletUtils {
	public static final String SSO_SESSIONID="SSOSESSIONID";
	public static final String SSO_USERMAIL="SSOUSEREMAIL";
	public static final String SSO_USERPWD="SSOUSERKEY";
	public HttpSession session=null;
	public HttpServletResponse response=null;
	public HttpServletRequest request=null;
	public String sessionid=null;
	public ServletContext context=null;
	
	public ServletUtils() {
		this.session=WebContextFactory.get().getSession();
		this.request=WebContextFactory.get().getHttpServletRequest();
		this.response=WebContextFactory.get().getHttpServletResponse();
		this.sessionid=session.getId();
	}
	
	public ServletUtils(HttpServletRequest req,HttpServletResponse res) {
		this.session=req.getSession();
		this.request=req;
		this.response=res;
		this.sessionid=session.getId();
		this.context=session.getServletContext();
	}
	
	public String getCookieValue(String name) {
		boolean found=false;
		String result=null;
		Cookie[] cookies=this.request.getCookies();
		if(cookies!=null) {
			int i=0;
			while(!found && (i <cookies.length)) {
				if(cookies[i].getName().equalsIgnoreCase(name)) {
					found=true;
					result=cookies[i].getValue();
				}
				i++;
			}
		}
		return (result);
	}
	
	public void setCookieValue(String cookieName,String cookieValue) {
		Cookie ssocookie=null;
		ssocookie =new Cookie(cookieName,cookieValue);
		ssocookie.setPath("/");
		ssocookie.setMaxAge(1800);
		response.addCookie(ssocookie);
		
	}
	
	public void removeAllCookies() {
		Cookie[] cookies=request.getCookies();
		
		for(int i=0;i<cookies.length;i++) {
			Cookie cookie=cookies[i];
			cookie.setValue(null);
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			response.addCookie(cookie);
			
		}
	}
	
	public synchronized void storeDataInContext(SingleSignOnBean ssoBean) {
		Hashtable sharedUserData=(Hashtable)context.getAttribute("shared_userdata");
		if(sharedUserData==null) {
			sharedUserData=new Hashtable();
		}else {
			sharedUserData.remove(ssoBean.getUserEmail());
		}
		sharedUserData.put(ssoBean.getUserEmail(),ssoBean);
		context.setAttribute("shared_userdata", sharedUserData);
		
		
	}
	public synchronized SingleSignOnBean getDataInContext(String sessionId) {
		SingleSignOnBean ssoBean=null;
		Hashtable sharedUserData=(Hashtable)context.getAttribute("shared_userdata");
		if(sharedUserData!=null) {
			ssoBean=(SingleSignOnBean)sharedUserData.get(sessionId);
		}
		return ssoBean;
		
	}
	
	public synchronized void removeDataFromContext(String strUserEmail) {
		Hashtable sharedUserData=(Hashtable)context.getAttribute("shared_userdata");
		if(sharedUserData!=null) {
			sharedUserData.remove(strUserEmail);
		}
	}
	

}
