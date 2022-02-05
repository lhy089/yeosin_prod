package com.yeosin.apply;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeosin.user.EduCompletionDao;

@Service
public class ApplyService {
	
	@Autowired	
	private ApplyDao applyDao;
	
	@Autowired	
	private ExamDao examDao;
	
	@Autowired	
	private ExamZoneDao examZoneDao;
	
	@Autowired
	private EduCompletionDao eduDao;
	
	public List<ApplyDto> getApplyList(ApplyDto applyDto) throws Exception {
		return applyDao.getApplyList(applyDto);
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
	
	public String getIsCompleteEdu(Map<String, Object> map) throws Exception {
		return eduDao.getIsCompleteEdu(map);
	}
	
	public List<ExamZoneDto> getExamZoneList(Map<String, Object> map) throws Exception {
		return examZoneDao.getExamZoneList(map);
	}
	
	public String getExamZoneName(String examZoneId) throws Exception {
		return examZoneDao.getExamZoneName(examZoneId);
	}
	
	public int countApplyListTotal(String userId) throws Exception{
		return applyDao.countApplyListTotal(userId);
	}
	
	public List<ExamZoneDto> getExamDetailList() throws Exception{
		return examZoneDao.getExamDetailList();
	}
}
