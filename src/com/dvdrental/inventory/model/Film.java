package com.dvdrental.inventory.model;

import java.io.Serializable;
import java.util.Arrays;
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
@Table(name="film")
public class Film implements Serializable {
	@Id
	@Column(name="film_id")
	public int filmId;
	
	@Column(name="title")
	public String title;
	
	@Column(name="description")
	public String description;
		
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="language_id",unique = true)
	public Language language;
	
	@Column(name="rental_duration")
	public int rentalDuration;
	
	@Column(name="rental_rate")
	public double rentalRate;
	
	@Column(name="length")
	public int length;
	
	@Column(name="replacement_cost")
	public double replaceMentCost;
	
	@Column(name="rating")
	public String rating;
	
	@Column(name="last_update")
	@Temporal(TemporalType.TIMESTAMP) 
	public Date lastUpdate;
 
	@Column(name="special_features")
	public String[] specialFeatures;
	
	@Column(name="fulltext")
	public String fullText;

	public Film() {
		super();
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplaceMentCost() {
		return replaceMentCost;
	}

	public void setReplaceMentCost(double replaceMentCost) {
		this.replaceMentCost = replaceMentCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String[] getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String[] specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public String getFullText() {
		return fullText;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", description=" + description + ", language=" + language
				+ ", rentalDuration=" + rentalDuration + ", rentalRate=" + rentalRate + ", length=" + length
				+ ", replaceMentCost=" + replaceMentCost + ", rating=" + rating + ", lastUpdate=" + lastUpdate
				+ ", specialFeatures=" + Arrays.toString(specialFeatures) + ", fullText=" + fullText + "]";
	}
	
	
	
	 
}
