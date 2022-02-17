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