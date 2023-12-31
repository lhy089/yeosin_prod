<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%

request.setCharacterEncoding("EUC-KR");
/****************************************************************************************
* 파일명 : okurl.jsp
* 작성자 : 서비스개발팀
* 작성일 : 2013.12
* 용  도 : 실시간 계좌이체 okurl 페이지
* 버  전 : 0004
* ---------------------------------------------------------------------------------------
* 결제 성공시 웹 페이지 전환으로 호출되는 페이지이며 가맹점에서 구현해야하는 페이지
*
* 결제 성공에 따른 결과를 사용자에게 출력 또는 서비스처리 페이지
* notiurl 에서 결과 저장시 중복 처리 주의 필요
* 팝업 형식의 결제창 사용시 가맹점 부모창에 대한 스크립트 처리를 하시면 됩니다.
****************************************************************************************/

String Resultcd		= request.getParameter("Resultcd");		//[   4byte 고정] 결과코드
String Resultmsg	= request.getParameter("Resultmsg");	//[ 100byte 이하] 결과메세지

String CASH_GB		= request.getParameter("CASH_GB");		//[   2byte 고정] 결제수단(RA)
String Mobilid		= request.getParameter("Mobilid");		//[  15byte 이하] 모빌리언스 거래번호
String Mrchid		= request.getParameter("Mrchid");		//[   8byte 고정] 상점ID
String MSTR			= request.getParameter("MSTR");			//[2000byte 이하] 가맹점 전달 콜백변수
String Payeremail	= request.getParameter("Payeremail");	//[  30byte 이하] 결제자 이메일
String Prdtnm		= request.getParameter("Prdtnm");		//[  30byte 이하] 상품명
String Prdtprice	= request.getParameter("Prdtprice");	//[  10byte 이하] 상품가격
String Signdate		= request.getParameter("Signdate");		//[  14byte 이하] 결제일자
String Svcid		= request.getParameter("Svcid");		//[  12byte 고정] 서비스ID
String Tradeid		= request.getParameter("Tradeid");		//[  40byte 이하] 상점거래번호
String Userid		= request.getParameter("Userid");		//[  20byte 이하] 사용자ID
String Deposit		= request.getParameter("Deposit");		//[  10byte 이하] 1회용컵보증금


/*********************************************************************************
* 아래는 결과를 단순히 출력하는 샘플입니다.
* 가맹점에서는 부모창 전환등 스크립트 처리등을 하시면 됩니다.
*********************************************************************************/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr"/>
<title>가맹점 OKURL 모빌리언스 실시간 계좌이체</title>
</head>

<body>
<!-- input user information# -->
<table border ='1' width="100%">
<tr>
	<td width="20%">파라미터</td>
	<td width="80%">값</td>
</tr>
<tr>
	<td>Resultcd</td>
	<td><%=Resultcd%></td>
</tr>
<tr>
	<td>Resultmsg</td>
	<td><%=Resultmsg%></td>
</tr>
<tr>
	<td>CASH_GB</td>
	<td><%=CASH_GB%></td>
</tr>
<tr>
	<td>Mobilid</td>
	<td><%=Mobilid%></td>
</tr>
<tr>
	<td>Mrchid</td>
	<td><%=Mrchid%></td>
</tr>
<tr>
	<td>MSTR</td>
	<td><%=MSTR%></td>
</tr>
<tr>
	<td>Payeremail</td>
	<td><%=Payeremail%></td>
</tr>
<tr>
	<td>Prdtnm</td>
	<td><%=Prdtnm%></td>
</tr>
<tr>
	<td>Prdtprice</td>
	<td><%=Prdtprice%></td>
</tr>
<tr>
	<td>Signdate</td>
	<td><%=Signdate%></td>
</tr>
<tr>
	<td>Svcid</td>
	<td><%=Svcid%></td>
</tr>
<tr>
	<td>Tradeid</td>
	<td><%=Tradeid%></td>
</tr>
<tr>
	<td>Userid</td>
	<td><%=Userid%></td>
</tr>
<tr>
	<td>Deposit</td>
	<td><%=Deposit%></td>
</tr>
</table>
</body>
</html>
