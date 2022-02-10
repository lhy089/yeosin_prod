<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="ko">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  <meta charset="utf-8">
  <title>대출성 상품 판매대리•중개업자 등록 자격인증 평가</title>
  <meta name="description" content="여신금융협회">
  <meta name="keywords" content="원서접수, 평가응시현황, 시험안내, 알림마당, 회원정보">
  <meta name="viewport" content="user-scalable=no,
   initial-scale=1.0,
   maximum-scale=1.0,
   minimum-scale=1.0,
   width=device-width,
   height=device-height">
  <meta property="og:type" content="website">
  <meta property="og:site_name" content="여신금융협회"/>
  <!-- <meta property="og:url" content="사이트url"> -->
  <meta property="og:title" content="대출성 상품 판매대리•중개업자 등록 자격인증 평가">
  <meta property="og:description" content="대출성 상품 판매대리•중개업자 등록 자격인증 평가">
  <meta property="og:image" content="/www/inc/img/openGraph.jpg">
  <link rel="shortcut icon" href="/www/inc/img/favicon.png"/>
  <link rel="icon" href="/www/inc/img/favicon.png" type="image/x-icon">
  <link rel="stylesheet" href="/www/inc/css/apply.css">
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/apply/apply.js?t=2"></script>
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="apply second">
  <div class="contentBox">
    <h1>원서접수</h1>
    <div class="announcement">
      원서접수는 인터넷 접수만 가능합니다.<br/>
      원서접수 완료 후 반드시 접수 내용을 확인하세요.
    </div>
    <section>
      <table>
        <colgroup>
          <col width="56%">
          <col width="*">
        </colgroup>
        <tr>
          <th>응시료 반환 경우</th>
          <th>응시료 반환 규정</th>
        </tr>
        <tr>
          <td>1.응시료를 과오납 한 경우</td>
          <td class="red">과오납한 금액의 전부 환불</td>
        </tr>
        <tr>
          <td>2.시험 접수 기간 내에 접수를 취소하는 경우</td>
          <td class="red">납입한 수수료의 100% 환불(결제수수료 포함)</td>
        </tr>
        <tr>
          <td>3.접수기간 후부터 시험 시행일 7일 전까지 접수를 취소한 경우</td>
          <td class="red">납입한 수수료의 50% 환불(결제수수료 포함)</td>
        </tr>
        <tr>
          <td>4.시험 시행일 6일 이내에 접수를 취소한 경우</td>
          <td class="red">취소 및 환불 불가</td>
        </tr>
      </table>

      <h2>적용기간별 환불 안내</h2>
      <table>
        <colgroup>
          <col width="15%">
          <col width="11%">
          <col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%"><col width="3%">
          <col width="*">
        </colgroup>
        <tr>
          <th rowspan="2">적용기간</th>
          <th>접수기간<br/>중</th>
          <th colspan="15">접수기간 후(7일전)</th>
          <th colspan="6">회별시험시작<br/>(6일이내)</th>
          <th>시험일</th>
        </tr>
        <tr>
          <th>-</th>
          <th>...</th>
          <th>20</th>
          <th>19</th>
          <th>18</th>
          <th>17</th>
          <th>16</th>
          <th>15</th>
          <th>14</th>
          <th>13</th>
          <th>12</th>
          <th>11</th>
          <th>10</th>
          <th>9</th>
          <th>8</th>
          <th>7</th>
          <th>6</th>
          <th>5</th>
          <th>4</th>
          <th>3</th>
          <th>2</th>
          <th>1</th>
          <th>시험일</th>
        </tr>
        <tr>
          <th>적용기간</th>
          <td>100%</td>
          <td colspan="15">50%</td>
          <td colspan="7">취소 및 환불 불가</td>
        </tr>
      </table>
      <form action="/apply3" method="get" onsubmit="return doApplyStart();">
		<input type="hidden" value="${examId}" id="examId" name="examId"/>
		<input type="hidden" value="${local}" id="local" name="local"/>
      	<label><input type="checkbox" id="agreeChk" name="agreeChk"/> 위의 내용을 모두 인지하였고 이에 동의합니다.</label>
      	<input style="border:none;" class="btn_apply" type="submit" value="원서접수하기"/>
      </form>
    </section>
  </div>
</div>
<%@ include file="/www/common/footer.jsp"%>
<!--?php include_once "../common/footer.php";?-->

</body>
</html>

