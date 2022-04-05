package com.yeosin.user;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EduCompletionDao {
	
	/** The namespace. */
	private String namespace = "com.yeosin.user.EduCompletionDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public String getEduCompletionInfo(EduCompletionDto eduCompletionInfo) throws Exception {
		return this.sqlSession.selectOne(namespace + "getEduCompletionInfo", eduCompletionInfo);
	}
	
	public String getIsCompleteEdu(Map<String, Object> map) throws Exception {
		return this.sqlSession.selectOne(namespace + "getisUserEdu", map);
	}
	
	public String getIsValidCertDate(Map<String, Object> map) throws Exception {
		return this.sqlSession.selectOne(namespace + "getIsValidCertDate", map);
	}
	
	public int updateEduComepletionInfo(EduCompletionDto eduCompletionInfo) throws Exception {
		return this.sqlSession.update(namespace + "updateEduComepletionInfo", eduCompletionInfo);
	}
	
	public int insertEduComepletionInfo(EduCompletionDto eduCompletionInfo) throws Exception {
		return this.sqlSession.insert(namespace + "insertEduComepletionInfo", eduCompletionInfo);
	}
	
	public int insertAndUpdateEduComepletionInfo(List<EduCompletionDto> eduCompletionList) throws Exception {
		return this.sqlSession.insert(namespace + "insertAndUpdateEduComepletionInfo", eduCompletionList);
	}
}
