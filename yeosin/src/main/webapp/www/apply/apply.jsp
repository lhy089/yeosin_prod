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
	
	<link rel="stylesheet" href="/www/inc/css/apply.css">
	
	<style>
		select {width:492px; height:50px; border:1px solid #c7c6c6; box-sizing:border-box; margin-right:16px; font-family:sandoll-gothicneo3, sans-serif; font-size:23px; color:#58585b; letter-spacing:-0.5px; text-indent:12px;}
	</style>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/apply/apply.js?t=1"></script>	
	<script>
	if (${isReceipt}) 
	{ 
		alert("이미 접수가 완료된 시험입니다.");
	}
	</script>
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="apply list">
  <div class="contentBox">
    <h1>원서접수</h1>
    <div class="announcement">
      인터넷접수 첫날의 접수 시작 시간은 10:00 부터입니다.
    </div>
    <c:choose>
	<c:when test='${examListCnt > 0}'>
	<c:forEach var="exam" items="${examList}">
    <table>
      <colgroup>
        <col width="34.7%">
        <col width="*">
      </colgroup>
      <tr>
        <th>시험명</th>
        <td>${exam.examName}</td>
      </tr>
      <tr>
        <th>시험회차</th>
        <td>${exam.examDegree}</td>
      </tr>
      <tr>
        <th>시험일</th>
        <td>${exam.examDate}</td>
      </tr>  
      <tr>
        <th>시험지역</th>
        <td>      
           <c:set var="examId" value="${exam.examId}" />   
           <select required name="examLocalList" id ="${exam.examId}">   
               <option value="">시험지역을 선택하세요.</option>              
               <c:forEach var="localList" items="${examLocalList}">
                     <c:set var="examIdLocal" value="${localList.examId}" />  
                     <c:choose> 
                  <c:when test="${examIdLocal eq examId}">
                  <option value="${localList.examZoneDto.local}">${localList.examZoneDto.local}</option>              
                  </c:when>  
                  </c:choose>                             
               </c:forEach>         
           </select>
        </td>
      </tr>   
      <tr>
        <th>원서접수</th>
        <td><a href="#" class="btn_apply" onclick="localChk('${exam.examId}', '${userInfo.userId}');">접수하기</a></td>
      </tr>
    </table>
    <br/>
    </c:forEach>
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
