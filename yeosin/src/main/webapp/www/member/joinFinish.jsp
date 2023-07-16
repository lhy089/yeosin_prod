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

  <link rel="stylesheet" href="www/inc/css/member.css?t=<%= new java.util.Date() %>">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/member/join.js?t=<%= new java.util.Date() %>"></script>
  <script>
  if(${result == 'N'}) { 
	    alert("회원 정보가 존재하지 않거나 일치하지 않습니다.");
	    location.href = "/join1";
	}
  if(${result == 'E'}) { 
	    alert("잘못된 정보입니다. 회원가입을 진행할 수 없습니다.");
	    location.href = "/join1";
	}
  $(document).ready(function() {
	  if(${userInfo.isReceiveSms == 'Y'}) $("#userInfo_isReceiveSms").prop("checked","checked");
	  if(${userInfo.isReceiveEmail == 'Y'}) $("#userInfo_isReceiveEmail").prop("checked","checked");
  });
</script>
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="member join">
  <div class="contentBox">
    <h1 class="commonTop">회원가입</h1>
    <div class="joinBox">

      <!-- 완료// -->
      <section class="finish">
        <div class="completed">
          <div>
            <h3>회원가입 완료</h3>
            <p>대출성 상품 모집인 자격인증센터를 이용해 주셔서 감사드립니다.</p>
          </div>
        </div>

        <h2>회원가입 정보 확인</h2>
        <table>
          <colgroup>
            <col width="15.8%">
            <col width="*">
          </colgroup>
          <tr>
            <th>이름</th>
            <td id="userInfo_name">${userInfo.userName}</td>
          </tr>
          <tr>
            <th>아이디</th>
            <td id="userInfo_id">${userInfo.userId}</td>
          </tr>
          <tr>
            <th>연락처</th>
            <td id="userInfo_callNumber">${userInfo.callNumber}</td>
          </tr>
          <tr>
            <th>휴대폰번호</th>
            <td id="userInfo_phoneNumber">${userInfo.phoneNumber}
              수신 동의합니다 <input type="checkbox" id="userInfo_isReceiveSms" name="agreeChk" onclick="return false"/><!--동의 했을 때 'checked' 포함-->
            </td>
          </tr>
          <tr>
            <th>이메일</th>
            <td id="userInfo_emailAddress">${userInfo.emailAddress}
              수신 동의합니다 <input type="checkbox" id="userInfo_isReceiveEmail" name="agreeChk" onclick="return false"/>
            </td>
          </tr>
        </table>
      </section>
      <!-- //완료 -->
    </div>
  </div>
</div>

<!--?php include_once "../common/footer.php";?-->
<%@ include file="/www/common/footer.jsp"%>

</body>
</html>

