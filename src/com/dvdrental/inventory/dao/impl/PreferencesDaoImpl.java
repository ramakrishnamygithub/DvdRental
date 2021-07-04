package com.dvdrental.inventory.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dvdrental.inventory.common.LoggerHelper;
import com.dvdrental.inventory.dao.PreferencesDao;
import com.dvdrental.inventory.model.Preferences;

public class PreferencesDaoImpl implements PreferencesDao{
	
	
	
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
	public Preferences loadPreferenceData() {
		if(LoggerHelper.intialize()!=null) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadPreferenceData", "Entering into method");
		}
		Preferences preferences=null;
		// TODO Auto-generated method stub
		try {
		
		List<Preferences> preferencesList= hibernateTemplate.loadAll(Preferences.class);
		if(preferencesList!=null && preferencesList.size()>0) {
			preferences=(Preferences)preferencesList.get(0);
			
		}
		}catch(Exception e) {
			e.printStackTrace();
			LoggerHelper.logError(this.getClass().getName(), "loadPreferenceData", "Error");
		}finally {
			
		}
		LoggerHelper.logInfo(this.getClass().getName(), "loadPreferenceData", preferences.toString());
		LoggerHelper.logInfo(this.getClass().getName(), "loadPreferenceData", "Leaving from method");
		return preferences;
	}
	
	
	

}
