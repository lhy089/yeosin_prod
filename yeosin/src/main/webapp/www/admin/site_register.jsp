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
	<link rel="stylesheet" href="/www/inc/css/admin.css">
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/applymanage.js?t=<%= new java.util.Date() %>"></script>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<script>
	$(document).ready(function(){ 
		
		var examZoneId = $("#examZoneId").val();
		
		if (examZoneId == null || examZoneId == "null" || examZoneId == "")
		{
			$("#screenTitle").append("고사장 등록");
		}
		else 
		{
			$("#screenTitle").append("고사장 수정");
		}
		$("#screenTitle").append();
	});
	</script>
</head>

<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>

<form id="examZoneSaveForm" name="examZoneSaveForm" method="post" enctype="multipart/form-data">
<div class="site register">
  <div class="contentBoxAd">
    <h1 class="title">고사장설정</h1>
   	<input type="hidden" name="examZoneId" id="examZoneId" value="<%=request.getParameter("examZoneId")%>" />
    <h2 id="screenTitle">
  		<!-- 고사장 등록 or 고사장 수정 -->
	</h2>
    <table class="list">
      <colgroup>
        <col width="25%">
        <col width="75%">
      </colgroup>
      <tr>
        <th>지역</th>
        <td><input type="text" name="local" id="local" value="${examZone.local}"></td>
      </tr>
      <tr>
        <th>구</th>
        <td><input type="text" name="localDetail" id="localDetail" value="${examZone.localDetail}"></td>
      </tr>
      <tr>
        <th>고사장명</th>
        <td><input type="text" name="examZoneName" id="examZoneName" value="${examZone.examZoneName}"></td>
      </tr>
      <tr>
        <th>시험 교실 수</th>
        <td><input type="number" name="examRoomCnt" id="examRoomCnt" value="${examZone.examRoomCnt}"></td>
      </tr>
      <tr>
        <th>교실당 인원 수</th>
        <td><input type="number" name="examRoomUserCnt" id="examRoomUserCnt" value="${examZone.examRoomUserCnt}"></td>
      </tr>
      <tr>
        <th>주소</th>
        <td><input type="text" name="address" id="address" value="${examZone.description}"></td>
      </tr>
      <tr>
		<th>약도 등록</th>
         <td>
         	<button style="width:100px; height:40px; font-size:15px;" type="button" onclick="doAddMapFile()">추가</button>
        	<button style="width:100px; height:40px; font-size:15px;" type="button" onclick="doDeleteMapFile()">삭제</button>
	        <c:choose>
				<c:when test="${examZone.fileDto.fileId eq null}">

				</c:when>
				<c:otherwise>
					<c:url value="/examZoneMapDownload" var="downloadUrl">
            			<c:param name="fileSize" value="${examZone.fileDto.fileSize}" />
            			<c:param name="localFileName" value="${examZone.fileDto.localFileName}" />
            			<c:param name="realFileName" value="${examZone.fileDto.realFileName}" />
           			</c:url>
           			<a style="width:100px; height:40px; font-size:20px;" href="${downloadUrl}" id="btn_download">다운로드</a>
				</c:otherwise>
			</c:choose>
          	<input name="inputFileName" id="inputFileName" class="upload-name" value="${examZone.examZoneMap}" disabled="disabled">
          	<input style="display:none" type="file" id="file" name="file" class="upload-hidden">
          	<input type="hidden" name="fileId" id="fileId" value="${examZone.fileDto.fileId}">
        </td>
      </tr>
    </table>
	<a href="/siteRegister" onclick="return doExamZoneSave()" class="btn_apply mb100">저장</a>
  </div>
</div>
</form>

<script>
$(function(){
  	/* 첨부파일 */
  	var fileTarget = $('.upload-hidden');
  
  	fileTarget.on('change', function(){
	if (window.FileReader) 
	{
	  var filename = $(this)[0].files[0].name;
	} 
	else 
	{
	  var filename = $(this).val().split('/').pop().split('\\').pop();
	}
	$(this).siblings('.upload-name').val(filename);
  });
});
</script>

</body>
</html>
