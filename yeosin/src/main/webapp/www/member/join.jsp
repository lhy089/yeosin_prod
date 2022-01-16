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

  <link rel="stylesheet" href="../inc/css/member.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/member/join.js?t=1"></script>
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="member join">
  <div class="contentBox">
    <h1 class="commonTop">회원가입</h1>
    <div class="joinBox">
      <!-- (1단계)인트로// -->
      <section class="intro">
        개인회원가입
        <a class="btn_next" id="btn_doJoinProvision" onclick="return false;">만14세이상</a>
      </section>
      <!-- //(1단계)인트로 -->

      <!-- (2단계)약관// -->
      <section class="provision">
        <div><label class="agree"><input type="checkbox" name="check" value="만 14세 이상"> 만 14세 이상입니다.</label></div>
        <div>
          <label class="agree"><input type="checkbox" name="check" value="이용약관 동의"> 한국생산성본부 자격인증본부 이용약관</label>
          <div class="content">
            <!-- '한국생산성본부 자격인증본부 이용약관' 들어갈 자리 -->
          </div>
        </div>
        <div>
          <label class="agree"><input type="checkbox" name="check" value="개인정보수집 이용 동의"> 개인정보수집 이용 동의서</label>
          <div class="content">
            <!-- '개인정보수집 이용 동의서' 들어갈 자리 -->
          </div>
        </div>
        <div>
          <label class="agree"><input type="checkbox" name="check" value="개인정보의 제 3자 제공 동의"> 개인정보의 제 3자 제공</label>
          <div class="content">
            <!-- '개인정보의 제 3자 제공' 들어갈 자리 -->
          </div>
        </div>
        <div class="btnWrap">
          <a href="#none" class="btn_all">회원가입 약관에 모두 동의합니다</a>
          <a href="/www/index.jsp" class="btn_cancel">취소</a>
          <a class="btn_join" id="btn_doJoinCertification" onclick="return false;">회원가입</a>
        </div>
        <p class="announcement">
          2006년 9월 24일부터 개정된 ‘주민등록법’에 의해 타인의 주민등록번호를 도용하여 웹사이트에 가입하는 등의 주민등록 번호에<br/>
          대한 단순 부정사용에 대하여서도 3년 이하의 징역 또는 1천만원 이하의 벌금이라는 무거운 형벌을 부과하도록 하고 있습니다.<br/>
          관련 법률 : 주민등록법 제 21조(벌칙) 제 2항 9호 (2006년 9월 24일 시행)<br/>
          만약, 타인의 주민번호를 도용하여 ITQ 회원으로 가입하신 회원이 있으시다면,도용 행위를 즉시 중지하여 주시기 바랍니다.<br/>
          위와 관련하여 타인의 주민번호를 이용하여 원서접수, 자격증 신청및 기타에도 사전 및 사후 발견시에 실격처리됨을 알려드립니다.
        </p>
      </section>
      <!-- //(2단계)약관 -->

      <!-- (3단계)본인인증// -->
      <section class="certification">
        <ul>
          <li class="phone">
            <h3>휴대폰 본인인증</h3>
            <span>본인명의 휴대폰 번호로 인증</span>
            <div>
              <p>
                <a href="#">휴대폰 본인인증</a>
              </p>
            </div>
          </li>
          <li class="public">
            <h3>공동인증서인증</h3>
            <span>(구 공인인증서)</span>
            <div>
              <p>
                <a href="#">공동인증서인증</a>
              </p>
            </div>
          </li>
          <li class="ipin">
            <h3>I-PIN 인증</h3>
            <span>주민번호 대체 서비스</span>
            <div>
              <p>
                <a href="#">I-PIN 인증</a>
                <a href="#">I-PIN 발급신청</a>
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
      <!-- //(3단계)본인인증 -->

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
              <input type="text" name="" value="" id="userName">
            </td>
          </tr>
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
              <p>6~20자의 영문 대소문자와 숫자, 특수문자를 사용할 수 있으며, 최소 2종류이상을 조합해야 합니다.</br>허용 특수문자 { } [ ] ( ) / | ? ! . * ~ ‘ ^ - _ + # $ % =</p>
            </td>
          </tr>
          <tr>
            <th class="essential">비밀번호 확인</th>
            <td>
              <input type="password" name="" value="" id="userPwd2">
            </td>
          </tr>
        </table>

        <h1>추가정보</h1>
        <table>
          <colgroup>
            <col width="20.5%">
            <col width="*">
          </colgroup>
          <tr>
            <th>연락처</th>
            <td>
              <select id="" name="">
                <option value="02">02 서울</option>
                <option value="031">031 경기</option>
              </select>
              <input type="tel" maxlength="4" name="" value="">
              <input type="tel" maxlength="4" name="" value="">
              <p>수신동의 하시면 안내 및 수험 정보를 받으실 수 있습니다.</br>수신동의와 별도로 비밀번호 찾기에 사용되므로 <strong>반드시 입력</strong> 바랍니다.</p>
              <label><input type="checkbox" id="" name="agreeChk"/> 수신 동의합니다</label>
            </td>
          </tr>
          <tr>
            <th>휴대전화</th>
            <td>
              <select id="" name="">
                <option value="010">010</option>
              </select>
              <input type="tel" maxlength="4" name="" value="">
              <input type="tel" maxlength="4" name="" value="">
              <p>수신동의 하시면 안내 및 수험 정보를 받으실 수 있습니다.</br>수신동의와 별도로 비밀번호 찾기에 사용되므로 <strong>반드시 입력</strong> 바랍니다.</p>
              <label><input type="checkbox" id="" name="agreeChk"/> 수신 동의합니다</label>
            </td>
          </tr>
          <tr>
            <th>이메일</th>
            <td>
              <input type="email" name="" value="">
              <p>수신동의 하시면 안내 및 수험 정보를 받으실 수 있습니다.</br>수신동의와 별도로 비밀번호 찾기에 사용되므로 <strong>반드시 입력</strong> 바랍니다.</p>
              <label><input type="checkbox" id="" name="agreeChk"/> 수신 동의합니다</label>
            </td>
          </tr>
        </table>

        <a href="#" class="btn_apply" id="btn_doJoinFinish">가입하기</a>
      </section>
      <!-- //(4단계)기입 -->

      <!-- 완료// -->
      <section class="finish">
        <div class="completed">
          <div>
            <h3>회원가입 완료</h3>
            <p>고객과 함께 하는 한국생산성본부가 되기 위해 최선을 다할 것을 약속드리며,<br/>언제나 보다 나은 서비스로 찾아뵙겠습니다.</p>
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
            <td>아무개</td>
          </tr>
          <tr>
            <th>아이디</th>
            <td>abc123</td>
          </tr>
          <tr>
            <th>연락처</th>
            <td>02-</td>
          </tr>
          <tr>
            <th>휴대폰번호</th>
            <td>
              수신 동의합니다 <input type="checkbox" id="" name="agreeChk" onclick="return false" checked/><!--동의 했을 때 'checked' 포함-->
              010-0000-0000
            </td>
          </tr>
          <tr>
            <th>이메일</th>
            <td>
              수신 동의합니다 <input type="checkbox" id="" name="agreeChk" onclick="return false"/>
              abc123@test.com
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
<script>
$(function(){
  /* 인트로 제외 회원가입 순서 숨기기 */
  $('.joinBox section').not('.intro').hide();

  /* 전체 선택 */
  $('.btn_all').click(function() {
		if($(this).hasClass('all_chk')){  //전체선택 해제
      $("input[name=check]").prop("checked", false);
      $('.btn_all').removeClass('all_chk');
    } else {  //전체선택
      $("input[name=check]").prop("checked", true);
      $('.btn_all').addClass('all_chk');
    }
	});

  $("input[name=check]").click(function(){
    var total = $('input[name=check]').length;
    var checked = $('input[name=check]:checked').length;

    if(total != checked) {  //전체선택 해제
      $('.btn_all').removeClass('all_chk');
    } else {  //전체선택
      $('.btn_all').addClass('all_chk');
    }
  });
});
</script>
</body>
</html>

