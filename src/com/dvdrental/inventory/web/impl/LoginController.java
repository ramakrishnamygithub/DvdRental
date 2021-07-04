package com.dvdrental.inventory.web.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dvdrental.inventory.common.ApplicationProperties;
import com.dvdrental.inventory.common.DBFunctions;
import com.dvdrental.inventory.common.DesEncryption;
import com.dvdrental.inventory.common.IConstants;
import com.dvdrental.inventory.common.LoggerHelper;
import com.dvdrental.inventory.common.MessageConstants;
import com.dvdrental.inventory.common.ServletUtils;
import com.dvdrental.inventory.common.SingleSignOnBean;
import com.dvdrental.inventory.common.SpringBeanConstants;
import com.dvdrental.inventory.dao.PreferencesDao;
import com.dvdrental.inventory.dao.UserDao;
import com.dvdrental.inventory.model.Preferences;
import com.dvdrental.inventory.model.User;


public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public LoginController() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("came to loginController");
		LoggerHelper.intialize();
		LoggerHelper.logInfo(this.getClass().getName(), "doPost", "entering into method");
		ResourceBundle account=null;
		String ExPORT_PATH=null;
		String loginInfo="";
		String strEmailId=null;
		String strPwd=null;


		ServletUtils servletUtils=new ServletUtils(request,response);
		String strJsessionIdFromCookie=servletUtils.getCookieValue(servletUtils.SSO_SESSIONID);
		System.out.println("strJsessionIdFromCookie=="+strJsessionIdFromCookie);
		String strPwdEncrypted="";

		if(strJsessionIdFromCookie !=null) {
			strEmailId=servletUtils.getCookieValue(servletUtils.SSO_USERMAIL);
			strPwdEncrypted=servletUtils.getCookieValue(servletUtils.SSO_USERPWD);
		}else {
			strEmailId=request.getParameter("email");
			strPwd=request.getParameter("password");
		}
		System.out.println("strEmailId=="+strEmailId);
		System.out.println("strPwd=="+strPwd);
		HttpSession session=request.getSession(false);
		ServletContext context=getServletContext();
		String strIpAddress=request.getRemoteAddr();
		DBFunctions objDbFunctions=new DBFunctions();
		ServletContext servletContext=getServletContext();
		UserDao userDao=(UserDao)objDbFunctions.getDaoImplBean(SpringBeanConstants.UserDaoImpl, context);
		PreferencesDao preferencesDao=(PreferencesDao)objDbFunctions.getDaoImplBean(SpringBeanConstants.PreferncesDaoImpl, context);
		User userBean=null;
		List processPermList=null;
		List masterPermList=null;
		HashMap reportPermList=null;
		List<HashMap> listDetails=new ArrayList<HashMap>();
		HashMap usermenuprefList=null;
		//MenuPrefernces menuPreferencesbean=null;
		List userList=null;
		String CS_PATH=null;
		String strUserRequestUrl=null;
		ApplicationProperties appProps=null;
		Preferences preferences=null;

		try {
			userBean=userDao.loadByEmail(strEmailId);

			Date date=new Date();
			String strHidden=request.getParameter("hidden");
			strUserRequestUrl=request.getRequestURL().toString();
			if(session !=null) {
				if((strHidden !=null) && strHidden.equalsIgnoreCase("logOff")) {

					String strSessionId=session.getId();
					request.setAttribute("logoff", MessageConstants.LOGOFF_SUCCESS);

					strUserRequestUrl=request.getRequestURL().toString();
					appProps=(ApplicationProperties)context.getAttribute("apppropps");
					account=ResourceBundle.getBundle("config");
					if(strUserRequestUrl.indexOf(account.getString("Account.EXTERNAL_IP"))<0) {
						if(CS_PATH==null) {
							CS_PATH=account.getString("Account.CSAPP_LANPATH");
						}
					}else {
						if(CS_PATH==null) {
							CS_PATH=account.getString("Account.CSAPP_PATH");
						}
					}
					try {
						if(servletUtils!=null) {
							servletUtils.removeAllCookies();
							if(strEmailId!=null) {
								servletUtils.removeDataFromContext(strEmailId);
							}
						}
						session.invalidate();
					}catch(Exception e) {
						e.printStackTrace();
					}
					request.getRequestDispatcher("").forward(request, response);
				}else {
					String encryptionScheme=DesEncryption.DESEDE_ENCRYPTION_SCHEME;
					DesEncryption encryptor=new DesEncryption(encryptionScheme,IConstants.ENCRYPT_KEY);
					System.out.println("133 strEmailId=="+strEmailId+"  strPwdEncrypted=="+strPwdEncrypted+" strPwd=="+strPwd);
					if(strEmailId!=null && (strPwdEncrypted!=null || strPwd!=null)) {
						if(((strPwd!=null) && !strPwd.equals("")) && ((strPwdEncrypted==null || strPwdEncrypted.equals("")))) {
							strPwdEncrypted=encryptor.encrypt(strPwd);
							System.out.println("136 :: strPwdEncrypted=="+strPwdEncrypted);
						}
						userList=userDao.loadByEmailPwd(strEmailId, strPwdEncrypted);
						System.out.println("136");
						System.out.println("strPwdEncrypted==="+strPwdEncrypted);
						System.out.println("userList=="+userList);
						if(!userList.isEmpty()) {
							String strStatus=userBean.getUserStatus();

							if(strStatus.equals("A")) {
								System.out.println("141");
								SingleSignOnBean ssoBean=new SingleSignOnBean();
								ssoBean.setIsActive("yes");
								ssoBean.setjSessionId(session.getId());
								ssoBean.setUserEmail(strEmailId);
								ssoBean.setUserEncryptedPwd(strPwdEncrypted);
								servletUtils.storeDataInContext(ssoBean);

								User currentUser=(User)userList.get(0);
								String strJSessionID=session.getId();


								System.out.println("153");
								//here setting attributes in context and session objects
								session.setAttribute("user", currentUser);
								account=ResourceBundle.getBundle("config");
								int mb=1024*1024;
								Runtime runtime=Runtime.getRuntime();
								System.out.println("#### Heap utilization statistics[MB] ####");

								System.out.println("Used Memory :"+(runtime.totalMemory()-runtime.freeMemory()/mb));
								System.out.println("Total Memory:"+runtime.totalMemory()/mb);
								System.out.println("Max Memory:"+runtime.maxMemory()/mb);
								preferences=preferencesDao.loadPreferenceData();
								int pwdexpremainingday=1;

								/*
								 * if(preferences.getIsPwdExpCheck().equals("y")) { pwdexpremainingday=-1;
								 * pwdexpremainingday=userDao.getRemaingingPwdExpiryDays(currentUser.getUserId()
								 * ,prefbean.getPwdexpdays());
								 * 
								 * }
								 */
								System.out.println("174");
								if(currentUser.getIsFirstLogin().equals("y")) {
									System.out.println("181");
									session.setAttribute("msgforlogin", "You are accessing DvdRental for the first time");
									request.getRequestDispatcher("/web/jsp/common/FirstLogInPage.jsp").forward(request, response);
								}else if (pwdexpremainingday<=0) {
									System.out.println("185");
									session.setAttribute("msgforlogin", "your password has been expired");  
									request.getRequestDispatcher("/web/jsp/setup/FirstLoginPage.jsp").forward(request, response);
								}else {
									System.out.println("189");
									request.getRequestDispatcher("/web/jsp/common/dvdrentalindex.jsp").forward(request, response);
								}
							}

						}else {
							request.setAttribute("logerr", MessageConstants.STATUS_FAILED);
							request.getRequestDispatcher("/web/jsp/login/LoginPage.jsp").forward(request, response);

						}

					}else {
						request.setAttribute("logger", MessageConstants.LOGIN_FAILED);
						request.getRequestDispatcher("/web/jsp/login/LoginPage.jsp").forward(request, response);

					}

				}

			}else {
				request.setAttribute("logger", MessageConstants.LOGIN_FAILED);
				request.getRequestDispatcher("/web/jsp/login/LoginPage.jsp").forward(request, response);
			}


		}catch(Exception e) {
			e.printStackTrace();

		}


	}
}
