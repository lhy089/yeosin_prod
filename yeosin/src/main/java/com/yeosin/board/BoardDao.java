package com.yeosin.board;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("boardDao")
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String nameSpace = "com.yeosin.board.BoardDao.";


	public List<BoardDto> getBoardList(BoardDto boardDto){
		return this.sqlSession.selectList(nameSpace + "getBoardList", boardDto);
	}
	
	public BoardDto getBoardInfo(BoardDto boardDto) {
		return this.sqlSession.selectOne(nameSpace + "getBoardInfo", boardDto);
	}
	
	public void updateHitCnt(BoardDto boardDto) {
		this.sqlSession.update(nameSpace + "UpdateHitCnt", boardDto);
	}
	
	public List<BoardDto> getNoticeListForMain() {
		return this.sqlSession.selectList(nameSpace + "getNoticeListForMain");
	}
	
	public int countBoardListTotal(BoardDto boardDto) {
		return this.sqlSession.selectOne(nameSpace + "countBoardListTotal", boardDto);
	}	
	
	public FileDto getFileInfo(String fileId) {
		return this.sqlSession.selectOne(nameSpace + "getFileInfo", fileId);
	}
}
