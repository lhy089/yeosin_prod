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
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <link rel="stylesheet" href="/www/inc/css/admin.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<script>
if(${isAlert}) { 
    alert("로그인 후 이용 가능합니다.");
}

$(document).ready(function() {
	$("li[value='${pageMaker.boardDto.page}']").attr("class","on");
	
	$("#btn_delete").click(function() {
		var checkBox = $("input[name=boardCheck]:checked");	
		
		if(checkBox.length == 0)
		{
			alert('체크된 게시판이 없습니다.');
			return;
		}
		
		if(confirm("해당 게시판을 삭제하시겠습니까??")){
			
			var checkValueArr = [];
			
			checkBox.each(function(){
				var checkValue = $(this).val();
				checkValueArr.push(checkValue);	
			});
			
			$('#boardCheck').val(checkValueArr);
			$("#page").val($("#page").val());
			$("#boardType").val($("#boardType").val());
			$("#commonform").submit(); 
		}
	});
	
});
</script>
<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>
<form id="commonform" name="commonform" method="get" action="/boardQuestionDelete_action">
<input type="hidden" name="boardType" id="boardType" value="2">
<input type="hidden" name="page" id="page" value="${boardDto.page}" >

<div class="board">
  <div class="contentBoxAd">
    <h1 class="title">게시판</h1>
    <h2>자주하는 질문</h2>
    <ul class="btn_wrap">
      <li><a href="/boardQuestionInput?page=${boardDto.page}&boardType=2">등록하기</a></li>
    </ul>
    <table class="list">
      <colgroup>
        <col width="4%">
        <col width="4%">
        <col width="18%">
        <col width="48%">
        <col width="14%">
        <col width="12%">
      </colgroup>
      <tr>
        <th>선택</th>
        <th>번호</th>
        <th>카테고리</th>
        <th>제목</th>
        <th>등록날짜</th>
        <th>수정</th>
      </tr>
      <c:forEach var="question" items="${questionList}" varStatus="status">
        <tr class="center">
	    	<td><input type="checkbox" name="boardCheck" value="${question.boardId}"></td>
       		 <td>${fn:length(questionList) - status.index}</td>
       		<td>${question.category}</td>
       		<td class="flow flowTitle"><a href="#">${question.title}</a></td>
        	<td>${question.writeTime}</td>
        	<td><a href="/boardQuestionRevise?page=${boardDto.page}&boardId=${question.boardId}" class="btn_more">수정하기</a></td>
        </tr>
	  </c:forEach>
    </table>
    <a onclick="return false;" id="btn_delete" class="btn_apply mb100">선택 삭제</a>
    
    <ul class="btn-group pagination">
  	<c:if test="${pageMaker.prev}">
   		<li>
     		 <a href='<c:url value="/boardQuestion?page=${pageMaker.startPage-1}&boardType=2" />'><i class="fa fa-chevron-left">이전</i></a>
  		</li>
 	</c:if>
  	<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
    	<li value="${pageNum}"> 
       		<a href='<c:url value="/boardQuestion?page=${pageNum}&boardType=2"/>'><i class="fa">${pageNum}</i></a>
    	</li>
    </c:forEach>
    <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
    	<li>
      		<a href='<c:url value="/boardQuestion?page=${pageMaker.endPage+1}&boardType=2"/>'><i class="fa fa-chevron-right">다음</i></a>
   		</li>
    </c:if>
	</ul>
	
	<c:set var="OutputPageTotal" value="${(pageMaker.totalCount/boardDto.perPageNum)+(1-((pageMaker.totalCount/boardDto.perPageNum)%1))%1}" />
	<fmt:parseNumber var="OutputPage"  value="${OutputPageTotal}" integerOnly="true" type="number"/>
    <p class="pageCnt">전체 ${pageMaker.totalCount}건, ${boardDto.page} / <c:out value="${OutputPage}"/> 페이지</p>
    
  </div>
</div>
</form>

</body>
</html>
