package com.dvdrental.inventory.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dvdrental.inventory.common.DBFunctions;
import com.dvdrental.inventory.common.LoggerHelper;
import com.dvdrental.inventory.common.SpringBeanConstants;
import com.dvdrental.inventory.dao.CountryDao;
import com.dvdrental.inventory.dao.impl.CountryDaoImpl;
import com.dvdrental.inventory.model.Country;

@RemoteProxy(name="countryService")
public class CountryManager {

	@RemoteMethod
	public Country getCountryById(int countryId) throws ClassNotFoundException, SQLException {	
		Country country=new Country();
		addCountry();
		return country;
	}
	public void addCountry() {
		
	//WebContext webContext= WebContextFactory.get();
	ServletContext servletContext= WebContextFactory.get().getServletContext();
	DBFunctions dbFunctions=new DBFunctions();
	CountryDao countryDao=(CountryDao) dbFunctions.getDaoImplBean(SpringBeanConstants.CountryDaoImpl, servletContext);
		Country country= countryDao.loadCountry(3);
		List<Map<String, Object>> countriesList=countryDao.loadAllCountries();

	}
	
	@RemoteMethod
	public List<Country> getAllCountries() {
		//WebContext webContext= WebContextFactory.get();
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllCountries", "Entering the method");
		}
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		CountryDao countryDao=(CountryDao) dbFunctions.getDaoImplBean(SpringBeanConstants.CountryDaoImpl, servletContext);

		List<Country> countiesList=null;
		try {
		countiesList= countryDao.getAllCountries();
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "getAllCountries", "Exception occured...");
			}
			
		}finally {
			dbFunctions=null;
			countryDao=null;
			
		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllCountries", "Entering the method");
		}
			
	  return countiesList;
	}


}
