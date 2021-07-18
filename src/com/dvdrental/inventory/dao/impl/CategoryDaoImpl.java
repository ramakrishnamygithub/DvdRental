package com.dvdrental.inventory.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dvdrental.inventory.common.LoggerHelper;
import com.dvdrental.inventory.dao.CategoryDao;
import com.dvdrental.inventory.model.Category;
import com.dvdrental.inventory.model.Country;

public class CategoryDaoImpl implements CategoryDao {
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
	public  List<Map<String, Object>> loadAllCategories() {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadAllCategories", "Entering the method");
		}
		List<Map<String, Object>>  countriesList=null;
		try {
			String sqlQuery="select * from category";
			boolean flag= false;

			countriesList=jdbcTemplate.queryForList(sqlQuery);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "loadAllCategories", "Exception occured...");
			}
		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadAllCategories", "Leaving the method");
		}

		return countriesList;

	}

	@Override
	public Integer createCategory(Category category) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "createCategory", "Entering the method");
		}
		Integer id=0;
		try {
			id = (Integer) hibernateTemplate.save(category);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "createCategory", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "createCategory", "Leaving the method");
		}
		return id;
	}

	@Override
	public Integer updateCategory(Category category) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateCategory", "Entering the method");
		}
		try {
			hibernateTemplate.update(category);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "updateCategory", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateCategory", "Leaving the method");
		}
		return category.getCategoryId();
	}

	@Override
	public Integer deleteCategory(Integer categoryId) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteCategory", "Entering the method");
		}
		Category category=null;
		try {
			category = hibernateTemplate.get(Category.class, categoryId);
			if (category != null) {
				hibernateTemplate.delete(category);
			}
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "deleteCategory", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteCategory", "Leaving the method");
		}
		return category.getCategoryId();
	}

	@Override
	public Category loadCategory(Integer categoryId) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadCategory", "Entering the method");
		}
		Category category = null;
		try {
			category = hibernateTemplate.get(Category.class, categoryId);
		} catch (Exception e) {
			e.printStackTrace();

			if(LoggerHelper.isInfoEnabled()) {
				LoggerHelper.logException(this.getClass().getName(), "loadCategory", "Exception occured...");
			}

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "loadCategory", "Leaving the method");
		}
		return category;
	}

	@Override
	public List<Category> getAllCategories() {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllCategories", "Entering the method");
		}
		List<Category> citiesList=null;
		try {
			citiesList= hibernateTemplate.loadAll(Category.class);
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "getAllCategories", "Exception occured...");
			}
		}finally {

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllCategories", "Leaving the method");
		}

		return citiesList;
	}

}
