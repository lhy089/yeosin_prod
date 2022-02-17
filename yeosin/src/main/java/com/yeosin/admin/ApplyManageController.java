package com.yeosin.admin;

import com.yeosin.apply.*;
import com.yeosin.board.*;
import com.yeosin.user.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yeosin.user.UserDto;
import com.yeosin.user.UserService;

@Controller
public class ApplyManageController {
   
   @Autowired
   private ApplyManageService applyManageService;
   
   // 원서접수 리스트 조회(원서별)
   @RequestMapping(value="/manage_status_doc", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView ApplyListByDocument(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
   {      
      response.setCharacterEncoding("UTF-8");
      ModelAndView mav = new ModelAndView();
      
      UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
      
      if (userInfo == null) 
      {
         mav.addObject("isAlert", true);
         mav.setViewName("member/login");
      }
      else if (!"S".equals(userInfo.getUserStatus())) 
      {
         mav.addObject("isAlertNoAuth", true);
         mav.setViewName("main");      
      }
      else
      {
         List<ExamZoneDto> localList = applyManageService.getConditionLocalList();
         List<SubjectDto> subjectList = applyManageService.getConditionSubjectList();

         Map<String, Object> parameterMap = new HashMap<String, Object>();
         parameterMap.put("textCondition", request.getParameter("textCondition"));
         parameterMap.put("localCondition", request.getParameter("localCondition"));
         parameterMap.put("subjectCondition", request.getParameter("subjectCondition"));

         List<ApplyDto> applyListByDocument   = applyManageService.getApplyListByDocument(parameterMap);

         mav.addObject("localList", localList);
         mav.addObject("subjectList", subjectList);
         mav.addObject("applyListByDocument", applyListByDocument);
         mav.setViewName("admin/manage_status_doc"); 
      }
      return mav;
   }

   //로그아웃
   @RequestMapping(value="/adminLogout", method=RequestMethod.GET)
   @ResponseBody
   public void logout(HttpSession session, HttpServletResponse response) throws Exception {
      response.setCharacterEncoding("UTF-8");
      session.invalidate(); // 세션 초기화;
      response.getWriter().print(true);
      response.getWriter().flush();
      response.getWriter().close();
   }
   
   //회원정보
   @RequestMapping(value="/memberInfo", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView memberInfo()  
   {
      ModelAndView mav = new ModelAndView();      
      mav.setViewName("admin/member_info");
      return mav;
   }
   
   //교육수료정보
   @RequestMapping(value="/memberCourse", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView memberCourse()  
   {
      ModelAndView mav = new ModelAndView();      
      mav.setViewName("admin/member_course");
      return mav;
   }
   
   //접속자 집계
   @RequestMapping(value="/memberCount", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView memberCount()  
   {
      ModelAndView mav = new ModelAndView();      
      mav.setViewName("admin/member_count");
      return mav;
   }
   
   //고사장 등록
   @RequestMapping(value="/siteRegister", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView siteRegister()  
   {
      ModelAndView mav = new ModelAndView();      
      mav.setViewName("admin/site_register");
      return mav;
   }
   
   //고사장 리스트
   @RequestMapping(value="/siteList", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView siteList()  
   {
      ModelAndView mav = new ModelAndView();      
      mav.setViewName("admin/site_list");
      return mav;
   }
   
   //시험일정관리
   @RequestMapping(value="/manageSchedule", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView manageSchedule()  
   {
      ModelAndView mav = new ModelAndView();      
      mav.setViewName("admin/manage_schedule");
      return mav;
   }
   
   //시험일정등록
   @RequestMapping(value="/manageRegister", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView manageRegiste()  
   {
      ModelAndView mav = new ModelAndView();      
      mav.setViewName("admin/manage_register");
      return mav;
   }
   
   
   //채점표리스트
   @RequestMapping(value="/resultList", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView resultList()  
   {
      ModelAndView mav = new ModelAndView();      
      mav.setViewName("admin/result_list");
      return mav;
   }
   
   //성적처리
   @RequestMapping(value="/resultManage", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView resultManage()  
   {
      ModelAndView mav = new ModelAndView();      
      mav.setViewName("admin/result_manage");
      return mav;
   }
   
   //공지사항
   @RequestMapping(value="/boardNotice", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView boardNotice()  
   {
      ModelAndView mav = new ModelAndView();      
      mav.setViewName("admin/board_notice");
      return mav;
   }
   
   //시험 자료실
   @RequestMapping(value="/boardLibrary", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView boardLibrary()  
   {
      ModelAndView mav = new ModelAndView();      
      mav.setViewName("admin/board_library");
      return mav;
   }
   
   //자주하는 질문
   @RequestMapping(value="/boardQuestion", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView boardQuestion()  
   {
      ModelAndView mav = new ModelAndView();      
      mav.setViewName("admin/board_question");
      return mav;
   }
}