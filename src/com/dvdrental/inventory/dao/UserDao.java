package com.dvdrental.inventory.dao;

import java.util.List;
import java.util.Map;

import com.dvdrental.inventory.model.User;

public interface UserDao {
	
	public Integer createUser(User user);
	public Integer updateUser(User user);
	public Integer deleteUser(Integer userId);
	public User loadUser(Integer userId);
	public List<User> getAllUsers();
	public List<Map<String, Object>> loadAllUsers();
	public User loadByEmail(String email);
	public List<User> loadByEmailPwd(String email,String password);

}
