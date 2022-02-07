<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="ko">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  <meta charset="utf-8">
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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

  <link rel="stylesheet" href="www/inc/css/member.css?t=1">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/member/join.js?t=2"></script>
  <script language='javascript'>
	window.name ="Parent_window";
	
	function fnPopup(authType){
		
		$.ajax({
	        type: "POST",
	        url: "/doOpenCert",
	        data: {sAuthType : authType},
	        sendDataType : 'string',
	        success: function(data) { debugger;
	        	$("#encodeData").val(data);
	        	window.open('', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
	    		document.form_chk.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
	    		document.form_chk.target = "popupChk";
	    		document.form_chk.submit();
	        }
	      });
		
	}
	window.addEventListener('message', function(e) { debugger;
	     var data = e.data;
	     var birtDate = data.birth.substring(0,4)+"-"+data.birth.substring(4,6)+"-"+data.birth.substring(6,8);
	     var gender = data.gender==0?"여":"남"
	     $("#userName").val(data.name);
	     $("#birth").text(birtDate);
	     $("#birth").attr("value",data.birth)
	     $("#gender").text(gender);
	     $("#gender").attr("value",data.gender)
	     
	     alert("인증 되었습니다.");
	     
	    $(".intro").hide();
   		$(".provision").hide();
   		$(".certification").hide();
   		$(".entry").show();
   		$(".finish").hide();
	});
	</script>
</head>

<body>

<!--?php include_once "../common/header.php";?-->
<%@ include file="/www/common/header.jsp"%>
<div class="member join">
  <div class="contentBox">
    <h1 class="commonTop">회원가입</h1>
    <div class="joinBox">
      <!-- (1단계)인트로// -->
      <section class="intro">
        개인회원가입
        <a class="btn_next" id="btn_doJoinProvision" onclick="return false;">만14세이상</a>
      </section>
      <!-- //(1단계)인트로 -->

      <!-- (2단계)약관// -->
      <section class="provision">
        <div><label class="agree"><input type="checkbox" name="check" value="만 14세 이상"> 만 14세 이상입니다.</label></div>
        <div>
          <label class="agree"><input type="checkbox" name="check" value="이용약관 동의"> 대출성 상품 모집인 자격인증센터 이용약관</label>
          <div class="content">
            <!-- '대출성 상품 모집인 자격인증센터 이용약관' -->
            제1조(목적)<br/>
            (사)한국여신전문금융업협회 대출성 상품 모집인 자격인증센터(이하 "자격인증센터"라 합니다) 회원 이용약관(이하 "본 약관" 이라 합니다)은 회원이 자격인증센터에서 온라인으로 제공하는 각종 서비스(이하 "서비스"라 합니다)를 이용함에 있어 회원과 센터의 권리 · 의무 및 책임사항을 규정함을 목적으로 합니다.<br/><br/>

            제2조(약관의 효력 및 개정)<br/>
            ① 본 약관은 자격인증센터 홈페이지에 공지함으로써 효력이 발생합니다.<br/>
            ② 자격인증센터는 합리적인 사유가 발생될 경우 약관의 규제 등에 관한 법률, 전자거래기본법, 전자서명법, 정보통신망이용 촉진 등에 관한 법률 등 관련법을 위배하지 않는 범위에서 본 약관을 개정할 수 있습니다.<br/>
            ③ 자격인증센터는 본 약관을 개정할 경우에는 이를 홈페이지에 공지하는 방법으로 회원에 대한 약관개정 통지를 갈음할 수 있습니다.<br/>
            ④ 자격인증센터가 본 약관을 개정할 경우에는 그 개정약관은 개정된 내용이 관계 법령에 위배되지 않는 한 개정 이전에 회원으로 가입한 회원에게도 적용됩니다.<br/>
            ⑤ 변경된 약관에 이의가 있는 회원은 제6조 제1항에 따라 탈퇴할 수 있습니다.<br/><br/>

            제3조(약관의 적용)<br/>
            본 약관은 자격인증센터가 제공하는 각종 서비스의 이용에 관하여 적용하며, 본 약관에 명시되지 아니한 사항에 대해서는 관계법령 및 개별서비스에 관한 이용약관을 적용합니다.<br/><br/>

            제4조(용어의 정의)<br/>
            본 약관에서 사용하는 용어의 정의는 다음과 같습니다. <br/>
            1. “이용자”라 함은 약관에 따라 “자격인증센터”가 제공하는 “서비스”를 이용하는 회원 및 비회원을 말합니다.<br/>
            2. "회원(이하 "회원"이라 한다)"이란 본 약관에 따라 회원으로 가입하여 “자격인증센터”가 제공하는 서비스를 받는 개인 및 법인 또는 법인에 준하는 단체를 말합니다.<br/>
            3. “아이디(ID)”라 함은 “회원”의 식별과 “서비스” 이용을 위하여 “회원”이 정하고 “자격인증센터”가 부여하는 문자 또는 숫자의 조합을 말합니다.<br/>
            4. “비밀번호(PASSWORD)”라 함은 회원이 부여받은 아이디와 일치된 이용자임을 확인하고 회원의 권익보호를 위하여 회원이 선정한 문자 또는 숫자의 조합을 말합니다.<br/>
            5. “개인정보”라 함은 개인정보보호법 제2조에서 정하는 살아 있는 개인에 관한 정보로서 성명, 주민등록번호 및 영상 등을 통하여 개인을 알아볼 수 있는 정보(해당 정보만으로는 특정 개인을 알아볼 수 없더라도 다른 정보와 쉽게 결합하여 알아볼 수 있는 것을 포함)를 말합니다.<br/>
            6. “고유식별정보”라 함은 개인정보보호법 시행령 제19조 각 호에서 정하는 주민등록번호, 여권번호, 운전면허번호, 외국인등록번호 등을 말합니다.<br/><br/>

            제5조(회원가입 및 변경)<br/>
            ① 회원이 되고자 하는 자가 본 약관을 읽은 후 “동의” 버튼을 클릭하면 회원가입 신청이 됩니다.<br/>
            ② 자격인증센터는 전항과 같이 회원가입을 신청한 자가 허위, 기재누락, 오기가 없는 경우 신청자를 회원으로 등록합니다.<br/>
            ③ 회원가입계약의 성립시기는 서비스이용에 대한 자격인증센터의 이용승낙으로 성립합니다.<br/>
            ④ 회원은 제1항의 회원정보 등록사항의 기재내용에 변경이 발생한 경우, 즉시 변경사항을 정정하여 기재 하여야 합니다.<br/><br/>

            6조(회원 탈퇴 및 자격상실 등)<br/>
            ① 회원은 자격인증센터에 언제든지 자신의 회원 등록을 말소해 줄 것(회원 탈퇴)을 요청할 수 있으며, 자격인증센터는 위 요청을 받은 즉시 해당 회원의 회원 등록 말소를 위한 절차를 밟습니다.<br/>
            ② 회원이 다음 각 호의 사유에 해당하는 경우, 자격인증센터는 회원의 자격을 적절한 방법으로 제한 및 정지 또는 상실시킬 수 있습니다.<br/>
            &nbsp;&nbsp;1. 가입 신청 시에 허위 내용을 등록한 경우<br/>
            &nbsp;&nbsp;2. 다른 사람의 서비스 이용을 방해하거나 그 정보를 도용하는 등 전자거래질서를 해치는 경우<br/>
            &nbsp;&nbsp; 3. 서비스를 이용하여 법령과 본 약관에서 금지하거나 공서양속에 반하는 행위를 하는 경우<br/>
            ③ 자격인증센터는 회원 자격을 상실시키기로 결정한 경우 등록을 말소합니다. 이 경우 자격인증센터는 등록 말소 전에 해당 회원에게 이를 통지합니다.<br/><br/>

            제7조(회원에 대한 통지)<br/>
            ① 자격인증센터는 특정 회원에 대한 통지를 하는 경우 회원가입 시 등록한 회원의 E-mail 주소로 할 수 있습니다.<br/>
            ② 자격인증센터는 불특정다수 회원에 대한 통지를 하는 경우 자격인증센터 홈페이지에 공지함으로써 개별 통지에 갈음할 수 있습니다.<br/><br/>

            제8조(회원의 정보활용 및 보호)<br/>
            ① 자격인증센터는 관계법령이 정하는 바에 따라서 회원 등록정보를 보호하기 위하여 노력합니다. 회원의 개인정보 보호에 관해서는 관계법령 및 자격인증센터가 정하는 "개인정보처리방침"에 정한 바에 의합니다.<br/>
            ② 자격인증센터가 취득한 회원 정보는 정보통신망 이용촉진 및 정보보호 등에 관한 법률 및 기타 관련 법률에 위반되지 않는 범위 내에서 각 부문별 맞춤서비스를 위해서만 이용됩니다.<br/>
            ③ 회원가입 시 작성한 회원정보는 자격인증센터의 사이트 운영이나 개선을 위한 목적으로만 사용되며, 회원의 동의 없이 제3자에게 제공하거나 공개하지 않습니다.<br/><br/>

            제9조(서비스의 제공 및 변경)<br/>
            ① 자격인증센터의 서비스 내용은 아래와 같습니다.<br/>
            &nbsp;&nbsp;1. 원격교육(교육콘텐츠 및 이와 관련한 학사관리)<br/>
            &nbsp;&nbsp;2. 기타 학습관련 자료 및 정보<br/>
            &nbsp;&nbsp;3. 자격인증 평가 원서접수 및 관리<br/>
            &nbsp;&nbsp;4. 기타 자격인증과 관련한 서비스<br/>
            ② 자격인증센터는 필요한 경우 변경될 서비스의 내용 및 제공일자를 제7조 제2항에서 정한 방법으로 회원에게 통지하고, 전 항에서 정한 서비스를 변경하여 제공할 수 있습니다.<br/><br/>

            제10조(서비스의 중단)<br/>
            ① 자격인증센터는 전시, 천재지변 또는 이에 준하는 국가비상사태가 발생하거나 발생할 우려가 있는 경우와 컴퓨터 등 정보통신설비의 보수점검· 교체 및 고장, 장시간 정전 및 통신의 두절 등의 사유가 발생한 경우에는 서비스의 제공을 한시적으로 중단할 수 있고, 새로운 서비스로의 교체 등 기타 자격인증센터가 적절하다고 판단하는 사유가 있을 경우 현재 제공되는 서비스를 완전히 중단할 수 있습니다. <br/>
            ② 전항에 의한 서비스 중단의 경우에는 자격인증센터가 제7조 제2항에서 정한 방법으로 회원에게 통지합니다. 다만, 자격인증센터가 통제할 수 없는 사유로 인한 서비스의 중단으로 인하여 사전 통지가 불가능한 경우에는 그러하지 아니합니다.<br/><br/>

            제11조(자격인증센터의 의무)<br/>
            ① 자격인증센터는 특별한 사유가 없는 한 서비스 제공설비를 항상 운용 가능한 상태로 유지 · 보수하여야 하며, 안정적으로 서비스를 제공할 수 있도록 최선의 노력을 다하여야 합니다.<br/>
            ② 자격인증센터는 관계 법령에 의한 기관의 요청이 있는 경우 회원의 개인정보를 제공할 수 있습니다.<br/>

            제12조(회원의 의무)<br/>
            ① 회원은 관계법령, 본 약관의 규정, 이용안내 및 서비스 상에 공지한 주의사항, 자격인증센터가 통지하는 사항을 준수하여야 하며, 기타 자격인증센터 업무 및 서비스에 방해되는 행위를 하여서는 아니 됩니다.<br/>
            ② 회원은 자격인증센터의 사전 동의 없이 서비스를 이용하여 어떠한 영리행위도 할 수 없으며, 법령에 저촉되는 자료를 배포 또는 게재할 수 없습니다.<br/>
            ③ 회원은 자신의 ID와 비밀번호를 유지 및 관리할 책임이 있으며, 자신의 ID와 비밀번호를 유지 및 관리하지 못하여 발생하는 모든 결과에 대해 전적인 책임이 있습니다. 또한 자신의 ID와 비밀번호가 자신의 승낙없이 사용되었을 경우 즉시 자격인증센터에 통보하여야 하고, 안내가 있는 경우에는 그에 따라야 합니다. 또한 다른 회원의 ID를 도용하는 행위를 할 수 없습니다.<br/>
            ④ 회원은 회원등록내용을 진실하고 정확하게 기재하여야 하며, 현재의 사실과 일치하도록 유지하고 갱신 하여야 합니다.<br/>
            ⑤ 회원은 타인의 명예를 훼손하거나 모욕하는 행위와 타인의 지적재산권 등의 권리를 침해하는 행위 또는 다른 회원에 대한 개인정보를 본인의 사전 동의 없이 수집, 저장, 가공하거나 공개하는 행위를 할 수 없습니다.<br/>
            ⑥ 회원은 해킹이나 컴퓨터 소프트웨어, 하드웨어, 전기통신 장비의 정상적인 가동을 방해, 파괴할 목적으로 고안된 소프트웨어 바이러스, 기타 다른 컴퓨터 코드, 파일, 프로그램을 포함하고 있는 자료 및 관련 법령에 의하여 그 전송 또는 게시가 금지되는 정보를 게시하거나 전자우편으로 발송하는 행위 또는 타인의 의사에 반하여 광고성 정보 등 일정한 내용을 지속적으로 전송하는 행위를 할 수 없습니다.<br/>
            ⑦ 회원은 자격인증센터 직원이나 서비스 관리자를 가장 또는 사칭하거나 타인의 명의를 도용하여 글을 게시하거나 메일을 발송하는 행위를 할 수 없습니다.<br/>
            ⑧ 회원은 정크메일(junk mail), 스팸메일(spam mail), 및 외설 또는 폭력적인 메시지 · 화상 · 음성 등이 담긴 메일을 보내거나 기타 공서양속에 반하는 정보를 공개 또는 게시하는 행위를 할 수 없습니다.<br/>
            ⑨ 회원은 서비스의 운영에 지장을 주거나 줄 우려가 있는 일체의 행위, 기타 관계 법령에 위배되는 행위를 할 수 없습니다.<br/><br/>

            제13조(공개게시물의 삭제)<br/>
            회원이 게시한 공개게시물의 내용이 다음 각 호에 해당하는 경우 자격인증센터는 회원에게 사전 통지 없이 해당 공개게시물을 삭제할 수 있고, 해당 회원의 회원 자격을 제한, 정지 또는 상실시킬 수 있습니다.<br/>
            &nbsp;&nbsp;1. 다른 회원 또는 제3자를 비방하거나 중상모략으로 명예를 손상시키는 내용<br/>
            &nbsp;&nbsp;2. 공서양속에 위반되는 내용의 정보, 문장, 도형 등을 유포하는 내용<br/>
            &nbsp;&nbsp;3. 범죄행위와 관련이 있다고 판단되는 내용<br/>
            &nbsp;&nbsp;4. 다른 회원이나 제3자의 저작권 등 제반 권리를 침해하는 내용<br/>
            &nbsp;&nbsp;5. 기타 관계 법령에 위배된다고 판단되는 내용<br/><br/>

            제14조(저작권의 귀속 및 이용제한)<br/>
            ① 자격인증센터가 작성한 저작물에 대한 저작권 및 지적재산권 등은 자격인증센터에 귀속합니다.<br/>
            ② 회원은 서비스를 이용함으로써 얻은 정보를 자격인증센터의 사전 승낙 없이 복제, 전송, 출판, 배포, 방송 기타 방법에 의하여 영리목적으로 이용하거나 제3자에게 이용하게 하여서는 아니 됩니다.<br/><br/>

            제15조(면책 조항)<br/>
            자격인증센터, 자격인증센터의 이행보조자 또는 피용자의 고의 또는 중대한 과실로 인하여 회원에게 손해가 발생하였을 경우 자격인증센터는 이에 대하여 책임을 집니다. 단, 아래의 사항에 대하여는 면책이 됩니다. <br/>
            ① 자격인증센터는 회원이 게재한 정보, 자료, 사실의 정확성, 신뢰성 등 내용에 관하여는 어떠한 책임도 지지 아니하고, 서비스 자료에 대한 취사선택 또는 이용으로 발생하는 손해 등에 대해서도 책임을 지지 않습니다.<br/>
            ② 자격인증센터는 회원이 서비스를 이용하여 기대하는 손익이나 서비스를 통하여 얻은 자료로 인한 손해에 관하여 책임을 지지 않습니다.<br/>
            ③ 자격인증센터는 회원 상호간 또는 회원과 제3자 상호간에 서비스를 매개로 발생한 분쟁에 대해서는 개입할 의무가 없으며, 배상할 책임이 없습니다.<br/>
            ④ 자격인증센터는 회원의 귀책사유로 인하여 서비스 이용의 장애가 발생한 경우에는 책임을 지지 않습니다.<br/>
            ⑤ 회원은 메뉴에서 이루어지는 각종 상담코너를 이용할 수 있으나 이 답변을 근거로 한 제반 소송의 결과에 대해서는 책임을 지지 않습니다.<br/>
            ⑥ 본 약관의 규정을 위반함으로써 자격인증센터에 손해가 발생하게 되는 경우, 이 약관을 위반한 회원은 자격인증센터가 입은 모든 손해를 배상하여야 하며, 동 손해로부터 자격인증센터를 면책시켜야 합니다.<br/><br/>

            제16조(분쟁의 해결)<br/>
            ① 자격인증센터와 회원은 서비스와 관련하여 발생한 분쟁을 원만하게 해결하기 위하여 필요한 모든 노력을 기울여야합니다.<br/>
            ② 모든 노력에도 불구하고 본 서비스 이용으로부터 발생하거나 그와 관련되어 분쟁이 발생한 경우에는 자격인증센터 관할 법원에 전속적 관할권이 있음에 동의합니다. 따라서, 본 약관의 효력을 인정하지 않는 관할지역 내에서는 본 서비스의 사용이 허가되지 않습니다.<br/><br/>

            제17조(법률의 적용)<br/>
            서비스 이용에 관한 분쟁으로 발생되는 모든 법적인 문제는 대한민국의 관계법령의 적용을 받습니다.<br/><br/><br/>

            부칙<br/>
            제1조(시행일)<br/>
            이 약관은 2022년 2월 14일부터 시행한다.
          </div>
        </div>
        <div>
          <label class="agree"><input type="checkbox" name="check" value="개인정보수집 이용 동의"> 개인정보수집 이용 동의서</label>
          <div class="content">
            <!-- '개인정보수집 이용 동의서' -->
            1. 총칙<br/>
            ① “개인정보”란 살아있는 개인에 관한 정보로서 성명, 주민등록번호 및 영상 등을 통하여 개인을 알아볼 수 있는 정보 (해당 정보만으로는 특정 개인을 알아볼 수 없더라도 다른 정보와 결합하여 알아볼 수 있는 것을 포함)를 말합니다.<br/>
            ② (사)한국여신전문금융업협회 대출성 상품 모집인 자격인증센터(이하 “자격인증센터”)는 모든 개인정보를 중요시하며, 「개인정보 보호법」, 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」 등 관련 법령을 준수하고 있습니다.<br/>
            ③ 자격인증센터는 개인정보처리방침을 통하여 개인정보가 어떤 용도와 방식으로 이용되고 있으며 개인정보 보호를 위하여 어떤 조치가 취해지고 있는지 알려드립니다.<br/>
            ④ 자격인증센터는 개인정보처리방침을 첫 화면에 공개함으로써 언제나 용이하게 확인할 수 있도록 조치하고 있습니다.<br/>
            ⑤ 자격인증센터는 개인정보처리방침을 정부의 법률 및 지침 변경이나 내부 규정의 변경에 따라 개정할 수 있으며, 개인정보처리방침을 개정하는 경우 변경 즉시 홈페이지를 통해 개정된 사항을 쉽게 알아볼 수 있도록 공개합니다.<br/><br/>

            2. 수집하는 개인정보 항목<br/>
            자격인증센터는 홈페이지를 통해 각종 서비스의 제공을 위해 필요한 최소한의 개인정보만을 수집하고 있습니다. 수집하는 개인정보는 아래와 같습니다.<br/>
            &nbsp;&nbsp;◎ 개인정보 수집항목<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;- 자격인증센터는 각종 서비스의 제공을 위해 아래와 같은 개인정보를 수집하고 있습니다.<br/>
            <table class="lawTable">
              <colgroup>
                <col width="15%">
                <col width="*">
                <tr>
                  <th>구분</th>
                  <th>개인정보 수집항목</th>
                </tr>
                <tr>
                  <td class="center">수집항목</td>
                  <td>아이디, 비밀번호, 성명, 본인인증값, 생년월일, 성별, 주소, 이메일, 연락처, 교육과정명, 수료일자, 수료증번호, 평가종별, 평가합격일자, 자격인증서번호, 이메일/SMS 수신동의 여부</td>
                </tr>
              </colgroup>
            </table>
            &nbsp;&nbsp;&nbsp;&nbsp;- 서비스 이용 시 다음과 같은 정보들이 자동으로 생성되어 수집될 수 있습니다.<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;‧ 자동수집정보 : 접속로그, 쿠키, 접속IP, 서비스이용기록<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;- 서비스 이용 시 아래와 같은 정보들이 수집될 수 있습니다.<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;‧ 신용카드 결제 시 : 카드사명, 카드번호<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;‧ 실시간 계좌이체 및 환불요청 시 : 은행명, 계좌번호<br/>
            &nbsp;&nbsp;◎ 개인정보 수집방법<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;- 홈페이지 회원가입, 수강신청 및 수강료 결제, 자격인증 평가 원서접수 및 응시료 결제 등<br/><br/>

            3. 개인정보 수집 및 이용목적<br/>
            자격인증센터는 다음과 같은 목적을 위하여 개인정보를 수집하고 있습니다.<br/>
            &nbsp;&nbsp;◎ 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 비용 정산 : 교육 콘텐츠 제공, 자격인증 평가 운영, 본인인증<br/>
            &nbsp;&nbsp;◎ 교육운영 관리 : 교육운영 안내 및 학습, 독려 등<br/>
            &nbsp;&nbsp;◎ 자격인증평가 관리 : 원서접수 정보 및 결과 정보 관리 등<br/>
            &nbsp;&nbsp;◎ 회원 관리 : 회원제 서비스 이용에 따른 본인확인, 개인 식별, 불량회원의 부정 이용 방지와 비인가 사용 방지<br/>
            &nbsp;&nbsp;◎ 커뮤니티 서비스 활용 : 게시판 등<br/>
            &nbsp;&nbsp;◎ 교육과정 및 자격인증평가 안내에 활용<br/><br/>

            4. 개인정보의 보유 및 이용기간<br/>
            <strong class="rawBold">자격인증센터는 회원 가입일로부터 서비스를 제공하는 기간 동안에 한하여 이용자의 개인정보를 보유 및 이용합니다. 회원탈퇴를 요청하거나, 수집 및 이용목적이 달성 및 이용기간이 종료한 경우 해당 개인정보를 지체없이 파기합니다. 단, 다음의 정보에 대하여는 아래의 이유로 명시한 기간 동안 보존합니다.<br/>
            &nbsp;&nbsp;◎ 서비스 이용기록, 접속 로그, 접속 IP 정보 : 3개월(통신비밀보호법)<br/>
            &nbsp;&nbsp;◎ 대금결제 및 재화 등의 공급에 관한 기록 : 5년(전자상거래등에서의 소비자보호에 관한 법률)<br/>
            &nbsp;&nbsp;◎ 본인확인에 관한 기록 : 6개월(정보통신망 이용촉진 및 정보보호 등에 관한 법률)<br/>
            &nbsp;&nbsp;◎ 소비자의 불만 또는 분쟁처리에 관한 기록 : 3년(전자상거래등에서의 소비자보호에 관한 법률)<br/>
            &nbsp;&nbsp;◎ 교육 수료 및 평가 합격 정보 : 3년(대출성 상품 판매대리・중개업자 자격인증 관리지침)</strong><br/><br/>

            5. 개인정보의 파기절차 및 방법<br/>
            ① 자격인증센터는 수집한 개인정보의 수집·이용 목적이 달성되거나 그 보유기간이 종료되는 경우 본인의 동의 및 관련 법령에 따라 보관이 필요한 경우를 제외하고 해당 정보를 지체 없이 파기합니다.<br/>
            ② 자격인증센터는 서면에 기재된 개인정보의 경우에는 파쇄기로 파기하거나 소각하며 전자적 방법으로 저장된 개인정보의 경우에는 그 기록을 재생할 수 없는 기술적 방법을 사용하여 삭제합니다.<br/><br/>

            6. 개인정보의 안전성 확보조치<br/>
            자격인증센터는 개인정보를 처리함에 있어 개인정보가 분실, 도난, 누출, 변조 또는 훼손되지 않도록 안전성 확보를 위하여 다음과 같은 기술적, 관리적 대책을 강구하고 있습니다.<br/>
            &nbsp;&nbsp;• 허가되지 않은 접근에 대비한 대책<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;- 자격인증센터는 해킹 등의 허가되지 않은 접근 등으로 인하여 개인정보가 유출되거나 훼손되는 것을 막기 위해 최선을 다하고 있으며, 암호화 알고리즘을 이용하여 개인정보를 안전하게 전송하는 보안 솔루션을 적용하고 있습니다.<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;- 또한 침입차단시스템, 침입방지시스템 등을 통하여 무단 접근을 통제하고 있으며, 기타 시스템적으로 안정성을 확보하기 위하여 가능한 모든 기술적 대책을 수립하여 노력하고 있습니다.<br/>
            &nbsp;&nbsp;• 개인정보처리자의 안정성 확보<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;- 자격인증센터는 개인정보의 취급 직원을 최소한으로 제한하고 관련 직원들에 대한 수시 교육을 통하여 본 지침을 철저히 준수케 하고 있으며, 본 지침을 조금이라도 위반하는 사항이 발견될 경우 지체 없이 시정조치를 취합니다.<br/><br/>

            7. 개인정보의 제3자 제공<br/>
            ① 자격인증센터는 수집된 개인정보를 「개인정보의 수집항목 및 이용목적」에서 고지한 범위 내에서 사용하며, 이용자의 사전 동의 없이는 동 범위를 초과하여 이용하거나 원칙적으로 이용자의 개인정보를 외부에 제공하지 않습니다.<br/>
            ② 자격인증센터는 다음의 기관 및 자에게 개인정보를 제공하고 있으며, 제공목적 및 범위는 다음과 같습니다.<br/>
            <table class="lawTable">
              <colgroup>
                <col width="35%">
                <col width="35%">
                <col width="*">
                <tr>
                  <th class="rawBold">제공처</th>
                  <th class="rawBold">제공항목</th>
                  <th class="rawBold">제공목적</th>
                </tr>
                <tr>
                  <td class="center rawBold">전국은행연합회, 생명보험협회, 손해보험협회, 상호저축은행중앙회, 신용협동조합중앙회</td>
                  <td class="center rawBold">성명, 생년월일, 성별, 과정명, 수료일자, 수료증번호, 평가종별, 평가 합격일자, 자격인증서번호</td>
                  <td class="center rawBold">대출성 상품 판매대리・중개업자 수료증・자격인증서 발급내역 조회 등</td>
                </tr>
              </colgroup>
            </table></br>
            8. 개인정보 취급위탁</br>
            자격인증센터는 회원에 대한 서비스 향상을 위하여 아래와 같이 개인정보를 위탁하고 있으며, 관계 법령에 따라 위탁계약 시 개인정보가 안전하게 관리될 수 있도록 필요한 사항을 규정하고 있습니다. 자격인증센터의 개인정보 위탁처리 기관 및 위탁업무 내용은 아래와 같습니다.</br>
            <table class="lawTable">
              <colgroup>
                <col width="50%">
                <col width="*">
                <tr>
                  <th class="rawBold">수탁업체</th>
                  <th class="rawBold">위탁업무 내용</th>
                </tr>
                <tr>
                  <td class="center rawBold">나이스평가정보</td>
                  <td class="center rawBold">본인인증 서비스</td>
                </tr>
                <tr>
                  <td class="center rawBold red">능률협회에 확인필요(PG사)</td>
                  <td class="center rawBold red">온라인 결제 서비스</td>
                </tr>
                <tr>
                  <td class="center rawBold">한국능률협회</td>
                  <td class="center rawBold">자격인증 관련 평가 운영</td>
                </tr>
              </colgroup>
            </table></br>
            9. 이용자 및 법정대리인의 권리·의무 및 그 행사방법</br>
            ① 이용자는 언제든지 등록되어 있는 자신의 개인정보를 조회하거나 수정할 수 있으며 동의철회(가입해지)를 요청할 수도 있습니다.</br>
            ② 이용자의 개인정보 조회 및 수정을 위해서는 로그인 후 회원정보수정에서 개인정보를 변경, 가입해지(동의철회)를 위해서는 로그인 후 정보수정에서 회원탈퇴를 클릭하여 직접 열람, 정정 또는 탈퇴가 가능합니다. 혹은 개인정보관리책임자에게 서면, 전화 또는 이메일로 요청 시 본인 확인 절차를 거친 후 지체 없이 조치하겠습니다.</br>
            ③ 자격인증센터는 이용자가 개인정보의 오류에 대한 정정을 요청한 경우에는 정정을 완료하기 전까지 당해 개인 정보를 이용 또는 제공하지 않습니다. 또한 잘못된 개인정보를 제3자에게 이미 제공한 경우에는 정정 처리결과를 제3자에게 지체 없이 통지하여 정정이 이루어지도록 합니다.</br>
            ④ 자격인증센터는 이용자의 요청에 의해 해지 또는 삭제된 개인정보를 “개인정보의 보유 및 이용기간”에 명시된 바에 따라 처리하고 그 외의 용도로 열람 또는 이용할 수 없도록 처리하고 있습니다.</br>
            ⑤ 자격인증센터는 대리인이 방문하여 열람·증명을 요구하는 경우에는 적법한 위임을 받았는지 확인할 수 있는 위임장과 대리인의 신분증 등을 제출 받아 정확히 대리인 여부를 확인합니다.</br></br>

            10. 개인정보 자동수집 장치의 설치, 운영 및 그 거부에 관한 사항</br>
            ① 자격인증센터는 이용자의 정보를 수시로 저장하고 찾아내는 ‘쿠키(cookie)’ 등을 운용합니다. 쿠키란 자격인증센터의 웹사이트를 운영하는데 이용되는 서버가 이용자의 브라우저에 보내는 아주 작은 텍스트 파일로서 이용자의 컴퓨터 하드디스크에 저장됩니다. 자격인증센터는 다음과 같은 목적을 위해 쿠키를 사용합니다.</br>
            &nbsp;&nbsp;◎ 쿠키 등 사용 목적</br>
            &nbsp;&nbsp;&nbsp;&nbsp;- 회원과 비회원의 접속 빈도나 방문 시간 등을 분석, 이용자의 취향과 관심분야 파악 및 자취 추적, 방문 회수 파악 등을 통한 개인 맞춤 서비스 제공</br>
            ② 이용자는 쿠키 설치에 대한 선택권을 가지고 있습니다. 따라서, 이용자는 웹브라우저에서 옵션을 설정함으로써 모든 쿠키를 허용하거나, 쿠키가 저장될 때마다 확인을 거치거나, 아니면 모든 쿠키의 저장을 거부할 수도 있습니다. 단, 이용자가 쿠키 설치를 거부하였을 경우 서비스 이용에 어려움이 있을 수 있습니다.</br>
            &nbsp;&nbsp;◎ 쿠키 설정 거부 방법</br>
            &nbsp;&nbsp;&nbsp;&nbsp;- 예 : 쿠키 설정을 거부하는 방법으로는 회원님이 사용하시는 웹 브라우저의 옵션을 선택함으로써 모든 쿠키를 허용하거나 쿠키를 저장할 때마다 확인을 거치거나, 모든 쿠키의 저장을 거부할 수 있습니다.</br>
            &nbsp;&nbsp;&nbsp;&nbsp;- 설정방법 예(IE의 경우) : 웹 브라우저 상단의 도구 > 인터넷 옵션 > 개인정보</br></br>

            11. 개인정보보호 담당부서</br>
            자격인증센터는 수집된 개인정보를 보호하고 개인정보와 관련한 불만을 처리하기 위하여 아래와 같이 개인정보보호 담당부서를 지정하고 있습니다. 개인정보보호방침에 관하여 궁금한 사항은 아래의 연락처로 연락하여 주시기 바랍니다.</br>
            <table class="lawTable">
              <colgroup>
                <col width="50%">
                <col width="*">
                <tr>
                  <th>구분</th>
                  <th>개인정보보호업무</th>
                </tr>
                <tr>
                  <td class="center">담당부서</td>
                  <td class="center">연락처</td>
                </tr>
                <tr>
                  <td class="center">정보시스템부</td>
                  <td class="center">02-2011-0770</td>
                </tr>
              </colgroup>
            </table>
            ② 자격인증센터는 담당부서를 변경하는 경우 지정 및 변경사실 등을 인터넷 홈페이지의 공지사항에 게재 또는 개인정보처리방침의 변경 등을 통해 고지하겠습니다.</br></br>

            12. 권익침해 구제방법</br>
            개인정보주체는 개인정보침해로 인한 신고나 상담이 필요한 경우 아래의 기관에 문의하시기 바랍니다.</br>
            &nbsp;&nbsp;◎ 개인분쟁조정위원회 : (국번없이)118</br>
            &nbsp;&nbsp;◎ 정보보호마크인증위원회 : 02-580-0533~4</br>
            &nbsp;&nbsp;◎ 대검찰청 첨단범죄수사과 : 02-3480-2000</br>
            &nbsp;&nbsp;◎ 경찰청 사이버테러대응센터 : 02-392-0330</br></br>

            13. 개인정보처리방침 변경</br>
            이 개인정보처리방침은 동 처리방침이 게시된 2022.02.14부터 적용됩니다. 정부의 정책 또는 보안기술의 변경 등에 따라 개인정보처리방침이 개정될 경우 지체없이 홈페이지에 변경내용 및 시행시기를 지속적으로 공개할 것입니다.
          </div>
        </div>
        <div>
          <label class="agree"><input type="checkbox" name="check" value="개인정보의 제 3자 제공 동의"> 개인정보의 제 3자 제공</label>
          <div class="content">
            <!-- '개인정보의 제 3자 제공' 들어갈 자리 -->
          </div>
        </div>
        <div class="btnWrap">
          <a href="#none" class="btn_all">회원가입 약관에 모두 동의합니다</a>
          <a href="/www/main.jsp" class="btn_cancel">취소</a>
          <a class="btn_join" id="btn_doJoinCertification" onclick="return false;">회원가입</a>
        </div>
        <p class="announcement">
          2006년 9월 24일부터 개정된 ‘주민등록법’에 의해 타인의 주민등록번호를 도용하여 웹사이트에 가입하는 등의 주민등록 번호에<br/>
          대한 단순 부정사용에 대하여서도 3년 이하의 징역 또는 1천만원 이하의 벌금이라는 무거운 형벌을 부과하도록 하고 있습니다.<br/>
          관련 법률 : 주민등록법 제 21조(벌칙) 제 2항 9호 (2006년 9월 24일 시행)<br/>
          만약, 타인의 주민번호를 도용하여 ITQ 회원으로 가입하신 회원이 있으시다면,도용 행위를 즉시 중지하여 주시기 바랍니다.<br/>
          위와 관련하여 타인의 주민번호를 이용하여 원서접수, 자격증 신청및 기타에도 사전 및 사후 발견시에 실격처리됨을 알려드립니다.
        </p>
      </section>
      <!-- //(2단계)약관 -->

      <!-- (3단계)본인인증// -->
      <form name="form_chk" method="post">
      <section class="certification">
        <ul>
          <li class="phone">
            <h3>휴대폰 본인인증</h3>
            <span>본인명의 휴대폰 번호로 인증</span>
            <div>
              <p>
              
		<input type="hidden" name="m" value="checkplusService">						<!-- 필수 데이타로, 누락하시면 안됩니다. -->
		<input type="hidden" name="EncodeData" id="encodeData" value="">		<!-- 위에서 업체정보를 암호화 한 데이타입니다. -->
	    
		<a href="javascript:fnPopup('M');"> 휴대폰 본인인증</a>
	
              </p>
            </div>
          </li>
          <li class="public">
            <h3>공동인증서인증</h3>
            <span>(구 공인인증서)</span>
            <div>
              <p>
                <a href="javascript:fnPopup('U');">공동인증서인증</a>
              </p>
            </div>
          </li>
          <li class="ipin">
            <h3>I-PIN 인증</h3>
            <span>주민번호 대체 서비스</span>
            <div>
              <p>
                <a href="javascript:fnPopup('P');">I-PIN 인증</a>
                <a href="#">I-PIN 발급신청</a>
              </p>
            </div>
          </li>
        </ul>

        <h2>I-PIN 안내</h2>
        <div class="guide">
          아이핀(I-PIN)은 Internet Personal Identification Number의 약자로, 인터넷상 개인식별번호를 의미합니다.<br/>
          대면확인이 불가능한 인터넷상에서 주민등록번호를 대신하여, 본인임을 확인 받을 수 있는 사이버 신원 확인번호가 아이핀입니다.<br/>
          아이핀을 발급 받으셨다면 상단의 I-PIN 인증 받으시면 됩니다.
          <p>
            <a href="#">I-PIN 발급신청</a>
            <a href="#">SCI평가정보</a>
            <a href="#">코리아크레딧뷰로</a>
          </p>
        </div>
      </section>
      </form>
      <!-- //(3단계)본인인증 -->

      <!-- (4단계)기입// -->
      <section class="entry">
        <h1>기본정보<p class="essential">필수입력</p></h1>
        <table>
          <colgroup>
            <col width="20.5%">
            <col width="*">
          </colgroup>
          <tr>
            <th class="essential">이름</th>
            <td>
              <input type="text" name="" value="" id="userName">
            </td>
          </tr>
          <tr>
            <th class="essential">아이디</th>
            <td>
              <input type="text" name="" value="" id="userId"><a href="#" id="btn_chkIdDupl">중복확인</a>
              <p>6~20자의 영문 소문자, 숫자만 가능. 영문소문자 시작.</p>
            </td>
          </tr>
          <tr>
            <th class="essential">비밀번호</th>
            <td>
              <input type="password" name="" value="" id="userPwd">
              <p>6~20자의 영문 대소문자와 숫자, 특수문자를 사용할 수 있으며, 최소 2종류이상을 조합해야 합니다.</br>허용 특수문자 { } [ ] ( ) / | ? ! . * ~ ‘ ^ - _ + # $ % =</p>
            </td>
          </tr>
          <tr>
            <th class="essential">비밀번호 확인</th>
            <td>
              <input type="password" name="" value="" id="userPwd2">
            </td>
          </tr>
          <tr>
            <th class="essential">생년월일</th>
            <td>
              <p id="birth"></p>
            </td>
          </tr>
          <tr>
            <th class="essential">성별</th>
            <td>
              <p id="gender"></p>
            </td>
          </tr>
        </table>

        <h1>추가정보</h1>
        <table>
          <colgroup>
            <col width="20.5%">
            <col width="*">
          </colgroup>
          <tr>
            <th>연락처</th>
            <td>
              <select id="" name="">
                <option value="02">02 서울</option>
                <option value="031">031 경기</option>
              </select>
              <input type="tel" maxlength="4" name="" value="">
              <input type="tel" maxlength="4" name="" value="">
              <p>수신동의 하시면 안내 및 수험 정보를 받으실 수 있습니다.<!--</br>수신동의와 별도로 비밀번호 찾기에 사용되므로 <strong>반드시 입력</strong> 바랍니다.--></p>
              <label><input type="checkbox" id="" name="agreeChk"/> 수신 동의합니다</label>
            </td>
          </tr>
          <tr>
            <th>휴대전화</th>
            <td>
              <select id="phoneNumber" name="">
                <option value="010">010</option>
              </select>
              <input type="tel" maxlength="4" id="phoneNumber2" value="">
              <input type="tel" maxlength="4" id="phoneNumber3" value="">
              <p>수신동의 하시면 안내 및 수험 정보를 받으실 수 있습니다.</br>수신동의와 별도로 비밀번호 찾기에 사용되므로 <strong>반드시 입력</strong> 바랍니다.</p>
              <label><input type="checkbox" id="isReceiveSms" name="agreeChk" disabled="disabled"/> 수신 동의합니다</label>
            </td>
          </tr>
          <tr>
            <th>이메일</th>
            <td>
              <input type="email" id="emailAddress" value="">
              <p>수신동의 하시면 안내 및 수험 정보를 받으실 수 있습니다.</br>수신동의와 별도로 비밀번호 찾기에 사용되므로 <strong>반드시 입력</strong> 바랍니다.</p>
              <label><input type="checkbox" id="isReceiveEmail" name="agreeChk" disabled="disabled"/> 수신 동의합니다</label>
            </td>
          </tr>
        </table>

        <a href="#" class="btn_apply" id="btn_doJoinFinish">가입하기</a>
      </section>
      <!-- //(4단계)기입 -->

      <!-- 완료// -->
      <section class="finish">
        <div class="completed">
          <div>
            <h3>회원가입 완료</h3>
            <p>고객과 함께 하는 한국생산성본부가 되기 위해 최선을 다할 것을 약속드리며,<br/>언제나 보다 나은 서비스로 찾아뵙겠습니다.</p>
          </div>
        </div>

        <h2>회원가입 정보 확인</h2>
        <table>
          <colgroup>
            <col width="15.8%">
            <col width="*">
          </colgroup>
          <tr>
            <th>이름</th>
            <td id="userInfo_name"></td>
          </tr>
          <tr>
            <th>아이디</th>
            <td id="userInfo_id"></td>
          </tr>
          <tr>
            <th>연락처</th>
            <td id="userInfo_callNumber"></td>
          </tr>
          <tr>
            <th>휴대폰번호</th>
            <td id="userInfo_phoneNumber">
              수신 동의합니다 <input type="checkbox" id="userInfo_isReceiveSms" name="agreeChk" onclick="return false"/><!--동의 했을 때 'checked' 포함-->
            </td>
          </tr>
          <tr>
            <th>이메일</th>
            <td id="userInfo_emailAddress">
              수신 동의합니다 <input type="checkbox" id="userInfo_isReceiveEmail" name="agreeChk" onclick="return false"/>
            </td>
          </tr>
        </table>
      </section>
      <!-- //완료 -->
    </div>
  </div>
</div>

<!--?php include_once "../common/footer.php";?-->
<%@ include file="/www/common/footer.jsp"%>
<script>
$(function(){
  /* 인트로 제외 회원가입 순서 숨기기 */
  $('.joinBox section').not('.intro').hide();

  /* 전체 선택 */
  $('.btn_all').click(function() {
		if($(this).hasClass('all_chk')){  //전체선택 해제
      $("input[name=check]").prop("checked", false);
      $('.btn_all').removeClass('all_chk');
    } else {  //전체선택
      $("input[name=check]").prop("checked", true);
      $('.btn_all').addClass('all_chk');
    }
	});

  $("input[name=check]").click(function(){
    var total = $('input[name=check]').length;
    var checked = $('input[name=check]:checked').length;

    if(total != checked) {  //전체선택 해제
      $('.btn_all').removeClass('all_chk');
    } else {  //전체선택
      $('.btn_all').addClass('all_chk');
    }
  });
});
</script>
</body>
</html>

