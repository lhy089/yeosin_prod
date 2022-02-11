$(document).ready(function(){
   
    console.log("index.js");
   $('#btn_login').click(function(){
       console.log("btn_login clicked");
       location.href="member/login.jsp";
   });
   
   $('#btn_logout').click(function(){
	   $.ajax({
           url: "/logout",
           type: "post",
           success: function(data) {
               console.log("AJAX Request 성공");
               location.href="/index";
           },
           error: function() {
              console.log("에러 발생");
           },
           complete: function(){
           }
       }); 
   });
   
   $('#btn_logout2').click(function(){
	   $.ajax({
           url: "/logout",
           type: "post",
           success: function(data) {
               console.log("AJAX Request 성공");
               location.href="/index";
           },
           error: function() {
              console.log("에러 발생");
           },
           complete: function(){
           }
       }); 
   });
   
    $('#btn_simpleJoin').click(function(){
       console.log("btn_simpleJoin clicked");
       location.href="member/join.jsp";
   });
    
    $('.btn_change').click(function(){
        console.log("btn_change clicked");
        if($('#btn_logout').length > 0) {
        	location.href="/pwd";
        }
        else {
        	location.href="/www/member/login.jsp";
        }
        
    });

    $("#callSyncCertIdApi").click(function(){
    	$.ajax({
            url: "/callSyncCertIdApi",
            type: "post",
            data: {startDate:$("#startDate").val(),endDate:$("#endDate").val()},
            success: function(data) {
                console.log("AJAX Request 성공");
                alert("API 호출 건 수 : " + data);
                
            },
            error: function() {
               console.log("에러 발생");
            },
            complete: function(){
            }
        }); 
    });
   

     
});