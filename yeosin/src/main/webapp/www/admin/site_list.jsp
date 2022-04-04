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
		 $("li[value='${examZoneDto.page}']").attr("class","on");
		// 지역 선택했을때 구 리스트 초기화
		$("#localCondition").change(function(){
			
			var local = $('#localCondition').val();
			
			$("select[name='localDetailCondition'] option").remove();  
			
			$.ajax({
				url: "/localDetailListByLocal",
		        type: "GET",
		        async: false,
		        dataType: "json",
		        data: {
		        		local : local
					  },
		        success: function(localDetailList) 
				{
					console.log("AJAX Request 성공");
					
					var selectList = $('select#localDetailCondition');
					var createHtml;
					
					$.each(localDetailList, function(index, value)
					{		             
						createHtml += '<option value= ' + value.localDetail + '>' + value.localDetail + '</option>';
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

<div class="site list">
  <div class="contentBoxAd">
    <h1 class="title">고사장설정</h1>
    <h2>고사장 리스트</h2>
    <form action="/siteList" method="get">
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
          <th>구</th>
          <td>
			<select id="localDetailCondition" name="localDetailCondition">
				<c:forEach var="localDetailList" items="${localDetailList}" varStatus="status">
				<c:choose>
				<c:when test="${localDetailCondition eq localDetailList.localDetail}">
				  <option value="${localDetailList.localDetail}" selected>${localDetailList.localDetail}</option>
				</c:when>
				<c:otherwise>
				  <option value="${localDetailList.localDetail}">${localDetailList.localDetail}</option>
				</c:otherwise>
				</c:choose>
				</c:forEach>
          	</select>
          </td>
          <th>고사장명</th>
          <td>
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
    	<input type="hidden" name="menuId" id="menuId" value="siteList">
		<input type="hidden" name="localConditionForExcel" id="localConditionForExcel" value="${localCondition}">
		<input type="hidden" name="localDetailConditionForExcel" id="localDetailConditionForExcel" value="${localDetailCondition}">
		<input type="hidden" name="textConditionForExcel" id="textConditionForExcel" value="${textCondition}">
		<input type="hidden" name="onePageDataCountConditionForExcel" id="onePageDataCountConditionForExcel" value="${pageCondition}">
		<input type="hidden" name="pageForExcel" id="pageForExcel" value="${examZoneDto.page}">
		
  		<input type="hidden" name="fileName" id="fileName" value="">	
  		<input type="hidden" name="columns" id="columns" value="">	
  		<input type="hidden" name="data" id="data" value="">
  		<input type="hidden" name="docName" id="docName" value="고사장 리스트">	
  	</form>
    <ul class="btn_wrap">
      <li><a onclick="return false;" id="excelDownload">엑셀다운로드</a></li>
      <li><a href="/siteRegister">등록하기</a></li>
      <li><a href="/siteList" onclick="return doExamZoneDelete()">정보삭제</a></li>
    </ul>
    <table class="mb100" id="columnList">
      <colgroup>
        <col width="4%">
        <col width="4%">
        <col width="12%">
        <col width="12%">
        <col width="18.5%">
        <col width="10%">
        <col width="10%">
        <col width="10%">
        <col width="7.5%">
        <col width="12%">
      </colgroup>
     <tr class="column_thead">
	    <th class="first">선택</th>
        <th>번호</th>
        <th>지역</th>
        <th>구</th>
        <th>고사장명</th>
        <th>시험교실 수</th>
        <th>교실당 인원수</th>
        <th>전체 인원수</th>
        <th>약도여부</th>
        <th>비고</th>
      </tr>
      <c:forEach var="examZoneList" items="${examZoneList}" varStatus="status">   
	      <tr class="center">
	        <td class="flow flowNo"><input type="checkbox" name="examZoneCheck" value="${examZoneList.examZoneId}"></td>
	        <td class="flow flowNo">${status.count}</td>
	        <td class="flow flowNo">${examZoneList.local}</td>
	        <td class="flow flowNo">${examZoneList.localDetail}</td>
	        <td class="flow flowNo">${examZoneList.examZoneName}</td>
	        <td class="flow flowNo">${examZoneList.examRoomCnt}</td>
	        <td class="flow flowNo">${examZoneList.examRoomUserCnt}</td>
	        <td class="flow flowNo">${examZoneList.examTotalUserCnt}</td>
	        <td class="flow flowNo">${examZoneList.examZoneMap}</td>
	        <td><a href="/siteRegister?examZoneId=${examZoneList.examZoneId}" class="btn_more">자세히 보기</a></td>
	      </tr>
	  </c:forEach> 
    </table>
	<ul class="btn-group pagination">
	 <c:if test="${pageMaker.prev}">
	     <li>
	        <a href='<c:url value="/siteList?page=${pageMaker.startPage-1}&textCondition=${textCondition}&localCondition=${localCondition}&onePageDataCountCondition=${pageCondition}" />'><i class="fa fa-chevron-left">이전</i></a>
	    </li>
	</c:if>
	 <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
	   <li value="${pageNum}"> 
	         <a href='<c:url value="/siteList?page=${pageNum}&textCondition=${textCondition}&localCondition=${localCondition}&onePageDataCountCondition=${pageCondition}" />'><i class="fa">${pageNum}</i></a>
	   </li>
	</c:forEach>
	<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	   <li>
	        <a href='<c:url value="/siteList?page=${pageMaker.endPage+1}&textCondition=${textCondition}&localCondition=${localCondition}&onePageDataCountCondition=${pageCondition}"/>'><i class="fa fa-chevron-right">다음</i></a>
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
