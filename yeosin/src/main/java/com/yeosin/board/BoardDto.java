package com.yeosin.board;

import java.util.Date;

public class BoardDto {
	
	private String boardId;		//게시글 ID (PK)
	private String boardType;	//게시판 타입	
	private int boardSequence;	//게시글 번호
	private String title;		//제목
	private String contents;	//내용
	private int hitCnt;			//조회수	
	private String userId;		//회원 ID	(회원테이블)
	private Date writeTime;		//작성시간
	
	
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
