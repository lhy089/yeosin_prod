package com.yeosin.user;

import java.util.Date;

/*
 *	회원 테이블 DTO 
 */
public class UserDto {
	
	private String userId; // 회원 ID
	private String password; // 비밀번호
	private String userName; // 회원명
	private String grade; // 등급
	private String gender; // 성별
	private String joinDate; // 가입일
	private String lastConnectDate; //최근 접속일
	private String birthDate; // 생년월일 
	private String callNumber; // 연락처
	private String phoneNumber; // 휴대전화
	private String emailAddress; // 이메일
	private String isReceiveEmail; // 이메일 수신여부 > Y:수신 , N:수신x
	private String isReceiveSms; // 문자 수신 여부 > Y:수신, N:수신x
	private String modifyDate; // 수정일
	private String userStatus;	// 사용자 상태 > U:활성화, C:비밀번호 변경 필요, D:탈퇴
	private String ciCode;		// 개인식별번호
	private String diCode;		// 중복식별번호
	private String secessionDate; //탈퇴날짜
	
	private String dormantAccountDate; // 휴면계정전환일
	
	private EduCompletionDto eduCompletionDto;
	
	private int page;  		//현재 페이지 번호	
	private int perPageNum; //현 페이지당 보여줄 게시글의 갯수
	
	//관리자 회원정보 페이지 관련 변수
//	private String searchWord; // 검색어 
//	private String isCheckGeneralGrade; //일반등급 체크여부 > Y:체크 , N:체크x
//	private String isCheckManagerGrade;//관리자등급 체크여부 > Y:체크, N:체크x
//	private String isCheckAssistantGrade; //부관리자등급 체크 여부 > Y:체크, N:체크x
//	private String isCheckMemberGrade; //멤버등급 체크여부 > Y:체크, N:체크x
//	private String searchEmailType; //이메일 수신여부 검색타입 > A:전체, Y:수신허용, N:수신거부
//	private String searchSMSType; //SMS 수신여부 검색타입> A:전체, Y:수신허용, N:수신거부
	
	public UserDto() 
	{
		this.page = 1;
		this.perPageNum  = 50;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getLastConnectDate() {
		return lastConnectDate;
	}
	public void setLastConnectDate(String lastConnectDate) {
		this.lastConnectDate = lastConnectDate;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getCallNumber() {
		return callNumber;
	}
	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getIsReceiveEmail() {
		return isReceiveEmail;
	}
	public void setIsReceiveEmail(String isReceiveEmail) {
		this.isReceiveEmail = isReceiveEmail;
	}
	public String getIsReceiveSms() {
		return isReceiveSms;
	}
	public void setIsReceiveSms(String isReceiveSms) {
		this.isReceiveSms = isReceiveSms;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public EduCompletionDto getEduCompletionDto() {
		return eduCompletionDto;
	}
	public void setEduCompletionDto(EduCompletionDto eduCompletionDto) {
		this.eduCompletionDto = eduCompletionDto;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getCiCode() {
		return ciCode;
	}
	public void setCiCode(String ciCode) {
		this.ciCode = ciCode;
	}
	public String getDiCode() {
		return diCode;
	}
	public void setDiCode(String diCode) {
		this.diCode = diCode;
	}
	public String getDormantAccountDate() {
		return dormantAccountDate;
	}
	public void setDormantAccountDate(String dormantAccountDate) {
		this.dormantAccountDate = dormantAccountDate;
	}
	public String getSecessionDate() {
		return secessionDate;
	}
	public void setSecessionDate(String secessionDate) {
		this.secessionDate = secessionDate;
	}
//	public String getIsCheckGeneralGrade() {
//		return isCheckGeneralGrade;
//	}
//	public void setIsCheckGeneralGrade(String isCheckGeneralGrade) {
//		this.isCheckGeneralGrade = isCheckGeneralGrade;
//	}
//	public String getIsCheckManagerGrade() {
//		return isCheckManagerGrade;
//	}
//	public void setIsCheckManagerGrade(String isCheckManagerGrade) {
//		this.isCheckManagerGrade = isCheckManagerGrade;
//	}
//	public String getIsCheckAssistantGrade() {
//		return isCheckAssistantGrade;
//	}
//	public void setIsCheckAssistantGrade(String isCheckAssistantGrade) {
//		this.isCheckAssistantGrade = isCheckAssistantGrade;
//	}
//	public String getIsCheckMemberGrade() {
//		return isCheckMemberGrade;
//	}
//	public void setIsCheckMemberGrade(String isCheckMemberGrade) {
//		this.isCheckMemberGrade = isCheckMemberGrade;
//	}
//	public String getSearchEmailType() {
//		return searchEmailType;
//	}
//	public void setSearchEmailType(String searchEmailType) {
//		this.searchEmailType = searchEmailType;
//	}
//	public String getSearchSMSType() {
//		return searchSMSType;
//	}
//	public void setSearchSMSType(String searchSMSType) {
//		this.searchSMSType = searchSMSType;
//	}
//	public String getSearchWord() {
//		return searchWord;
//	}
//	public void setSearchWord(String searchWord) {
//		this.searchWord = searchWord;
//	}
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
			this.perPageNum = pageCount;
		}else {
			this.perPageNum = pageCount;
		}
	}
}
