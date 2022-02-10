$(document).ready(function(){

   $('#btn_doJoinCertification').click(function(){
   		var isNotChecked = $("input:checkbox[name=check]:not(:checked)").length;
   		if(isNotChecked > 0) {
   			alert("회원가입 약관 동의가 필요합니다.")
   		}else {
   	   		$(".provision").hide();
   	   		$(".certification").show();
   	   		$(".entry").hide();
   	   		$(".finish").hide();

   	   /*
   	 	setTimeout(function(){
   	 		
   	 	alert("미구현 된 기능입니다. 확인을 클릭하시면 다음 화면으로 넘어갑니다.");
    	   
	   		$(".intro").hide();
   		$(".provision").hide();
   		$(".certification").hide();
   		$(".entry").show();
   		$(".finish").hide();
   	 		
   	 	}, 1000);
   	 	*/
   	   		
   		}
   });
   
   $('#btn_doJoinFinish').click(function(){
	   doJoin();
   });
   
   $('#userId').blur(function(){ 
	   isCheckIdFalse();
  });
   
   $('#btn_chkIdDupl').click(function(){ 
		if(!isDuplication()) return false;
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

function isCheckIdFalse() {
	isCheckId = 0;
}

function doJoin() {
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
			userName : $('#userName').val(), 
			userId:$('#userId').val(),
			password:$('#userPwd').val(),
			callNumber:callNumber,
			phoneNumber:phoneNumber,
			emailAddress:$('#emailAddress').val(),
			isReceiveSms:$("#isReceiveSms").is(":checked") ? "Y" : "N",
			isReceiveEmail:$("#isReceiveEmail").is(":checked") ? "Y" : "N"
			};
	$.ajax({
        type: "POST",
        url: "/doJoin",
        data: joinData,
        sendDataType : 'string',
        success: function(data) {
            if(data != null) {
            	var userInfo = JSON.parse(data)[0];
            	alert("회원가입이 완료 되었습니다.")
            	
            	$("#userInfo_name").text(userInfo.userName);
   	   			$("#userInfo_id").text(userInfo.userId);
   	   			$("#userInfo_callNumber").text(userInfo.callNumber);
   	   			$("#userInfo_phoneNumber").append(userInfo.phoneNumber);
   	   			$("#userInfo_emailAddress").append(userInfo.emailAddress);
   	   			if(userInfo.isReceiveSms=="Y") $("#userInfo_isReceiveSms").prop("checked","checked");
   	   			if(userInfo.isReceiveEmail=="Y") $("#userInfo_isReceiveEmail").prop("checked","checked");
            	
            	$(".intro").hide();
   	   			$(".provision").hide();
   	   			$(".certification").hide();
   	   			$(".entry").hide();
   	   			$(".finish").show();
   	   			
            }else {
            	$('#id').val("");
        		$('#pwd').val("");
        		$('#id').focus();
            	alert("회원 정보가 존재하지 않거나 일치하지 않습니다.")
            }
        }
      });
}

function isValid() {
	var userName = $('#userName').val();
	var userId = $('#userId').val();
	var userPwd = $('#userPwd').val();
	var userPwd2 = $('#userPwd2').val();
	var phoneNumber = $("#phoneNumber").val();
	var phoneNumber2 = $("#phoneNumber2").val();
	var phoneNumber3 = $("#phoneNumber3").val();
	var emailAddress = $("#emailAddress").val();
	
	console.log("B");
	if (userName == '') {
		alert("이름 입력하세요.");
		$('#userName').focus();
		return false;
	}
	
	if (userId == '') {
		alert("아이디를 입력하세요.");
		$('#userId').focus();
		return false;
	}
	
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
	
	var nameReg = /^[가-힣a-zA-Z]+$/;
	if (!nameReg.test(userName)) {
		 alert("사용할 수 없는 이름 입니다.");
		$('#userName').val("");
		$('#userName').focus();
		return false;
	}
	
	var idReg = /^[a-z][a-z0-9]{5,19}$/g;
	var rst = idReg.test(userId);
	if (!rst) {
		 alert("사용할 수 없는 아이디 입니다.");
		$('#userId').val("");
		$('#userId').focus();
		return false;
	}
	
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
	
	if (userPwd != userPwd2) {
		 alert("비밀번호가 일치하지 않습니다.");
		$('#userPwd').val("");
		$('#userPwd2').val("");
		$('#userPwd').focus();
		return false;
	}

	if(isCheckId == 0) {
		alert("아이디 중복체크가 필요합니다.");
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

function isDuplication() {
	var userId = $("#userId").val();
	var idReg = /^[a-z][a-z0-9]{5,19}$/g;
	var rst = idReg.test(userId);
	if (!rst) {
		 alert("사용할 수 없는 아이디 입니다.");
		$('#userId').val("");
		$('#userId').focus();
		return false;
	}
	
	$.ajax({
        url: "/dupleId",
        type: "get",
        data: {userId : userId},
        success: function(data) {
            console.log("AJAX Request 성공");
            if(data=="Y") {
            	isCheckId = 0;
            	alert("중복된 아이디가 존재합니다.");
            }else {
            	isCheckId = 1;
            	alert("사용 가능한 아이디입니다.");
            }
        },
        error: function() {
           console.log("에러 발생");
           isCheckId=0;
        },
        complete: function(){
        }
    }); 
	
}
