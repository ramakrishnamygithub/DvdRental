package com.dvdrental.inventory.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dvdrental.inventory.common.LoggerHelper;
import com.dvdrental.inventory.dao.CountryDao;
import com.dvdrental.inventory.model.Country;

public class CountryDaoImpl implements CountryDao {
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
	  public  List<Map<String, Object>> loadAllCountries() { 
		  String sqlQuery="select * from country";
    	  boolean flag= false;
    	  List<Map<String, Object>>  countriesList=null;
    	  countriesList=jdbcTemplate.queryForList(sqlQuery);
    	  
    	 return countriesList;
	  
	  }
	 
	@Override
	public Integer createCountry(Country country) {
		Integer id = (Integer) hibernateTemplate.save(country);
		return id;
	}

	@Override
	public Integer updateCountry(Country country) {
		hibernateTemplate.update(country);
		return country.getCountryId();
	}

	@Override
	public Integer deleteCountry(Integer countryId) {
		Country country = hibernateTemplate.get(Country.class, countryId);
		if (country != null) {
			hibernateTemplate.delete(country);
		}
		return country.getCountryId();
	}

	@Override
	public Country loadCountry(Integer countryId) {
		System.out.println(" came1");
		Country country = null;
		try {
			country = hibernateTemplate.get(Country.class, countryId);
			System.out.println(" country==" + country);
		} catch (Exception e) {
			System.out.println("e==" + e);
		}

		return country;
	}

	@Override
	public List<Country> getAllCountries() {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllCountries", "Entering the method");
		}
		 List<Country> countriesList=null;
		 try {
			 countriesList= hibernateTemplate.loadAll(Country.class);
		 }catch(Exception e) {
			 e.printStackTrace();
			 if(LoggerHelper.isDebugEnabled()) {
					LoggerHelper.logDebug(this.getClass().getName(), "getAllCountries", "Exception occured...");
				}
		 }finally {
			 
		 }
		 if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logInfo(this.getClass().getName(), "getAllCountries", "Leaving the method");
			}
		 
		return countriesList;
	}

}
