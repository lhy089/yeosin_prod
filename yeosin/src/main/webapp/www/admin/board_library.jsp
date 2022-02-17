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

<div class="board">
  <div class="contentBoxAd">
    <h1 class="title">게시판</h1>
    <h2>시험자료실</h2>
    <ul class="btn_wrap">
      <li><a href="board_library_input.html">등록하기</a></li>
    </ul>
    <table class="list">
      <colgroup>
        <col width="4%">
        <col width="4%">
        <col width="58%">
        <col width="8%">
        <col width="14%">
        <col width="12%">
      </colgroup>
      <tr>
        <th>선택</th>
        <th>번호</th>
        <th>제목</th>
        <th>첨부파일</th>
        <th>등록날짜</th>
        <th>수정</th>
      </tr>
      <tr class="center">
        <td><input type="checkbox" name="memberCheck" value=""></td>
        <td>1</td>
        <td class="flow flowTitle"><a href="board_library_revise.html">첫번째 제목</a></td>
        <td></td>  <!--첨부파일 있을 시 'file'클래스 명 추가.-->
        <td>2022-01-01</td>
        <td><a href="board_library_revise.html" class="btn_more">수정하기</a></td>
      </tr>
      <tr class="center">
        <td><input type="checkbox" name="memberCheck" value=""></td>
        <td>2</td>
        <td class="flow flowTitle"><a href="#">두번째 제목</a></td>
        <td class="file"></td>
        <td>2022-01-02</td>
        <td><a href="#" class="btn_more">수정하기</a></td>
      </tr>
    </table>
    <a href="#" class="btn_apply mb100">선택 삭제</a>
  </div>
</div>

</body>
</html>
