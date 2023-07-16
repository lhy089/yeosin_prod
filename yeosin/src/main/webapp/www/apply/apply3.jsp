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
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/apply/apply.js?t=<%= new java.util.Date() %>"></script>
</head>
<script>
if(${isAlert}) { 
	if(${resultCd == 1}){
		alert("등록되어있는 교육증 수료번호가 아닙니다.\n교육과정 및 수료증번호를 다시 확인해 주시기 바랍니다.");
	}else if(${resultCd == 2}){
		alert("해당 교육 수료번호는 현재날짜 기준으로 1년이상이 지난 교육 수료번호입니다.\n교육과정 및 수료증번호를 다시 확인해 주시기 바랍니다.");
	}else if(${resultCd == 3}) {
		alert("오류가 발생했습니다.\n다음단계로 진행할 수 없습니다.");
	}
    
}
</script>
<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="apply third">
  <div class="contentBox">
    <h1>원서접수</h1>
    <div class="schedule">
      	대출성 상품 판매대리 &middot; 중개업자 등록 자격인증 평가<br/>
      <span><strong>평가일</strong>${examInfo.examDate}</span><span><strong>인터넷접수</strong>${examInfo.receiptStartDate} ~ ${examInfo.receiptEndDate}</span>
    </div>
    <section>
      <h2>개인정보</h2>
      <table>
        <colgroup>
          <col width="20.5%">
          <col width="*">
        </colgroup>
        <tr>
          <th>성명</th>
          <td>${userInfo.userName}</td>
        </tr>
        <tr>
          <th>생년월일</th>
          <td>${userInfo.birthDate}</td>
        </tr>
        <tr>
          <th>휴대전화</th>
          <td>${userInfo.phoneNumber}</td>
        </tr>
        <tr>
          <th>이메일</th>
          <td>${userInfo.emailAddress}</td>
        </tr>
      </table>
      <a href="/change" class="btn_change">개인정보 변경</a>

      <h2>교육수료정보</h2>
      <form action="/apply4" method="post">
	      <table>
	        <colgroup>
	          <col width="20.5%">
	          <col width="*">
	        </colgroup>
	        <tr>
	          <th>교육과정</th>
	          <td>
				<select id="subjectType" name="subjectType">
					<option value="*">선택</option>
					<c:forEach var="subjectList" items="${subjectInfo}" varStatus="status">
						<option value="${subjectList.subjectId}">${subjectList.subjectName}</option>
					</c:forEach>
	          	</select>
	          </td>
	        </tr>
	        <tr>
	          <th>교육수료증번호</th>
	          <td>
	            <input type="text" value="" id="eduNum" name="eduNum" >
	            <span>※ 필수입력항목</span>
	          </td>
	        </tr>
	      </table>
	      <div class="guide">
        <p>
          <strong class="blue">교육 수료증 번호 입력 시, 각 교육 운영기관에서 발급받은 번호 부분을 그대로 입력하여야 합니다.</strong><br/>
          1) 제 ____ 호 → '제', '호' 를 제외하고 ____에 해당하는 번호를 공백 없이 입력<br/>
          2) 영문, 숫자, 바( _ , -) 등이 포함된 경우, 누락 없이 기입 (영문은 대소문자 구분)<br/>
          3) 입력 예시(부호 입력 및 대소문자 구분 필요)<br/>
		 - 여신금융협회 수료증 : HU-2022-12345678<br/>
		 - 한국금융연수원 수료증 : 1234567891234<br/>
		 - 보험연수원 수료증 : C012345678912-01234<br/>  
        </p>
      </div>
      	<input type="hidden" value="${examInfo.examId}" id="examId" name="examId"/>
      	<input type="hidden" value="${examInfo.examDate}" id="examDate" name="examDate"/>
	      <input type="hidden" value="${local}" id="local" name="local"/>
	      <input type="hidden" value="${userInfo.userName}" id="userName" name="userName"/>
	      <input type="hidden" value="${userInfo.gender}" id="gender" name="gender"/>
	      <input type="hidden" value="${userInfo.birthDate}" id="birthDate" name="birthDate"/>
	      <input style="border:none;" class="btn_apply" type="submit" value="작성완료"/>
      </form>
    </section>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>
