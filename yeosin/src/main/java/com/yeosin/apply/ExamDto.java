package com.yeosin.apply;

import java.util.Date;

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
	private int examDegree; // 시험회차
	private Date examDate; // 시험일
	private String examLocal; // 시험지역
	private String examArea; // 시험영역
	private Date receiptstartDate; // 접수 시작일
	private Date receiptEndDate; // 접수 마감일
	private Date allRefundExitDate; // 100% 환불 종료일
	private String isPracticaleExam; // 실기시험여부
	private Date certPrintStartDate; // 수험표 출력기간(시작)
	private Date certPrintEndDate; // 수혐표 출력기간(끝)
	private Date gradeStartDate; // 성적 공고기간(시작)
	private Date gradeEndDate; // 성적 공고기간(끝)
	
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
	public int getExamDegree() {
		return examDegree;
	}
	public void setExamDegree(int examDegree) {
		this.examDegree = examDegree;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
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
	public Date getReceiptstartDate() {
		return receiptstartDate;
	}
	public void setReceiptstartDate(Date receiptstartDate) {
		this.receiptstartDate = receiptstartDate;
	}
	public Date getReceiptEndDate() {
		return receiptEndDate;
	}
	public void setReceiptEndDate(Date receiptEndDate) {
		this.receiptEndDate = receiptEndDate;
	}
	public Date getAllRefundExitDate() {
		return allRefundExitDate;
	}
	public void setAllRefundExitDate(Date allRefundExitDate) {
		this.allRefundExitDate = allRefundExitDate;
	}
	public String getIsPracticaleExam() {
		return isPracticaleExam;
	}
	public void setIsPracticaleExam(String isPracticaleExam) {
		this.isPracticaleExam = isPracticaleExam;
	}
	public Date getCertPrintStartDate() {
		return certPrintStartDate;
	}
	public void setCertPrintStartDate(Date certPrintStartDate) {
		this.certPrintStartDate = certPrintStartDate;
	}
	public Date getCertPrintEndDate() {
		return certPrintEndDate;
	}
	public void setCertPrintEndDate(Date certPrintEndDate) {
		this.certPrintEndDate = certPrintEndDate;
	}
	public Date getGradeStartDate() {
		return gradeStartDate;
	}
	public void setGradeStartDate(Date gradeStartDate) {
		this.gradeStartDate = gradeStartDate;
	}
	public Date getGradeEndDate() {
		return gradeEndDate;
	}
	public void setGradeEndDate(Date gradeEndDate) {
		this.gradeEndDate = gradeEndDate;
	}

}
