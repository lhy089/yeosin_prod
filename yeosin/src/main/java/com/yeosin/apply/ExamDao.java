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
	
	public ExamDto getExamInfoForMain() {
		return this.sqlSession.selectOne(nameSpace + "getExamInfoForMain");
	}
	
	public List<ExamDto> getExamList() {
		return this.sqlSession.selectList(nameSpace + "getExamList");
	}
	
	public ExamDto getExamInfo(String examId) {
		return this.sqlSession.selectOne(nameSpace + "getExamInfo", examId);
	}
	
	public ExamDto getExamInfo2(String examId) {
		return this.sqlSession.selectOne(nameSpace + "getExamInfo2", examId);
	}
	
	/*public List<ApplyDto> getApplyList(String userId) throws Exception {
		return sqlSession.selectList(nameSpace + "GetApplyList", userId);
	}*/
	
	public String getAmtValidCheck(ExamDto examDto) {
        return (String)this.sqlSession.selectOne(nameSpace + "getAmtValidCheck", (Object)examDto);
    }
	
	public ExamDto getExamInfoByGradeDate() {
		return this.sqlSession.selectOne(nameSpace + "getExamInfoByGradeDate");
	}
    
    public ExamDto getExamInfoByReceiptDate() {
		return this.sqlSession.selectOne(nameSpace + "getExamInfoByReceiptDate");
	}

}
