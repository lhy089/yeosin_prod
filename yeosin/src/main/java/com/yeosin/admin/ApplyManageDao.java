package com.yeosin.admin;

import com.yeosin.apply.*;
import com.yeosin.board.*;
import com.yeosin.user.*;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("applyManageDao")
public class ApplyManageDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String nameSpace = "com.yeosin.admin.ApplyManageDao.";

	// 지역 리스트 조회(조회조건)
	public List<ExamZoneDto> getConditionLocalList() throws Exception {
		return sqlSession.selectList(nameSpace + "getConditionLocalList");
	}
	
	// 종목 리스트 조회(조회조건)
	public List<SubjectDto> getConditionSubjectList() throws Exception {
		return sqlSession.selectList(nameSpace + "getConditionSubjectList");
	}
	
	// 시험회차 리스트 조회(조회조건)
	public List<ExamDto> getConditionExamYearList() throws Exception {
		return sqlSession.selectList(nameSpace + "getConditionExamYearList");
	}
	
	// 원서접수 리스트 조회(원서별)
	public List<ApplyDto> getApplyListByDocument(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(nameSpace + "getApplyListByDocument", map);
	}
	
	// 원서접수 리스트 조회(고사장별)
	public List<ApplyDto> getApplyListByExamZone(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(nameSpace + "getApplyListByExamZone", map);
	}
	
	// 시험, 고사장에 접수된 총 접수번호 리스트
	public List<Object> getTotalReceiptIdByExamZone(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(nameSpace + "getTotalReceiptIdByExamZone", map);
	}
	
	// 좌석배치 확정 업데이트
	public int setExamZoneSeatConfirm(Map<String, Object> map) throws Exception {
		return sqlSession.update(nameSpace + "setExamZoneSeatConfirm", map);
	}
	
}
