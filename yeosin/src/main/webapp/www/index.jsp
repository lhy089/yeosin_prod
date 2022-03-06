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
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/www/inc/css/main.css?t=<%= new java.util.Date() %>">
	
</head>

<body>

<!--?php include_once "common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="main">
  <section class="cont01">
    <div class="contentBox">
      <div class="schedule">
        <h2>평가일정 안내</h2>
        <ul>
          <li>제목 : <span id="examSubject">제${examInfo.examYear}-${examInfo.examDegree}차 ${examInfo.examName}</span></li>
          <li>원서접수 : <span id="receiptDate">${examInfo.receiptStartDate} ~ ${examInfo.receiptEndDate}</span></li>
          <li>수험표발급기간 : <span id="certPrintDate">${examInfo.certPrintStartDate} ~ ${examInfo.certPrintEndDate}</span></li>
          <li>평가일 : <span id="examDate">${examInfo.examDate}</span></li>
          <li>합격자발표일 : <span id="gradeDate">${examInfo.gradeStartDate}</span></li>
        </ul>
      </div>
    </div>
  </section>
	<c:choose>
	<c:when test='${examInfo ne null}'>
  <section class="cont02">
    <div class="contentBox">
      <ul class="quickMenu">
        <li class="report"><a href="/examGuide">평가 소개</a></li>
        <li class="guide"><a href="/registerGuide">응시 안내</a></li>
        <li class="library"><a href="/library?boardType=3">시험자료실</a></li>
        <li class="apply"><a href="/apply">원서접수신청</a></li>
        <li class="info"><a href="/accept">나의 접수정보</a></li>
        <li class="question"><a href="/question?boardType=2">자주하는 질문</a></li>
      </ul>
    </div>
  </section>
  </c:when>
  </c:choose>

  <section class="cont03">
    <div class="contentBox">
      <div class="notice">
        <h2>공지사항</h2>
        <ul>
        <c:forEach var="notice" items="${noticeList}">
          <li><a href="/notice_view?boardType=${notice.boardType}&boardSequence=${notice.boardSequence}&page=${notice.page}&searchWord=${notice.searchWord}&searchType=${notice.searchType}">${notice.title}</a><span class="date">${notice.writeTime}</span></li>
         </c:forEach>
        </ul>
      </div>
      <div class="crm">
        <h2>고객센터</h2>
        <ul>
          <li>Tel1 : <span>070-5129-1824</span></li>
          <li>Tel2 : <span>02-2011-0770</span></li>
        </ul>
        <h3>상담시간</h3>
        <p><em>평일 </em>09시 00분 ~ 18시 00분</p>
        <p><em>점심시간 </em>12시 00분 ~ 13시 00분</p>
        <p class="red">토요일, 일요일 및 공휴일은 휴무</p>
      </div>
    </div>
    </section>
    <!-- 팝업// -->
  <script>
    $( document ).ready(function() {
     var userStatus = "${loginUserInfo.userStatus}";
      cookiedata = document.cookie;
      if ( (userStatus=="" || userStatus=='C') && cookiedata.indexOf("ncookie=done") < 0 ){
        document.getElementById('layer_pop').style.display = "block";    //  팝업창 아이디
      } else {
        document.getElementById('layer_pop').style.display = "none";    // 팝업창 아이디
      }
    });

    function setCookie( name, value, expiredays ) {
      var todayDate = new Date();
      todayDate.setDate( todayDate.getDate() + expiredays );
      document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
    }

    function closeWin() {
      document.getElementById('layer_pop').style.display = "none";    // 팝업창 아이디
    }

    function todaycloseWin() {
      setCookie( "ncookie", "done" , 1 );     // 저장될 쿠키명 , 쿠키 value값 , 기간( ex. 1은 하루, 7은 일주일)
      document.getElementById('layer_pop').style.display = "none";    // 팝업창 아이디
    }
  </script>
  <div class="popWrap" id="layer_pop">
    <div class="pop">
      <h1>
        <p>기존 평가 홈페이지 회원 로그인 안내</p>
        <a href="#none" onClick="closeWin();" class="btn_close"></a>
      </h1>
      <p class="text">
        자격인증평가 홈페이지 개편으로 인하여<br/>
        기존에 가입하셨던 회원들은 패스워드<br/>
        재설정 후 이용이 가능합니다.<br/>
        조금 불편하시더라도 비밀번호 찾기를<br/>
        통해 패스워드 변경 후 로그인 부탁드립니다.
      </p>
      <a href="/find_pwd" class="btn_apply">비밀번호 찾기</a>
      <div class="closeWrap">
      	<a href="#none" onClick="closeWin();" class="btn_close">닫기</a>
      	<a href="#none" onClick="todaycloseWin();" class="btn_closeToday">오늘 하루 보지 않기</a>
      </div>
    </div>
  </div>
  <!-- //팝업 -->
  
   <!-- 주차 안내 팝업// -->
  <script>
    $( document ).ready(function() {
      cookiedata = document.cookie;
      if ( cookiedata.indexOf("ncookie220214=done") < 0 ){
        document.getElementById('alert_220214').style.display = "block";    //  팝업창 아이디
      } else {
        document.getElementById('alert_220214').style.display = "none";    // 팝업창 아이디
      }
    });

    function setCookie220214( name, value, expiredays ) {
      var todayDate = new Date();
      todayDate.setDate( todayDate.getDate() + expiredays );
      document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
    }

    function closeWin220214() {
      document.getElementById('alert_220214').style.display = "none";    // 팝업창 아이디
    }

    function todaycloseWin220214() {
      setCookie220214( "ncookie220214", "done" , 1 );     // 저장될 쿠키명 , 쿠키 value값 , 기간( ex. 1은 하루, 7은 일주일)
      document.getElementById('alert_220214').style.display = "none";    // 팝업창 아이디
    }
  </script>
  <div class="alertPop" id="alert_220214">
    <a href="#none" onClick="closeWin220214();" class="btn_close"></a>
    <p class="text">
      <br/>
      시험장이 협소한 관계로<br/>
      서울 시험장 (대방중학교),<br/>
      부산 시험장(경남공업고등학교)의 경우<br/>
      주차가 절대 불가합니다.<br/><br/>

      주차와 관련하여<br/>
      발생할 수 있는 일에 대해서는<br/>
      본 기관에서 책임을 지지 않습니다.
    </p>
    <div class="closeWrap">
      <a href="#none" onClick="closeWin220214();" class="btn_close">닫기</a>
      <a href="#none" onClick="todaycloseWin220214();" class="btn_closeToday">오늘 하루 보지 않기</a>
    </div>
  </div>
  <!-- //주차 안내 팝업 -->
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "common/footer.php";?-->
</body>
</html>
