<%
/*****************************************************************************************
* 파일명 : cn_web.jsp
* 작성자 : PG웹개발
* 작성일 : 2022.12
* 용  도 : 신용카드(CN) Webnoti 방식 결제 연동 페이지
* 버	전 : 0004
* ---------------------------------------------------------------------------------------
* 소스 임의변경에 따른 손실은 모빌리언스에서 책임지지 않습니다.
* 요청 파라미터 및 결제결과 전달 정보는 반드시 연동 매뉴얼을 참조하십시오.
* 신용카드(CN)는 결제테스트 환경을 제공하지 않습니다.
* 테스트 결제건은 가맹점 관리자 또는 취소 모듈을 이용하여 직접 취소 처리를 해야 합니다.
*
* 암호화 사용 시 필수 클래스
* mup.mcash.module.common.McashCipher.class
* mup.mcash.module.common.McashSeed.class
*****************************************************************************************/
%>

<%@page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="mup.mcash.module.common.McashCipher.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%

	// Unique한 거래번호를 위한 값 추출 (밀리세컨드까지 조회)
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
	String appr_dtm = dateFormat.format(new Date());


	/*****************************************************************************************/
	String CASH_GB = "CN"; 	// 대표결제수단. 고정


	/*****************************************************************************************
	- 필수 입력 항목
	*****************************************************************************************/
	String VER = "ALL_NEW";						//ALL_NEW : 버전설정 고정
	String CN_TAX_VER = "CPLX";					//CPLX : 복합과세취소버전설정 고정
	String CN_SVCID = "";						//서비스아이디
	String PAY_MODE = "10";						//10 : 실거래결제 고정
	String Prdtprice = "";						//결제요청금액.
	String Prdtnm = "";							//상품명 ( 50byte 이내 )
	String Siteurl = "";						//가맹점도메인
	String Okurl = "";							//성공화면처리URL : 결제완료통보페이지 full Url (예:http://www.mcash.co.kr/okurl.jsp )
	String Tradeid = CN_SVCID + "_" + appr_dtm;	//가맹점거래번호 //결제 요청 시 마다 unique한 값을 세팅해야 함.



	/*****************************************************************************************
	- 선택 입력 항목
	*****************************************************************************************/
	String Notiurl = "";					//결제처리URL : 결제 완료 후, 가맹점측 과금 등 처리할 가맹점측 URL
	String CALL_TYPE = "";					//결제창 호출방식
	String Failurl = "";					//결제 실패 시 사용자에게 보여질 가맹점 측 실패 페이지
	String Closeurl = "";					//닫기버튼 클릭 시 호출되는 가맹점 측 페이지. CALL_TYPE = ‘I’ 또는 ‘SELF’ 셋팅 시 필수
	String MSTR = "";						//가맹점콜백변수 //가맹점에서 추가적으로 파라미터가 필요한 경우 사용하며 &,%,?,^ 는 사용불가 ( 예 : MSTR="a=1|b=2|c=3" )
	String Payeremail = "";					//결제자email
	String Userid = "";						//가맹점결제자ID
	String CN_BILLTYPE = "";				//매출전표 출력 시 과세/비과세 구분
	String CN_TAXFREE = "";					//비과세
	String CN_TAX = "";						//부과세 - 전체금액의 10%이하로 설정
	String CN_TAXAMT = "";					//과세
	String CN_FREEINTEREST = "";			//무이자할부정보
	String CN_POINT = "";					//카드사포인트사용여부
	String Termregno = "";					//하위가맹점사업자번호
	String APP_SCHEME = "";					//APP SCHEME
	String Username = "";					//결제자명
	String CN_INSTALL = "";					//할부개월
	String CN_FIXCARDCD = "";				//카드사 선택노출 '결제창에 노출할 카드사 코드 셋팅
	String CN_DIRECT = "";					//카드사 직접호출 ( 예 : KBC:00:N )
	String Deposit = "";					//일회용컵보증금
	String CN_PAY_APP_USE_YN = "";			//우리카드,우리페이(WON카드,WON뱅킹) 결제만 제공
	String CN_PAY_APP_USE_CD = "";			//우리카드,우리페이(WON카드,WON뱅킹) 중 1개 단독 결제 여부(CN_PAY_APP_USE_YN = Y 일 때만 적용 가능)



	/*****************************************************************************************
	- 암호화 처리 (암호화 사용 시)
	Cryptstring 항목은 금액변조에 대한 확인용으로 반드시 아래와 같이 문자열을 생성하여야 합니다.

	주) 암호화 스트링은 가맹점에서 전달하는 거래번호로 부터 추출되어 사용되므로
	암호화에 이용한 거래번호가  변조되어 전달될 경우 복호화 실패로 결제 진행이 불가합니다.
	*****************************************************************************************/
	String Cryptyn		= "N";	//Y: 암호화 사용, N: 암호화 미사용
	String Cryptstring	= "";	//암호화 사용 시 암호화된 스트링

	if ("Y".equals(Cryptyn)) {
		Cryptstring	= Prdtprice + Okurl;	//금액변조확인 (결제요청금액 + Okurl)
		Okurl		= McashCipher.encodeString(Okurl, Tradeid);
		Failurl		= McashCipher.encodeString(Failurl, Tradeid);
		Notiurl		= McashCipher.encodeString(Notiurl, Tradeid);
		Prdtprice	= McashCipher.encodeString(Prdtprice, Tradeid);
		Cryptstring	= McashCipher.encodeString(Cryptstring, Tradeid);
	}

%>

<!--  가맹점의 결제요청 페이지 -->
<!DOCTYPE html>
<html>
<head>
<style>
	body {font-size:14px;}
	table td {font-size:13px;}
	table {border-collapse: collapse; text-align: left;}
</style>
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
 		<td align="center" colspan="6"><font size="15pt"><b>신용카드 결제 SAMPLE PAGE</b></font></td>
 	</tr>
 	<tr>
 		<td align="center"><font color="red">결제수단구분</font></td>
 		<td align="center"><font color="red">*CASH_GB</font></td>
 		<td><input type="text" name="CASH_GB" id="CASH_GB" size="2" value="<%=CASH_GB%>"></td>
 		<td align="center"><font color="red">서비스ID</font></td>
 		<td align="center"><font color="red">*CN_SVCID</font></td>
 		<td><input type="text" name="CN_SVCID" id="CN_SVCID" size="12" value="<%=CN_SVCID%>"></td>
 	</tr>
 	<tr>
 		<td align="center"><font color="red">거래모드</font></td>
 		<td align="center"><font color="red">*PAY_MODE</font></td>
 		<td><input type="text" name="PAY_MODE" id="PAY_MODE" size="2" value="<%=PAY_MODE%>"></td>
 		<td align="center"><font color="red">버전설정</font></td>
 		<td align="center"><font color="red">*VER</font></td>
 		<td><input type="text" name="VER" id="VER" size="10" value="<%=VER%>"></td>
 	</tr>
 	<tr>
 		<td align="center"><font color="red">거래금액</font></td>
 		<td align="center"><font color="red">*Prdtprice</font></td>
 		<td><input type="text" name="Prdtprice" id="Prdtprice" size="10" value="<%=Prdtprice%>"></td>
 		<td align="center"><font color="red">상품명</font></td>
 		<td align="center"><font color="red">*Prdtnm</font></td>
 		<td><input type="text" name="Prdtnm" id="Prdtnm" size="50" value="<%=Prdtnm%>"></td>
 	<tr>
 		<td align="center"><font color="red">가맹점거래번호</font></td>
 		<td align="center"><font color="red">*Tradeid</font></td>
 		<td><input type="text" name="Tradeid" id="Tradeid" size="40" value="<%=Tradeid%>"></td>
 		<td align="center"><font color="red">가맹점도메인</font></td>
 		<td align="center"><font color="red">*Siteurl</font></td>
 		<td><input type="text" name="Siteurl" id="Siteurl" size="40" value="<%=Siteurl%>"></td>
 	</tr>
 	<tr>
 		<td align="center"><font color="red">성공URL</font></td>
 		<td align="center"><font color="red">*Okurl</font></td>
 		<td><input type="text" name="Okurl" id="Okurl" value="<%=Okurl%>"></td>
 		<td align="center"><font color="red">복합과세취소전문요청</font></td>
 		<td align="center"><font color="red">*CN_TAX_VER</font></td>
 		<td><input type="text" name="CN_TAX_VER" id="CN_TAX_VER" value="<%=CN_TAX_VER%>"></td>
 	</tr>
	<tr>
		<td align="center">결제처리URL</td>
 		<td align="center">Notiurl</td>
 		<td><input type="text" name="Notiurl" id="Notiurl" value="<%=Notiurl%>"></td>
		<td align="center">결제창 호출 방식</td>
 		<td align="center">CALL_TYPE</td>
 		<td><input type="text" name="CALL_TYPE" id="CALL_TYPE" value="<%=CALL_TYPE%>"></td>
 	</tr>
 	<tr>
 		<td align="center">실패URL</td>
 		<td align="center">Failurl</td>
 		<td><input type="text" name="Failurl" id="Failurl" value="<%=Failurl%>"></td>
		<td align="center">사용자ID</td>
 		<td align="center">Userid</td>
 		<td><input type="text" name="Userid" id="Userid" size="30" value="<%=Userid%>"></td>
	</tr>
 	<tr>
		<td align="center">가맹점콜백변수</td>
 		<td align="center">MSTR</td>
 		<td><input type="text" name="MSTR" id="MSTR" value="<%=MSTR%>"></td>
	 	<td align="center">결제자E-mail</td>
 		<td align="center">Payeremail</td>
 		<td><input type="text" name="Payeremail" id="Payeremail" size="30" value="<%=Payeremail%>"></td>
 	</tr>
 	<tr>
 		<td align="center">암호화사용여부</td>
 		<td align="center">Cryptyn</td>
 		<td><input type="text" name="Cryptyn" id="Cryptyn" size="1" value="<%=Cryptyn%>"></td>
	 	<td align="center">암호화문자열</td>
 		<td align="center">Cryptstring</td>
 		<td><input type="text" name="Cryptstring" id="Cryptstring" value="<%=Cryptstring%>"></td>
 	</tr>
	<tr>
 		<td align="center">결제자명</td>
 		<td align="center">Username</td>
 		<td><input type="text" name="Username" id="Username" value="<%=Username%>"></td>
 		<td align="center">APP SCHEME</td>
 		<td align="center">APP_SCHEME</td>
 		<td><input type="text" name="APP_SCHEME" id="APP_SCHEME" value="<%=APP_SCHEME%>"></td>
 	</tr>
 	<tr>
	 	<td align="center">과세/비과세/복합과세</td>
 		<td align="center">CN_BILLTYPE</td>
 		<td><input type="text" name="CN_BILLTYPE" id="CN_BILLTYPE" value="<%=CN_BILLTYPE%>"></td>
 		<td align="center">부가세</td>
 		<td align="center">CN_TAX</td>
 		<td><input type="text" name="CN_TAX" id="CN_TAX" value="<%=CN_TAX%>"></td>
 	</tr>
 	<tr>
 		<td align="center">과세</td>
 		<td align="center">CN_TAXAMT</td>
 		<td><input type="text" name="CN_TAXAMT" id="CN_TAXAMT" value="<%=CN_TAXAMT%>"></td>
	 	<td align="center">비과세</td>
 		<td align="center">CN_TAXFREE</td>
 		<td><input type="text" name="CN_TAXFREE" id="CN_TAXFREE" value="<%=CN_TAXFREE%>"></td>
 	</tr>
 	<tr>
 		<td align="center">결제취소URL</td>
 		<td align="center">Closeurl</td>
 		<td><input type="text" name="Closeurl" id="Closeurl" value="<%=Closeurl%>"></td>
	 	<td align="center">무이자할부정보</td>
 		<td align="center">CN_FREEINTEREST</td>
 		<td><input type="text" name="CN_FREEINTEREST" id="CN_FREEINTEREST" value="<%=CN_FREEINTEREST%>"></td>
 	</tr>
 	<tr>
 		<td align="center">카드사포인트사용여부</td>
 		<td align="center">CN_POINT</td>
 		<td><input type="text" name="CN_POINT" id="CN_POINT" value="<%=CN_POINT%>"></td>
	 	<td align="center">하위가맹점사업자번호</td>
 		<td align="center">Termregno</td>
 		<td><input type="text" name="Termregno" id="Termregno" value="<%=Termregno%>"></td>
 	</tr>
 	<tr>
 		<td align="center">카드사선택노출</td>
 		<td align="center">CN_FIXCARDCD</td>
 		<td><input type="text" name="CN_FIXCARDCD" id="CN_FIXCARDCD" value="<%=CN_FIXCARDCD%>"></td>
 		<td align="center">카드사직접호출</td>
 		<td align="center">CN_DIRECT</td>
 		<td><input type="text" name="CN_DIRECT" id="CN_DIRECT" value="<%=CN_DIRECT%>"></td>
 	</tr>
 	<tr>
	 	<td align="center">특정할부개월</td>
 		<td align="center">CN_INSTALL</td>
 		<td><input type="text" name="CN_INSTALL" id="CN_INSTALL" value="<%=CN_INSTALL%>"></td>
 		<td align="center">1회용컵보증금</td>
 		<td align="center">Deposit</td>
 		<td><input type="text" name="Deposit" id="Deposit" size="10" value="<%=Deposit%>"></td>
 	</tr>
 	<tr>
	 	<td align="center">우리카드,우리페이(WON카드,WON뱅킹) 결제만 제공</td>
 		<td align="center">CN_PAY_APP_USE_YN</td>
 		<td><input type="text" name="CN_PAY_APP_USE_YN" id="CN_PAY_APP_USE_YN" size="1" value="<%=CN_PAY_APP_USE_YN%>"></td>
 		<td align="center">우리카드,우리페이(WON카드,WON뱅킹) 중 1개 단독 결제 여부</td>
 		<td align="center">CN_PAY_APP_USE_CD</td>
 		<td><input type="text" name="CN_PAY_APP_USE_CD" id="CN_PAY_APP_USE_CD" size="2" value="<%=CN_PAY_APP_USE_CD%>"></td>
 	</tr>
 	<tr>
 		<td align="center" colspan="6"></td>
 	</tr>
 	<tr>
 		<td align="center" colspan="6"><input type="button" value="결제하기" onclick="payRequest()"></td>
 	</tr>
 </table>
</form>
</body>
</html>