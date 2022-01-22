package com.yeosin.user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EduCompletionDao {
	
	/** The namespace. */
	private String namespace = "com.yeosin.user.EduCompletionDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public String getEduCompletionInfo(String userId) {
		return this.sqlSession.selectOne(namespace + "getEduCompletionInfo", userId);
	}
}
