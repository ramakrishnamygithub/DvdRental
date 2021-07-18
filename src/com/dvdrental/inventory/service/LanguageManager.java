package com.dvdrental.inventory.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

import com.dvdrental.inventory.common.DBFunctions;
import com.dvdrental.inventory.common.LoggerHelper;
import com.dvdrental.inventory.common.SpringBeanConstants;
import com.dvdrental.inventory.dao.LanguageDao;
import com.dvdrental.inventory.model.Language;

@RemoteProxy(name="languageService")
public class LanguageManager {

	
	

	@RemoteMethod
	public List<Language> getAllLanguages() {
		//WebContext webContext= WebContextFactory.get();
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllLanguages", "Entering the method");
		}
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		LanguageDao languageDao=(LanguageDao) dbFunctions.getDaoImplBean(SpringBeanConstants.LanguageDaoImpl, servletContext);

		List<Language> languagesList=null;
		try {
			languagesList= languageDao.getAllLanguages();
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "getAllLanguages", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			languageDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllLanguages", "Entering the method");
		}

		return languagesList;
	}
	@RemoteMethod
	public Language updateLanguage(HashMap languageMap) {
		//WebContext webContext= WebContextFactory.get();
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateLanguage", "Entering the method");
		}
		Language language=null;
		int languageId=0;
		int updatedLanguageId=0;
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		LanguageDao languageDao=(LanguageDao) dbFunctions.getDaoImplBean(SpringBeanConstants.LanguageDaoImpl, servletContext);
		try {
			languageId=Integer.parseInt((String) languageMap.get("languageId"));
			String languageName=(String)languageMap.get("language");
			language=languageDao.loadLanguage(languageId);
			if(language!=null) {
				language.setName(languageName);
				language.setLastUpdate(new Date());

			}
			updatedLanguageId=languageDao.updateLanguage(language);
			if(updatedLanguageId==0) {
				language=null;
			}

		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "updateLanguage", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			languageDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateCountry", "Entering the method");
		}

		return language;
	}
	@RemoteMethod
	public boolean deleteLanguage(HashMap languageMap) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteLanguage", "Entering the method");
		}
		Language language=null;
		int languageId=0;
		int deletedLanguageId=0;
		boolean isdeleted=false;
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		LanguageDao languageDao=(LanguageDao) dbFunctions.getDaoImplBean(SpringBeanConstants.LanguageDaoImpl, servletContext);
		try {
			languageId=Integer.parseInt((String) languageMap.get("languageId"));


			deletedLanguageId=languageDao.deleteLanguage(languageId);
			if(deletedLanguageId!=0) {
				isdeleted=true;
			}else {
				isdeleted=false;
			}

		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "deleteLanguage", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			languageDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteLanguage", "Entering the method");
		}

		return isdeleted;
	}


}
