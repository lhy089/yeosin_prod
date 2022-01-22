package com.yeosin.apply;

/*
 *	시험 - 종목 관계테이블 DTO 
 */
public class ExamAndSubjectRelDto {
	
	private String examId; // 시험 ID
	private String subjectId; // 종목 ID
	
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	} 
	
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

}
