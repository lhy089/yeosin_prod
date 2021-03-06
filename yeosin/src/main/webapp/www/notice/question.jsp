<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="ko">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  <meta charset="utf-8">
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
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

  <link rel="stylesheet" href="/www/inc/css/notice.css">
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	
	if("${chkSearchValid}" != null && "${chkSearchValid}" != "") {
		alert("검색할 수 없는 문자열이 포함되어 있습니다. [${chkSearchValid}]");	
	}
	
	$("li[value='${pageMaker.boardDto.page}']").attr("class","on");
		
	   $("#btn_search").click(function() {
	      $("#boardType").val('2');
	      $("#category").val($("#category").val());
	      $("#searchWord").val($("#searchWord").val());
	      $("#searchType").val($("#searchType").val());
	      $("#page").val(1);
	      $("#commonform").submit(); 
	   });
});
</script>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>

<form id="commonform" name="commonform" method="post" action="/question">
<input type="hidden" name="boardType" id="boardType" >
<input type="hidden" name="page" id="page">

<div class="notice question">
  <div class="contentBox">
    <h1>자주하는 질문</h1>
    <div class="announcement">
      자주하는 질문을 검색할 수 있습니다.
    </div>
    <div class="searchBox">
       <select id="category" name="category">
       	<option value="">전체</option>
         <option value="원서접수 및 취소" <c:if test="${boardDto.category eq '원서접수 및 취소'}">selected="selected"</c:if>>원서접수 및 취소</option>
        <option value="시험안내" <c:if test="${boardDto.category eq '시험안내'}">selected="selected"</c:if>>시험안내</option>
        <option value="결제 및 환불" <c:if test="${boardDto.category eq '결제 및 환불'}">selected="selected"</c:if>>결제 및 환불</option>
        <option value="자격인증서 발급" <c:if test="${boardDto.category eq '자격인증서 발급'}">selected="selected"</c:if>>자격인증서 발급</option>
        <option value="응시 관련 " <c:if test="${boardDto.category eq '응시 관련 '}">selected="selected"</c:if>>응시 관련</option>
        <option value="회원가입 및 이용" <c:if test="${boardDto.category eq '회원가입 및 이용'}">selected="selected"</c:if>>회원가입 및 이용</option>
        <option value="시험 결과 관련 " <c:if test="${boardDto.category eq '시험 결과 관련 '}">selected="selected"</c:if>>시험 결과 관련</option>
        <option value="기타" <c:if test="${boardDto.category eq '기타'}">selected="selected"</c:if>>기타</option>
      </select>
       <select id="searchType" name="searchType">
        <option value="S" <c:if test="${boardDto.searchType eq 'S'}">selected="selected"</c:if>>제목</option>
        <option value="C" <c:if test="${boardDto.searchType eq 'C'}">selected="selected"</c:if>>내용</option>
        <option value="A" <c:if test="${boardDto.searchType eq 'A'}">selected="selected"</c:if>>제목+내용</option>
      </select>
      <input type="text" id="searchWord" name="searchWord" placeholder="내용을 입력해주세요" value="${boardDto.searchWord}">
     <!-- <a href="#" class="btn_serch">검색</a>-->
      <button id="btn_search" name="btn_search" class="btn_serch">검색</button>
    </div>
    <div class="questionList">
     <c:forEach var="question" items="${questionList}">	
      	<dl>
       		 <dt>${question.title}</dt>
       		 <dd>${question.contents}</dd>
      	</dl>
      </c:forEach> 
    </div>
    
    <ul class="btn-group pagination">
  	<c:if test="${pageMaker.prev}">
   		<li>
   			<c:url value="/question" var="preQuestionUrl">
   				<c:param name="page" value="${pageMaker.startPage-1}" />
            	<c:param name="boardType" value="2" />
            	<c:param name="searchWord" value="${boardDto.searchWord}" />
            	<c:param name="searchType" value="${boardDto.searchType}" />
            	<c:param name="category" value="${boardDto.category}"/>
        	 </c:url>
     		 <a href='${preQuestionUrl}'><i class="fa fa-chevron-left">이전</i></a>
  		</li>
 	</c:if>
  	<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
    	<li value="${pageNum}"> 
    		<c:url value="/question" var="curQuestionUrl">
   				<c:param name="page" value="${pageNum}" />
            	<c:param name="boardType" value="2" />
            	<c:param name="searchWord" value="${boardDto.searchWord}" />
            	<c:param name="searchType" value="${boardDto.searchType}" />
            	<c:param name="category" value="${boardDto.category}"/>
        	 </c:url>
       		<a href='${curQuestionUrl}'><i class="fa">${pageNum}</i></a>
    	</li>
    </c:forEach>
    <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
    	<li>
    		<c:url value="/question" var="nextQuestionUrl">
   				<c:param name="page" value="${pageMaker.endPage+1}" />
            	<c:param name="boardType" value="2" />
            	<c:param name="searchWord" value="${boardDto.searchWord}" />
            	<c:param name="searchType" value="${boardDto.searchType}" />
            	<c:param name="category" value="${boardDto.category}"/>
        	 </c:url>
      		<a href='${nextQuestionUrl}'><i class="fa fa-chevron-right">다음</i></a>
   		</li>
    </c:if>
	</ul>
	
	<c:set var="OutputPageTotal" value="${(pageMaker.totalCount/boardDto.perPageNum)+(1-((pageMaker.totalCount/boardDto.perPageNum)%1))%1}" />
	<fmt:parseNumber var="OutputPage"  value="${OutputPageTotal}" integerOnly="true" type="number"/>
    <p class="pageCnt">전체 ${pageMaker.totalCount}건, ${boardDto.page} / <c:out value="${OutputPage}"/> 페이지</p>
    <div class="pageWrap">
      <!-- 페이징 -->
    </div>
  </div>
</div>

</form>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

<script>
$(function(){
  /* 자주하는 질문 여닫기 */
  $('.questionList dt').click(function(){
    if ($(this).attr('class') == 'open') {
      $(this).removeClass('open');
      $(this).next('dd').slideUp('fast');
    } else {  //아코디언 슬라이드
      $('.questionList dt').removeClass('open');
      $(this).addClass('open');
      $('.questionList dd').slideUp('fast');
      $(this).next('dd').slideDown('fast');
    }
  });
});
</script>
</body>
</html>