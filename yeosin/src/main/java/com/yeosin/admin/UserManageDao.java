package com.yeosin.admin;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeosin.apply.ApplyDto;
import com.yeosin.user.EduCompletionDto;
import com.yeosin.user.EduCompletionHisDto;
import com.yeosin.user.UserDto;

@Repository
public class UserManageDao {
	
	/** The namespace. */
	private String namespace = "com.yeosin.admin.UserManageDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<UserDto> getUserInfo(Map<String, Object> map) throws Exception{
		return this.sqlSession.selectList(namespace + "getUserInfo", map);
	}
	
	public int countUserListTotal(Map<String, Object> map) throws Exception{
		return this.sqlSession.selectOne(namespace + "countUserListTotal", map);
	}
	
	public List<EduCompletionDto> getEduCompletionList(EduCompletionDto eduCompletionInfo) throws Exception {
		return sqlSession.selectList(namespace + "getEduCompletionList", eduCompletionInfo);
	}
	
	public int insertEduCompletionHis(EduCompletionHisDto eduCompletionHis) throws Exception {
		return sqlSession.insert(namespace + "insertEduCompletionHis", eduCompletionHis);
	}
	
	public List<EduCompletionHisDto> getEduCompletionHisList() throws Exception {
		return sqlSession.selectList(namespace + "getEduCompletionHisList");
	}
	
	public UserDto getUserInfoByUserId(String userId) throws Exception{
		return sqlSession.selectOne(namespace + "getUserInfoByUserId", userId);
	}
}
	