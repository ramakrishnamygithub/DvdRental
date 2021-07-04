package com.dvdrental.inventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
@Entity
@Table(name="preferences")
public class Preferences {
	@Id
	@Column(name="preferences_id")
	public int preferencedId;
	@Column(name="login_fail_count_limit")
	public int loginFailCountLimit;
	@Column(name="is_login_fail_check")
	public String isLoginFailCheck;
	@Column(name="is_pwd_exp_check")
	public String isPwdExpCheck;
	@Column(name="is_mac_address_check")
	public boolean isMacAddressCheck;
	@Column(name="mac_address")
	public String macAddress;
	public Preferences() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPreferencedId() {
		return preferencedId;
	}
	public void setPreferencedId(int preferencedId) {
		this.preferencedId = preferencedId;
	}
	public int getLoginFailCountLimit() {
		return loginFailCountLimit;
	}
	public void setLoginFailCountLimit(int loginFailCountLimit) {
		this.loginFailCountLimit = loginFailCountLimit;
	}
	public String getIsLoginFailCheck() {
		return isLoginFailCheck;
	}
	public void setIsLoginFailCheck(String isLoginFailCheck) {
		this.isLoginFailCheck = isLoginFailCheck;
	}
	public String getIsPwdExpCheck() {
		return isPwdExpCheck;
	}
	public void setIsPwdExpCheck(String isPwdExpCheck) {
		this.isPwdExpCheck = isPwdExpCheck;
	}
	public boolean isMacAddressCheck() {
		return isMacAddressCheck;
	}
	public void setMacAddressCheck(boolean isMacAddressCheck) {
		this.isMacAddressCheck = isMacAddressCheck;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	@Override
	public String toString() {
		return "Preferences [preferencedId=" + preferencedId + ", loginFailCountLimit=" + loginFailCountLimit
				+ ", isLoginFailCheck=" + isLoginFailCheck + ", isPwdExpCheck=" + isPwdExpCheck + ", isMacAddressCheck="
				+ isMacAddressCheck + ", macAddress=" + macAddress + "]";
	}
	
	

}
