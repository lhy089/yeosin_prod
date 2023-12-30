<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="mup.mcash.module.common.McashCipher.*"%>
<%
	/*********************************************************************************
	* 결제 성공시 시스템 백단으로 호출되는 페이지로 결제처리(서비스제공) 용도
	* 결제처리(서비스제공) 성공 시 'SUCCESS' 실패 시 'FAIL' 출력
	* Okurl과 결제 결과 중복 처리 주의
	*********************************************************************************/

	String CASH_GB		= request.getParameter("CASH_GB"); 		/* 결제수단(CN)*/
	String Svcid		= request.getParameter("Svcid"); 		/* 서비스아이디 */
	String Mobilid		= request.getParameter("Mobilid"); 		/* 모빌리언스 거래번호 */
	String Signdate		= request.getParameter("Signdate"); 	/* 결제일자 */
	String Tradeid		= request.getParameter("Tradeid"); 		/* 상점거래번호 */
	String Prdtnm		= request.getParameter("Prdtnm"); 		/* 상품명 */
	String Prdtprice	= request.getParameter("Prdtprice");	/* 상품가격 */
	String Interest		= request.getParameter("Interest");		/* 할부개월수 */
	String Userid		= request.getParameter("Userid");		/* 사용자아이디*/
	String Username		= request.getParameter("Username");		/* 결제자명*/
	String Resultcd		= request.getParameter("Resultcd");		/* 결과코드 */
	String Resultmsg	= request.getParameter("Resultmsg");	/* 결과메세지 */
	String Payeremail	= request.getParameter("Payeremail");	/* 결제자 이메일 */
	String MSTR			= request.getParameter("MSTR");			/* 가맹점 전달 콜백변수 */
	String Cardnum		= request.getParameter("Cardnum");		/* 결제 카드번호 */
	String Cardcode		= request.getParameter("Cardcode");		/* 결제 카드코드 */
	String Cardname		= request.getParameter("Cardname");		/* 결제 카드사명 */
	String Apprno		= request.getParameter("Apprno");		/* 승인번호 */
	String Paymethod	= request.getParameter("Paymethod");	/* 지불방법 */
	String Couponprice	= request.getParameter("Couponprice");	/* 결제된 쿠폰금액 */
	String chkValue		= request.getParameter("chkValue");		/* 결과값 검증 hash데이터 */
	String Deposit		= request.getParameter("Deposit");		/* 일회용컵보증금액 */
	String Owndivcd		= request.getParameter("Owndivcd");		/* 카드소유구분 */

	/*
	 * 결제 정보의 위·변조 여부 확인 용도
	 * 주요 결제 정보를 HASH 처리한 chkValue 값을 받아
	 * 동일한 규칙으로 Notiurl에서 생성한 값(output)과 비교합니다.
	 */

	String returnMsg = "";
	String cpChkValue = "";
	cpChkValue = "Mobilid="+ Mobilid +
							"&Mrchid=null"+
							"&Svcid="+ Svcid +
							"&Tradeid="+ Tradeid +
							"&Signdate="+ Signdate +
							"&Prdtprice="+ Prdtprice;

	String encChkValue = McashCipher.encodeString(cpChkValue, Tradeid);

	// 값 비교
	if (encChkValue.equals(chkValue)) {
		// 동일 시 결제처리(서비스제공)
		returnMsg = "SUCCESS";
	} else {
		// 일치하지 않을 경우 데이터 위·변조 가능성 높으니 FAIL 처리
		returnMsg = "FAIL";
	}
%>

<%=returnMsg%>