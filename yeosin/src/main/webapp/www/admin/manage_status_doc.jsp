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
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/applymanage.js?t=<%= new java.util.Date() %>"></script>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>

<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%-- <%@ include file="/www/common/admin_header.jsp"%> --%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%-- <%@ include file="/www/common/admin_lnb.jsp"%> --%>
<div class="manage status">
  <a href="/www/main.jsp" class="home"><img src="/www/inc/img/common/logo.png" alt="여신금융협회"></a>
  <div class="contentBoxAd">
    <h1 class="title">시험운영관리</h1>
    <h2>원서접수현황 <em>– 고사장별 현황</em></h2>
    <div class="selectTable">
    <form action="/manage_status_doc" method="get" onsubmit="return true">
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
            <input type="text" id="textCondition" name="textCondition" value="">
          </td>
        </tr>
        <tr>
          <th>지역</th>
          <td>
            <select id="localCondition" name="localCondition">
	            <c:forEach var="localList" items="${localList}" varStatus="status">
					<option value="${localList.local}">${localList.local}</option>
	            </c:forEach>
            </select>
          </td>
          <th>과목</th>
          <td>
            <select id="subjectCondition" name="subjectCondition">
            	<c:forEach var="subjectList" items="${subjectList}" varStatus="status">
					<option value="${subjectList.subjectId}">${subjectList.subjectName}</option>
				</c:forEach>
            </select>
          </td>
<!--           <th>목록건수</th>
          <td>
            <select id="" name="" class="count">
              <option value="">30</option>
            </select>
          </td> -->
        </tr>
      </table>
    </div>
    <input style="border:none;" class="btn_apply mb100" type="submit" value="조회"/>
    </form>
    <ul class="btn_wrap">
      <li><a href="#">원서별 확인</a></li>
      <li><a href="#">엑셀다운로드</a></li>
    </ul>
    <table class="list">
      <colgroup>
<%--         <col width="4%">
        <col width="4%">
        <col width="10.5%">
        <col width="12%">
        <col width="20%">
        <col width="9.5%">
        <col width="9.5%">
        <col width="9.5%">
        <col width="10.5%">
        <col width="10.5%"> --%>
      </colgroup>
      <tr>
        <th>선택</th>
        <th>번호</th>
        <th>시험회차</th>
        <th>수험번호</th>
        <th>이름</th>
        <th>아이디</th>
        <th>전화번호</th>
        <th>결제방식</th>
        <th>수험지역</th>
        <th>고사장</th>
        <th>좌석배치</th>
        <th>과목</th>
        <th>성별</th>
        <th>접수일</th>
        <th>시험일</th>
        <th>취소여부</th>
      </tr>
      <c:forEach var="applyList" items="${applyListByDocument}" varStatus="status">
	      <tr class="center">
	        <td><input type="checkbox" name="memberCheck" value=""></td>
	        <td>${status.count}</td>
	        <td class="flow flowNo"><p>${applyList.examDto.examDegree}</p></td>
	        <td>${applyList.studentCode}</td>
	        <td class="flow flowArea"><p>${applyList.userDto.userName}</p></td>
	        <td>${applyList.userId}</td>
	        <td>${applyList.userDto.phoneNumber}</td>
	        <td>${applyList.paymentMethod}</td>
	        <td>${applyList.examZoneDto.local}</td>
	        <td>${applyList.examZoneDto.examZoneName}</td>
	        <td>-</td>
	        <td>${applyList.subjectDto.subjectName}</td>
	        <td>${applyList.userDto.gender}</td>
	        <td>${applyList.receiptDate}</td>
	        <td>${applyList.examDto.examDate}</td>
	        <td>${applyList.isCancel}</td>
	      </tr>
	  </c:forEach>
    </table>
  </div>
</div>

</body>
</html>
