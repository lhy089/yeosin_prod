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

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="com.mobilians.cnnew_v0004.*" %>
<%@ page import="java.util.Date" %>
<%

 	String mode	= "CN46";
	String recordKey	= "사이트URL";	
	String svcId	= CommonUtil.Decode(request.getParameter("Svcid"));
	String tradeId	= CommonUtil.Decode(request.getParameter("Tradeid"));
	String prdtPrice	= CommonUtil.Decode(request.getParameter("Prdtprice"));
	String mobilId	= CommonUtil.Decode(request.getParameter("Mobilid")); 

	/****************************************************************************************
	*  모빌리언스와  결제 통신   *
	****************************************************************************************/
	McashManager mm = new McashManager();


	/****************************************************************************************
	사용자 지정 환경변수 설정시 아래의 함수를 이용하세요
	****************************************************************************************/
	//mm.setConfigFileDir("./Mcash.properties" );


	/****************************************************************************************
	사용자 설정시  아래의 함수를 이용하세요
	mm.setServerInfo(
 		String serverIp,	// 서버아이피
		String switchIp,	// 스위치 아이피
		int serverPort,		// 서버포트
		int recvTimeOut,	// 타임아웃 설정 ( milliseconds 단위로 셋팅, 0일 경우 타임아웃 미설정 처리 )
		String logDir,		// 로그경로, 로그디렉토리경로
		String KeySeq,		// 가맹점 KEY 순번 ( 0 : default , 1 : 가맹점입력key1 , 2 : 가맹점입력key2 ), 암호화 키 순번으로 가맹점 관리자 페이지상에 서 세팅
		String Key,			// KEY value ( 0 번의 경우 미 세팅 )
		String UserEncode,	// 캐릭터셋, "" or null 인경우 EUC-KR
		String logLevel		// 로그레벨, "" or null 가능
	);
	****************************************************************************************/
	//mm.setServerInfo("218.38.71.164", "218.38.71.164", 9110, 30000, "c:\\test2\\", "0", "", "EUC-KR", "");

	AckParam ap = mm.McashApprv(
		mode,			/* 거래모드 */
		recordKey,		/* 사이트URL */
		svcId,			/* 서비스아이디 */
		mobilId,		/* 모빌리언스거래번호 */
		tradeId,		/* 가맹점거래번호 */
		prdtPrice,		/* 상품금액 */
		""				/* 자동결제키 */
	);

%>

<!-- 정상적일 때 처리 -->
<!DOCTYPE html>
<html>
<body>
<br>
<h3>mode : <%=mode %> 일때 데이터는 다음과 같습니다. </h4>
<font color=blue> <h4> mode :		<%= ap.getMode() %>			</h4>	</font>
<font color=blue> <h4> resultCd :	<%= ap.getResultCd() %>		</h4>	</font>
<font color=blue> <h4> resultMsg :	<%= ap.getResultMsg() %>	</h4>	</font>
<font color=blue> <h4> tradeId :	<%= ap.getTradeId() %>		</h4>	</font>
<font color=blue> <h4> mobilId :	<%= ap.getMobilId() %>		</h4>	</font>
<font color=blue> <h4> prdtPrice :	<%= ap.getPrdtPrice() %>	</h4>	</font>
<font color=blue> <h4> signDate :	<%= ap.getSignDate() %>		</h4>	</font>
<font color=blue> <h4> interest :	<%= ap.getInterest() %>		</h4>	</font>
<font color=blue> <h4> cardCode :	<%= ap.getCardCode() %>		</h4>	</font>
<font color=blue> <h4> cardName :	<%= ap.getCardName() %>		</h4>	</font>
<font color=blue> <h4> cardNum :	<%= ap.getCardNum() %>		</h4>	</font>
<font color=blue> <h4> apprNo :		<%= ap.getApprNo() %>		</h4>	</font>
<font color=blue> <h4> couponPrice :<%= ap.getCouponPrice() %>	</h4>	</font>
<font color=blue> <h4> payMethod :	<%= ap.getPayMethod() %>	</h4>	</font>
<font color=blue> <h4> deposit :	<%= ap.getDeposit() %>		</h4>	</font>
<br>
</body>
</html>