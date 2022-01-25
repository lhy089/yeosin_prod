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
	var examId = $('#examId').val();

	if (eduNum == '') {
		alert("교육수료증번호는 필수입력입니다.");
		$('#eduNum').focus();
		return false;
	}
	
	$.ajax({
		url: "/isCompleteEdu",
        type: "GET",
        data: {
				eduNum : eduNum,
		   		examId : examId
			  },
        success: function(data) 
		{
			console.log("AJAX Request 성공");
			if (data.isPassEdu == "Y") 
			{
				var url = "/www/apply/apply4.jsp";
			    $(location).attr("href", url);
				//$("#examId").text(data.examId); // TODO : apply4.jsp 페이지로 시험 ID 넘겨줘야함
			} 
			else 
			{
				alert("등록되어있는 교육수료증번호가 아닙니다.");
			}
        }
	});
	   
}