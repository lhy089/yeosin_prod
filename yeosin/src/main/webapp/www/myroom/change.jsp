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
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/myroom/change.js?t=<%= new java.util.Date() %>"></script>
  <script>
  debugger;
  
  var isCert = false;
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
  
	function fnPopup(authType){
		
		$.ajax({
	        type: "POST",
	        url: "/doOpenCert",
	        data: {sAuthType : authType},
	        sendDataType : 'string',
	        success: function(data) { debugger;
	        	$("#encodeData").val(data);
	        	window.open('', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
	    		document.form_chk.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
	    		document.form_chk.target = "popupChk";
	    		document.form_chk.submit();
	        }
	      });
	}
	
	window.addEventListener('message', function(e) {
		isCert = true;
		debugger;
		var data = e.data;
		var birtDate = data.birth.substring(0, 4) + "-"
			+ data.birth.substring(4, 6) + "-"
			+ data.birth.substring(6, 8);
		var gender = data.gender == 0 ? "여" : "남"
		$("#userName").text(data.name);
		$("#birthDate").text(birtDate);
		$("#birthDate").attr("value", birtDate);
		$("#gender").text(gender);
		$("#gender").attr("value", gender);
		$("#diCode").val(data.diCode);
		$("#ciCode").val(data.ciCode);
	});
 
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
     <h3>
      인적사항 수정 안내
      <p>
        * 성명, 생년월일, 성별은 본인인증을 통해서 변경하실 수 있습니다.<br/>
        * 본인인증 후 하단 저장하기를 클릭해주시기 바랍니다.
      </p>
    </h3>
    <a href="javascript:fnPopup('A')" class="btn_apply prove" id="btn_cert">본인인증</a>
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
          <td id="userName">${userInfo.userName}</td>
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
          <td id="birthDate">${userInfo.birthDate}</td>
        </tr>
        <tr>
          <th>성별</th>
          <td id="gender">${userInfo.gender}</td>
        </tr>
    <!--     <tr>
          <th>내국인여부</th>
          <td></td>
        </tr> -->
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
      <a onclick="return false;" class="btn_apply" id="btn_save">저장하기</a>
    </section>
    
    <form name="form_chk" method="post">
		<input type="hidden" name="m" value="checkplusService">						<!-- 필수 데이타로, 누락하시면 안됩니다.휴대폰,공동 -->
		<input type="hidden" name="m" value="pubmain">						<!-- 필수 데이타로, 누락하시면 안됩니다. i-pin--> 
		<input type="hidden" name="EncodeData" id="encodeData" value="">		<!-- 위에서 업체정보를 암호화 한 데이타입니다.휴대폰,공동 -->
	    <input type="hidden" name="enc_data" id="encodeDataForIpin" value="">	<!-- 위에서 업체정보를 암호화 한 데이타입니다. i-pin-->
  	</form>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>

