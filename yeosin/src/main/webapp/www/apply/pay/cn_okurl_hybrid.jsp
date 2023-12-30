<%
/****************************************************************************************
* ���ϸ� : cn_okurl_hybrid.jsp
* �ۼ��� : PG������
* �ۼ��� : 2022.12
* ��  �� : �ſ�ī�� hybrid ��� ���� ó�� ���� ������
* ��  �� : 0004
* ---------------------------------------------------------------------------------------
* ������� �ſ�ī�� ����â���� ���� �ܰ� �Ϸ� �� ȣ��Ǵ� �������� �������̸� ���� ���� ��û �� ó�� ���� ������
* 1) Mode = "CN46" (���� ����)
* 2) CommonUtil.Decode(String str) �޼ҵ�� �ѱ��� �������
*    CommonUtil.Decode_1(String str)�� ���
****************************************************************************************/
%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="com.mobilians.cnnew_v0004.*" %>
<%@ page import="java.util.Date" %>
<%

	request.setCharacterEncoding("utf-8"); 

 	String mode	= "CN46";
	String recordKey	= "http://127.0.0.1/";	
	String svcId	= CommonUtil.Decode(request.getParameter("Svcid"));
	String tradeId	= CommonUtil.Decode(request.getParameter("Tradeid"));
	String prdtPrice	= CommonUtil.Decode(request.getParameter("Prdtprice"));
	String mobilId	= CommonUtil.Decode(request.getParameter("Mobilid")); 
	
	String userId			= request.getParameter("userId");
	String examId			= request.getParameter("examId");
	String certId			= request.getParameter("certId");
	String examZoneId 		= request.getParameter("examZoneId");
	String subjectId 		= request.getParameter("subjectId");
	
	System.out.println("mode : " + mode);
	System.out.println("recordKey : " + recordKey);
	System.out.println("svcId : " + svcId);
	System.out.println("tradeId : " + tradeId);
	System.out.println("prdtPrice : " + prdtPrice);
	System.out.println("mobilId : " + mobilId);
	
	System.out.println("userId : " + userId);
	System.out.println("examId : " + examId);
	System.out.println("certId : " + certId);
	System.out.println("examZoneId : " + examZoneId);
	System.out.println("subjectId : " + subjectId);

%>

<!-- �������� �� ó�� -->
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="utf-8">
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(document).ready(function(){
	document.kgPayForm.submit();
});
</script>
</head>
<body>
<form action="/apply6_pg" method="POST" name="kgPayForm">
		<input type="hidden" id="userId" name="userId" value="<%=request.getParameter("userId")%>"/>
		<input type="hidden" id="examId" name="examId" value="<%=request.getParameter("examId")%>"/>
		<input type="hidden" id="certId" name="certId" value="<%=request.getParameter("certId")%>"/>
		<input type="hidden" id="examZoneId" name="examZoneId" value="<%=request.getParameter("examZoneId")%>"/>
		<input type="hidden" id="subjectId" name="subjectId" value="<%=request.getParameter("subjectId")%>"/>
		
		<input type="hidden" id="mode" name="mode" value="CN46"/>
		<input type="hidden" id="recordKey" name="recordKey" value="http://127.0.0.1/"/>
		<input type="hidden" id="svcId" name="svcId" value="<%=request.getParameter("Svcid")%>"/>
		<input type="hidden" id="tradeId" name="tradeId" value="<%=request.getParameter("Tradeid")%>"/>
		<input type="hidden" id="prdtPrice" name="prdtPrice" value="<%=request.getParameter("Prdtprice")%>"/>
		<input type="hidden" id="mobilId" name="mobilId" value="<%=request.getParameter("Mobilid")%>"/>
</form>
</body>
</html>