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
	
	public UserDto getLoginUserInfo1(UserDto user) {
		return userDao.getLoginUserInfo1(user);
	}
	
	public int insertUserInfo(UserDto user) {
		return userDao.insertUserInfo(user);
	}
	
	public int updateUserInfo(UserDto user) {
		return userDao.updateUserInfo(user);
	}
	
	public int withdrawUser(String userId) {
		return userDao.withdrawUser(userId);
	}
	
	public String findUserId(UserDto userDto) {
		
		String userId = userDao.findUserId(userDto);
		
		if(userId != null)
		{
			StringBuilder tempUserId = new StringBuilder(userId);
		
			for(int i = 1; userId.length() - 4 < userId.length() - i; i++)
				tempUserId.setCharAt(userId.length() - i, '*');
		
			userId = tempUserId.toString();
		}
		
		return userId;
	}
}
