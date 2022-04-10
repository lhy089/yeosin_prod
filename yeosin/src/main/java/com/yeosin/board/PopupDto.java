package com.yeosin.board;

public class PopupDto {
	
	private String popupId; // Popup ID
	private String title; // 제목
	private String contents; // 내용
	private String startDate; // 팝업 시작날짜
	private String endDate;	// 팝업 종료날짜
	private String fileId; // 파일 ID	
	private int layerLeftPx; // 팝업 레이어 좌측위치
	private int layerTopPx;	// 팝업 레이어 상단위치
	private String isVisible; // 팝업 노출여부
	private String writeTime; //작성시간
	private String cookieId; // 쿠키 ID
	
	private int page; // 현재 페이지 번호   
	private int perPageNum; // 현 페이지당 보여줄 게시글의 갯수
	
	private FileDto fileDto;
	
	public PopupDto() 
	{
		this.page = 1;
		this.perPageNum  = 30;
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
		if(pageCount != cnt) {
			this.perPageNum = pageCount;
		}else {
			this.perPageNum = pageCount;
		}
	} 
	public int getPageStart() {
	   return (this.page-1) * perPageNum;
	}
	
	public String getPopupId() {
		return popupId;
	}
	public void setPopupId(String popupId) {
		this.popupId = popupId;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public int getLayerLeftPx() {
		return layerLeftPx;
	}
	public void setLayerLeftPx(int layerLeftPx) {
		this.layerLeftPx = layerLeftPx;
	}
	public int getLayerTopPx() {
		return layerTopPx;
	}
	public void setLayerTopPx(int layerTopPx) {
		this.layerTopPx = layerTopPx;
	}
	public String getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}
	public String getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}
	public FileDto getFileDto() {
		return fileDto;
	}
	public void setFileDto(FileDto fileDto) {
		this.fileDto = fileDto;
	}
	public String getCookieId() {
		return cookieId;
	}
	public void setCookieId(String cookieId) {
		this.cookieId = cookieId;
	}
}
