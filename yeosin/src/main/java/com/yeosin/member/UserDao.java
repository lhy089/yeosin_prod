package com.yeosin.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	/** The namespace. */
	private String namespace = "com.yeosin.member.UserDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public Boolean getLoginUser(UserDto user) {
		return "Y".equals(this.sqlSession.selectOne(namespace + "getLoginUser", user));
	}
}
