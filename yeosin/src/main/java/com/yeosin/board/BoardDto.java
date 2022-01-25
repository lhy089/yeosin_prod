package com.yeosin.board;

public class BoardDto {
	
	private String boardId;		//게시글 ID (PK)
	private String boardType;	//게시판 타입	// 1 : 공지사항 , 2: 자주하는질문 , 3 : 시험자료실
	private int boardSequence;	//게시글 번호
	private String title;		//제목
	private String contents;	//내용
	private int hitCnt;			//조회수	
	private String userId;		//회원 ID	(회원테이블)
	private String writeTime;		//작성시간
	private String category;	//카테고리
	private String categorySequence; //카테고리 번호
	
	private String searchWord;
	private String searchType;
	
	private int page;  		//현재 페이지 번호
	private int perPageNum; //현 페이지당 보여줄 게시글의 갯수
	
	public BoardDto() {
		this.page = 1;
		this.perPageNum  = 3;
	}
	
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
	public String getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategorySequence() {
		return categorySequence;
	}
	public void setCategorySequence(String categorySequence) {
		this.categorySequence = categorySequence;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
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
			this.perPageNum = cnt;
		}else {
			this.perPageNum = pageCount;
		}
	}
}
