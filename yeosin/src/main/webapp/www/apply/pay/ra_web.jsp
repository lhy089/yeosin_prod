<%@page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%
/****************************************************************************************
* ���ϸ� : ra_web.jsp
* �ۼ��� : ���񽺰�����
* �ۼ��� : 2013.12
* ��  �� : �ǽð� ������ü ����ũ ��� ���� ���� ������
* ��  �� : 0004
* ---------------------------------------------------------------------------------------
* �������� �ҽ� ���Ǻ��濡 ���� å���� ������𽺿��� å���� ���� �ʽ��ϴ�.
* ��û �Ķ���� �� ���� �� �������� Okurl / Notiurl ���� Return �Ǵ� �Ķ���Ϳ�
* ������ ����ó�� ����� ���� �Ŵ����� �ݵ�� �����ϼ���.
* �����Ǽ��� ��ȯ�� �� ������� ������������� �����ٶ��ϴ�.
* 
* ��ȣȭ ���� �ʼ� Ŭ����
* mup.mcash.module.common.McashCipher.class
* mup.mcash.module.common.McashSeed.class
****************************************************************************************/
%>

<%@page import="mup.mcash.module.common.McashCipher.*"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%
//unique�� �ŷ���ȣ�� ���� �ŷ��Ͻ� (�и���������� ��ȸ)
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
String appr_dtm = dateFormat.format(new Date());
%>

<%
/*****************************************************************************************
- �ʼ� �Է� �׸�
*****************************************************************************************/
String CASH_GB		= "RA";		//[   2byte ����] �������ܱ���. "RA" ������. �����Ұ�!
String RA_SVCID		= "";		//[  12byte ����] ������𽺿��� �ο��� ����ID (12byte ���� ����)
String Prdtprice	= "";		//[  10byte ����] ������û�ݾ� (��ȣȭ ��� �� ��ȣȭ ���)
String PAY_MODE		= "00";		//[   2byte ����] ������ �׽�Ʈ/�ǰ��� ���� (00: �׽�Ʈ����-�����, 10: �ǰŷ�����-����)
String Okurl		= "";		//[ 128byte ����] ���� �Ϸ� �� ����ڿ��� ������ �������� �Ϸ� ������. (��: http://www.mcash.co.kr/okurl.jsp)
String Prdtnm		= "";		//[  30byte ����] ��ǰ��
String Siteurl		= "";		//[  20byte ����] ������������ (��: www.mcash.co.kr)

String Tradeid		= RA_SVCID + "_" + appr_dtm;	//[4byte �̻�, 40byte ����] �������ŷ���ȣ. ���� ��û �� ���� unique�� ���� �����ؾ� ��.
													//�ش� ���ÿ��� �׽�Ʈ�� ���� {������ ����ID + ��û�Ͻ�} �������� �����Ͽ���.



/*****************************************************************************************
- ������ ���� �ʼ��׸�
*****************************************************************************************/
String LOGO_YN		= "N";		//[   1byte ����] ������ �ΰ� ��� ���� (N: ������� �ΰ�-default, Y: ������ �ΰ� (������ ������𽺿� ������ �ΰ� �̹����� ����ؾ���))
String CALL_TYPE	= "P";		//[   4byte ����] ����â ȣ�� ��� (P: �˾�-default, SELF: ��������ȯ, I: ����������)



/*****************************************************************************************
- ���� �Է� �׸�
*****************************************************************************************/
String Payeremail		= "";	//[  30byte ����] ������ e-mail
String Userid			= "";	//[  20byte ����] ������ ������ID
String Item				= "";	//[   8byte ����] �������ڵ�. �̻�� �� �ݵ�� �������� ����.
String Prdtcd			= "";	//[  30byte ����] ��ǰ�ڵ�. ������ ������𽺿� ����� �ʿ���.
String Notiemail		= "";	//[  30byte ����] �˸� e-mail: ���� �Ϸ� �� ���� ���������� Noti ������ ������ ��� �˶� ������ ���� ������ ����� �̸����ּ�
String Notiurl			= "";	//[ 128byte ����] ���� �Ϸ� �� ������ �� ���� ó���� ����ϴ� ������. System back������ ȣ���� �Ǹ� ����ڿ��Դ� �������� �ʴ´�.
String Closeurl			= "";	//[ 128byte ����] ����â ��ҹ�ư, �ݱ��ư Ŭ�� �� ȣ��Ǵ� ������ �� ������. iframe ȣ�� �� �ʼ�! (��: http://www.mcash.co.kr/closeurl.jsp)
String Failurl			= "";	//[ 128byte ����] ���� ���� �� ����ڿ��� ������ ������ �� ���� ������. ����ó���� ���� ����ó�� �ȳ��� ���������� �����ؾ� �� ��츸 ���.
								//                iframe ȣ�� �� �ʼ�! (��: http://www.mcash.co.kr/failurl.jsp)
String MSTR				= "";	//[2000byte ����] ������ �ݹ� ����. ���������� �߰������� �Ķ���Ͱ� �ʿ��� ��� ����ϸ� &, % �� ���Ұ� (��: MSTR="a=1|b=2|c=3")
String Deposit		= "";	//[  10byte ����] 1ȸ���� ������ ����

/*****************************************************************************************
- ������ ���� �����׸� (���� ����� �� �ֽ��ϴ�.)
*****************************************************************************************/
String IFRAME_NAME		= "";	//[   1byte ����] ����â�� iframe���� ȣ�� �� ��� iframe ��Ī ����
String INFOAREA_YN		= "";	//[   1byte ����] ����â �ȳ��� ǥ�ÿ��� (Y: ǥ��-default,  N: ��ǥ��)
String FOOTER_YN		= "";	//[   1byte ����] ����â �ϴ� �ȳ� ǥ�ÿ��� (Y: ǥ��-default,  N: ��ǥ��)
String HEIGHT			= "";	//[   4byte ����] ����â ���� (px����: iframe �� ���� ����â ���� ����, �˾�â �� ȣ��� "" �� ����)
String PRDT_HIDDEN		= "";	//[   1byte ����] iframe ���� ��ǰ�� ���� ���� (������ ������ ����â���� ���� �Է� ���׸� iframe���� ����)
String EMAIL_HIDDEN		= "";	//[   1byte ����] ������ e-mail �Է�â ���� ���� (N: ǥ��-default, Y: ��ǥ��)
String CONTRACT_HIDDEN	= "";	//[   1byte ����] �̿��� ���� ���� (Y: ǥ��-default,  N: ��ǥ��)



/*****************************************************************************************
- ��ȣȭ ó�� (��ȣȭ ��� ��)
Cryptstring �׸��� �ݾ׺����� ���� Ȯ�ο����� �ݵ�� �Ʒ��� ���� ���ڿ��� �����Ͽ��� �մϴ�.

��) ��ȣȭ ��Ʈ���� ���������� �����ϴ� �ŷ���ȣ�� ���� ����Ǿ� ���ǹǷ�
��ȣȭ�� �̿��� �ŷ���ȣ��  �����Ǿ� ���޵� ��� ��ȣȭ ���з� ���� ���� �Ұ�
*****************************************************************************************/
String Cryptyn		= "N";	//Y: ��ȣȭ ���, N: ��ȣȭ �̻��
String Cryptstring	= "";	//��ȣȭ ��� �� ��ȣȭ�� ��Ʈ��

if( Cryptyn.equals("Y") ){
	Cryptstring	= Prdtprice + Okurl;	//�ݾ׺���Ȯ�� (������û�ݾ� + Okurl)
	Okurl		= McashCipher.encodeString(Okurl, Tradeid);
	Failurl		= McashCipher.encodeString(Failurl, Tradeid);
	Notiurl		= McashCipher.encodeString(Notiurl, Tradeid);
	Prdtprice	= McashCipher.encodeString(Prdtprice, Tradeid);
	Cryptstring	= McashCipher.encodeString(Cryptstring, Tradeid);
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr"/>
<title>�ǽð� ������ü SAMPLE PAGE</title>
<%
/*****************************************************************************************
������������ �Ʒ� js ������ �ݵ�� include �ؾ� ��.
�� ����ȯ�� ������ ������� ����ڿ� ���� ���
*****************************************************************************************/
%>
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
	<td align="center" colspan="6"><font size="15pt"><b>�ǽð� ������ü SAMPLE PAGE</b></font></td>
</tr>
<tr>
	<td colspan="3"><font color="red">&nbsp;������ �׸��� �ʼ� ��!!!</font></td>
	<td colspan="3"><font color="blue">&nbsp;�Ķ��� �׸��� ����â UI ���� �Ķ����</font></td>
</tr>
<tr>
	<td align="center"><font color="red">�������� ����</font></td>
	<td align="center"><font color="red">*CASH_GB</font></td>
	<td><input type="text" name="CASH_GB" id="CASH_GB" size="30" value="<%=CASH_GB%>"></td>
	<td align="center"><font color="red">����URL</font></td>
	<td align="center"><font color="red">*Okurl</font></td>
	<td><input type="text" name="Okurl" id="Okurl" size="50" value="<%=Okurl%>"></td>
</tr>
<tr>
	<td align="center"><font color="red">���񽺾��̵�</font></td>
	<td align="center"><font color="red">*RA_SVCID</font></td>
	<td><input type="text" name="RA_SVCID" id="RA_SVCID" size="30" value="<%=RA_SVCID%>"></td>
	<td align="center"><font color="red">��ǰ��</font></td>
	<td align="center"><font color="red">*Prdtnm</font></td>
	<td><input type="text" name="Prdtnm" id="Prdtnm" size="30" value="<%=Prdtnm%>"></td>
</tr>
<tr>
	<td align="center"><font color="red">������û�ݾ�</font></td>
	<td align="center"><font color="red">*Prdtprice</font></td>
	<td><input type="text" name="Prdtprice" id="Prdtprice" size="30" value="<%=Prdtprice%>"></td>
	<td align="center"><font color="red">������������</font></td>
	<td align="center"><font color="red">*Siteurl</font></td>
	<td><input type="text" name="Siteurl" id="Siteurl" size="30" value="<%=Siteurl%>"></td>
</tr>
<tr>
	<td align="center"><font color="red">�ŷ�����</font></td>
	<td align="center"><font color="red">*PAY_MODE</font></td>
	<td><input type="text" name="PAY_MODE" id="PAY_MODE" size="30" value="<%=PAY_MODE%>"></td>
	<td align="center"><font color="red">�������ŷ���ȣ</font></td>
	<td align="center"><font color="red">*Tradeid</font></td>
	<td><input type="text" name="Tradeid" id="Tradeid" size="50" value="<%=Tradeid%>"></td>
</tr>
<tr>
	<td align="center"><font color="red">������ �ΰ� ��뿩��</font></td>
	<td align="center"><font color="red">*LOGO_YN</font></td>
	<td><input type="text" name="LOGO_YN" id="LOGO_YN" size="30" value="<%=LOGO_YN%>"></td>
	<td align="center"><font color="red">����â ȣ����</font></td>
	<td align="center"><font color="red">*CALL_TYPE</font></td>
	<td><input type="text" name="CALL_TYPE" id="CALL_TYPE" size="30" value="<%=CALL_TYPE%>"></td>
</tr>
<tr>
	<td align="center">����뺸 ó�� url</td>
	<td align="center">Notiurl</td>
	<td><input type="text" name="Notiurl" id="Notiurl" size="50" value="<%=Notiurl%>"></td>
	<td align="center">���/�ݱ� �� �̵� url</td>
	<td align="center">Closeurl</td>
	<td><input type="text" name="Closeurl" id="Closeurl" size="50" value="<%=Closeurl%>"></td>
</tr>
<tr>
	<td align="center">���� �� �̵� url</td>
	<td align="center">Failurl</td>
	<td><input type="text" name="Failurl" id="Failurl" size="50" value="<%=Failurl%>"></td>
	<td align="center">����� ID</td>
	<td align="center">Userid</td>
	<td><input type="text" name="Userid" id="Userid" size="30" value="<%=Userid%>"></td>
</tr>
<tr>
	<td align="center">������</td>
	<td align="center">Item</td>
	<td><input type="text" name="Item" id="Item" size="30" value="<%=Item%>"></td>
	<td align="center">��ǰ�ڵ�</td>
	<td align="center">Prdtcd</td>
	<td><input type="text" name="Prdtcd" id="Prdtcd" size="30" value="<%=Prdtcd%>"></td>
</tr>
<tr>
	<td align="center">������ �̸���</td>
	<td align="center">Payeremail</td>
	<td><input type="text" name="Payeremail" id="Payeremail" size="30" value="<%=Payeremail%>"></td>
	<td align="center">������ �ݹ� ����</td>
	<td align="center">MSTR</td>
	<td><input type="text" name="MSTR" id="MSTR" size="50" value="<%=MSTR%>"></td>
</tr>
<tr>
	<td align="center">Noti �˸�E-mail</td>
	<td align="center">Notiemail</td>
	<td><input type="text" name="Notiemail" id="Notiemail" size="30" value="<%=Notiemail%>"></td>
	<td align="center"><font color="blue">iframe ��Ī</font></td>
	<td align="center"><font color="blue">IFRAME_NAME</font></td>
	<td><input type="text" name="IFRAME_NAME" id="IFRAME_NAME" size="30" value="<%=IFRAME_NAME%>"></td>
</tr>
<tr>
	<td align="center"><font color="blue">����â �ȳ��� ǥ�� ����</font></td>
	<td align="center"><font color="blue">INFOAREA_YN</font></td>
	<td><input type="text" name="INFOAREA_YN" id="INFOAREA_YN" size="30" value="<%=INFOAREA_YN%>"></td>
	<td align="center"><font color="blue">����â �ϴ� �ȳ� ǥ�� ����</font></td>
	<td align="center"><font color="blue">FOOTER_YN</font></td>
	<td><input type="text" name="FOOTER_YN" id="FOOTER_YN" size="30" value="<%=FOOTER_YN%>"></td>
</tr>
<tr>
	<td align="center"><font color="blue">����â ����</font></td>
	<td align="center"><font color="blue">HEIGHT</font></td>
	<td><input type="text" name="HEIGHT" id="HEIGHT" size="30" value="<%=HEIGHT%>"></td>
	<td align="center"><font color="blue">��ǰ�� ���� ����</font></td>
	<td align="center"><font color="blue">PRDT_HIDDEN</font></td>
	<td><input type="text" name="PRDT_HIDDEN" id="PRDT_HIDDEN" size="30" value="<%=PRDT_HIDDEN%>"></td>
</tr>
<tr>
	<td align="center"><font color="blue">������ �̸��� ���� ����</font></td>
	<td align="center"><font color="blue">EMAIL_HIDDEN</font></td>
	<td><input type="text" name="EMAIL_HIDDEN" id="EMAIL_HIDDEN" size="30" value="<%=EMAIL_HIDDEN%>"></td>
	<td align="center"><font color="blue">�̿��� ���� ����</font></td>
	<td align="center"><font color="blue">CONTRACT_HIDDEN</font></td>
	<td><input type="text" name="CONTRACT_HIDDEN" id="CONTRACT_HIDDEN" size="30" value="<%=CONTRACT_HIDDEN%>"></td>
</tr>
<tr>
	<td align="center">��ȣȭ ��� ����</td>
	<td align="center">Cryptyn</td>
	<td><input type="text" name="Cryptyn" id="Cryptyn" size="30" value="<%=Cryptyn%>"></td>
	<td align="center">��ȣȭ ���� ��</td>
	<td align="center">Cryptstring</td>
	<td><input type="text" name="Cryptstring" id="Cryptstring" size="50" value="<%=Cryptstring%>"></td>
</tr>
<tr>
	<td align="center">1ȸ���ź�����</td>
	<td align="center">Deposit</td>
	<td><input type="text" name="Deposit" id="Deposit" size="10" value=""></td>
	<td align="center" colspan="3">&nbsp;</td>
</tr>
<tr>
	<td align="center" colspan="6">&nbsp;</td>
</tr>
<tr>
	<td align="center" colspan="6"><input type="button" value="�����ϱ�" onclick="payRequest();"></td>
</tr>
</table>
</form>
</body>
</html>
