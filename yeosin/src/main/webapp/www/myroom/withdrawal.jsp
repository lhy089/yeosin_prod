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
  
  <link rel="stylesheet" href="../inc/css/myroom.css">
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="myroom withdrawal">
  <div class="contentBox">
    <h1>회원탈퇴</h1>
    <h2><srrong class="red">회원탈퇴를 신청하기 전에 안내 사항을 꼭 확인해 주세요.</strong></h2>
    <p class="notice">사용하고 계신 아이디는 탈퇴할 경우 재사용 및 복구가 불가능합니다.</p>
    <p class="userID">회원탈퇴 ID : <span>아이디</span></p>
    <p class="notice">회원탈퇴 후 삭제되는 정보(삭제된 데이터는 복구 되지 않습니다.)</p>
    <table>
      <colgroup>
        <col width="35%">
        <col width="*">
      </colgroup>
      <tr>
        <th>회원정보</th>
        <td>메일, 연락처등 개인정보 삭제</td>
      </tr>
      <tr>
        <th>KPC자격 강사정보</th>
        <td>강사정보 및 KPC자격 마일리지 삭제</td>
      </tr>
    </table>

    <p class="notice">회원탈퇴 후에도 남아 있는 정보</p>
    <table>
      <colgroup>
        <col width="35%">
        <col width="*">
      </colgroup>
      <tr>
        <th>성적 정보 및 KPC자격 자격정보</th>
        <td>취득한 KPC자격정보(성명, 자격명, 자격등록정보등)</td>
      </tr>
      <tr>
        <th>결제정보</th>
        <td>원서접수, 자격증신청등의 결제 관련 정보(일정기간 후 폐기 처리)</td>
      </tr>
    </table>

    <div class="announcement">
      <p>탈퇴 후에는 아이디 로 다시 가입할 수 없으며 아이디와 데이터는 복구할 수 없습니다.</p>
      <label><input type="checkbox" id="" name="agreeChk"/> 안내 사항을 모두 확인하였으며, 이에 동의합니다.</label>
    </div>

    <a href="#" class="btn_apply">탈퇴하기</a>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>
