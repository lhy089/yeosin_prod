<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%

request.setCharacterEncoding("EUC-KR");
/****************************************************************************************
* ���ϸ� : notiurl.jsp
* �ۼ��� : ���񽺰�����
* �ۼ��� : 2013.12
* ��  �� : �ǽð� ������ü notiurl ������
* ��  �� : 0004
* ---------------------------------------------------------------------------------------
* ���������� �����ؾ� �ϴ� notiurl ������ �̸�
* ������𽺿��� ���������� ��������� ���� ȣ���ϴ� ������
* SUCCESS �Ǵ� FAIL �� ���
*
* ��������� �޾� ������� ������ SUCCESS
* ������� ���н� FAIL �� ���
*
* ��) ���� ����� ���� ���� �ΰ��� ���� �ϳ��� ����ؾ� �մϴ�.
*     FAIL ��½� ������𽺿��� ���� ����� ��ȣ�� �մϴ�.
*
*     okurl �ε� ����� �����ϹǷ� notiurl���� �������� okurl ���� �ߺ� ó�� ����
*
*     notiurl �� �ش��ϴ� �Ķ���Ͱ� �����ϴ� ��� notiurl ȣ�� �� okurl ȣ��
*
*     okurl �� �������� ��ȯ�̹Ƿ� ����� �������� ��Ȳ�� ���� ������� ���� ���ɼ��� ����
*     notiurl ȣ���� �������� ������� ������ ȣ���ϴ� ������� ���н� �ٽ� ȣ���ϴ� �������
*     ����������� ������ �ּ�ȭ
****************************************************************************************/

String Resultcd		= request.getParameter("Resultcd");		//[   4byte ����] ����ڵ�
String Resultmsg	= request.getParameter("Resultmsg");	//[ 100byte ����] ����޼���

String Mobilid		= request.getParameter("Mobilid");		//[  15byte ����] ������� �ŷ���ȣ
String Mrchid		= request.getParameter("Mrchid");		//[   8byte ����] ����ID
String MSTR			= request.getParameter("MSTR");			//[2000byte ����] ������ ���� �ݹ麯��
String Prdtnm		= request.getParameter("Prdtnm");		//[  50byte ����] ��ǰ��
String Prdtprice	= request.getParameter("Prdtprice");	//[  10byte ����] ��ǰ����
String Signdate		= request.getParameter("Signdate");		//[  14byte ����] ��������
String Svcid		= request.getParameter("Svcid");		//[  12byte ����] ����ID
String Tradeid		= request.getParameter("Tradeid");		//[  40byte ����] �����ŷ���ȣ
String Userid		= request.getParameter("Userid");		//[  20byte ����] �����ID
String Deposit		= request.getParameter("Deposit");		//[  10byte ����] 1ȸ���ź�����


try {
	/*********************************************************
	* ������ ���������� �Ϸ�Ǿ��� ���(Resultcd=0000) ����
	*********************************************************/
	if("0000".equals(Resultcd)) {
		/*******************************************
		* ���⿡�� ������ �� ���� ó���� �����Ѵ�.
		*******************************************/
		
		//������ �� ���� ó�� ���� ��
		if("���� ó��" == "����") {
			out.println("SUCCESS");
			
		//������ �� ���� ó�� ���� ��
		} else {
			out.println("FAIL");
		}
	}
} catch ( Exception e ){
	
	//������ �� ���� ó�� ���� ��
	out.println("FAIL");
}
%>
