package com.dvdrental.inventory.dao;

import java.util.List;
import java.util.Map;

import com.dvdrental.inventory.model.Country;

public interface CountryDao {
	
	public Integer createCountry(Country country);
	public Integer updateCountry(Country country);
	public Integer deleteCountry(Integer country);
	public Country loadCountry(Integer countryId);
	public List<Country> getAllCountries();
	public List<Map<String, Object>> loadAllCountries();

}
