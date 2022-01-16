$(document).ready(function(){
	
    console.log("index.js");
   $('#btn_doJoinProvision').click(function(){  debugger;
   		$(".intro").hide();
   		$(".provision").show();
   		$(".certification").hide();
   		$(".entry").hide();
   		$(".finish").hide();
       
   });
   $('#btn_doJoinCertification').click(function(){
   		var isNotChecked = $("input:checkbox[name=check]:not(:checked)").length;
   		if(isNotChecked > 1) {
   			alert("회원가입 약관 동의가 필요합니다.")
   		}else {
   			$(".intro").hide();
   	   		$(".provision").hide();
   	   		$(".certification").hide();
   	   		$(".entry").show();
   	   		$(".finish").hide();
   		}
   });
   
   $('#btn_doJoinFinish').click(function(){
		if(!isValid()) return false;
   });
   
   $('#btn_chkIdDupl').click(function(){ 
		if(!isDuplication()) return false;
   });
    
});

var isCheckId = 0;

function isValid() {
	if ($('#userName').val() == '') {
		alert("이름 입력하세요.");
		$('#userName').focus();
		return false;
	}
	
	var userId = $('#userId').val();
	if (userId == '') {
		alert("아이디를 입력하세요.");
		$('#userId').focus();
		return false;
	}
	
	if ($('#userPwd').val() == '') {
		alert("비밀번호를 입력하세요.");
		$('#userPwd').focus();
		return false;
	}
	
	if ($('#userPwd2').val() == '') {
		alert("비밀번호를 입력하세요.");
		$('#userPwd2').focus();
		return false;
	}
	
	var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
	if (!idReg.test(userId)) {
		 alert("아이디는 영문자로 시작하는 6~20자 영문자 또는 숫자이어야 합니다.");
		$('#userId').val("");
		$('#userId').focus();
		return false;
	}
	
	if(isCheckId == 0) {
		alert("아이디 중복체크가 필요합니다.");
	}
	
	//비밀번호 정규식
}

function isDuplication() {
	var userId = $("#userId").val();
	
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