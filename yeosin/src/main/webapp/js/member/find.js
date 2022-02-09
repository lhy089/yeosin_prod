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
	        	if(data != "null"){
	        		alert("회원님의 아이디는 " + data + " 입니다.");
	        		$(location).attr("href", "/www/member/login.jsp");
	        	}else {
	        		alert("입력정보가 맞지 않습니다.");
	        	}
	        },
	      });
   });  
});
