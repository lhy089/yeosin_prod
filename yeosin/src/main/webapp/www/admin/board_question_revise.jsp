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
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
if(${isAlert}) { 
    alert("로그인 후 이용 가능합니다.");
}

$(document).ready(function() {
	$("#btn_revise").click(function() {
		  $("#category").val($("#category").val());
		  $("#title").val($("#title").val());
		  $("#contents").val($("#contents").val());	 
		  $("#boardId").val($("#boardId").val());
		  $("#page").val($("#page").val());
		  $("#boardType").val($("#boardType").val());
		  $("#commonform").submit(); 
	});
});
</script>

<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>
<form id="commonform" name="commonform" method="get" action="/boardQuestionRevise_action">
<input type="hidden" name="boardType" id="boardType" value="2">
<input type="hidden" name="page" id="page" value="${boardDto.page}" >
<input type="hidden" name="boardId" id="boardId" value="${boardDto.boardId}">

<div class="board">
  <div class="contentBoxAd">
    <h1 class="title">게시판</h1>
    <h2>자주하는 질문 <em>-수정하기</em></h2>
    <table>
      <colgroup>
        <col width="25%">
        <col width="75%">
      </colgroup>
      <tr>
        <th>카테고리</th>
        <td>
          <select id="category" name="category">
            <option value="원서접수 및 취소" <c:if test="${question.category eq '원서접수 및 취소'}">selected="selected"</c:if>>원서접수 및 취소</option>
            <option value="시험안내" <c:if test="${question.category eq '시험안내'}">selected="selected"</c:if>>시험안내</option>
            <option value="결제 및 환불" <c:if test="${question.category eq '결제 및 환불'}">selected="selected"</c:if>>결제 및 환불</option>
            <option value="자격인증서 발급" <c:if test="${question.category eq '자격인증서 발급'}">selected="selected"</c:if>>자격인증서 발급</option>
            <option value="응시 관련" <c:if test="${question.category eq '응시 관련 '}">selected="selected"</c:if>>응시 관련</option>
            <option value="회원가입 및 이용" <c:if test="${question.category eq '회원가입 및 이용'}">selected="selected"</c:if>>회원가입 및 이용</option>
            <option value="시험 결과 관련" <c:if test="${question.category eq '시험 결과 관련 '}">selected="selected"</c:if>>시험 결과 관련</option>
            <option value="기타" <c:if test="${question.category eq '기타'}">selected="selected"</c:if>>기타</option>
          </select>
        </td>
      </tr>
      <tr>
        <th>질문</th>
        <td><input type="text" id="title" name="title" value="${question.title}"></td>
      </tr>
      <tr>
        <th>답변</th>
        <td><textarea id="contents" name="contents" placeholder="" rows="8" cols="50">${question.contents}</textarea></td>
      </tr>
    </table>
    <div class="btn_apWrap mb100">
      <a onclick="return false;" id="btn_revise" class="btn_apply">수정</a>
      <a href="/boardQuestion?page=${boardDto.page}&boardType=2" class="btn_apply">뒤로가기</a>
    </div>
  </div>
</div>
</form>

</body>
</html>
