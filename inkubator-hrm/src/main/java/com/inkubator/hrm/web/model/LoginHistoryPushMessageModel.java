package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class LoginHistoryPushMessageModel implements Serializable{
	private String loginDate;
	private String loginTime;
	private String loginName;
	private String growlMessage;
	private String growlTitle;
	private Boolean isLogin;
	
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getGrowlMessage() {
		return growlMessage;
	}
	public void setGrowlMessage(String growlMessage) {
		this.growlMessage = growlMessage;
	}
	public String getGrowlTitle() {
		return growlTitle;
	}
	public void setGrowlTitle(String growlTitle) {
		this.growlTitle = growlTitle;
	}
	public Boolean getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}
}
