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

  <link rel="stylesheet" href="/www/inc/css/notice.css">
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="notice notice_view">
  <div class="contentBox">
    <h1>공지사항</h1>
    <div class="btnWrap topBtn">
      <a href="#" class="btn">이전</a>
      <a href="#" class="btn">다음</a>
      <a href="#" class="btn">목록</a>
    </div>
    <div class="content">
      <div class="info">
        <h3><!--정원 조기마감 시 다음 회차 평가 접수--> ${boardInfo.title}</h3>
        <div>
          <p class="num">No.2117</p>
          <p class="date">2021-09-17</p>
          <p class="viewCount">조회수 : 600</p>
          <p class="document"></p> <!--문서표시 있을때 'document'클래스명 추가-->
        </div>
      </div>
      <!-- 내용 영역// -->
      <div class="text">
        <!--  각 고사장 접수 가능 인원 조기 마감 시 증원, 대기 신청 등은 불가합니다.<br/>
        원서접수 기한 내 취소자 발생 시 잔여석에 신청 가능 인원이 표시됩니다.<br/>
        개별 안내 및 유선 접수는 불가한 점 양해 부탁드립니다.<br/>
        고사장 선택란에 표시되는 잔여석이 0일 경우, 잔여석이 있는 타지역으로 접수하시거나<br/>
        다음 회차 평가 접수해주시기 바랍니다.<br/><br/>

        제5회 평가 (11월 6일) <br/>
        원서접수 가능 기간 : 10.12(화) 10:00 ~ 10.22(금) 17:00<br/><br/>

        제6회 평가 (12월 11일 / ★금년 마지막 평가, 차년 계획 미정)<br/>
        원서접수 가능 기간 : 11.1(월) 10:00 ~ 11.26(금) 17:00-->
        ${boardInfo.contents}
      </div>
      <!-- //내용 영역 -->
    </div>
    <div class="btnWrap bottomBtn">
      <a href="#" class="btn">이전</a>
      <a href="#" class="btn">다음</a>
      <a href="#" class="btn">목록</a>
    </div>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>