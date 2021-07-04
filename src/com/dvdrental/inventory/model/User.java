package com.dvdrental.inventory.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
@Entity
@Table(name="user_master")

public class User implements Serializable{
	@Id
	@Column(name="user_id")
	public int userId;
	@Column(name="user_fname")
	public String userFname;
	@Column(name="user_mname")
	public String userMname;
	@Column(name="user_lname")
	public String userLname;
	@Column(name="user_email")
	public String userEmail;
	@Column(name="user_pwd")
	public String userPwd;
	@Column(name="user_authantication")
	public String userAuthentication;
	@Column(name="user_ip_address")
	public String userIpAddress;
	@Column(name="login_fail_count")
	public int loginFailCount;
	
	@Column(name="user_status")
	public String userStatus;
	
	@Column(name="login_fail_time")
	public Date loginFailTime; 
	
	@Column(name="is_first_login")
	public String isFirstLogin;
	
	@Column(name="last_update")
	public Date lastUpdate;
	
	public User() {
		super();
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserFname() {
		return userFname;
	}
	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}
	public String getUserMname() {
		return userMname;
	}
	public void setUserMname(String userMname) {
		this.userMname = userMname;
	}
	public String getUserLname() {
		return userLname;
	}
	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
	public String getUserAuthentication() {
		return userAuthentication;
	}
	public void setUserAuthentication(String userAuthentication) {
		this.userAuthentication = userAuthentication;
	}
	
	public String getUserIpAddress() {
		return userIpAddress;
	}
	public void setUserIpAddress(String userIpAddress) {
		this.userIpAddress = userIpAddress;
	}
	
	public int getLoginFailCount() {
		return loginFailCount;
	}
	public void setLoginFailCount(int loginFailCount) {
		this.loginFailCount = loginFailCount;
	}
	
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	public Date getLoginFailTime() {
		return loginFailTime;
	}
	public void setLoginFailTime(Date loginFailTime) {
		this.loginFailTime = loginFailTime;
	}
	
	public String getIsFirstLogin() {
		return isFirstLogin;
	}
	public void setIsFirstLogin(String isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userFname=" + userFname + ", userMname=" + userMname + ", userLname="
				+ userLname + ", userEmail=" + userEmail + ", userPwd=" + userPwd + ", userAuthentication="
				+ userAuthentication + ", userIpAddress=" + userIpAddress + ", loginFailCount=" + loginFailCount
				+ ", userStatus=" + userStatus + ", loginFailTime=" + loginFailTime + ", isFirstLogin=" + isFirstLogin
				+ ", lastUpdate=" + lastUpdate + "]";
	}
	
	
	
	
	
	
	
	
	
	
	

}
