package com.yeosin.member;

import java.util.Date;

public class UserDto {
	
	private String userId;
	private String userName;
	private String password;
	private String isPrivacy;
	private String engUserNameFirst;
	private String engUserNameLast;
	private String telNumber;
	private String phoneNumber;
	private String email;
	private String description;
	private Date createTime;
	private String testNumber;
	private Date birthDay;
	private String validState;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsPrivacy() {
		return isPrivacy;
	}
	public void setIsPrivacy(String isPrivacy) {
		this.isPrivacy = isPrivacy;
	}
	public String getEngUserNameFirst() {
		return engUserNameFirst;
	}
	public void setEngUserNameFirst(String engUserNameFirst) {
		this.engUserNameFirst = engUserNameFirst;
	}
	public String getEngUserNameLast() {
		return engUserNameLast;
	}
	public void setEngUserNameLast(String engUserNameLast) {
		this.engUserNameLast = engUserNameLast;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTestNumber() {
		return testNumber;
	}
	public void setTestNumber(String testNumber) {
		this.testNumber = testNumber;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getValidState() {
		return validState;
	}
	public void setValidState(String validState) {
		this.validState = validState;
	}
	
	
}
