package com.dvdrental.inventory.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dvdrental.inventory.common.LoggerHelper;
import com.dvdrental.inventory.dao.UserDao;
import com.dvdrental.inventory.model.User;

public class UserDaoImpl implements UserDao {
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
	  public  List<Map<String, Object>> loadAllUsers() { 
		  String sqlQuery="select * from country";
    	  boolean flag= false;
    	  List<Map<String, Object>>  usersList=null;
    	  usersList=jdbcTemplate.queryForList(sqlQuery);
    	  
    	 return usersList;
	  
	  }
	 
	@Override
	public Integer createUser(User user) {
		Integer id = (Integer) hibernateTemplate.save(user);
		return id;
	}

	@Override
	public Integer updateUser(User user) {
		hibernateTemplate.update(user);
		return user.getUserId();
	}

	@Override
	public Integer deleteUser(Integer userId) {
		User user = hibernateTemplate.get(User.class, userId);
		if (user != null) {
			hibernateTemplate.delete(user);
		}
		return user.getUserId();
	}

	@Override
	public User loadUser(Integer userId) {
		User user = null;
		try {
			user = hibernateTemplate.get(User.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public List<User> getAllUsers() {

		return hibernateTemplate.loadAll(User.class);
	}

	@Override
	public User loadByEmail(String email) {
		if(LoggerHelper.intialize()!=null) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadByEmail", "Entering into method");
		}
		User user=null;
		try {
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userEmail", email));
		List<User> userList = criteria.list();
		if(userList!=null && userList.size()>0) {
			user=(User)userList.get(0);
			
		}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		LoggerHelper.logInfo(this.getClass().getName(), "loadByEmail", "Leaving from method");
		return user;
	}

	@Override
	public List<User> loadByEmailPwd(String email, String password) {
		if(LoggerHelper.intialize()!=null) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadByEmailPwd", "Entering into method");
		}
		User user=null;
		List<User> userList=null;
		try {
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(User.class);
		Criterion mailCriterion = Restrictions.eq("userEmail", email);
		Criterion passwordCriterion = Restrictions.eq("userPwd",password);
		LogicalExpression andExp = Restrictions.and(mailCriterion, passwordCriterion);
		criteria.add( andExp );
		 userList = criteria.list();
		}catch(Exception e) {
			e.printStackTrace();
		}
		LoggerHelper.logInfo(this.getClass().getName(), "loadByEmailPwd", "Leaving from method");

		return userList;
	}

}
