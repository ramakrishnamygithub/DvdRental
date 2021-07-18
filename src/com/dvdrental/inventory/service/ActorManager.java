package com.dvdrental.inventory.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

import com.dvdrental.inventory.common.DBFunctions;
import com.dvdrental.inventory.common.LoggerHelper;
import com.dvdrental.inventory.common.SpringBeanConstants;
import com.dvdrental.inventory.dao.ActorDao;
import com.dvdrental.inventory.dao.CountryDao;
import com.dvdrental.inventory.model.Actor;
import com.dvdrental.inventory.model.Country;

@RemoteProxy(name="actorService")
public class ActorManager {

	
	

	@RemoteMethod
	public List<Actor> getAllActors() {
		//WebContext webContext= WebContextFactory.get();
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllActors", "Entering the method");
		}
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		ActorDao actorDao=(ActorDao) dbFunctions.getDaoImplBean(SpringBeanConstants.ActorDaoImpl, servletContext);

		List<Actor> actorsList=null;
		try {
			actorsList= actorDao.getAllActors();
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "getAllActors", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			actorDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllActors", "Entering the method");
		}

		return actorsList;
	}
	@RemoteMethod
	public Actor updateActor(HashMap actorMap) {
		//WebContext webContext= WebContextFactory.get();
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateActor", "Entering the method");
		}
		Actor actor=null;
		int actorId=0;
		int updatedActorId=0;
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		ActorDao actorDao=(ActorDao) dbFunctions.getDaoImplBean(SpringBeanConstants.ActorDaoImpl, servletContext);
		try {
			actorId=Integer.parseInt((String) actorMap.get("actorId"));
			String actorName=(String)actorMap.get("actor");
			actor=actorDao.loadActor(actorId);
			if(actor!=null) {
				actor.setFirstName(actorName);
				actor.setLastUpdate(new Date());

			}
			updatedActorId=actorDao.updateActor(actor);
			if(updatedActorId==0) {
				actor=null;
			}

		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "updateActor", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			actorDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateCountry", "Entering the method");
		}

		return actor;
	}
	@RemoteMethod
	public boolean deleteActor(HashMap actorMap) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteActor", "Entering the method");
		}
		Actor actor=null;
		int actorId=0;
		int deletedActorId=0;
		boolean isdeleted=false;
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		ActorDao actorDao=(ActorDao) dbFunctions.getDaoImplBean(SpringBeanConstants.ActorDaoImpl, servletContext);
		try {
			actorId=Integer.parseInt((String) actorMap.get("actorId"));


			deletedActorId=actorDao.deleteActor(actorId);
			if(deletedActorId!=0) {
				isdeleted=true;
			}else {
				isdeleted=false;
			}

		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "deleteActor", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			actorDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteActor", "Entering the method");
		}

		return isdeleted;
	}


}
