package com.yeosin.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.yeosin.user.EduCompletionDto;
import com.yeosin.user.EduCompletionDtoPageMaker;
import com.yeosin.user.EduCompletionHisDto;
import com.yeosin.user.EduCompletionHisDtoPageMaker;
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
	public ModelAndView member_info(UserDto userDto, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception 
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
			// 페이징 데이터 준비(페이지당 데이터 목록수)
			int pagePerNum = 30;
			if (request.getParameterMap().containsKey("onePageDataCountCondition")) 
			{
				if (request.getParameter("onePageDataCountCondition") != null 
						&& !request.getParameter("onePageDataCountCondition").trim().isEmpty())
				{
					pagePerNum = Integer.parseInt(request.getParameter("onePageDataCountCondition"));
				}
			}
			userDto.setPerPageNum(pagePerNum);
			   
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("searchEmailType", request.getParameter("searchEmailType"));
			parameterMap.put("searchSMSType", request.getParameter("searchSMSType"));
			parameterMap.put("searchWord", request.getParameter("searchWord"));
			parameterMap.put("generalUser", request.getParameter("generalUser") == null? "N" : request.getParameter("generalUser"));
			parameterMap.put("dormancyUser", request.getParameter("dormancyUser") == null? "N" : request.getParameter("dormancyUser"));
			parameterMap.put("secessionUser", request.getParameter("secessionUser") == null? "N" : request.getParameter("secessionUser"));
			parameterMap.put("userName", userDto.getUserName());
			parameterMap.put("userId", userDto.getUserId());
			parameterMap.put("phoneNumber", userDto.getPhoneNumber());
			parameterMap.put("emailAddress", userDto.getEmailAddress());
			parameterMap.put("pageStart", userDto.getPageStart());
			parameterMap.put("perPageNum", userDto.getPerPageNum());
			
			UserPageMaker pageMaker = new UserPageMaker();
			pageMaker.setUserDto(userDto);
			pageMaker.setTotalCount(userManageService.countUserListTotal(parameterMap));
			
			List<UserDto> userList = userManageService.getUserInfo(parameterMap);
			
			for(int i = 0; i < userList.size(); i++)
			{
				UserDto tempUserDto = userList.get(i);
				if("휴면".equals(tempUserDto.getUserStatus()))
				{
					tempUserDto.setJoinDate(tempUserDto.getJoinDate() + " (" + tempUserDto.getDormantAccountDate() + ")");
				}
			}
			
			mav.addObject("generalUser", request.getParameter("generalUser"));
			mav.addObject("dormancyUser", request.getParameter("dormancyUser"));
			mav.addObject("secessionUser", request.getParameter("secessionUser"));
			mav.addObject("searchEmailType", request.getParameter("searchEmailType"));
			mav.addObject("searchSMSType", request.getParameter("searchSMSType"));
			mav.addObject("searchWord", request.getParameter("searchWord"));
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("userDto", userDto);
			mav.addObject("userList", userList);
			mav.addObject("isAlert",false);
			mav.addObject("pageCondition", pagePerNum);
			mav.setViewName("admin/member_info");
		}

		return mav;
	}
	
	//회원수정화면
	@RequestMapping(value="/member_modify", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView member_modify(UserDto userDto, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception 
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
			userDto =  userManageService.getUserInfoByUserId(request.getParameter("memberCheck"));
			 
			if(userDto.getUserStatus().equals("C") && userDto.getLastConnectDate() != null)
			{
				Calendar cal = Calendar.getInstance();
				DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
				cal.setTime(df.parse(userDto.getLastConnectDate()));
				cal.add(Calendar.YEAR, 1);
				userDto.setDormantAccountDate(df.format(cal.getTime()));
			}
			else if(userDto.getUserStatus().equals("D"))
			{
				userDto.setDormantAccountDate(userDto.getSecessionDate());
			}
			else
			{
				userDto.setDormantAccountDate("");
			}
			
			mav.addObject("userDto", userDto);
			mav.setViewName("admin/member_modify");
		}

		return mav;
	}
	
	//회원수정하기
	@RequestMapping(value="/member_modify_action", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView member_modify_action(UserDto userDto, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception 
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
			if(userDto.getIsReceiveEmail()==null) userDto.setIsReceiveEmail("N");
			if(userDto.getIsReceiveSms()==null) userDto.setIsReceiveSms("N");	
			if(userDto.getPassword()!=null && !"".equals(userDto.getPassword())) userDto.setPassword(EncryptUtils.getSha256(userDto.getPassword()));
			// 사용자 정보 조회
			int cnt = userService.updateUserInfo(userDto);
			

			
			String pageInit = request.getParameter("onePageDataCountCondition")==null ? "200" : request.getParameter("onePageDataCountCondition");			
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("generalUser", request.getParameter("generalUser") == null? "Y" : request.getParameter("generalUser"));
			parameterMap.put("dormancyUser", request.getParameter("dormancyUser") == null? "Y" : request.getParameter("dormancyUser"));
			parameterMap.put("secessionUser", request.getParameter("secessionUser") == null? "Y" : request.getParameter("secessionUser"));
			parameterMap.put("userName", userDto.getUserName());
			parameterMap.put("userId", userDto.getUserId());
			parameterMap.put("phoneNumber", userDto.getPhoneNumber());
			parameterMap.put("emailAddress", userDto.getEmailAddress());
			parameterMap.put("pageStart", userDto.getPageStart());
			parameterMap.put("perPageNum", userDto.getPerPageNum());
			
			userDto.setPerPageNum(Integer.parseInt(pageInit));
			
			UserPageMaker pageMaker = new UserPageMaker();
			pageMaker.setUserDto(userDto);
			pageMaker.setTotalCount(userManageService.countUserListTotal(parameterMap));
			
			List<UserDto> userList = userManageService.getUserInfo(parameterMap);
			
			for(int i = 0; i < userList.size(); i++)
			{
				UserDto tempUserDto = userList.get(i);
				if("휴면".equals(tempUserDto.getUserStatus()))
				{
					tempUserDto.setJoinDate(tempUserDto.getJoinDate() + " (" + tempUserDto.getDormantAccountDate() + ")");
				}
			}
			
			mav.addObject("generalUser", request.getParameter("generalUser") == null? "Y" : request.getParameter("generalUser"));
			mav.addObject("dormancyUser", request.getParameter("dormancyUser") == null? "Y" : request.getParameter("dormancyUser"));
			mav.addObject("secessionUser", request.getParameter("secessionUser") == null? "Y" : request.getParameter("secessionUser"));
			mav.addObject("searchEmailType", request.getParameter("searchEmailType")==null? "A" : request.getParameter("searchEmailType"));
			mav.addObject("searchSMSType", request.getParameter("searchSMSType")==null? "A" : request.getParameter("searchSMSType"));
			mav.addObject("searchWord", request.getParameter("searchWord")==null? "" : request.getParameter("searchWord"));
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("userDto", userDto);
			mav.addObject("userList", userList);
			mav.addObject("isAlert",false);
			mav.addObject("pageCondition", pageInit);
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
			mav.setViewName("admin/login");
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
	
	// 교육수료정보
	@RequestMapping(value="/memberCourseMng", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView memberCourseMng(EduCompletionHisDto eduCompletionHisDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	    	// 페이징 데이터 준비(페이지당 데이터 목록수)
	    	int pagePerNum = 30;
	    	if (request.getParameterMap().containsKey("onePageDataCountCondition")) 
	    	{
	    		if (request.getParameter("onePageDataCountCondition") != null 
	    				&& !request.getParameter("onePageDataCountCondition").trim().isEmpty())
	    		{
	    			pagePerNum = Integer.parseInt(request.getParameter("onePageDataCountCondition"));
	    		}
	    	}
	    	eduCompletionHisDto.setPerPageNum(pagePerNum);
			   
	    	Map<String, Object> parameterMap = new HashMap<String, Object>();
	    	parameterMap.put("pageStart", eduCompletionHisDto.getPageStart());
	    	parameterMap.put("perPageNum", eduCompletionHisDto.getPerPageNum());
	    	
	    	List<EduCompletionHisDto> eduCompletionHisList = new ArrayList<EduCompletionHisDto>();
			eduCompletionHisList = userManageService.getEduCompletionHisList(parameterMap);
			
			// 페이징 하기위한 데이터
			EduCompletionHisDtoPageMaker pageMaker = new EduCompletionHisDtoPageMaker();
			pageMaker.setEduCompletionHisDto(eduCompletionHisDto);
			pageMaker.setTotalCount(userManageService.getEduCompletionHisListCount(parameterMap));
			
			mav.addObject("eduCompletionHisList", eduCompletionHisList);
			mav.addObject("pageCondition", request.getParameter("onePageDataCountCondition"));
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("eduCompletionHisDto", eduCompletionHisDto);
			mav.setViewName("admin/member_course");
	    }
		return mav;
	}
	
	@RequestMapping(value="/memberCourseViewMng", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView memberCourseViewMng(EduCompletionDto eduCompletionDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	    	// 페이징 데이터 준비(페이지당 데이터 목록수)
	    	int pagePerNum = 30;
	    	if (request.getParameterMap().containsKey("onePageDataCountCondition")) 
	    	{
	    		if (request.getParameter("onePageDataCountCondition") != null 
	    				&& !request.getParameter("onePageDataCountCondition").trim().isEmpty())
	    		{
	    			pagePerNum = Integer.parseInt(request.getParameter("onePageDataCountCondition"));
	    		}
	    	}
	    	eduCompletionDto.setPerPageNum(pagePerNum);
	    	
	    	Map<String, Object> parameterMap = new HashMap<String, Object>();
	    	parameterMap.put("apiSyncId", request.getParameter("apiSyncId"));
	    	parameterMap.put("searchWord", eduCompletionDto.getSearchWord());
	    	parameterMap.put("gender", eduCompletionDto.getGender());
	    	parameterMap.put("subject", eduCompletionDto.getSubject());
	    	parameterMap.put("pageStart", eduCompletionDto.getPageStart());
	    	parameterMap.put("perPageNum", eduCompletionDto.getPerPageNum());
	    	
	    	List<EduCompletionDto> eduCompletionList = new ArrayList<EduCompletionDto>();
			eduCompletionList = userManageService.getEduCompletionList(parameterMap);
			
			// 페이징 하기위한 데이터
			EduCompletionDtoPageMaker pageMaker = new EduCompletionDtoPageMaker();
			pageMaker.setEduCompletionDto(eduCompletionDto);
			pageMaker.setTotalCount(userManageService.getEduCompletionListCount(parameterMap));
			String pageInit = (request.getParameter("onePageDataCountCondition") == null) ? "30" : request.getParameter("onePageDataCountCondition");
			mav.addObject("eduCompletionInfo", eduCompletionDto);
			mav.addObject("eduCompletionList", eduCompletionList);
			mav.addObject("pageCondition", pageInit);
			mav.addObject("pageMaker", pageMaker);
			mav.setViewName("admin/member_course_view");
	    }
		
		return mav;
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
//		List<ApplyDto> list = new ArrayList<>();
		String result = "";
		//엑셀 파일이 xls일때와 xlsx일때 서비스 라우팅
		String excelType = req.getParameter("excelType");
		String examId = req.getParameter("examId");

		MultipartFile file = mult.getFile("excel_"+examId);
		try {
			if(file.getSize()==0) {
				result="NOT FOUND";
			}else {
				if(excelType.equals("xlsx")){
					result = userManageService.xlsxExcelReader(file, examId);
					if("FAILED".equals(result)) result = userManageService.xlsExcelReader(file, examId);
				}else if(excelType.equals("xls")){
					result = userManageService.xlsExcelReader(file, examId);
					if("FAILED".equals(result)) result = userManageService.xlsxExcelReader(file, examId);
				}

			}
			mav.addObject("uploadSuccess", result);
			return mav;
		}catch(Exception e) {
			result = "FAILED";
			mav.addObject("uploadSuccess", result);
			return mav;
		}
	}
	
	@RequestMapping(value="/loginForceByMng", method=RequestMethod.POST)
	@ResponseBody
	public String loginForceByMng(String userId, HttpSession session, HttpServletResponse response) throws Exception {
		String result = "";
		response.setCharacterEncoding("UTF-8");
		UserDto user = new UserDto();
		user.setUserId(userId);
		// 사용자 정보 조회
		UserDto userInfo = userService.getLoginUserInfo(user);

		if(userInfo != null) {
			result = "U";
			session.setAttribute("loginUserInfo",userInfo);
		}else {
		}
		return result;
	}
}
