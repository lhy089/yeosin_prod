package com.yeosin.apply;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("applyDao")
public class ApplyDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String nameSpace = "com.yeosin.apply.ApplyDao.";

	public List<ApplyDto> getAcceptList(ApplyDto applyDto) throws Exception {
		return sqlSession.selectList(nameSpace + "getAcceptList", applyDto);
	}
	
	public int countAcceptListTotal(String userId) throws Exception{
		return sqlSession.selectOne(nameSpace + "countAcceptListTotal", userId);
	}
	
	public List<ApplyDto> getTicketList(ApplyDto applyDto) throws Exception {
		return sqlSession.selectList(nameSpace + "getTicketList", applyDto);
	}
	
	public int countTicketListTotal(String userId) throws Exception{
		return sqlSession.selectOne(nameSpace + "countTicketListTotal", userId);
	}
	
	public List<ApplyDto> getDetailApplyList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(nameSpace + "GetDetailApplyList", map);
	}
	
	public ApplyDto getDetailApplyInfo(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(nameSpace + "getDetailApplyInfo", map);
	}
	
	public List<ApplyDto> getExamResult(String userId) throws Exception {
		return sqlSession.selectList(nameSpace + "getExamResult", userId);
	}
	
	public int countApplyReceptionStatusListTotal(String userId) throws Exception{
		return sqlSession.selectOne(nameSpace + "countApplyReceptionStatusListTotal", userId);
	}
	
	public List<ApplyDto> getApplyReceptionStatusList(ApplyDto applyDto) throws Exception {
		return sqlSession.selectList(nameSpace + "getApplyReceptionStatusList", applyDto);
	}
	
	public String getMaxReceiptNumber() throws Exception {
		return sqlSession.selectOne(nameSpace + "getMaxReceiptNumber");
	}
	
	public int setReceiptInfo(ApplyDto applyDto) throws Exception {
		return sqlSession.insert(nameSpace + "setReceiptInfo", applyDto);
	}
	
	public int getIsReceipt(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(nameSpace + "getIsReceipt", map);
	}
	
	public int setPaymentInfo(ApplyDto applyDto) throws Exception {
		return sqlSession.update(nameSpace + "setPaymentInfo", applyDto);
	}
	
	public int setCancelReceipt(Map<String, String> map) throws Exception {
		return sqlSession.update(nameSpace + "setCancelReceipt", map);
	}
	
	public String getIsCancelReceipt(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(nameSpace + "getIsCancelReceipt", map);
	}
	
	public int setDeleteReceiptInfo(String receiptId) throws Exception {
		return sqlSession.delete(nameSpace + "setDeleteReceiptInfo", receiptId);
	}
}
