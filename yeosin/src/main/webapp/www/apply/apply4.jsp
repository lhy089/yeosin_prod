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

  <link rel="stylesheet" href="../inc/css/apply.css">
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="apply fourth">
  <div class="contentBox">
    <h1>원서접수</h1>
    <div class="schedule">
      2021 대출성 상품 판매대리 &middot; 중개업자 등록 자격인증 평가<br/>
      <span><strong>시험일</strong>2021-06-07</span><span><strong>인터넷접수</strong>2021-06-08 오전 10:00~2021-07-23</span>
    </div>
    <div class="announcement">
      &middot; 응시하실 지역을 선택 후 원하는 고사장을 선택하세요.<br/>
      &middot; 고사장이 확보되지 않은 지역은 표기되지 않습니다.
    </div>
    <h2>접수정보</h2>
    <div class="areaInfo">
      <h4>고사장</h4>
      <p class="location">
        <select id="" name="">
            <c:forEach var="examZoneList" items="${examZoneList}" varStatus="i">
         		<option value="${examZoneList.examZoneId}">${examZoneList.examZoneName}</option>
      		</c:forEach>
        </select>
      </p>
      <p class="serch"><a href="#" class="btn_serch">검색</a></p>
    </div>
    <table class="areaTable">
      <colgroup>
        <col width="14.5%">
        <col width="*">
        <col width="25%">
        <col width="15%">
        <col width="15%">
      </colgroup>
      <tr>
        <th>선택</th>
        <th>고사장명</th>
        <th>소속지역센터</th>
        <th>잔여좌석</th>
        <th>가능</th>
      </tr>
      <tr>
        <td><input type="checkbox" name="check" value=""></td>
        <td>(대전) 대성고등학교</td>
        <td>대전지역센터</td>
        <td>495</td>
        <td><a href="#" class="btn_map">약도</a></td>
      </tr>
      <tr>
        <td><input type="checkbox" name="check" value=""></td>
        <td>(주)커커</td>
        <td>서울강남지역센터</td>
        <td>100</td>
        <td><a href="#" class="btn_map">약도</a></td>
      </tr>
      <tr>
        <td><input type="checkbox" name="check" value=""></td>
        <td>(서울) 월촌초등학교</td>
        <td>서울남부지역센터</td>
        <td>100</td>
        <td><a href="#" class="btn_map">약도</a></td>
      </tr>
    </table>

    <div class="objInfo">
      <h4>시험영역</h4>
    </div>
    <table class="objTable">
      <colgroup>
        <col width="14.5%">
        <col width="*">
      </colgroup>
      <tr>
        <th>선택</th>
        <th>과목</th>
      </tr>
      <tr>
        <td><input type="checkbox" name="check" value=""></td>
        <td>대출 · 기타 대출성 상품</td>
      </tr>
      <tr>
        <td><input type="checkbox" name="check" value=""></td>
        <td>리스 · 할부 상품</td>
      </tr>
    </table>

    <a href="#" class="btn_apply">접수하기</a>

  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>
