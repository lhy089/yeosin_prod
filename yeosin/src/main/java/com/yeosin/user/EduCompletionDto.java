package com.yeosin.user;

public class EduCompletionDto {
	
	private String userId;
	private String eduUserId;
	private String userName;
	private String birthDate;
	private String gender;
	private String certId;
	private String subject;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEduUserId() {
		return eduUserId;
	}
	public void setEduUserId(String eduUserId) {
		this.eduUserId = eduUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCertId() {
		return certId;
	}
	public void setCertId(String certId) {
		this.certId = certId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
