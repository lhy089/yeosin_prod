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
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
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
          <li>Tel1 : <span>1522-9581</span></li>
        </ul>
        <h3>상담시간</h3>
        <p><em>평일 </em>09시 00분 ~ 18시 00분</p>
        <p><em>점심시간 </em>12시 00분 ~ 13시 00분</p>
        <p class="red">토요일, 일요일 및 공휴일은 휴무</p>
      </div>
    </div>
    </section>
    
    <!-- 팝업 -->
	<script>
    $(document).ready(function() {
    	
      var cookie_0 = $("#cookie_0").val() + "=done";
      var cookie_1 = $("#cookie_1").val() + "=done";
      var cookie_2 = $("#cookie_2").val() + "=done";
    	
      cookiedata = document.cookie;
      
      // 팝업 1
      if (cookiedata.indexOf(cookie_0) < 0) 
      {
  		if ($("#layer_pop0").length > 0)
		{
  			document.getElementById('layer_pop0').style.display = "block"; // 팝업창 아이디
		}
      } 
      else 
      {
   		if ($("#layer_pop0").length > 0)
   		{
   			document.getElementById('layer_pop0').style.display = "none"; // 팝업창 아이디
   		}
      }
      
      // 팝업 2
      if (cookiedata.indexOf(cookie_1) < 0)
      {
    	if ($("#layer_pop1").length > 0)
    	{
      		document.getElementById('layer_pop1').style.display = "block"; // 팝업창 아이디
    	}
      } 
      else 
      {
  		if ($("#layer_pop1").length > 0)
  		{
    		document.getElementById('layer_pop1').style.display = "none"; // 팝업창 아이디
  		}
      }
      
      // 팝업 3
      if (cookiedata.indexOf(cookie_2) < 0)
      {
   		if ($("#layer_pop2").length > 0)
   		{
     		document.getElementById('layer_pop2').style.display = "block"; // 팝업창 아이디
   		}
	  } 
      else 
      {
   		if ($("#layer_pop2").length > 0)
   		{
     		document.getElementById('layer_pop2').style.display = "none"; // 팝업창 아이디
   		}
      }
	});
    
    // 쿠키 설정
    function setCookie(name, value, expiredays) {
        var todayDate = new Date();
        todayDate.setDate(todayDate.getDate() + expiredays);
        document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";"
     }

    // 닫기
    function closeWin(index) {
    	if (index == 0) {
    		if ($("#layer_pop0").length > 0)
    		{
    			document.getElementById('layer_pop0').style.display = "none"; // 팝업창 아이디
    		}
    	}
    	else if (index == 1){
    		if ($("#layer_pop1").length > 0)
    		{
    			document.getElementById('layer_pop1').style.display = "none"; // 팝업창 아이디
    		}
    	}
    	else if (index == 2){
    		if ($("#layer_pop2").length > 0)
    		{
    			document.getElementById('layer_pop2').style.display = "none"; // 팝업창 아이디
    		}
    	}
    }
    
    // 오늘하루 보지않기
    function todaycloseWin(cookieId, index) {
    	if(index == 0){
    		 setCookie(cookieId, "done" , 1); // 저장될 쿠키명 , 쿠키 value값 , 기간(ex. 1은 하루, 7은 일주일)
    	     document.getElementById('layer_pop0').style.display = "none"; // 팝업창 아이디
    	}
    	else if(index == 1){
    		  setCookie(cookieId, "done" , 1); // 저장될 쿠키명 , 쿠키 value값 , 기간(ex. 1은 하루, 7은 일주일)
    	      document.getElementById('layer_pop1').style.display = "none"; // 팝업창 아이디
    	}
    	else if(index == 2){
    		  setCookie(cookieId, "done" , 1); // 저장될 쿠키명 , 쿠키 value값 , 기간(ex. 1은 하루, 7은 일주일)
    	      document.getElementById('layer_pop2').style.display = "none"; // 팝업창 아이디
    	}
    }
	</script>
    
	<c:forEach var="popupInfo" items="${popupList}" varStatus="status">
		<div class="popWrap" id="layer_pop${status.index}">
			<div class="pop">
				<h1>
			       <p>${popupInfo.title}</p>
			       <a href="#none" onClick="closeWin(${status.index});" class="btn_close"></a>
			 	</h1>
			 	<p class="img">
					<c:url value="/popupImageView" var="popupImageView">
                    <c:param name="fileUrl" value="${popupInfo.fileDto.fileURL}" />
                    <c:param name="localFileName" value="${popupInfo.fileDto.localFileName}" />
                  	</c:url>
               	<img src="${popupImageView}" alt="팝업이미지"> 
			 	</p>
				<!-- <p class="text">
					${popupInfo.contents}
				</p> -->
			 	<div class="closeWrap">
			 		<a href="#none" onClick="closeWin(${status.index});" class="btn_close">닫기</a>
			 		<a href="#none" onClick="todaycloseWin('${popupInfo.cookieId}', ${status.index});" class="btn_closeToday">오늘 하루 보지 않기</a>
			 		<input type="hidden" id="cookie_${status.index}" value="${popupInfo.cookieId}">
			  	</div>
			</div>
		</div>
	</c:forEach>
	
</div>

<script>
$(function(){
	windowW = $(window).width();
	/* (모바일)팝업 위치 설정 */
	if (windowW < 768){
		var popBottomHeight = $(".popWrap .closeWrap").height(); //오늘하루닫기, 닫기 영역 높이
		var pop0Height = $("#layer_pop0 .pop").height()+popBottomHeight+10; //첫번째 팝업 높이값(+10은 하단여백)
		var pop1Height = $("#layer_pop1 .pop").height()+popBottomHeight+10;	 //두번째 팝업 높이값(+10은 하단여백)	

		//두번째 팝업 위치(y축)
		$("#layer_pop1 .pop").css('top',pop0Height);

		//세번째 팝업 위치(y축)
		$("#layer_pop2 .pop").css('top',pop0Height+pop1Height);
	}
});
</script>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "common/footer.php";?-->
</body>
</html>
