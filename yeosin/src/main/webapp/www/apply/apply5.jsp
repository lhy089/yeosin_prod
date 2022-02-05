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
<div class="apply fifth">
  <div class="contentBox">
    <h1>원서접수</h1>
    <div class="announcement">
      인터넷접수 첫날의 접수 시작 시간은 10:00 부터입니다.
    </div>
    <h2>3단계 접수확인 및 결제선택</h2>
    <section>
      <table>
        <colgroup>
          <col width="15.5%">
          <col width="*">
        </colgroup>
        <tr>
          <th>시험명</th>
          <td>${examInfo.examName}</td>
        </tr>
        <tr>
          <th>성명</th>
          <td>${userInfo.userName}</td>
        </tr>
        <tr>
          <th>연락처</th>
          <td>${userInfo.phoneNumber}</td>
        </tr>
        <tr>
          <th>이메일</th>
          <td>${userInfo.emailAddress}</td>
        </tr>
        <tr>
          <th>수료번호</th>
          <td><%=request.getParameter("eduNum")%></td>
        </tr>
        <tr>
          <th>시험장명</th>
          <td>${examZoneName}</td>
        </tr>
        <tr>
          <th>시험일</th>
          <td>${examInfo.examDate}</td>
        </tr>
        <tr>
          <th>결제금액</th>
          <td>${examInfo.examCost}원</td>
        </tr>
        <tr>
          <th>결제방법선택</th>
          <td>
            <label class="pay"><input type="radio" name="pay" value=""><span>가상계좌</span></label>
            <label class="pay"><input type="radio" name="pay" value=""><span>실시간 계좌이체</span></label>
            <label class="pay"><input type="radio" name="pay" value=""><span>신용카드</span></label>
          </td>
        </tr>
      </table>
      <a href="#" class="btn_apply">결제하기</a>
    </section>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>

