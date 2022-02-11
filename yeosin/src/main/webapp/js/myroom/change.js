$(document).ready(function(){
	
    console.log("index.js");
   $('#btn_save').click(function(){
   		doModifyUser();
   });
   
   $('#emailAddress').blur(function(){
   		if(!$(this).val()) {
   			$("#isReceiveEmail").prop("checked",false);
   			$("#isReceiveEmail").prop("disabled",true);
   		}else {
   			$("#isReceiveEmail").prop("disabled",false);
   		}
   });
   
   $('#phoneNumber2').blur(function(){
		if(!$(this).val() || !$('#phoneNumber3').val()) {
			$("#isReceiveSms").prop("checked",false);
			$("#isReceiveSms").prop("disabled",true);
		}else {
			$("#isReceiveSms").prop("disabled",false);
		}
   });
   $('#phoneNumber3').blur(function(){
	if(!$(this).val() || !$('#phoneNumber2').val()) {
		$("#isReceiveSms").prop("checked",false);
		$("#isReceiveSms").prop("disabled",true);
	}else {
		$("#isReceiveSms").prop("disabled",false);
	}
   });
});

var isCheckId = 0;


function doModifyUser() {
	if(!isValid()) return false;
	var callNumber = "";
	var phoneNumber = "";
	if($("#callNumber2").val() && $("#callNumber3").val()) {
		callNumber = $("#callNumber option:selected").val()+"-"+$("#callNumber2").val()+"-"+$("#callNumber3").val();
	}
	if($("#phoneNumber2").val() && $("#phoneNumber3").val()) {
		phoneNumber = $("#phoneNumber option:selected").val()+"-"+$("#phoneNumber2").val()+"-"+$("#phoneNumber3").val();
	}
	var joinData = {
			userId:$('#userId').text(),
			password:$('#userPwd').val(),
			callNumber:callNumber,
			phoneNumber:phoneNumber,
			emailAddress:$('#emailAddress').val(),
			isReceiveSms:$("#isReceiveSms").is(":checked") ? "Y" : "N",
			isReceiveEmail:$("#isReceiveEmail").is(":checked") ? "Y" : "N",
			birthDate:$("#birthDate").val(),
			gender:$("#gender").val()
			};
	$.ajax({
        type: "POST",
        url: "/doChange",
        data: joinData,
        sendDataType : 'string',
        success: function(data) {
        	if(data > 0){
        		alert("회원정보를 수정 했습니다.");
        		location.href="/www/main.jsp";
        	}else {
        		
        	}
        }
      });
}

function isValid() {
	if(!isCert) {
		alert("본인인증이 필요합니다.");
		return false;
	}
	var userName = $('#userName').val();
	var userId = $('#userId').val();
	var userPwd = $('#userPwd').val();
	var userPwd2 = $('#userPwd2').val();
	var phoneNumber = $("#phoneNumber").val();
	var phoneNumber2 = $("#phoneNumber2").val();
	var phoneNumber3 = $("#phoneNumber3").val();
	var emailAddress = $("#emailAddress").val();
	
	if(userPwd) {
	
//	6~20자의 영문 대소문자와 숫자, 특수문자를 사용할 수 있으며, 최소 2종류이상을 조합해야 합니다.
//	허용 특수문자 { } [ ] ( ) / | ? ! . * ~ ‘ ^ - _ + # $ % =
	
	 var regExp1 = /[0-9]/;
	 var regExp2 = /[a-zA-Z]/;
	 var regExp3 = /[{}[\]()/|?!.*~‘^-_+#$%=]/;
	 
	 if(userPwd.length<6 || userPwd.length>20) { // 6자 이상 20자 이하
		 alert("사용할 수 없는 비밀번호 입니다.");
		 $('#userPwd').val("");
		 $('#userPwd2').val("");
		 $('#userPwd').focus();
		 return false;
	 }
	 
	 if(!((regExp1.test(userPwd)&&regExp2.test(userPwd)) || (regExp1.test(userPwd)&&regExp3.test(userPwd)) || (regExp2.test(userPwd)&&regExp3.test(userPwd)))) {
		 // 숫자,영문 대소문자 포함 이거나 숫자,특수문자 포함 이거나 영문 대소문자, 특수문자 포함이 아니면 (최소 두 가지 문자 포함이 아닐 경우)
		 alert("사용할 수 없는 비밀번호 입니다.");
		 $('#userPwd').val("");
		 $('#userPwd2').val("");
		 $('#userPwd').focus();
		 return false;
	 }
	}
	
	if (userPwd != userPwd2) {
		 alert("비밀번호가 일치하지 않습니다.");
		$('#userPwd').val("");
		$('#userPwd2').val("");
		$('#userPwd').focus();
		return false;
	}
	
	if(phoneNumber == '' || phoneNumber2 == '' || phoneNumber3 == ''){
		$("#phoneNumber2").val("");
		$("#phoneNumber3").val("");
		$('#phoneNumber2').focus();
		alert("핸드폰 번호를 입력하세요.")
		return false;
	}
	
	if(emailAddress == ''){
		$('#emailAddress').focus();
		alert("이메일을 입력하세요.")
		return false;
	}
	
	return true;

}
