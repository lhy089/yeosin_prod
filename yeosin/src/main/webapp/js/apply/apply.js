$(document).ready(function(){  
	
	// 고사장 검색(apply4.jsp)
	$('#btn_examzoneSearch').click(function(){
		doExamZoneSearch();
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
	var productType = $('#productType').val();	
	var eduNum = $('#eduNum').val();
	var examId = $('#examId').val();
	var isPassEdu = "N";

	if (productType == "*")
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
	var isExamZoneChecked = $('input:radio[type=radio]').is(':checked');
	var isExamAreaChecked = $('input:checkbox[type=checkbox]').is(':checked');
	
	if (!isExamZoneChecked || !isExamAreaChecked)
	{
		alert("고사장과 시험영역은 필수체크입니다.");
		return false;
	}
	else 
	{
		var examZoneId = $('input:radio[name=radio]').val();
		var subjectId = $('input:checkbox[name=check]').val();
		$('input[id=examZoneId]').attr('value', examZoneId);
		$('input[id=examSubjectId]').attr('value', subjectId);
		return true;	
	}
}