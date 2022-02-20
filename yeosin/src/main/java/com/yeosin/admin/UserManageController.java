package com.yeosin.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
}
