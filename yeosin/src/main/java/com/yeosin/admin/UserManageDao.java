package com.yeosin.admin;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeosin.user.UserDto;

@Repository
public class UserManageDao {
	
	/** The namespace. */
	private String namespace = "com.yeosin.user.UserManageDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<UserDto> getUserInfo(UserDto userDto) {
		return this.sqlSession.selectList(namespace + "getUserInfo", userDto);
	}

}
