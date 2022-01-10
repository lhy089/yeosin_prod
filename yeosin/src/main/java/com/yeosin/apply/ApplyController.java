package com.yeosin.apply;

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

@Controller
public class ApplyController {
	
	@RequestMapping(value="/apply", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView apply(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
		
//		List<ApplyDto> result = new ArrayList<>();
		//to-do : 표시할 내용 가져오기
		String result = null;
		if(result != null) {
			mav.addObject("result", result);
		}else {
			mav.addObject("result", false);
		};
		mav.setViewName("redirect:/www/apply/apply.jsp");
		return mav;
	}
	
	@RequestMapping(value="/accept", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView accept(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
		
//		List<ApplyDto> result = new ArrayList<>();
		//to-do : 표시할 내용 가져오기
		String result = null;
		if(result != null) {
			mav.addObject("result", result);
		}else {
			mav.addObject("result", "");
		};
		mav.setViewName("redirect:/www/apply/accept.jsp");
		return mav;
	}
	
	@RequestMapping(value="/ticket", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView ticket(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
		
//		List<ApplyDto> result = new ArrayList<>();
		//to-do : 표시할 내용 가져오기
		String result = null;
		if(result != null) {
			mav.addObject("result", result);
		}else {
			mav.addObject("result", "");
		};
		mav.setViewName("redirect:/www/apply/ticket.jsp");
		return mav;
	}
	
	@RequestMapping(value="/refund", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView refund(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
	
		mav.addObject("result", "");
		mav.setViewName("redirect:/www/apply/refund.jsp");
		return mav;
	}
	
	@RequestMapping(value="/accept_view", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView accept_view(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
	
		mav.addObject("result", "");
		mav.setViewName("redirect:/www/apply/accept_view.jsp");
		return mav;
	}
	
	@RequestMapping(value="/ticket_view", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView ticket_view(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
	
		mav.addObject("result", "");
		mav.setViewName("redirect:/www/apply/ticket_view.jsp");
		return mav;
	}
	
	@RequestMapping(value="/cancel", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView cancel(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
	
		mav.addObject("result", "");
		mav.setViewName("redirect:/www/apply/cancel.jsp");
		return mav;
	}
}
