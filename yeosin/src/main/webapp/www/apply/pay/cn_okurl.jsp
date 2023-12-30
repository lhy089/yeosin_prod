<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%

	/*********************************************************************************
	* 결제 성공시 웹 페이지 전환으로 호출되는 페이지로 가맹점에서 구현해야할 페이지
	* Notiurl사용 시 결제 결과 중복 처리 주의
	* 팝업 형식의 결제창 사용 시 가맹점 부모창 제어를 위한 스크립트 처리 필요
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
	String Resultcd		= request.getParameter("Resultcd");		/* 결과코드 */
	String Resultmsg	= request.getParameter("Resultmsg");	/* 결과메세지 */
	String Payeremail	= request.getParameter("Payeremail");	/* 결제자 이메일 */
	String MSTR			= request.getParameter("MSTR");			/* 가맹점 전달 콜백변수 */
	String Apprno		= request.getParameter("Apprno");		/* 승인번호 */
	String Paymethod	= request.getParameter("Paymethod");	/* 지불방법 */
	String Couponprice	= request.getParameter("Couponprice");	/* 결제된 쿠폰금액 */
	String Deposit		= request.getParameter("Deposit");		/* 일회용컵보증금액 */
	String Owndivcd		= request.getParameter("Owndivcd");		/* 카드소유구분 */

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
<title>가맹점 OKURL 모빌리언스 신용카드 결제</title>
</head>
<body>
	<!-- input user information# -->
	<table border ="1" width="100%">
		<tr>
			<td width="25%">CASH_GB</td>
			<td width="75%"><%=CASH_GB%></td>
		</tr>
		<tr>
			<td>Svcid</td>
			<td><%=Svcid%></td>
		</tr>
		<tr>
			<td>Mobilid</td>
			<td><%=Mobilid%></td>
		</tr>
		<tr>
			<td>Signdate</td>
			<td><%=Signdate%></td>
		</tr>
		<tr>
			<td>Tradeid</td>
			<td><%=Tradeid%></td>
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
			<td>Interest</td>
			<td><%=Interest%></td>
		</tr>
		<tr>
			<td>Userid</td>
			<td><%=Userid%></td>
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
			<td>Payeremail</td>
			<td><%=Payeremail%></td>
		</tr>
		<tr>
			<td>MSTR</td>
			<td><%=MSTR%></td>
		</tr>
		<tr>
			<td>Apprno</td>
			<td><%=Apprno%></td>
		</tr>
		<tr>
			<td>Paymethod</td>
			<td><%=Paymethod%></td>
		</tr>
		<tr>
			<td>Couponprice</td>
			<td><%=Couponprice%></td>
		</tr>
		<tr>
			<td>Deposit</td>
			<td><%=Deposit%></td>
		</tr>
		<tr>
			<td>Owndivcd</td>
			<td><%=Owndivcd%></td>
		</tr>
	</table>
</body>
</html>