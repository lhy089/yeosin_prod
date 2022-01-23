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
<div class="apply third">
  <div class="contentBox">
    <h1>원서접수</h1>
    <div class="schedule">
      2021 대출성 상품 판매대리 &middot; 중개업자 등록 자격인증 평가<br/>
      <span><strong>시험일</strong>${examInfo.examDate}</span><span><strong>인터넷접수</strong>${examInfo.receiptStartDate} 오전 10:00 ~ ${examInfo.receiptEndDate}</span>
    </div>
    <section>
      <h2>개인정보</h2>
      <table>
        <colgroup>
          <col width="20.5%">
          <col width="*">
        </colgroup>
        <tr>
          <th>성명</th>
          <td>${userInfo.userName}</td>
        </tr>
        <tr>
          <th>생년월일</th>
          <td>${userInfo.birthDate}</td>
        </tr>
        <tr>
          <th>휴대전화</th>
          <td>${userInfo.phoneNumber}</td>
        </tr>
        <tr>
          <th>이메일</th>
          <td>${userInfo.emailAddress}</td>
        </tr>
      </table>
      <a href="/change" class="btn_change">개인정보 변경</a>

      <h2>교육수료정보</h2>
      <table>
        <colgroup>
          <col width="20.5%">
          <col width="*">
        </colgroup>
        <tr>
          <th>교육과정</th>
          <td>
            <select id="" name="">
              <option value="">선택</option>
              <option value="">대출•기타 대출성 상품</option>
              <option value="">리스 •할부 상품</option>
            </select>
          </td>
        </tr>
        <tr>
          <th>교육수료증번호</th>
          <td>
            <input type="text" name="" value="">
            <span>※ 필수입력항목</span>
          </td>
        </tr>
      </table>
      <a href="#" class="btn_apply">작성완료</a>
    </section>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>
