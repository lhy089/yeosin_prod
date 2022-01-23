package com.yeosin.apply;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
	
	@Autowired	
	private ApplyDao applyDao;
	
	@Autowired	
	private ExamDao examDao;
	
	public List<ApplyDto> getApplyList(String userId) throws Exception {
		return applyDao.getApplyList(userId);
	}
	
	public List<ApplyDto> getDetailApplyList(Map<String, Object> map) throws Exception {
		return applyDao.getDetailApplyList(map);
	}
	
	public ApplyDto getDetailApplyInfo(Map<String, Object> map) throws Exception {
		return applyDao.getDetailApplyInfo(map);
	}
	
	public ExamDto getExamInfoForMain() throws Exception {
		return examDao.getExamInfoForMain();
	}
	
	public List<ExamDto> getExamList() throws Exception {
		return examDao.getExamList();
	}
	
	public ExamDto getExamInfo(String examId) throws Exception {
		return examDao.getExamInfo(examId);
	}
	
	public List<ApplyDto> getExamResult(String userId) throws Exception {
		return applyDao.getExamResult(userId);
	}
	
	
}
