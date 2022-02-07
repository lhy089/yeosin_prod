package com.yeosin.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Kisinfo.Check.IPIN2Client;

public class CheckPlus {
   public static String getEncData(HttpSession session, String authType) {
	   NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
	    
	    String sSiteCode = "BW918";			// NICE로부터 부여받은 사이트 코드
	    String sSitePassword = "Sgkdtz74Hbjv";		// NICE로부터 부여받은 사이트 패스워드
	    
	    String sRequestNumber = "REQ0000000001";        	// 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로 
	                                                    	// 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.
	    sRequestNumber = niceCheck.getRequestNO(sSiteCode);
	  	session.setAttribute("REQ_SEQ" , sRequestNumber);	// 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
	  	
	   	String sAuthType = authType;      	// 없으면 기본 선택화면, M(휴대폰), X(인증서공통), U(공동인증서), F(금융인증서), S(PASS인증서), C(신용카드)
		String customize 	= "";		//없으면 기본 웹페이지 / Mobile : 모바일페이지
		
	    // CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
		//리턴url은 인증 전 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url : http://www.~
	    String sReturnUrl = "http://119.205.221.175/www/member/checkplus_success.jsp";      // 성공시 이동될 URL
//	    String sReturnUrl = "http://127.0.0.1/successCheckPlus";      // 성공시 이동될 URL
	    String sErrorUrl = "http://119.205.221.175/www/member/checkplus_fail.jsp";          // 실패시 이동될 URL

	    // 입력될 plain 데이타를 만든다.
	    String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber +
	                        "8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode +
	                        "9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType +
	                        "7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl +
	                        "7:ERR_URL" + sErrorUrl.getBytes().length + ":" + sErrorUrl +
	                        "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize;
	    
	    String sMessage = "";
	    String sEncData = "";
	    
	    int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
	    if( iReturn == 0 )
	    {
	        sEncData = niceCheck.getCipherData();
	        return sEncData;
	    }
	    else if( iReturn == -1)
	    {
	        sMessage = "암호화 시스템 에러입니다.";
	    }    
	    else if( iReturn == -2)
	    {
	        sMessage = "암호화 처리오류입니다.";
	    }    
	    else if( iReturn == -3)
	    {
	        sMessage = "암호화 데이터 오류입니다.";
	    }    
	    else if( iReturn == -9)
	    {
	        sMessage = "입력 데이터 오류입니다.";
	    }    
	    else
	    {
	        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
	    }
	    return sMessage;
    }
   
   public static String successCheckPlus(HttpServletRequest request, HttpSession session) {
	   NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

	   String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");

	    String sSiteCode = "BW918";				// NICE로부터 부여받은 사이트 코드
	    String sSitePassword = "Sgkdtz74Hbjv";			// NICE로부터 부여받은 사이트 패스워드

	    String sCipherTime = "";			// 복호화한 시간
	    String sRequestNumber = "";			// 요청 번호
	    String sResponseNumber = "";		// 인증 고유번호
	    String sAuthType = "";				// 인증 수단
	    String sName = "";					// 성명
	    String sDupInfo = "";				// 중복가입 확인값 (DI_64 byte)
	    String sConnInfo = "";				// 연계정보 확인값 (CI_88 byte)
	    String sBirthDate = "";				// 생년월일(YYYYMMDD)
	    String sGender = "";				// 성별
	    String sNationalInfo = "";			// 내/외국인정보 (개발가이드 참조)
		String sMobileNo = "";				// 휴대폰번호
		String sMobileCo = "";				// 통신사
	    String sMessage = "";
	    String sPlainData = "";
	    
	    int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

	    if( iReturn == 0 )
	    {
	        sPlainData = niceCheck.getPlainData();
	        sCipherTime = niceCheck.getCipherDateTime();
	        
	        // 데이타를 추출합니다.
	        java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
	        
	        sRequestNumber  = (String)mapresult.get("REQ_SEQ");
	        sResponseNumber = (String)mapresult.get("RES_SEQ");
	        sAuthType		= (String)mapresult.get("AUTH_TYPE");
	        sName			= (String)mapresult.get("NAME");
			//sName			= (String)mapresult.get("UTF8_NAME"); //charset utf8 사용시 주석 해제 후 사용
	        sBirthDate		= (String)mapresult.get("BIRTHDATE");
	        sGender			= (String)mapresult.get("GENDER");
	        sNationalInfo  	= (String)mapresult.get("NATIONALINFO");
	        sDupInfo		= (String)mapresult.get("DI");
	        sConnInfo		= (String)mapresult.get("CI");
	        sMobileNo		= (String)mapresult.get("MOBILE_NO");
	        sMobileCo		= (String)mapresult.get("MOBILE_CO");
	        
//	        String session_sRequestNumber = (String)session.getAttribute("REQ_SEQ");
//	        if(!sRequestNumber.equals(session_sRequestNumber))
//	        {
//	            sMessage = "세션값 불일치 오류입니다.";
//	            sResponseNumber = "";
//	            sAuthType = "";
//	        }
	    }
	    else if( iReturn == -1)
	    {
	        sMessage = "복호화 시스템 오류입니다.";
	    }    
	    else if( iReturn == -4)
	    {
	        sMessage = "복호화 처리 오류입니다.";
	    }    
	    else if( iReturn == -5)
	    {
	        sMessage = "복호화 해쉬 오류입니다.";
	    }    
	    else if( iReturn == -6)
	    {
	        sMessage = "복호화 데이터 오류입니다.";
	    }    
	    else if( iReturn == -9)
	    {
	        sMessage = "입력 데이터 오류입니다.";
	    }    
	    else if( iReturn == -12)
	    {
	        sMessage = "사이트 패스워드 오류입니다.";
	    }    
	    else
	    {
	        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
	    }
	    
	    return sBirthDate;
    }
   
   public static String requestReplace (String paramValue, String gubun) {

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

   public static String getEncDataForIpin(HttpSession session) {
	   /********************************************************************************************************************************************
		NICE평가정보 Copyright(c) KOREA INFOMATION SERVICE INC. ALL RIGHTS RESERVED

		서비스명 : IPIN 가상주민번호 서비스 
		페이지명 : IPIN 가상주민번호 서비스 호출 페이지
	    *********************************************************************************************************************************************/

	   String sSiteCode	= "GE74";		//  NICE평가정보에서 발급한 IPIN 서비스 사이트코드
	   String sSitePw		= "lpcrefia0770**";		//  NICE평가정보에서 발급한 IPIN 서비스 사이트패스워드


	   /*
	┌ sReturnURL 변수에 대한 설명  ─────────────────────────────────────────────────────
		암호화된 인증결과 데이터를 리턴받을 URL을 프로토콜부터 풀주소로 정의해주세요.

	    * URL은 반드시 프로토콜부터 입력해야 하며 외부에서 접속이 가능한 주소여야 합니다.
	    * 당사 샘플페이지 중 ipin_process 페이지가 인증결과 데이터를 리턴받는 페이지입니다.

		예 - http://www.test.co.kr/ipin_process.jsp
			 https://www.test.kr:4343/ipin_process.jsp
	└────────────────────────────────────────────────────────────────────
	    */
	   	String sReturnURL   = "http://119.205.221.175/www/member/ipin_process.jsp";
//	   String sReturnURL   = "http://127.0.0.1/www/member/ipin_process.jsp";


	   /*
	┌ sCPRequest 변수에 대한 설명  ─────────────────────────────────────────────────────
		CP요청번호는 추가적인 보안처리를 위한 변수입니다. 인증 후 인증결과 데이터와 함께 전달됩니다.
		세션에 저장된 값과 비교해 데이터 위변조를 검사하거나, 사용자 특정용으로 이용할 수 있습니다.	
		위변조 검사는 인증에 필수적인 처리는 아니며 보안을 위한 권고 사항입니다.		

		+ CP요청번호 설정 방법
			1. 당사에서 배포된 모듈로 생성
			2. 귀사에서 임의로 정의(최대 30byte) 
	└────────────────────────────────────────────────────────────────────
	    */
	   String sCPRequest = "";	


	   // 모듈객체 생성
	   IPIN2Client pClient = new IPIN2Client();	

	   // CP요청번호 생성
	   sCPRequest = pClient.getRequestNO(sSiteCode);

	   // CP요청번호 세션에 저장 
	   // : 저장된 값은 ipin_result 페이지에서 데이터 위변조 검사에 이용됩니다.
	   session.setAttribute("CPREQUEST" , sCPRequest);


	   // 인증요청 암호화 데이터 생성
	   int iRtn = pClient.fnRequest(sSiteCode, sSitePw, sCPRequest, sReturnURL);

	   String sEncData		= "";	// 인증요청 암호화 데이터
	   String sRtnMsg		= "";	// 처리결과 메세지

	   // 암호화 처리결과코드에 따른 처리
	   if (iRtn == 0)
	   {
		   // 인증요청 암호화 데이터 추출
		   sEncData = pClient.getCipherData();		
		   sRtnMsg = "정상 처리되었습니다.";
	   }
	   else if (iRtn == -1)
	   {
		   sRtnMsg = "암호화 시스템 오류 : 귀사 서버 환경에 맞는 모듈을 이용해주십시오.<br>오류가 지속되는 경우 iRtn 값, 서버 환경정보, 사이트코드를 기재해 문의주시기 바랍니다.";
	   }
	   else if (iRtn == -2)
	   {
		   sRtnMsg = "암호화 처리 오류 : 최신 모듈을 이용해주십시오. 오류가 지속되는 경우 iRtn 값, 서버 환경정보, 사이트코드를 기재해 문의주시기 바랍니다.";
	   }
	   else if (iRtn == -9)
	   {
		   sRtnMsg = "입력 정보 오류 : 암호화 함수에 입력된 파라미터 값을 확인해주십시오.<br>오류가 지속되는 경우, 함수 실행 직전 각 파라미터 값을 로그로 출력해 발송해주시기 바랍니다.";
	   }
	   else
	   {
		   sRtnMsg = "기타 오류: iRtn 값과 적용한 샘플소스를 발송해주시기 바랍니다.";
	   }
	   return sEncData;
   }
}