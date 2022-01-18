package com.yeosin.apply;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("localCenterDao")
public class LocalCenterDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String nameSpace = "com.yeosin.apply.LocalCenterDao.";
	
	/*public List<ApplyDto> getApplyList(String userId) throws Exception {
		return sqlSession.selectList(nameSpace + "GetApplyList", userId);
	}*/

}
