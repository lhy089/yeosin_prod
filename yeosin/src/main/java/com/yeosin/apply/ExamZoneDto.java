package com.yeosin.apply;

public class ExamZoneDto {
	
	private String examZoneId;		//�����(PK)
	private String localCenterId;	//�������� (�������� ���̺�)
	private String local;			//����
	private String localDetail;		//��
	private String examZoneName;	//������
	private int examRoomCnt;		//���豳�� ��
	private int examRoomUserCnt;	//���Ǵ� �ο� ��
	private int examTotalUserCnt;	//��ü �ο� ��
	private String examZoneMap;		//�൵
	private String description;		//���
	
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
