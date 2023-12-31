<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%

request.setCharacterEncoding("EUC-KR");
/****************************************************************************************
* 파일명 : notiurl.jsp
* 작성자 : 서비스개발팀
* 작성일 : 2013.12
* 용  도 : 실시간 계좌이체 notiurl 페이지
* 버  전 : 0004
* ---------------------------------------------------------------------------------------
* 가맹점에서 구현해야 하는 notiurl 페이지 이며
* 모빌리언스에서 결제성공시 결과전송을 위해 호출하는 페이지
* SUCCESS 또는 FAIL 만 출력
*
* 결제결과를 받아 결과저장 성공시 SUCCESS
* 결과저장 실패시 FAIL 를 출력
*
* 주) 결제 결과에 따라 위의 두가지 값중 하나를 출력해야 합니다.
*     FAIL 출력시 모빌리언스에서 결제 결과를 재호출 합니다.
*
*     okurl 로도 결과를 전송하므로 notiurl에서 결과저장시 okurl 에서 중복 처리 주의
*
*     notiurl 에 해당하는 파라메터가 존재하는 경우 notiurl 호출 후 okurl 호출
*
*     okurl 은 웹페이지 전환이므로 사용자 브라우져에 상황에 따라 결과전송 실패 가능성이 존재
*     notiurl 호출은 브라우져와 상관없이 페이지 호출하는 방식으로 실패시 다시 호출하는 방식으로
*     결제결과전송 단절을 최소화
****************************************************************************************/

String Resultcd		= request.getParameter("Resultcd");		//[   4byte 고정] 결과코드
String Resultmsg	= request.getParameter("Resultmsg");	//[ 100byte 이하] 결과메세지

String Mobilid		= request.getParameter("Mobilid");		//[  15byte 이하] 모빌리언스 거래번호
String Mrchid		= request.getParameter("Mrchid");		//[   8byte 고정] 상점ID
String MSTR			= request.getParameter("MSTR");			//[2000byte 이하] 가맹점 전달 콜백변수
String Prdtnm		= request.getParameter("Prdtnm");		//[  50byte 이하] 상품명
String Prdtprice	= request.getParameter("Prdtprice");	//[  10byte 이하] 상품가격
String Signdate		= request.getParameter("Signdate");		//[  14byte 이하] 결제일자
String Svcid		= request.getParameter("Svcid");		//[  12byte 고정] 서비스ID
String Tradeid		= request.getParameter("Tradeid");		//[  40byte 이하] 상점거래번호
String Userid		= request.getParameter("Userid");		//[  20byte 이하] 사용자ID
String Deposit		= request.getParameter("Deposit");		//[  10byte 이하] 1회용컵보증금


try {
	/*********************************************************
	* 결제가 정상적으로 완료되었을 경우(Resultcd=0000) 진행
	*********************************************************/
	if("0000".equals(Resultcd)) {
		/*******************************************
		* 여기에서 가맹점 측 결제 처리를 진행한다.
		*******************************************/
		
		//가맹점 측 결제 처리 성공 시
		if("결제 처리" == "성공") {
			out.println("SUCCESS");
			
		//가맹점 측 결제 처리 실패 시
		} else {
			out.println("FAIL");
		}
	}
} catch ( Exception e ){
	
	//가맹점 측 결제 처리 실패 시
	out.println("FAIL");
}
%>
