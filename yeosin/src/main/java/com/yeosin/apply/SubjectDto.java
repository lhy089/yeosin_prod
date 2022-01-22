package com.yeosin.apply;

/*
 *	종목 테이블 DTO 
 */
public class SubjectDto {
		
	private String subjectId; // 종목 ID
	private String subjectName; // 종목명
	
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
}
