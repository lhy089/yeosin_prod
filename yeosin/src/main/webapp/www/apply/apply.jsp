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

  <link rel="stylesheet" href="/www/inc/css/apply.css">
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="apply list">
  <div class="contentBox">
    <h1>원서접수</h1>
    <div class="announcement">
      인터넷접수 첫날의 접수 시작 시간은 10:00 부터입니다.
    </div>
    <c:choose>
	<c:when test='${result}'>
    <table>
      <colgroup>
        <col width="34.7%">
        <col width="*">
      </colgroup>
      <tr>
        <th>시험명</th>
        <td>${exam_name}</td>
      </tr>
      <tr>
        <th>접수기간</th>
        <td>제20XX-X회차</td>
      </tr>
      <tr>
        <th>시험일</th>
        <td>관리자 등록</td>
      </tr>
      <tr>
        <th>시험지역</th>
        <td>관리자 등록</td>
      </tr>
      <tr>
        <th>원서접수</th>
        <td><a href="#" class="btn_apply">접수하기</a></td>
      </tr>
    </table>
    </c:when>
    <c:otherwise>
    	표시할 데이터가 없습니다.
    </c:otherwise>
    </c:choose>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>
