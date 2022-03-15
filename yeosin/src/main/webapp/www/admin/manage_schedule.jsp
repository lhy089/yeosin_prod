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
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<script>
	 $(document).ready(function(){ 
	
	    // 년도 선택했을때 시험명 리스트 초기화
	    $("#yearCondition").change(function(){
	       
	       var year = $('#yearCondition').val();
	       
	       $("select[name='examNameCondition'] option").remove();  
	       
	       $.ajax({
	          url: "/examNameConditionByYear",
	            type: "GET",
	            async: false,
	            dataType: "json",
	            data: {
	                  year : year
	               },
	            success: function(examNameList) 
	          {
	             console.log("AJAX Request 성공");
	             
	             var selectList = $('select#examNameCondition');
	             var createHtml;
	             
	             $.each(examNameList, function(index, value)
	             {                   
	                createHtml += '<option value= ' + value.examName + '>' + value.examName + '</option>';
	                selectList.append(createHtml);
	                createHtml = '';
	             });
	            },
	            error: function() 
	          {
	               console.log("AJAX Request 실패");
	            }
	       });
	    });
	    
	    // 시험명 선택했을때 시험차수 리스트 초기화
	    $("#examNameCondition").change(function(){
	       
	       var examName = $("#examNameCondition option:selected").text();
	       
	       $("select[name='degreeCondition'] option").remove();  
	       
	       $.ajax({
	          url: "/degreeConditionByExamName",
	            type: "GET",
	            async: false,
	            dataType: "json",
	            data: {
	               examName : examName
	               },
	            success: function(examDegreeList) 
	          {
	             console.log("AJAX Request 성공");
	             
	             var selectList = $('select#degreeCondition');
	             var createHtml;
	             
	             $.each(examDegreeList, function(index, value)
	             {                   
	                createHtml += '<option value= ' + value.examDegree + '>' + value.examDegree + '</option>';
	                selectList.append(createHtml);
	                createHtml = '';
	             });
	            },
	            error: function() 
	          {
	               console.log("AJAX Request 실패");
	            }
	       });
	    });
	 });
	 </script>
</head>
<body>
<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>

<div class="manage schedule">
  <div class="contentBoxAd">
    <h1 class="title">시험운영관리</h1>
    <h2>시험일정관리</h2>
    <form action="/manageSchedule" method="get">
    <div class="selectTable">
      <table>
        <colgroup>
          <col width="10.5%">
          <col width="20.5%">
          <col width="10.5%">
          <col width="25.5%">
          <col width="11.5%">
          <col width="*">
        </colgroup>
        <tr>
          <th>시험년도</th>
          <td>
		   <select id="yearCondition" name="yearCondition">
					<c:forEach var="yearList" items="${yearList}" varStatus="status">
						<c:choose>
							<c:when test="${yearCondition eq yearList.examYear}">
								<option value="${yearList.examYear}" selected>${yearList.examYear}</option>
							</c:when>
							<c:otherwise>
								<option value="${yearList.examYear}">${yearList.examYear}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select>
		  </td>
          <th>시험명</th>
          <td>
			<select id="examNameCondition" name="examNameCondition">
					<c:forEach var="examNameList" items="${examNameList}"
						varStatus="status">
						<c:choose>
							<c:when test="${examNameCondition eq examNameList.examName}">
								<option value="${examNameList.examName}" selected>${examNameList.examName}</option>
							</c:when>
							<c:otherwise>
								<option value="${examNameList.examName}">${examNameList.examName}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select>
		  </td>
          <th>시험차수</th>
		  <td>
			<select id="degreeCondition" name="degreeCondition">
					<c:forEach var="degreeList" items="${degreeList}"
						varStatus="status">
						<c:choose>
							<c:when test="${degreeCondition eq degreeList.examDegree}">
								<option value="${degreeList.examDegree}" selected>${degreeList.examDegree}</option>
							</c:when>
							<c:otherwise>
								<option value="${degreeList.examDegree}">${degreeList.examDegree}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select>
		</td>
        </tr>
      </table>
    </div>
	<input style="border:none;" class="btn_apply mb100" type="submit" value="조회"/>
   	</form>
   	<!-- 엑셀 다운로드 Form 태그 -->
	<form action="/excelDownload" method="POST" name="excelForm" id="excelForm">
        <input type="hidden" name="fileName" id="fileName" value="">   
        <input type="hidden" name="columns" id="columns" value="">   
        <input type="hidden" name="data" id="data" value="">
        <input type="hidden" name="docName" id="docName" value="시험일정관리리스트">   
     </form>

    <ul class="btn_wrap">
		<li><a onclick="return false;" id="excelDownload">엑셀다운로드</a></li>
      	<li><a href="/manageRegister">등록하기</a></li>
      	<li><a href="/manageSchedule" onclick="return doScheduleDelete()">정보삭제</a></li>
    </ul>
    <table class="mb100" id="columnList">
      <colgroup>
        <col width="4%">
        <col width="4%">
        <col width="14%">
        <col width="*">
        <col width="14%">
        <col width="14%">
        <col width="14%">
      </colgroup>
	  <tr class="column_thead">
        <th class="first">선택</th>
        <th>번호</th>
        <th>시험년도</th>
        <th>시험명</th>
        <th>시험차수</th>
        <th>시험일</th>
        <th>비고</th>
      </tr>
      <tr class="center">
		<c:forEach var="examList" items="${examList}" varStatus="status">    
         <tr class="center">
           <td class="flow flowNo"><input type="checkbox" name="examCheck" value="${examList.examId}"></td>
           <td class="flow flowNo">${status.count}</td>
           <td class="flow flowNo">${examList.examYear}</td>
           <td class="flow flowNo">${examList.examName}</td>
           <td class="flow flowNo">${examList.examDegree}</td>
           <td class="flow flowNo">${examList.examDate}</td>
           <c:set var="examYear" value="${examList.examYear}"/>
           <c:set var="examDegree" value="${examList.examDegree}"/>
           <c:set var="hyphen" value="-"/>
           <td><a href="/manageRegister?examId=${examList.examId}" class="btn_more">자세히 보기</a></td>
         </tr>
       </c:forEach>
      </tr>
    </table>
    <br>
	<ul class="btn-group pagination">
	 <c:if test="${pageMaker.prev}">
	     <li>
	        <a href='<c:url value="/manageSchedule?page=${pageMaker.startPage-1}&yearCondition=${yearCondition}&examNameCondition=${examNameCondition}&degreeCondition=${degreeCondition}" />'><i class="fa fa-chevron-left">이전</i></a>
	    </li>
	</c:if>
	 <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
	   <li value="${pageNum}"> 
	         <a href='<c:url value="/manageSchedule?page=${pageNum}&yearCondition=${yearCondition}&examNameCondition=${examNameCondition}&degreeCondition=${degreeCondition}" />'><i class="fa">${pageNum}</i></a>
	   </li>
	</c:forEach>
	<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	   <li>
	        <a href='<c:url value="/manageSchedule?page=${pageMaker.endPage+1}&yearCondition=${yearCondition}&examNameCondition=${examNameCondition}&degreeCondition=${degreeCondition}"/>'><i class="fa fa-chevron-right">다음</i></a>
	     </li>
	</c:if>
	</ul>   
	<c:set var="OutputPageTotal" value="${(pageMaker.totalCount/examDto.perPageNum)+(1-((pageMaker.totalCount/examDto.perPageNum)%1))%1}" />
	<fmt:parseNumber var="OutputPage"  value="${OutputPageTotal}" integerOnly="true" type="number"/>
	<p class="pageCnt">전체 ${pageMaker.totalCount}건, ${examDto.page} / <c:out value="${OutputPage}"/> 페이지</p>
	<div class="pageWrap">
	<!-- 페이징 -->
	</div>
  </div>
</div>

</body>
</html>
