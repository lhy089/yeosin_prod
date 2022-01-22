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


	public List<BoardDto> getBoardList(String getBoardType){
		return this.sqlSession.selectList(nameSpace + "GetBoardList", getBoardType);
	}
	

	public BoardDto getBoardInfo(BoardDto boardDto) {
		return this.sqlSession.selectOne(nameSpace + "GetBoardInfo", boardDto);
	}
	
	public void updateHitCnt(BoardDto boardDto) {
		this.sqlSession.update(nameSpace + "UpdateHitCnt", boardDto);
	}
	
}
