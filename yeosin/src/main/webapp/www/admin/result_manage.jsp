<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="ko">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  <meta charset="utf-8">
  <title>[admin]대출성 상품 판매대리•중개업자 등록 자격인증 평가</title>
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

  <link rel="stylesheet" href="/www/inc/css/admin.css">
</head>

<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>

<div class="result manage">
  <div class="contentBoxAd">
    <h1 class="title">성적관리</h1>
    <h2>성적처리</h2>
    <table class="list">
      <colgroup>
        <col width="4%">
        <col width="4%">
        <col width="22.5%">
        <col width="10.5%">
        <col width="14%">
        <col width="22.5%">
        <col width="12%">
        <col width="10.5%">
      </colgroup>
      <tr>
        <th>선택</th>
        <th>번호</th>
        <th>시험고유번호</th>
        <th>차수</th>
        <th>시험일시</th>
        <th>시험영역</th>
        <th>결과 등록</th>
        <th>처리결과</th>
      </tr>
      <tr class="center">
        <td><input type="checkbox" name="memberCheck" value=""></td>
        <td>1</td>
        <td class="flow flowNum"><p>LPBQ220101</p></td>
        <td>22-1</td>
        <td>2022-01-01</td>
        <td class="flow flowSub"><p>리스할부상품</p></td>
        <td><a href="#" class="btn_more">등록하기</a></td>
        <td>완료</td>
      </tr>
    </table>
  </div>
</div>

</body>
</html>
