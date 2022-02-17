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

<div class="result list">
  <div class="contentBoxAd">
    <h1 class="title">성적관리</h1>
    <h2>채점표리스트</h2>
    <div class="selectTable">
      <table>
        <colgroup>
          <col width="7.7%">
          <col width="27%">
          <col width="7.7%">
          <col width="27%">
          <col width="10.5%">
          <col width="*">
        </colgroup>
        <tr>
          <th>검색</th>
          <td colspan="5">
            <input type="text" name="" value="">
          </td>
        </tr>
        <tr>
          <th>지역</th>
          <td>
            <select id="" name="">
              <option value="">전체</option>
            </select>
          </td>
          <th>과목</th>
          <td>
            <select id="" name="">
              <option value="">전체</option>
            </select>
          </td>
          <th>목록건수</th>
          <td>
            <select id="" name="" class="count">
              <option value="">30</option>
            </select>
          </td>
        </tr>
      </table>
    </div>
    <a href="#" class="btn_apply mb100">조회</a>

    <table class="list">
      <colgroup>
        <col width="20%">
        <col width="14%">
        <col width="9%">
        <col width="9%">
        <col width="4%">
        <col width="12%">
        <col width="7%">
        <col width="7%">
        <col width="9%">
        <col width="9%">
      </colgroup>
      <tr>
        <th>고사장</th>
        <th>수험번호</th>
        <th>성명</th>
        <th>생년월일</th>
        <th>성별</th>
        <th>유형</th>
        <th>고사실</th>
        <th>좌석번호</th>
        <th class="score">점수</th>
        <th class="accept">합격여부</th>
      </tr>
      <tr class="center">
        <td class="flow flowArea"><p>부산 경남공업고등학교</p></td>
        <td>B2021-00-0000</td>
        <td>이OO</td>
        <td>840908</td>
        <td>남</td>
        <td class="flow flowSub"><p>대출성 상품</p></td>
        <td>00</td>
        <td>00</td>
        <td>77.5</td>
        <td>합격</td>
      </tr>
      <tr class="center">
        <td class="flow flowArea"><p>부산 경남공업고등학교</p></td>
        <td>B2021-00-0000</td>
        <td>김OO</td>
        <td>840908</td>
        <td>여</td>
        <td class="flow flowSub"><p>리스할부 상품</p></td>
        <td>00</td>
        <td>00</td>
        <td>85.0</td>
        <td>불합격</td>
      </tr>
    </table>
  </div>
</div>

</body>
</html>
