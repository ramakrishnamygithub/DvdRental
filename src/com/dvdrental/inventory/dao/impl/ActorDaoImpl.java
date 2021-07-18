package com.dvdrental.inventory.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dvdrental.inventory.common.LoggerHelper;
import com.dvdrental.inventory.dao.ActorDao;
import com.dvdrental.inventory.model.Actor;
import com.dvdrental.inventory.model.Country;

public class ActorDaoImpl implements ActorDao {
	private JdbcTemplate jdbcTemplate;

	private HibernateTemplate hibernateTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	@Override
	public  List<Map<String, Object>> loadAllActors() {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadAllActors", "Entering the method");
		}
		List<Map<String, Object>>  countriesList=null;
		try {
			String sqlQuery="select * from actor";
			boolean flag= false;

			countriesList=jdbcTemplate.queryForList(sqlQuery);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "loadAllActors", "Exception occured...");
			}
		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadAllActors", "Leaving the method");
		}

		return countriesList;

	}

	@Override
	public Integer createActor(Actor actor) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "createActor", "Entering the method");
		}
		Integer id=0;
		try {
			id = (Integer) hibernateTemplate.save(actor);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "createActor", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "createActor", "Leaving the method");
		}
		return id;
	}

	@Override
	public Integer updateActor(Actor actor) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateActor", "Entering the method");
		}
		try {
			hibernateTemplate.update(actor);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "updateActor", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateActor", "Leaving the method");
		}
		return actor.getActorId();
	}

	@Override
	public Integer deleteActor(Integer actorId) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteActor", "Entering the method");
		}
		Actor actor=null;
		try {
			actor = hibernateTemplate.get(Actor.class, actorId);
			if (actor != null) {
				hibernateTemplate.delete(actor);
			}
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "deleteActor", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteActor", "Leaving the method");
		}
		return actor.getActorId();
	}

	@Override
	public Actor loadActor(Integer actorId) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadActor", "Entering the method");
		}
		Actor actor = null;
		try {
			actor = hibernateTemplate.get(Actor.class, actorId);
		} catch (Exception e) {
			e.printStackTrace();

			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "loadActor", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadActor", "Leaving the method");
		}
		return actor;
	}

	@Override
	public List<Actor> getAllActors() {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllActors", "Entering the method");
		}
		List<Actor> citiesList=null;
		try {
			citiesList= hibernateTemplate.loadAll(Actor.class);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "getAllActors", "Exception occured...");
			}
		}finally {

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllActors", "Leaving the method");
		}

		return citiesList;
	}

}
