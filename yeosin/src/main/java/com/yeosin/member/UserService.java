package com.yeosin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired	
	private UserDao userDao;
	
	public Boolean getLoginUser(UserDto user) {
		return userDao.getLoginUser(user);
	}
}
