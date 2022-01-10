package com.yeosin.member;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@Autowired	
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public void login(UserDto user, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		Boolean result = userService.getLoginUser(user);
		if(result) {
			String sessionId = user.getUserId();
			session.setAttribute("loginId",sessionId);
		}else {

		}
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView join(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
	
		mav.addObject("result", "");
		mav.setViewName("redirect:/www/member/join.jsp");
		return mav;
	}
	@RequestMapping(value="/find_id", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView find_id(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
	
		mav.addObject("result", "");
		mav.setViewName("redirect:/www/member/find_id.jsp");
		return mav;
	}
	
	@RequestMapping(value="/find_pwd", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView find_pwd(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
	
		mav.addObject("result", "");
		mav.setViewName("redirect:/www/member/find_pwd.jsp");
		return mav;
	}
}
