package com.yeosin.board;


public class PopupDtoPageMaker {
   
	private PopupDto popupDto;
	private int totalCount;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int displayPageNum = 10;
    
    public PopupDto getPopupDto() {
      return popupDto;
   }
   public void sePopupDto(PopupDto popupDto) {
      this.popupDto = popupDto;
   }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }
    
    private void calcData() {
        
        endPage = (int) (Math.ceil(popupDto.getPage() / (double) displayPageNum) * displayPageNum);
 
        startPage = (endPage - displayPageNum) + 1;
        if(startPage <= 0) startPage = 1;
        
        int tempEndPage = (int) (Math.ceil(totalCount / (double) popupDto.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
 
        prev = startPage == 1 ? false : true;
        next = endPage * popupDto.getPerPageNum() < totalCount ? true : false;
        
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