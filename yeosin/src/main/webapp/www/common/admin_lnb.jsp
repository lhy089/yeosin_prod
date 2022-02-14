<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="lnb">
  <p class="logoLnb"></p>
  <dl>
    <dt><a href="#none">회원관리</a></dt>
    <dd><a href="member_info.html">회원정보</a></dd>
    <dd><a href="member_course.html">교육수료정보</a></dd>
    <dd><a href="member_count.html">접속자 집계</a></dd>
  </dl>
  <dl>
    <dt><a href="#none">고사장설정</a></dt>
    <dd><a href="site_register.html">고사장 등록</a></dd>
    <dd><a href="site_list.html">고사장 리스트</a></dd>
  </dl>
  <dl>
    <dt><a href="#none">시험운영관리</a></dt>
    <dd><a href="manage_schedule.html">시험일정관리</a></dd>
    <dd><a href="manage_register.html">시험일정등록</a></dd>
    <dd><a href="manage_status_doc.html">원서접수현황</a></dd>
  </dl>
  <dl>
    <dt><a href="#none">성적관리</a></dt>
    <dd><a href="result_list.html">채점표리스트</a></dd>
    <dd><a href="result_manage.html">성적처리</a></dd>
    <!-- <dd><a href="#">성적조회</a></dd> -->
  </dl>
  <dl>
    <dt><a href="#none">게시물관리</a></dt>
    <dd><a href="board_notice.html">공지사항</a></dd>
    <dd><a href="board_library.html">시험 자료실</a></dd>
    <dd><a href="board_question.html">자주하는 질문</a></dd>
  </dl>
</nav>

<script>
  $(function(){
    /* 선택된 lnb */
    $('.lnb dd').click(function(){
      $('.lnb dd').removeClass('on');
      $(this).addClass('on');
    });

    /* 메뉴 펼치기 토글 */
    $('.lnb dt').click(function(){
      $(this).parent('dl').find('dd').slideToggle(300);
    });
  });
</script>
