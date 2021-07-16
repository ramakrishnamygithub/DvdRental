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
import com.dvdrental.inventory.dao.AddressDao;
import com.dvdrental.inventory.dao.CityDao;
import com.dvdrental.inventory.model.Address;
import com.dvdrental.inventory.model.City;

@RemoteProxy(name="addressService")

public class AddressManager {

	@RemoteMethod
	public List<Address> getAllAddresses() {
		//WebContext webContext= WebContextFactory.get();
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllAddresses", "Entering the method");
		}
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		AddressDao addressDao=(AddressDao) dbFunctions.getDaoImplBean(SpringBeanConstants.AddressDaoImpl, servletContext);

		List<Address> addressesList=null;
		try {
			addressesList= addressDao.getAllAddresses();
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "getAllAddresses", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			addressDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllAddresses", "Entering the method");
		}

		return addressesList;
	}
	@RemoteMethod
	public Address updateAddress(HashMap addressMap) {
		//WebContext webContext= WebContextFactory.get();
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateAddress", "Entering the method");
		}
		Address address=null;
		int addressId=0;
		int updatedAddressId=0;
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		AddressDao addressDao=(AddressDao) dbFunctions.getDaoImplBean(SpringBeanConstants.AddressDaoImpl, servletContext);
		try {
			addressId=Integer.parseInt((String) addressMap.get("addressId"));
			String address1=(String)addressMap.get("address");
			address=addressDao.loadAddress(addressId);
			if(address!=null) {
				address.setAddress(address1);
				address.setLastUpdate(new Date());

			}
			updatedAddressId=addressDao.updateAddress(address);
			if(updatedAddressId==0) {
				address=null;
			}

		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "updateAddress", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			addressDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateAddress", "Entering the method");
		}

		return address;
	}
	@RemoteMethod
	public boolean deleteAddress(HashMap cityMap) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteAddress", "Entering the method");
		}
		Address address=null;
		int addressId=0;
		int deletedAddressId=0;
		boolean isdeleted=false;
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		AddressDao addressDao=(AddressDao) dbFunctions.getDaoImplBean(SpringBeanConstants.AddressDaoImpl, servletContext);
		try {
			addressId=Integer.parseInt((String) cityMap.get("addressId"));


			deletedAddressId=addressDao.deleteAddress(addressId);
			if(deletedAddressId!=0) {
				isdeleted=true;
			}else {
				isdeleted=false;
			}

		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "deleteAddress", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			addressDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteAddress", "Entering the method");
		}

		return isdeleted;
	}


}
