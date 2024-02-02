package com.yeosin.apply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.yeosin.board.FileDto;
import com.yeosin.user.EduCompletionDao;
import com.yeosin.user.UserDto;

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
	
	@Autowired
	private SubjectDao subjectDao;
	
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
		ExamDto examInfoForMain = examDao.getExamInfoByGradeDate();
		
		if(examInfoForMain == null) {
			examInfoForMain = examDao.getExamInfoByReceiptDate();
		}
		return examInfoForMain;
	}
	
	public List<ExamDto> getExamList() throws Exception {
		return examDao.getExamList();
	}
	
	public ExamDto getExamInfo(String examId) throws Exception {
		return examDao.getExamInfo(examId);
	}
	
	public ExamDto getExamInfo2(String examId) throws Exception{
		ExamDto examDto = examDao.getExamInfo2(examId);
		
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
	
	public String getIsValidCertDate(Map<String, Object> map) throws Exception {
		return eduDao.getIsValidCertDate(map);
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
	
	public int setCancelReceipt(Map<String, String> map) throws Exception{
		return applyDao.setCancelReceipt(map);
	}
	
	public String getIsCancelReceipt(Map<String, Object> map) throws Exception{
		return applyDao.getIsCancelReceipt(map);
	}
	
	public int getLeftOverSeat(Map<String, Object> map) throws Exception {
	      return examZoneDao.getLeftOverSeat(map);
	}
	
	public String getSubjectName(String subjectId) throws Exception {
		return subjectDao.getSubjectName(subjectId);
	}
	
	public int setDeleteReceiptInfo(String receiptId) throws Exception {
		return applyDao.setDeleteReceiptInfo(receiptId);
	}
	
	public List<ApplyDto> selectPassUser(String examDate) throws Exception {
		return applyDao.selectPassUser(examDate);
	}
	
	public List<SubjectDto> getSujectListByExamId(String examId) throws Exception {
		return applyDao.getSujectListByExamId(examId);
	}
	
	public String getLocalFileName(String localFileName) throws Exception{
		return applyDao.getLocalFileName(localFileName);
	}
	
	public FileDto getFileInfo(String localFileName) throws Exception{
		return applyDao.getFileInfo(localFileName);
	}
	
	public String getAmtValidCheck(ExamDto examDto) throws Exception {
        return this.examDao.getAmtValidCheck(examDto);
    }
}
