<%@page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%
/****************************************************************************************
* 파일명 : ra_web.jsp
* 작성자 : 서비스개발팀
* 작성일 : 2013.12
* 용  도 : 실시간 계좌이체 웹링크 방식 결제 연동 페이지
* 버  전 : 0004
* ---------------------------------------------------------------------------------------
* 가맹점의 소스 임의변경에 따른 책임은 모빌리언스에서 책임을 지지 않습니다.
* 요청 파라미터 및 결제 후 가맹점측 Okurl / Notiurl 으로 Return 되는 파라미터와
* 가맹점 서비스처리 방법은 연동 매뉴얼을 반드시 참조하세요.
* 결제실서버 전환시 꼭 모빌리언스 기술지원팀으로 연락바랍니다.
* 
* 암호화 사용시 필수 클래스
* mup.mcash.module.common.McashCipher.class
* mup.mcash.module.common.McashSeed.class
****************************************************************************************/
%>

<%@page import="mup.mcash.module.common.McashCipher.*"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%
//unique한 거래번호를 위한 거래일시 (밀리세컨드까지 조회)
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
String appr_dtm = dateFormat.format(new Date());
%>

<%
/*****************************************************************************************
- 필수 입력 항목
*****************************************************************************************/
String CASH_GB		= "RA";		//[   2byte 고정] 결제수단구분. "RA" 고정값. 수정불가!
String RA_SVCID		= "";		//[  12byte 고정] 모빌리언스에서 부여한 서비스ID (12byte 숫자 형식)
String Prdtprice	= "";		//[  10byte 이하] 결제요청금액 (암호화 사용 시 암호화 대상)
String PAY_MODE		= "00";		//[   2byte 고정] 연동시 테스트/실결제 구분 (00: 테스트결제-비과금, 10: 실거래결제-과금)
String Okurl		= "";		//[ 128byte 이하] 결제 완료 후 사용자에게 보여질 가맹점측 완료 페이지. (예: http://www.mcash.co.kr/okurl.jsp)
String Prdtnm		= "";		//[  30byte 이하] 상품명
String Siteurl		= "";		//[  20byte 이하] 가맹점도메인 (예: www.mcash.co.kr)

String Tradeid		= RA_SVCID + "_" + appr_dtm;	//[4byte 이상, 40byte 이하] 가맹점거래번호. 결제 요청 시 마다 unique한 값을 세팅해야 함.
													//해당 샘플에는 테스트를 위해 {가맹점 서비스ID + 요청일시} 형식으로 세팅하였음.



/*****************************************************************************************
- 디자인 관련 필수항목
*****************************************************************************************/
String LOGO_YN		= "N";		//[   1byte 고정] 가맹점 로고 사용 여부 (N: 모빌리언스 로고-default, Y: 가맹점 로고 (사전에 모빌리언스에 가맹점 로고 이미지를 등록해야함))
String CALL_TYPE	= "P";		//[   4byte 이하] 결제창 호출 방식 (P: 팝업-default, SELF: 페이지전환, I: 아이프레임)



/*****************************************************************************************
- 선택 입력 항목
*****************************************************************************************/
String Payeremail		= "";	//[  30byte 이하] 결제자 e-mail
String Userid			= "";	//[  20byte 이하] 가맹점 결제자ID
String Item				= "";	//[   8byte 이하] 아이템코드. 미사용 시 반드시 공백으로 세팅.
String Prdtcd			= "";	//[  30byte 이하] 상품코드. 사전에 모빌리언스에 등록이 필요함.
String Notiemail		= "";	//[  30byte 이하] 알림 e-mail: 결제 완료 후 당사와 가맹점간의 Noti 연동이 실패한 경우 알람 메일을 받을 가맹점 담당자 이메일주소
String Notiurl			= "";	//[ 128byte 이하] 결제 완료 후 가맹점 측 결제 처리를 담당하는 페이지. System back단으로 호출이 되며 사용자에게는 보여지지 않는다.
String Closeurl			= "";	//[ 128byte 이하] 결제창 취소버튼, 닫기버튼 클릭 시 호출되는 가맹점 측 페이지. iframe 호출 시 필수! (예: http://www.mcash.co.kr/closeurl.jsp)
String Failurl			= "";	//[ 128byte 이하] 결제 실패 시 사용자에게 보여질 가맹점 측 실패 페이지. 결제처리에 대한 실패처리 안내를 가맹점에서 제어해야 할 경우만 사용.
								//                iframe 호출 시 필수! (예: http://www.mcash.co.kr/failurl.jsp)
String MSTR				= "";	//[2000byte 이하] 가맹점 콜백 변수. 가맹점에서 추가적으로 파라미터가 필요한 경우 사용하며 &, % 는 사용불가 (예: MSTR="a=1|b=2|c=3")
String Deposit		= "";	//[  10byte 이하] 1회용컵 보증금 변수

/*****************************************************************************************
- 디자인 관련 선택항목 (향후 변경될 수 있습니다.)
*****************************************************************************************/
String IFRAME_NAME		= "";	//[   1byte 고정] 결제창을 iframe으로 호출 할 경우 iframe 명칭 세팅
String INFOAREA_YN		= "";	//[   1byte 고정] 결제창 안내문 표시여부 (Y: 표시-default,  N: 미표시)
String FOOTER_YN		= "";	//[   1byte 고정] 결제창 하단 안내 표시여부 (Y: 표시-default,  N: 미표시)
String HEIGHT			= "";	//[   4byte 이하] 결제창 높이 (px단위: iframe 등 사용시 결제창 높이 조절, 팝업창 등 호출시 "" 로 세팅)
String PRDT_HIDDEN		= "";	//[   1byte 고정] iframe 사용시 상품명 숨김 여부 (가맹점 디자인 결제창으로 결제 입력 사항만 iframe에서 사용시)
String EMAIL_HIDDEN		= "";	//[   1byte 고정] 결제자 e-mail 입력창 숨김 여부 (N: 표시-default, Y: 미표시)
String CONTRACT_HIDDEN	= "";	//[   1byte 고정] 이용약관 숨김 여부 (Y: 표시-default,  N: 미표시)



/*****************************************************************************************
- 암호화 처리 (암호화 사용 시)
Cryptstring 항목은 금액변조에 대한 확인용으로 반드시 아래와 같이 문자열을 생성하여야 합니다.

주) 암호화 스트링은 가맹점에서 전달하는 거래번호로 부터 추출되어 사용되므로
암호화에 이용한 거래번호가  변조되어 전달될 경우 복호화 실패로 결제 진행 불가
*****************************************************************************************/
String Cryptyn		= "N";	//Y: 암호화 사용, N: 암호화 미사용
String Cryptstring	= "";	//암호화 사용 시 암호화된 스트링

if( Cryptyn.equals("Y") ){
	Cryptstring	= Prdtprice + Okurl;	//금액변조확인 (결제요청금액 + Okurl)
	Okurl		= McashCipher.encodeString(Okurl, Tradeid);
	Failurl		= McashCipher.encodeString(Failurl, Tradeid);
	Notiurl		= McashCipher.encodeString(Notiurl, Tradeid);
	Prdtprice	= McashCipher.encodeString(Prdtprice, Tradeid);
	Cryptstring	= McashCipher.encodeString(Cryptstring, Tradeid);
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr"/>
<title>실시간 계좌이체 SAMPLE PAGE</title>
<%
/*****************************************************************************************
가맹점에서는 아래 js 파일을 반드시 include 해야 함.
실 결제환경 구성시 모빌리언스 담당자와 상의 요망
*****************************************************************************************/
%>
<script src="https://mup.mobilians.co.kr/js/ext/ext_inc_comm.js"></script>
<script language="javascript">
function payRequest(){
	//아래와 같이 ext_inc_comm.js에 선언되어 있는 함수를 호출
	MCASH_PAYMENT(document.payForm);
}
</script>
</head>

<body>
<form name="payForm" accept-charset="euc-kr">
<table border="1" width="100%">
<tr>
	<td align="center" colspan="6"><font size="15pt"><b>실시간 계좌이체 SAMPLE PAGE</b></font></td>
</tr>
<tr>
	<td colspan="3"><font color="red">&nbsp;빨간색 항목은 필수 값!!!</font></td>
	<td colspan="3"><font color="blue">&nbsp;파란색 항목은 결제창 UI 관련 파라미터</font></td>
</tr>
<tr>
	<td align="center"><font color="red">결제수단 구분</font></td>
	<td align="center"><font color="red">*CASH_GB</font></td>
	<td><input type="text" name="CASH_GB" id="CASH_GB" size="30" value="<%=CASH_GB%>"></td>
	<td align="center"><font color="red">성공URL</font></td>
	<td align="center"><font color="red">*Okurl</font></td>
	<td><input type="text" name="Okurl" id="Okurl" size="50" value="<%=Okurl%>"></td>
</tr>
<tr>
	<td align="center"><font color="red">서비스아이디</font></td>
	<td align="center"><font color="red">*RA_SVCID</font></td>
	<td><input type="text" name="RA_SVCID" id="RA_SVCID" size="30" value="<%=RA_SVCID%>"></td>
	<td align="center"><font color="red">상품명</font></td>
	<td align="center"><font color="red">*Prdtnm</font></td>
	<td><input type="text" name="Prdtnm" id="Prdtnm" size="30" value="<%=Prdtnm%>"></td>
</tr>
<tr>
	<td align="center"><font color="red">결제요청금액</font></td>
	<td align="center"><font color="red">*Prdtprice</font></td>
	<td><input type="text" name="Prdtprice" id="Prdtprice" size="30" value="<%=Prdtprice%>"></td>
	<td align="center"><font color="red">가맹점도메인</font></td>
	<td align="center"><font color="red">*Siteurl</font></td>
	<td><input type="text" name="Siteurl" id="Siteurl" size="30" value="<%=Siteurl%>"></td>
</tr>
<tr>
	<td align="center"><font color="red">거래종류</font></td>
	<td align="center"><font color="red">*PAY_MODE</font></td>
	<td><input type="text" name="PAY_MODE" id="PAY_MODE" size="30" value="<%=PAY_MODE%>"></td>
	<td align="center"><font color="red">가맹점거래번호</font></td>
	<td align="center"><font color="red">*Tradeid</font></td>
	<td><input type="text" name="Tradeid" id="Tradeid" size="50" value="<%=Tradeid%>"></td>
</tr>
<tr>
	<td align="center"><font color="red">가맹점 로고 사용여부</font></td>
	<td align="center"><font color="red">*LOGO_YN</font></td>
	<td><input type="text" name="LOGO_YN" id="LOGO_YN" size="30" value="<%=LOGO_YN%>"></td>
	<td align="center"><font color="red">결제창 호출방식</font></td>
	<td align="center"><font color="red">*CALL_TYPE</font></td>
	<td><input type="text" name="CALL_TYPE" id="CALL_TYPE" size="30" value="<%=CALL_TYPE%>"></td>
</tr>
<tr>
	<td align="center">결과통보 처리 url</td>
	<td align="center">Notiurl</td>
	<td><input type="text" name="Notiurl" id="Notiurl" size="50" value="<%=Notiurl%>"></td>
	<td align="center">취소/닫기 시 이동 url</td>
	<td align="center">Closeurl</td>
	<td><input type="text" name="Closeurl" id="Closeurl" size="50" value="<%=Closeurl%>"></td>
</tr>
<tr>
	<td align="center">실패 시 이동 url</td>
	<td align="center">Failurl</td>
	<td><input type="text" name="Failurl" id="Failurl" size="50" value="<%=Failurl%>"></td>
	<td align="center">사용자 ID</td>
	<td align="center">Userid</td>
	<td><input type="text" name="Userid" id="Userid" size="30" value="<%=Userid%>"></td>
</tr>
<tr>
	<td align="center">아이템</td>
	<td align="center">Item</td>
	<td><input type="text" name="Item" id="Item" size="30" value="<%=Item%>"></td>
	<td align="center">상품코드</td>
	<td align="center">Prdtcd</td>
	<td><input type="text" name="Prdtcd" id="Prdtcd" size="30" value="<%=Prdtcd%>"></td>
</tr>
<tr>
	<td align="center">결제자 이메일</td>
	<td align="center">Payeremail</td>
	<td><input type="text" name="Payeremail" id="Payeremail" size="30" value="<%=Payeremail%>"></td>
	<td align="center">가맹점 콜백 변수</td>
	<td align="center">MSTR</td>
	<td><input type="text" name="MSTR" id="MSTR" size="50" value="<%=MSTR%>"></td>
</tr>
<tr>
	<td align="center">Noti 알림E-mail</td>
	<td align="center">Notiemail</td>
	<td><input type="text" name="Notiemail" id="Notiemail" size="30" value="<%=Notiemail%>"></td>
	<td align="center"><font color="blue">iframe 명칭</font></td>
	<td align="center"><font color="blue">IFRAME_NAME</font></td>
	<td><input type="text" name="IFRAME_NAME" id="IFRAME_NAME" size="30" value="<%=IFRAME_NAME%>"></td>
</tr>
<tr>
	<td align="center"><font color="blue">결제창 안내문 표시 여부</font></td>
	<td align="center"><font color="blue">INFOAREA_YN</font></td>
	<td><input type="text" name="INFOAREA_YN" id="INFOAREA_YN" size="30" value="<%=INFOAREA_YN%>"></td>
	<td align="center"><font color="blue">결제창 하단 안내 표시 여부</font></td>
	<td align="center"><font color="blue">FOOTER_YN</font></td>
	<td><input type="text" name="FOOTER_YN" id="FOOTER_YN" size="30" value="<%=FOOTER_YN%>"></td>
</tr>
<tr>
	<td align="center"><font color="blue">결제창 높이</font></td>
	<td align="center"><font color="blue">HEIGHT</font></td>
	<td><input type="text" name="HEIGHT" id="HEIGHT" size="30" value="<%=HEIGHT%>"></td>
	<td align="center"><font color="blue">상품명 숨김 여부</font></td>
	<td align="center"><font color="blue">PRDT_HIDDEN</font></td>
	<td><input type="text" name="PRDT_HIDDEN" id="PRDT_HIDDEN" size="30" value="<%=PRDT_HIDDEN%>"></td>
</tr>
<tr>
	<td align="center"><font color="blue">결제자 이메일 숨김 여부</font></td>
	<td align="center"><font color="blue">EMAIL_HIDDEN</font></td>
	<td><input type="text" name="EMAIL_HIDDEN" id="EMAIL_HIDDEN" size="30" value="<%=EMAIL_HIDDEN%>"></td>
	<td align="center"><font color="blue">이용약관 숨김 여부</font></td>
	<td align="center"><font color="blue">CONTRACT_HIDDEN</font></td>
	<td><input type="text" name="CONTRACT_HIDDEN" id="CONTRACT_HIDDEN" size="30" value="<%=CONTRACT_HIDDEN%>"></td>
</tr>
<tr>
	<td align="center">암호화 사용 여부</td>
	<td align="center">Cryptyn</td>
	<td><input type="text" name="Cryptyn" id="Cryptyn" size="30" value="<%=Cryptyn%>"></td>
	<td align="center">암호화 검증 값</td>
	<td align="center">Cryptstring</td>
	<td><input type="text" name="Cryptstring" id="Cryptstring" size="50" value="<%=Cryptstring%>"></td>
</tr>
<tr>
	<td align="center">1회용컵보증금</td>
	<td align="center">Deposit</td>
	<td><input type="text" name="Deposit" id="Deposit" size="10" value=""></td>
	<td align="center" colspan="3">&nbsp;</td>
</tr>
<tr>
	<td align="center" colspan="6">&nbsp;</td>
</tr>
<tr>
	<td align="center" colspan="6"><input type="button" value="결제하기" onclick="payRequest();"></td>
</tr>
</table>
</form>
</body>
</html>
