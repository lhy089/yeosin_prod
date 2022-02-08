<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head lang="ko">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta charset="utf-8">
	
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
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/www/inc/css/main.css">
	
</head>

<body>

<!--?php include_once "common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="main">
  <section class="cont01">
    <div class="contentBox">
      <div class="schedule">
        <h2>시험일정 안내</h2>
        <ul>
          <li>제목 : <span id="examSubject">제${examInfo.examYear}-${examInfo.examDegree}차 ${examInfo.examName}</span></li>
          <li>원서접수 : <span id="receiptDate">${examInfo.receiptStartDate} ~ ${examInfo.receiptEndDate}</span></li>
          <li>수험표발급기간 : <span id="certPrintDate">${examInfo.certPrintStartDate} ~ ${examInfo.certPrintEndDate}</span></li>
          <li>평가일 : <span id="examDate">${examInfo.examDate}</span></li>
          <li>합격자발표기간 : <span id="gradeDate">${examInfo.gradeStartDate} ~ ${examInfo.gradeEndDate}</span></li>
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
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "common/footer.php";?-->
</body>
</html>
