package com.yeosin.apply;

/*
 *	고사장 테이블 DTO 
 */
public class ExamZoneDto {
	
	private String examZoneId;		//고사장(PK)
	private String localCenterId;	//지역센터 (지역센터 테이블)
	private String local;			//지역
	private String localDetail;		//구
	private String examZoneName;	//고사장명
	private String examRoomCnt;		//시험교실 수
	private String examRoomUserCnt;	//교실당 인원 수
	private String examTotalUserCnt;//전체 인원 수
	private String examZoneMap;		//약도
	private String description;		//비고
	private String leftOverSeat;  	//잔여좌석수
	private String receiptSeat;  	//접수좌석수
	private String rowNum; 			// 행순서
	
	private LocalCenterDto localCenterDto; // 지역센터 DTO
	private ExamDto examDto; // 시험 DTO
	private ApplyDto applyDto; // 접수 DTO
	
	private int page;        		// 현재 페이지 번호   
	private int perPageNum; 		// 현 페이지당 보여줄 게시글의 갯수
	   
	public ExamZoneDto() 
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
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public ExamDto getExamDto() {
		return examDto;
	}
	public void setExamDto(ExamDto examDto) {
		this.examDto = examDto;
	}
	public ApplyDto getApplyDto() {
		return applyDto;
	}
	public void setApplyDto(ApplyDto applyDto) {
		this.applyDto = applyDto;
	}
	public LocalCenterDto getLocalCenterDto() {
		return localCenterDto;
	}
	public void setLocalCenterDto(LocalCenterDto localCenterDto) {
		this.localCenterDto = localCenterDto;
	}
	public String getReceiptSeat() {
		return receiptSeat;
	}
	public void setReceiptSeat(String receiptSeat) {
		this.receiptSeat = receiptSeat;
	}
	public String getLeftOverSeat() {
		return leftOverSeat;
	}
	public void setLeftOverSeat(String leftOverSeat) {
		this.leftOverSeat = leftOverSeat;
	}
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
	public String getExamRoomCnt() {
		return examRoomCnt;
	}
	public void setExamRoomCnt(String examRoomCnt) {
		this.examRoomCnt = examRoomCnt;
	}
	public String getExamRoomUserCnt() {
		return examRoomUserCnt;
	}
	public void setExamRoomUserCnt(String examRoomUserCnt) {
		this.examRoomUserCnt = examRoomUserCnt;
	}
	public String getExamTotalUserCnt() {
		return examTotalUserCnt;
	}
	public void setExamTotalUserCnt(String examTotalUserCnt) {
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
