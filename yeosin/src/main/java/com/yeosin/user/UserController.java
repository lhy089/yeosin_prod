package com.yeosin.user;

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

import com.google.gson.Gson;
import com.yeosin.util.EncryptUtils;

@Controller
public class UserController {
	
	@Autowired	
	private UserService userService;
	
	// 로그인 처리
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public void login(UserDto user, HttpSession session, HttpServletResponse response) throws Exception {
		Boolean result = false;
		response.setCharacterEncoding("UTF-8");
		user.setPassword(EncryptUtils.getSha256(user.getPassword()));
		// 사용자 정보 조회
		UserDto userInfo = userService.getLoginUserInfo(user);
		
		if(userInfo != null) {
			// 세션추가
			session.setAttribute("loginUserInfo",userInfo);
			result = true;
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
		mav.setViewName("member/join");
		return mav;
	}
	@RequestMapping(value="/find_id", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView find_id(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
	
		mav.addObject("result", "");
		mav.setViewName("member/find_id");
		return mav;
	}
	
	@RequestMapping(value="/find_pwd", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView find_pwd(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
	
		mav.addObject("result", "");
		mav.setViewName("member/find_pwd");
		return mav;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView logout(HttpSession session, HttpServletResponse response) throws Exception {
		Boolean result = false;
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		session.invalidate(); // 세션 초기화
		mav.addObject("result", "");
		mav.setViewName("index");
		return mav;
	}
	
	// id 중복체크
	@RequestMapping(value="/dupleId", method=RequestMethod.GET)
	@ResponseBody
	public void dupleId(String userId, HttpSession session, HttpServletResponse response) throws Exception {
		String result = userService.getLoginUser(userId);
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
	}

	// 회원가입 처리
	@RequestMapping(value="/doJoin", method=RequestMethod.POST)
	@ResponseBody
	public void doJoin(UserDto user, HttpSession session, HttpServletResponse response) throws Exception {
		List<Object> userResult = new ArrayList<>();
		response.setCharacterEncoding("UTF-8");
		user.setPassword(EncryptUtils.getSha256(user.getPassword()));
		// 사용자 정보 조회
		int cnt = userService.insertUserInfo(user);
		if(cnt > 0) userResult.add(userService.getLoginUserInfo(user));

		new Gson().toJson(userResult,response.getWriter());
	}
	
	@RequestMapping(value="/pwd", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView pwd(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
	
		mav.addObject("result", "");
		mav.setViewName("myroom/pwd");
		return mav;
	}
	
	// 
	@RequestMapping(value="/doCheckPwd", method=RequestMethod.POST)
	@ResponseBody
	public void doCheckPwd(UserDto user, HttpSession session, HttpServletResponse response) throws Exception {
		boolean result = false;
		response.setCharacterEncoding("UTF-8");
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		userInfo.setPassword(EncryptUtils.getSha256(user.getPassword()));

		userInfo = userService.getLoginUserInfo(userInfo);
		if(userInfo != null ) {
			result = true;
		}
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	// 회원정보 수정
	@RequestMapping(value="/change", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView change(UserDto user, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
		userInfo = userService.getLoginUserInfo(userInfo);
	
		mav.addObject("userInfo", userInfo);
		mav.setViewName("myroom/change");
		return mav;
	}
	
	// 회원정보 수정
		@RequestMapping(value="/doChange", method=RequestMethod.POST)
		@ResponseBody
		public void doChange(UserDto user, HttpSession session, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			if(user.getPassword()!=null && !"".equals(user.getPassword())) user.setPassword(EncryptUtils.getSha256(user.getPassword()));
			// 사용자 정보 조회
			int cnt = userService.updateUserInfo(user);
//			if(cnt > 0) userResult.add(userService.getLoginUserInfo(user));
			response.getWriter().print(cnt);
			response.getWriter().flush();
			response.getWriter().close();
//			new Gson().toJson(userResult,response.getWriter());
		}

}
