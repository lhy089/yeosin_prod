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
	
	// 구 리스트 조회(조회조건)
	public List<ExamZoneDto> getConditionLocalDetailList() throws Exception {
		return applyManageDao.getConditionLocalDetailList();
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
	public List<ExamZoneDto> getApplyListByExamZone(Map<String, Object> map) throws Exception {
		return applyManageDao.getApplyListByExamZone(map);
	}
	
	// 원서접수 리스트 조회(고사장별) - 엑셀
	public List<ExamZoneDto> getApplyListByExamZoneForExcel(Map<String, Object> map) throws Exception {
		return applyManageDao.getApplyListByExamZoneForExcel(map);
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
	
	// 채점표리스트  조회
	public List<ApplyDto> getScorecardList(Map<String, Object> map) throws Exception{
		return applyManageDao.getScorecardList(map);
	}
	
	// 채점표리스트 총 갯수
	public int getScorecardListCount(Map<String, Object> map) throws Exception {
		return applyManageDao.getScorecardListCount(map);
	}
	
	// 성적처리 시험리스트 조회
	public List<ExamDto> getExamListForGradeRegistration() throws Exception {
		return applyManageDao.getExamListForGradeRegistration();
	}
	
	// 고사장 리스트 조회
	public List<ExamZoneDto> getExamZoneList(Map<String, Object> map) throws Exception {
		return applyManageDao.getExamZoneList(map);
	}
	
	// 고사장 리스트 총 갯수 조회
	public int getExamZoneListCount(Map<String, Object> map) throws Exception {
		return applyManageDao.getExamZoneListCount(map);
	}
	
	// 지역에 해당하는 구 조회
	public List<ExamZoneDto> getLocalDetailListByLocal(Map<String, Object> map) throws Exception {
		return applyManageDao.getLocalDetailListByLocal(map);
	}
	
	// 고사장 ID에 따른 1개의 고사장 데이터 조회
	public ExamZoneDto getExamZone(Map<String, Object> map) throws Exception {
		return applyManageDao.getExamZone(map);
	}
	
	// 고사장 ID MaxNumber 조회
	public int getMaxExamZoneId() throws Exception {
		return applyManageDao.getMaxExamZoneId();
	}
	
	// 고사장 저장
	public int setExamZoneSave(Map<String, Object> map) throws Exception {
		return applyManageDao.setExamZoneSave(map);
	}
	
	// 고사장 수정
	public int setExamZoneModify(Map<String, Object> map) throws Exception {
		return applyManageDao.setExamZoneModify(map);
	}
	
	// 고사장 삭제
	public int setExamZoneDelete(Map<String, Object> map) throws Exception {
		return applyManageDao.setExamZoneDelete(map);
	}
	
	//시험일정등록 고사장 리스트 조회
	public List<ExamZoneDto> getExamZoneListByExamRegister() throws Exception {
		return applyManageDao.getExamZoneListByExamRegister();
	}
	
	//시험일정등록 과목 리스트 조회
	public List<SubjectDto> getSubjectListByExamRegister() throws Exception{
		return applyManageDao.getSubjectListByExamRegister();
	}
	
	// 시험명 리스트 조회(조회조건)
	public List<ExamDto> getExamNameListByYear(Map<String, Object> map) throws Exception {
		return applyManageDao.getExamNameListByYear(map);
	}

	// 시험차수 리스트 조회(조회조건)
	public List<ExamDto> getExamDegreeListByExamName(Map<String, Object> map) throws Exception {
		return applyManageDao.getExamDegreeListByExamName(map);
	}

	// 시험일정 리스트 조회
	public List<ExamDto> getExamList(Map<String, Object> map) throws Exception {
		return applyManageDao.getExamList(map);
	}
	
	// 성적 처리 여부 확인 
	public int getGradeCntByExamId(String examId) throws Exception{
		return applyManageDao.getGradeCntByExamId(examId);
	}

	// 시험일정 리스트 총 갯수 조회
	public int getExamListCount(Map<String, Object> map) throws Exception {
		return applyManageDao.getExamListCount(map);
	}

	// 시험 삭제
	public int setExamDelete(Map<String, Object> map) throws Exception {
		return applyManageDao.setExamDelete(map);
	}
	
	// 해당 시험에 접수가 1건이라도 되어있는지 확인
	public String getContainApply(String examList) throws Exception {
		return applyManageDao.getContainApply(examList);
	}
	
	// 시험 ID MaxNumber 조회
	public int getMaxExamId() throws Exception {
		return applyManageDao.getMaxExamId();
	}
	
	// 시험일정등록(시험테이블)
	public int registerExam(ExamDto examDto) throws Exception{
		return applyManageDao.registerExam(examDto);
	}
	
	// 시험일정등록(EXAMANDSUBJECTREL 테이블)
	public int registerExamAndSubjectRel(Map<String, Object> map) throws Exception{
		return applyManageDao.registerExamAndSubjectRel(map);
	}
	
	// 시험일정등록(EXAMANDEXAMZONEREL 테이블)
	public int registerExamAndExamZoneRel(Map<String, Object> map) throws Exception{
		return applyManageDao.registerExamAndExamZoneRel(map);
	}

	// 시험차수 리스트 조회(조회조건) - 원서접수현황용(원서별, 고사장별)
	public List<ExamDto> getConditionExamYearAndDegreeList() throws Exception {
		return applyManageDao.getConditionExamYearAndDegreeList();
	}
	
	// 취소여부 리스트 조회(조회조건)
	public List<ApplyDto> getConditionIsCancelList() throws Exception {
		return applyManageDao.getConditionIsCancelList();
	}
	
	// 고사장의 교실수, 교실당 인원수 조회
	public ExamZoneDto getExamZoneByExamRoomCntAndExamRoomUserCnt(Map<String, Object> map) throws Exception {
		return applyManageDao.getExamZoneByExamRoomCntAndExamRoomUserCnt(map);
	}
	
	// 시험 ID에 대한 시험데이터 조회
	public ExamDto getExamInfo(String examId) throws Exception {
		return applyManageDao.getExamInfo(examId);
	}
	
	// 시험 ID에 대한 고사장 데이터 조회
	public List<ExamAndExamzoneRelDto> getExamZoneListByExamModify(String examId) throws Exception {
		return applyManageDao.getExamZoneListByExamModify(examId);
	}
	
	// 시험 ID에 대한 시험영역 데이터 조회
	public List<ExamAndSubjectRelDto> getSubjectListByExamModify(String examId) throws Exception {
		return applyManageDao.getSubjectListByExamModify(examId);
	}
	
	// 시험 ID에 대한 수정
	public int modifyExam(ExamDto examDto) throws Exception {
		return applyManageDao.modifyExam(examDto);
	}
	
	// 시험일정등록(EXAMANDEXAMZONEREL 테이블)
	public int deleteExamAndExamZoneRel(Map<String, Object> map) throws Exception{
		return applyManageDao.deleteExamAndExamZoneRel(map);
	}
   
	// 시험일정등록(EXAMANDSUBJECTREL 테이블)
	public int deleteExamAndSubjectRel(Map<String, Object> map) throws Exception{
		return applyManageDao.deleteExamAndSubjectRel(map);
	}
	
	// 현재 접수 진행 중인 시험 id 찾기
	public String getOpenReceiptExamId() throws Exception {
		return applyManageDao.getOpenReceiptExamId();
	}

	// 고사장 수정 후 ExamAndExamZoneRel update
	public int modifyExamAndExamZoneRel(Map<String, Object> map) throws Exception {
		return applyManageDao.modifyExamAndExamZoneRel(map);
	}
	
	// 고사장 파일등록여부 체크
	public FileDto getExamZoneFileInfo(String fileId) throws Exception {
		return applyManageDao.getExamZoneFileInfo(fileId);
	}
	
	// 고사장 약도등록
	public int saveExamZoneMapFileInfo(FileDto fileDto) throws Exception {
		return applyManageDao.saveExamZoneMapFileInfo(fileDto);
	}
	
	// 고사장 약도수정
	public int updateExamZoneMapFileInfo(FileDto fileDto) throws Exception {
		return applyManageDao.updateExamZoneMapFileInfo(fileDto);
	}
	
	// 고사장 약도삭제
	public int deleteExamZoneMapFileInfo(String fileId) throws Exception {
		return applyManageDao.deleteExamZoneMapFileInfo(fileId);
	}
}
