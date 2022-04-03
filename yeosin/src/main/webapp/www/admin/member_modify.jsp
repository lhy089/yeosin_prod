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
  <link rel="stylesheet" href="/www/inc/css/admin.css?t=<%= new java.util.Date() %>">
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/usermanage.js?t=<%= new java.util.Date() %>"></script>-->
<script>
window.onload = function() { debugger;
var callNumberStr = '${userDto.callNumber}';
if(callNumberStr) {
 	var callNumberArr = callNumberStr.split("-");
 	$("#callNumber").val(callNumberArr[0]);
	$("#callNumber2").val(callNumberArr[1]);
	$("#callNumber3").val(callNumberArr[2]);
}

var phoneNumberStr = '${userDto.phoneNumber}';
if(phoneNumberStr) {
	var phoneNumberArr = phoneNumberStr.split("-");
	$("#phoneNumber").val(phoneNumberArr[0]);
	$("#phoneNumber2").val(phoneNumberArr[1]);
	$("#phoneNumber3").val(phoneNumberArr[2]);
}
}

$(document).ready(function() {
	 
		debugger;
	
		$("#btn_modify").click(function() {
			
			//if(!isValid()) return false;
			
			var password = $('#password').val();
			var password2 = $('#password2').val();
		
			if (password != password2) {
				alert("비밀번호가 일치하지 않습니다.");
				$('#password').val("");
				$('#password2').val("");
				$('#password').focus();
				return false;
			}
			
			if(confirm("해당 회원정보를 수정하시겠습니까?")){
				var callNumber = "";
				var phoneNumber = "";
				if($("#callNumber2").val() && $("#callNumber3").val()) {
					callNumber = $("#callNumber").val()+"-"+$("#callNumber2").val()+"-"+$("#callNumber3").val();
				}
				if($("#phoneNumber2").val() && $("#phoneNumber3").val()) {
					phoneNumber = $("#phoneNumber").val()+"-"+$("#phoneNumber2").val()+"-"+$("#phoneNumber3").val();
				}
				
				var isReceiveSmsVal =  $("#isReceiveSms").is(":checked") ? "Y" : "N";
				var isReceiveEmailVal = $("#isReceiveEmail").is(":checked") ? "Y" : "N";
				
			   $("#userId").val($("#userId").val());
			   $("#userName").val($("#userName").val());
			   $("#password").val($("#password").val());
			   $("#birthDate").val($("#birthDate").val());
			   $('input[name=gender]').val($('input[name=gender]:checked').val());
			   $("#callNumber").val(callNumber);
			   $("#phoneNumber").val(phoneNumber);
			   $('#emailAddress').val($('#emailAddress').val());
			   $("#isReceiveSms").val(isReceiveSmsVal);
			   $("#isReceiveEmail").val(isReceiveEmailVal);
			   $("#commonform").submit(); 
			}
		});
	
});

</script>

<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>

<div class="member info">
  <div class="contentBoxAd">
    <h1 class="title">회원관리</h1>
    <h2>회원정보</h2>
    <table>
      <colgroup>
        <col width="17%">
        <col width="33%">
        <col width="17%">
        <col width="33%">
      </colgroup>
      <tr>
        <th>검색어</th>
        <td><input type="text" name="" value=""></td>
        <th>회원구분</th>
        <td>
          <input type="hidden" id="generalUser" name="generalUser">
          <input type="hidden" id="dormancyUser" name="dormancyUser">
          <input type="hidden" id="secessionUser" name="secessionUser">
          <label class="type"><input type="checkbox" id="checkGeneral" name="checkUserStatus" <c:if test="${generalUser eq 'Y'}">checked="checked"</c:if>> 일반</label>  
          <label class="type"><input type="checkbox" id="checkDormancy" name="checkUserStatus" <c:if test="${dormancyUser eq 'Y'}">checked="checked"</c:if>> 휴면</label>
          <label class="type"><input type="checkbox" id="checkSecession" name="checkUserStatus"<c:if test="${secessionUser eq 'Y'}">checked="checked"</c:if>> 탈퇴</label>
        </td>
      </tr>
      <tr>
        <th>이메일 수신 여부</th>
        <td>
           <label class="agree"><input type="radio" name="searchEmailType" <c:if test="${searchEmailType eq 'A'}">checked="checked"</c:if> value="A"> 전체</label>
          <label class="agree"><input type="radio" name="searchEmailType" <c:if test="${searchEmailType eq 'Y'}">checked="checked"</c:if> value="Y"> 수신허용</label>
          <label class="agree"><input type="radio" name="searchEmailType" <c:if test="${searchEmailType eq 'N'}">checked="checked"</c:if> value="N"> 수신거부</label>
        </td>
        <th>문자(SMS) 수신 여부</th>
        <td>
         <label class="agree"><input type="radio" name="searchSMSType" <c:if test="${searchSMSType eq 'A'}">checked="checked"</c:if> value="A"> 전체</label>
          <label class="agree"><input type="radio" name="searchSMSType" <c:if test="${searchSMSType eq 'Y'}">checked="checked"</c:if> value="Y"> 수신허용</label>
          <label class="agree"><input type="radio" name="searchSMSType" <c:if test="${searchSMSType eq 'N'}">checked="checked"</c:if> value="N"> 수신거부</label>
        </td>
      </tr>
      <tr>
        <th>목록건수</th>
            <td>
			<select id="onePageDataCountCondition" name="onePageDataCountCondition" class="count">
	            <c:forEach var="i" begin="50" end="300" step="50">
	            <c:choose>
	            <c:when test="${i eq pageCondition}">
	               <option value="${i}" selected>${i}</option>
	            </c:when>
	            <c:otherwise>
	               <option value="${i}">${i}</option>
	            </c:otherwise>
	            </c:choose>
	            </c:forEach>
            </select>
          </td>
          <td colspan="2"></td>
      </tr>
    </table>
    <a href="#" class="btn_apply mb100">조회</a>

	<form id="commonform" name="commonform" method="get" action="/member_modify_action">
    <!-- 아이디를 제외한 모든 정보(패스워드 포함) 수정// -->
    <input type="hidden" name="userId" id="userId" value="${userDto.userId}"> 
    
    <table class="modify">
      <colgroup>
        <col width="20.5%">
        <col width="*">
      </colgroup>
      <tr>
        <th>아이디</th>
        <td>
        ${userDto.userId}
        </td>
      </tr>
      <tr>
        <th>이름</th>
        <td>
          <input type="text" name="userName" id="userName" value="${userDto.userName}" placeholder="사용자 이름 불러와서 뿌려줍니다.">
        </td>
      </tr>
      <tr>
        <th>비밀번호</th>
        <td>
          <input type="password" name="password" id="password" value="">
          <!-- <p>6~20자의 영문 대소문자와 숫자, 특수문자를 사용할 수 있으며, 최소 2종류이상을 조합해야 합니다.<br/>허용 특수문자 { } [ ] ( ) / | ? ! . * ~ ‘ ^ - _ + # $ % =</p> -->
        </td>
      </tr>
      <tr>
        <th>비밀번호 확인</th>
        <td>
          <input type="password" name="password2" id="password2" value="">
        </td>
      </tr>
      <tr>
        <th>생년월일</th>
        <td>
          <input type="date" name="birthDate" id="birthDate" value='${userDto.birthDate}'>
        </td>
      </tr>
      <tr>
        <th>성별</th>
        <td>
          <label class="gender"><input type="radio" name="gender" <c:if test="${userDto.gender eq '남'}">checked="checked"</c:if> value='남'> 남</label>
          <label class="gender"><input type="radio" name="gender" <c:if test="${userDto.gender eq '여'}">checked="checked"</c:if> value='여'> 여</label>
        </td>
      </tr>
      <tr>
        <th>최근접속일</th>
        <td>
          ${userDto.lastConnectDate}
        </td>
      </tr>
      <tr>
        <th>가입일</th>
        <td>
          ${userDto.joinDate}
        </td>
      </tr>
      <tr>
        <th>휴면계정 전환일</th>
        <td>
         ${userDto.dormantAccountDate}
        </td>
      </tr>
      <tr>
        <th>연락처</th>
        <td>
           <!--<select id="callNumber" name="callNumber">
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
           </select>-->
          <input type="tel" maxlength="4" name="callNumber" id="callNumber" value="${callNumber}" placeholder="0000">
          <input type="tel" maxlength="4" name="callNumber2" id="callNumber2" value="${callNumber2}" placeholder="0000">
          <input type="tel" maxlength="4" name="callNumber3" id="callNumber3" value="${callNumber3}" placeholder="0000">
        </td>
      </tr>
      <tr>
        <th>휴대전화</th>
        <td>
          <!--<select id="" name="">
            <option value="010">010</option>
          </select>-->
          <input type="tel" maxlength="4" name="phoneNumber" id="phoneNumber" value="${phoneNumber}"  	placeholder="0000">
          <input type="tel" maxlength="4" name="phoneNumber2" id="phoneNumber2" value="${phoneNumber2}" placeholder="0000">
          <input type="tel" maxlength="4" name="phoneNumber3" id="phoneNumber3" value="${phoneNumber3}" placeholder="0000">
        </td>
      </tr>
      <tr>
        <th>이메일</th>
        <td>
          <input type="email" name="emailAddress" id="emailAddress" value="${userDto.emailAddress}" placeholder="이메일 불러와서 뿌려줍니다.">
        </td>
      </tr>

      <tr>
        <th>SMS 수신동의</th>
        <td>
          <p>원서접수, 자격증 발급 관련 정보를 SMS를 이용하여 서비스하고 있습니다.<br/>동의하지 않을 경우 <strong>정보가 누락</strong>될 수 있습니다.</p>
          <label><input type="checkbox" id="isReceiveEmail" name="isReceiveEmail"  <c:if test="${userDto.isReceiveEmail eq 'Y'}">checked="checked"</c:if>/> 수신 동의합니다</label>
        </td>
      </tr>
      <tr>
        <th>이메일 수신동의</th>
        <td>
          <p>마케팅, 홍보관련 자료 및 원서접수와 관련된 정보를 이메일로 발송합니다.<br/>동의하지 않을 경우 <strong>정보가 누락</strong>될 수 있습니다.</p>
          <label><input type="checkbox" id="isReceiveSms" name="isReceiveSms" <c:if test="${userDto.isReceiveSms eq 'Y'}">checked="checked"</c:if>/> 수신 동의합니다</label>
        </td>
      </tr>
    </table>
    <a onclick="return false;" id="btn_modify" class="btn_apply mb100">정보수정</a>
    <!-- //아이디를 제외한 모든 정보(패스워드 포함) 수정 -->
    </form>
  </div>
</div>

</body>
</html>
