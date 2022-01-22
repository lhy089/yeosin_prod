package com.yeosin.user;

import java.util.Date;

/*
 *	회원 테이블 DTO 
 */
public class UserDto {
	
	private String userId; // 회원 ID
	private String password; // 비밀번호
	private String userName; // 회원명
	private String grade; // 등급
	private String gender; // 성별
	private Date joinDate; // 가입일
	private String lastConnectDate; //최근 접속일
	private String birthDate; // 생년월일 
	private String callNumber; // 연락처
	private String phoneNumber; // 휴대전화
	private String emailAddress; // 이메일
	private String isReceiveEmail; // 이메일 수신여부
	private String isReceiveSms; // 문자 수신 여부
	private String modifyDate; // 수정일
	
	private EduCompletionDto eduCompletionDto;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getLastConnectDate() {
		return lastConnectDate;
	}
	public void setLastConnectDate(String lastConnectDate) {
		this.lastConnectDate = lastConnectDate;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getCallNumber() {
		return callNumber;
	}
	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getIsReceiveEmail() {
		return isReceiveEmail;
	}
	public void setIsReceiveEmail(String isReceiveEmail) {
		this.isReceiveEmail = isReceiveEmail;
	}
	public String getIsReceiveSms() {
		return isReceiveSms;
	}
	public void setIsReceiveSms(String isReceiveSms) {
		this.isReceiveSms = isReceiveSms;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public EduCompletionDto getEduCompletionDto() {
		return eduCompletionDto;
	}
	public void setEduCompletionDto(EduCompletionDto eduCompletionDto) {
		this.eduCompletionDto = eduCompletionDto;
	}
	
}
