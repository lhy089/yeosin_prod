$(document).ready(function(){  
	
	// 고사장 검색(apply4.jsp)	
	$('#btn_examzoneSearch').click(function(){
		doExamZoneSearch();
	});
	
	// 강제클릭(화면 진입시 바로 조회)
	$('#btn_examzoneSearch').trigger("click");
	
	// 영수증 출력
	$('#btn_receiptPrint').click(function () {
//		alert("준비중입니다.\n영수증 필요시 카드사 출력 가능하며\n불가능 시 별도 문의 바랍니다.");
		var url = "https://npg.nicepay.co.kr/issue/IssueLoader.do?TID=" + $("#TID").val() + "&type=0";
		var options = 'top=10, left=10, width=250, height=400, status=no, menubar=no, toolbar=no, resizable=no';
		window.open(url, "영수증 출력", options);
	})

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
	var userName = $('#userName').val();
	var gender = $('#gender').val();
	var birthDate = $('#birthDate').val();
	var eduNum = $('#eduNum').val();
	var examId = $('#examId').val();
	var subjectId = $('#subjectType').val();
	var isPassEdu = "N";
	var isValidCertDate = "N";

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
        type: "POST",
        async: false,
        data: {
				userName : userName,
		   		gender : gender,
		   		birthDate : birthDate,
		   		eduNum : eduNum,
		   		examId : examId,
		   		subjectId : subjectId
			  },
        success: function(data) 
		{
			console.log("AJAX Request 성공");
			isPassEdu = data.isPassEdu;
			isValidCertDate = data.isValidCertDate;
        },
        error: function() 
		{
           console.log("AJAX Request 실패");
           isPassEdu = "N";
           isValidCertDate = "N";
        }
	});  
	
	if (isPassEdu == "Y" && isValidCertDate == "Y") 
	{
		return true;	
	} 
	else if (isPassEdu == "N")
	{
		alert("등록되어있는 교육증 수료번호가 아닙니다.\n교육과정 및 수료증번호를 다시 확인해 주시기 바랍니다.");
		return false;
	}
	else if (isValidCertDate == "N")
	{
		alert("해당 교육 수료번호는 현재날짜 기준으로 1년이상이 지난 교육 수료번호입니다.\n교육과정 및 수료증번호를 다시 확인해 주시기 바랍니다.");
		return false;
	}
	else
	{
		alert("오류가 발생했습니다.\n다음단계로 진행할 수 없습니다.");
		return false;		
	}
}

// 고사장 검색함수(apply4.jsp)
function doExamZoneSearch() 
{
	var local = $('#local').val();
	var examZoneDetail = $('#examZoneDetailList').val();
	var examId = $('#examId').val();
	
	$.ajax({
		url: "/SearchExamZone",
        type: "GET",
        data: {
				local : local,
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
				var examZoneMap = "'" + value.examZoneMap + "'";
				var description = "'" + value.description + "'";
				
				var readonlyOption = "";
            
	            if(value.leftOverSeat == '0')
	               readonlyOption ="disabled";
	            else    
	               readonlyOption ="";
               
				createHtml += '<tr class="examZoneListRowAjax">';
				createHtml += '<td><input ' + readonlyOption +' style="width:20px; height:20px;" type="radio" name="exmaZoneRadio" value="' + value.examZoneId + '"></td>';
				createHtml += '<td>' + value.examZoneName + '</td>';
				createHtml += '<td>' + value.local + '</td>';
				createHtml += '<td>' + value.leftOverSeat + '</td>';
				createHtml += '<td><a onclick="callExamZoneMap(' + examZoneMap + ', ' + description + ');" href="#" class="btn_map">약도</a>';
				createHtml += '<input type="hidden" id="' + value.examZoneMap + '" name="examZoneMap" value="' + value.examZoneMap + '"/>';
				createHtml += '<input type="hidden" id="' + value.description + '" name="description" value="' + value.description + '"/></td>';
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

// 약도호출(apply4.jsp)
function callExamZoneMap(examZoneMap, description)
{
	if (examZoneMap == "null")
	{
		alert("등록된 고사장 약도가 없습니다.");
	}
	else 
	{
		var fileUrl = "";
		var localFileName = "";
		
		$.ajax({
			url: "/searchImageView",
	        type: "GET",
	        async: false,
	        data:	{            
	        	fileId : examZoneMap
	           		},
	        success: function(result) 
	        { 
				console.log("AJAX Request 성공");
				fileUrl = result.fileUrl;
				localFileName = result.localFileName;
	        },
	        error: function() 
	        {
				console.log("AJAX Request 실패");
	        },
			complete: function()
			{
			}   
	}); 
		
		var width = screen.availWidth / 1.5;
		var height = screen.availHeight / 1.5;
	    var left = (window.screen.width / 2) - (width / 2);
	    var top = (window.screen.height / 2) - (height / 2);
		var createHtml = "";
		
		var win = window.open("", 'asdsad', 'top=' + top + ', left=' + left + ', width=' + width + ', height =' + height + ',scrollbars=yes, status=yes');
		examZoneMap = "/popupImageView?fileUrl="+encodeURIComponent(fileUrl)+"&localFileName="+encodeURIComponent(localFileName);
		createHtml += "<h2>고사장 약도</h2>";
		createHtml += "<p>고사장 주소 : " + description + "</p>";
		createHtml += "<img src='" + examZoneMap + "' style=" + "width:100%;" + "height: 80%" + "/>";
	
		win.document.write(createHtml);	
	}
}

// 접수하기 체크함수(apply4.jsp)
function doReceipt() 
{		 
	var isExamZoneChecked = $('input:radio[name=exmaZoneRadio]').is(':checked');
	var isExamAreaChecked = $('input:radio[name=subjectRadio]').is(':checked');
	
	var examId = $('#examId').val();   
   	var examZoneId = $('input:radio[name=exmaZoneRadio]').val();
   	var seatCount = 0;
   
	if (!isExamZoneChecked || !isExamAreaChecked)
	{
		alert("고사장과 시험영역은 필수체크입니다.");
		return false;
	}
	else 
	{
		$.ajax({
				url: "/CheckLeftOverSeat",
		        type: "POST",
		        async: false,
		        data:	{            
		            		examId : examId,
		            		examZoneId : examZoneId
		           		},
		        success: function(result) 
		        {
					console.log("AJAX Request 성공");
		            seatCount = result.seat;      
		        },
		        error: function() 
		        {
					console.log("AJAX Request 실패");
		        },
				complete: function()
				{
				}   
		}); 
		      
		if (seatCount > 0)
		{
			$("#examZoneId").val(examZoneId);
			return true;
		}
  		else
  		{
			alert("잔여 좌석이 없습니다.");
		    return false;	
		}
	}
}

function localChk(examId, userId)
{
	var local = $("#" + examId + " option:selected").val();
	var receiptCount = 0;
   	
   	if (!local)
   	{
      	alert("시험지역을 선택 해 주세요.");
      	return false;
   	} 
   	
	// 해당시험에 접수한 이력이 있는지 확인
	$.ajax({
		url: "/isReceiptExam",
	    type: "GET",
	    async: false,
	    data: {
				examId : examId,
		   		userId : userId
			  },
	    success: function(data) 
		{
			console.log("AJAX Request 성공");
			receiptCount = data.receiptCount;
	    },
	    error: function() 
		{
	       console.log("AJAX Request 실패");
	       receiptCount = 100;
	    }
	});  
	
	if (receiptCount > 0)
   	{
      	alert("이미 접수한 시험입니다.");
      	return false;
	}
	else 
	{
		var url = encodeURI("/apply2?examId=" + examId + "&local=" + local)
		location.href= url;
	}
   	   
}

// 환불규정 동의(accept_view.jsp)
function doRefund()
{
	var receiptId = $('#receiptId').val();
	var isCancelReceipt = "N";
	var CancelAmt = $('#CancelAmt').val();
	var examDate = new Date($('#examDate').val());
	var receiptStartDate = new Date($('#receiptStartDate').val());
	var receiptEndDate = new Date($('#receiptEndDate').val());
	var now = new Date();
	var before_6Days = new Date(examDate.setDate(examDate.getDate()-6));
	var bCheckHalf = false;
	
   	if(now > before_6Days || now.getDate() == before_6Days.getDate())
   	{
   		alert("접수 취소 가능한 날짜가 지났습니다.");
   		return false;
   	}	
   	else if(now > receiptEndDate && now.getDate() != receiptEndDate.getDate())
   	{
   		alert("접수기간 이후로 취소하여 환불금액이 50% 감소합니다.");
   		bCheckHalf = true;
   	}
	
	// 해당 접수가 이미 취소된 상태인지 확인
	$.ajax({
		url: "/isCancelReceipt",
	    type: "GET",
	    async: false,
	    data: {
				receiptId : receiptId
			  },
	    success: function(data) 
		{
			console.log("AJAX Request 성공");
			isCancelReceipt = data.isCancelReceipt;			
	    },
	    error: function() 
		{
	       console.log("AJAX Request 실패");
	       receiptCount = 100;
	    }
	}); 
	
	if (isCancelReceipt == "Y")
	{
		alert("이미 접수가 취소된 건입니다.");
		return false;
	}
	else if (confirm("환불규정을 확인하셨습니까?\n환불규정에 동의 후 취소가 가능합니다.")) 
	{
		if(bCheckHalf == true){
			CancelAmt = CancelAmt / 2;
   			$("#CancelAmt").val(CancelAmt);
   			$("#PartialCancelCode").val('1');
		}
		return true;
	}	
	else 
	{
		return false;
	}
}

// 결제하기 체크함수(apply5.jsp)
function doPayment()
{	
	var isPaymentChecked = $('input:radio[name=paymentMethod]').is(':checked');
	var examId = $('#examId').val();
	var isValidExam = false;
	
	// 현재 접수중인 시험이 현재 접수기간인 시험인지 유효성 체크
	$.ajax({
		url: "/IsValidExam",
	    type: "GET",
	    async: false,
	    data: {
				examId : examId
			  },
	    success: function(data) 
		{
			console.log("AJAX Request 성공");
			isValidExam = data.isValidExam;			
	    },
	    error: function() 
		{
	       console.log("AJAX Request 실패");
	    }
	}); 
	
	if (!isValidExam)
	{
		alert("현재 접수중인 시험은 접수기간의 시험이 아닙니다.\n홈화면부터 다시시작하거나, 브라우저를 재실행해주세요.");
		return false;
	}
	else if (!isPaymentChecked)
	{
		alert("결제방법은 필수체크입니다.");
		return false;
	}
	else 
	{
		var result = confirm('위 내용으로 결제를 진행하시겠습니까?'); 
		if (result) 
		{
			nicepayStart();
		}
		else return false;
	}
}

// TODO:나누기
function nicepayStart() { debugger;
	var payMethod = $('input[name="paymentMethod"]:checked').val();
	$("#PayMethod").val(payMethod);
	$("#BuyerTel").val($("#BuyerTel").val().replace(/-/g,""));
	if(checkPlatform(window.navigator.userAgent) == "mobile"){//모바일 결제창 진입
		document.payForm.action = "https://web.nicepay.co.kr/v3/v3Payment.jsp";
		document.payForm.acceptCharset="euc-kr";
		document.payForm.submit();
	}else{//PC 결제창 진입
		var a = goPay(document.payForm);
		return true;
	}
}

//[PC 결제창 전용]결제 최종 요청시 실행됩니다. <<'nicepaySubmit()' 이름 수정 불가능>>
function nicepaySubmit(){
	document.payForm.submit();
}

//[PC 결제창 전용]결제창 종료 함수 <<'nicepayClose()' 이름 수정 불가능>>
function nicepayClose(){
	alert("결제가 취소 되었습니다");
}

//결제하기 체크함수(apply5.jsp)

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
