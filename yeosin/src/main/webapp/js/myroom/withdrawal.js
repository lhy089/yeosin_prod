$(document).ready(function(){
	
	$('#btn_withdrawal').click(function(){
		doWithdrawal();
   });
	
});

function doWithdrawal() {
	if (!$('#agreeChk').is(':checked')) {
			alert("회원탈퇴 동의가 필요합니다.")
			$('#agreeChk').focus();
			return false;
	}
	
	$.ajax({
        type: "POST",
        url: "/doWithdrawal",
        data: {userId:$('#userId').val()},
        sendDataType : 'string',
        success: function(data) {
        	if(data > 0){
        		alert("탈퇴를 완료 했습니다.");
        		location.href="/www/main.jsp";
        	}else {
        		
        	}
        }
	});
}