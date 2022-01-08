package com.yeosin.prod;

import java.util.Date;

public class GradeDto {
	
	private String receiptNumber;
	private String passResultDate;
	private String isNoCheckIn;
	private double totalScore;
	private String isPass;
	private String description;
	private Date createTime;
	private String validState;
	
	public String getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	public String getPassResultDate() {
		return passResultDate;
	}
	public void setPassResultDate(String passResultDate) {
		this.passResultDate = passResultDate;
	}
	public String getIsNoCheckIn() {
		return isNoCheckIn;
	}
	public void setIsNoCheckIn(String isNoCheckIn) {
		this.isNoCheckIn = isNoCheckIn;
	}
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
	public String getIsPass() {
		return isPass;
	}
	public void setIsPass(String isPass) {
		this.isPass = isPass;
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
	public String getValidState() {
		return validState;
	}
	public void setValidState(String validState) {
		this.validState = validState;
	}
	

}
