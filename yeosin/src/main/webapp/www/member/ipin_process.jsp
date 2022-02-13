<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	/*********************************************************************************************
		NICE평가정보 Copyright(c) KOREA INFOMATION SERVICE INC. ALL RIGHTS RESERVED
		
		서비스명 : IPIN 가상주민번호 서비스 
		페이지명 : IPIN 가상주민번호 서비스 인증결과 처리 페이지
		
		            리턴받은 인증결과 데이터를 호출 페이지로 전달하고 인증팝업을 닫는 페이지
	**********************************************************************************************/
	
	// 인증결과 암호화 데이터 취득 (인증요청 암호화 데이터 값과 달라야 정상)
	String sResponseData = requestReplace(request.getParameter("enc_data"), "encodeData");
%>

<html>
<head>
	<meta charset="EUC-KR">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>NICE평가정보 가상주민번호 서비스</title>
	<script>

		function fnLoad()
		{ debugger;
		
		var childData = {
				module:"ipin",
				enc_data :  "<%= sResponseData %>", 
				target : "popupIPIN2", 
// 				action : "http://127.0.0.1/www/member/ipin_result.jsp"
				action : "https://www.lpcrefia.or.kr/www/member/ipin_result.jsp"
			}
	    window.opener.postMessage(childData, '*');
			// 당사에서는 최상위를 'parent.opener.parent.document.'로 정의하였습니다
			// 귀사의 환경 및 프로세스에 맞게 정의하시기 바랍니다
<%-- 			parent.opener.parent.document.vnoform.enc_data.value = "<%= sResponseData %>"; --%>
// 			parent.opener.parent.document.vnoform.target = "Parent_window";
			
// 			// 인증결과 데이터를 최종 수신하는 결과페이지 URL (형식:절대주소, 필수항목:프로토콜) 
// 			parent.opener.parent.document.vnoform.action = "http://127.0.0.1/www/member/ipin_result.jsp";
// 			parent.opener.parent.document.vnoform.submit();
			
// 			self.close();
		}
	</script>
</head>

<%    
	// 인증결과 암호화 데이터가 존재하는 경우
	if (!sResponseData.equals("") && sResponseData != null)
	{
%>
<body onLoad="fnLoad()">
<%
	// 인증결과 암호화 데이터가 존재하지 않는 경우
	} else {
%>
<body onLoad="self.close()">
<%
	}
%>

<%!
	// 문자열 점검 함수
	public String requestReplace (String paramValue, String gubun) {
        String result = "";
        
        if (paramValue != null) {
        	
        	paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        	paramValue = paramValue.replaceAll("\\*", "");
        	paramValue = paramValue.replaceAll("\\?", "");
        	paramValue = paramValue.replaceAll("\\[", "");
        	paramValue = paramValue.replaceAll("\\{", "");
        	paramValue = paramValue.replaceAll("\\(", "");
        	paramValue = paramValue.replaceAll("\\)", "");
        	paramValue = paramValue.replaceAll("\\^", "");
        	paramValue = paramValue.replaceAll("\\$", "");
        	paramValue = paramValue.replaceAll("'", "");
        	paramValue = paramValue.replaceAll("@", "");
        	paramValue = paramValue.replaceAll("%", "");
        	paramValue = paramValue.replaceAll(";", "");
        	paramValue = paramValue.replaceAll(":", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll("#", "");
        	paramValue = paramValue.replaceAll("--", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll(",", "");
        	
        	if(gubun != "encodeData"){
        		paramValue = paramValue.replaceAll("\\+", "");
        		paramValue = paramValue.replaceAll("/", "");
            paramValue = paramValue.replaceAll("=", "");
        	}        	
        	result = paramValue;
            
        }
        return result;
  }
%>
</body>
</html>