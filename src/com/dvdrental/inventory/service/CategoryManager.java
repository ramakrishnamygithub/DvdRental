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
import com.dvdrental.inventory.dao.CategoryDao;
import com.dvdrental.inventory.dao.CountryDao;
import com.dvdrental.inventory.model.Category;
import com.dvdrental.inventory.model.Country;

@RemoteProxy(name="categoryService")
public class CategoryManager {

	
	

	@RemoteMethod
	public List<Category> getAllCategories() {
		//WebContext webContext= WebContextFactory.get();
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllCategories", "Entering the method");
		}
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		CategoryDao categoryDao=(CategoryDao) dbFunctions.getDaoImplBean(SpringBeanConstants.CategoryDaoImpl, servletContext);

		List<Category> categoriesList=null;
		try {
			categoriesList= categoryDao.getAllCategories();
		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "getAllCategories", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			categoryDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "getAllCategories", "Entering the method");
		}

		return categoriesList;
	}
	@RemoteMethod
	public Category updateCategory(HashMap categoryMap) {
		//WebContext webContext= WebContextFactory.get();
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateCategory", "Entering the method");
		}
		Category category=null;
		int categoryId=0;
		int updatedCategoryId=0;
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		CategoryDao categoryDao=(CategoryDao) dbFunctions.getDaoImplBean(SpringBeanConstants.CategoryDaoImpl, servletContext);
		try {
			categoryId=Integer.parseInt((String) categoryMap.get("categoryId"));
			String categoryName=(String)categoryMap.get("category");
			category=categoryDao.loadCategory(categoryId);
			if(category!=null) {
				category.setName(categoryName);
				category.setLastUpdate(new Date());

			}
			updatedCategoryId=categoryDao.updateCategory(category);
			if(updatedCategoryId==0) {
				category=null;
			}

		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "updateCategory", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			categoryDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "updateCountry", "Entering the method");
		}

		return category;
	}
	@RemoteMethod
	public boolean deleteCategory(HashMap categoryMap) {
		LoggerHelper.intialize();
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteCategory", "Entering the method");
		}
		Category category=null;
		int categoryId=0;
		int deletedCategoryId=0;
		boolean isdeleted=false;
		ServletContext servletContext= WebContextFactory.get().getServletContext();
		DBFunctions dbFunctions=new DBFunctions();
		CategoryDao categoryDao=(CategoryDao) dbFunctions.getDaoImplBean(SpringBeanConstants.CategoryDaoImpl, servletContext);
		try {
			categoryId=Integer.parseInt((String) categoryMap.get("categoryId"));


			deletedCategoryId=categoryDao.deleteCategory(categoryId);
			if(deletedCategoryId!=0) {
				isdeleted=true;
			}else {
				isdeleted=false;
			}

		}catch(Exception e) {
			e.printStackTrace();
			if(LoggerHelper.isDebugEnabled()) {
				LoggerHelper.logDebug(this.getClass().getName(), "deleteCategory", "Exception occured...");
			}

		}finally {
			dbFunctions=null;
			categoryDao=null;

		}
		if(LoggerHelper.isInfoEnabled()) {
			LoggerHelper.logInfo(this.getClass().getName(), "deleteCategory", "Entering the method");
		}

		return isdeleted;
	}


}
