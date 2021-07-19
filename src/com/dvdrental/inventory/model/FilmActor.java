package com.dvdrental.inventory.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
@Entity
@Table(name="film_actor")
public class FilmActor implements Serializable {
	 @EmbeddedId
	public FilmActorPK filmActorPK;
	 
	 @Column(name="last_update")
		@Temporal(TemporalType.TIMESTAMP) 
		public Date lastUpdate;



	public FilmActor() {
		super();
	}



	public FilmActorPK getFilmActorPK() {
		return filmActorPK;
	}



	public void setFilmActorPK(FilmActorPK filmActorPK) {
		this.filmActorPK = filmActorPK;
	}



	public Date getLastUpdate() {
		return lastUpdate;
	}



	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filmActorPK == null) ? 0 : filmActorPK.hashCode());
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmActor other = (FilmActor) obj;
		if (filmActorPK == null) {
			if (other.filmActorPK != null)
				return false;
		} else if (!filmActorPK.equals(other.filmActorPK))
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "FilmActor [filmActorPK=" + filmActorPK + ", lastUpdate=" + lastUpdate + "]";
	}

    

}
