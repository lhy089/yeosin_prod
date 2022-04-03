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

  <link rel="stylesheet" href="/www/inc/css/state.css">
</head>
<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="state result">
  <div class="contentBox">
    <h1>응시결과</h1>
    <div class="announcement">
      최근 3년의 응시 결과 정보만 조회 가능합니다
    </div>
    <c:choose>
    <c:when test="${resultListCnt>0}">
    <table>
      <colgroup>
        <col width="*">
        <col width="20%">
        <col width="20%">
        <col width="20%">
      </colgroup>
      <tr>
        <th>시험명</th>
        <th>시험차수</th>
        <th>응시결과</th>        
        <th>자격인증서</th>
      </tr>
      <c:forEach var="result" items="${resultList}">
      <tr>
        <td>${result.examDto.examName}</td>
        <td>${result.examDto.examDegree}</td>
        <td>${result.gradeDto.isPass}</td>
		<td>
		<c:url value="/certificate" var="url">
            	<c:param name="examDto.gradeStartDate" value="${result.examDto.gradeStartDate}" />
            	<c:param name="gradeDto.passCertId" value="${result.gradeDto.passCertId}" />
            	<c:param name="subjectDto.subjectName" value="${result.subjectDto.subjectName}" />
            	<c:param name="userDto.userName" value="${result.userDto.userName}" />
         </c:url>
         <c:set var = "passResult"  value = "${result.gradeDto.isPass}"/>
            <c:choose> 
            <c:when test="${passResult eq '합격'}">
            	 <a href="${url}"  class="btn_apply">출력하기</a>
            </c:when>   
            <c:otherwise>
                <p>출력 불가</p>
            </c:otherwise>
         </c:choose>     
        </td>
      </tr>
      </c:forEach>
    </table>
    </c:when>
    <c:otherwise>
    표시할 데이터가 없습니다.
    </c:otherwise>
    </c:choose>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>



