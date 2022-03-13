$(document).ready(function(){ 
	
	// 엑셀 다운로드 버튼
	$("#excelDownload").click(function(){
 		saveExcel($("#docName").val());
	});
	
	// 고사장 등록 버튼
	$("#btnFile").click(function(){
       doExamZoneSave();
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
	
	if (isSuccess) alert("좌석배치를 완료했습니다.");
	else alert("좌석배치가 실패했습니다.");
	
	return isSuccess;
}

// 고사장 수정 or 저장함수(site_register.jsp)
function doExamZoneSave() 
{
	var examZoneId = $("#examZoneId").val(); 
   	var local = $("#local").val();
   	var localDetail = $("#localDetail").val();
   	var examZoneName = $("#examZoneName").val();
   	var examRoomCnt = $("#examRoomCnt").val();
   	var examRoomUserCnt = $("#examRoomUserCnt").val();
   	var address = $("#address").val();
   	var mapFileName = $("#mapFileName").val();
   	var mapFileFullName = $("#mapFileFullName").val();
   	var mapFileDialog = $("#mapFileDialog").val();   
   	var actionCode = "Save";
   	var isSuccess = false;
   	   
   	if (examZoneId == null || examZoneId == "null" || examZoneId == "") actionCode = "Save";   
   	else actionCode = "Modify";
   
   	if (local == null || local == "null" || local == "")
   	{
		alert("지역은 필수입력입니다.");
      	return isSuccess;
   	}
   	else if (localDetail == null || localDetail == "null" || localDetail == "")
   	{
      	alert("구는 필수입력입니다.");
      	return isSuccess;
   	}
   	else if (examZoneName == null || examZoneName == "null" || examZoneName == "")
   	{
      	alert("고사장명은 필수입력입니다.");
      	return isSuccess;
   	}
   	else if (examRoomCnt == null || examRoomCnt == "null" || examRoomCnt == "")
   	{
      	alert("시험 교실 수는 필수입력입니다.");
      	return isSuccess;
   	}
   	else if (examRoomUserCnt == null || examRoomUserCnt == "null" || examRoomUserCnt == "")
   	{
      	alert("교실당 인원수는 필수입력입니다.");
      	return isSuccess;
   	}
   	else if (address == null || address == "null" || address == "")
   	{
      	alert("주소는 필수입력입니다.");
      	return isSuccess;
   	}   
   
   	if (confirm("해당 내용으로 저장하시겠습니까?")) 
   	{            
      	$.ajax({
         	url: "/ExamZoneSaveByAjax",
           	type: "POST",
           	async: false,
         	data: {
               		examZoneId : examZoneId,
               		local : local,
               		localDetail : localDetail,
               		examZoneName : examZoneName,
               		examRoomCnt : examRoomCnt,
               		examRoomUserCnt : examRoomUserCnt,
               		address : address,
               		mapFileName : mapFileName,
               		mapFileFullName : mapFileFullName,
               		actionCode : actionCode,
               		mapFileDialog : mapFileDialog
              	  },
			success: function(data) 
         	{
				console.log("AJAX Request 성공");
            	isSuccess = data.isSuccess;   
            	location.href = "/siteRegister";
               
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
   
   	if (isSuccess) alert("저장이 완료되었습니다.");
   	else alert("저장이 실패되었습니다.");
   	
   	var form = $("#form").submitButton.disabled = true;
   
   	return isSuccess;
}

// 고사장 삭제함수(site_list.jsp)
function doExamZoneDelete() 
{
	var checkBox = $("input[name=examZoneCheck]:checked");	
	var checkValueArr = [];		
	var isSuccess = false;
			
	if (checkBox.length < 1)
	{
		alert("삭제할 고사장이 한개도 선택되지 않았습니다.");
		return false;
	}
	
	if (confirm("해당 고사장을 삭제 하시겠습니까?")) 
	{
		checkBox.each(function(){
			var checkValue = $(this).val();
			checkValueArr.push(checkValue);	
		});
		
		$.ajax({
			url: "/ExamZoneDeleteByAjax",
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
	
	if (isSuccess) alert("고사장 삭제를 완료했습니다.");
	else alert("고사장 삭제를 실패했습니다.");
	
	return isSuccess;
}

// 고사장 약도등록(site_register.jsp)
function doAddMapFile()
{
	$('#mapFileDialog').click();
	
	$("#mapFileDialog").change(function(e){

		$('#mapFileName').val($('input[id=mapFileDialog]')[0].files[0].name);
		$('#mapFileFullName').val($('input[id=mapFileDialog]')[0].files[0]);

    });
}

// 고사장 약도삭제(site_register.jsp)
function doDeleteMapFile()
{
	$('#mapFileDialog').val(null);
	$('#mapFileName').val(null);
	$('#mapFileFullName').val(null);
}

// 시험 삭제함수(manage_schedule.jsp)
function doScheduleDelete() 
{
	var checkBox = $("input[name=examCheck]:checked");
	var checkValueArr = [];
	var isSuccess = false;

	if (checkBox.length < 1) {
		alert("삭제할 시험일정이 한개도 선택되지 않았습니다.");
		return false;
	}

	if (confirm("해당 시험일정을 삭제 하시겠습니까?")) {
		checkBox.each(function() {
			var checkValue = $(this).val();
			checkValueArr.push(checkValue);
		});

		$.ajax({
			url: "/ExamDeleteByAjax",
			type: "GET",
			async: false,
			dataType: 'json',
			data: {
				examCheck: checkValueArr
			},
			success: function(data) {
				console.log("AJAX Request 성공");
				isSuccess = data.isSuccess;
			},
			error: function() {
				console.log("AJAX Request 실패");
				isSuccess = false;
			}
		});
	}
	else {
		isSuccess = false;
	}

	if (isSuccess) alert("시험일정 삭제를 완료했습니다.");
	else alert("시험일정 삭제를 실패했습니다.");

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