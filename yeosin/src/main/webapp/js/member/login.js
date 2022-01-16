$(document).ready(function(){
   
    console.log("index.js");
   $('#btn_doLogin').click(function(){
       doLogin();
   });
   
   $("#id").on("keyup",function(key){
       if(key.keyCode==13) {
    	   doLogin();
       }
   });
   
   $("#pwd").on("keyup",function(key){
       if(key.keyCode==13) {
    	   doLogin();
       }
   });

    
});

function doLogin() {
	var userId = $('#id').val();
	var password = $('#pwd').val();

	if (userId == '') {
		alert("아이디를 입력하세요.");
		$('#id').focus();
		$('#pwd').val("");
		return false;
	}
	if (password == '') {
		alert("비밀번호를 입력 하세요.");
		$('#pwd').focus();
		return false;
	}
	
	var data = {userId : userId, password:password};
	$.ajax({
        type: "POST",
        url: "/login",
        data: data,
        sendDataType : 'string',
		dataType : 'json',
        success: function(data) {
            if(data) {
         	   $(location).attr("href", "/www/index.jsp");
            }else {
            	$('#id').val("");
        		$('#pwd').val("");
        		$('#id').focus();
            	alert("회원 정보가 존재하지 않거나 일치하지 않습니다.")
            }
        }
      });
	   
}