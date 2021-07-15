package com.dvdrental.inventory.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dvdrental.inventory.common.LoggerHelper;
import com.dvdrental.inventory.dao.CityDao;
import com.dvdrental.inventory.model.City;
import com.dvdrental.inventory.model.Country;

public class CityDaoImpl implements CityDao {
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
	public  List<Map<String, Object>> loadAllCities() {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadAllCities", "Entering the method");
		}
		List<Map<String, Object>>  countriesList=null;
		try {
			String sqlQuery="select * from country";
			boolean flag= false;

			countriesList=jdbcTemplate.queryForList(sqlQuery);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "loadAllCities", "Exception occured...");
			}
		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadAllCities", "Leaving the method");
		}

		return countriesList;

	}

	@Override
	public Integer createCity(City city) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "createCity", "Entering the method");
		}
		Integer id=0;
		try {
			id = (Integer) hibernateTemplate.save(city);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "createCity", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "createCity", "Leaving the method");
		}
		return id;
	}

	@Override
	public Integer updateCity(City city) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateCity", "Entering the method");
		}
		try {
			hibernateTemplate.update(city);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "updateCity", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateCity", "Leaving the method");
		}
		return city.getCityId();
	}

	@Override
	public Integer deleteCity(Integer cityId) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteCity", "Entering the method");
		}
		City city=null;
		try {
			city = hibernateTemplate.get(City.class, cityId);
			if (city != null) {
				hibernateTemplate.delete(city);
			}
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "deleteCity", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteCity", "Leaving the method");
		}
		return city.getCityId();
	}

	@Override
	public City loadCity(Integer cityId) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadCity", "Entering the method");
		}
		City city = null;
		try {
			city = hibernateTemplate.get(City.class, cityId);
		} catch (Exception e) {
			e.printStackTrace();

			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "loadCity", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadCity", "Leaving the method");
		}
		return city;
	}

	@Override
	public List<City> getAllCities() {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllCities", "Entering the method");
		}
		List<City> citiesList=null;
		try {
			citiesList= hibernateTemplate.loadAll(City.class);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "getAllCities", "Exception occured...");
			}
		}finally {

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllCities", "Leaving the method");
		}

		return citiesList;
	}

}
