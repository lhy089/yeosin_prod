<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--footer-->
<footer>
  <div class="contentBox">
    <div class="logo">
      <p><img src="/www/inc/img/common/logo_white.png" alt="여신금융협회"></p>
    </div>
    <div class="corporate">
      <div>
        <ul>
          <li><a href="#">HOME</a></li>
          <li><a href="/www/common/law_individual.html" target="_blank">개인정보처리방침</a></li>
          <li><a href="/www/common/law_use.html" target="_blank">이용약관</a></li>
          <li><a href="#none" class="btn_siteMap">사이트맵</a></li>
        </ul>
        서울특별시 중구 다동길 43(다동 70번지) 한외빌딩 12~13층<span class="mo"></span> 여신금융협회 (04521)<br/>
        상호명 : (사)한국여신전문금융업협회 <span class="mo"></span> 사업자등록번호 : 202-82-05723 &nbsp;대표자 : 김주현<br/>
        <span class="copyright">COPYRIGHT© THE CREDIT FINANCE ASSOCIATION ALL RIGHTS RESERVED</span>
      </div>
    </div>
  </div>
  <div class="siteMapWrap">
    <h1>사이트 맵</h1>
    <ul>
      <li>
        <dl>
          <dt><a href="#">원서접수</a></dt>
          <dd><a href="/apply">원서접수 신청</a></dd>
          <dd><a href="/accept">원서접수 확인 및 취소</a></dd>
          <dd><a href="/ticket">수험표 출력</a></dd>
          <dd><a href="/refund">환불 안내</a></dd>
        </dl>
      </li>
      <li>
        <dl>
          <dt><a href="#">응시현황</a></dt>
          <dd><a href="/receipt">원서접수 현황</a></dd>
          <dd><a href="/result">응시결과</a></dd>
        </dl>
      </li>
      <li>
        <dl>
          <dt><a href="#">평가안내</a></dt>
          <dd><a href="/examGuide">평가 소개</a></dd>
          <dd><a href="/registerGuide">응시 안내</a></dd>
          <dd><a href="/library?boardType=3">시험자료실</a></dd>
        </dl>
      </li>
    </ul>
    <ul>
      <li>
        <dl>
          <dt><a href="#">알림마당</a></dt>
          <dd><a href="/notice?boardType=1">공지사항</a></dd>
          <dd><a href="/question?boardType=2">자주하는 질문</a></dd>
        </dl>
      </li>
      <li>
        <dl>
          <dt><a href="#">회원정보</a></dt>
          <dd><a onclick="return false;" class="btn_change">회원정보수정</a></dd>
          <dd><a href="/withdrawal">회원 탈퇴</a></dd>
        </dl>
      </li>
      <li>
        <dl>
      <c:choose>
    	<c:when test="${loginUserInfo.userId != null}">
       		<dt class="gray"><a onclick="return false;" name="btn_logout" id="btn_logout">로그아웃</a></dt>
    	</c:when>
    	<c:otherwise>
    		<dt class="gray"><a href="/join">회원가입</a></dt>
          	<dt class="gray"><a href="/www/member/login.jsp" id="btn_login">로그인</a></dt>
    	</c:otherwise>
   		</c:choose>
          
          <dt class="gray mt70"><a href="/www/common/law_use.html" target="_blank">이용약관</a></dt>
          <dt class="gray"><a href="/www/common/law_individual.html" target="_blank">개인정보처리방침</a></dt>
        </dl>
      </li>
    </ul>
  </div>
</footer>

<script>
  $(function(){
    /* 사이트 맵 */
    var footerH = $('footer').height();
    $('.siteMapWrap').css('bottom',footerH);
    $('.btn_siteMap').click(function(){
      $('.siteMapWrap').slideToggle(500);
    });
  });
</script>
