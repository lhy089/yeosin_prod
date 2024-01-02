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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/print.js?t=<%= new java.util.Date() %>"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/apply/apply.js?t=<%= new java.util.Date() %>"></script>
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="apply accept_view">
  <div class="contentBox">
    <h1>원서접수 확인 및 취소 안내</h1>
    <div class="announcement">
      접수를 취소할 경우 환불 규정을 꼭 확인해야 합니다.
    </div>
    <form action="/cancel_restapi" method="POST" onsubmit="return doRefund();">
	    <c:choose>
	    <c:when test="${applyInfo ne null}">
	    <div id="printArea">
	    <table>
	      <colgroup>
	        <col width="15.5%">
	        <col width="*">
	      </colgroup>
	      <tr>
	        <th>시험명</th>
	        <td>${applyInfo.examDto.examName}</td>
	      </tr>
	      <tr>
	        <th>시험회차</th>
	        <td>${applyInfo.examDto.examDegree}</td>
	      </tr>
	      <tr>
	        <th>성명</th>
	        <td>${applyInfo.userDto.userName}</td>
	      </tr>
	      <tr>
	        <th>연락처</th>
	        <td>${applyInfo.userDto.phoneNumber}</td>
	      </tr>
	      <tr>
	        <th>이메일</th>
	        <td>${applyInfo.userDto.emailAddress}</td>
	      </tr>
	      <tr>
	        <th>시험장명</th>
	        <td>${applyInfo.examZoneDto.examZoneName}</td>
	      </tr>
	      <tr>
	        <th>자격종별</th>
	        <td>${applyInfo.subjectDto.subjectName}</td>
	      </tr>
	      <tr>
	        <th>접수일</th>
	        <td>${applyInfo.receiptDate}</td>
	      </tr>
	      <tr>
	        <th>시험일</th>
	        <td>${applyInfo.examDto.examDate}</td>
	      </tr>
<!-- 	      <tr> -->
<!-- 	        <th>결제정보</th> -->
<!-- 	       <td> <a onclick="return false;" id="btn_receiptPrint" class="btn_apply">영수증 출력</a></td> -->
<!-- 	      </tr> -->
	    </table>
	    </div>
	    </c:when>
	    <c:otherwise>
	    표시할 데이터가 없습니다.
	    </c:otherwise>
	    </c:choose>
	    <div class="btnWrap">
	    	<input type="hidden" value="${applyInfo.receiptId}" id="receiptId" name="receiptId"/>
	    	<input type="hidden" value="${applyInfo.paymentId}" id="TID" name="TID"/>
	    	<input type="hidden" value="${applyInfo.examFee}" id="CancelAmt" name="CancelAmt"/>
	    	<input type="hidden" value="0" id="PartialCancelCode" name="PartialCancelCode"/>
	    	<input type="hidden" value="${applyInfo.examDto.receiptStartDate}" id="receiptStartDate" name="receiptStartDate" />
	    	<input type="hidden" value="${applyInfo.examDto.receiptEndDate}" id="receiptEndDate" name="receiptEndDate" />
	    	<input type="hidden" value="${applyInfo.examDto.examDate}" id="examDate" name="examDate" />
			<!-- <a onclick="return false;" class="btn_apply" id="btn_print">출력하기</a> -->
	      <input style="border:none;" class="btn_apply" type="submit" value="접수취소"/>
	    </div>
	</form>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>
