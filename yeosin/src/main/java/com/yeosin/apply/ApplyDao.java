package com.yeosin.apply;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("applyDao")
public class ApplyDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String nameSpace = "com.yeosin.apply.ApplyDao.";
	
	// 로그인 유저별 원서접수 조회
	public List<ApplyDto> getApplyList(ApplyDto applyDto) throws Exception {
		return sqlSession.selectList(nameSpace + "GetApplyList", applyDto);
	}
	
	//회원이 접수한 총 갯수
	public int countApplyListTotal(String userId) throws Exception{
		return sqlSession.selectOne(nameSpace + "countApplyListTotal", userId);
	}
	
	// 로그인 유저의 접수별 상세현황 조회
	public List<ApplyDto> getDetailApplyList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(nameSpace + "GetDetailApplyList", map);
	}
	
	public ApplyDto getDetailApplyInfo(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(nameSpace + "getDetailApplyInfo", map);
	}
	
	public List<ApplyDto> getExamResult(String userId) throws Exception {
		return sqlSession.selectList(nameSpace + "getExamResult", userId);
	}
	
	
	
}
