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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <script>
   if(${isAlert}) { 
	      alert("로그인 후 이용 가능합니다.");
	}
   
   $(document).ready(function() {
		
		$("#btn_search").click(function() {
		   $("#searchWord").val($("#searchWord").val());
		   $("#isCheckGeneralGrade").val($("#isCheckGeneralGrade").val());
		   $("#isCheckManagerGrade").val($("#isCheckManagerGrade").val());	   
		   $("#isCheckAssistantGrade").val($("#isCheckAssistantGrade").val());
		   $("#isCheckMemberGrade").val($("#isCheckMemberGrade").val());
		   $('input[name=searchEmailType]').val($('input[name=searchEmailType]:checked').val());
		   $('input[name=searchSMSType]').val($('input[name=searchSMSType]:checked').val());
		   $("#commonform").submit(); 
		});
	});

 </script>

<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>

<form id="commonform" name="commonform" method="get" action="/member_info">
<div class="member info">
  <div class="contentBoxAd">
    <h1 class="title">회원관리</h1>
    <h2>회원정보</h2>
    <table>
      <colgroup>
        <col width="17%">
        <col width="33%">
        <col width="17%">
        <col width="33%">
      </colgroup>
      <tr>
        <th>검색어</th>
        <td><input type="text" id="searchWord"  name="searchWord" value="${userDto.searchWord}"></td>
        <th>회원구분</th>
        <td>
          <label class="type"><input type="checkbox" name="isCheckGeneralGrade" id="isCheckGeneralGrade"  <c:if test="${userDto.isCheckGeneralGrade eq 'Y'}">checked="checked"</c:if> value="Y"> 일반</label>
          <label class="type"><input type="checkbox" name="isCheckManagerGrade" id="isCheckManagerGrade" <c:if test="${userDto.isCheckManagerGrade eq 'Y'}">checked="checked"</c:if> value="Y"> 관리자</label>
          <label class="type"><input type="checkbox" name="isCheckAssistantGrade" id="isCheckAssistantGrade" <c:if test="${userDto.isCheckAssistantGrade eq 'Y'}">checked="checked"</c:if> value="Y"> 부관리자</label>
          <label class="type"><input type="checkbox" name="isCheckMemberGrade" id="isCheckMemberGrade" <c:if test="${userDto.isCheckMemberGrade eq 'Y'}">checked="checked"</c:if> value="Y"> 회원</label>
        </td>
      </tr>
      <tr>
        <th>이메일 수신 여부</th>
        <td>
          <label class="agree"><input type="radio" name="searchEmailType" <c:if test="${userDto.searchEmailType eq 'A'}">checked="checked"</c:if> value="A"> 전체</label>
          <label class="agree"><input type="radio" name="searchEmailType" <c:if test="${userDto.searchEmailType eq 'Y'}">checked="checked"</c:if> value="Y"> 수신허용</label>
          <label class="agree"><input type="radio" name="searchEmailType" <c:if test="${userDto.searchEmailType eq 'N'}">checked="checked"</c:if> value="N"> 수신거부</label>
        </td>
        <th>문자(SMS) 수신 여부</th>
        <td>
          <label class="agree"><input type="radio" name="searchSMSType" <c:if test="${userDto.searchSMSType eq 'A'}">checked="checked"</c:if> value="A"> 전체</label>
          <label class="agree"><input type="radio" name="searchSMSType" <c:if test="${userDto.searchSMSType eq 'Y'}">checked="checked"</c:if> value="Y"> 수신허용</label>
          <label class="agree"><input type="radio" name="searchSMSType" <c:if test="${userDto.searchSMSType eq 'N'}">checked="checked"</c:if> value="N"> 수신거부</label>
        </td>
      </tr>
    </table>
    <a onclick="return false;" id="btn_search" class="btn_apply mb100">조회</a>

    <ul class="btn_wrap">
      <li><a href="#">수정</a></li>
      <li><a href="#">엑셀다운로드</a></li>
      <li><a href="#">회원등급</a></li>
      <li><a href="#">정보삭제</a></li>
    </ul>
    <table class="memberList">
      <colgroup>
        <col width="4%">
        <col width="4%">
        <col width="6.5%">
        <col width="7%">
        <col width="11%">
        <col width="4%">
        <col width="8.5%">
        <col width="8.5%">
        <col width="8.5%">
        <col width="11%">
        <col width="11%">
        <col width="14%">
      </colgroup>
      <tr>
        <th>선택</th>
        <th>번호</th>
        <th>등급</th>
        <th>이름</th>
        <th>아이디</th>
        <th>성별</th>
        <th>가입일</th>
        <th>최근접속일</th>
        <th>생년월일</th>
        <th>연락처</th>
        <th>휴대전화</th>
        <th>이메일</th>
      </tr>
      <c:forEach var="user" items="${userList}">
	      <tr class="center">
	        <td><input type="checkbox" name="memberCheck" value=""></td>
	        <td>1</td>
	        <td>${user.grade} </td>
	        <td class="flow flowName"><p>${user.userName}</p></td>
	        <td class="flow flowId"><p>${user.userId}</p></td>
	        <td>${user.gender}</td>
	        <td>${user.joinDate}</td>
	        <td>${user.lastConnectDate}</td>
	        <td>${user.birthDate}</td>
	        <td>${user.callNumber}</td>
	        <td>${user.phoneNumber}</td>
	        <td class="flow flowEmail"><p>${user.emailAddress}</p></td>
	      </tr>
	  </c:forEach>
    </table>
  </div>
</div>
</form>

</body>
</html>
