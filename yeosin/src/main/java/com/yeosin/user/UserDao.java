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
	
	public int UpdateLastConnectDate(UserDto user) {
		return this.sqlSession.update(namespace + "UpdateLastConnectDate", user);
	}
	
	public UserDto getLoginUserInfo1(UserDto user) {
		return this.sqlSession.selectOne(namespace + "getLoginUserInfo1", user);
	}
	
	public int insertUserInfo(UserDto user) {
		return this.sqlSession.insert(namespace + "insertUserInfo", user);
	}
	
	public int updateUserInfo(UserDto user) {
		return this.sqlSession.update(namespace + "updateUserInfo", user);
	}
	
	public int withdrawUser(String userId) {
		return this.sqlSession.update(namespace + "withdrawUser", userId);
	}
	
	public String findUserId(UserDto userDto) {
		return this.sqlSession.selectOne(namespace + "findUserId", userDto);
	}
	public int findUserInfoCnt(UserDto userDto) {
		return this.sqlSession.selectOne(namespace + "findUserInfoCnt", userDto);
	}
	public String findUserIdByCert(UserDto userDto) {
		return this.sqlSession.selectOne(namespace + "findUserIdByCert", userDto);
	}
	public int updateUserPassword(UserDto userInfo) {
		return this.sqlSession.update(namespace + "updateUserPassword", userInfo);
	}
	public void updateUserStatus(UserDto userInfo) {
		sqlSession.update(namespace + "updateUserStatus", userInfo );
	}
	public String getUserByCIDI(UserDto user) {
		return this.sqlSession.selectOne(namespace + "getUserByCIDI", user);
	}

	public int dormantAccountProcessing() {
		return this.sqlSession.update(namespace + "dormantAccountProcessing");
	}
	
	public int secessionDateProcessing() {
		return this.sqlSession.update(namespace + "secessionDateProcessing");
	}
	public UserDto findUserInfo(UserDto user) {
		return this.sqlSession.selectOne(namespace + "findUserInfo", user);
	}
}
