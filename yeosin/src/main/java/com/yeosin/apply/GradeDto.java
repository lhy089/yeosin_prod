package com.yeosin.apply;

/*
 *	성적 테이블 DTO 
 */
public class GradeDto {
	
	private String receiptId; // 접수번호(수험번호)
	private String isQuit; // 결시여부
	private double allScore; // 총점
	private String isPass; // 합격여부
	private String isQual; // 자격여부
	
	public String getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	public String getIsQuit() {
		return isQuit;
	}
	public void setIsQuit(String isQuit) {
		this.isQuit = isQuit;
	}
	public double getAllScore() {
		return allScore;
	}
	public void setAllScore(double allScore) {
		this.allScore = allScore;
	}
	public String getIsPass() {
		return isPass;
	}
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}
	public String getIsQual() {
		return isQual;
	}
	public void setIsQual(String isQual) {
		this.isQual = isQual;
	}
	
	
}