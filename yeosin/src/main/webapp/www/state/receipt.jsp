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

  <link rel="stylesheet" href="../inc/css/state.css">
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="state receipt">
  <div class="contentBox">
    <h1>원서접수현황</h1>
    <div class="announcement">
      시험에 응시한 접수 정보를 확인 할 수 있습니다.
    </div>
    <table>
      <colgroup>
        <col width="*">
        <col width="19.5%">
        <col width="15%">
        <col width="15%">
        <col width="15%">
      </colgroup>
      <tr>
        <th>시험명</th>
        <th>진행상태</th>
        <th>시험일</th>
        <th>시험지역</th>
        <th>시험회차</th>
      </tr>
      <tr>
        <td>시험명표기</td>
        <td>접수중/시험완료</td>
        <td>2021-12-31</td>
        <td>도시명</td>
        <td>회차표기</td>
      </tr>
    </table>
    <p class="pageCnt">전체 0건, 1/0 페이지</p>
    <div class="pageWrap">
      <!-- 페이징 -->
    </div>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>

