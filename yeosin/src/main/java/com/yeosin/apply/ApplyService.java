package com.yeosin.apply;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
	
	@Autowired	
	private ApplyDao applyDao;
	
	public List<ApplyDto> getApplyList(String userId) throws Exception {
		return applyDao.getApplyList(userId);
	}
	
	public List<ApplyDto> getDetailApplyList(Map<String, Object> map) throws Exception {
		return applyDao.getDetailApplyList(map);
	}
}
