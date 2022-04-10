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
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<link rel="stylesheet" href="/www/inc/css/admin.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/boardmanage.js?t=<%= new java.util.Date() %>"></script>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
if(${isAlert}) { 
    alert("로그인 후 이용 가능합니다.");
}

$(document).ready(function() {
	$("li[value='${pageMaker.popupDto.page}']").attr("class","on");
});

</script>
<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>

<div class="board">
  <div class="contentBoxAd">
    <h1 class="title">팝업관리</h1>
    <h2>팝업관리</h2>
    <br>
    <p style="font-size: 18px; color: red;">※ 팝업노출여부가 'Y'이고, 팝업종료일자가 임박한 상위 3개의 팝업이 순서대로 노출됩니다.</p>
    <ul class="btn_wrap">
       <li><a href="/PopupInput?page=${popupDto.page}">등록하기</a></li>
    </ul>
    <table class="list">
      <colgroup>
        <col width="4%">
        <col width="4%">
        <col width="36%">
        <col width="10%">
        <col width="12%">
        <col width="12%">
        <col width="12%">
        <col width="10%">
      </colgroup>
      <tr>
        <th>선택</th>
        <th>번호</th>
        <th>제목</th>
        <th>팝업 노출여부</th>
        <th>시작날짜</th>
        <th>종료날짜</th>
        <th>등록날짜</th>
        <th>수정</th>
      </tr>      
       <c:forEach var="popup" items="${popupList}" varStatus="status">
        <tr class="center">
	    	<td><input type="checkbox" name="popupCheck" value="${popup.popupId}"></td> <!-- 선택 -->
       		<td>${fn:length(popupList) - status.index}</td> <!-- 번호 -->
       		<td class="flow flowTitle">${popup.title}</td> <!-- 제목 -->
       		<td>${popup.isVisible}</td> <!-- 팝업 노출여부 -->
       		<td>${popup.startDate}</td> <!-- 팝업 시작날짜 -->
       		<td>${popup.endDate}</td> <!-- 팝업 종료날짜 -->
<%--         	<c:if test="${not empty popup.fileDto}">
         	 	 <td  class="file"></td> 
         	</c:if>
         	<c:if test="${empty popup.fileDto}">
         	 	 <td></td> 
         	</c:if> --%>
        	<td>${popup.writeTime}</td> <!-- 등록날짜 -->
        	<td><a href="/PopupRevise?page=${popup.page}&popupId=${popup.popupId}&fileId=${popup.fileId}" class="btn_more">수정하기</a></td> <!-- 수정하기 -->
        </tr>
	  </c:forEach>
    </table>
    <a href="/PopupList" class="btn_apply mb100" onclick="return doPopupDelete()">선택삭제</a>

     <ul class="btn-group pagination">
  	<c:if test="${pageMaker.prev}">
   		<li>
     		 <a href='<c:url value="/PopupList?page=${pageMaker.startPage-1}" />'><i class="fa fa-chevron-left">이전</i></a>
  		</li>
 	</c:if>
  	<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
    	<li value="${pageNum}"> 
       		<a href='<c:url value="/PopupList?page=${pageNum}"/>'><i class="fa">${pageNum}</i></a>
    	</li>
    </c:forEach>
    <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
    	<li>
      		<a href='<c:url value="/PopupList?page=${pageMaker.endPage+1}"/>'><i class="fa fa-chevron-right">다음</i></a>
   		</li>
    </c:if>
	</ul>
	
	<c:set var="OutputPageTotal" value="${(pageMaker.totalCount/popupDto.perPageNum)+(1-((pageMaker.totalCount/popupDto.perPageNum)%1))%1}" />
	<fmt:parseNumber var="OutputPage"  value="${OutputPageTotal}" integerOnly="true" type="number"/>
    <p class="pageCnt">전체 ${pageMaker.totalCount}건, ${popupDto.page} / <c:out value="${OutputPage}"/> 페이지</p>
    <div class="pageWrap">
      <!-- 페이징 -->
    </div>
  </div>
</div>

</body>
</html>