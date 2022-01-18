package com.yeosin.apply;

public class ExamZoneDto {
	
	private String examZoneId;		//고사장(PK)
	private String localCenterId;	//지역센터 (지역센터 테이블)
	private String local;			//지역
	private String localDetail;		//구
	private String examZoneName;	//고사장명
	private int examRoomCnt;		//시험교실 수
	private int examRoomUserCnt;	//교실당 인원 수
	private int examTotalUserCnt;	//전체 인원 수
	private String examZoneMap;		//약도
	private String description;		//비고
	
	public String getExamZoneId() {
		return examZoneId;
	}
	public void setExamZoneId(String examZoneId) {
		this.examZoneId = examZoneId;
	}
	public String getLocalCenterId() {
		return localCenterId;
	}
	public void setLocalCenterId(String localCenterId) {
		this.localCenterId = localCenterId;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getLocalDetail() {
		return localDetail;
	}
	public void setLocalDetail(String localDetail) {
		this.localDetail = localDetail;
	}
	public String getExamZoneName() {
		return examZoneName;
	}
	public void setExamZoneName(String examZoneName) {
		this.examZoneName = examZoneName;
	}
	public int getExamRoomCnt() {
		return examRoomCnt;
	}
	public void setExamRoomCnt(int examRoomCnt) {
		this.examRoomCnt = examRoomCnt;
	}
	public int getExamRoomUserCnt() {
		return examRoomUserCnt;
	}
	public void setExamRoomUserCnt(int examRoomUserCnt) {
		this.examRoomUserCnt = examRoomUserCnt;
	}
	public int getExamTotalUserCnt() {
		return examTotalUserCnt;
	}
	public void setExamTotalUserCnt(int examTotalUserCnt) {
		this.examTotalUserCnt = examTotalUserCnt;
	}
	public String getExamZoneMap() {
		return examZoneMap;
	}
	public void setExamZoneMap(String examZoneMap) {
		this.examZoneMap = examZoneMap;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
