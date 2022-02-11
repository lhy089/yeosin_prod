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
		dataType : 'text',
        success: function(data) {
        	
            if(data == 'U' || data == 'S') {
         	   $(location).attr("href", "/www/main.jsp");
            }else if(data == 'C'){
            	alert("대출성 상품 판매대리 중개업자 등록자격 인증평가 사이트 운영 관리 서버가 변경되어 기존 회원들의 패스워드 재설정이 필요합니다.조금 불편하시더라도 비밀번호 찾기를 통해 패스워드 변경 후 로그인 부탁드립니다.");
               $(location).attr("href","/www/member/find_pwd.jsp")
            }
            else {
            	$('#id').val("");
        		$('#pwd').val("");
        		$('#id').focus();
            	alert("회원 정보가 존재하지 않거나 일치하지 않습니다.")
            }
        }
      });
	   
}