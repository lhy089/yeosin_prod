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
  
  if(${existUser == 'Y'}) {
	  alert("기존에 가입된 회원입니다.\n아이디를 분실하신 경우 아이디 찾기*를 통해 확인하시기 바랍니다.\n* 홈페이지 우측 상단 로그인 > 아이디 찾기");  
 } else if (${noCert == 'Y'}) {
	 alert("잘못된 정보입니다.");  
 }
  window.name ="Parent_window";
	
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
	
	function fnPopupIpin(){
		$.ajax({
	        type: "POST",
	        url: "/doOpenCertForIpin",
	        sendDataType : 'string',
	        success: function(data) { debugger;
	        	$("#encodeDataForIpin").val(data);
	        	window.open('', 'popupIPIN2', 'width=450, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
				document.form_chk.target = "popupIPIN2";
				document.form_chk.action = "https://cert.vno.co.kr/ipin.cb";
				document.form_chk.submit();
	        }
	      });
	}
	
	
	
			window.addEventListener('message', function(e) {
				debugger;
				var data = e.data;
				if (data.module == "ipin") {
					document.vnoform.enc_data.value = data.enc_data;
					document.vnoform.target = data.target;
					document.vnoform.action = data.action;
					document.vnoform.submit();
				} else if (data.module == "cert") { debugger;
					$("#userName").val(data.name);
					$("#birthDate").val(data.birth);
					$("#gender").val(data.gender);
					$("#diCode").val(data.diCode);
					$("#ciCode").val(data.ciCode);
					$("#findType").val(data.certInfoResult);
					
					$("#checkForm").submit();
				}
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

      <!-- (3단계)본인인증// -->
      <form name="form_chk" method="post">
      <section class="certification">
        <ul>
          <li class="phone">
            <h3>휴대폰 본인인증</h3>
            <span>본인명의 휴대폰 번호로 인증</span>
            <div>
              <p>
              
		<input type="hidden" name="m" value="checkplusService">						<!-- 필수 데이타로, 누락하시면 안됩니다.휴대폰,공동 -->
		<input type="hidden" name="m" value="pubmain">						<!-- 필수 데이타로, 누락하시면 안됩니다. i-pin--> 
		<input type="hidden" name="EncodeData" id="encodeData" value="">		<!-- 위에서 업체정보를 암호화 한 데이타입니다.휴대폰,공동 -->
	    <input type="hidden" name="enc_data" id="encodeDataForIpin" value="">	<!-- 위에서 업체정보를 암호화 한 데이타입니다. i-pin-->
		<a href="javascript:fnPopup('M');"> 휴대폰 본인인증</a>
	
              </p>
            </div>
          </li>
          <li class="public">
            <h3>공동인증서인증</h3>
            <span>(구 공인인증서)</span>
            <div>
              <p>
                <a href="javascript:fnPopup('U');">공동인증서인증</a>
              </p>
            </div>
          </li>
          <li class="ipin">
            <h3>I-PIN 인증</h3>
            <span>주민번호 대체 서비스</span>
            <div>
              <p>
				<!-- 인증요청 암호화 데이터 -->
                <a href="javascript:fnPopupIpin();">I-PIN 인증</a>
              </p>
            </div>
          </li>
        </ul>

        <h2>I-PIN 안내</h2>
        <div class="guide">
          아이핀(I-PIN)은 Internet Personal Identification Number의 약자로, 인터넷상 개인식별번호를 의미합니다.<br/>
          대면확인이 불가능한 인터넷상에서 주민등록번호를 대신하여, 본인임을 확인 받을 수 있는 사이버 신원 확인번호가 아이핀입니다.<br/>
          아이핀을 발급 받으셨다면 상단의 I-PIN 인증 받으시면 됩니다.
          <p>
            <a href="#">I-PIN 발급신청</a>
            <a href="#">SCI평가정보</a>
            <a href="#">코리아크레딧뷰로</a>
          </p>
        </div>
      </section>
      </form>
      <!-- 아이핀 가상주민번호 서비스 팝업 인증결과 전달 form -->
	<form name="vnoform" method="post">
		<!-- 인증결과 암호화 데이터 -->
		<input type="hidden" name="enc_data">								
	</form>
      <!-- //(3단계)본인인증 -->
    </div>
  </div>
   
   <form action="/checkCiDiCode" method="POST" name="checkForm" id="checkForm">
   		<input type="hidden" name="userName" id="userName" value="">
   		<input type="hidden" name="birthDate" id="birthDate" value="">
   		<input type="hidden" name="gender" id="gender" value="">
        <input type="hidden" name="diCode" id="diCode" value="">   
        <input type="hidden" name="ciCode" id="ciCode" value="">
        <input type="hidden" name="findType" id="findType" value="">
   </form>
   
</div>

<!--?php include_once "../common/footer.php";?-->
<%@ include file="/www/common/footer.jsp"%>
</body>
</html>

