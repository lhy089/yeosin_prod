<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="ko">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  <meta charset="utf-8">
  <title>[admin]대출성 상품 판매대리•중개업자 등록 자격인증 평가</title>
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
  <link rel="stylesheet" href="/www/inc/css/admin.css">
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/applymanage.js?t=<%= new java.util.Date() %>"></script>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <script>
   	if(${loginUserInfo.userId != null}) {
   	  	$(location).attr("href", "/manageHome");
	}
 	$(document).ready(function(){
 		$('#btn_doLogin').click(function(){
 		var userId = $('#id').val();
 		var password = $('#pwd').val();

 		if (userId == '') {
 			alert("아이디를 입력하세요.");
 			$('#id').focus();
 			$('#pwd').val("");
 			return false;
 		}
 		if (password == '') {
 			alert("비밀번호를 입력 하세요.");
 			$('#pwd').focus();
 			return false;
 		}
 		
 		var data = {userId : userId, password:password};
 		$.ajax({
 	        type: "POST",
 	        url: "/manageLogin",
 	        data: data,
 	        sendDataType : 'string',
 			dataType : 'text',
 	        success: function(data) {
 	            if(data == 'S') {
 	         	   $(location).attr("href", "/manageHome");
 	            }else {
 	            	$('#id').val("");
 	        		$('#pwd').val("");
 	        		$('#id').focus();
 	            	alert("회원 정보가 존재하지 않거나 일치하지 않습니다.")
 	            }
 	        }
 	      });
 		});
 	});
   </script>

</head>

<body>
<%@ include file="/www/common/admin_header.jsp"%>
<div class="login">
  <header>
    <div class="contentBox">
      <h1><span>여신금융협회</span> 관리자 로그인 페이지입니다</h1>
    </div>
  </header>
  <div class="contentBoxAd">
    <h1 class="title">로그인</h1>
    <div class="loginBox">
      <p><label for="id">아이디</label><input type="text" id="id" placeholder="아이디 입력"></p>
      <p><label for="pwd">비밀번호</label><input type="password" id="pwd" placeholder="비밀번호 입력"></p>
      <a id="btn_doLogin" onclick="return false;">로그인</a>
    </div>
    <p class="logo"><img src="/www/inc/img/admin/logo.png" alt="여신금융"></p>
  </div>
</div>

</body>
</html>
