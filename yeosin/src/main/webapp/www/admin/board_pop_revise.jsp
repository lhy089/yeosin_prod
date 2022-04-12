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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/boardmanage.js?t=<%= new java.util.Date() %>"></script>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
</head>

<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>

<form id="popupSaveForm" name="popupSaveForm" method="post" enctype="multipart/form-data">
<div class="board">
  <div class="contentBoxAd">
    <h1 class="title">팝업관리</h1>
    <h2>팝업관리 <em>-수정하기</em></h2>
    <table>
      <colgroup>
        <col width="25%">
        <col width="75%">
      </colgroup>
      <tr>
        <th>팝업제목</th>
        <td><input type="text" id="title" name="title" placeholder="제목명" value="${popupInfo.title}"></td>
      </tr>
      <tr>
        <th>시작일시</th>
        <td class="popDate">
          <input type="date" id="startDate" name="startDate" value="${popupInfo.startDate}" class="startDate">
          <input type="checkbox" class="check_today"> 시작일시를 오늘로
        </td>
      </tr>
      <tr>
        <th>종료일시</th>
        <td class="popDate">
          <input type="date" id="endDate" name="endDate" value="${popupInfo.endDate}" class="endDate">
          <input type="checkbox" class="check_7day"> 종료일시를 오늘로부터 7일 후로
        </td>
      </tr>
 	  <tr>
        <th>팝업첨부</th>
        <td class="info">
          <c:choose>
          	<c:when test="${fileInfo eq null}">
          		<input class="upload-name" value="파일선택" disabled="disabled">
          	</c:when>
          	<c:otherwise>
          		<input class="upload-name" value="${fileInfo.realFileName}" disabled="disabled">
          	</c:otherwise>
          </c:choose>
		  <label for="file" >파일선택</label>
          <input type="file" id="file" name="file" class="upload-hidden" accept=".jpg,.png,.gif"> 	
          <input type="hidden" name="fileId" id="fileId" value="${fileInfo.fileId}">
         <input type="hidden" name="fileName" id="fileName" value="${fileInfo.realFileName}">
          <a href="#" class="btn_more reset">삭제</a>
        </td>
      </tr>
      <tr>
      <th>팝업 노출여부</th>
        <td>
	        <select id="isVisible" name="isVisible">
				<c:choose>
				<c:when test="${popupInfo.isVisible eq 'Y'}">
					<option value="Y" selected="selected">Y</option>
	        		<option value="N">N</option>
				</c:when>
				<c:otherwise>
					<option value="N" selected="selected">N</option>
	        		<option value="Y">Y</option>
				</c:otherwise>
				</c:choose>
	        </select>
        </td>
      </tr>
      <tr>
      <!-- 팝업 내용 및 팝업 레이어 주석처리 -->
<!--      <th>팝업 내용</th>
        <td>
        	<textarea id="contents" name="contents" cols="10" rows="10"></textarea>
        </td>
      </tr>
      <tr>
        <th>팝업레이어 좌측 위치</th>
        <td><input type="number" min="0"> px</td>
      </tr>
      <tr>
        <th>팝업레이어 상단 위치</th>
        <td><input type="number" min="0"> px</td>
      </tr> -->
    </table>
    <input type="hidden" name="popupId" id="popupId" value="<%=request.getParameter("popupId")%>" />
    <div class="btn_apWrap mb100">
      <a href="/PopupList" onclick="return doPopupSave()" class="btn_apply">수정</a>
      <a href="/PopupList" class="btn_apply">뒤로가기</a>
    </div>
  </div>
</div>
</form>

<script>
$(function(){
	/* 첨부파일 */
	var fileTarget = $('.info .upload-hidden');
	fileTarget.on('change', function(){
	  if(window.FileReader) {
	    var filename = $(this)[0].files[0].name;
	  } else {
	    var filename = $(this).val().split('/').pop().split('\\').pop();
	  }
	  $(this).siblings('.upload-name').val(filename);
	  $('#fileName').val(filename);
	});
	
	$('.reset').click(function(){ //선택파일 초기화
	  $('#file,.upload-name').val('');
	  $('#fileName').val('');
	});

	/* 시작일시를 오늘로 */
	function getToday()
	{
		var date = new Date();
		var yyyy = date.getFullYear();
		var month = date.getMonth() + 1;
		var mm = month > 9 ? month : '0' + month;
		var dd = date.getDate() > 9 ? date.getDate() : '0' + date.getDate();

	    return yyyy + '-' + mm + '-' + dd;
	}
	
	$('.check_today').click(function(){
		  if($(this).is(':checked')){
		    $('.startDate').val(getToday());
		  }
	});
	
	/* 종료일시를 오늘로부터 7일 후로 */
	function getTodayAddSevenDay()
	{
		var date = new Date(getToday()); 
		date.setDate(date.getDate() + 7); 
		
		var yyyy = date.getFullYear();
		var month = date.getMonth() + 1;
		var mm = month > 9 ? month : '0' + month;
		var dd = date.getDate() > 9 ? date.getDate() : '0' + date.getDate();
		
		return yyyy + '-' + mm + '-' + dd;
	}
	
	$('.check_7day').click(function(){
	  if($(this).is(':checked')){
	    $('.endDate').val(getTodayAddSevenDay());
	  }
	});

});
</script>
</body>
</html>
