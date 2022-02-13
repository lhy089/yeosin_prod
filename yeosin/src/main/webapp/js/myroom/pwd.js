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
        	if(data == "true") {
        		location.href = "/change";
        	}else {
        		alert("비밀번호가 일치하지 않습니다.");
        	}
        }
      });
}
