package com.yeosin.apply;

/*
 *	시험 - 고사장 관계테이블 DTO 
 */
public class ExamAndExamzoneRelDto {
	
	private String keyId; // 시험코드 + local
	private String examzoneId; // 고사장 ID
	private String examId; // 시험코드
	private ExamZoneDto examZoneDto; // 시험Zone DTO
	   
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}	
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
	public ExamZoneDto getExamZoneDto() {
		return examZoneDto;
	}
	public void setExamZoneDto(ExamZoneDto examZoneDto) {
		this.examZoneDto = examZoneDto;
	}
}
