package com.dvdrental.inventory.common;

import java.io.Serializable;

public class SingleSignOnBean implements Serializable {
	public String userEmail=null;
	public String userEncryptedPwd=null;
	public String userDecryptedPwd=null;
	public String jSessionId=null;
	public String isActive=null;
	
	public SingleSignOnBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserEncryptedPwd() {
		return userEncryptedPwd;
	}

	public void setUserEncryptedPwd(String userEncryptedPwd) {
		this.userEncryptedPwd = userEncryptedPwd;
	}

	public String getUserDecryptedPwd() {
		return userDecryptedPwd;
	}

	public void setUserDecryptedPwd(String userDecryptedPwd) {
		this.userDecryptedPwd = userDecryptedPwd;
	}

	public String getjSessionId() {
		return jSessionId;
	}

	public void setjSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "SingleSignOnBean [userEmail=" + userEmail + ", userEncryptedPwd=" + userEncryptedPwd
				+ ", userDecryptedPwd=" + userDecryptedPwd + ", jSessionId=" + jSessionId + ", isActive=" + isActive
				+ "]";
	}
	
	
	
			
	

}
