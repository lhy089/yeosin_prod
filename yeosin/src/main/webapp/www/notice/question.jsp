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

  <link rel="stylesheet" href="/www/inc/css/notice.css">
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="notice question">
  <div class="contentBox">
    <h1>자주하는 질문</h1>
    <div class="announcement">
      자주하는 질문을 검색할 수 있습니다.
    </div>
    <div class="searchBox">
      <select id="" name="">
        <option value="">전체</option>
      </select>
      <select id="" name="">
        <option value="">조건검색</option>
      </select>
      <input type="text" name="" value="">
      <a href="#" class="btn_serch">검색</a>
    </div>
    <div class="questionList">
     <c:forEach var="question" items="${questionList}">	
      	<dl>
       		 <dt>${question.title}</dt>
       		 <dd>${question.contents}</dd>
      	</dl>
      </c:forEach> 
    </div>
    <p class="pageCnt">전체 7건, 1/1 페이지</p>
    <div class="pageWrap">
      <!-- 페이징 -->
    </div>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

<script>
$(function(){
  /* 자주하는 질문 여닫기 */
  $('.questionList dt').click(function(){
    if ($(this).attr('class') == 'open') {
      $(this).removeClass('open');
      $(this).next('dd').slideUp('fast');
    } else {  //아코디언 슬라이드
      $('.questionList dt').removeClass('open');
      $(this).addClass('open');
      $('.questionList dd').slideUp('fast');
      $(this).next('dd').slideDown('fast');
    }
  });
});
</script>
</body>
</html>