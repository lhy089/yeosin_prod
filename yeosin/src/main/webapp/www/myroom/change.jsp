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

  <link rel="stylesheet" href="/www/inc/css/myroom.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/myroom/change.js?t=1"></script>
  <script>
  debugger;
  window.onload = function() { debugger;
	  var callNumberStr = $("#callNumberStr").val();
	  if(callNumberStr) {
 	  	var callNumberArr = callNumberStr.split("-");
	  	$("#callNumber2").val(callNumberArr[1]);
	  	$("#callNumber3").val(callNumberArr[2]);
	  }
	  
	  var phoneNumberStr = $("#phoneNumberStr").val();
	  if(phoneNumberStr) {
	  	var phoneNumberArr = phoneNumberStr.split("-");
	  	$("#phoneNumber2").val(phoneNumberArr[1]);
	  	$("#phoneNumber3").val(phoneNumberArr[2]);
	  	$("#isReceiveSms").prop("disabled",false);
	  	if($("#isReceiveSmsStr").val() == "Y") $("#isReceiveSms").prop("checked","checked");
	  }
	  
	  var emailAddressStr = $("#emailAddress").val();
	  if(emailAddressStr) {
		  $("#isReceiveEmail").prop("disabled",false);
		  if($("#isReceiveEmailStr").val() == "Y") $("#isReceiveEmail").prop("checked","checked");
	  }	 
  }
 
  </script>
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="myroom change">
  <div class="contentBox">
    <h1>회원정보수정</h1>
    <div class="announcement">
      <em>비밀번호 규칙</em>
      <ul>
        <li>6~20자 이내, 한글,띄어쓰기 금지.</li>
        <li>영문, 숫자, 특수문자 조합 2개.</li>
        <li>허용 특수문자 { } [ ] ( ) / | ? ! . * ~ ‘ ^ - _ + # $ % =</li>
      </ul>
    </div>
    <h2>회원가입 정보 확인</h2>
    <section>
      <table>
        <colgroup>
          <col width="20.5%">
          <col width="*">
        </colgroup>
        <tr>
          <th>아이디</th>
          <td id="userId">${userInfo.userId}</td>
        </tr>
        <tr>
          <th>성명</th>
          <td>${userInfo.userName}</td>
        </tr>
        <tr>
            <th class="essential">비밀번호</th>
            <td>
              <input type="password" name="" value="" id="userPwd">
            </td>
          </tr>
          <tr>
            <th class="essential">비밀번호 확인</th>
            <td>
              <input type="password" name="" value="" id="userPwd2">
            </td>
          </tr>
        <tr>
          <th>생년월일</th>
          <td></td>
        </tr>
        <tr>
          <th>성별</th>
          <td>${userInfo.gender}</td>
        </tr>
    <!--     <tr>
          <th>내국인여부</th>
          <td></td>
        </tr> -->
        <tr>
          <th>연락처</th>
          <td>
            <select id="callNumber" name="">
              <option value="02">02 서울</option>
              <option value="031">031 경기</option>
            </select>
            <input type="tel" maxlength="4" id="callNumber2" value="">
            <input type="tel" maxlength="4" id="callNumber3" value="">      
      		<input type="hidden" value="${userInfo.callNumber}" id="callNumberStr"/>
          </td>
        </tr>
        <tr>
          <th>휴대전화</th>
          <td>
            <select id="phoneNumber" name="">
              <option value="010">010</option>
            </select>
            <input type="tel" maxlength="4" id="phoneNumber2" value="">
            <input type="tel" maxlength="4" id="phoneNumber3" value="">
     		<input type="hidden" value="${userInfo.phoneNumber}" id="phoneNumberStr"/>
          </td>
        </tr>
        <tr>
          <th>이메일</th>
          <td>
            <input type="email"id="emailAddress" value="${userInfo.emailAddress}">
          </td>
        </tr>
        <tr>
          <th>SMS 수신동의</th>
          <td>
            <p>원서접수, 자격증 발급 관련 정보를 SMS를 이용하여 서비스하고 있습니다.<br/>동의하지 않을 경우 <strong>정보가 누락</strong>될 수 있습니다.</p>
            <label><input type="checkbox" id="isReceiveSms" name="agreeChk"/> 수신 동의합니다</label>
            <input type="hidden" value="${userInfo.isReceiveSms}" id="isReceiveSmsStr" disabled="disabled"/>
          </td>
        </tr>
        <tr>
          <th>이메일 수신동의</th>
          <td>
            <p>마케팅,홍보관련 자료 및 원서접수와 관련 된 정보를 이메일로 발송합니다.<br/>동의하지 않을 경우 <strong>정보가 누락</strong>될 수 있습니다.</p>
            <label><input type="checkbox" id="isReceiveEmail" name="agreeChk"/> 수신 동의합니다</label>
            <input type="hidden" value="${userInfo.isReceiveEmail}" id="isReceiveEmailStr" disabled="disabled"/>
          </td>
        </tr>
      </table>
      <a onclick="return false;" class="btn_apply">저장하기</a>
    </section>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>

