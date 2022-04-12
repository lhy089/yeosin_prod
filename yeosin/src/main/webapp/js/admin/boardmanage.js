function fileSize()
{
	 var size = 0;
	
	 var node = $('#file')[0];
	 if(node.files.length != 0)
		 size = node.files[0].size;
	
	 return size;
}

// 팝업 수정 or 저장함수
function doPopupSave() 
{
   	var title = $("#title").val();
   	var startDate = $("#startDate").val();
   	var endDate = $("#endDate").val();
   	var fileName = $("#fileName").val();
   	var isVisible = $("#isVisible").val();
   	var isSuccess = false;
   	 
   	if (title == null || title == "null" || title == "")
   	{
		alert("팝업 제목은 필수입력입니다.");
      	return isSuccess;
   	}
   	else if (startDate == null || startDate == "null" || startDate == "")
   	{
      	alert("시작날짜는 필수입력입니다.");
      	return isSuccess;
   	}
   	else if (endDate == null || endDate == "null" || endDate == "")
   	{
      	alert("종료날짜는 필수입력입니다.");
      	return isSuccess;
   	}
   	else if (fileName == null || fileName == "null" || fileName == "")
   	{
      	alert("팝업첨부용 이지미 파일은 필수입력입니다.");
      	return isSuccess;
   	}
   	else if (isVisible == null || isVisible == "null" || isVisible == "")
   	{
      	alert("팝업 노출여부는 필수입력입니다.");
      	return isSuccess;
   	}     
   
   	if (confirm("해당 내용으로 저장하시겠습니까?")) 
   	{            
		var form = $("#popupSaveForm")[0];
		var formData = new FormData(form);
      	$.ajax({
			type: "post",
           	enctype: 'multipart/form-data', 
         	url: "/PopupSaveByAjax",
         	data: formData,
         	dataType: 'json',
        	processData : false, 
           	contentType : false,
           	async: false,
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
   
   	if (isSuccess) alert("저장이 완료되었습니다.");
   	else alert("저장이 실패되었습니다.");
   
   	return isSuccess;
}

// 팝업 삭제함수
function doPopupDelete() 
{
	var checkBox = $("input[name=popupCheck]:checked");	
	var checkValueArr = [];		
	var isSuccess = false;
			
	if (checkBox.length < 1)
	{
		alert("삭제할 팝업이 한개도 선택되지 않았습니다.");
		return false;
	}
	
	if (confirm("해당 팝업을 삭제 하시겠습니까?")) 
	{
		checkBox.each(function(){
			var checkValue = $(this).val();
			checkValueArr.push(checkValue);	
		});
		
		$.ajax({
			url: "/PopupDeleteByAjax",
	        type: "GET",
	        async: false,
	        dataType : 'json',
	        data: {
					popupCheck : checkValueArr
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
	
	if (isSuccess) alert("팝업 삭제를 완료했습니다.");
	else alert("팝업 삭제를 실패했습니다.");
	
	return isSuccess;
}