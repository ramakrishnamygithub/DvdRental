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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.directwebremoting.annotations.DataTransferObject;
@DataTransferObject
@Entity
@Table(name="inventory")
public class Inventory implements Serializable {
	@Id
	@Column(name="inventory_id")
	public int inventoryId;
			
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="film_id",unique = true)
	public Film film;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="store_id",unique = true)
	public Store store;
	
	@Column(name="last_update")
	@Temporal(TemporalType.TIMESTAMP) 
	public Date lastUpdate;

	public Inventory() {
		super();
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Inventory [inventoryId=" + inventoryId + ", film=" + film + ", store=" + store + ", lastUpdate="
				+ lastUpdate + "]";
	}
	
	
}
