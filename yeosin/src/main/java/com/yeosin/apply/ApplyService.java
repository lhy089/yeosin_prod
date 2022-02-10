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
	
	@Autowired
	private ExamAndExamzoneRelDao examAndExamzoneRelDao;
	
	public List<ApplyDto> getAcceptList(ApplyDto applyDto) throws Exception {
		return applyDao.getAcceptList(applyDto);
	}
	
	public List<ApplyDto> getTicketList(ApplyDto applyDto) throws Exception {
		return applyDao.getTicketList(applyDto);
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
	
	public ExamDto getExamInfoForApply3(String examId) throws Exception{
		ExamDto examDto = examDao.getExamInfoForApply3(examId);
		
		examDto.setReceiptStartDate(examDto.getReceiptStartDate().replace("AM", "오전")); ;
		examDto.setReceiptStartDate(examDto.getReceiptStartDate().replace("PM", "오후"));
		examDto.setReceiptEndDate(examDto.getReceiptEndDate().replace("AM", "오전")) ;
		examDto.setReceiptEndDate(examDto.getReceiptEndDate().replace("PM", "오후"));
		
		return examDto;
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
	
	public int countAcceptListTotal(String userId) throws Exception{
		return applyDao.countAcceptListTotal(userId);
	}
	
	public int countTicketListTotal(String userId) throws Exception{
		return applyDao.countTicketListTotal(userId);
	}
	
	public List<ExamZoneDto> getExamDetailList(String examId) throws Exception{
		return examZoneDao.getExamDetailList(examId);
	}
	
	public int countApplyReceptionStatusListTotal(String userId) throws Exception{
		return applyDao.countApplyReceptionStatusListTotal(userId);
	}
	
	public List<ApplyDto> getApplyReceptionStatusList(ApplyDto applyDto) throws Exception{
		return applyDao.getApplyReceptionStatusList(applyDto);
	}
	
	public String getMaxReceiptNumber() throws Exception{
		return applyDao.getMaxReceiptNumber();
	}
	
	public int setReceiptInfo(ApplyDto applyDto) throws Exception{
		return applyDao.setReceiptInfo(applyDto);
	}
	
	public int getIsReceipt(Map<String, Object> map) throws Exception{
		return applyDao.getIsReceipt(map);
	}
	
	public List<ExamAndExamzoneRelDto> getExamLocalList() throws Exception{
	    return examAndExamzoneRelDao.getExamLocalList();
	}
	   
	public List<ExamZoneDto> getExamDetailListByLocal(Map<String, Object> map) throws Exception{
		return examZoneDao.getExamDetailListByLocal(map);
	}
	
	public int setPaymentInfo(ApplyDto applyDto) throws Exception{
		return applyDao.setPaymentInfo(applyDto);
	}
}
