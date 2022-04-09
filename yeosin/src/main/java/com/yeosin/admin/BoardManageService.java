package com.yeosin.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeosin.board.BoardDto;
import com.yeosin.board.FileDto;
import com.yeosin.board.PopupDto;

@Service
public class BoardManageService {

	@Autowired
	private BoardManageDao boardManageDao;

	//게시물관리 리스트 조회
	public List<BoardDto> getBoardList(BoardDto boardDto) throws Exception {
		
		List<BoardDto> getBoardList = boardManageDao.getBoardList(boardDto);
		return getBoardList;
	}
	
	//게시물관리 리스트 총 갯수
	public int countBoardListTotal(BoardDto boardDto) throws Exception {
		return boardManageDao.countBoardListTotal(boardDto);
	}
	
	//게시물관리 파일 조회
	public FileDto getFileInfo(String boardId) throws Exception{
		return boardManageDao.getFileInfo(boardId);
	}
	
	//게시물관리 게시판 조회
	public BoardDto getBoardInfo(BoardDto boardDto) throws Exception{
		return boardManageDao.getBoardInfo(boardDto);
	}
	
	//게시물관리 게시판 업데이트
	public int setBoardInfo(BoardDto boardDto) throws Exception{
		return boardManageDao.setBoardInfo(boardDto);
	}
	
	//게시물관리 파일 업데이트
	public int setFileInfo(FileDto fileDto) throws Exception{
		return boardManageDao.setFileInfo(fileDto);
	}
	
	//Board테이블 BoardId컬럼 MaxNumber 조회
	public int getMaxBoardId() throws Exception{
		return boardManageDao.getMaxBoardId();
	}
	
	//File테이블 fileId컬럼 MaxNumber 조회
	public int getMaxFileId() throws Exception{
		return boardManageDao.getMaxFileId();
	}
	
	//Board테이블 boardType별 boardSequence컬럼 MaxNumber 조회
	public int getMaxBoardSequence(BoardDto boardDto) throws Exception{
		return boardManageDao.getMaxBoardSequence(boardDto);
	}
	
	//게시물관리 게시판 추가하기
	public int insertBoardInfo(BoardDto boardDto) throws Exception{
		return boardManageDao.insertBoardInfo(boardDto);
	}
	
	//게시물관리 파일 추가하기
	public int insertFileInfo(FileDto fileDto) throws Exception{
		return boardManageDao.insertFileInfo(fileDto);
	}
	
	//게시물관리 게시판 리스트 삭제
	public int deleteBoardList(List<String> list) throws Exception{
		return boardManageDao.deleteBoardList(list);
	}
	
	//게시물관리 파일 리스트 삭제
	public int deleteFileList(List<String> list) throws Exception{
		return boardManageDao.deleteFileList(list);
	}
	
	//게시물관리 파일 삭제
	public int deleteFileInfo(String boardId) throws Exception{
		return boardManageDao.deleteFileInfo(boardId);
	}
	
	// 새로운 팝업 ID 채번
	public int getMaxPopupId() throws Exception {
		return boardManageDao.getMaxPopupId();
	}
	
	// 팝업 저장
	public int setPopupSave(Map<String, Object> map) throws Exception {
		return boardManageDao.setPopupSave(map);
	}
	
	// 팝업 수정
	public int setPopupModify(Map<String, Object> map) throws Exception {
		return boardManageDao.setPopupModify(map);
	}
	
	// 팝업 삭제
	public int setPopupDelete(Map<String, Object> map) throws Exception {
		return boardManageDao.setPopupDelete(map);
	}
	
	// 팝업 파일등록여부 체크
	public FileDto getPopupFileInfo(String fileId) throws Exception {
		return boardManageDao.getPopupFileInfo(fileId);
	}
	
	// 팝업 파일등록
	public int savePopupFileInfo(FileDto fileDto) throws Exception {
		return boardManageDao.savePopupFileInfo(fileDto);
	}
	
	// 팝업 파일수정
	public int updatePopupFileInfo(FileDto fileDto) throws Exception {
		return boardManageDao.updatePopupFileInfo(fileDto);
	}
	
	// 팝업 파일삭제
	public int deletePopupFileInfo(String fileId) throws Exception {
		return boardManageDao.deletePopupFileInfo(fileId);
	}
	
	//팝업 리스트 조회
		public List<PopupDto> getPopupList(Map<String, Object> map) throws Exception{
			return boardManageDao.getPopupList(map);
		}
		
		//팝업 리스트 총 갯수
		public int getPopupListCount(Map<String, Object> map) throws Exception{
			return boardManageDao.getPopupListCount(map);
		}
		
		//팝업 조회
		public PopupDto getPopupInfo(PopupDto popupDto)  throws Exception{
			return boardManageDao.getPopupInfo(popupDto);
		}
		
		//FileId 이용하여 File 정보 조회
		public FileDto getFileInfoByFileId(String fileId) throws Exception{
			return  boardManageDao.getFileInfoByFileId(fileId);
		}
}
