<%
/****************************************************************************************
* ���ϸ� : cn_cancel_req.jsp
* �ۼ��� : PG������
* �ۼ��� : 2022.12
* ��  �� : �ſ�ī�� Socket ��� ��� ���� ������
* ��	�� : 0004
* ---------------------------------------------------------------------------------------
* �ҽ� ���Ǻ��濡 ���� �ս��� ������𽺿��� å������ �ʽ��ϴ�.
* ��û �Ķ���� �� ������� ���� ������ �ݵ�� ���� �Ŵ����� �����Ͻʽÿ�.
* �ſ�ī��(CN)�� �����׽�Ʈ ȯ���� �������� �ʽ��ϴ�.
* �׽�Ʈ �������� ������ ������ �Ǵ� ��� ����� �̿��Ͽ� ���� ��� ó���� �ؾ� �մϴ�.
****************************************************************************************/
%>

<%@page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!--  �������� ������û ������ -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ksc5601">
</head>

<body>
<h3>�ſ�ī�� ���� ��� SAMPLE PAGE</h3>
<hr>
<form name='cplogn' method='post' action='cn_cancel_result.jsp'>
<table>
	<tr>
		<td> �ŷ���� : </td>
		<td> <input type="text" maxlength="20" name="mode" value="CN47"> </td>
	</tr>
	<tr>
		<td> ���հ�������������� : </td>
		<td> <input type="text" maxlength="20" name="taxVer" value="CPLX"> </td>
	</tr>
	<tr>
		<td> ����ƮURL : </td>
		<td> <input type="text" maxlength="20" name="recordKey" value=""> </td>
	</tr>
	<tr>
		<td> ���񽺾��̵� : </td>
		<td> <input type="text" maxlength="12" name="svcId" value=""> </td>
	</tr>
	<tr>
		<td> �ŷ���ȣ(������) : </td>
		<td> <input type="text" maxlength="50" name="tradeId" value=""> </td>
	</tr>
	<tr>
		<td> �ŷ���ȣ(�������) : </td>
		<td> <input type="text" maxlength="20" name="mobilId" value=""> </td>
	</tr>
	<tr>
		<td> ��ұݾ� : </td>
		<td> <input type="text" maxlength="30" name="prdtPrice" value=""> </td>
	</tr>
	<tr>
		<td> �κ���ҿ��� : </td>
		<td> <input type="text" maxlength="40" name="partCancelYn" value=""> </td>
	</tr>
	<tr>
		<td> ��ҿ�û���� </td>
		<td> <input type="text" maxlength="10" name="rmk" value=""> </td>
	</tr>
	<tr>
		<td> ��ȭ�ڵ�(KRW,USD) </td>
		<td> <input type="text" maxlength="10" name="crcCd" value=""> </td>
	</tr>
	<tr>
		<td> �������� </td>
		<td> <input type="text" maxlength="10" name="taxatDiv" value=""> </td>
	</tr>
	<tr>
		<td> �ΰ���  </td>
		<td> <input type="text" maxlength="10" name="taxAmt" value=""> </td>
	</tr>
	<tr>
		<td> �����  </td>
		<td> <input type="text" maxlength="10" name="taxFreeAmt" value=""> </td>
	</tr>
	<tr>
		<td> ����  </td>
		<td> <input type="text" maxlength="10" name="taxatAmt" value=""> </td>
	</tr>
	<tr>
		<td> �ܾ׺ΰ���  </td>
		<td> <input type="text" maxlength="10" name="remainTaxAmt" value=""> </td>
	</tr>
	<tr>
		<td> 1ȸ���ź�����  </td>
		<td> <input type="text" maxlength="10" name="deposit" value=""> </td>
	</tr>
</table>
<p> <input type="button" value="��û�ϱ�" onClick='document.cplogn.submit();'> </p>
</form>
</body>
</html>