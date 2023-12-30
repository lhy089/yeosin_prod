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

 	String mode	= "CN46";
	String recordKey	= "����ƮURL";	
	String svcId	= CommonUtil.Decode(request.getParameter("Svcid"));
	String tradeId	= CommonUtil.Decode(request.getParameter("Tradeid"));
	String prdtPrice	= CommonUtil.Decode(request.getParameter("Prdtprice"));
	String mobilId	= CommonUtil.Decode(request.getParameter("Mobilid")); 

	/****************************************************************************************
	*  ������𽺿�  ���� ���   *
	****************************************************************************************/
	McashManager mm = new McashManager();


	/****************************************************************************************
	����� ���� ȯ�溯�� ������ �Ʒ��� �Լ��� �̿��ϼ���
	****************************************************************************************/
	//mm.setConfigFileDir("./Mcash.properties" );


	/****************************************************************************************
	����� ������  �Ʒ��� �Լ��� �̿��ϼ���
	mm.setServerInfo(
 		String serverIp,	// ����������
		String switchIp,	// ����ġ ������
		int serverPort,		// ������Ʈ
		int recvTimeOut,	// Ÿ�Ӿƿ� ���� ( milliseconds ������ ����, 0�� ��� Ÿ�Ӿƿ� �̼��� ó�� )
		String logDir,		// �αװ��, �α׵��丮���
		String KeySeq,		// ������ KEY ���� ( 0 : default , 1 : �������Է�key1 , 2 : �������Է�key2 ), ��ȣȭ Ű �������� ������ ������ �������� �� ����
		String Key,			// KEY value ( 0 ���� ��� �� ���� )
		String UserEncode,	// ĳ���ͼ�, "" or null �ΰ�� EUC-KR
		String logLevel		// �α׷���, "" or null ����
	);
	****************************************************************************************/
	//mm.setServerInfo("218.38.71.164", "218.38.71.164", 9110, 30000, "c:\\test2\\", "0", "", "EUC-KR", "");

	AckParam ap = mm.McashApprv(
		mode,			/* �ŷ���� */
		recordKey,		/* ����ƮURL */
		svcId,			/* ���񽺾��̵� */
		mobilId,		/* ������𽺰ŷ���ȣ */
		tradeId,		/* �������ŷ���ȣ */
		prdtPrice,		/* ��ǰ�ݾ� */
		""				/* �ڵ�����Ű */
	);

%>

<!-- �������� �� ó�� -->
<!DOCTYPE html>
<html>
<body>
<br>
<h3>mode : <%=mode %> �϶� �����ʹ� ������ �����ϴ�. </h4>
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