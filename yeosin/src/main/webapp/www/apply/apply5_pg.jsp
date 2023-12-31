<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="ko">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta charset="utf-8">
	<title>대출성 상품 판매대리•중개업자 등록 자격인증 평가</title>
	<meta name="description" content="여신금융협회">
	<meta name="keywords" content="원서접수, 평가응시현황, 시험안내, 알림마당, 회원정보">
	<meta name="viewport" content="user-scalable=no,
	 initial-scale=1.0,
	 maximum-scale=1.0,
	 minimum-scale=1.0,
	 width=device-width,
	 height=device-height">
	<meta property="og:type" content="website">
	<meta property="og:site_name" content="여신금융협회"/>
	<!-- <meta property="og:url" content="사이트url"> -->
	<meta property="og:title" content="대출성 상품 판매대리•중개업자 등록 자격인증 평가">
	<meta property="og:description" content="대출성 상품 판매대리•중개업자 등록 자격인증 평가">
	<meta property="og:image" content="/www/inc/img/openGraph.jpg">
	<link rel="shortcut icon" href="/www/inc/img/favicon.png"/>
	<link rel="icon" href="/www/inc/img/favicon.png" type="image/x-icon">
	<link rel="stylesheet" href="/www/inc/css/apply.css">	
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/apply/apply.js?t=<%= new java.util.Date() %>"></script>
<%-- 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/apply/pay.js?t=<%= new java.util.Date() %>"></script> --%>
<!-- 	<script src="https://web.nicepay.co.kr/v3/webstd/js/nicepay-3.0.js" type="text/javascript"></script> -->
	<script src="https://pg-web.nicepay.co.kr/v3/common/js/nicepay-pgweb.js" type="text/javascript"></script>
	<style>
	body {font-size:14px;}
	table td {font-size:13px;}
	table {border-collapse: collapse; text-align: left;}
</style>
<script src="https://mup.mobilians.co.kr/js/ext/ext_inc_comm.js"></script>
<script language="javascript">
	function payRequest(){
		//아래와 같이 ext_inc_comm.js에 선언되어 있는 함수를 호출
		if($("input[name='CASH_GB']:checked").val() == 'RA'){
			document.payForm.Okurl.value = document.payForm.RA_Okurl.value;
		}
		
		MCASH_PAYMENT(document.payForm);
	}
</script>

</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="apply fifth">
  <div class="contentBox">
    <h1>원서접수</h1>
    <div class="announcement">
      인터넷접수 첫날의 접수 시작 시간은 10:00 부터입니다.
    </div>
    <h2>3단계 접수확인 및 결제선택</h2>
<!--     <form action="/apply6" method="post" onsubmit="return doPayment();"> -->
    <form action="/apply6" method="post" name="payForm" id="payForm">
	    <section>
	      <table>
	        <colgroup>
	          <col width="15.5%">
	          <col width="*">
	        </colgroup>
	        <tr>
	          <th>시험명</th>
	          <td>${examInfo.examName}</td>
	       	</tr>
        	<tr>
       			<th>자격종별</th>
         		<td>${subjectName}</td>
        	</tr>
       		<tr>
	          <th>성명</th>
	          <td>${userInfo.userName}</td>
	        </tr>
	        <tr>
	          <th>연락처</th>
	          <td>${userInfo.phoneNumber}</td>
	        </tr>
	        <tr>
	          <th>이메일</th>
	          <td>${userInfo.emailAddress}</td>
	        </tr>
	        <tr>
	          <th>수료번호</th>
	          <td><%=request.getParameter("eduNum")%></td>
	        </tr>
	        <tr>
	          <th>시험장명</th>
	          <td>${examZoneName}</td>
	        </tr>
	        <tr>
	          <th>시험일</th>
	          <td>${examInfo.examDate}</td>
	        </tr>
	        <tr>
	          <th>결제금액</th>
	          <td>${examInfo.examCost}원</td>
	        </tr>
	        <tr>
	          <th>결제방법선택</th>
	          <td>
<!-- 	            <label class="pay"><input type="radio" name="paymentMethod" value="VBANK"><span>가상계좌</span></label> -->
	            <label class="pay"><input type="radio" name="CASH_GB" value="RA"><span>실시간 계좌이체</span></label>
	            <label class="pay"><input type="radio" name="CASH_GB" value="CN" checked><span>신용카드</span></label>
	          </td>
	        </tr>
	      </table>
	      	<input type="hidden" value="<%=request.getParameter("examId")%>" id="examId" name="examId"/>
			<input type="hidden" value="<%=request.getParameter("eduNum")%>" id="eduNum" name="eduNum"/>
			<input type="hidden" value="<%=request.getParameter("exmaZoneRadio")%>" id="exmaZoneId" name="exmaZoneId"/>
			<input type="hidden" value="<%=request.getParameter("subjectRadio")%>" id="subjectId" name="subjectId"/>
		
		<input type="hidden" name="VER" id="VER" value="${VER}"/>
		<input type="hidden" name="CN_TAX_VER" id="CN_TAX_VER" value="${CN_TAX_VER}"/>
		<input type="hidden" name="CN_SVCID" id="CN_SVCID" value="${CN_SVCID}"/>
		<input type="hidden" name="RA_SVCID" id="RA_SVCID" value="${RA_SVCID}"/>
		<input type="hidden" name="PAY_MODE" id="PAY_MODE" value="${PAY_MODE}"/>
		<input type="hidden" name="Prdtprice" id="Prdtprice" value="${Prdtprice}"/>
		<input type="hidden" name="Prdtnm" id="Prdtnm" value="${Prdtnm}"/>
		<input type="hidden" name="Siteurl" id="Siteurl" value="${Siteurl}"/>
		<input type="hidden" name="Okurl" id="Okurl" value="${Okurl}"/>
		<input type="hidden" name="RA_Okurl" id="RA_Okurl" value="${RA_Okurl}"/>
		<input type="hidden" name="Tradeid" id="Tradeid" value="${Tradeid}"/>
		
		<input type="hidden" name="Notiurl" id="Notiurl" value="${Notiurl}"/>
		<input type="hidden" name="IFRAME_NAME" id="IFRAME_NAME" value="${IFRAME_NAME}"/>
		<input type="hidden" name="CALL_TYPE" id="CALL_TYPE" value="${CALL_TYPE}"/>
		<input type="hidden" name="RA_CALL_TYPE" id="RA_CALL_TYPE" value="P"/>
		<input type="hidden" name="Failurl" id="Failurl" value="${Failurl}"/>
		<input type="hidden" name="Closeurl" id="Closeurl" value="${Closeurl}"/>
		<input type="hidden" name="MSTR" id="MSTR" value="${MSTR}"/>
		<input type="hidden" name="Payeremail" id="Payeremail" value="${Payeremail}"/>
		<input type="hidden" name="Userid" id="Userid" value="${Userid}"/>
		<input type="hidden" name="CN_BILLTYPE" id="CN_BILLTYPE" value="${CN_BILLTYPE}"/>
		<input type="hidden" name="CN_TAXFREE" id="CN_TAXFREE" value="${CN_TAXFREE}"/>
		<input type="hidden" name="CN_TAX" id="CN_TAX" value="${CN_TAX}"/>
		
		<input type="hidden" name="CN_TAXAMT" id="CN_TAXAMT" value="${CN_TAXAMT}"/>
		<input type="hidden" name="CN_FREEINTEREST" id="CN_FREEINTEREST" value="${CN_FREEINTEREST}"/>
		<input type="hidden" name="CN_POINT" id="CN_POINT" value="${CN_POINT}"/>
		<input type="hidden" name="Termregno" id="Termregno" value="${Termregno}"/>
		<input type="hidden" name="APP_SCHEME" id="APP_SCHEME" value="${APP_SCHEME}"/>
		<input type="hidden" name="CN_AUTHPAY" id="CN_AUTHPAY" value="${CN_AUTHPAY}"/>
		<input type="hidden" name="prdtCd" id="prdtCd" value="${prdtCd}"/>
		<input type="hidden" name="Username" id="Username" value="${Username}"/>
		<input type="hidden" name="CN_INSTALL" id="CN_INSTALL" value="${CN_INSTALL}"/>
		<input type="hidden" name="CN_FIXCARDCD" id=CN_FIXCARDCD value="${CN_FIXCARDCD}"/>
		<input type="hidden" name="CN_DIRECT" id="CN_DIRECT" value="${CN_DIRECT}"/>
		<input type="hidden" name="Deposit" id="Deposit" value="${Deposit}"/>
		<input type="hidden" name="CN_PAY_APP_USE_YN" id="CN_PAY_APP_USE_YN" value="${CN_PAY_APP_USE_YN}"/>
		<input type="hidden" name="CN_PAY_APP_USE_CD" id="CN_PAY_APP_USE_CD" value="${CN_PAY_APP_USE_CD}"/>
		
		<input type="hidden" name="LOGO_YN" id="LOGO_YN" value="N"/>
		<input type="hidden" name="Item" id="Item" value=""/>
		<input type="hidden" name="INFOAREA_YN" id="INFOAREA_YN" value="Y"/>
		<input type="hidden" name="FOOTER_YN" id="FOOTER_YN" value="Y"/>
		<input type="hidden" name="HEIGHT" id="HEIGHT" value=""/>
		<input type="hidden" name="PRDT_HIDDEN" id="PRDT_HIDDEN" value=""/>
		<input type="hidden" name="EMAIL_HIDDEN" id="EMAIL_HIDDEN" value="N"/>
		<input type="hidden" name="CONTRACT_HIDDEN" id="CONTRACT_HIDDEN" value="Y"/>
		<input type="hidden" name="Cryptyn" id="Cryptyn" value="N"/>
		<input type="hidden" name="Cryptstring" id="Cryptstring" value=""/>
		<input type="hidden" name="Deposit" id="Deposit" value=""/>
		
		
			<input style="border:none;" class="btn_apply" onclick="payRequest()" value="결제하기"/>
<!-- 			<input style="border:none;" class="btn_apply" id="btnReq" value="결제하기"/> -->
	    </section>
    </form>

			<!--   	<form name="payForm" method="post" action="/payResult"> -->
		
<!-- 	</form> -->
	
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>

