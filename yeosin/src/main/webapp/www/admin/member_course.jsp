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

  <script src="../inc/js/jquery-3.4.1.min.js"></script><!--test용-->
  <link rel="stylesheet" href="/www/inc/css/reset.css"><!--test용-->
  <link rel="stylesheet" href="/www/inc/css/font.css"><!--test용-->
  <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css" rel="stylesheet"> <!--test용-->
  <link rel="stylesheet" href="/www/inc/css/common.css"><!--test용-->
  <link rel="stylesheet" href="/www/inc/css/admin.css">
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
</head>

<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>

<div class="member course">
  <div class="contentBoxAd">
    <h1 class="title">회원관리</h1>
    <form action="/memberCourseViewMng" method="GET" onsubmit="return setSubjectValue();">
    <h2>교육수료정보</h2>
    <table>
      <colgroup>
        <col width="14%">
        <col width="23.5%">
        <col width="12.5%">
        <col width="*">
        <col width="12.5%">
        <col width="11.5%">
      </colgroup>
      <tr>
        <th>검색어</th>
        <td><input type="text" id="searchWord" name="searchWord" value="${eduCompletionInfo.searchWord}"></td>
        <th>과목</th>
        <td>
          <label class="type"><input type="checkbox" name="subjectVal" <c:if test="${eduCompletionInfo.subject eq 'LP01'}">checked="checked"</c:if> value="LP01"> 대출성상품</label>
          <label class="type"><input type="checkbox" name="subjectVal" <c:if test="${eduCompletionInfo.subject eq 'LS01'}">checked="checked"</c:if> value="LS01"> 리스 할부상품</label>
          <input type="hidden" name="subject" id="subject" value="">	
        </td>
        <th>성별</th>
        <td>
          <label class="agree"><input type="radio" name="gender" <c:if test="${eduCompletionInfo.gender eq '남'}">checked="checked"</c:if> value="남"> 남</label>
          <label class="agree"><input type="radio" name="gender" <c:if test="${eduCompletionInfo.gender eq '여'}">checked="checked"</c:if> value="여"> 여</label>
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
     <input style="border:none;" class="btn_apply mb100" type="submit" value="조회"/>
	</form>

    <table class="memberList">
      <colgroup>
        <col width="5.5%">
        <col width="5.5%">
        <col width="*">
        <col width="17.5%">
        <col width="17.5%">
        <col width="17.5%">
        <col width="17.5%">
      </colgroup>
      <tr>
        <th>선택</th>
        <th>번호</th>
        <th>내용</th>
        <th>전송일시</th>
        <th>전송</th>
        <th>수신</th>
        <th>결과 등록</th>
      </tr>
      <c:forEach var="apiSyncHis" items="${eduCompletionHisList}" varStatus="status">
      	<tr class="center">
        	<td><input type="checkbox" name="memberCheck" value=""></td>
        	<td>${status.count}</td>
        	<td class="flow flowCon"><p>교육수료자 리스트</p></td>
        	<td>${apiSyncHis.apiSyncDate}</td>
        	<td class="flow flowReceived"><p>여신금융협회</p></td>
        	<td class="flow flowSent"><p>능률협회</p></td>
        	<td><a href="/memberCourseViewMng?apiSyncId=${apiSyncHis.apiSyncId}" class="btn_more">자세히 보기</a></td>
      	</tr>
      </c:forEach>
    </table>
	<br>
	<ul class="btn-group pagination">
	 <c:if test="${pageMaker.prev}">
	     <li>
	        <a href='<c:url value="/memberCourseMng?page=${pageMaker.startPage-1}&onePageDataCountCondition=${pageCondition}" />'><i class="fa fa-chevron-left">이전</i></a>
	    </li>
	</c:if>
	 <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
	   <li value="${pageNum}"> 
	         <a href='<c:url value="/memberCourseMng?page=${pageNum}&onePageDataCountCondition=${pageCondition}" />'><i class="fa">${pageNum}</i></a>
	   </li>
	</c:forEach>
	<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	   <li>
	        <a href='<c:url value="/memberCourseMng?page=${pageMaker.endPage+1}&onePageDataCountCondition=${pageCondition}"/>'><i class="fa fa-chevron-right">다음</i></a>
	     </li>
	</c:if>
	</ul>   
	<c:set var="OutputPageTotal" value="${(pageMaker.totalCount/eduCompletionHisDto.perPageNum)+(1-((pageMaker.totalCount/eduCompletionHisDto.perPageNum)%1))%1}" />
	<fmt:parseNumber var="OutputPage"  value="${OutputPageTotal}" integerOnly="true" type="number"/>
	<p class="pageCnt">전체 ${pageMaker.totalCount}건, ${eduCompletionHisDto.page} / <c:out value="${OutputPage}"/> 페이지</p>	
  </div>
</div>

<script>
(function(d) {
  var config = {
    kitId: 'ndu0cee',
    scriptTimeout: 3000,
    async: true
  },
  h=d.documentElement,t=setTimeout(function(){h.className=h.className.replace(/\bwf-loading\b/g,"")+" wf-inactive";},config.scriptTimeout),tk=d.createElement("script"),f=false,s=d.getElementsByTagName("script")[0],a;h.className+=" wf-loading";tk.src='https://use.typekit.net/'+config.kitId+'.js';tk.async=true;tk.onload=tk.onreadystatechange=function(){a=this.readyState;if(f||a&&a!="complete"&&a!="loaded")return;f=true;clearTimeout(t);try{Typekit.load(config)}catch(e){}};s.parentNode.insertBefore(tk,s)
})(document);
</script>
</body>
</html>
