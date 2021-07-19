package com.dvdrental.inventory.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class FilmCategoryPK implements Serializable {
	public Film film;
    public Category category;

    public FilmCategoryPK() {}

    public FilmCategoryPK(Film film, Category category) {
        this.film = film;
        this.category = category;
    }


}
