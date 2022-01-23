$(document).ready(function(){
	
    console.log("index.js");
   $('#btn_doCheckPwd').click(function(){  debugger;
   		doCheckedPwd();
       
   });
});

var isCheckId = 0;


function doCheckedPwd() {
	var password = $("#password").val();
	if(!password) {
		alert("비밀번호를 입력하세요.");
		return false;
	} 
	$.ajax({
        type: "POST",
        url: "/doCheckPwd",
        data: {password : $("#password").val()},
        sendDataType : 'string',
        success: function(data) {
        	if(data) {
        		location.href = "/change";
        	}else {
        		alert("비밀번호가 일치하지 않습니다.");
        	}
        }
      });
}

function isValid() {
	var userPwd = $('#userPwd').val();
	var userPwd2 = $('#userPwd2').val();
	
	if (userPwd == '') {
		alert("비밀번호를 입력하세요.");
		$('#userPwd').focus();
		return false;
	}
	
	if (userPwd2 == '') {
		alert("비밀번호를 입력하세요.");
		$('#userPwd2').focus();
		return false;
	}
	
//	6~20자의 영문 대소문자와 숫자, 특수문자를 사용할 수 있으며, 최소 2종류이상을 조합해야 합니다.
//	허용 특수문자 { } [ ] ( ) / | ? ! . * ~ ‘ ^ - _ + # $ % =
	
//	var pwdReg1 = /^(?=.*[a-zA-Z])(?=.*[0-9]).{5,19}$/; //영문,숫자
//	var pwdReg2 = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{5,19}$/; //영문,특수문자
//	var pwdReg3 = /^(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{5,19}$/; //특수문자, 숫자
//	
//	var pwdReg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\d~!@#$%^&*()+|=]{8,16}$;
	
	var pwdReg = /^[a-z]+[a-z0-9]{5,19}$/g;
	if (!pwdReg.test(userPwd)) {
		 alert("사용할 수 없는 비밀번호 입니다.");
		$('#userPwd').val("");
		$('#userPwd2').val("");
		$('#userPwd').focus();
		return false;
	}
	
	if (userPwd != userPwd2) {
		 alert("비밀번호가 일치하지 않습니다.");
		$('#userPwd').val("");
		$('#userPwd2').val("");
		$('#userPwd').focus();
		return false;
	}
	return true;

}
