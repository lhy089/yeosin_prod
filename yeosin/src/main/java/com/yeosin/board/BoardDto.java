package com.yeosin.board;

import java.util.Date;

public class BoardDto {
	
	private String boardId;		//�Խñ� ID (PK)
	private String boardType;	//�Խ��� Ÿ��	
	private int boardSequence;	//�Խñ� ��ȣ
	private String title;		//����
	private String contents;	//����
	private int hitCnt;			//��ȸ��	
	private String userId;		//ȸ�� ID	(ȸ�����̺�)
	private Date writeTime;		//�ۼ��ð�
	
	
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public int getBoardSequence() {
		return boardSequence;
	}
	public void setBoardSequence(int boardSequence) {
		this.boardSequence = boardSequence;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHitCnt() {
		return hitCnt;
	}
	public void setHitCnt(int hitCnt) {
		this.hitCnt = hitCnt;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}
}
