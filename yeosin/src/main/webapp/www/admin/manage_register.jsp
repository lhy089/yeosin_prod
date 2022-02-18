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

<div class="manage register">
  <div class="contentBoxAd">
    <h1 class="title">시험운영관리</h1>
    <h2>시험일정 등록</h2>
    <ul class="btn_wrap">
      <li><a href="#">엑셀다운로드</a></li>
    </ul>
    <table>
      <colgroup>
        <col width="25%">
        <col width="75%">
      </colgroup>
      <tr>
        <th>시험코드</th>
        <td>LPBQ [기본표기]</td>
      </tr>
      <tr>
        <th>시험명</th>
        <td>상품 판매대리 중개업자 자격인증 평가 [기본표기]</td>
      </tr>
      <tr>
        <th>시험회차</th>
        <td>제 20ㅇㅇ - X회차 [연도 2자리 – 회차 1자리 입력]</td>
      </tr>
      <tr>
        <th>시험일</th>
        <td>
          <input type="date" name="" value="">
        </td>
      </tr>
      <tr>
        <th>시험영역</th>
        <td>
          <label class="type"><input type="checkbox" name="check" value=""> 대출 기타 대출성 상품</label>
          <label class="type"><input type="checkbox" name="check" value=""> 리스 할부상품</label>
        </td>
      </tr>
      <tr>
        <th>접수시작일</th>
        <td>
          <input type="date" name="" value="">
          <input type="time" name="" value="10:00">
          -
          <input type="date" name="" value="">
          <input type="time" name="" value="10:00">
        </td>
      </tr>
      <tr>
        <th>접수마감일</th>
        <td>
          <input type="date" name="" value="">
          <input type="time" name="" value="10:00">
          -
          <input type="date" name="" value="">
          <input type="time" name="" value="10:00">
        </td>
      </tr>
      <tr>
        <th>100%환불 종료일</th>
        <td>
          <input type="date" name="" value="">
        </td>
      </tr>
      <tr>
        <th>수험표출력기간</th>
        <td>
          <input type="date" name="" value="">
        </td>
      </tr>
      <tr>
        <th>성적공고기간</th>
        <td>
          <input type="date" name="" value="">
        </td>
      </tr>
    </table>

    <h2>시험일정 고시장 등록</h2>
    <table>
      <colgroup>
        <col width="25%">
        <col width="75%">
      </colgroup>
      <tr>
        <th>고사장 선택</th>
        <td>
          <select id="" name="">
            <option value="">- 선택하세요 -</option>
          </select>
        </td>
      </tr>
      <tr>
        <th>시험 교실 수</th>
        <td>
          <input type="number" min="0" name="" value="" placeholder="등록 수보다 같거나 낮은 수">
        </td>
      </tr>
      <tr>
        <th>교실당 인원 수</th>
        <td>
          <input type="number" min="0" name="" value="" placeholder="등록 수보다 같거나 낮은 수">
        </td>
      </tr>
      <tr>
        <th>전체 인원 수</th>
        <td>
          <input type="number" min="0" name="" value="" placeholder="등록 수보다 같거나 낮은 수">
        </td>
      </tr>
    </table>
    <a href="#" class="btn_plus">추가</a>
    <a href="#" class="btn_apply mb100">등록하기</a>
  </div>
</div>

</body>
</html>