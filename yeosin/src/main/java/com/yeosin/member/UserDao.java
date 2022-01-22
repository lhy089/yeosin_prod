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
	
	public String getLoginUser(String userId) {
		return this.sqlSession.selectOne(namespace + "getLoginUser", userId);
	}
	
	public UserDto getLoginUserInfo(UserDto user) {
		return this.sqlSession.selectOne(namespace + "getLoginUserInfo", user);
	}
}
