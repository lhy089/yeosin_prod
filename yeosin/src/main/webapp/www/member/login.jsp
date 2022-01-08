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

  <link rel="stylesheet" href="../inc/css/member.css">
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="member login">
  <div class="contentBox">
    <h1>로그인</h1>
    <div class="loginBox">
      <p><label for="id">아이디</label><input type="text" id="id" placeholder="아이디 입력"></p>
      <p><label for="pwd">비밀번호</label><input type="password" id="pwd" placeholder="비밀번호 입력"></p>
      <a href="#">로그인</a>
    </div>
    <ul class="membership">
      <li><a href="#">회원가입</a></li>
      <li><a href="#">아이디 찾기</a></li>
      <li><a href="#">비밀번호 찾기</a></li>
    </ul>

    <h2>기존회원 - 비밀번호변경 안내</h2>
    <div class="guide">
      대출성  상품 판매대리 중개업자 등록자격 인증평가 사이트 운영 관리 서버가 변경되어 기존 회원들의 패스워드 재설정이 필요합니다.<br/>
      조금 불편하시더라도 <p class="red">비밀번호 찾기</p>를 통해 <p class="red">패스워드 변경</p> 후 로그인 부탁드립니다.
    </div>
    <div class="guide">
      <p class="blue">
        개인정보보호법에 의해 최종로그인 기준 1년 이상 미접속회원은 회원정보가 파기됩니다. 회원 가입 후 이용 부탁드립니다.
      </p>
    </div>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>
