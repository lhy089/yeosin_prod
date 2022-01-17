package com.yeosin.apply;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeosin.member.UserDao;
import com.yeosin.member.UserDto;

@Service
public class ApplyService {
	
	@Autowired	
	private ApplyDao applyDao;
	
	public List<ApplyDto> getApplyList(String userId) throws Exception {
		return applyDao.getApplyList(userId);
	}
	
}