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

<div class="manage schedule">
  <div class="contentBoxAd">
    <h1 class="title">시험운영관리</h1>
    <h2>시험일정관리</h2>
    <div class="selectTable">
      <table>
        <colgroup>
          <col width="10.5%">
          <col width="20.5%">
          <col width="10.5%">
          <col width="25.5%">
          <col width="11.5%">
          <col width="*">
        </colgroup>
        <tr>
          <th>시험년도</th>
          <td>
            <select id="" name="">
              <option value="2021">2021</option>
            </select>
          </td>
          <th>시험명</th>
          <td>
            <select id="" name="" class="exams">
              <option value="대출성 상품 판매대리 · 중개">대출성 상품 판매대리 · 중개</option>
            </select>
          </td>
          <th>시험차수</th>
          <td>
            <select id="" name="">
              <option value="1">1</option>
            </select>
          </td>
        </tr>
      </table>
    </div>
    <a href="#" class="btn_apply mb100">조회</a>

    <ul class="btn_wrap">
      <li><a href="#">엑셀다운로드</a></li>
      <li><a href="#">등록하기</a></li>
      <li><a href="#">정보삭제</a></li>
    </ul>
    <table class="mb100">
      <colgroup>
        <col width="4%">
        <col width="4%">
        <col width="14%">
        <col width="*">
        <col width="14%">
        <col width="14%">
        <col width="14%">
      </colgroup>
      <tr>
        <th>선택</th>
        <th>번호</th>
        <th>시험년도</th>
        <th>시험명</th>
        <th>시험차수</th>
        <th>시험일</th>
        <th>비고</th>
      </tr>
      <tr class="center">
        <td><input type="checkbox" name="memberCheck" value=""></td>
        <td>1</td>
        <td>2021</td>
        <td class="flow flowName"><p>대출성 상품 판매대리 · 중개</p></td>
        <td>1</td>
        <td>20211026</td>
        <td><a href="#" class="btn_more">자세히 보기</a></td>
      </tr>
    </table>
  </div>
</div>

</body>
</html>
