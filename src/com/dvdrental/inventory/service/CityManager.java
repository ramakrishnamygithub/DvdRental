package com.dvdrental.inventory.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

import com.dvdrental.inventory.common.DBFunctions;
import com.dvdrental.inventory.common.LoggerHelper;
import com.dvdrental.inventory.common.SpringBeanConstants;
import com.dvdrental.inventory.dao.CityDao;
import com.dvdrental.inventory.dao.CountryDao;
import com.dvdrental.inventory.model.City;
import com.dvdrental.inventory.model.Country;

@RemoteProxy(name="cityService")
public class CityManager {

	
	

	@RemoteMethod
	public List<City> getAllCities() {
		//WebContext webContext= WebContextFactory.get();
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllCities", "Entering the method");
		}
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		CityDao cityDao=(CityDao) dbFunctions.getDaoImplBean(SpringBeanConstants.CityDaoImpl, servletContext);

		List<City> citiesList=null;
		try {
			citiesList= cityDao.getAllCities();
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "getAllCities", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			cityDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllCities", "Entering the method");
		}

		return citiesList;
	}
	@RemoteMethod
	public City updateCity(HashMap cityMap) {
		//WebContext webContext= WebContextFactory.get();
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateCity", "Entering the method");
		}
		City city=null;
		int cityId=0;
		int updatedCityId=0;
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		CityDao cityDao=(CityDao) dbFunctions.getDaoImplBean(SpringBeanConstants.CityDaoImpl, servletContext);
		try {
			cityId=Integer.parseInt((String) cityMap.get("cityId"));
			String cityName=(String)cityMap.get("city");
			city=cityDao.loadCity(cityId);
			if(city!=null) {
				city.setCity(cityName);
				city.setLastUpdate(new Date());

			}
			updatedCityId=cityDao.updateCity(city);
			if(updatedCityId==0) {
				city=null;
			}

		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "updateCity", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			cityDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateCountry", "Entering the method");
		}

		return city;
	}
	@RemoteMethod
	public boolean deleteCity(HashMap cityMap) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteCity", "Entering the method");
		}
		City city=null;
		int cityId=0;
		int deletedCityId=0;
		boolean isdeleted=false;
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		CityDao cityDao=(CityDao) dbFunctions.getDaoImplBean(SpringBeanConstants.CityDaoImpl, servletContext);
		try {
			cityId=Integer.parseInt((String) cityMap.get("cityId"));


			deletedCityId=cityDao.deleteCity(cityId);
			if(deletedCityId!=0) {
				isdeleted=true;
			}else {
				isdeleted=false;
			}

		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "deleteCity", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			cityDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteCity", "Entering the method");
		}

		return isdeleted;
	}


}
