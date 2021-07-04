package com.dvdrental.inventory.web.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dvdrental.inventory.common.ApplicationProperties;
import com.dvdrental.inventory.common.DBFunctions;
import com.dvdrental.inventory.common.DesEncryption;
import com.dvdrental.inventory.common.IConstants;
import com.dvdrental.inventory.common.LoggerHelper;
import com.dvdrental.inventory.common.MessageConstants;
import com.dvdrental.inventory.common.ServletUtils;
import com.dvdrental.inventory.common.SingleSignOnBean;
import com.dvdrental.inventory.common.SpringBeanConstants;
import com.dvdrental.inventory.dao.CountryDao;
import com.dvdrental.inventory.dao.PreferencesDao;
import com.dvdrental.inventory.dao.UserDao;
import com.dvdrental.inventory.model.Country;
import com.dvdrental.inventory.model.Preferences;
import com.dvdrental.inventory.model.User;

/**
 * Servlet implementation class SSOLoginContoller
 */
public class SSOLoginContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		if(LoggerHelper.intialize()!=null) {
			LoggerHelper.logInfo(this.getClass().getName(), "processRequest", "Entering into method");
		}
		String strRedirectURL=null;
		ResourceBundle account=null;
		String MSURL_PATH=null;
		String CSURL_PATH=null;
		ApplicationProperties appProps=null;
		String strUserRequestURL=null;
		SingleSignOnBean ssoBean=null;
		String strUserLoginFailStatus=null;
		InetAddress ip=null;
		NetworkInterface network;
		String systemMacAddress=null;
		boolean isUsrAccessFrmAuth=false;
		boolean isUserAccessFrmAuthSystemChk=true;
		boolean systemIsAuthenticatedChk=true;
		User user=null;
		User validUser=null;
		Map prevLoginUserMapData=null;
		User prvlogInUserInfoObj=null;
		try {
			String strEmailId=request.getParameter("email");
			String password=request.getParameter("password");
			HttpSession session=request.getSession(false);
			ServletUtils servletUtils=new ServletUtils(request,response);
			ServletContext context=getServletContext();
			strUserRequestURL=request.getRequestURL().toString();
			DBFunctions objDbFunctions=new DBFunctions();
			UserDao userDao=(UserDao)objDbFunctions.getDaoImplBean(SpringBeanConstants.UserDaoImpl, context);
			PreferencesDao preferencesDao=(PreferencesDao)objDbFunctions.getDaoImplBean(SpringBeanConstants.PreferncesDaoImpl, context);
			String encryptionScheme=DesEncryption.DESEDE_ENCRYPTION_SCHEME;
			DesEncryption encryptor=new DesEncryption(encryptionScheme,IConstants.ENCRYPT_KEY);
			String strPwdEncrypted="";
			boolean isValidUserLogin=true;

			ip=InetAddress.getLocalHost();
			
			
			
			network=NetworkInterface.getByInetAddress(ip);

			/*
			 * byte[] mac=network.getHardwareAddress(); StringBuilder sb=new
			 * StringBuilder(); for(int i=0;i<mac.length;i++) {
			 * sb.append(String.format("%02X%s", mac[i],(i < mac.length-1)? "-" : "")); }
			 * systemMacAddress=sb.toString();
			 * if(!systemMacAddress.equalsIgnoreCase("00-16-76-12-AE-1E")) {
			 * systemIsAuthenticatedChk=false;
			 * strUserLoginFailStatus=MessageConstants.UN_AUTHERISED_SYSTEM; }
			 */
			//IF U WANT SYSTEM AUTHANTICATION BY CHECKING MAC ADDRESS YOU CAN REPLACE ABOVE MAC ADDRESS BY YOUR SYSTEM MAC
			//AND REMOVE THE ABOVE COMMENT LINES AND COMMENT THE FOLLOWING LINE
			systemIsAuthenticatedChk=true;
			user=userDao.loadByEmail(strEmailId);
			//check the user accessed from authorized address or not 
			if(user!=null) {
				/*if(user!=null && user.getUserAuthentication().equalsIgnoreCase("y"));
				ip=InetAddress.getByName(request.getRemoteAddr());
				if(ip.getHostAddress().equalsIgnoreCase(user.getUserIpAddress())) {
					isUserAccessFrmAuthSystemChk=true;
				}else {
					isUserAccessFrmAuthSystemChk=false;
					strUserLoginFailStatus=MessageConstants.UN_AUTHERISED_ACCESS_POINT;
				}*/
			}
			if(isUserAccessFrmAuthSystemChk && systemIsAuthenticatedChk) {
				Preferences preferences=preferencesDao.loadPreferenceData();
				if(preferences.getIsLoginFailCheck().equalsIgnoreCase("y")) {
					if(user!=null) {
						if(user.getLoginFailCount()>=preferences.getLoginFailCountLimit()) {
							user.setLoginFailCount(user.getLoginFailCount()+1);
							user.setLoginFailTime(new Date());
							userDao.updateUser(user);
							strUserLoginFailStatus=MessageConstants.LOGIN_FAILCOUNT_EXCEED;
							isValidUserLogin=false;

						}
					}
				}
				if((password!=null && ! password.equals(""))) {
					strPwdEncrypted=encryptor.encrypt(password);
				}
				account=ResourceBundle.getBundle("config");
				if(strUserRequestURL.indexOf(account.getString("ACCOUNT.EXTERNAL_IP"))<0) {
					if(MSURL_PATH==null) {
						MSURL_PATH=account.getString("Account.MSURL_LANPATH");
					}
					if(CSURL_PATH==null) {
						CSURL_PATH=account.getString("Account.CSURL_LANPATH");
					}

				}else {
					if(MSURL_PATH==null) {
						MSURL_PATH=account.getString("Account.MSURL_PATH");
					}
					if(CSURL_PATH==null) {
						CSURL_PATH=account.getString("Account.CSURL_PATH");
					}

				}
				if(isValidUserLogin) {
					List<User> userList=userDao.loadByEmailPwd(strEmailId,strPwdEncrypted);
					validUser=null;

					if(userList!=null && !userList.isEmpty()) {
						validUser=(User)userList.get(0);
						strUserLoginFailStatus=MessageConstants.STATUS_FAILED;
					}else {
						if(preferences.getIsLoginFailCheck().equalsIgnoreCase("y")) {
							if(user!=null) {
								user.setLoginFailCount(user.getLoginFailCount()+1);
								user.setLoginFailTime(new Date());
								userDao.updateUser(user);
								strUserLoginFailStatus="login Failed "+(preferences.getLoginFailCountLimit()-user.getLoginFailCount()+1);

							}
						}
					}
				}
				
				
				if(validUser!=null && validUser.getUserStatus().equalsIgnoreCase("A")) {
					ssoBean=new SingleSignOnBean();
					ssoBean.setUserEmail(strEmailId);
					ssoBean.setIsActive("yes");
					ssoBean.setjSessionId(session.getId());
					ssoBean.setUserEncryptedPwd(strPwdEncrypted);
					servletUtils.storeDataInContext(ssoBean);
					strRedirectURL=""+CSURL_PATH+"?jsid="+session.getId()+"";
				}
				if(strRedirectURL!=null) {
					

					 
					servletUtils.setCookieValue(servletUtils.SSO_SESSIONID,session.getId());
					servletUtils.setCookieValue(servletUtils.SSO_USERMAIL,strEmailId);
					servletUtils.setCookieValue(servletUtils.SSO_USERPWD,strPwdEncrypted);
					
					/*
					 * response.addHeader("Access-Control-Allow-Origin",
					 * "http://localhost:8080/DvdRental");
					 * 
					 * response.addHeader("Access-Control-Allow-Headers","Content-Type, *");
					 * response.addHeader("Access-Control-Allow-Credentials","true");
					 * response.addHeader("Access-Control-Allow-Methods","GET,POST");
					 */
					//response.sendRedirect(strRedirectURL);
					request.getRequestDispatcher("Login").forward(request, response);
					
				}else {
					if(strUserLoginFailStatus==null) {
						strUserLoginFailStatus=MessageConstants.LOGIN_FAILED;

					}
					
					
					request.setAttribute("logger",strUserLoginFailStatus);
					request.getRequestDispatcher("/web/jsp/login/Login.jsp").forward(request, response);
				}

			}
		}catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(this.getClass().getName()).error("Exception occurred in ssologincontroller", e);
			try {
				request.setAttribute("logger",strUserLoginFailStatus);
				request.getRequestDispatcher("/web/jsp/common/commonerror.jsp").forward(request, response);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}finally {
			
		}
		if(LoggerHelper.intialize()!=null) {
			LoggerHelper.logInfo(this.getClass().getName(), "processRequest", "Entering into method");
		}
	}

	public SSOLoginContoller() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
}
