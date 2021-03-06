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

  <link rel="stylesheet" href="/www/inc/css/guide.css">
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="guide library_view">
  <div class="contentBox">
    <h1>시험자료실</h1>
    <div class="btnWrap topBtn">
    	<c:url value="/library_previous" var="preLibraryUrl">
            	<c:param name="boardType" value="${libraryInfo.boardType}" />
            	<c:param name="boardSequence" value="${libraryInfo.boardSequence}" />
            	<c:param name="searchWord" value="${libraryInfo.searchWord}" />
            	<c:param name="searchType" value="${libraryInfo.searchType}" />
            	<c:param name="page" value="${libraryInfo.page}" />
        </c:url>
        <c:url value="/library_next" var="nextLibraryUrl">
            	<c:param name="boardType" value="${libraryInfo.boardType}" />
            	<c:param name="boardSequence" value="${libraryInfo.boardSequence}" />
            	<c:param name="searchWord" value="${libraryInfo.searchWord}" />
            	<c:param name="searchType" value="${libraryInfo.searchType}" />
            	<c:param name="page" value="${libraryInfo.page}" />
          </c:url>
          <c:url value="/library" var="listLibraryUrl">
            	<c:param name="boardType" value="3" />
            	<c:param name="page" value="${libraryInfo.page}" />
            	<c:param name="searchWord" value="${libraryInfo.searchWord}" />
            	<c:param name="searchType" value="${libraryInfo.searchType}" />	
          </c:url>
    
      <c:if test="${librarySequence.pageMinBoardSequence != libraryInfo.boardSequence}">
     		 <a href="${preLibraryUrl}" class="btn">이전</a>
      	</c:if>
     	<c:if test="${librarySequence.pageMaxBoardSequence != libraryInfo.boardSequence}">
     		 <a href="${nextLibraryUrl}" class="btn">다음</a>
    	</c:if>
      <a href="${listLibraryUrl}" class="btn">목록</a>
    </div>
    <div class="content">
      <div class="info">
        <h3>${libraryInfo.title}</h3>
        <div>
          <p class="num">No. ${libraryInfo.boardSequence}</p>
          <p class="date"> ${libraryInfo.writeTime}</p>
          <p class="viewCount">조회수 :  ${libraryInfo.hitCnt}</p>
          <c:url value="/download" var="downloadUrl">
            	<c:param name="fileSize" value="${libraryInfo.fileDto.fileSize}" />
            	<c:param name="localFileName" value="${libraryInfo.fileDto.localFileName}" />
            	<c:param name="realFileName" value="${libraryInfo.fileDto.realFileName}" />
         </c:url>
          <c:if test="${not empty libraryInfo.fileDto}">
          <a href="${downloadUrl}" id="btn_download" class="document">${libraryInfo.fileDto.realFileName}</a> <!--문서표시 있을때 해당 태그 추가-->
          </c:if>
        </div>
      </div>
      <!-- 내용 영역// -->
      <div class="text">
        ${libraryInfo.contents}
      </div>
      <!-- //내용 영역 -->
    </div>
    <div class="btnWrap bottomBtn">
    	<c:if test="${librarySequence.pageMinBoardSequence != libraryInfo.boardSequence}">
     		 <a href="${preLibraryUrl}" class="btn">이전</a>
      	</c:if>
     	<c:if test="${librarySequence.pageMaxBoardSequence != libraryInfo.boardSequence}">
     		 <a href="${nextLibraryUrl}" class="btn">다음</a>
    	</c:if>
      <a href="${listLibraryUrl}" class="btn">목록</a>
    </div>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>

