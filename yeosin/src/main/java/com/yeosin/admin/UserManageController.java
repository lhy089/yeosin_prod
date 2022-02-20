package com.yeosin.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.yeosin.user.EduCompletionDto;
import com.yeosin.user.EduCompletionHisDto;
import com.yeosin.util.ExcelUtil;


import com.yeosin.user.UserDto;

@Controller
public class UserManageController {

	@Autowired
	private UserManageService userManageService;

	// 원서접수(접수가능한 시험 리스트 View)
	@RequestMapping(value="/member_info", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView member_info(UserDto userDto, HttpSession session, HttpServletResponse response) throws Exception 
	{		
		response.setCharacterEncoding("UTF-8");
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		ModelAndView mav = new ModelAndView();

		// 로그인 정보 있을 때
		if (userInfo != null) 
		{
			List<UserDto> userList = userManageService.getUserInfo(userDto);

			mav.addObject("userDto", userDto);
			mav.addObject("userList", userList);
			mav.addObject("isAlert",false);
			mav.setViewName("admin/member_info");
		} 
		// 로그인 정보 없을 때
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("admin/login");
		}

		return mav;
	}
	
	@RequestMapping(value="/manageHome", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView manageHome(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/home");
		return mav;
	}
	
	@RequestMapping(value="/memberCourseMng", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView memberCourseMng(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		
		List<EduCompletionHisDto> eduCompletionHisList = new ArrayList<EduCompletionHisDto>();
		eduCompletionHisList = userManageService.getEduCompletionHisList();
		
		mav.addObject("eduCompletionHisList", eduCompletionHisList);
		mav.setViewName("admin/member_course");
		return mav;
	}
	
	@RequestMapping(value="/memberCourseViewMng", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView memberCourseViewMng(EduCompletionDto eduCompletionInfo, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		
		List<EduCompletionDto> eduCompletionList = new ArrayList<EduCompletionDto>();
		eduCompletionList = userManageService.getEduCompletionList(eduCompletionInfo);
		
		mav.addObject("eduCompletionInfo", eduCompletionInfo);
		mav.addObject("eduCompletionList", eduCompletionList);
		mav.setViewName("admin/member_course_view");
		return mav;
	}
	
	
	@RequestMapping(value="/excelDownload", method=RequestMethod.GET)
	@ResponseBody
	public void excelDownloadForMemberCourseView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String column = request.getParameter("columns") == null ? "" : request.getParameter("columns");
			String data = request.getParameter("data") == null ? "" : request.getParameter("data");
			String fileName = request.getParameter("fileName");
			HSSFWorkbook wb = ExcelUtil.excelDownloadByDetailList(column, data);
			ExcelUtil.downLoadFile(wb, fileName, request, response);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
