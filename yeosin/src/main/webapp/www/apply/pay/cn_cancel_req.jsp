<%
/****************************************************************************************
* 파일명 : cn_cancel_req.jsp
* 작성자 : PG웹개발
* 작성일 : 2022.12
* 용  도 : 신용카드 Socket 방식 취소 연동 페이지
* 버	전 : 0004
* ---------------------------------------------------------------------------------------
* 소스 임의변경에 따른 손실은 모빌리언스에서 책임지지 않습니다.
* 요청 파라미터 및 결제결과 전달 정보는 반드시 연동 매뉴얼을 참조하십시오.
* 신용카드(CN)는 결제테스트 환경을 제공하지 않습니다.
* 테스트 결제건은 가맹점 관리자 또는 취소 모듈을 이용하여 직접 취소 처리를 해야 합니다.
****************************************************************************************/
%>

<%@page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!--  가맹점의 결제요청 페이지 -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ksc5601">
</head>

<body>
<h3>신용카드 결제 취소 SAMPLE PAGE</h3>
<hr>
<form name='cplogn' method='post' action='cn_cancel_result.jsp'>
<table>
	<tr>
		<td> 거래모드 : </td>
		<td> <input type="text" maxlength="20" name="mode" value="CN47"> </td>
	</tr>
	<tr>
		<td> 복합과세취소전문버전 : </td>
		<td> <input type="text" maxlength="20" name="taxVer" value="CPLX"> </td>
	</tr>
	<tr>
		<td> 사이트URL : </td>
		<td> <input type="text" maxlength="20" name="recordKey" value=""> </td>
	</tr>
	<tr>
		<td> 서비스아이디 : </td>
		<td> <input type="text" maxlength="12" name="svcId" value=""> </td>
	</tr>
	<tr>
		<td> 거래번호(가맹점) : </td>
		<td> <input type="text" maxlength="50" name="tradeId" value=""> </td>
	</tr>
	<tr>
		<td> 거래번호(모빌리언스) : </td>
		<td> <input type="text" maxlength="20" name="mobilId" value=""> </td>
	</tr>
	<tr>
		<td> 취소금액 : </td>
		<td> <input type="text" maxlength="30" name="prdtPrice" value=""> </td>
	</tr>
	<tr>
		<td> 부분취소여부 : </td>
		<td> <input type="text" maxlength="40" name="partCancelYn" value=""> </td>
	</tr>
	<tr>
		<td> 취소요청사유 </td>
		<td> <input type="text" maxlength="10" name="rmk" value=""> </td>
	</tr>
	<tr>
		<td> 통화코드(KRW,USD) </td>
		<td> <input type="text" maxlength="10" name="crcCd" value=""> </td>
	</tr>
	<tr>
		<td> 과세구분 </td>
		<td> <input type="text" maxlength="10" name="taxatDiv" value=""> </td>
	</tr>
	<tr>
		<td> 부가세  </td>
		<td> <input type="text" maxlength="10" name="taxAmt" value=""> </td>
	</tr>
	<tr>
		<td> 비과세  </td>
		<td> <input type="text" maxlength="10" name="taxFreeAmt" value=""> </td>
	</tr>
	<tr>
		<td> 과세  </td>
		<td> <input type="text" maxlength="10" name="taxatAmt" value=""> </td>
	</tr>
	<tr>
		<td> 잔액부가세  </td>
		<td> <input type="text" maxlength="10" name="remainTaxAmt" value=""> </td>
	</tr>
	<tr>
		<td> 1회용컵보증금  </td>
		<td> <input type="text" maxlength="10" name="deposit" value=""> </td>
	</tr>
</table>
<p> <input type="button" value="요청하기" onClick='document.cplogn.submit();'> </p>
</form>
</body>
</html>