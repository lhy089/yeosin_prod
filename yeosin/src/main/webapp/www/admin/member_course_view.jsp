<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="ko">
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
  <script>
  $(document).ready(function(){ 
 	$("#excelDownload").click(function(){
 		saveExcel();
	});
  });
  
  /*
  	excel download 기능.
  	- 필수 : (1)번 부분에 맞춰서 head, body를 선택하면 됨.
  	- 선택 : (2)번은 그대로 사용하거나 변경/추가해서 사용 가능. 
  */  
  function saveExcel() {
	 /*
		(1)
	  	 아래 두 가지 요소만 정확하게 선택되면 됨.
	   	$('.column_thead').find('th')
	 	$('#columnList').find('tr') 
	*/
  	var columnList = $.map($('.column_thead').find('th'), function(th){ // 리스트 head 찾기 
		if(!$(th).hasClass('first')) return $(th).text();
	});
	
	var dataList = new Array();
	$('#columnList').find('tr').each(function(tr){ // 리스트 body 찾기
		if(tr == 0) return true;
		var row = new Array();
		$(this).children().each(function(idx){
			if(idx == 0) return;
			row.push($(this).text());
		});
		dataList.push(row.join('▒'));
	});
	
	$("#fileName").val("수료증번호 목록");  // 다운로드 받을 엑셀 이름 정의
	$("#columns").val(columnList.join(','));
	$("#data").val(dataList.join('▧'));
	$("#excelForm").submit();
	/* 
		(2)
		<form action="/excelDownload" method="POST" name="excelForm" id="excelForm">
		/excelDownload > UserManageController 에 있음.
	 	완전히 똑같이 호출해도 되고,
	 	/excelDownloadForApplyList 와 같이 다른 이름으로 controller에 추가해서 사용 가능.
	 	controller 메서드는 그대로 사용.
	*/;
  }
  
  function setSubjectValue() {
	  var isLP01 = $('input[name="subjectVal"][value="LP01"]').is(':checked');
	  var isLS01 = $('input[name="subjectVal"][value="LS01"]').is(':checked');
	  if (isLP01 && !isLS01){
		  $("#subject").val("LP01");
	  }else if (!isLP01 && isLS01){
		  $("#subject").val("LS01");
	  }
	  return true;
	  debugger;
  }
  </script>

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
    </table>
     <input style="border:none;" class="btn_apply mb100" type="submit" value="조회"/>
<!--     <a id ="btn_search" type="submit" class="btn_apply mb100">조회</a> -->
	</form>

	  
  	<form action="/excelDownload" method="POST" name="excelForm" id="excelForm">
  		<input type="hidden" name="fileName" id="fileName" value="수료증번호 목록">	
  		<input type="hidden" name="columns" id="columns" value="">	
  		<input type="hidden" name="data" id="data" value="">	
  	</form>
    <ul class="btn_wrap">
      <li><a onclick="return false;" id="excelDownload">엑셀다운로드</a></li>
    </ul>

    <table class="memberList" id="columnList">
      <colgroup>
        <col width="5.5%">
        <col width="5.5%">
        <col width="13%">
        <col width="12%">
        <col width="5.5%">
        <col width="29.5%">
        <col width="29.5%">
      </colgroup>
      <tr class="column_thead">
        <th class="first">선택</th>
        <th>번호</th>
        <th>이름</th>
        <th>생년월일</th>
        <th>성별</th>
        <th>과목</th>
        <th>수료증번호</th>
      </tr>
      <c:forEach var="eduCompletionList" items="${eduCompletionList}" varStatus="status">
      	<tr class="center">
        	<td><input type="checkbox" name="memberCheck" value=""></td>
        	<td>${status.count}</td>
        	<td class="flow flowName"><p>${eduCompletionList.userName}</p></td>
        	<td>${eduCompletionList.birthDate}</td>
        	<td>${eduCompletionList.gender}</td>
        	<td><p>${eduCompletionList.subject}</p></td>
        	<td>${eduCompletionList.certId}</td>
      	</tr>
      </c:forEach>
    </table>
    <a href="/memberCourseMng" class="btn_apply mb100">뒤로가기</a>
  </div>
</div>

</body>
</html>

