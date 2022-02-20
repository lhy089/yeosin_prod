package com.yeosin.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeosin.user.UserDto;

@Service
public class UserManageService {

	@Autowired	
	private UserManageDao userManageDao;
	
	
	// 지역 리스트 조회(조회조건)
	public List<UserDto> getUserInfo(UserDto userDto) throws Exception {
		return userManageDao.getUserInfo(userDto);
	}
}
