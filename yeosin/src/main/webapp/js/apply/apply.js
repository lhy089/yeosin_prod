$(document).ready(function(){

	// 작성완료 클릭시 교육과정 및 교육수료증번호 Valid Check(apply3.jsp)
	$('#btn_completed').click(function(){	
		doCompleted();		
	});
  
	// 고사장 검색(apply4.jsp)
	$('#btn_examzoneSearch').click(function(){
		doExamZoneSearch();
	});
	
	// 접수하기 클릭시 고사장과 시험영역이 선택되었는지 Valid Check(apply4.jsp)
	$('#btn_receipt').click(function(){
		doReceipt();
	});

});

// 교육증수료번호 체크함수(apply3.jsp)
function doCompleted() 
{
	var productType = $('#productType').val();	
	var eduNum = $('#eduNum').val();
	var examId = $('#examId').val();

	if (productType == "*")
	{
		alert("교육과정이 선택되지 않았습니다.");
		return false;	
	}
	else if (eduNum == '') 
	{
		alert("교육수료증번호는 필수입력입니다.");
		$('#eduNum').focus();
		return false;
	}
	
	$.ajax({
		url: "/isCompleteEdu",
        type: "GET",
        data: {
				eduNum : eduNum,
		   		examId : examId
			  },
        success: function(data) 
		{
			console.log("AJAX Request 성공");
			if (data.isPassEdu == "Y") 
			{
				// TODO : apply4.jsp로 링크를 이동시킴과 동시에 교육수료번호, 시험ID를 넘겨야함
				$(location).attr("href", "/www/apply/apply4.jsp");
			} 
			else 
			{
				alert("등록되어있는 교육수료증번호가 아닙니다.");
			}
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
				createHtml += '<td><input style="width:20px; height:20px;" type="radio" name="radio" value="' + value.examZoneId + '"></td>';
				createHtml += '<td>' + value.examZoneName + '</td>';
				createHtml += '<td>' + value.localCenterDto.localCenterName + '</td>';
				createHtml += '<td>' + value.leftOverSeat + '</td>';
				createHtml += '<td><a href="#" class="btn_map value=' + value.examZoneMap + '">약도</a></td>';
				createHtml += '</tr>';
				
				table.append(createHtml);
				
				createHtml = '';
				
/*				table.append('<tr class="examZoneListRowAjax">');
				table.append($('<td>', {html : "<input type='checkbox' name='check' value='" + value.examZoneId +"'>"}));
				table.append($('<td>', {text : value.examZoneName}));
				table.append($('<td>', {text : value.localCenterDto.localCenterName}));
				table.append($('<td>', {text : value.leftOverSeat}));
				table.append($('<td>', {html : "<a href='#' class='btn_map' value='" + value.examZoneMap + "'>약도</a>"}));
				table.append('</tr>');*/
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
	var isExamZoneChecked = $('input:radio[name=radio]').is(':checked');
	var isExamAreaChecked = $('input:checkbox[name=check]').is(':checked');
	
	if (!isExamZoneChecked || !isExamAreaChecked)
	{
		alert("고사장과 시험영역은 필수체크입니다.");
		return false;
	}
	else 
	{
		// TODO : apply5.jsp로 링크를 이동시킴과 동시에 교육수료번호, 시험ID, 고사장ID, 종목ID를 넘겨야함
		$(location).attr("href", "/www/apply/apply5.jsp");		
	}
}