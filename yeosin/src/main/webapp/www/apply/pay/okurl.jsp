<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="EUC-KR"%>
<%@ page import="com.mobilians.cnnew_v0004.*" %>
<%@ page import="java.util.Date" %>
<%
System.out.println(request.getParameter("Resultmsg"));
System.out.println(CommonUtil.Decode(request.getParameter("Resultmsg")));

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta charset="utf-8" http-equiv="Content-Type" content="text/html;">
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>

window.onload = function() { debugger;
var childData = {
		payMode : "RA",
		Resultcd : "<%=request.getParameter("Resultcd")%>",
		Resultmsg : "<%=request.getParameter("Resultmsg")%>",
		CASH_GB : "<%=request.getParameter("CASH_GB")%>",
		Mobilid : "<%=request.getParameter("Mobilid")%>",
		Mrchid : "<%=request.getParameter("Mrchid")%>",
		MSTR : "<%=request.getParameter("MSTR")%>",
		Payeremail : "<%=request.getParameter("Payeremail")%>",
		Signdate : "<%=request.getParameter("Signdate")%>"
	}
window.opener.postMessage(childData, '*');
this.close();

};

</script>
</head>

<body>
</body>
</html>
