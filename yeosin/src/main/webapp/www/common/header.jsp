<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js?t=<%= new java.util.Date() %>"></script>
  
 <c:set var="root" value="${pageContext.request.contextPath}"/>
<link href="/www/inc/css/reset.css" rel="stylesheet" type="text/css" media="screen">
<link href="/www/inc/css/common.css?a=2" rel="stylesheet" type="text/css" media="screen">
<link href="/www/inc/css/header_footer.css?t=<%= new java.util.Date() %>" rel="stylesheet" type="text/css" media="screen">
<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css" rel="stylesheet"> <!--나눔스퀘어 font-->
<script>
/* 고딕neo2,고딕neo3 font */
(function(d) {
  var config = {
    kitId: 'ndu0cee',
    scriptTimeout: 3000,
    async: true
  },
  h=d.documentElement,t=setTimeout(function(){h.className=h.className.replace(/\bwf-loading\b/g,"")+" wf-inactive";},config.scriptTimeout),tk=d.createElement("script"),f=false,s=d.getElementsByTagName("script")[0],a;h.className+=" wf-loading";tk.src='https://use.typekit.net/'+config.kitId+'.js';tk.async=true;tk.onload=tk.onreadystatechange=function(){a=this.readyState;if(f||a&&a!="complete"&&a!="loaded")return;f=true;clearTimeout(t);try{Typekit.load(config)}catch(e){}};s.parentNode.insertBefore(tk,s)
  var loginUserInfo = "${loginUserInfo.userId}";
})(document);
</script>

<!--header-->
<header class="pc">
  <div class="contentBox">
    <a href="/www/main.jsp" class="home"><img src="/www/inc/img/common/logo.png" alt="여신금융협회"></a>
    <!-- 로그인전 노출 버튼// -->
<!-- 로그인세션 사용 -->
<!-- 세션관련은 모든 페이지 일괄 적용되도록 추가예정-->
    <c:choose>
    <c:when test="${loginUserInfo.userId != null}">
    <div class="btnWrap">
    	<a onclick="return false;" id="btn_logout" name="btn_logout">로그아웃</a>
		<c:choose>
      		<c:when test="${loginUserInfo.userStatus == 'S'}">
<!--       			<a href="/manageHome" id="goManageHome">관리자페이지</a> -->
      			<a href="/www/manage/syncCert.jsp" id="goSyncCertIdMng">수료번호API호출</a>
<!--       			<a href="/manage_status_doc">접수자 리스트</a> -->
      		</c:when>
      	</c:choose>
    </div>
    </c:when>
    <c:otherwise>
    <div class="btnWrap">
      <a href="/www/member/login.jsp" id="btn_login">로그인</a>
      <a href="/join1">회원가입</a>
      </div>
    </c:otherwise>
    </c:choose>
    
    <!-- //로그인전 노출 버튼 -->
    <h1>대출성 상품 판매대리 &middot; 중개업자 등록 자격인증 평가</h1>
  </div>
</header>

<!-- gnb// -->
<div class="gnb pc">
  <div class="contentBox">
    <ul class="depth1">
      <li><a href="/apply">원서접수</a></li>
      <li><a href="/receipt">응시현황</a></li>
      <li><a href="/examGuide">평가안내</a></li>
      <li><a href="/notice?boardType=1">알림마당</a></li>
      <li><a onclick="return false;" class="btn_change">회원정보수정</a></li>
    </ul>
  </div>
  <div class="depth2">
    <div class="contentBox">
      <ul>
        <li><a href="/apply">원서접수 신청</a></li>
        <li><a href="/accept">원서접수 확인 및 취소</a></li>
        <li><a href="/ticket">수험표 출력</a></li>
        <li><a href="/refund">환불 안내</a></li>
      </ul>
      <ul>
        <li><a href="/receipt">원서접수 현황</a></li>
        <li><a href="/result">응시결과</a></li>
        <!-- <li><a href="/certificate">자격인증서발급</a></li> -->
      </ul>
      <ul>
        <li><a href="/examGuide">평가 소개</a></li>
        <li><a href="/registerGuide">응시 안내</a></li>
        <li><a href="/library?boardType=3">시험자료실</a></li>
      </ul>
      <ul>
        <li><a href="/notice?boardType=1">공지사항</a></li>
        <li><a href="/question?boardType=2">자주하는 질문</a></li>
      </ul>
      <ul>
        <li><a onclick="return false;" class="btn_change">회원 정보 수정</a></li>
        <li><a href="/withdrawal">회원 탈퇴</a></li>
      </ul>
    </div>
  </div>
</div>
<!-- //gnb -->

<!-- mo// -->
<header class="mo">
  <p class="home"><a href="/www/main.jsp"><img src="/www/inc/img/common/logo.png" alt="여신금융협회"></a></p>
  <a href="#" class="btn_menu"></a>
</header>
<div class="menuWrap mo">
  <div class="menu">
    <dl>
      <dt>원서접수<p>+</p></dt>
      <dd>
        <p><a href="/apply">원서접수 신청</a></p>
        <p><a href="/accept">원서접수 확인 및 취소</a></p>
        <p><a href="/ticket">수험표 출력</a></p>
        <p><a href="/refund">환불 안내</a></p>
      </dd>
    </dl>
    <dl>
      <dt>응시현황<p>+</p></dt>
      <dd>
        <p><a href="/accept">원서접수 현황</a></p>
        <p><a href="/result">응시결과</a></p>
        <!-- <p><a href="/certificate">자격인증서발급</a></p> -->
      </dd>
    </dl>
    <dl>
      <dt>평가안내<p>+</p></dt>
      <dd>
        <p><a href="/examGuide">평가 소개</a></p>
        <p><a href="/registerGuide">응시 안내</a></p>
        <p><a href="/library?boardType=3">시험자료실</a></p>
      </dd>
    </dl>
    <dl>
      <dt>알림마당<p>+</p></dt>
      <dd>
        <p><a href="/notice?boardType=1">공지사항</a></p>
        <p><a href="/question?boardType=2">자주하는 질문</a></p>
      </dd>
    </dl>
    <dl>
    	<dt>회원정보수정<p>+</p></dt>
      	<dd>
        	<p><a onclick="return false;" class="btn_change">회원정보수정</a></p>
        	<p><a href="/withdrawal">회원 탈퇴</a></p>
      	</dd>
    </dl>
  </div>
  <c:choose>
    <c:when test="${loginUserInfo.userId != null}">
    <div class="btnWrap">
    	<a onclick="return false;" id="btn_logout2" name="btn_logout">로그아웃</a>
    	</div>
    </c:when>
    <c:otherwise>
    <div class="btnWrap">
      	<a href="/www/member/login.jsp" id="btn_login">로그인</a>
      	<a href="/join1">회원가입</a>
    </div>
    </c:otherwise>
    </c:choose>
</div>
<!-- //mo -->

<script>
  $(function(){
    /* gnb */
    // 2depth 숨기기 디폴트
    $('.gnb .depth2').hide();

    // 2depth 보이기(마우스 오버 시)
    $('.depth1 li,.depth2').mouseover(function(e) {
      $('.depth2').stop().slideDown(500);
    });
    $('.depth1 li,.depth2').mouseout(function(e) {
      $('.depth2').stop().slideUp(500);
    });

    // 2depth의 해당 1depth active
    $('.depth2 ul a').hover(function(e){
      var thisTab = $(this).parents('ul').index()+1;
      //alert(thisTab);
      $('.depth1 li a').removeClass('on');
      $('.depth1 li:nth-child('+ thisTab +') a').addClass('on');
    },function(e){
      $('.depth1 li a').removeClass('on');
    });

    /* (모바일) 메뉴 */
    $('.btn_menu').click(function(){
      if($(this).hasClass('open')){ //메뉴닫기
        $(this).removeClass('open');
        $('.menuWrap').fadeOut(300);
      } else {  //메뉴열기
        $(this).addClass('open');
        $('.menuWrap').fadeIn(300);
      }
    });
    $('.menuWrap dl>dt').click(function(e) {
      if ($(this).attr('class') == 'menuOn_dt') {
        $(this).removeClass('menuOn_dt');
        $(this).children('p').html('+');
        $(this).next('dd').slideUp('fast');
      } else {
        $('.menuWrap dl>dt').removeClass('menuOn_dt');
        $('.menuWrap dl>dt>p').html('+');
        $(this).addClass('menuOn_dt');
        $(this).children('p').html('-');
        $('.menuWrap dl>dd').slideUp('fast');
        $(this).next('dd').slideDown('fast');
      }
    });

  });
</script>

