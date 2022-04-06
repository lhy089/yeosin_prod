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
</head>

<body>
<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>
<div class="manage status">
  <div class="contentBoxAd">
    <h1 class="title">시험운영관리</h1>
    <h2>원서접수현황 <em>– 고사장별 현황</em></h2>
    <form action="/manage_status_site" method="get">
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
          <td colspan="5">
			<c:choose>
			<c:when test="${textCondition eq '' || textCondition eq null}">
			   <input type="text" id="textCondition" name="textCondition" value="">
			</c:when>
			<c:otherwise>
			   <input type="text" id="textCondition" name="textCondition" value="${textCondition}">
			</c:otherwise>
			</c:choose>
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
          <th>차수</th>
          <td>
            <select id="examDegreeCondition" name="examDegreeCondition">
	            <c:forEach var="examDegreeList" items="${examDegreeList}" varStatus="status">
					<c:choose>
					<c:when test="${examDegreeCondition eq examDegreeList.examDegree}">
						<option value="${examDegreeList.examDegree}" selected>${examDegreeList.examDegree}</option>
					</c:when>
					<c:otherwise>
						<option value="${examDegreeList.examDegree}">${examDegreeList.examDegree}</option>
					</c:otherwise>
					</c:choose>
	            </c:forEach>
            </select>  
<%-- 			<select id="examYearCondition" name="examYearCondition">
				<c:forEach var="examYearList" items="${examYearList}" varStatus="status">
				   <c:choose>
				<c:when test="${examYearList eq examYearList.examYear}">
				   <option value="${examYearList.examYear}" selected>${examYearList.examYear}</option>
				</c:when>
				<c:otherwise>
				   <option value="${examYearList.examYear}">${examYearList.examYear}</option>
				</c:otherwise>
				</c:choose>
				</c:forEach>
            </select> --%>
          </td>
          <th>목록건수</th>
          <td>
			<select id="onePageDataCountCondition" name="onePageDataCountCondition" class="count">
	            <c:forEach var="i" begin="30" end="300" step="10">
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
    <input style="border:none;" class="btn_apply mb100" type="submit" value="조회"/>
    </form> 
    <!-- 엑셀 다운로드 Form 태그 -->
    <form action="/excelDownload" method="POST" name="excelForm" id="excelForm">
    	 <input type="hidden" name="menuId" id="menuId" value="statusSite">
		<input type="hidden" name="textConditionForExcel" id="textConditionForExcel" value="${textCondition}">
		<input type="hidden" name="localConditionForExcel" id="localConditionForExcel" value="${localCondition}">
		<input type="hidden" name="examDegreeConditionForExcel" id="examDegreeConditionForExcel" value="${examDegreeCondition}">
		<input type="hidden" name="onePageDataCountConditionForExcel" id="onePageDataCountConditionForExcel" value="${pageCondition}">
		<input type="hidden" name="pageForExcel" id="pageForExcel" value="${examZoneDto.page}">
    
  		<input type="hidden" name="fileName" id="fileName" value="">	
  		<input type="hidden" name="columns" id="columns" value="">	
  		<input type="hidden" name="data" id="data" value="">
  		<input type="hidden" name="docName" id="docName" value="원서접수현황(고사장별) 리스트">	
  	</form>
	<ul class="btn_wrap">
		<li><a href="/manage_status_site" onclick="return doSeatConfirm()">좌석배치 확정</a></li>
    	<li><a href="/manage_status_doc">원서별 확인</a></li>
    	<li><a onclick="return false;" id="excelDownload">엑셀다운로드</a></li>
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
        <th>시험명</th>
        <th>수험지역</th>
        <th>고사장</th>
        <th>총좌석수</th>
        <th>접수현황</th>
        <th>남은수량</th>
        <th>접수일시</th>
        <th>시험일</th>
      </tr>
      <c:forEach var="applyList" items="${applyListByExamZone}" varStatus="status">   
	      <tr class="center">
	        <td class="flow flowNo"><input type="checkbox" name="examZoneCheck" value="${applyList.examDto.examId}.${applyList.examZoneId}"></td>
	        <td class="flow flowNo">${status.count}</td>
	        <td class="flow flowNo">${applyList.examDto.examDegree}</td>
	        <td class="flow flowArea">${applyList.examDto.examName}</td>
	        <td class="flow flowNo">${applyList.local}</td>
	        <td class="flow flowNo">${applyList.examZoneName}</td>
	        <td class="flow flowNo">${applyList.examTotalUserCnt}</td>
	        <td class="flow flowNo">${applyList.receiptSeat}</td>
	        <td class="flow flowNo">${applyList.leftOverSeat}</td>
	        <td class="flow flowNo">${applyList.applyDto.receiptDate}</td>
	        <td class="flow flowNo">${applyList.examDto.examDate}</td>
	      </tr>
	  </c:forEach>  
    </table>
	<br>
	<!-- 엑셀 다운로드용 영역 -->
	<div style="display:none">
	    <table class="list" id="columnList">
	      <tr class="column_thead">
	        <th class="first">선택</th>
	        <th>번호</th>
	        <th>시험회차</th>
	        <th>시험명</th>
	        <th>수험지역</th>
	        <th>고사장</th>
	        <th>총좌석수</th>
	        <th>접수현황</th>
	        <th>남은수량</th>
	        <th>접수일시</th>
	        <th>시험일</th>
	      </tr>
	      <c:forEach var="applyListByExamZoneAndExcel" items="${applyListByExamZoneAndExcel}" varStatus="status">   
		      <tr class="center">
		        <td class="flow flowNo"><input type="checkbox" name="examZoneCheck" value="${applyListByExamZoneAndExcel.examDto.examId}.${applyListByExamZoneAndExcel.examZoneId}"></td>
		        <td class="flow flowNo">${status.count}</td>
		        <td class="flow flowNo">${applyListByExamZoneAndExcel.examDto.examDegree}</td>
		        <td class="flow flowArea">${applyListByExamZoneAndExcel.examDto.examName}</td>
		        <td class="flow flowNo">${applyListByExamZoneAndExcel.local}</td>
		        <td class="flow flowNo">${applyListByExamZoneAndExcel.examZoneName}</td>
		        <td class="flow flowNo">${applyListByExamZoneAndExcel.examTotalUserCnt}</td>
		        <td class="flow flowNo">${applyListByExamZoneAndExcel.receiptSeat}</td>
		        <td class="flow flowNo">${applyListByExamZoneAndExcel.leftOverSeat}</td>
		        <td class="flow flowNo">${applyListByExamZoneAndExcel.applyDto.receiptDate}</td>
		        <td class="flow flowNo">${applyListByExamZoneAndExcel.examDto.examDate}</td>
		      </tr>
		  </c:forEach>  
	    </table>	
	</div>
	<ul class="btn-group pagination">
	 <c:if test="${pageMaker.prev}">
	     <li>
	        <a href='<c:url value="/manage_status_site?page=${pageMaker.startPage-1}&textCondition=${textCondition}&localCondition=${localCondition}&examYearCondition=${subjectCondition}&examDegreeCondition=${examDegreeCondition}&onePageDataCountCondition=${pageCondition}" />'><i class="fa fa-chevron-left">이전</i></a>
	    </li>
	</c:if>
	 <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
	   <li value="${pageNum}"> 
	         <a href='<c:url value="/manage_status_site?page=${pageNum}&textCondition=${textCondition}&localCondition=${localCondition}&examYearCondition=${subjectCondition}&examDegreeCondition=${examDegreeCondition}&onePageDataCountCondition=${pageCondition}" />'><i class="fa">${pageNum}</i></a>
	   </li>
	</c:forEach>
	<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	   <li>
	        <a href='<c:url value="/manage_status_site?page=${pageMaker.endPage+1}&textCondition=${textCondition}&localCondition=${localCondition}&examYearCondition=${subjectCondition}&examDegreeCondition=${examDegreeCondition}&onePageDataCountCondition=${pageCondition}"/>'><i class="fa fa-chevron-right">다음</i></a>
	     </li>
	</c:if>
	</ul>   
	<c:set var="OutputPageTotal" value="${(pageMaker.totalCount/examZoneDto.perPageNum)+(1-((pageMaker.totalCount/examZoneDto.perPageNum)%1))%1}" />
	<fmt:parseNumber var="OutputPage"  value="${OutputPageTotal}" integerOnly="true" type="number"/>
	<p class="pageCnt">전체 ${pageMaker.totalCount}건, ${examZoneDto.page} / <c:out value="${OutputPage}"/> 페이지</p>
	<div class="pageWrap">
	<!-- 페이징 -->
	</div>
  </div>
</div>

</body>
</html>
