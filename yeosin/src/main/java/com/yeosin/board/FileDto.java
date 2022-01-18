package com.yeosin.board;

public class FileDto {
	
	private String fileId;			//���� ID	(PK)
	private String realFileName;	//���� ���ϸ�
	private String localFileName;	//���� ���ϸ�
	private double fileSize;		//���� ũ��
	private String fileURL;			//���� URL
	private String boardId;			//�Խñ� ID (�Խ��� ���̺�)
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
	public String getLocalFileName() {
		return localFileName;
	}
	public void setLocalFileName(String localFileName) {
		this.localFileName = localFileName;
	}
	public double getFileSize() {
		return fileSize;
	}
	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileURL() {
		return fileURL;
	}
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
}
