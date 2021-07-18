package com.dvdrental.inventory.dao;

import java.util.List;
import java.util.Map;

import com.dvdrental.inventory.model.Category;

public interface CategoryDao {
	public Integer createCategory(Category category);
	public Integer updateCategory(Category category);
	public Integer deleteCategory(Integer category);
	public Category loadCategory(Integer categoryId);
	public List<Category> getAllCategories();
	public List<Map<String, Object>> loadAllCategories();

}
