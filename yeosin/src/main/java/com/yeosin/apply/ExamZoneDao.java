package com.yeosin.apply;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("examZoneDao")
public class ExamZoneDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String nameSpace = "com.yeosin.apply.ExamZoneDao.";
	
	public List<ExamZoneDto> getExamZoneList(Map<String, Object> map) {
		return this.sqlSession.selectList(nameSpace + "getExamZoneList", map);
	}

	public String getExamZoneName(String examZoneId) {
		return this.sqlSession.selectOne(nameSpace + "getExamZoneName", examZoneId);
	}
	
	public List<ExamZoneDto> getExamDetailList(String examId) {
		return this.sqlSession.selectList(nameSpace + "getExamDetailList", examId);
	}
}
