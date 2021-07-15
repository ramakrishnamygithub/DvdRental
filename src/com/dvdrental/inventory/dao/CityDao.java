package com.dvdrental.inventory.dao;

import java.util.List;
import java.util.Map;

import com.dvdrental.inventory.model.City;

public interface CityDao {
	
	

	public Integer createCity(City city);
	public Integer updateCity(City city);
	public Integer deleteCity(Integer city);
	public City loadCity(Integer cityId);
	public List<City> getAllCities();
	public List<Map<String, Object>> loadAllCities();
}
