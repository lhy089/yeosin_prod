package com.yeosin.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.yeosin.apply.ApplyDto;
import com.yeosin.apply.GradeDto;
import com.yeosin.board.PageMaker;
import com.yeosin.user.EduCompletionDto;
import com.yeosin.user.EduCompletionHisDto;
import com.yeosin.user.UserDto;
import com.yeosin.user.UserPageMaker;
import com.yeosin.user.UserService;
import com.yeosin.util.EncryptUtils;
import com.yeosin.util.ExcelUtil;

@Controller
public class UserManageController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserManageService userManageService;
	
	
	@RequestMapping(value="/manageLogout", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView manageLogout(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		session.invalidate(); // 세션 초기화;
		mav.setViewName("admin/login");
		return mav;
	}
	
	//회원정보
	@RequestMapping(value="/member_info", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView member_info(UserDto userDto, HttpSession session, HttpServletResponse response) throws Exception 
	{		
		response.setCharacterEncoding("UTF-8");
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		ModelAndView mav = new ModelAndView();

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
			userDto.setPerPageNum(200);
			
			UserPageMaker pageMaker = new UserPageMaker();
			pageMaker.setUserDto(userDto);
			pageMaker.setTotalCount(userManageService.countUserListTotal(userDto));
			
			List<UserDto> userList = userManageService.getUserInfo(userDto);

			mav.addObject("pageMaker", pageMaker);
			mav.addObject("userDto", userDto);
			mav.addObject("userList", userList);
			mav.addObject("isAlert",false);
			mav.setViewName("admin/member_info");
		}

		return mav;
	}
	
	@RequestMapping(value="/manageHome", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView manageHome(HttpSession session, HttpServletResponse response) throws Exception {
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
	    else {
			mav.setViewName("admin/home");
	    }
		return mav;
	}
	
	@RequestMapping(value="/memberCourseMng", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView memberCourseMng(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
	      
	    if (userInfo == null) 
	    {
	       mav.addObject("isAlert", true);
	       mav.setViewName("admin/login");
	    }
	    else if (!"S".equals(userInfo.getUserStatus())) 
	    {
	       mav.addObject("isAlertNoAuth", true);
	       mav.setViewName("main");      
	    }
	    else 
	    {
	    	List<EduCompletionHisDto> eduCompletionHisList = new ArrayList<EduCompletionHisDto>();
			eduCompletionHisList = userManageService.getEduCompletionHisList();
			
			mav.addObject("eduCompletionHisList", eduCompletionHisList);
			mav.setViewName("admin/member_course");
	    }
		return mav;
	}
	
	@RequestMapping(value="/memberCourseViewMng", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView memberCourseViewMng(EduCompletionDto eduCompletionInfo, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
	      
	    if (userInfo == null)
	    {
	       mav.addObject("isAlert", true);
	       mav.setViewName("admin/login");
	    }
	    else if (!"S".equals(userInfo.getUserStatus())) 
	    {
	       mav.addObject("isAlertNoAuth", true);
	       mav.setViewName("main");      
	    }
	    else 
	    {
	    	List<EduCompletionDto> eduCompletionList = new ArrayList<EduCompletionDto>();
			eduCompletionList = userManageService.getEduCompletionList(eduCompletionInfo);
			
			mav.addObject("eduCompletionInfo", eduCompletionInfo);
			mav.addObject("eduCompletionList", eduCompletionList);
			mav.setViewName("admin/member_course_view");
	    }
		
		return mav;
	}
	
	
	@RequestMapping(value="/excelDownload", method=RequestMethod.POST)
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
	
	// 로그인 처리
	@RequestMapping(value="/manageLogin", method=RequestMethod.POST)
	@ResponseBody
	public String manageLogin(UserDto user, HttpSession session, HttpServletResponse response) throws Exception {
		String result = "";
		response.setCharacterEncoding("UTF-8");
		user.setPassword(EncryptUtils.getSha256(user.getPassword()));
		// 사용자 정보 조회
		UserDto userInfo = userService.getLoginUserInfo(user);
		
		if(userInfo != null && userInfo.getUserStatus().equals("S")) {
			// 세션추가
			result = "S";
			session.setAttribute("loginUserInfo",userInfo);
            session.setMaxInactiveInterval(3600);
            session.setAttribute("sessionTime", System.currentTimeMillis());
		}
		return result;
	}
	
	// 세션연장
	@RequestMapping(value="/manageRefreshSession", method=RequestMethod.GET)
	@ResponseBody
	public void manageRefreshSession(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		session.setAttribute("sessionTime", System.currentTimeMillis());
	}
	

	
	@RequestMapping(value = "/excelUploadForGradeRegistration")
	public ModelAndView excelUpload(HttpServletRequest req){
		MultipartHttpServletRequest mult =  (MultipartHttpServletRequest)req;  
		ModelAndView mav = new ModelAndView("admin/result_manage");
		List<ApplyDto> list = new ArrayList<>();
		//엑셀 파일이 xls일때와 xlsx일때 서비스 라우팅
		String excelType = req.getParameter("excelType");
		String examId = req.getParameter("examId");
		if(excelType.equals("xlsx")){
			list = userManageService.xlsxExcelReader(mult, examId);
		}else if(excelType.equals("xls")){
			list = userManageService.xlsExcelReader(mult, examId);
		}
		mav.addObject("uploadSuccess", true);
		return mav;
	}
}
