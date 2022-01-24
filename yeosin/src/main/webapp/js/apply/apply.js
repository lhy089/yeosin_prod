$(document).ready(function(){

   $('#btn_completed').click(function(){
       doCompleted();
   });
   
   $("#id").on("keyup",function(key){
       if(key.keyCode==13) {
    	   doCompleted();
       }
   });
   
   $("#pwd").on("keyup",function(key){
       if(key.keyCode==13) {
    	   doCompleted();
       }
   });

    
});

function doCompleted() {
	var eduNum = $('#eduNum').val();

	if (eduNum == '') {
		alert("교육수료증번호는 필수입력입니다.");
		$('#eduNum').focus();
		return false;
	}
	
	$.ajax({
		url: "/apply4",
        type: "GET",
        data: {eduNum : eduNum},
        success: function(data) {
			console.log("AJAX Request 성공");
            if (data=="Y") {
         	   $(location).attr("href", "/www/apply/apply4.jsp");
            } else {
            	alert("등록되어있는 교육수료증번호가 아닙니다.");
            }
        }
	});
	   
}