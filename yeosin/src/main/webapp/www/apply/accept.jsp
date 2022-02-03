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

  <link rel="stylesheet" href="/www/inc/css/apply.css">
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="apply accept">
  <div class="contentBox">
    <h1>원서접수 확인 및 취소</h1>
    <div class="announcement">
      접수를 취소할 경우 환불 규정을 꼭 확인해야 합니다.
    </div>
    <table>
      <colgroup>
        <col width="*">
        <col width="19.5%">
        <col width="15%">
        <col width="15%">
        <col width="15%">
      </colgroup>
      <tr>
        <th>시험명</th>
        <th>접수기간</th>
        <th>시험일</th>
        <th>시험지역</th>
        <th>바로가기</th>
      </tr>
      <c:forEach var="apply" items="${applyList}">
      	<tr>
			<td>${apply.examDto.examName}</td>
	        <td>${apply.examDto.receiptStartDate} ~ ${apply.examDto.receiptEndDate}</td>
	        <td>${apply.examDto.examDate}</td>
	        <td>${apply.examDto.examLocal}</td>
	        <td><a href="/accept_view?receiptId=${apply.receiptId}" class="btn_apply">자세히 보기</a></td>
      	</tr>
      </c:forEach>
    </table>
    
   <ul class="btn-group pagination">
  	<c:if test="${pageMaker.prev}">
   		<li>
     		 <a href='<c:url value="/accept?page=${pageMaker.startPage-1}&userId=${applyDto.userId}" />'><i class="fa fa-chevron-left">이전</i></a>
  		</li>
 	</c:if>
  	<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
    	<li>
       		<a href='<c:url value="/accept?page=${pageNum}&userId=${applyDto.userId}"/>'><i class="fa">${pageNum}</i></a>
    	</li>
    </c:forEach>
    <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
    	<li>
      		<a href='<c:url value="/accept?page=${pageMaker.endPage+1}&userId=${applyDto.userId}"/>'><i class="fa fa-chevron-right">다음</i></a>
   		</li>
    </c:if>
	</ul>
	
	<c:set var="OutputPageTotal" value="${(pageMaker.totalCount/applyDto.perPageNum)+(1-((pageMaker.totalCount/applyDto.perPageNum)%1))%1}" />
	<fmt:parseNumber var="OutputPage"  value="${OutputPageTotal}" integerOnly="true" type="number"/>
    <p class="pageCnt">전체 ${pageMaker.totalCount}건, ${applyDto.page} / <c:out value="${OutputPage}"/> 페이지</p>
    <div class="pageWrap">
      <!-- 페이징 -->
    </div>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>
