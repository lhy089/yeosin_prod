package com.yeosin.apply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("examDao")
public class ExamDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String nameSpace = "com.yeosin.apply.ExamDao.";
	
	/*public List<ApplyDto> getApplyList(String userId) throws Exception {
		return sqlSession.selectList(nameSpace + "GetApplyList", userId);
	}*/

}
