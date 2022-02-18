<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head lang="ko">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  <meta charset="utf-8">
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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

  <link rel="stylesheet" href="/www/inc/css/myroom.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/myroom/pwd.js?t=<%= new java.util.Date() %>"></script>
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%-- <%@ include file="/www/common/header.jsp"%> --%>
<c:choose>
	    <c:when test="${type eq 'id'}">
<div class="myroom pwd">
  <div class="contentBox">
    <h1>아이디 확인</h1>
    <div class="pwdBox">
      		회원님의 아이디는 ${data} 입니다.
    </div>
  </div>
</div>
</c:when>
<c:when test="${type eq 'pwd'}">
<div class="myroom pwd">
  <div class="contentBox">
    <h1>비밀번호 확인</h1>
    <div class="pwdBox">
      	회원님의 비밀번호는 ${data} 입니다.
    </div>
  </div>
</div>
<%-- <%@ include file="/www/common/footer.jsp"%> --%>
<!--?php include_once "../common/footer.php";?-->
</c:when>
<c:when test="${type eq 'duple'}">
<div class="myroom pwd">
  <div class="contentBox">
    <h1>아아디 확인</h1>
    <div class="pwdBox">
      	일치하는 정보가 여러 건 입니다. <br>휴대전화 및 아이핀 본인인증을 이용해 주세요.
    </div>
  </div>
</div>
<%-- <%@ include file="/www/common/footer.jsp"%> --%>
<!--?php include_once "../common/footer.php";?-->
</c:when>
<c:otherwise>
<div class="myroom pwd">
  <div class="contentBox">
    <h1>아이디/비밀번호 확인</h1>
    <div class="pwdBox">
      	일치하는 데이터가 없습니다.
    </div>
  </div>
</div>
</c:otherwise>
</c:choose>
</body>
</html>

