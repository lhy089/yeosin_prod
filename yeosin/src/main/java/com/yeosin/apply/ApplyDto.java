package com.yeosin.apply;

import java.util.Date;

public class ApplyDto {
	
	private String reception_num;
	private int member_idx; 
	private int exam_idx;
	private Date reception_date; 
	private String education_certificate_num;
	private String education_process;
	private String student_code; 
	private String progress; 
	private String membership_id;
	
	public String getReception_num() {
		return reception_num;
	}
	public void setReception_num(String reception_num) {
		this.reception_num = reception_num;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public int getExam_idx() {
		return exam_idx;
	}
	public void setExam_idx(int exam_idx) {
		this.exam_idx = exam_idx;
	}
	public Date getReception_date() {
		return reception_date;
	}
	public void setReception_date(Date reception_date) {
		this.reception_date = reception_date;
	}
	public String getEducation_certificate_num() {
		return education_certificate_num;
	}
	public void setEducation_certificate_num(String education_certificate_num) {
		this.education_certificate_num = education_certificate_num;
	}
	public String getEducation_process() {
		return education_process;
	}
	public void setEducation_process(String education_process) {
		this.education_process = education_process;
	}
	public String getStudent_code() {
		return student_code;
	}
	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getMembership_id() {
		return membership_id;
	}
	public void setMembership_id(String membership_id) {
		this.membership_id = membership_id;
	} 
	
}
