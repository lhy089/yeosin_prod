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
  <script language='javascript'>
  
  if(${isCert != 'Y'}) { 
	   alert("잘못 된 접근입니다.");
  }else {
	  alert("인증 되었습니다.");
  }
		</script>
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="member join">
  <div class="contentBox">
    <h1 class="commonTop">회원가입</h1>
    <div class="joinBox">
      <!-- (4단계)기입// -->
      <section class="entry">
        <h1>기본정보<p class="essential">필수입력</p></h1>
        <table>
          <colgroup>
            <col width="20.5%">
            <col width="*">
          </colgroup>
          <tr>
            <th class="essential">이름</th>
            <td>
              <p id="userName">${userInfo.userName}</p>
            </td>
          </tr>
    <!--
          <tr>
            <th class="essential">이름</th>
            <td>
              <input type="text" name="" value="" id="userName" disabled>
            </td>
          </tr>
          -->
          <tr>
            <th class="essential">아이디</th>
            <td>
              <input type="text" name="" value="" id="userId"><a href="#" id="btn_chkIdDupl">중복확인</a>
              <p>6~20자의 영문 소문자, 숫자만 가능. 영문소문자 시작.</p>
            </td>
          </tr>
          <tr>
            <th class="essential">비밀번호</th>
            <td>
              <input type="password" name="" value="" id="userPwd">
              <p>6~20자의 영문 대소문자와 숫자, 특수문자를 사용할 수 있으며, 최소 2종류이상을 조합해야 합니다.<br/>허용 특수문자 { } [ ] ( ) / | ? ! . * ~ ‘ ^ - _ + # $ % =</p>
            </td>
          </tr>
          <tr>
            <th class="essential">비밀번호 확인</th>
            <td>
              <input type="password" name="" value="" id="userPwd2">
            </td>
          </tr>
          <tr>
            <th class="essential">생년월일</th>
            <td>
              <p id="birth" value="${userInfo.birthDate}">${birthDate}</p>
            </td>
          </tr>
          <tr>
            <th class="essential">성별</th>
            <td>
              <p id="gender" value="${userInfo.gender}">${gender}</p>
            </td>
          </tr>
        <!-- </table>

        <h1>추가정보</h1>
        <table>
          <colgroup>
            <col width="20.5%">
            <col width="*">
          </colgroup> -->
          <tr>
            <th>연락처</th>
            <td>
              <select id="callNumber" name="">
                <option value="02">02</option>
                <option value="051">051</option>
                <option value="053">053</option>
                <option value="032">032</option>
                <option value="062">062</option>
                <option value="042">042</option>
                <option value="052">052</option>
                <option value="044">044</option>
                <option value="031">031</option>
                <option value="033">033</option>
                <option value="043">043</option>
                <option value="041">041</option>
                <option value="063">063</option>
                <option value="061">061</option>
                <option value="054">054</option>
                <option value="055">055</option>
                <option value="064">064</option>
              </select>
              <input type="tel" maxlength="4" id="callNumber2" name="" value="">
              <input type="tel" maxlength="4" id="callNumber3"name="" value="">
              <p>수신동의 하시면 안내 및 수험 정보를 받으실 수 있습니다.<!--<br/>수신동의와 별도로 비밀번호 찾기에 사용되므로 <strong>반드시 입력</strong> 바랍니다.--></p>
              <label><input type="checkbox" id="" name="agreeChk"/> 수신 동의합니다</label>
            </td>
          </tr>
          <tr>
            <th class="essential">휴대전화</th>
            <td>
              <select id="phoneNumber" name="">
                <option value="010">010</option>
              </select>
              <input type="tel" maxlength="4" id="phoneNumber2" value="">
              <input type="tel" maxlength="4" id="phoneNumber3" value="">
              <p>수신동의 하시면 안내 및 수험 정보를 받으실 수 있습니다.<br/>수신동의와 별도로 비밀번호 찾기에 사용되므로 <strong>반드시 입력</strong> 바랍니다.</p>
              <label><input type="checkbox" id="isReceiveSms" name="agreeChk" disabled="disabled"/> 수신 동의합니다</label>
            </td>
          </tr>
          <tr>
           <th class="essential">이메일</th>
            <td>
              <input type="email" id="emailAddress" name="emailAddress" value="">
              <p>수신동의 하시면 안내 및 수험 정보를 받으실 수 있습니다.<br/>수신동의와 별도로 비밀번호 찾기에 사용되므로 <strong>반드시 입력</strong> 바랍니다.</p>
              <label><input type="checkbox" id="isReceiveEmail" name="agreeChk" disabled="disabled"/> 수신 동의합니다</label>
            </td>
          </tr>
        </table>
		<input type="hidden" name="diCode" id="diCode" value="${userInfo.diCode}">	
		<input type="hidden" name="ciCode" id="ciCode" value="${userInfo.ciCode}">	
		
        <a href="#" class="btn_apply" id="btn_doJoinFinish">가입하기</a>
      </section>
      <!-- //(4단계)기입 -->

    </div>
  </div>
  <form action="/doJoin" method="POST" name="joinForm" id="joinForm">
		<input type="hidden" name="userName" id="userNameForm" value="">
    	<input type="hidden" name="userId" id="userIdForm" value="">
		<input type="hidden" name="password" id="passwordForm" value="">
		<input type="hidden" name="callNumber" id="callNumberForm" value="">
		<input type="hidden" name="phoneNumber" id="phoneNumberForm" value="">
		<input type="hidden" name="emailAddress" id="emailAddressForm" value="">
        <input type="hidden" name="isReceiveSms" id="isReceiveSmsForm" value="">   
        <input type="hidden" name="isReceiveEmail" id="isReceiveEmailForm" value="">   
        <input type="hidden" name="birthDate" id="birthDateForm" value="">
        <input type="hidden" name="gender" id="genderForm" value="">   
        <input type="hidden" name="diCode" id="diCodeForm" value="">   
        <input type="hidden" name="ciCode" id="ciCodeForm" value="">  
   </form>
   
</div>

<!--?php include_once "../common/footer.php";?-->
<%@ include file="/www/common/footer.jsp"%>
</body>
</html>

