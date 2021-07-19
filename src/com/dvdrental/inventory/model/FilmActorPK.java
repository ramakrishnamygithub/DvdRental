package com.dvdrental.inventory.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class FilmActorPK implements Serializable {
	public Film film;
    public Actor actor;

    public FilmActorPK() {}

    public FilmActorPK(Film film, Actor actor) {
        this.film = film;
        this.actor = actor;
    }


}
