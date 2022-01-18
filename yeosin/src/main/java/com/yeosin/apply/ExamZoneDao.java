package com.yeosin.apply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("examZoneDao")
public class ExamZoneDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String nameSpace = "com.yeosin.apply.ExamZoneDao.";
	
	/*public List<ApplyDto> getApplyList(String userId) throws Exception {
		return sqlSession.selectList(nameSpace + "GetApplyList", userId);
	}*/

}
