package com.yeosin.apply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("examAndExamzoneRelDao")
public class ExamAndExamzoneRelDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String nameSpace = "com.yeosin.apply.ExamAndExamzoneRelDao.";
	
	public List<ExamAndExamzoneRelDto> getExamLocalList() {
	      return this.sqlSession.selectList(nameSpace + "getExamLocalList");
	}
	
}
