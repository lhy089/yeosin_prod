package com.yeosin.apply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("SubjectDao")
public class SubjectDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String nameSpace = "com.yeosin.apply.SubjectDao.";
	
	public List<SubjectDto> getSubjectList() throws Exception {
		return sqlSession.selectList(nameSpace + "getSubjectList");
	}
	
	public String getSubjectName(String subjectId) throws Exception {
		return sqlSession.selectOne(nameSpace + "getSubjectName", subjectId);
	}
}
