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

$(document).ready(function() {
	$("li[value='${pageMaker.applyDto.page}']").attr("class","on");
	
	   $("#btn_search").click(function() {
	      $("#textCondition").val($("#textCondition").val());
	      $("#localCondition").val($("#localCondition").val());
	      $("#subjectCondition").val($("#subjectCondition").val());
	      $("#isPassCondition").val($("#isPassCondition").val());
	      $("#onePageDataCountCondition").val($("#onePageDataCountCondition").val());
	      $("#page").val(1);
	      $("#commonform").submit(); 
	   });
	   
	   $("#excelResultListDownload").click(function(){
	 		RuesltListsaveExcel();
		});
});

function RuesltListsaveExcel() {
	 /*
		(1)
	  	 아래 두 가지 요소만 정확하게 선택되면 됨.
	   	$('.column_thead').find('th')
	 	$('#columnList').find('tr') 
	*/
	var columnList = $.map($('.column_thead').find('th'), function(th){ // 리스트 head 찾기 
		if(!$(th).hasClass('first')) return $(th).text();
	});
	
	var dataList = new Array();
	$('#columnList').find('tr').each(function(tr){ // 리스트 body 찾기
		if(tr == 0) return true;
		var row = new Array();
		$(this).children().each(function(idx){
			row.push($(this).text());
		});
		dataList.push(row.join('▒'));
	});

	$("#fileName").val("채점리스트 목록");  // 다운로드 받을 엑셀 이름 정의
	$("#columns").val(columnList.join(','));
	$("#data").val(dataList.join('▧'));
	$("#excelForm").submit();
	/* 
	(2)
	<form action="/excelDownload" method="POST" name="excelForm" id="excelForm">
	/excelDownload > UserManageController 에 있음.
	완전히 똑같이 호출해도 되고,
	/excelDownloadForApplyList 와 같이 다른 이름으로 controller에 추가해서 사용 가능.
	controller 메서드는 그대로 사용.
	*/;
 }
</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>

<form id="commonform" name="commonform" method="get" action="/resultList">
<div class="result list">
  <div class="contentBoxAd">
    <h1 class="title">성적관리</h1>
    <h2>채점표리스트</h2>
    <div class="selectTable">
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
          <td colspan="3">
			<c:choose>
			<c:when test="${textCondition eq '' || textCondition eq null}">
			   <input type="text" id="textCondition" name="textCondition" value="">
			</c:when>
			<c:otherwise>
			   <input type="text" id="textCondition" name="textCondition" value="${textCondition}">
			</c:otherwise>
			</c:choose>
          </td>
          <th>합격여부</th>
          <td>
            <select id="isPassCondition" name="isPassCondition" class="count">
              <option value="전체" <c:if test="${isPassCondition eq '전체'}">selected="selected"</c:if>>전체</option>
              <option value="Y" <c:if test="${isPassCondition eq 'Y'}">selected="selected"</c:if>>합격</option>
             <option value="N" <c:if test="${isPassCondition eq 'N'}">selected="selected"</c:if>>불합격</option>
            </select>
          </td>
        </tr>
        <tr>
          <th>지역</th>
          <td>
            <select id="localCondition" name="localCondition">
              <c:forEach var="localList" items="${localList}" varStatus="status">
					<c:choose>
					<c:when test="${localCondition eq localList.local}">
						<option value="${localList.local}" selected>${localList.local}</option>
					</c:when>
					<c:otherwise>
						<option value="${localList.local}">${localList.local}</option>
					</c:otherwise>
					</c:choose>
	            </c:forEach>
            </select>
          </td>
          <th>과목</th>
          <td>
            <select id="subjectCondition" name="subjectCondition">
              <c:forEach var="subjectList" items="${subjectList}" varStatus="status">
					<c:choose>
					<c:when test="${subjectCondition eq subjectList.subjectName}">
						<option value="${subjectList.subjectName}" selected>${subjectList.subjectName}</option>
					</c:when>
					<c:otherwise>
						<option value="${subjectList.subjectName}">${subjectList.subjectName}</option>
					</c:otherwise>
					</c:choose>
	            </c:forEach>
            </select>
          </td>
          <th>목록건수</th>
           <td>
			<select id="onePageDataCountCondition" name="onePageDataCountCondition" class="count">
	            <c:forEach var="i" begin="50" end="300" step="50">
	            <c:choose>
	            <c:when test="${i eq pageCondition}">
	               <option value="${i}" selected>${i}</option>
	            </c:when>
	            <c:otherwise>
	               <option value="${i}">${i}</option>
	            </c:otherwise>
	            </c:choose>
	            </c:forEach>
            </select>
          </td>
        </tr>
      </table>
    </div>
    <a onclick="return false;" id="btn_search" class="btn_apply mb100">조회</a>
    </form>
    <form action="/excelDownload" method="POST" name="excelForm" id="excelForm">
  		<input type="hidden" name="fileName" id="fileName" value="">	
  		<input type="hidden" name="columns" id="columns" value="">	
  		<input type="hidden" name="data" id="data" value="">	
  	</form>
  	<ul class="btn_wrap">
      <li><a onclick="return false;" id="excelResultListDownload">엑셀다운로드</a></li>
    </ul>
    <table class="list" id="columnList">
      <colgroup>
        <col width="20%">
        <col width="14%">
        <col width="9%">
        <col width="9%">
        <col width="4%">
        <col width="12%">
        <col width="7%">
        <col width="7%">
        <col width="9%">
        <col width="9%">
      </colgroup>
      <tr class="column_thead">
        <th >고사장</th>
        <th>수험번호</th>
        <th>성명</th>
        <th>생년월일</th>
        <th>성별</th>
        <th>유형</th>
        <th>좌석번호</th>
        <th class="score">점수</th>
        <th class="accept">합격여부</th>
      </tr>
       <c:forEach var="scorecardInfo" items="${socrecardList}" varStatus="status">
      	<tr class="center">
			<td class="flow flowArea"><p>${scorecardInfo.examZoneDto.examZoneName}</p></td>
       		<td> ${scorecardInfo.studentCode}</td>
       		<td>${scorecardInfo.userDto.userName}</td>
        	<td>${scorecardInfo.userDto.birthDate}</td>
        	<td>${scorecardInfo.userDto.gender}</td>
        	<td class="flow flowSub"><p>${scorecardInfo.subjectDto.subjectName}</p></td>
        	<td>${scorecardInfo.seatNumber}</td>
        	<td>${scorecardInfo.gradeDto.allScore}</td>
        	<td>${scorecardInfo.gradeDto.isPass}</td>
      	</tr>
      </c:forEach>
    </table>
    
    <ul class="btn-group pagination">
	 <c:if test="${pageMaker.prev}">
	     <li>
	        <a href='<c:url value="/resultList?page=${pageMaker.startPage-1}&textCondition=${textCondition}&localCondition=${localCondition}&subjectCondition=${subjectCondition}&onePageDataCountCondition=${pageCondition}" />'><i class="fa fa-chevron-left">이전</i></a>
	    </li>
	</c:if>
	 <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
	   <li value="${pageNum}"> 
	         <a href='<c:url value="/resultList?page=${pageNum}&textCondition=${textCondition}&localCondition=${localCondition}&subjectCondition=${subjectCondition}&onePageDataCountCondition=${pageCondition}" />'><i class="fa">${pageNum}</i></a>
	   </li>
	</c:forEach>
	<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	   <li>
	        <a href='<c:url value="/resultList?page=${pageMaker.endPage+1}&textCondition=${textCondition}&localCondition=${localCondition}&subjectCondition=${subjectCondition}&onePageDataCountCondition=${pageCondition}"/>'><i class="fa fa-chevron-right">다음</i></a>
	     </li>
	</c:if>
	</ul>   
	<c:set var="OutputPageTotal" value="${(pageMaker.totalCount/pageCondition)+(1-((pageMaker.totalCount/pageCondition)%1))%1}" />
	<fmt:parseNumber var="OutputPage"  value="${OutputPageTotal}" integerOnly="true" type="number"/>
	<p class="pageCnt">전체 ${pageMaker.totalCount}건, ${applyDto.page} / <c:out value="${OutputPage}"/> 페이지</p>
	<div class="pageWrap">
	<!-- 페이징 -->
    
  </div>
</div>


</body>
</html>
