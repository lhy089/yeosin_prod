package com.yeosin.board;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("fileDao")
public class FileDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String nameSpace = "com.yeosin.board.FileDao.";
	
	/*public List<ApplyDto> getApplyList(String userId) throws Exception {
	return sqlSession.selectList(nameSpace + "GetApplyList", userId);
	}*/

}
