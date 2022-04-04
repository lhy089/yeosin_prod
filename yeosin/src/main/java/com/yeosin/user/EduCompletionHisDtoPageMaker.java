package com.yeosin.user;

public class EduCompletionHisDtoPageMaker {

	private EduCompletionHisDto eduCompletionHisDto;
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = 10;

	public EduCompletionHisDto getEduCompletionHisDto() {
		return eduCompletionHisDto;
	}

	public void setEduCompletionHisDto(EduCompletionHisDto eduCompletionHisDto) {
		this.eduCompletionHisDto = eduCompletionHisDto;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	private void calcData() {

		endPage = (int) (Math.ceil(eduCompletionHisDto.getPage() / (double) displayPageNum) * displayPageNum);

		startPage = (endPage - displayPageNum) + 1;
		if (startPage <= 0)
			startPage = 1;

		int tempEndPage = (int) (Math.ceil(totalCount / (double) eduCompletionHisDto.getPerPageNum()));
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		prev = startPage == 1 ? false : true;
		next = endPage * eduCompletionHisDto.getPerPageNum() < totalCount ? true : false;

	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
}