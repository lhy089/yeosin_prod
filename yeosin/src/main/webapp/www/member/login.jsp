<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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

  <link rel="stylesheet" href="/www/inc/css/member.css">
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/member/login.js?t=<%= new java.util.Date() %>"></script>
   <script>
   if(${loginUserInfo.userId != null}) {
	      $(location).attr("href", "/www/main.jsp");
	}	
   if(${isAlert}) { 
	      alert("로그인 후 이용 가능합니다.");
	}
   </script>
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
      <a id="btn_doLogin" onclick="return false;">로그인</a>
    </div>
    <ul class="membership">
      <li><a href="/join1">회원가입</a></li>
      <li><a href="/find_id">아이디 찾기</a></li>
      <li><a href="/find_pwd">비밀번호 찾기</a></li>
    </ul>
<h2>비밀번호 규칙</h2>
    <div class="guide">
      <p>
        6~20자 이내, 한글,띄어쓰기 금지.<br/>
        영문, 숫자, 특수문자 조합 2개.<br/>
        허용 특수문자 { } [ ] ( ) / | ? ! . * ~ ‘ ^ - _ + # $ % =
      </p>
    </div>
   <h2>기존회원 - 가입안내</h2>
    <div class="guide">
      <!-- 대출성  상품 판매대리 중개업자 등록자격 인증평가 사이트 운영 관리 서버가 변경되어 기존 회원들의 패스워드 재설정이 필요합니다.<br/>
      조금 불편하시더라도 <p class="red">비밀번호 찾기</p>를 통해 <p class="red">패스워드 변경</p> 후 로그인 부탁드립니다. -->
      자격인증 평가 홈페이지 리뉴얼에 따라 기존 평가 홈페이지에 가입(22년 2월 14일 이전)하신 회원들은<br/>
      서비스 이용을 위하여 번거로우시더라도 재가입해 주시기 바랍니다.<br/>
      <p class="blue">2021년도 응시 이력 및 자격인증서는 재가입 하신 아이디로 로그인 후 확인이 가능합니다.</p>
    </div>
    <div class="guide">
      <p class="blue">
       최종로그인 기준 1년 이상 미접속회원은 휴면계정으로 전환되며,<br/>
        휴면계정 전환 후 2년 이내에 해제를 하지 않을 경우 자동 탈퇴처리됩니다.
      </p>
    </div>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>
