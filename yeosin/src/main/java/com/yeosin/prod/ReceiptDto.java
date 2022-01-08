package com.yeosin.prod;

import java.util.Date;

public class ReceiptDto {
	
	private String receiptNumber;
	private String userId;
	private Date receiptDate;
	private String formId;
	private String studentCode;
	private String subject;
	private String local;
	private String examAreaDetail;
	private String examClass;
	private int examSequence;
	private int seatNumber;
	private String description;
	private Date createdTime;
	private String validState;
	
	
	public String getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getExamAreaDetail() {
		return examAreaDetail;
	}
	public void setExamAreaDetail(String examAreaDetail) {
		this.examAreaDetail = examAreaDetail;
	}
	public String getExamClass() {
		return examClass;
	}
	public void setExamClass(String examClass) {
		this.examClass = examClass;
	}
	public int getExamSequence() {
		return examSequence;
	}
	public void setExamSequence(int examSequence) {
		this.examSequence = examSequence;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
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
