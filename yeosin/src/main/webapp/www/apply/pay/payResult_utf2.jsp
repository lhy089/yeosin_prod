
<%
request.setCharacterEncoding("utf-8"); 

String authResultCode 	= (String)request.getParameter("AuthResultCode"); 	// 인증결과 : 0000(성공)
String authResultMsg 	= (String)request.getParameter("AuthResultMsg"); 	// 인증결과 메시지
String nextAppURL 		= (String)request.getParameter("NextAppURL"); 		// 승인 요청 URL
String txTid 			= (String)request.getParameter("TxTid"); 			// 거래 ID
String authToken 		= (String)request.getParameter("AuthToken"); 		// 인증 TOKEN
String payMethod 		= (String)request.getParameter("PayMethod"); 		// 결제수단
String mid 				= (String)request.getParameter("MID"); 				// 상점 아이디
String moid 			= (String)request.getParameter("Moid"); 			// 상점 주문번호
String amt 				= (String)request.getParameter("Amt"); 				// 결제 금액
String reqReserved 		= (String)request.getParameter("ReqReserved"); 		// 상점 예약필드
String netCancelURL 	= (String)request.getParameter("NetCancelURL"); 	// 망취소 요청 UR
String userId			= request.getParameter("userId");
String examId			= request.getParameter("examId");
String certId			= request.getParameter("certId");
String examZoneId 		= request.getParameter("examZoneId");
String subjectId 		= request.getParameter("subjectId");

System.out.println(">>> moRecipt parameter return <<< ");
System.out.println(">>> moRecipt authResultCode : " + authResultCode);
System.out.println(">>> moRecipt authResultMsg : " + authResultMsg);
System.out.println(">>> moRecipt nextAppURL : " + nextAppURL);
System.out.println(">>> moRecipt txTid : " + txTid);
System.out.println(">>> moRecipt authToken : " + authToken);
System.out.println(">>> moRecipt payMethod : " + payMethod);
System.out.println(">>> moRecipt mid : " + mid);
System.out.println(">>> moRecipt moid : " + moid);
System.out.println(">>> moRecipt amt : " + amt);
System.out.println(">>> moRecipt reqReserved : " + reqReserved);
System.out.println(">>> moRecipt netCancelURL : " + netCancelURL);
System.out.println(">>> moRecipt userId : " + userId);
System.out.println(">>> moRecipt examId : " + examId);
System.out.println(">>> moRecipt certId : " + certId);
System.out.println(">>> moRecipt examZoneId : " + examZoneId);
System.out.println(">>> moRecipt subjectId : " + subjectId);
%>
<!DOCTYPE html>
<html>
<head>
<title>NICEPAY PAY RESULT(UTF-8)</title>
<m eta charset="utf-8">
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(document).ready(function(){
	document.moPayForm.submit();
});
</script>
</head>
<body>
	
	<form action="/moReceipt2" method="POST" name="moPayForm">
		<input type="hidden" id="mode" name="mode" value="mo"/>
		<input type="hidden" id="userId" name="userId" value="<%=request.getParameter("userId")%>"/>
		<input type="hidden" id="examId" name="examId" value="<%=request.getParameter("examId")%>"/>
		<input type="hidden" id="certId" name="certId" value="<%=request.getParameter("certId")%>"/>
		<input type="hidden" id="examZoneId" name="examZoneId" value="<%=request.getParameter("examZoneId")%>"/>
		<input type="hidden" id="subjectId" name="subjectId" value="<%=request.getParameter("subjectId")%>"/>
		<input type="hidden" id="AuthResultCode" name="AuthResultCode" value="<%=request.getParameter("AuthResultCode")%>"/>
		<input type="hidden" id="AuthResultMsg" name="AuthResultMsg" value="<%=request.getParameter("AuthResultMsg")%>"/>
		<input type="hidden" id="NextAppURL" name="NextAppURL" value="<%=request.getParameter("NextAppURL")%>"/>
		<input type="hidden" id="TxTid" name="TxTid" value="<%=request.getParameter("TxTid")%>"/>
		<input type="hidden" id="AuthToken" name="AuthToken" value="<%=request.getParameter("AuthToken")%>"/>
		<input type="hidden" id="PayMethod" name="PayMethod" value="<%=request.getParameter("PayMethod")%>"/>
		<input type="hidden" id="MID" name="MID" value="<%=request.getParameter("MID")%>"/>
		<input type="hidden" id="Moid" name="Moid" value="<%=request.getParameter("Moid")%>"/>
		<input type="hidden" id="Amt" name="Amt" value="<%=request.getParameter("Amt")%>"/>
		<input type="hidden" id="ReqReserved" name="ReqReserved" value="<%=request.getParameter("ReqReserved")%>"/>
		<input type="hidden" id="NetCancelURL" name="NetCancelURL" value="<%=request.getParameter("NetCancelURL")%>"/>
	</form>
	
</body>
</html>
