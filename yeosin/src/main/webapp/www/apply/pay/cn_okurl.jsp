<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%

	/*********************************************************************************
	* ���� ������ �� ������ ��ȯ���� ȣ��Ǵ� �������� ���������� �����ؾ��� ������
	* Notiurl��� �� ���� ��� �ߺ� ó�� ����
	* �˾� ������ ����â ��� �� ������ �θ�â ��� ���� ��ũ��Ʈ ó�� �ʿ�
	*********************************************************************************/

	String CASH_GB		= request.getParameter("CASH_GB"); 		/* ��������(CN)*/
	String Svcid		= request.getParameter("Svcid"); 		/* ���񽺾��̵� */
	String Mobilid		= request.getParameter("Mobilid"); 		/* ������� �ŷ���ȣ */
	String Signdate		= request.getParameter("Signdate"); 	/* �������� */
	String Tradeid		= request.getParameter("Tradeid"); 		/* �����ŷ���ȣ */
	String Prdtnm		= request.getParameter("Prdtnm"); 		/* ��ǰ�� */
	String Prdtprice	= request.getParameter("Prdtprice");	/* ��ǰ���� */
	String Interest		= request.getParameter("Interest");		/* �Һΰ����� */
	String Userid		= request.getParameter("Userid");		/* ����ھ��̵�*/
	String Resultcd		= request.getParameter("Resultcd");		/* ����ڵ� */
	String Resultmsg	= request.getParameter("Resultmsg");	/* ����޼��� */
	String Payeremail	= request.getParameter("Payeremail");	/* ������ �̸��� */
	String MSTR			= request.getParameter("MSTR");			/* ������ ���� �ݹ麯�� */
	String Apprno		= request.getParameter("Apprno");		/* ���ι�ȣ */
	String Paymethod	= request.getParameter("Paymethod");	/* ���ҹ�� */
	String Couponprice	= request.getParameter("Couponprice");	/* ������ �����ݾ� */
	String Deposit		= request.getParameter("Deposit");		/* ��ȸ���ź����ݾ� */
	String Owndivcd		= request.getParameter("Owndivcd");		/* ī��������� */

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
<title>������ OKURL ������� �ſ�ī�� ����</title>
</head>
<body>
	<!-- input user information# -->
	<table border ="1" width="100%">
		<tr>
			<td width="25%">CASH_GB</td>
			<td width="75%"><%=CASH_GB%></td>
		</tr>
		<tr>
			<td>Svcid</td>
			<td><%=Svcid%></td>
		</tr>
		<tr>
			<td>Mobilid</td>
			<td><%=Mobilid%></td>
		</tr>
		<tr>
			<td>Signdate</td>
			<td><%=Signdate%></td>
		</tr>
		<tr>
			<td>Tradeid</td>
			<td><%=Tradeid%></td>
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
			<td>Interest</td>
			<td><%=Interest%></td>
		</tr>
		<tr>
			<td>Userid</td>
			<td><%=Userid%></td>
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
			<td>Payeremail</td>
			<td><%=Payeremail%></td>
		</tr>
		<tr>
			<td>MSTR</td>
			<td><%=MSTR%></td>
		</tr>
		<tr>
			<td>Apprno</td>
			<td><%=Apprno%></td>
		</tr>
		<tr>
			<td>Paymethod</td>
			<td><%=Paymethod%></td>
		</tr>
		<tr>
			<td>Couponprice</td>
			<td><%=Couponprice%></td>
		</tr>
		<tr>
			<td>Deposit</td>
			<td><%=Deposit%></td>
		</tr>
		<tr>
			<td>Owndivcd</td>
			<td><%=Owndivcd%></td>
		</tr>
	</table>
</body>
</html>