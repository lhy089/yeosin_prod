package com.yeosin.member;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
