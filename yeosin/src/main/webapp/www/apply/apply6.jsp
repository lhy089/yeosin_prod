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
	<script type="text/javascript">
	window.history.forward();
	function noBack()
	{
		window.history.forward();
	}
	</script>
</head>

<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="apply sixth">
  <div class="contentBox">
    <h1>원서접수</h1>
    <div class="announcement">
      인터넷접수 첫날의 접수 시작 시간은 10:00 부터입니다.
    </div>
    <h2>4단계 접수완료</h2>
    <form action="/ticket_view" method="get" onsubmit="return true">
	    <div class="doneBox">
	    <c:set var="isSuccess" value="${isSuccess}"/>
	    <c:choose>
	    	<c:when test="${isSuccess eq 'Y'}">
		    	수험번호 : ${studentCode}
		      <p>접수가 정상적으로 완료되었습니다.</p>
		      <input type="hidden" value="${receiptId}" id="receiptId" name="receiptId"/>
		      <input style="border:none;" class="btn_apply" type="submit" value="수험표 출력"/>
	    	</c:when>
	    	<c:otherwise>
	    		수험번호 : 결제실패
				<p>접수가 실패하였습니다.</p>
				<c:choose>
				<c:when test="${not empty resultCode}">
					<p>결제 오류 코드 : ${resultCode}</p>
				</c:when>
				<c:when test="${isRefund eq 'Y'}">
					<p>결제가 자동으로 환불 처리 됐습니다.</p>
					<p>재시도 하신 후 실패할 시 고객센터에 문의 부탁 드립니다.</p>
				</c:when>
				</c:choose>
				<p>출력 불가</p>
	    	</c:otherwise>
	    </c:choose>
	    </div>
    </form>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>

