package com.dvdrental.inventory.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dvdrental.inventory.common.LoggerHelper;
import com.dvdrental.inventory.dao.AddressDao;
import com.dvdrental.inventory.model.Address;
import com.dvdrental.inventory.model.City;

public class AddressDaoImpl implements AddressDao {
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
	public  List<Map<String, Object>> loadAllAddresses() {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadAllAddresses", "Entering the method");
		}
		List<Map<String, Object>>  addressesList=null;
		try {
			String sqlQuery="select * from address order by address";
			boolean flag= false;

			addressesList=jdbcTemplate.queryForList(sqlQuery);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "loadAllAddresses", "Exception occured...");
			}
		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadAllAddresses", "Leaving the method");
		}

		return addressesList;

	}

	@Override
	public Integer createAddress(Address address) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "createAddress", "Entering the method");
		}
		Integer id=0;
		try {
			id = (Integer) hibernateTemplate.save(address);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "createAddress", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "createAddress", "Leaving the method");
		}
		return id;
	}

	@Override
	public Integer updateAddress(Address address) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateAddress", "Entering the method");
		}
		try {
			hibernateTemplate.update(address);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "updateAddress", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateAddress", "Leaving the method");
		}
		return address.getAddressId();
	}

	@Override
	public Integer deleteAddress(Integer addressId) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteAddress", "Entering the method");
		}
		Address address=null;
		try {
			address = hibernateTemplate.get(Address.class, addressId);
			if (address != null) {
				hibernateTemplate.delete(address);
			}
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "deleteAddress", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteAddress", "Leaving the method");
		}
		return address.getAddressId();
	}

	@Override
	public Address loadAddress(Integer addressId) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadAddress", "Entering the method");
		}
		Address address = null;
		try {
			address = hibernateTemplate.get(Address.class, addressId);
		} catch (Exception e) {
			e.printStackTrace();

			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "loadAddress", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadAddress", "Leaving the method");
		}
		return address;
	}

	@Override
	public List<Address> getAllAddresses() {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllAddresses", "Entering the method");
		}
		List<Address> addressesList=null;
		try {
			addressesList= hibernateTemplate.loadAll(Address.class);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "getAllAddresses", "Exception occured...");
			}
		}finally {

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllAddresses", "Leaving the method");
		}

		return addressesList;
	}

}
