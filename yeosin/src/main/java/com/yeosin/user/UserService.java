package com.yeosin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired	
	private UserDao userDao;
	
	@Autowired
	private EduCompletionDao eduCompletionDao;
	
	public String getLoginUser(String userId) throws Exception  {
		return userDao.getLoginUser(userId);
	}
	
	public UserDto getLoginUserInfo(UserDto user) throws Exception  {
		return userDao.getLoginUserInfo(user);
	}
	
	public int UpdateLastConnectDate(UserDto user) throws Exception {
		return userDao.UpdateLastConnectDate(user);
	}
	
	public UserDto getLoginUserInfo1(UserDto user) throws Exception  {
		return userDao.getLoginUserInfo1(user);
	}
	
	public int insertUserInfo(UserDto user) throws Exception  {
		return userDao.insertUserInfo(user);
	}
	
	public int updateUserInfo(UserDto user) throws Exception  {
		return userDao.updateUserInfo(user);
	}
	
	public int withdrawUser(String userId) throws Exception  {
		return userDao.withdrawUser(userId);
	}

	public String findUserIdByCert(UserDto userDto) throws Exception   {
		return userDao.findUserIdByCert(userDto);
	}
	
	public int updateUserPassword(UserDto userInfo) throws Exception   {
		return userDao.updateUserPassword(userInfo);
	}
	
	public void updateUserStatus(UserDto userInfo) throws Exception {
		userDao.updateUserStatus(userInfo);
	}
	
	public int findUserInfoCnt(UserDto userInfo) throws Exception {
		return userDao.findUserInfoCnt(userInfo);
	}
	
	public String findUserId(UserDto userDto) throws Exception   {
		
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
	
	public String getEduCompletionInfo(EduCompletionDto eduCompletionInfo) throws Exception {
		return eduCompletionDao.getEduCompletionInfo(eduCompletionInfo);
	}
	
	public int updateEduComepletionInfo(EduCompletionDto eduCompletionInfo) throws Exception {
		return eduCompletionDao.updateEduComepletionInfo(eduCompletionInfo);
	}
	
	public int insertEduComepletionInfo(EduCompletionDto eduCompletionInfo) throws Exception {
		return eduCompletionDao.insertEduComepletionInfo(eduCompletionInfo);
	}
	public String getUserByCIDI(UserDto userDto) throws Exception {
		return userDao.getUserByCIDI(userDto);
	}
	public int insertAndUpdateEduComepletionInfo(List<EduCompletionDto> eduCompletionList) throws Exception {
		return eduCompletionDao.insertAndUpdateEduComepletionInfo(eduCompletionList);
	}
	
	//휴면계정처리
	public int dormantAccountProcessing() throws Exception{
		return userDao.dormantAccountProcessing();
	}
}
