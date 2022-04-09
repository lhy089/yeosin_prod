package com.yeosin.admin;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeosin.board.BoardDto;
import com.yeosin.board.FileDto;

@Repository("boardManageDao")
public class BoardManageDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String nameSpace = "com.yeosin.admin.BoardManageDao.";

	//게시물관리 리스트
	public List<BoardDto> getBoardList(BoardDto boardDto) throws Exception {
		return sqlSession.selectList(nameSpace + "getBoardList", boardDto);
	}
	
	//게시물관리 총 갯수
	public int countBoardListTotal(BoardDto boardDto) throws Exception {
		return sqlSession.selectOne(nameSpace + "countBoardListTotal", boardDto);
	}
	
	//게시물관리 파일 조회
	public FileDto getFileInfo(String boardId) throws Exception {
		return sqlSession.selectOne(nameSpace + "getFileInfo", boardId);
	}
	
	//게시물관리 게시판 조회
	public BoardDto getBoardInfo(BoardDto boardDto) throws Exception{
		return sqlSession.selectOne(nameSpace + "getBoardInfo", boardDto);
	}
	
	//게시물관리 게시판 업데이트
	public int setBoardInfo(BoardDto boardDto) throws Exception{
		return sqlSession.update(nameSpace + "setBoardInfo", boardDto);
	}
	
	//게시물관리 파일 업데이트
	public int setFileInfo(FileDto fileDto) throws Exception{
		return sqlSession.update(nameSpace + "setFileInfo", fileDto);
	}
	
	//Board테이블 boardId컬럼 MaxNumber 조회
	public int getMaxBoardId() throws Exception{
		return sqlSession.selectOne(nameSpace + "getMaxBoardId");
	}
	
	//File테이블 fileId컬럼 MaxNumber 조회
	public int getMaxFileId() throws Exception{
		return sqlSession.selectOne(nameSpace + "getMaxFileId");
	}
	
	//Board테이블 boardType별 boardSequence컬럼 MaxNumber 조회
	public int getMaxBoardSequence(BoardDto boardDto) throws Exception{
		return sqlSession.selectOne(nameSpace + "getMaxBoardSequence", boardDto);
	}
	
	//게시물관리 게시판 추가하기
	public int insertBoardInfo(BoardDto boardDto) throws Exception{
		return sqlSession.update(nameSpace + "insertBoardInfo", boardDto);
	}
	
	//게시물관리 파일 추가하기
	public int insertFileInfo(FileDto fileDto) throws Exception{
		return sqlSession.update(nameSpace + "insertFileInfo", fileDto);
	}
	
	//게시물관리 게시판 리스트 삭제
	public int deleteBoardList(List<String> list) throws Exception {
		return sqlSession.delete(nameSpace + "deleteBoardList", list);
	}
	
	//게시물관리 파일 리스트 삭제
	public int deleteFileList(List<String> list) throws Exception{
		return sqlSession.delete(nameSpace + "deleteFileList", list);
	}
	
	//게시물관리 파일 삭제
	public int deleteFileInfo(String boardId) throws Exception{
		return sqlSession.delete(nameSpace + "deleteFileInfo", boardId); 
	}
	
	// 새로운 팝업 ID 채번
	public int getMaxPopupId() throws Exception {
		return sqlSession.selectOne(nameSpace + "getMaxPopupId");
	}
	
	// 팝업 저장
	public int setPopupSave(Map<String, Object> map) throws Exception {
		return sqlSession.insert(nameSpace + "setPopupSave", map);
	}
	
	// 팝업 수정
	public int setPopupModify(Map<String, Object> map) throws Exception {
		return sqlSession.update(nameSpace + "setPopupModify", map);
	}
	
	// 팝업 삭제
	public int setPopupDelete(Map<String, Object> map) throws Exception {
		return sqlSession.delete(nameSpace + "setPopupDelete", map);
	}
	
	// 팝업 파일등록여부 체크
	public FileDto getPopupFileInfo(String fileId) throws Exception {
		return sqlSession.selectOne(nameSpace + "getPopupFileInfo", fileId);
	}
	
	// 팝업 파일등록
	public int savePopupFileInfo(FileDto fileDto) throws Exception {
		return sqlSession.insert(nameSpace + "savePopupFileInfo", fileDto);
	}
	
	// 팝업 파일수정
	public int updatePopupFileInfo(FileDto fileDto) throws Exception {
		return sqlSession.update(nameSpace + "updatePopupFileInfo", fileDto);
	}
	
	// 팝업 파일삭제
	public int deletePopupFileInfo(String fileId) throws Exception {
		return sqlSession.delete(nameSpace + "deletePopupFileInfo", fileId);
	}
}
