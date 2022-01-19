package com.yeosin.apply;

/*
 *	시험 - 고사장 관계테이블 DTO 
 */
public class ExamAndExamzoneRelDto {
	
	private String examzoneId; // 고사장 ID
	private String examId; // 시험코드
	
	public String getExamzoneId() {
		return examzoneId;
	}
	public void setExamzoneId(String examzoneId) {
		this.examzoneId = examzoneId;
	}
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	} 

}
