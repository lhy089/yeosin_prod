<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="ko">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta charset="utf-8">
<title>대출성 상품 판매대리•중개업자 등록 자격인증 평가</title>
<meta name="description" content="여신금융협회">
<meta name="keywords" content="원서접수, 평가응시현황, 시험안내, 알림마당, 회원정보">
<meta name="viewport"
	content="user-scalable=no,
   initial-scale=1.0,
   maximum-scale=1.0,
   minimum-scale=1.0,
   width=device-width,
   height=device-height">
<meta property="og:type" content="website">
<meta property="og:site_name" content="여신금융협회" />
<!-- <meta property="og:url" content="사이트url"> -->
<meta property="og:title" content="대출성 상품 판매대리•중개업자 등록 자격인증 평가">
<meta property="og:description" content="대출성 상품 판매대리•중개업자 등록 자격인증 평가">
<meta property="og:image" content="/www/inc/img/openGraph.jpg">
<link rel="shortcut icon" href="/www/inc/img/favicon.png" />
<link rel="icon" href="/www/inc/img/favicon.png" type="image/x-icon">

<link rel="stylesheet" href="/www/inc/css/member.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/member/checkPlus.js?t=<%= new java.util.Date() %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member/find.js?t=<%= new java.util.Date() %>"></script>
</head>

<body>

	<!--?php include_once "../common/header.php";?-->
	<%@ include file="/www/common/header.jsp"%>
<!-- 	<form id="commonform" name="commonform" method="get"> -->
		<input type="hidden" name="birthDate" id="birthDate">

		<div class="member find">
			<div class="contentBox">
				<h1>아아디 / 비밀번호 확인</h1>
				<div class="tabBox">
					<a onclick="return false;" class="active">아이디 확인</a> 
					<a href="/find_pwd">비밀번호 확인/변경</a>
				</div>
				<div class="choice">
					<h2>아이디 찾는 방법을 선택해 주세요</h2>
					<ul>
					

						<!-- 아이핀 본인 인증// -->
						<li>
							<h4>
								<a href="#none">아이핀 본인 인증</a>
							</h4>
							<div class="proveBox">
								<p>아이핀 인증이 완료된 후, 해당 명의(이름)로 가입된 아이디를 찾습니다.</p>
								<p>아이핀이 없다면, 신규로 발급 받아 사용할 수 있습니다.</p>
								<a href="javascript:fnPopupIpin();" class="btn_apply">아이핀
									인증하기</a>
							</div>
						</li>
						<!-- //아이핀 본인 인증 -->

						<!-- 휴대전화 본인 인증// -->
						<li>
							<h4>
								<a href="#none">본인 명의(이름)로 가입 된 휴대전화 본인 인증</a>
							</h4>
							<div class="proveBox">
								<p>휴대전화로 본인 인증 후, 해당 명의(이름)로 가입된 아이디를 찾습니다.</p>
								<a href="javascript:fnPopup('M');" class="btn_apply">휴대전화
									인증하기</a>
							</div>
						</li>
						<!-- //휴대전화 본인 인증 -->

						<!-- 이름,생년월일,성별로 찾기// -->
						<li>
							<h4>
								<a href="#none">이름 / 생년월일 / 성별로 찾기</a>
							</h4>
							<div class="proveBox">
								<p>회원정보의 이름/생년월일/성별로 가입한 아이디의 일부분을 볼 수 있습니다.</p>
								<table>
									<colgroup>
										<col width="20.5%">
										<col width="*">
									</colgroup>
									<tr>
										<th>이름</th>
										<td><input type="text" id="userName" name="userName"></td>
									</tr>
									<tr>
										<th>생년월일</th>
										<td><input type="text" id="year">년 <input
											type="text" id="month">월 <input type="text" id="day">일
										</td>
									</tr>
									<tr>
										<th>성별</th>
										<td><label class="idType"><input type="radio"
												name="gender" value="남" checked="checked"><span>남자</span></label>
											<label class="idType"><input type="radio"
												name="gender" value="여"><span>여자</span></label></td>
									</tr>
								</table>
								<a onclick="return false;" class="btn_apply center" id="btn_ok">확인</a>
							</div>
						</li>
						<!-- //이름,생년월일,성별로 찾기 -->
					</ul>
				</div>
			</div>
		</div>
		
		<form name="form_chk" method="post">
			<input type="hidden" name="m" value="checkplusService">
			<!-- 필수 데이타로, 누락하시면 안됩니다.휴대폰,공동 -->
			<input type="hidden" name="m" value="pubmain">
			<!-- 필수 데이타로, 누락하시면 안됩니다. i-pin-->
			<input type="hidden" name="EncodeData" id="encodeData" value="">
			<!-- 위에서 업체정보를 암호화 한 데이타입니다.휴대폰,공동 -->
			<input type="hidden" name="enc_data" id="encodeDataForIpin" value="">
			<!-- 위에서 업체정보를 암호화 한 데이타입니다. i-pin-->
		</form>
		<!-- 아이핀 가상주민번호 서비스 팝업 인증결과 전달 form -->
		<form name="vnoform" method="post">
			<!-- 인증결과 암호화 데이터 -->
			<input type="hidden" name="enc_data">
		</form>
		<input type="hidden" id="findType" value="id">
		<!-- 팝업// -->
		<div class="popWrap">
			<div class="pop">
				<h1>
					<p>문자/메일이 오지 않을 경우</p>
					<a href="#none" class="btn_close"></a>
				</h1>
				<!-- 휴대폰인증 문구// -->
				<p class="text phone">
					1577번호가 스팸 문자로<br /> 등록되어 있는지 확인해주세요.<br /> 스팸 문자로 등록되어 있지 않다면 <br />
					‘전송’을 다시 눌러주세요.
				</p>
				<!-- //휴대폰인증 문구 -->
				<!-- 이메일인증 문구// -->
				<p class="text email">
					홈페이지명이 발송한 메일이 스팸 메일로<br /> 분류된 것은 아닌지 확인해주세요.<br /> 스팸 메일로 등록 되어
					있지 않다면<br /> ‘전송’을 다시 눌러주세요.
				</p>
				<!-- //이메일인증 문구 -->
				<a href="#none" class="btn_apply">확인</a>
			</div>
		</div>
<!-- 	</form> -->
	<!-- //팝업 -->
	<%@ include file="/www/common/footer.jsp"%>
	<!--?php include_once "../common/footer.php";?-->
	<script>
		$(function() {
			/* 선택 항목 열기 */
			$('.choice ul li h4 a').click(function() {
				if ($(this).parents('li').attr('class') == 'active') {
					$(this).parents('li').removeClass('active');
					$(this).parent('h4').next('.proveBox').slideUp(300);
				} else {
					$('.choice ul li').removeClass('active');
					$(this).parents('li').addClass('active');
					$('.proveBox').slideUp(300);
					$(this).parent('h4').next('.proveBox').slideDown(300);
				}
			});

			/* 인증 안내 팝업 */
			$('.btn_guide').click(function() {
				$('.popWrap').fadeIn(300);
				var text = $(this).attr('class').replace('btn_guide ', ''); //해당 팝업 문구 노출
				$('.popWrap .text').hide();
				$('.popWrap .text.' + text).show();
			});

			/* 팝업 닫기 */
			$('.popWrap .btn_apply,.popWrap .btn_close').click(function() {
				$('.popWrap').fadeOut(300);
			});
		});
	</script>
</body>
</html>
