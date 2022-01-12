package com.yeosin.apply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
	
	@Autowired	
	private ApplyDao applyDao;
	
	public List<ApplyDto> getApplyList() throws Exception
	{
		return applyDao.getApplyList();
	}
}
