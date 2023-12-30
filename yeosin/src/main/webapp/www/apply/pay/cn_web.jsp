<%
/*****************************************************************************************
* ���ϸ� : cn_web.jsp
* �ۼ��� : PG������
* �ۼ��� : 2022.12
* ��  �� : �ſ�ī��(CN) Webnoti ��� ���� ���� ������
* ��	�� : 0004
* ---------------------------------------------------------------------------------------
* �ҽ� ���Ǻ��濡 ���� �ս��� ������𽺿��� å������ �ʽ��ϴ�.
* ��û �Ķ���� �� ������� ���� ������ �ݵ�� ���� �Ŵ����� �����Ͻʽÿ�.
* �ſ�ī��(CN)�� �����׽�Ʈ ȯ���� �������� �ʽ��ϴ�.
* �׽�Ʈ �������� ������ ������ �Ǵ� ��� ����� �̿��Ͽ� ���� ��� ó���� �ؾ� �մϴ�.
*
* ��ȣȭ ��� �� �ʼ� Ŭ����
* mup.mcash.module.common.McashCipher.class
* mup.mcash.module.common.McashSeed.class
*****************************************************************************************/
%>

<%@page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="mup.mcash.module.common.McashCipher.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%

	// Unique�� �ŷ���ȣ�� ���� �� ���� (�и���������� ��ȸ)
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
	String appr_dtm = dateFormat.format(new Date());


	/*****************************************************************************************/
	String CASH_GB = "CN"; 	// ��ǥ��������. ����


	/*****************************************************************************************
	- �ʼ� �Է� �׸�
	*****************************************************************************************/
	String VER = "ALL_NEW";						//ALL_NEW : �������� ����
	String CN_TAX_VER = "CPLX";					//CPLX : ���հ�����ҹ������� ����
	String CN_SVCID = "";						//���񽺾��̵�
	String PAY_MODE = "10";						//10 : �ǰŷ����� ����
	String Prdtprice = "";						//������û�ݾ�.
	String Prdtnm = "";							//��ǰ�� ( 50byte �̳� )
	String Siteurl = "";						//������������
	String Okurl = "";							//����ȭ��ó��URL : �����Ϸ��뺸������ full Url (��:http://www.mcash.co.kr/okurl.jsp )
	String Tradeid = CN_SVCID + "_" + appr_dtm;	//�������ŷ���ȣ //���� ��û �� ���� unique�� ���� �����ؾ� ��.



	/*****************************************************************************************
	- ���� �Է� �׸�
	*****************************************************************************************/
	String Notiurl = "";					//����ó��URL : ���� �Ϸ� ��, �������� ���� �� ó���� �������� URL
	String CALL_TYPE = "";					//����â ȣ����
	String Failurl = "";					//���� ���� �� ����ڿ��� ������ ������ �� ���� ������
	String Closeurl = "";					//�ݱ��ư Ŭ�� �� ȣ��Ǵ� ������ �� ������. CALL_TYPE = ��I�� �Ǵ� ��SELF�� ���� �� �ʼ�
	String MSTR = "";						//�������ݹ麯�� //���������� �߰������� �Ķ���Ͱ� �ʿ��� ��� ����ϸ� &,%,?,^ �� ���Ұ� ( �� : MSTR="a=1|b=2|c=3" )
	String Payeremail = "";					//������email
	String Userid = "";						//������������ID
	String CN_BILLTYPE = "";				//������ǥ ��� �� ����/����� ����
	String CN_TAXFREE = "";					//�����
	String CN_TAX = "";						//�ΰ��� - ��ü�ݾ��� 10%���Ϸ� ����
	String CN_TAXAMT = "";					//����
	String CN_FREEINTEREST = "";			//�������Һ�����
	String CN_POINT = "";					//ī�������Ʈ��뿩��
	String Termregno = "";					//��������������ڹ�ȣ
	String APP_SCHEME = "";					//APP SCHEME
	String Username = "";					//�����ڸ�
	String CN_INSTALL = "";					//�Һΰ���
	String CN_FIXCARDCD = "";				//ī��� ���ó��� '����â�� ������ ī��� �ڵ� ����
	String CN_DIRECT = "";					//ī��� ����ȣ�� ( �� : KBC:00:N )
	String Deposit = "";					//��ȸ���ź�����
	String CN_PAY_APP_USE_YN = "";			//�츮ī��,�츮����(WONī��,WON��ŷ) ������ ����
	String CN_PAY_APP_USE_CD = "";			//�츮ī��,�츮����(WONī��,WON��ŷ) �� 1�� �ܵ� ���� ����(CN_PAY_APP_USE_YN = Y �� ���� ���� ����)



	/*****************************************************************************************
	- ��ȣȭ ó�� (��ȣȭ ��� ��)
	Cryptstring �׸��� �ݾ׺����� ���� Ȯ�ο����� �ݵ�� �Ʒ��� ���� ���ڿ��� �����Ͽ��� �մϴ�.

	��) ��ȣȭ ��Ʈ���� ���������� �����ϴ� �ŷ���ȣ�� ���� ����Ǿ� ���ǹǷ�
	��ȣȭ�� �̿��� �ŷ���ȣ��  �����Ǿ� ���޵� ��� ��ȣȭ ���з� ���� ������ �Ұ��մϴ�.
	*****************************************************************************************/
	String Cryptyn		= "N";	//Y: ��ȣȭ ���, N: ��ȣȭ �̻��
	String Cryptstring	= "";	//��ȣȭ ��� �� ��ȣȭ�� ��Ʈ��

	if ("Y".equals(Cryptyn)) {
		Cryptstring	= Prdtprice + Okurl;	//�ݾ׺���Ȯ�� (������û�ݾ� + Okurl)
		Okurl		= McashCipher.encodeString(Okurl, Tradeid);
		Failurl		= McashCipher.encodeString(Failurl, Tradeid);
		Notiurl		= McashCipher.encodeString(Notiurl, Tradeid);
		Prdtprice	= McashCipher.encodeString(Prdtprice, Tradeid);
		Cryptstring	= McashCipher.encodeString(Cryptstring, Tradeid);
	}

%>

<!--  �������� ������û ������ -->
<!DOCTYPE html>
<html>
<head>
<style>
	body {font-size:14px;}
	table td {font-size:13px;}
	table {border-collapse: collapse; text-align: left;}
</style>
<script src="https://mup.mobilians.co.kr/js/ext/ext_inc_comm.js"></script>
<script language="javascript">
	function payRequest(){
		//�Ʒ��� ���� ext_inc_comm.js�� ����Ǿ� �ִ� �Լ��� ȣ��
		MCASH_PAYMENT(document.payForm);
	}
</script>
</head>

<body>
<form name="payForm" accept-charset="euc-kr">
 <table border="1" width="100%">
 	<tr>
 		<td align="center" colspan="6"><font size="15pt"><b>�ſ�ī�� ���� SAMPLE PAGE</b></font></td>
 	</tr>
 	<tr>
 		<td align="center"><font color="red">�������ܱ���</font></td>
 		<td align="center"><font color="red">*CASH_GB</font></td>
 		<td><input type="text" name="CASH_GB" id="CASH_GB" size="2" value="<%=CASH_GB%>"></td>
 		<td align="center"><font color="red">����ID</font></td>
 		<td align="center"><font color="red">*CN_SVCID</font></td>
 		<td><input type="text" name="CN_SVCID" id="CN_SVCID" size="12" value="<%=CN_SVCID%>"></td>
 	</tr>
 	<tr>
 		<td align="center"><font color="red">�ŷ����</font></td>
 		<td align="center"><font color="red">*PAY_MODE</font></td>
 		<td><input type="text" name="PAY_MODE" id="PAY_MODE" size="2" value="<%=PAY_MODE%>"></td>
 		<td align="center"><font color="red">��������</font></td>
 		<td align="center"><font color="red">*VER</font></td>
 		<td><input type="text" name="VER" id="VER" size="10" value="<%=VER%>"></td>
 	</tr>
 	<tr>
 		<td align="center"><font color="red">�ŷ��ݾ�</font></td>
 		<td align="center"><font color="red">*Prdtprice</font></td>
 		<td><input type="text" name="Prdtprice" id="Prdtprice" size="10" value="<%=Prdtprice%>"></td>
 		<td align="center"><font color="red">��ǰ��</font></td>
 		<td align="center"><font color="red">*Prdtnm</font></td>
 		<td><input type="text" name="Prdtnm" id="Prdtnm" size="50" value="<%=Prdtnm%>"></td>
 	<tr>
 		<td align="center"><font color="red">�������ŷ���ȣ</font></td>
 		<td align="center"><font color="red">*Tradeid</font></td>
 		<td><input type="text" name="Tradeid" id="Tradeid" size="40" value="<%=Tradeid%>"></td>
 		<td align="center"><font color="red">������������</font></td>
 		<td align="center"><font color="red">*Siteurl</font></td>
 		<td><input type="text" name="Siteurl" id="Siteurl" size="40" value="<%=Siteurl%>"></td>
 	</tr>
 	<tr>
 		<td align="center"><font color="red">����URL</font></td>
 		<td align="center"><font color="red">*Okurl</font></td>
 		<td><input type="text" name="Okurl" id="Okurl" value="<%=Okurl%>"></td>
 		<td align="center"><font color="red">���հ������������û</font></td>
 		<td align="center"><font color="red">*CN_TAX_VER</font></td>
 		<td><input type="text" name="CN_TAX_VER" id="CN_TAX_VER" value="<%=CN_TAX_VER%>"></td>
 	</tr>
	<tr>
		<td align="center">����ó��URL</td>
 		<td align="center">Notiurl</td>
 		<td><input type="text" name="Notiurl" id="Notiurl" value="<%=Notiurl%>"></td>
		<td align="center">����â ȣ�� ���</td>
 		<td align="center">CALL_TYPE</td>
 		<td><input type="text" name="CALL_TYPE" id="CALL_TYPE" value="<%=CALL_TYPE%>"></td>
 	</tr>
 	<tr>
 		<td align="center">����URL</td>
 		<td align="center">Failurl</td>
 		<td><input type="text" name="Failurl" id="Failurl" value="<%=Failurl%>"></td>
		<td align="center">�����ID</td>
 		<td align="center">Userid</td>
 		<td><input type="text" name="Userid" id="Userid" size="30" value="<%=Userid%>"></td>
	</tr>
 	<tr>
		<td align="center">�������ݹ麯��</td>
 		<td align="center">MSTR</td>
 		<td><input type="text" name="MSTR" id="MSTR" value="<%=MSTR%>"></td>
	 	<td align="center">������E-mail</td>
 		<td align="center">Payeremail</td>
 		<td><input type="text" name="Payeremail" id="Payeremail" size="30" value="<%=Payeremail%>"></td>
 	</tr>
 	<tr>
 		<td align="center">��ȣȭ��뿩��</td>
 		<td align="center">Cryptyn</td>
 		<td><input type="text" name="Cryptyn" id="Cryptyn" size="1" value="<%=Cryptyn%>"></td>
	 	<td align="center">��ȣȭ���ڿ�</td>
 		<td align="center">Cryptstring</td>
 		<td><input type="text" name="Cryptstring" id="Cryptstring" value="<%=Cryptstring%>"></td>
 	</tr>
	<tr>
 		<td align="center">�����ڸ�</td>
 		<td align="center">Username</td>
 		<td><input type="text" name="Username" id="Username" value="<%=Username%>"></td>
 		<td align="center">APP SCHEME</td>
 		<td align="center">APP_SCHEME</td>
 		<td><input type="text" name="APP_SCHEME" id="APP_SCHEME" value="<%=APP_SCHEME%>"></td>
 	</tr>
 	<tr>
	 	<td align="center">����/�����/���հ���</td>
 		<td align="center">CN_BILLTYPE</td>
 		<td><input type="text" name="CN_BILLTYPE" id="CN_BILLTYPE" value="<%=CN_BILLTYPE%>"></td>
 		<td align="center">�ΰ���</td>
 		<td align="center">CN_TAX</td>
 		<td><input type="text" name="CN_TAX" id="CN_TAX" value="<%=CN_TAX%>"></td>
 	</tr>
 	<tr>
 		<td align="center">����</td>
 		<td align="center">CN_TAXAMT</td>
 		<td><input type="text" name="CN_TAXAMT" id="CN_TAXAMT" value="<%=CN_TAXAMT%>"></td>
	 	<td align="center">�����</td>
 		<td align="center">CN_TAXFREE</td>
 		<td><input type="text" name="CN_TAXFREE" id="CN_TAXFREE" value="<%=CN_TAXFREE%>"></td>
 	</tr>
 	<tr>
 		<td align="center">�������URL</td>
 		<td align="center">Closeurl</td>
 		<td><input type="text" name="Closeurl" id="Closeurl" value="<%=Closeurl%>"></td>
	 	<td align="center">�������Һ�����</td>
 		<td align="center">CN_FREEINTEREST</td>
 		<td><input type="text" name="CN_FREEINTEREST" id="CN_FREEINTEREST" value="<%=CN_FREEINTEREST%>"></td>
 	</tr>
 	<tr>
 		<td align="center">ī�������Ʈ��뿩��</td>
 		<td align="center">CN_POINT</td>
 		<td><input type="text" name="CN_POINT" id="CN_POINT" value="<%=CN_POINT%>"></td>
	 	<td align="center">��������������ڹ�ȣ</td>
 		<td align="center">Termregno</td>
 		<td><input type="text" name="Termregno" id="Termregno" value="<%=Termregno%>"></td>
 	</tr>
 	<tr>
 		<td align="center">ī��缱�ó���</td>
 		<td align="center">CN_FIXCARDCD</td>
 		<td><input type="text" name="CN_FIXCARDCD" id="CN_FIXCARDCD" value="<%=CN_FIXCARDCD%>"></td>
 		<td align="center">ī�������ȣ��</td>
 		<td align="center">CN_DIRECT</td>
 		<td><input type="text" name="CN_DIRECT" id="CN_DIRECT" value="<%=CN_DIRECT%>"></td>
 	</tr>
 	<tr>
	 	<td align="center">Ư���Һΰ���</td>
 		<td align="center">CN_INSTALL</td>
 		<td><input type="text" name="CN_INSTALL" id="CN_INSTALL" value="<%=CN_INSTALL%>"></td>
 		<td align="center">1ȸ���ź�����</td>
 		<td align="center">Deposit</td>
 		<td><input type="text" name="Deposit" id="Deposit" size="10" value="<%=Deposit%>"></td>
 	</tr>
 	<tr>
	 	<td align="center">�츮ī��,�츮����(WONī��,WON��ŷ) ������ ����</td>
 		<td align="center">CN_PAY_APP_USE_YN</td>
 		<td><input type="text" name="CN_PAY_APP_USE_YN" id="CN_PAY_APP_USE_YN" size="1" value="<%=CN_PAY_APP_USE_YN%>"></td>
 		<td align="center">�츮ī��,�츮����(WONī��,WON��ŷ) �� 1�� �ܵ� ���� ����</td>
 		<td align="center">CN_PAY_APP_USE_CD</td>
 		<td><input type="text" name="CN_PAY_APP_USE_CD" id="CN_PAY_APP_USE_CD" size="2" value="<%=CN_PAY_APP_USE_CD%>"></td>
 	</tr>
 	<tr>
 		<td align="center" colspan="6"></td>
 	</tr>
 	<tr>
 		<td align="center" colspan="6"><input type="button" value="�����ϱ�" onclick="payRequest()"></td>
 	</tr>
 </table>
</form>
</body>
</html>