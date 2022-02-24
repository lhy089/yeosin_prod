<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "com.yeosin.user.UserDto" %>
<script>
/* 고딕neo2,고딕neo3 font */
(function(d) {
  var config = {
    kitId: 'ndu0cee',
    scriptTimeout: 3000,
    async: true
  },
  h=d.documentElement,t=setTimeout(function(){h.className=h.className.replace(/\bwf-loading\b/g,"")+" wf-inactive";},config.scriptTimeout),tk=d.createElement("script"),f=false,s=d.getElementsByTagName("script")[0],a;h.className+=" wf-loading";tk.src='https://use.typekit.net/'+config.kitId+'.js';tk.async=true;tk.onload=tk.onreadystatechange=function(){a=this.readyState;if(f||a&&a!="complete"&&a!="loaded")return;f=true;clearTimeout(t);try{Typekit.load(config)}catch(e){}};s.parentNode.insertBefore(tk,s)
})(document);

var min ="";
var sec ="";

var initTime = 3600;
var sessionTime = '<%=session.getAttribute("sessionTime")%>';
var curTime = new Date().getTime();

var time = initTime - (( curTime - sessionTime)/1000);
var x = setInterval(function(){

     if(time < 0){
        clearInterval(x);
        
        $.ajax({
            url: "/manageLogout",
             type: "GET",
             async: false,
             success: function(data) 
            {
               console.log("AJAX Request 성공");
             },
             error: function() 
            {
                console.log("AJAX Request 실패");
             }
         }); 
     }
     else
     {
        min = Lpad(parseInt(time/60), 2); 
        sec = Lpad(parseInt(time%60), 2);
        
        $('#timer').text("[ 자동로그아웃 : " + min +":" + sec);         
        
        time--;
     }
}, 1000);

Lpad = function(str, len) {
    str = str + "";
    while (str.length < len) {
        str = "0" + str;
    }
    return str;
}

function refereshSession()
{
   $.ajax({
      url: "/manageRefreshSession",
       type: "GET",
       async: false,
       success: function(data) 
      {
         console.log("AJAX Request 성공");
         location.reload();
       },
       error: function() 
      {
          console.log("AJAX Request 실패");
       }
   });          
}
</script>
<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css" rel="stylesheet"> <!--나눔스퀘어 font-->
<link href="/www/inc/css/font.css" rel="stylesheet" type="text/css" media="screen">
<link href="/www/inc/css/common.css" rel="stylesheet" type="text/css" media="screen">
<script src="/www/inc/js/jquery-3.4.1.min.js"></script>
<link href="/www/inc/css/reset.css" rel="stylesheet" type="text/css" media="screen">
<link href="/www/inc/css/header_admin.css" rel="stylesheet" type="text/css" media="screen">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/applymanage.js?t=<%= new java.util.Date() %>"></script>
<!--header-->
<header>
  <div class="contentBox">
    <div class="leftSec">
      <p class="date"></p>
      <% UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
         if(userInfo == null)
            out.print("<h1><span>/span></h1>");
         else
            out.print("<h1><span>" + userInfo.getUserName() + "</span>님 환영합니다</h1>");
   %>
    </div>
    <div class="rightSec">
      <a href="/manageHome" class="home">HOME</a>
      <a href="/manageLogout" class="btn_header" id="btn_logout">로그아웃</a>
	  <span id ="timer" class="autoLogout"></span> 
      <div class="autoLogout">
         <a href="#none" onClick="refereshSession();" class="btn_header">연장</a>
         <span class="autoLogout">]</span> 
      </div>
    </div>
  </div>
</header>

<script>
	$(function(){
	  /* 오늘 날짜 출력 */
	  let today = new Date();
	  let year = today.getFullYear(); // 년도
	  let month = today.getMonth() + 1;  // 월
	  let date = today.getDate();  // 날짜
	
	  $('.date').text(year+'년 '+month+'월 '+date+'일');
	});
</script>