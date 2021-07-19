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
@Table(name="film_category")
public class FilmCategory implements Serializable{
	 @EmbeddedId
		public FilmCategoryPK filmCategoryPK;
		 
		 @Column(name="last_update")
			@Temporal(TemporalType.TIMESTAMP) 
			public Date lastUpdate;

		public FilmCategory() {
			super();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((filmCategoryPK == null) ? 0 : filmCategoryPK.hashCode());
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
			FilmCategory other = (FilmCategory) obj;
			if (filmCategoryPK == null) {
				if (other.filmCategoryPK != null)
					return false;
			} else if (!filmCategoryPK.equals(other.filmCategoryPK))
				return false;
			if (lastUpdate == null) {
				if (other.lastUpdate != null)
					return false;
			} else if (!lastUpdate.equals(other.lastUpdate))
				return false;
			return true;
		}

		public FilmCategoryPK getFilmCategoryPK() {
			return filmCategoryPK;
		}

		public void setFilmCategoryPK(FilmCategoryPK filmCategoryPK) {
			this.filmCategoryPK = filmCategoryPK;
		}

		public Date getLastUpdate() {
			return lastUpdate;
		}

		public void setLastUpdate(Date lastUpdate) {
			this.lastUpdate = lastUpdate;
		}

		@Override
		public String toString() {
			return "FilmCategory [filmCategoryPK=" + filmCategoryPK + ", lastUpdate=" + lastUpdate + "]";
		}
		 
		 
}
