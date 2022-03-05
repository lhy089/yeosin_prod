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
	
	// 구 리스트 조회(조회조건)
	public List<ExamZoneDto> getConditionLocalDetailList() throws Exception {
		return sqlSession.selectList(nameSpace + "getConditionLocalDetailList");
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
	
	// 원서접수 리스트 총 갯수 조회(원서별)
	public int getApplyListByDocumentCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(nameSpace + "getApplyListByDocumentCount", map);
	}
	
	// 원서접수 리스트 총 갯수 조회(원서별)
	public int getApplyListByExamZoneCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(nameSpace + "getApplyListByExamZoneCount", map);
	}
	
	// 채점리스트 조회
	public List<ApplyDto> getScorecardList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(nameSpace + "getScorecardList", map);
	}
	
	// 채점표리스트 총 갯수
	public int getScorecardListCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(nameSpace + "getScorecardListCount", map);
	}

	// 성적처리 시험리스트 조회
	public List<ExamDto> getExamListForGradeRegistration() throws Exception {
		return sqlSession.selectList(nameSpace + "getExamListForGradeRegistration");
	}
	
	public int insertExcelData(List<GradeDto> gradeList) throws Exception {
		return sqlSession.update(nameSpace + "insertExcelData", gradeList);
	}
	
	public String getReceiptIdByStudentCode(String studentCode) throws Exception {
		return sqlSession.selectOne(nameSpace + "getReceiptIdByStudentCode", studentCode);
	}
	
	public ExamDto getExamInfoByExamId(String examId) throws Exception {
		return sqlSession.selectOne(nameSpace + "getExamInfoByExamId", examId);
	}
	
	public int updateGradeStatus(String examId) throws Exception {
		return sqlSession.update(nameSpace + "updateGradeStatus", examId);
	}
	
	// 고사장 리스트 조회
	public List<ExamZoneDto> getExamZoneList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(nameSpace + "getExamZoneList", map);
	}
	
	// 고사장 리스트 총 갯수 조회
	public int getExamZoneListCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(nameSpace + "getExamZoneListCount", map);
	}
	
	// 지역에 해당하는 구 조회
	public List<ExamZoneDto> getLocalDetailListByLocal(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(nameSpace + "getConditionLocalDetailList", map);
	}
	
	// 고사장 ID에 따른 1개의 고사장 데이터 조회
	public ExamZoneDto getExamZone(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(nameSpace + "getExamZone", map);
	}
	
	// 고사장 ID MaxNumber 조회
	public int getMaxExamZoneId() throws Exception {
		return sqlSession.selectOne(nameSpace + "getMaxExamZoneId");
	}
	
	// 고사장 저장
	public int setExamZoneSave(Map<String, Object> map) throws Exception {
		return sqlSession.insert(nameSpace + "insertExamZone", map);
	}
	
	// 고사장 수정
	public int setExamZoneModify(Map<String, Object> map) throws Exception {
		return sqlSession.update(nameSpace + "updateExamZone", map);
	}
	
	// 고사장 삭제
	public int setExamZoneDelete(Map<String, Object> map) throws Exception {
		return sqlSession.delete(nameSpace + "deleteExamZone", map);
	}
	
	// 시험일정등록 고사장 리스트 조회
	public List<ExamZoneDto> getExamZoneListByExamRegister() throws Exception {
		return sqlSession.selectList(nameSpace + "getExamZoneListByExamRegister");
	}
}
