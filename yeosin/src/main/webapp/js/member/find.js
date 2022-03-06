$(document).ready(function(){
	   
   $('#btn_ok').click(function(){
	   var userName = $("#userName").val();
		
		var birthDate = $("#year").val();
		birthDate += '-';
		birthDate += $("#month").val();
		birthDate += '-';
		birthDate += $("#day").val();
		
		var gender = $('input[name="gender"]:checked').val();
		
		
		var inputData = {
				userName:userName,
				birthDate:birthDate,
				gender:gender};

		$.ajax({
	        type: "GET",
	        url: "/find_id_ok",
	        data: inputData,
	        sendDataType : 'string',
	        success: function(data) {
	        	if(data == "null"){
	        		var url = "/popup?type=noData&data="+data;
		    		var options = 'top=10, left=10, width=400, height=200, status=no, menubar=no, toolbar=no, resizable=no';
		    		var popup = window.open(url, "아이디/비밀번호 찾기", options);
		    		popup.focus();
	        	}else if(data == "duple"){
	        		var url = "/popup?type=duple&data="+data;
		    		var options = 'top=10, left=10, width=400, height=200, status=no, menubar=no, toolbar=no, resizable=no';
		    		var popup = window.open(url, "아이디/비밀번호 찾기", options);
		    		popup.focus();
	        	}else {
	        		var url = "/popup?type=id&data="+data;
		    		var options = 'top=10, left=10, width=400, height=200, status=no, menubar=no, toolbar=no, resizable=no';
		    		var popup = window.open(url, "아이디/비밀번호 찾기", options);
		    		popup.focus();
	        	}
	        },
	      });
   });  
});
