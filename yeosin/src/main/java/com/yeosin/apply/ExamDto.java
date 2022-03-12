package com.yeosin.apply;

/*
 *	시험 테이블 DTO 
 */
public class ExamDto {
	
	private String examId; // 시험코드
	private String examName; // 시험명
	private String examYear; // 시험년도
	private String subjectId; // 종목 ID
	private String examType; // 시험구분
	private String isApproval; // 승인상태
	private String examDegree; // 시험회차
	private String examDate; // 시험일
	private String examLocal; // 시험지역
	private String examArea; // 시험영역
	private String receiptStartDate; // 접수 시작일
	private String receiptEndDate; // 접수 마감일
	private String allRefundExitDate; // 100% 환불 종료일
	private String isPracticalExam; // 실기시험여부
	private String certPrintStartDate; // 수험표 출력기간(시작)
	private String certPrintEndDate; // 수혐표 출력기간(끝)
	private String gradeStartDate; // 성적 공고기간(시작)
	private String gradeEndDate; // 성적 공고기간(끝)
	private String examCost; // 시험비용
	private String period;	//교시
	private String gradeStatus; // 성적등록여부
	
	private int page; // 현재 페이지 번호
	private int perPageNum; // 현 페이지당 보여줄 게시글의 갯수

	public ExamDto() {
		this.page = 1;
		this.perPageNum = 30;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int pageCount) {
		int cnt = this.perPageNum;
		if (pageCount != cnt) {
			this.perPageNum = pageCount;
		} else {
			this.perPageNum = pageCount;
		}
	}

	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}

	public String getExamCost() {
		return examCost;
	}
	public void setExamCost(String examCost) {
		this.examCost = examCost;
	}	
	
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getExamYear() {
		return examYear;
	}
	public void setExamYear(String examYear) {
		this.examYear = examYear;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public String getIsApproval() {
		return isApproval;
	}
	public void setIsApproval(String isApproval) {
		this.isApproval = isApproval;
	}
	public String getExamDegree() {
		return examDegree;
	}
	public void setExamDegree(String examDegree) {
		this.examDegree = examDegree;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public String getExamLocal() {
		return examLocal;
	}
	public void setExamLocal(String examLocal) {
		this.examLocal = examLocal;
	}
	public String getExamArea() {
		return examArea;
	}
	public void setExamArea(String examArea) {
		this.examArea = examArea;
	}
	public String getReceiptStartDate() {
		return receiptStartDate;
	}
	public void setReceiptStartDate(String receiptStartDate) {
		this.receiptStartDate = receiptStartDate;
	}
	public String getReceiptEndDate() {
		return receiptEndDate;
	}
	public void setReceiptEndDate(String receiptEndDate) {
		this.receiptEndDate = receiptEndDate;
	}
	public String getAllRefundExitDate() {
		return allRefundExitDate;
	}
	public void setAllRefundExitDate(String allRefundExitDate) {
		this.allRefundExitDate = allRefundExitDate;
	}
	public String getIsPracticalExam() {
		return isPracticalExam;
	}
	public void setIsPracticalExam(String isPracticalExam) {
		this.isPracticalExam = isPracticalExam;
	}
	public String getCertPrintStartDate() {
		return certPrintStartDate;
	}
	public void setCertPrintStartDate(String certPrintStartDate) {
		this.certPrintStartDate = certPrintStartDate;
	}
	public String getCertPrintEndDate() {
		return certPrintEndDate;
	}
	public void setCertPrintEndDate(String certPrintEndDate) {
		this.certPrintEndDate = certPrintEndDate;
	}
	public String getGradeStartDate() {
		return gradeStartDate;
	}
	public void setGradeStartDate(String gradeStartDate) {
		this.gradeStartDate = gradeStartDate;
	}
	public String getGradeEndDate() {
		return gradeEndDate;
	}
	public void setGradeEndDate(String gradeEndDate) {
		this.gradeEndDate = gradeEndDate;
	}
	public String getGradeStatus() {
		return gradeStatus;
	}
	public void setGradeStatus(String gradeStatus) {
		this.gradeStatus = gradeStatus;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
}
