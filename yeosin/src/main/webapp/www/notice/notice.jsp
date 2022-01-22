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
<div class="notice library">
  <div class="contentBox">
    <h1>공지사항</h1>
    <div class="searchBox">
      <select id="" name="">
        <option value="">조건검색</option>
      </select>
      <input type="text" name="" value="">
      <a href="#" class="btn_serch">검색</a>
    </div>
    <ul class="dataList">
      <c:forEach var="notice" items="${noticeList}">	
      	<li>
        	<a href="/notice_view?boardType=${notice.boardType}&boardSequence=${notice.boardSequence}">${notice.title}</a>
        	<div>
          		<p class="num">No.${notice.boardSequence}</p>
         	 	<p class="viewCount">조회수 : ${notice.hitCnt}</p>
         	 	 <p class="document"></p>
       		</div>
      	</li>
      </c:forEach>
    </ul>

    <p class="pageCnt">전체 7건, 1/1 페이지</p>
    <div class="pageWrap">
      <!-- 페이징 -->
    </div>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>