package com.yeosin.prod;

import java.util.Date;

public class FormDto {
	
	private String formId;
	private String examArea;
	private Date examDate;
	private String examPrice;
	private String examName;
	private Date gradeNotice;
	private String checkInTime;
	private String examSequence;
	private String certificateType;
	private String description;
	private Date createdTime;
	private String validState;
	
	
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getExamArea() {
		return examArea;
	}
	public void setExamArea(String examArea) {
		this.examArea = examArea;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public String getExamPrice() {
		return examPrice;
	}
	public void setExamPrice(String examPrice) {
		this.examPrice = examPrice;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public Date getGradeNotice() {
		return gradeNotice;
	}
	public void setGradeNotice(Date gradeNotice) {
		this.gradeNotice = gradeNotice;
	}
	public String getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}
	public String getExamSequence() {
		return examSequence;
	}
	public void setExamSequence(String examSequence) {
		this.examSequence = examSequence;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getValidState() {
		return validState;
	}
	public void setValidState(String validState) {
		this.validState = validState;
	}

}
