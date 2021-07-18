package com.dvdrental.inventory.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dvdrental.inventory.common.LoggerHelper;
import com.dvdrental.inventory.dao.LanguageDao;
import com.dvdrental.inventory.model.Language;
import com.dvdrental.inventory.model.Country;

public class LanguageDaoImpl implements LanguageDao {
	private JdbcTemplate jdbcTemplate;

	private HibernateTemplate hibernateTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	@Override
	public  List<Map<String, Object>> loadAllLanguages() {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadAllLanguages", "Entering the method");
		}
		List<Map<String, Object>>  countriesList=null;
		try {
			String sqlQuery="select * from language";
			boolean flag= false;

			countriesList=jdbcTemplate.queryForList(sqlQuery);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "loadAllLanguages", "Exception occured...");
			}
		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadAllLanguages", "Leaving the method");
		}

		return countriesList;

	}

	@Override
	public Integer createLanguage(Language language) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "createLanguage", "Entering the method");
		}
		Integer id=0;
		try {
			id = (Integer) hibernateTemplate.save(language);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "createLanguage", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "createLanguage", "Leaving the method");
		}
		return id;
	}

	@Override
	public Integer updateLanguage(Language language) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateLanguage", "Entering the method");
		}
		try {
			hibernateTemplate.update(language);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "updateLanguage", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateLanguage", "Leaving the method");
		}
		return language.getLanguageId();
	}

	@Override
	public Integer deleteLanguage(Integer languageId) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteLanguage", "Entering the method");
		}
		Language language=null;
		try {
			language = hibernateTemplate.get(Language.class, languageId);
			if (language != null) {
				hibernateTemplate.delete(language);
			}
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "deleteLanguage", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteLanguage", "Leaving the method");
		}
		return language.getLanguageId();
	}

	@Override
	public Language loadLanguage(Integer languageId) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadLanguage", "Entering the method");
		}
		Language language = null;
		try {
			language = hibernateTemplate.get(Language.class, languageId);
		} catch (Exception e) {
			e.printStackTrace();

			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "loadLanguage", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadLanguage", "Leaving the method");
		}
		return language;
	}

	@Override
	public List<Language> getAllLanguages() {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllLanguages", "Entering the method");
		}
		List<Language> citiesList=null;
		try {
			citiesList= hibernateTemplate.loadAll(Language.class);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "getAllLanguages", "Exception occured...");
			}
		}finally {

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllLanguages", "Leaving the method");
		}

		return citiesList;
	}

}
