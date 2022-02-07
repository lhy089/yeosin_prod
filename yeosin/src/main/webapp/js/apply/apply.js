$(document).ready(function(){  
	
	// 고사장 검색(apply4.jsp)
	$('#btn_examzoneSearch').click(function(){
		doExamZoneSearch();
	});
	
	// 접수취소(cancel.jsp)
	$('#btn_cancel').click(function(){
		if (confirm("환불규정을 확인하셨습니까?\n환불규정에 동의 후 취소가 가능합니다.")) 
		{
			location.href="/cancel?receiptId="+$("#receiptId").val();
		}
	});
	
});

// 원서접수 환불규정동의 체크함수(apply2.jsp)
function doApplyStart()
{
	if (!$('#agreeChk').is(':checked'))
	{
		alert("위 내용에 동의가 필요합니다.");
		$('#agreeChk').focus();
		return false;
	} 
	else
	{
		return true;
	}	
}

// 교육증수료번호 체크함수(apply3.jsp)
function doCompleted() 
{
	var subjectType = $('#subjectType').val();	
	var eduNum = $('#eduNum').val();
	var examId = $('#examId').val();
	var isPassEdu = "N";

	if (subjectType == "*")
	{
		alert("교육과정이 선택되지 않았습니다.");
		return false;	
	}
	else if (eduNum == '') 
	{
		alert("교육수료증번호는 필수입력입니다.");
		return false;
	}
	
	$.ajax({
		url: "/isCompleteEdu",
        type: "GET",
        async: false,
        data: {
				eduNum : eduNum,
		   		examId : examId
			  },
        success: function(data) 
		{
			console.log("AJAX Request 성공");
			isPassEdu = data.isPassEdu;
        },
        error: function() 
		{
           console.log("AJAX Request 실패");
           isPassEdu = "N";
        }
	});  
	
	if (isPassEdu == "Y") 
	{
		return true;	
	} 
	else 
	{
		alert("등록되어있는 교육증 수료번호가 아닙니다.");
		return false;
	}
}

// 고사장 검색함수(apply4.jsp)
function doExamZoneSearch() 
{
	var examZoneDetail = $('#examZoneDetailList').val();
	var examId = $('#examId').val();
	
	$.ajax({
		url: "/SearchExamZone",
        type: "GET",
        data: {
				examZoneDetail : examZoneDetail,
				examId : examId
			  },
        success: function(examZoneList) 
		{
			console.log("AJAX Request 성공");
			var table = $('table#examZoneListAjax');
			var tableRows = $(".examZoneListRowAjax");
			var createHtml;
			tableRows.empty();
			
			$.each(examZoneList, function(index, value)
			{	
				createHtml += '<tr class="examZoneListRowAjax">';
				createHtml += '<td><input style="width:20px; height:20px;" type="radio" name="exmaZoneRadio" value="' + value.examZoneId + '"></td>';
				createHtml += '<td>' + value.examZoneName + '</td>';
				createHtml += '<td>' + value.localCenterDto.localCenterName + '</td>';
				createHtml += '<td>' + value.leftOverSeat + '</td>';
				createHtml += '<td><a href="#" class="btn_map value=' + value.examZoneMap + '">약도</a></td>';
				createHtml += '</tr>';
				
				table.append(createHtml);
				
				createHtml = '';
			});
        },
        error: function() 
		{
           console.log("AJAX Request 실패");
        },
        complete: function()
		{
        }	
	});   
}

// 접수하기 체크함수(apply4.jsp)
function doReceipt() 
{		 
	var isExamZoneChecked = $('input:radio[name=exmaZoneRadio]').is(':checked');
	var isExamAreaChecked = $('input:radio[name=subjectRadio]').is(':checked');
	
	if (!isExamZoneChecked || !isExamAreaChecked)
	{
		alert("고사장과 시험영역은 필수체크입니다.");
		return false;
	}
	else 
	{
		return true;	
	}
}

// 결제하기 체크함수(apply5.jsp)
function doPayment()
{	
	var isPaymentChecked = $('input:radio[name=paymentMethod]').is(':checked');
	
	if (!isPaymentChecked)
	{
		alert("결제방법은 필수체크입니다.");
		return false;
	}
	else 
	{
		var result = confirm('위 내용으로 결제를 진행하시겠습니까?'); 
		if (result) {
			nicepayStart();
			doApplyInfoInsert
		}
		else return false;
	}
}

// TODO:나누기
function nicepayStart() { debugger;
	var payMethod = $('input[name="paymentMethod"]:checked').val();
	$("#PayMethod").val(payMethod);
	$("#BuyerTel").val($("#BuyerTel").val().replaceAll("-",""));
	if(checkPlatform(window.navigator.userAgent) == "mobile"){//모바일 결제창 진입
		document.payForm.action = "https://web.nicepay.co.kr/v3/v3Payment.jsp";
		document.payForm.acceptCharset="euc-kr";
		document.payForm.submit();
	}else{//PC 결제창 진입
		var a = goPay(document.payForm);
		return true;
	}
}

//결제하기 체크함수(apply5.jsp)
function doApplyInfoInsert()
{	
	document.doApplyInfoIForm.submit();
}

function checkPlatform(ua) {
if(ua === undefined) {
	ua = window.navigator.userAgent;
}

ua = ua.toLowerCase();
var platform = {};
var matched = {};
var userPlatform = "pc";
var platform_match = /(ipad)/.exec(ua) || /(ipod)/.exec(ua) 
	|| /(windows phone)/.exec(ua) || /(iphone)/.exec(ua) 
	|| /(kindle)/.exec(ua) || /(silk)/.exec(ua) || /(android)/.exec(ua) 
	|| /(win)/.exec(ua) || /(mac)/.exec(ua) || /(linux)/.exec(ua)
	|| /(cros)/.exec(ua) || /(playbook)/.exec(ua)
	|| /(bb)/.exec(ua) || /(blackberry)/.exec(ua)
	|| [];

matched.platform = platform_match[0] || "";

if(matched.platform) {
	platform[matched.platform] = true;
}

if(platform.android || platform.bb || platform.blackberry
		|| platform.ipad || platform.iphone 
		|| platform.ipod || platform.kindle 
		|| platform.playbook || platform.silk
		|| platform["windows phone"]) {
	userPlatform = "mobile";
}

if(platform.cros || platform.mac || platform.linux || platform.win) {
	userPlatform = "pc";
}

return userPlatform;
}