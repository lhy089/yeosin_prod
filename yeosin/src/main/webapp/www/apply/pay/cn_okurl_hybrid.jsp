<%
/****************************************************************************************
* 파일명 : cn_okurl_hybrid.jsp
* 작성자 : PG웹개발
* 작성일 : 2022.12
* 용  도 : 신용카드 hybrid 방식 결제 처리 연동 페이지
* 버  전 : 0004
* ---------------------------------------------------------------------------------------
* 모빌리언스 신용카드 결제창에서 인증 단계 완료 후 호출되는 가맹점측 페이지이며 최종 결제 요청 및 처리 예제 페이지
* 1) Mode = "CN46" (결제 승인)
* 2) CommonUtil.Decode(String str) 메소드로 한글일 깨질경우
*    CommonUtil.Decode_1(String str)을 사용
****************************************************************************************/
%>

<!-- 정상적일 때 처리 -->
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="utf-8">
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>

window.onload = function() { debugger;
var childData = {
		payMode : "CN",
		mobilId : "<%=request.getParameter("Mobilid")%>"
	}
window.opener.postMessage(childData, '*');
this.close();

};

</script>
</head>
<body>
</body>
</html>