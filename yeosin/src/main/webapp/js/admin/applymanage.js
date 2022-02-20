$(document).ready(function(){
   
   $('#btn_logout').click(function(){
      $.ajax({
           url: "/adminLogout",
           type: "get",
           success: function(data) {
               console.log("AJAX Request 성공");
               location.href="www/admin/login.jsp";
           },
           error: function() {
              console.log("에러 발생");
           },
           complete: function(){
           }
       }); 
   });

});

// 좌석배치 체크함수(manage_status_site.jsp)
function IsCheckedSeatConfirm() 
{
	var checkBox = $("input[name=examZoneCheck]:checked");
			
	if (checkBox.length < 1)
	{
		alert("좌석배치할 고사장이 한개도 선택되지 않았습니다.");
		return false;
	}
}