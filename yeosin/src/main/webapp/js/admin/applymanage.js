$(document).ready(function(){ 
	
	// 엑셀 다운로드 버튼
	$("#excelDownload").click(function(){
 		saveExcel($("#docName").val());
	});
});


// 좌석배치 확정함수(manage_status_site.jsp)
function doSeatConfirm() 
{
	var checkBox = $("input[name=examZoneCheck]:checked");	
	var checkValueArr = [];		
	var isSuccess = false;
			
	if (checkBox.length < 1)
	{
		alert("좌석배치할 고사장이 한개도 선택되지 않았습니다.");
		return false;
	}
	
	if (confirm("해당 고사장에 대한 좌석배치 확정을 진행하시겠습니까?")) 
	{
		checkBox.each(function(){
			var checkValue = $(this).val();
			checkValueArr.push(checkValue);	
		});
		
		$.ajax({
			url: "/SeatConfirmByAjax",
	        type: "GET",
	        async: false,
	        dataType : 'json',
	        data: {
					examZoneCheck : checkValueArr
				  },
	        success: function(data) 
			{
				console.log("AJAX Request 성공");
				isSuccess = data.isSuccess;
				alert("좌석배치를 완료했습니다.");
	        },
	        error: function() 
			{
	           console.log("AJAX Request 실패");
	           isSuccess = false;
	        }
		});
	}
	else 
	{
		isSuccess = false;
	}
	
	return isSuccess;
}

/*
excel download 기능.
- 필수 : (1)번 부분에 맞춰서 head, body를 선택하면 됨.
- 선택 : (2)번은 그대로 사용하거나 변경/추가해서 사용 가능. 
*/  
function saveExcel(docName) {

   	$('.column_thead').find('th');
 	$('#columnList').find('tr');

	var columnList = $.map($('.column_thead').find('th'), function(th){ // 리스트 head 찾기 
		if(!$(th).hasClass('first')) return $(th).text();
	});
	
	var dataList = new Array();
	$('#columnList').find('tr').each(function(tr){ // 리스트 body 찾기
		if(tr == 0) return true;
		var row = new Array();
		$(this).children().each(function(idx){
			if(idx == 0) return;
			row.push($(this).text());
		});
		dataList.push(row.join('▒'));
	});
	
	$("#fileName").val(docName);  // 다운로드 받을 엑셀 이름 정의
	$("#columns").val(columnList.join(','));
	$("#data").val(dataList.join('▧'));
	$("#excelForm").submit();
	/* 
		(2)
		<form action="/excelDownload" method="POST" name="excelForm" id="excelForm">
		/excelDownload > UserManageController 에 있음.
	 	완전히 똑같이 호출해도 되고,
	 	/excelDownloadForApplyList 와 같이 다른 이름으로 controller에 추가해서 사용 가능.
	 	controller 메서드는 그대로 사용.
	*/;
}