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
  
  <script src="/www/inc/js/jquery-3.4.1.min.js"></script>
  <link href="/www/inc/css/reset.css" rel="stylesheet" type="text/css" media="screen">
  <link href="/www/inc/css/common.css" rel="stylesheet" type="text/css" media="screen">
  <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css" rel="stylesheet"> <!--나눔스퀘어 font-->
  
  <link rel="stylesheet" href="/www/inc/css/apply.css?t=<%= new java.util.Date() %>">
  
<script>
$(function(){
  /* 유의사항 넘버링 */
  $('.content ul li').each(function(){
    var liNum = $(this).index()+1;
    $(this).prepend('<span>'+ liNum +'</span>');
  });
  $(document).ready(function(){
	  $('#btn_print').click(function() {
  		window.print();
	  })
  })
});
</script>
</head>

<body>
<div class="ticket">
  <h1>수험표<span>대출성 상품 판매대리 &middot; 중개업자 등록 자격인증 평가</span></h1>
  <table>
    <colgroup>
      <col width="22%">
      <col width="*">
    </colgroup>
    <tr>
      <th>자격종목</th>
      <td>${applyInfo.examDto.examName}</td>
    </tr>
    <tr>
      <th>자격종별</th>
      <td>${applyInfo.subjectDto.subjectName}</td>
    </tr>
    <tr>
      <th>수험번호</th>
      <td>${applyInfo.studentCode}</td>
    </tr>
    <tr>
      <th>이름</th>
      <td>${applyInfo.userDto.userName}</td>
    </tr>
    <tr>
      <th>생년월일</th>
      <td>${applyInfo.userDto.birthDate}</td>
    </tr>
    <tr>
      <th>시험일자</th>
      <td>${applyInfo.examDto.examDate}</td>
    </tr>
    <tr>
      <th>성적공고</th>
      <td>${applyInfo.examDto.gradeStartDate} ~ ${applyInfo.examDto.gradeEndDate}</td>
    </tr>
    <tr>
      <th>시험장소</th>
      <td>${applyInfo.examZoneDto.examZoneName}</td>
    </tr>
    <tr>
      <th>입실완료시간</th>
      <td>09:30까지 [10:00~11:30]</td>
    </tr>
  </table>

  <h2>수험자 유의사항</h2>
  <div class="content">
    <ul>
      <li><p>수험원서, 제출 서류 등의 허위 작성 · 위조 · 기재오기 · 누락 및 연락불능의 경우에 발생하는 불이익은 전적으로 수험자 책임입니다.</p></li>
      <li><p>수험자는 시험 시행 전까지 시험장 위치 및 교통편을 확인하여야 하며, 시험 당일 입실 시간까지 신분즐, 수험표, 필기구를 지참하고 해당 시험실의 지정된 좌석에 착석하여야 합니다.</p></li>
      <li><p>신분증은 주민등록증, 유효기간 만료 전의 여권, 운전면허증, 주민등록 발급신청서, 외국인등록증, 재외동포 국내 거소증만 인정됩니다.</p></li>
      <li><p>시험 도중 포기하거나 답안지를 제출하지 않은 수험자는 0점 처리 됩니다.</p></li>
      <li><p>지정된 시험실 좌석 이외의 좌석에서는 응시할 수 없습니다.</p></li>
      <li><p>개인용 손목시계를 준비하여 시험 시간을 관리하기 바라며, 휴대전화를 비롯하여 데이터를 저장할 수 있는 전자기기는 시계 대용으로 사용할 수 없습니다.</p></li>
      <li><p>수험자는 감독위원의 지시에 따라야 하며,  부정한 행위를 한 수험자에게는 해당 시험을 무효로 처리합니다.</p></li>
      <li><p>시험 기간 중에는 통신기기 및 전자기기를 일체 휴대할 수 없으며, 시험 도중 관련 장비를 가지고 있다가 적발될 경우 실제 관련 장비의 사용여부와 관계없이 부정행위자로 처리될 수 있습니다.</p></li>
      <li><p>수험자 인적사항 · 답안지 등 작성은 반드시 검정색 컴퓨터용 사인펜만 사용하여야 합니다.</p></li>
      <li><p>시험 당일 시험장 내에는 주차는 불가합니다. 가급적 대중교통을 이용하여 주시기 바랍니다.</p></li>
      <li><p>시험장은 전체가 금연 구역이므로 흡연을 금지하며, 쓰레기를 함부로 버리거나 시설물이 훼손되지 않도록 주의하시기 바랍니다.</p></li>
      <li><p>기타 시험 일정, 운영 등에 관한 사항은 대출성 상품 판매대리 · 중개업자 등록 자격인증 평가 홈페이지의 시행 공고를 확인하시기 바라며, 미확인으로 인한 불이이익은 수험자의 책임입니다.</p></li>
    </ul>
  </div>

  <h2>
    고사장 약도
    <p>고사장 주소 : ${applyInfo.examZoneDto.description}</p>
  </h2>
  <div class="content">
    <!-- <img src="/www/inc/img/apply/test-map.jpg" class="mapImg" alt="지도이미지 예시"> -->
    <img src="${examZoneMap}" class="mapImg" style="width: 100%; height: 100%">
  </div> 
</div>
<div><a onclick="return false;" id="btn_print" class="btn_apply">출력하기</a></div>
</body>
</html>
