package com.yeosin.user;

import java.security.SecureRandom;
import java.util.Date;

public class EduCompletionHisDto {
	
	private String apiSyncId;
	private String apiSyncDate;
	private String passStartDate;
	private String passEndDate;
	private String apiSyncType;
	
	public String getApiSyncId() {
		return apiSyncId;
	}
	public void setApiSyncId() {
		String apiSyncId = "EC" + getRamdomPwd();
		this.apiSyncId = apiSyncId;
	}
	public String getApiSyncDate() {
		return apiSyncDate;
	}
	public void setApiSyncDate(String apiSyncDate) {
		this.apiSyncDate = apiSyncDate;
	}
	public String getPassStartDate() {
		return passStartDate;
	}
	public void setPassStartDate(String passStartDate) {
		this.passStartDate = passStartDate;
	}
	public String getPassEndDate() {
		return passEndDate;
	}
	public void setPassEndDate(String passEndDate) {
		this.passEndDate = passEndDate;
	}
	public String getApiSyncType() {
		return apiSyncType;
	}
	public void setApiSyncType(String apiSyncType) {
		this.apiSyncType = apiSyncType;
	}
	public String getRamdomPwd() { 
		char[] charSet = new char[] {'0','1','2','3','4','5','6','7','8','9'}; 
		StringBuffer sb = new StringBuffer(); 
		SecureRandom sr = new SecureRandom(); 
		sr.setSeed(new Date().getTime()); 
		int idx = 0; 
		int len = charSet.length; 
		for (int i=0; i<10; i++) {
			idx = sr.nextInt(len); // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다. 
			sb.append(charSet[idx]); 
		} 
		return sb.toString() ;
	}
}
