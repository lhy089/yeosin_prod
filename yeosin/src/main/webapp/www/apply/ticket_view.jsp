<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="ko">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  <meta charset="utf-8">
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
  <script>
  $(document).ready(function(){
	  $('#btn_print').click(function() {
  		window.open('/ticket_print?receiptId=${applyInfo.receiptId}', 'ticketPrint', 'width=1000, height=1340, left=400, top=400, resizable = yes');
	  })
  })
  </script>
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="apply accept_view">
  <div class="contentBox">
    <h1>수험표 출력</h1>
    <div class="announcement">
      이메일 및 연락처 정보 변경은 회원정보수정에서 수정 가능합니다.
    </div>
    <c:choose>
    <c:when test="${applyInfo ne null}">
    <table>
      <colgroup>
        <col width="15.5%">
        <col width="*">
      </colgroup>
      <tr>
        <th>시험명</th>
        <td>${applyInfo.examDto.examName}</td>
      </tr>
      <tr>
      	<th>자격종별</th>
      	<td>${applyInfo.subjectDto.subjectName}</td>
      </tr>
      <tr>
        <th>성명</th>
        <td>${applyInfo.userDto.userName}</td>
      </tr>
      <tr>
        <th>연락처</th>
        <td>${applyInfo.userDto.phoneNumber}</td>
      </tr>
      <tr>
        <th>이메일</th>
        <td>${applyInfo.userDto.emailAddress}</td>
      </tr>
      <tr>
        <th>수료번호</th>
        <td>${applyInfo.certId}</td>
      </tr>
      <tr>
        <th>수험번호</th>
        <td>${applyInfo.studentCode}</td>
      </tr>
      <tr>
        <th>시험장명</th>
        <td>${applyInfo.examZoneDto.examZoneName}</td>
      </tr>
      <tr>
        <th>시험일</th>
        <td>${applyInfo.examDto.examDate}</td>
      </tr>
      <tr>
        <th>결제금액</th>
        <td>${applyInfo.examFee}원</td>
      </tr>
    </table>
    </c:when>
    <c:otherwise>
    표시할 데이터가 없습니다.
    </c:otherwise>
    </c:choose>
    <a onclick="return false;" id="btn_print" class="btn_apply">수험표 출력</a>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>

