<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="ko">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
  <script type="text/javascript">
  window.onload = function() {
	  debugger;
	  if("${uploadSuccess}" == "FAILED") {
		  alert("오류가 발생했습니다.");
		  location.href = "/resultManage";
	  }else if ("${uploadSuccess}" == "NOT FOUND") {
		  alert("파일을 찾을 수 없습니다.");
		  location.href = "/resultManage";
	  }else if ("${uploadSuccess}" == "SUCCESS") {
		  alert("성적 등록을 완료 했습니다.");
		  location.href = "/resultManage";
	  }
  }
	$(document).ready(function() {
	});

	function checkFileType(filePath) {
		var fileFormat = filePath.split(".");
		if (fileFormat.indexOf("xlsx") > -1 || fileFormat.indexOf("xls") > -1) {
			return true;
		} else {
			return false;
		}
	}

	function check(examId) {
		var file = $("#excel_"+examId).val();
		if (file == "" || file == null) {
			alert("파일을 선택해 주세요");
			return false;
		} else if (!checkFileType(file)) {
			alert("엑셀(xlsx) 파일만 업로드 가능합니다");
			return false;
		}
		var fileFormat = file.split(".");
		var fileType = fileFormat[1];
		if (confirm("업로드 하시겠습니까?")) {
			$("#excelType").val(fileType);
			$("#examId").val(examId);
			$("#excelUpForm").submit();
		}
	}
  </script>
</head>

<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>

<div class="result manage">
  <div class="contentBoxAd">
    <h1 class="title">성적관리</h1>
    <h2>성적처리</h2>
    <form id="excelUpForm" name="excelUpForm" action="/excelUploadForGradeRegistration" method="post" enctype="multipart/form-data">
    <input type="hidden" id="excelType" name="excelType" value=""/>
    <input type="hidden" id="examId" name="examId" value=""/>
    <table class="list">
      <colgroup>
<!--         <col width="4%"> -->
        <col width="4%">
        <col width="22.5%">
        <col width="10.5%">
        <col width="14%">
        <col width="26.5%">
        <col width="12%">
        <col width="10.5%">
      </colgroup>
      <tr>
<!--         <th>선택</th> -->
        <th>번호</th>
        <th>시험고유번호</th>
        <th>차수</th>
        <th>시험일시</th>
        <th>시험영역</th>
        <th>결과 등록</th>
        <th>처리결과</th>
      </tr>
      <c:forEach var="examInfo" items="${examList}" varStatus="status">
      	<tr class="center">
<!--         <td><input type="checkbox" name="memberCheck" value=""></td> -->
        	<td>${status.count}</td>
        	<td class="flow flowNum"><p>${examInfo.examId}</p></td>
        	<td>${examInfo.examDegree}</td>
        	<td>${examInfo.examDate}</td>
        	<td class="flow flowSub"><p>대출/리스</p></td>
        	<td class="upload">
        		<input id="excel_${examInfo.examId}" name="excel_${examInfo.examId}" type="file">
<%--         		<label className="input-file-button" for="input-file" id="excelUp" onclick="check('${examInfo.examId}')">등록하기</label> --%>
				<button type="button" id="excelUp" onclick="check('${examInfo.examId}')">등록하기</button>
        	</td>
        	<td>${examInfo.gradeStatus}</td>
      	</tr>
      </c:forEach>
    </table>
    </form>
  </div>
</div>

</body>
</html>
