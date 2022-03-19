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
	<link rel="stylesheet" href="/www/inc/css/admin.css?t=<%= new java.util.Date() %>">
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>

<script>
$(document).ready(function() {
	
	if(${alertResult}) { 
	      alert("시험일정이 등록되었습니다.");
	}
	
	if(${alertError}){
		alert("오류로 인하여 시험일정이 등록되지 않았습니다.");
	}
		
	   $("#btn_register").click(function() {
		   
		   if(confirm("시험일정을 등록하시겠습니까?"))
			{	
			   var examYear = "20" + $("#examYear").val();
			   var examDegree = $("#examDegree").val();
			   var examDate= $("#examDate").val();
			   var subjectId = $("input[name=subjectId]:checked");	
			   var receiptStartDate =  $("#receiptStartDate").val();
			   var receiptStartTime = $("#receiptStartTime").val();
			   var receiptEndDate = $("#receiptEndDate").val();
			   var receiptEndTime = $("#receiptEndTime").val();
			   var certPrintStartDate = $("#certPrintStartDate").val();
			   var certPrintEndDate = $("#certPrintEndDate").val();
			   var gradeStartDate = $("#gradeStartDate").val();
			   var examCost = $("#examCost").val();
			   var examZoneId = $("input[name=examZoneId]:checked");
			   
			   if(examYear == "20"){
				   alert("시험연도를 입력해주세요,"); return;
			   }else if(examDegree == ""){
				   alert("시험회차를 입력해주세요."); return;
			   }else if(examDate == ""){
				   alert("시험일를 입력해주세요."); return;
			   }else if(subjectId.length == 0){
				   alert("시험영역을 한개이상 선택해주세요."); return;
			   }else if (receiptStartDate == "" || receiptStartTime == "" || receiptEndDate == "" || receiptEndTime == ""){
				   alert("접수기간을 입력해주세요."); return;
			   }else if(certPrintStartDate == "" || certPrintEndDate == ""){
				   alert("수험표출력기간을 입력해주세요."); return;
			   }else if(gradeStartDate == ""){
				   alert("성적공고기간을 입력해주세요."); return;
			   }else if(examCost == ""){
				   alert("시험비용을 입력해주세요."); return;
			   }else if(examZoneId.length == 0){
				   alert("고사장 등록을 한개이상 선택해주세요."); return;
			   }
				  
			   $("#examYear").val(examYear);
			   $("#examDegree").val(examDegree);
			   $("#examDate").val(examDate);
			   $("#subjectId").val(subjectId);
			   $("#receiptStartDate").val(receiptStartDate);
			   $("#receiptStartTime").val(receiptStartTime);
			   $("#receiptEndDate").val(receiptEndDate);
			   $("#receiptEndTime").val(receiptEndTime);
			   $("#certPrintStartDate").val(certPrintStartDate);
			   $("#certPrintEndDate").val(certPrintEndDate);
			   $("#gradeStartDate").val(gradeStartDate);
			   $("#examCost").val(examCost);
			   $("#examZoneId").val(examZoneId);
		      $("#commonform").submit(); 
		  }
	   });
});

</script>


<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>
<form id="commonform" name="commonform" method="get" action="/manageRegister_action">
<div class="manage register">
  <div class="contentBoxAd">
    <h1 class="title">시험운영관리</h1>
    <h2>
    <c:choose>
		<c:when test="${examDto.examId eq null}">
			시험일정 등록 
		</c:when>
		<c:otherwise>
			시험일정 수정 
		</c:otherwise>
	</c:choose>
    </h2>
    <!-- <ul class="btn_wrap">
      <li><a href="#">엑셀다운로드</a></li>
    </ul>-->
    <table>
      <colgroup>
        <col width="25%">
        <col width="75%">
      </colgroup>
      <tr>
        <th>시험코드</th>
		<c:choose>
			<c:when test="${examDto.examId eq null}">
				<td>LPBQ [기본표기]</td>
			</c:when>
			<c:otherwise>
				<td>${examDto.examId}</td>
			</c:otherwise>
		</c:choose>
      </tr>
      <tr>
        <th>시험명</th>
        <c:choose>
			<c:when test="${examDto.examName eq null}">
				<td>상품 판매대리 중개업자 자격인증 평가 [기본표기]</td>
			</c:when>
			<c:otherwise>
				<td>${examDto.examName}</td>
			</c:otherwise>
		</c:choose>
      </tr>
      <tr>
        <th>시험회차</th>
        <td>
        제 20 
        <c:choose>
			<c:when test="${examDto.examYear eq null}">
				<input type="text" id="examYear" name="examYear" maxlength="2"> 
			</c:when>
			<c:otherwise>
				<input type="text" id="examYear" name="examYear" maxlength="2" value="${examDto.examYear}"> 
			</c:otherwise>
		</c:choose>
        - 
        <c:choose>
			<c:when test="${examDto.examDegree eq null}">
				<input type="text" maxlength="2" id="examDegree" name="examDegree"> 
			</c:when>
			<c:otherwise>
				<input type="text" maxlength="2" id="examDegree" name="examDegree" value="${examDto.examDegree}"> 
			</c:otherwise>
		</c:choose>
        회차 [연도 2자리 – 회차 1자리 입력]
        </td>
      </tr>
      <tr>
        <th>시험일</th>
        <td>
        <c:choose>
			<c:when test="${examDto.examDate eq null}">
				<input type="date" id="examDate" name="examDate" value=""> 
			</c:when>
			<c:otherwise>
				<input type="date" id="examDate" name="examDate" value="${examDto.examDate}"> 
			</c:otherwise>
		</c:choose>
        </td>
      </tr>
      <tr>
        <th>시험영역</th>
        <td>
        	<c:forEach var="subject" items="${subjectList}" varStatus="status">
	      		<label class="type">
	      		<c:choose>
			      	<c:when test="${subject.examAndSubjectRelDto eq null}">
			        	<input type="checkbox" name="subjectId" value="${subject.subjectId}"> ${subject.subjectName}
					</c:when>
					<c:otherwise>
			        	<input type="checkbox" name="subjectId" value="${subject.subjectId}" checked="checked"> ${subject.subjectName}
					</c:otherwise>
				</c:choose>
	      		</label>
	 	 	</c:forEach>
        </td>
      </tr>
      <tr>
        <th>접수기간</th>
        <td>
        <c:choose>
			<c:when test="${examDto.receiptStartDate eq null}">
	          <input type="date" name="receiptStartDate" id="receiptStartDate" value="">
			</c:when>
			<c:otherwise>
	          <input type="date" name="receiptStartDate" id="receiptStartDate" value="${examDto.receiptStartDate}">
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${examDto.receiptStartDate eq null}">
	          <input type="time" name="receiptStartTime" id="receiptStartTime" value="10:00">
			</c:when>
			<c:otherwise>
	          <input type="time" name="receiptStartTime" id="receiptStartTime" value="${examDto.receiptStartTime}">
			</c:otherwise>
		</c:choose>
        -
        <c:choose>
			<c:when test="${examDto.receiptEndDate eq null}">
	          <input type="date" name="receiptEndDate" id="receiptEndDate" value="">
			</c:when>
			<c:otherwise>
	          <input type="date" name="receiptEndDate" id="receiptEndDate" value="${examDto.receiptEndDate}">
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${examDto.receiptEndTime eq null}">
	          <input type="time" name="receiptEndTime" id="receiptEndTime" value="10:00">
			</c:when>
			<c:otherwise>
	          <input type="time" name="receiptEndTime" id="receiptEndTime" value="${examDto.receiptEndTime}">
			</c:otherwise>
		</c:choose>
        </td>
      </tr>
      <!-- <tr>
        <th>100%환불 종료일</th>
        <td>
          <input type="date" name="" value="">
        </td>
      </tr> -->
      <tr>
        <th>수험표출력기간</th>		
        <td>
        <c:choose>
			<c:when test="${examDto.certPrintStartDate eq null}">
				<input type="date" name="certPrintStartDate" id="certPrintStartDate" value="">
			</c:when>
			<c:otherwise>
				<input type="date" name="certPrintStartDate" id="certPrintStartDate" value="${examDto.certPrintStartDate}"> 
			</c:otherwise>
		</c:choose>
		-
		<c:choose>
			<c:when test="${examDto.certPrintEndDate eq null}">
				<input type="date" name="certPrintEndDate" id="certPrintEndDate" value="">
			</c:when>
			<c:otherwise>
				<input type="date" name="certPrintEndDate" id="certPrintEndDate" value="${examDto.certPrintEndDate}"> 
			</c:otherwise>
		</c:choose>
        </td>
      </tr>
      <tr>
        <th>성적공고기간</th>
        <td>
        <c:choose>
			<c:when test="${examDto.gradeStartDate eq null}">
				<input type="date" name="gradeStartDate" id="gradeStartDate" value="">
			</c:when>
			<c:otherwise>
				<input type="date" name="gradeStartDate" id="gradeStartDate" value="${examDto.gradeStartDate}"> 
			</c:otherwise>
		</c:choose>
        </td>
      </tr>
       <tr>
        <th>시험비용</th>
        <td>
        <c:choose>
			<c:when test="${examDto.examCost eq null}">
				<input type="text" name="examCost" id="examCost" class="won"> 원
			</c:when>
			<c:otherwise>
				<input type="text" name="examCost" id="examCost" class="won" value="${examDto.examCost}"> 원
			</c:otherwise>
		</c:choose>
        </td>
      </tr>
    </table>

    <h2>시험일정 고시장 등록</h2>
     <table class="signUp">
      <colgroup>
        <col width="5%">
        <col width="*">
        <col width="20%">
        <col width="20%">
        <col width="20%">
      </colgroup>
      <tr>
        <th></th>
        <th>고사장 선택</th>
        <th>시험 교실 수</th>
        <th>교실당 인원 수</th>
        <th>전체 인원 수</th>
      </tr>
       <c:forEach var="examZone" items="${examZoneList}" varStatus="status">
	      <tr>
	       <td>
		    <c:choose>
				<c:when test="${examZone.examAndExamzoneRelDto eq null}">
					<input type="checkbox" name="examZoneId" value="${examZone.examZoneId}">
				</c:when>
				<c:otherwise>
					<input type="checkbox" name="examZoneId" value="${examZone.examZoneId}" checked="checked">
				</c:otherwise>
			</c:choose>
	       </td>
	        <td>${examZone.examZoneName}</td>
	        <td>${examZone.examRoomCnt}</td>
	        <td>${examZone.examRoomUserCnt}</td>
	        <td>${examZone.examTotalUserCnt}</td>
	      </tr>
	  </c:forEach>
    </table>
    <!-- <a href="#" class="btn_plus">추가</a> -->
    <input type="hidden" id="examId" name="examId" value="${examDto.examId}">
    
    <c:choose>
		<c:when test="${examDto.examId eq null}">
			<a onclick="return false;" id="btn_register" class="btn_apply mb100">등록하기</a>
		</c:when>
		<c:otherwise>
			<a onclick="return false;" id="btn_register" class="btn_apply mb100">수정하기</a>
		</c:otherwise>
	</c:choose>
  </div>
</div>
</form>
<script>
$(function(){
  /* 원화표시 (1000단위당','추가)*/
  //Null check
  function isEmpty(value){
    if(value.length == 0 || value == null){
      return true;
    } else {
      return false;
    }
  }
  //Number check with Regular expression
  function isNumeric(value){
    var regExp = /^[0-9]+$/g;
    return regExp.test(value);
  }
  //숫자 세자리 마다 콤마를 추가하여 금액 표기 형태로 변환
  function currencyFormatter(amount){
    return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g,',');
  }

  $('.manage table input.won').on('focus',function(){
    var val = $('.manage table input.won').val();
    if(!isEmpty(val)){
      val = val.replace(/,/g,'');
      $('.manage table input.won').val(val);
    }
  });
  $('.manage table input.won').on('blur',function(){
    var val = $('.manage table input.won').val();
    if(!isEmpty(val) && isNumeric(val)){
      val = currencyFormatter(val);
      $('.manage table input.won').val(val);
    }
  });
});
</script>

</body>
</html>
