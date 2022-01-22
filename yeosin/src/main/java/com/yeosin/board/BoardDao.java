package com.yeosin.board;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("boardDao")
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "com.yeosin.board.BoardDao.";

	public BoardDto viewBoard(BoardDto board) {
		return this.sqlSession.selectOne(namespace + "viewBoard", board);
	}
	
	
	/*public List<ApplyDto> getApplyList(String userId) throws Exception {
		return sqlSession.selectList(nameSpace + "GetApplyList", userId);
	}*/
	
}
