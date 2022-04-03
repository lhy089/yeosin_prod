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
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <link rel="stylesheet" href="/www/inc/css/admin.css">
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <script> 
 $(window).bind("pageshow", function(event) {
	    if ( event.originalEvent && event.originalEvent.persisted) {// BFCahe
	        window.location.reload();
	    }
	}); 
 
   if(${isAlert}) { 
	      alert("로그인 후 이용 가능합니다.");
	}
   
   document.addEventListener('keydown', function(event) {
	   if (event.keyCode === 13) {
	     event.preventDefault();
	   };
	 }, true);
      
   $(document).ready(function() {
	   $("li[value='${pageMaker.userDto.page}']").attr("class","on");
	   
		$("#btn_search").click(function() {
		
			var generalUser = $("input:checkbox[id=checkGeneral]").is(":checked") ? "Y" : "N";
			var dormancyUser = $("input:checkbox[id=checkDormancy]").is(":checked") ? "Y" : "N";
			var secessionUser = $("input:checkbox[id=checkSecession]").is(":checked")? "Y" : "N";
					
		   $("#searchWord").val($("#searchWord").val());
		   $("#generalUser").val(generalUser);
		   $("#dormancyUser").val(dormancyUser);
		   $("#secessionUser").val(secessionUser);
		   $('input[name=searchEmailType]').val($('input[name=searchEmailType]:checked').val());
		   $('input[name=searchSMSType]').val($('input[name=searchSMSType]:checked').val());
		   $("#onePageDataCountCondition").val($("#onePageDataCountCondition").val());
		   $("#commonform").submit(); 
		});
		
		$("#excelDownload").click(function(){
	 		saveExcel();
		});
		
		$("#btn_detail").click(function(){
				
			if($('input:radio[name=memberCheck]').is(':checked') == false)
			{
				alert('선택된 회원이 없습니다.');
				return;
			}
			
			var generalUser = $("input:checkbox[id=checkGeneral]").val();
			var dormancyUser = $("input:checkbox[id=checkDormancy]").val();
			var secessionUser = $("input:checkbox[id=checkSecession]").val();
			
			  $("#searchWord").val($("#searchWord").val());
			   $("#generalUser").val(generalUser);
			   $("#dormancyUser").val(dormancyUser);
			   $("#secessionUser").val(secessionUser);
			   $('input[name=searchEmailType]').val($('input[name=searchEmailType]:checked').val());
			   $('input[name=searchSMSType]').val($('input[name=searchSMSType]:checked').val());
			   $("#onePageDataCountCondition").val($("#onePageDataCountCondition").val());
			   $('input[name=memberCheck]').val($('input[name=memberCheck]:checked').val());
			   $("#modifyForm").submit(); 
		});
		
// 		$("#loginForce").click(function(){
// 			loginForce(this.dataset.userId, this.dataset.userName);
// 		});
	});
   
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

		$("#fileName").val("회원정보 목록");  // 다운로드 받을 엑셀 이름 정의
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
   
   function loginForce(userId, userName) {
	   if(confirm(userName + "님으로 접속 하시겠습니까?")) {
	   $.ajax({
			url: "/loginForceByMng",
	        type: "POST",
	        sendDataType : 'string',
 			dataType : 'text',
	        data: {userId : userId},
	        success: function(data) 
			{ debugger;
	        	if(data == 'U') {
	          	   $(location).attr("href", "/www/main.jsp");
	             }else {
	             	alert("회원 정보가 존재하지 않거나 일치하지 않습니다.")
	             }
	        },
	        error: function() 
			{
	           console.log("AJAX Request 실패");
	        }
		});
	   }
   }

 </script>

<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>

<form id="commonform" name="commonform" method="get" action="/member_info">
<div class="member info">
  <div class="contentBoxAd">
    <h1 class="title">회원관리</h1>
    <h2>회원정보</h2>
    <table>
      <colgroup>
        <col width="17%">
        <col width="33%">
        <col width="17%">
        <col width="33%">
      </colgroup>
      <tr>
        <th>검색어</th>
        <td><input type="text" id="searchWord"  name="searchWord" value="${searchWord}"></td>
        <th>회원구분</th>
        <td>
          <input type="hidden" id="generalUser" name="generalUser">
          <input type="hidden" id="dormancyUser" name="dormancyUser">
          <input type="hidden" id="secessionUser" name="secessionUser">
          <label class="type"><input type="checkbox" id="checkGeneral" name="checkUserStatus" <c:if test="${generalUser eq 'Y'}">checked="checked"</c:if>> 일반</label>  
          <label class="type"><input type="checkbox" id="checkDormancy" name="checkUserStatus" <c:if test="${dormancyUser eq 'Y'}">checked="checked"</c:if>> 휴면</label>
          <label class="type"><input type="checkbox" id="checkSecession" name="checkUserStatus"<c:if test="${secessionUser eq 'Y'}">checked="checked"</c:if>> 탈퇴</label>
        </td>
      </tr>
      <tr>
        <th>이메일 수신 여부</th>
        <td>
          <label class="agree"><input type="radio" name="searchEmailType" <c:if test="${searchEmailType eq 'A'}">checked="checked"</c:if> value="A"> 전체</label>
          <label class="agree"><input type="radio" name="searchEmailType" <c:if test="${searchEmailType eq 'Y'}">checked="checked"</c:if> value="Y"> 수신허용</label>
          <label class="agree"><input type="radio" name="searchEmailType" <c:if test="${searchEmailType eq 'N'}">checked="checked"</c:if> value="N"> 수신거부</label>
        </td>
        <th>문자(SMS) 수신 여부</th>
        <td>
          <label class="agree"><input type="radio" name="searchSMSType" <c:if test="${searchSMSType eq 'A'}">checked="checked"</c:if> value="A"> 전체</label>
          <label class="agree"><input type="radio" name="searchSMSType" <c:if test="${searchSMSType eq 'Y'}">checked="checked"</c:if> value="Y"> 수신허용</label>
          <label class="agree"><input type="radio" name="searchSMSType" <c:if test="${searchSMSType eq 'N'}">checked="checked"</c:if> value="N"> 수신거부</label>
        </td>
      </tr>
       <tr>
       <th>목록건수</th>
           <td>
			<select id="onePageDataCountCondition" name="onePageDataCountCondition" class="count">
	            <c:forEach var="i" begin="50" end="300" step="50">
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
          <td colspan="2"></td>
      </tr>
    </table>
    <a onclick="return false;" id="btn_search" class="btn_apply mb100">조회</a>
	</form>

	<form action="/excelDownload" method="POST" name="excelForm" id="excelForm">
  		<input type="hidden" name="fileName" id="fileName" value="">	
  		<input type="hidden" name="columns" id="columns" value="">	
  		<input type="hidden" name="data" id="data" value="">	
  	</form>
    <ul class="btn_wrap">
 	  <li><a onclick="return false;" id="btn_detail">자세히보기</a></li>
      <li><a onclick="return false;" id="excelDownload">엑셀다운로드</a></li>
 <!-- <li><a href="#">회원등급</a></li>
      <li><a href="#">정보삭제</a></li> -->
    </ul>
    <form  id="modifyForm" name="modifyForm" method="get"  action="/member_modify">
    <table class="memberList" id="columnList">
      <colgroup>
        <col width="4%">
        <col width="4%">
        <col width="6.5%">
        <col width="7%">
        <col width="11%">
        <col width="4%">
        <col width="8.5%">
        <col width="8.5%">
        <col width="11%">
        <col width="11%">
        <col width="14%">
        <col width="8.5%">
      </colgroup>
      <tr class="column_thead">
        <th class="first">선택</th>
        <th>번호</th>
		<th>상태</th>
        <th>이름</th>
        <th>아이디</th>
        <th>성별</th>
        <th>가입일</th>
        <th>생년월일</th>
        <th>연락처</th>
        <th>휴대전화</th>
        <th>이메일</th>
        <th>상태</th>
      </tr>
      <c:forEach var="user" items="${userList}" varStatus="status">
	      <tr class="center">
	        <!-- <td><input type="checkbox" name="memberCheck" value=""></td> -->
      		<td><input type="radio" name="memberCheck" value="${user.userId}"></td>
	        <td>${status.count}</td>
			<td>${user.userStatus}</td>
	        <td class="flow flowName"><p>${user.userName}</p></td>
	        <td class="flow flowId" ><p>${user.userId}</p></td>
	        <td>${user.gender}</td>
	        <td>${user.joinDate}</td>
	        <td>${user.birthDate}</td>
	        <td>${user.callNumber}</td>
	        <td>${user.phoneNumber}</td>
	        <td class="flow flowEmail"><p>${user.emailAddress}</p></td>
	        <td><a onclick="loginForce('${user.userId}','${user.userName}')" id="loginForce" href="#" class="btn_more">접속</a></td>
	      </tr>
	  </c:forEach>
    </table>
    </form>
    <ul class="btn-group pagination">
  	<c:if test="${pageMaker.prev }">
   		<li>
     		 <a href='<c:url value="/member_info?page=${pageMaker.startPage-1}&searchWord=${searchWord}&searchEmailType=${searchEmailType}&searchSMSType=${searchSMSType}&onePageDataCountCondition=${pageCondition}&generalUser=${generalUser}&dormancyUser=${dormancyUser}&secessionUser=${secessionUser}" />'><i class="fa fa-chevron-left">이전</i></a>
  		</li>`
 	</c:if>
  	<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
    	<li value="${pageNum}"> 
       		<a href='<c:url value="/member_info?page=${pageNum}&searchWord=${searchWord}&searchEmailType=${searchEmailType}&searchSMSType=${searchSMSType}&onePageDataCountCondition=${pageCondition}&generalUser=${generalUser}&dormancyUser=${dormancyUser}&secessionUser=${secessionUser}"/>'><i class="fa">${pageNum}</i></a>
    	</li>
    </c:forEach>
    <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
    	<li>
      		<a href='<c:url value="/member_info?page=${pageMaker.endPage+1}&searchWord=${searchWord}&searchEmailType=${searchEmailType}&searchSMSType=${searchSMSType}&onePageDataCountCondition=${pageCondition}&generalUser=${generalUser}&dormancyUser=${dormancyUser}&secessionUser=${secessionUser}"/>'><i class="fa fa-chevron-right">다음</i></a>
   		</li>
    </c:if>
	</ul>
	
	<c:set var="OutputPageTotal" value="${(pageMaker.totalCount/userDto.perPageNum)+(1-((pageMaker.totalCount/userDto.perPageNum)%1))%1}" />
	<fmt:parseNumber var="OutputPage"  value="${OutputPageTotal}" integerOnly="true" type="number"/>
    <p class="pageCnt">전체 ${pageMaker.totalCount}건, ${userDto.page} / <c:out value="${OutputPage}"/> 페이지</p>
	 <div class="pageWrap">
      <!-- 페이징 -->
    </div>
  </div>
</div>

	
</body>
</html>
