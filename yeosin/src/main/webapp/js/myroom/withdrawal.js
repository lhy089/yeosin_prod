$(document).ready(function(){
	
	$('#btn_withdrawal').click(function(){
		 console.log("btn_change clicked");
		 
		 if (!$('#agreeChk').is(':checked')) {
				alert("회원탈퇴 동의가 필요합니다.")
				$('#agreeChk').focus();
				return false;
		}
		 
	        if($('#btn_logout').length > 0) {
	        	location.href="/pwdForWithdrawal";
	        }
	        else {
	        	location.href="/www/member/login.jsp";
	        }		
   });
	
	$('#btn_doCheckPwdForWithdrawal').click(function(){  debugger;
		doWithdrawal();
   
	});
	
});

function doWithdrawal() {
	
	$.ajax({
        type: "POST",
        url: "/doWithdrawal",
        data: {password : $("#password").val()},
        sendDataType : 'string',
        success: function(data) {
        	if(data > 0){
        		alert("탈퇴를 완료 했습니다.");
        		location.href="/www/main.jsp";
        	}else {
        		alert("탈퇴에 실패 했습니다.");
        	}
        }
	});
}