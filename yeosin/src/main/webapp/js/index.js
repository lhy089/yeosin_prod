$(document).ready(function(){
   
    console.log("index.js");
   $('#btn_login').click(function(){
       console.log("btn_login clicked");
       location.href="member/login.jsp";
   });
   
    $('#btn_simpleJoin').click(function(){
       console.log("btn_simpleJoin clicked");
       location.href="member/join.jsp";
   });
   

     
});