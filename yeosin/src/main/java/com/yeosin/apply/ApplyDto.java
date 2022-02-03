package com.yeosin.apply;

import java.util.List;

import com.yeosin.user.UserDto;

/*
 *	접수 테이블 DTO 
 */
public class ApplyDto {
		
	private String receiptId; // 접수번호
	private String userId; // 회원 ID
	private String examId; // 시험코드
	private String receiptDate; // 접수일시
	private String isCancel; // 취소여부
	private String paymentMethod; // 결제수단
	private String cardName; // 카드이름
	private String bankName; // 은행이름
	private String paymentDate; // 결제날짜
	private Double examFee; // 응시료
	private Double saleRate; // 할인률
	private Double fees; // 수수료
	
	private int page;  		//현재 페이지 번호	
	private int perPageNum; //현 페이지당 보여줄 게시글의 갯수
	
	private ExamDto examDto; // 시험 DTO
	private UserDto userDto; // 유저 DTO
	private SubjectDto subjectDto; // 종목 DTO
	private GradeDto gradeDto; // 성적 DTO
		
	public ApplyDto() {
		this.page = 1;
		this.perPageNum  = 3;
	}
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
	public String getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(String receiptDate) {
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
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
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
	public ExamDto getExamDto() {
		return examDto;
	}
	public void setExamDto(ExamDto examDto) {
		this.examDto = examDto;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public SubjectDto getSubjectDto() {
		return subjectDto;
	}
	public void setSubjectDto(SubjectDto subjectDto) {
		this.subjectDto = subjectDto;
	}
	public GradeDto getGradeDto() {
		return gradeDto;
	}
	public void setGradeDto(GradeDto gradeDto) {
		this.gradeDto = gradeDto;
	}
	
	public int getPageStart() {
		return (this.page-1) * perPageNum;
	}
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	
	public void setPerPageNum(int pageCount) {
		int cnt = this.perPageNum;
		if(pageCount != cnt) {
			this.perPageNum = cnt;
		}else {
			this.perPageNum = pageCount;
		}
	}
	
}
