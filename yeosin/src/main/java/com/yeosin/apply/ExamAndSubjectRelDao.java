package com.yeosin.apply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ExamAndSubjectRelDao")
public class ExamAndSubjectRelDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String nameSpace = "com.yeosin.apply.ExamAndSubjectRelDao.";
	
	public List<ExamAndSubjectRelDto> getExamAndSubjectRel() throws Exception {
		return sqlSession.selectList(nameSpace + "getExamAndSubjectRel");
	}
}
