$(document).ready(function(){

	// apply3.jsp - 작성완료 클릭시 교육과정 및 교육수료증번호 Valid Check
	$('#btn_completed').click(function(){
		
		var productType = $('#productType').val();	
			
		if (productType == "*")
		{
			alert("교육과정이 선택되지 않았습니다.");
			return;	
		}
		else 
		{
			doCompleted();		
		}
	});
  
	// apply4.jsp - 고사장 검색
	$('#btn_examzoneSearch').click(function(){
		doExamZoneSearch();
	});

});

// 교육증수료번호 체크함수
function doCompleted() 
{
	var eduNum = $('#eduNum').val();
	var examId = $('#examId').val();

	if (eduNum == '') {
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
				$(location).attr("href", "/www/apply/apply4.jsp");
				//doNextPageApply4();
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

// 교육증수료번호가 올바르다면 apply4.jsp로 이동하기 위한 함수
function doNextPageApply4() 
{
	var examId = $('#examId').val();

	$.ajax({
		url: "/apply4",
        type: "GET",
        data: {
		   		examId : examId
			  },
        success: function(data) 
		{
			console.log("AJAX Request 성공2");
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

// 고사장 검색함수
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
			$.each(examZoneList, function(index, value)
			{
				// TODO : List를 apply4.jsp c:forEach 태그에 넣어서 뿌려줘야함			
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