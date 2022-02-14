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

			List<ApplyDto> applyListByDocument	= applyManageService.getApplyListByDocument(parameterMap);

			mav.addObject("localList", localList);
			mav.addObject("subjectList", subjectList);
			mav.addObject("applyListByDocument", applyListByDocument);
			mav.setViewName("admin/manage_status_doc"); 
		}
		return mav;
	}

}
