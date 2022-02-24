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
	private String examFee; // 응시료
	private String saleRate; // 할인률
	private String fees; // 수수료
	private String certId; // 교육수료증 번호
	private String studentCode; // 수험번호
	private String seatNumber; // 좌석번호
	private String examZoneId; // 고사장 ID
	private String paymentId; // 거래 ID
	private String paymentCancelDate; // 결제취소일
	private String subjectId; // 종목코드
	private String rowNum; // 행순서

	private int page;  		//현재 페이지 번호	
	private int perPageNum; //현 페이지당 보여줄 게시글의 갯수
	
	private ExamDto examDto; // 시험 DTO
	private UserDto userDto; // 유저 DTO
	private SubjectDto subjectDto; // 종목 DTO
	private GradeDto gradeDto; // 성적 DTO
	private ExamZoneDto examZoneDto; // 고사장 DTO
		
	public ApplyDto() 
	{
		this.page = 1;
		this.perPageNum  = 30;
	}
	
	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	
	public String getPaymentCancelDate() {
		return paymentCancelDate;
	}

	public void setPaymentCancelDate(String paymentCancelDate) {
		this.paymentCancelDate = paymentCancelDate;
	}
	public String getCertId() {
		return certId;
	}
	public void setCertId(String certId) {
		this.certId = certId;
	}
	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getExamZoneId() {
		return examZoneId;
	}
	public void setExamZoneId(String examZoneId) {
		this.examZoneId = examZoneId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
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
	public String getExamFee() {
		return examFee;
	}
	public void setExamFee(String examFee) {
		this.examFee = examFee;
	}
	public String getSaleRate() {
		return saleRate;
	}
	public void setSaleRate(String saleRate) {
		this.saleRate = saleRate;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
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
	public ExamZoneDto getExamZoneDto() {
		return examZoneDto;
	}

	public void setExamZoneDto(ExamZoneDto examZoneDto) {
		this.examZoneDto = examZoneDto;
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
