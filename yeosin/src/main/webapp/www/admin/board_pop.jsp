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
    <ul class="btn_wrap">
       <li><a href="/PopupInput?page=${popupDto.page}">등록하기</a></li>
    </ul>
    <table class="list">
      <colgroup>
        <col width="4%">
        <col width="4%">
        <col width="58%">
        <col width="8%">
        <col width="14%">
        <col width="12%">
      </colgroup>
      <tr>
        <th>선택</th>
        <th>번호</th>
        <th>제목</th>
        <th>팝업첨부</th>
        <th>등록날짜</th>
        <th>수정</th>
      </tr>      
       <c:forEach var="popup" items="${popupList}" varStatus="status">
        <tr class="center">
	    	<td><input type="checkbox" name="memberCheck" value="${popup.popupId}"></td>
       		<td>${fn:length(popupList) - status.index}</td>
       		<td class="flow flowTitle">${popup.title}</td>
        	<c:if test="${not empty popup.fileDto}">
         	 	 <td  class="file"></td> 
         	</c:if>
         	<c:if test="${empty popup.fileDto}">
         	 	 <td></td> 
         	</c:if>
        	<td>${popup.writeTime}</td>
        	<td><a href="/PopupRevise?page=${popup.page}&popupId=${popup.popupId}&fileId=${popup.fileId}" class="btn_more">수정하기</a></td>
        </tr>
	  </c:forEach>
    </table>
    <a href="#" class="btn_apply mb100">선택 삭제</a>

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