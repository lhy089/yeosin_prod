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

function fnFindUserInfo(target, type){
	var userName = $("#userName").val();
	var userName2 = $("#userName2").val();
	var phoneNumber = $("#phoneNumber").val();
	var phoneNumber2 = $("#phoneNumber2").val();
	var phoneNumber3 = $("#phoneNumber3").val();
	var emailAddress = $("#emailAddress").val();
	
	var url = target=="id" ? "/sendUserId" : "/sendUserPwd";
	var data ={};
	
	if(type=="H") { // 핸드폰
		if(userName==null || userName=="") {
			alert("이름을 입력하세요.")
			$("#userName").focus()
			return false;
		}
		if(phoneNumber == '' || phoneNumber2 == '' || phoneNumber3 == ''){
			$("#phoneNumber").val("");
			$("#phoneNumber2").val("");
			$('#phoneNumber3').focus();
			alert("핸드폰 번호를 입력하세요.")
			$("#phoneNumber").focus();
			return false;
		}
		data={userName : userName, phoneNumber : phoneNumber+"-"+phoneNumber2+"-"+phoneNumber3, findType : type};
	}
	if(type=="M") { // 메일
		if(userName2==null || userName2=="") {
			alert("이름을 입력하세요.")
			return false;
		}
		if(emailAddress==null || emailAddress=="") {
			alert("이메일 주소를 입력하세요.")
			return false;
		}
		data={userName : userName2, emailAddress : emailAddress, findType : type};
	}

	$.ajax({
        type: "GET",
        url: url,
        sendDataType : 'string',
        data : data,
        success: function(data) {
        	if(data=="SUCCESS") {
        		if(target=="id") alert("인증수단으로 아이디를 전송 했습니다.");
        		else alert("인증수단으로 임시 비밀번호를 전송 했습니다.");
        	}else {
        		alert("일치하는 정보가 없습니다.");
        	}
        }
      });
}
