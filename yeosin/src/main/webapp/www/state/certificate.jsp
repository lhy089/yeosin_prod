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
	<link rel="stylesheet" href="/www/inc/css/state.css">
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/print.js?t=1"></script>
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="state certificate">
  <div class="contentBox">
    <h1>자격인증서발급</h1>
    <div id="printArea">
	    <div class="document">
	      <p class="docNum">제2101100002호</p>
	      <h3><p>자격인증서</p></h3>
	      <table>
	        <colgroup>
	          <col width="14%">
	          <col width="">
	        </colgroup>
	        <tr>
	          <th>성명</th>
	          <td>홍길동</td>
	        </tr>
	        <tr>
	          <th>자격구분</th>
	          <td>대출성 상품 판매대리 · 중개업자 등록<br/>자격인증 평가(대출 · 기타 대출성 상품)</td>
	        </tr>
	        <tr>
	          <th>인증일</th>
	          <td>2021-06-24</td>
	        </tr>
	      </table>
	      <div class="evidence">
	        <p>
	          위 사람은 「금융소비자 보호에 관한 법률」<br/>
	          및 같은 법 시행령, 감독규정에 따라 실시한<br/>
	          대출성 상품 판매대리 · 중개업자 자격인증 평가에<br/>
	          합격하였으므로 이 증서를 드립니다.
	        </p>
	      </div>
	      <p class="date">2021년 06월 23일</p>
	    </div>
    </div>
	<a onclick="return false;" class="btn_apply" id="btn_print_certificate">출력하기</a>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>


