<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="mup.mcash.module.common.McashCipher.*"%>
<%
	/*********************************************************************************
	* ���� ������ �ý��� ������� ȣ��Ǵ� �������� ����ó��(��������) �뵵
	* ����ó��(��������) ���� �� 'SUCCESS' ���� �� 'FAIL' ���
	* Okurl�� ���� ��� �ߺ� ó�� ����
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
	String Username		= request.getParameter("Username");		/* �����ڸ�*/
	String Resultcd		= request.getParameter("Resultcd");		/* ����ڵ� */
	String Resultmsg	= request.getParameter("Resultmsg");	/* ����޼��� */
	String Payeremail	= request.getParameter("Payeremail");	/* ������ �̸��� */
	String MSTR			= request.getParameter("MSTR");			/* ������ ���� �ݹ麯�� */
	String Cardnum		= request.getParameter("Cardnum");		/* ���� ī���ȣ */
	String Cardcode		= request.getParameter("Cardcode");		/* ���� ī���ڵ� */
	String Cardname		= request.getParameter("Cardname");		/* ���� ī���� */
	String Apprno		= request.getParameter("Apprno");		/* ���ι�ȣ */
	String Paymethod	= request.getParameter("Paymethod");	/* ���ҹ�� */
	String Couponprice	= request.getParameter("Couponprice");	/* ������ �����ݾ� */
	String chkValue		= request.getParameter("chkValue");		/* ����� ���� hash������ */
	String Deposit		= request.getParameter("Deposit");		/* ��ȸ���ź����ݾ� */
	String Owndivcd		= request.getParameter("Owndivcd");		/* ī��������� */

	/*
	 * ���� ������ �������� ���� Ȯ�� �뵵
	 * �ֿ� ���� ������ HASH ó���� chkValue ���� �޾�
	 * ������ ��Ģ���� Notiurl���� ������ ��(output)�� ���մϴ�.
	 */

	String returnMsg = "";
	String cpChkValue = "";
	cpChkValue = "Mobilid="+ Mobilid +
							"&Mrchid=null"+
							"&Svcid="+ Svcid +
							"&Tradeid="+ Tradeid +
							"&Signdate="+ Signdate +
							"&Prdtprice="+ Prdtprice;

	String encChkValue = McashCipher.encodeString(cpChkValue, Tradeid);

	// �� ��
	if (encChkValue.equals(chkValue)) {
		// ���� �� ����ó��(��������)
		returnMsg = "SUCCESS";
	} else {
		// ��ġ���� ���� ��� ������ �������� ���ɼ� ������ FAIL ó��
		returnMsg = "FAIL";
	}
%>

<%=returnMsg%>