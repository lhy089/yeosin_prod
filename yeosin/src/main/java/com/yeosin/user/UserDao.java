package com.yeosin.user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	/** The namespace. */
	private String namespace = "com.yeosin.user.UserDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public String getLoginUser(String userId) {
		return this.sqlSession.selectOne(namespace + "getLoginUser", userId);
	}
	
	public UserDto getLoginUserInfo(UserDto user) {
		return this.sqlSession.selectOne(namespace + "getLoginUserInfo", user);
	}
	
	public UserDto getLoginUserInfo1(UserDto user) {
		return this.sqlSession.selectOne(namespace + "getLoginUserInfo1", user);
	}
	
	public int insertUserInfo(UserDto user) {
		return this.sqlSession.insert(namespace + "insertUserInfo", user);
	}
	
	public int updateUserInfo(UserDto user) {
		return this.sqlSession.insert(namespace + "updateUserInfo", user);
	}
}