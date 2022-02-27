package com.yeosin.admin;

import com.yeosin.apply.*;
import com.yeosin.board.*;
import com.yeosin.user.*;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyManageService {
	
	@Autowired	
	private ApplyManageDao applyManageDao;
	
	// 지역 리스트 조회(조회조건)
	public List<ExamZoneDto> getConditionLocalList() throws Exception {
		return applyManageDao.getConditionLocalList();
	}
	
	// 종목 리스트 조회(조회조건)
	public List<SubjectDto> getConditionSubjectList() throws Exception {
		return applyManageDao.getConditionSubjectList();
	}
	
	// 시험회차 리스트 조회(조회조건)
	public List<ExamDto> getConditionExamYearList() throws Exception {
		return applyManageDao.getConditionExamYearList();
	}
	
	// 원서접수 리스트 조회(원서별)
	public List<ApplyDto> getApplyListByDocument(Map<String, Object> map) throws Exception {
		return applyManageDao.getApplyListByDocument(map);
	}
	
	// 원서접수 리스트 조회(고사장별)
	public List<ApplyDto> getApplyListByExamZone(Map<String, Object> map) throws Exception {
		return applyManageDao.getApplyListByExamZone(map);
	}
	
	// 시험, 고사장에 접수된 총 접수번호 리스트
	public List<Object> getTotalReceiptIdByExamZone(Map<String, Object> map) throws Exception {
		return applyManageDao.getTotalReceiptIdByExamZone(map);
	}

	// 좌석배치 확정 업데이트
	public int setExamZoneSeatConfirm(Map<String, Object> map) throws Exception {
		return applyManageDao.setExamZoneSeatConfirm(map);
	}
	
	// 원서접수 리스트 총 갯수 조회(원서별)
	public int getApplyListByDocumentCount(Map<String, Object> map) throws Exception {
		return applyManageDao.getApplyListByDocumentCount(map);
	}
	
	// 원서접수 리스트 총 갯수 조회(고사장)
	public int getApplyListByExamZoneCount(Map<String, Object> map) throws Exception {
		return applyManageDao.getApplyListByExamZoneCount(map);
	}
	
	// 성적처리 시험리스트 조회
	public List<ExamDto> getExamListForGradeRegistration() throws Exception {
		return applyManageDao.getExamListForGradeRegistration();
	}
}
