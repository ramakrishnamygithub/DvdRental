package com.dvdrental.inventory.dao;

import java.util.List;
import java.util.Map;

import com.dvdrental.inventory.model.Address;

public interface AddressDao {
	public Integer createAddress(Address address);
	public Integer updateAddress(Address address);
	public Integer deleteAddress(Integer address);
	public Address loadAddress(Integer AddressId);
	public List<Address> getAllAddresses();
	public List<Map<String, Object>> loadAllAddresses();

}
