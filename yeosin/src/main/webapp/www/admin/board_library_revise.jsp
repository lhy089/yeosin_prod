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
		  $("#title").val($("#title").val());
		  $("#contents").val($("#contents").val());	 
		  $("#boardId").val($("#boardId").val());
		  $("#page").val($("#page").val());
		  $("#boardType").val($("#boardType").val());
		  var inputFileName = $('#inputFileName').val();
		  $("#fileName").val(inputFileName);
		  //var inputFileSize = $('#file')[0].files[0].size;
		  //$("#fileSize").val(inputFileSize);
		  $("#commonform").submit(); 
	});
});
</script>

<body>

<!-- header 붙여주세요. (/common/admin_header.html) -->
<%@ include file="/www/common/admin_header.jsp"%>
<!-- lnb 붙여주세요. (/common/admin_lnb.html) -->
<%@ include file="/www/common/admin_lnb.jsp"%>
<form id="commonform" name="commonform" method="get" action="/boardLibraryRevise_action">
<input type="hidden" name="boardType" id="boardType" value="3">
<input type="hidden" name="page" id="page" value="${boardDto.page}" >
<input type="hidden" name="boardId" id="boardId" value="${boardDto.boardId}">
<input type="hidden" name="fileName" id="fileName">
<input type="hidden" name="fileSize" id="fileSize">

<div class="board">
  <div class="contentBoxAd">
    <h1 class="title">게시판</h1>
    <h2>시험자료실 <em>-수정하기</em></h2>
    <table>
      <colgroup>
        <col width="25%">
        <col width="75%">
      </colgroup>
      <tr>
        <th>제목</th>
        <td><input type="text" id="title" name="title" value="${library.title}" ></td>
      </tr>
      <tr>
        <th>내용</th>
        <td><textarea id="contents" name="contents" placeholder="" rows="8" cols="50">${library.contents}</textarea></td>
      </tr>
      <tr>
        <th>첨부파일</th>
        <td class="info">
          <input name="inputFileName" id="inputFileName" class="upload-name" value="${fileName}" disabled="disabled">
          <label for="file">파일선택</label>
          <input type="file" id="file" class="upload-hidden">
          <a href="#" class="btn_more reset">삭제</a>
        </td>
      </tr>
    </table>
    <div class="btn_apWrap mb100">
       <a onclick="return false;" id="btn_revise" class="btn_apply">수정</a>
       <a href="/boardLibrary?page=${boardDto.page}&boardType=3" class="btn_apply">뒤로가기</a>
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
  });
  $('.reset').click(function(){ //선택파일 초기화
    $('#file,.upload-name').val('');
  });
});
</script>
</body>
</html>
