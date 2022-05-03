package com.yeosin.user;

public class EduCompletionDto {
	
	private String userId;
	private String eduUserId;
	private String userName;
	private String birthDate;
	private String gender;
	private String certId;
	private String subject;
	private String apiSyncId;
	private String upApiSyncId;
	private String certDate;
	private String searchWord;
	private int page; // 현재 페이지 번호
	private int perPageNum; // 현 페이지당 보여줄 게시글의 갯수

	public EduCompletionDto() {
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
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEduUserId() {
		return eduUserId;
	}
	public void setEduUserId(String eduUserId) {
		this.eduUserId = eduUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCertId() {
		return certId;
	}
	public void setCertId(String certId) {
		this.certId = certId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getApiSyncId() {
		return apiSyncId;
	}
	public void setApiSyncId(String apiSyncId) {
		this.apiSyncId = apiSyncId;
	}
	public String getUpApiSyncId() {
		return upApiSyncId;
	}
	public void setUpApiSyncId(String upApiSyncId) {
		this.upApiSyncId = upApiSyncId;
	}
	public String getCertDate() {
		return certDate;
	}
	public void setCertDate(String certDate) {
		this.certDate = certDate;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
}
