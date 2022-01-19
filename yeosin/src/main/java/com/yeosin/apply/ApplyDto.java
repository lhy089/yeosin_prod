package com.yeosin.apply;

import java.util.Date;

/*
 *	접수 테이블 DTO 
 */
public class ApplyDto {
		
	private String receiptId; // 접수번호
	private String userId; // 회원 ID
	private String examId; // 시험코드
	private Date receiptDate; // 접수일시
	private String isCancel; // 취소여부
	private String paymentMethod; // 결제수단
	private String cardName; // 카드이름
	private String bankName; // 은행이름
	private Date paymentDate; // 결제날짜
	private Double examFee; // 응시료
	private Double saleRate; // 할인률
	private Double fees; // 수수료
	
	public String getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	public Date getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}
	public String getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Double getExamFee() {
		return examFee;
	}
	public void setExamFee(Double examFee) {
		this.examFee = examFee;
	}
	public Double getSaleRate() {
		return saleRate;
	}
	public void setSaleRate(Double saleRate) {
		this.saleRate = saleRate;
	}
	public Double getFees() {
		return fees;
	}
	public void setFees(Double fees) {
		this.fees = fees;
	}
	
}
