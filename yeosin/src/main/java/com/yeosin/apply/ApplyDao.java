package com.yeosin.apply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("applyDao")
public class ApplyDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String nameSpace = "com.yeosin.apply.ApplyDao.";
	
	public List<ApplyDto> getApplyList() throws Exception
	{
		return sqlSession.selectList(nameSpace + "GetApplyList");
	}
}
