<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%

request.setCharacterEncoding("EUC-KR");
/****************************************************************************************
* ���ϸ� : okurl.jsp
* �ۼ��� : ���񽺰�����
* �ۼ��� : 2013.12
* ��  �� : �ǽð� ������ü okurl ������
* ��  �� : 0004
* ---------------------------------------------------------------------------------------
* ���� ������ �� ������ ��ȯ���� ȣ��Ǵ� �������̸� ���������� �����ؾ��ϴ� ������
*
* ���� ������ ���� ����� ����ڿ��� ��� �Ǵ� ����ó�� ������
* notiurl ���� ��� ����� �ߺ� ó�� ���� �ʿ�
* �˾� ������ ����â ���� ������ �θ�â�� ���� ��ũ��Ʈ ó���� �Ͻø� �˴ϴ�.
****************************************************************************************/

String Resultcd		= request.getParameter("Resultcd");		//[   4byte ����] ����ڵ�
String Resultmsg	= request.getParameter("Resultmsg");	//[ 100byte ����] ����޼���

String CASH_GB		= request.getParameter("CASH_GB");		//[   2byte ����] ��������(RA)
String Mobilid		= request.getParameter("Mobilid");		//[  15byte ����] ������� �ŷ���ȣ
String Mrchid		= request.getParameter("Mrchid");		//[   8byte ����] ����ID
String MSTR			= request.getParameter("MSTR");			//[2000byte ����] ������ ���� �ݹ麯��
String Payeremail	= request.getParameter("Payeremail");	//[  30byte ����] ������ �̸���
String Prdtnm		= request.getParameter("Prdtnm");		//[  30byte ����] ��ǰ��
String Prdtprice	= request.getParameter("Prdtprice");	//[  10byte ����] ��ǰ����
String Signdate		= request.getParameter("Signdate");		//[  14byte ����] ��������
String Svcid		= request.getParameter("Svcid");		//[  12byte ����] ����ID
String Tradeid		= request.getParameter("Tradeid");		//[  40byte ����] �����ŷ���ȣ
String Userid		= request.getParameter("Userid");		//[  20byte ����] �����ID
String Deposit		= request.getParameter("Deposit");		//[  10byte ����] 1ȸ���ź�����


/*********************************************************************************
* �Ʒ��� ����� �ܼ��� ����ϴ� �����Դϴ�.
* ������������ �θ�â ��ȯ�� ��ũ��Ʈ ó������ �Ͻø� �˴ϴ�.
*********************************************************************************/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr"/>
<title>������ OKURL ������� �ǽð� ������ü</title>
</head>

<body>
<!-- input user information# -->
<table border ='1' width="100%">
<tr>
	<td width="20%">�Ķ����</td>
	<td width="80%">��</td>
</tr>
<tr>
	<td>Resultcd</td>
	<td><%=Resultcd%></td>
</tr>
<tr>
	<td>Resultmsg</td>
	<td><%=Resultmsg%></td>
</tr>
<tr>
	<td>CASH_GB</td>
	<td><%=CASH_GB%></td>
</tr>
<tr>
	<td>Mobilid</td>
	<td><%=Mobilid%></td>
</tr>
<tr>
	<td>Mrchid</td>
	<td><%=Mrchid%></td>
</tr>
<tr>
	<td>MSTR</td>
	<td><%=MSTR%></td>
</tr>
<tr>
	<td>Payeremail</td>
	<td><%=Payeremail%></td>
</tr>
<tr>
	<td>Prdtnm</td>
	<td><%=Prdtnm%></td>
</tr>
<tr>
	<td>Prdtprice</td>
	<td><%=Prdtprice%></td>
</tr>
<tr>
	<td>Signdate</td>
	<td><%=Signdate%></td>
</tr>
<tr>
	<td>Svcid</td>
	<td><%=Svcid%></td>
</tr>
<tr>
	<td>Tradeid</td>
	<td><%=Tradeid%></td>
</tr>
<tr>
	<td>Userid</td>
	<td><%=Userid%></td>
</tr>
<tr>
	<td>Deposit</td>
	<td><%=Deposit%></td>
</tr>
</table>
</body>
</html>
