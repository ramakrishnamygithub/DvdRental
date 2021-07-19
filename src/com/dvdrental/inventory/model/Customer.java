package com.dvdrental.inventory.model;

import java.io.Serializable;
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
@Table(name="customer")
public class Customer implements Serializable{
	@Id
	@Column(name="customer_id")
	public int customerId;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="stored_id",unique = true)
	public Store store;
	
	@Column(name="first_name")
	public String firstName;
	
	@Column(name="last_name")
	public String lastName;
	
	@Column(name="email")
	public String email;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="address_id",unique = true)
	public Address address;
	
	@Column(name="activebool")
	public boolean activebool;
	

	@Column(name="create_date")
	public Date createDate;
	
	@Column(name="last_update")
	public Date lastUpdate;
	
	@Column(name="active")
	public int active;

	public Customer() {
		super();
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isActivebool() {
		return activebool;
	}

	public void setActivebool(boolean activebool) {
		this.activebool = activebool;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", store=" + store + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", address=" + address + ", activebool=" + activebool
				+ ", createDate=" + createDate + ", lastUpdate=" + lastUpdate + ", active=" + active + "]";
	}
	
	
	
	

	
	 
}
