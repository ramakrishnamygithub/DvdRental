package com.dvdrental.inventory.dao;

import java.util.List;
import java.util.Map;

import com.dvdrental.inventory.model.Actor;

public interface ActorDao {
	public Integer createActor(Actor actor);
	public Integer updateActor(Actor actor);
	public Integer deleteActor(Integer actor);
	public Actor loadActor(Integer actorId);
	public List<Actor> getAllActors();
	public List<Map<String, Object>> loadAllActors();

}
