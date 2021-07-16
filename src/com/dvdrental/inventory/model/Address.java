package com.dvdrental.inventory.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
@Entity
@Table(name="address")
public class Address {
	@Id
	@Column(name="address_id")
	public int addressId;
	
	@Column(name="address")
	public String address;
	
	@Column(name="address2")
	public String address2;
	
	@Column(name="district")
	public String district;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="city_id",unique = true)
	public City city;
	
	@Column(name="postal_code")
	public String postalCode;
	
	@Column(name="phone")
	public String phone;
	
	@Column(name="last_update")
	public Date lastUpdate;

	public Address() {
		super();
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}
	

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", address=" + address + ", address2=" + address2 + ", district="
				+ district + ", city=" + city + ", postalCode=" + postalCode + ", phone=" + phone + ", lastUpdate="
				+ lastUpdate + "]";
	}

	 
}
