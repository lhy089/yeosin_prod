package com.yeosin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired	
	private UserDao userDao;
	
	public String getLoginUser(String userId) {
		return userDao.getLoginUser(userId);
	}
	
	public UserDto getLoginUserInfo(UserDto user) {
		return userDao.getLoginUserInfo(user);
	}
	
	public int insertUserInfo(UserDto user) {
		return userDao.insertUserInfo(user);
	}
}
