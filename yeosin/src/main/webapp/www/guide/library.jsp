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

  <link rel="stylesheet" href="/www/inc/css/guide.css">
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	   $("#btn_search").click(function() {
	      $("#boardType").val('3');
	      $("#category").val('');
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

<form id="commonform" name="commonform" method="post" action="/library">
<input type="hidden" name="boardType" id="boardType" >
<input type="hidden" name="category" id="category">

<div class="guide library">
  <div class="contentBox">
    <h1>시험자료실</h1>
    <div class="searchBox">
      <select id="searchType" name="searchType">
        <option value="S" <c:if test="${boardDto.searchType eq 'S'}">selected="selected"</c:if>>제목</option>
        <option value="C" <c:if test="${boardDto.searchType eq 'C'}">selected="selected"</c:if>>내용</option>
        <option value="A" <c:if test="${boardDto.searchType eq 'A'}">selected="selected"</c:if>>제목+내용</option>
      </select>
       <input type="text" id="searchWord" name="searchWord" placeholder="내용을 입력해주세요" value="${boardDto.searchWord}" >
      <!--<a href="/#" id="btn_search" class="btn_serch">검색</a>-->
      <button id="btn_search" name="btn_search" class="btn_serch">검색</button>
    </div>
    <ul class="dataList">
      <c:forEach var="library" items="${libraryList}">	
      	<li>
        	<a href="/library_view?boardType=${library.boardType}&boardSequence=${library.boardSequence}&page=${pageMaker.boardDto.page}&searchWord=${library.searchWord}&searchType=${library.searchType}">${library.title}</a>
        	<div>
          		<p class="num">No.${library.boardSequence}</p>
         	 	<p class="viewCount">조회수 : ${library.hitCnt}</p>
         	 	<p class="document"></p>
       		</div>
      	</li>
      </c:forEach>
    </ul>
    
    <ul class="btn-group pagination">
  	<c:if test="${pageMaker.prev}">
   		<li>
     		 <a href='<c:url value="/library?page=${pageMaker.startPage-1}&boardType=3&searchWord=${boardDto.searchWord}&searchType=${boardDto.searchType}" />'><i class="fa fa-chevron-left">이전</i></a>
  		</li>
 	</c:if>
  	<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
    	<li>
       		<a href='<c:url value="/library?page=${pageNum}&boardType=3&searchWord=${boardDto.searchWord}&searchType=${boardDto.searchType}"/>'><i class="fa">${pageNum}</i></a>
    	</li>
    </c:forEach>
    <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
    	<li>
      		<a href='<c:url value="/library?page=${pageMaker.endPage+1}&boardType=3&searchWord=${boardDto.searchWord}&searchType=${boardDto.searchType}"/>'><i class="fa fa-chevron-right">다음</i></a>
   		</li>
    </c:if>
	</ul>
	
	<c:set var="OutputPageTotal" value="${(pageMaker.totalCount/boardDto.perPageNum)+(1-((pageMaker.totalCount/boardDto.perPageNum)%1))%1}" />
	<fmt:parseNumber var="OutputPage"  value="${OutputPageTotal}" integerOnly="true" type="number"/>
    <p class="pageCnt">전체 ${pageMaker.totalCount}건,  ${boardDto.page} / <c:out value="${OutputPage}"/> 페이지</p>
    <div class="pageWrap">
      <!-- 페이징 -->
    </div>
  </div>
</div>
</form>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>

