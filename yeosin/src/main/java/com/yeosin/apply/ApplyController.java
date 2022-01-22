package com.yeosin.apply;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.yeosin.prod.UserDto;

@Controller
public class ApplyController {
	
	@Autowired
	private ApplyService applyService;
	private String _userId = "hyong02";
	
	// 원서접수
	@RequestMapping(value="/apply", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView apply(HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		
		String loginId = (String)session.getAttribute("loginId");
		
		ModelAndView mav = new ModelAndView();
		
//		List<ApplyDto> applyList = new ArrayList<>();
//		applyList = applyService.getApplyList(loginId);
//		
//		mav.addObject("applyList", applyList);
		mav.setViewName("apply/apply");
		return mav;
	}
	
	// 원서확인 및 취소
	@RequestMapping(value="/accept", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView accept(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		
//		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
//		String loginId = userInfo.getUserId();
		String loginId = (String)session.getAttribute("loginId");
		
		ModelAndView mav = new ModelAndView();
		
		List<ApplyDto> applyList = new ArrayList<>();
		applyList = applyService.getApplyList(_userId); // TODO : UserId Session에서 가져올 수 있도록 수정예정
		
		if (applyList.size() == 0)
		{
			mav.addObject("applyList", "noData");
		}
		else 
		{
			mav.addObject("applyList", applyList);
		}
		mav.setViewName("apply/accept");
		return mav;
	}
	
	// 수험표 출력
	@RequestMapping(value="/ticket", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView ticket(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		
		String loginId = (String)session.getAttribute("loginId");
		
		ModelAndView mav = new ModelAndView();
		
		List<ApplyDto> applyList = new ArrayList<>();
		applyList = applyService.getApplyList(_userId); // TODO : UserId Session에서 가져올 수 있도록 수정예정
		
		if (applyList.size() == 0)
		{
			mav.addObject("applyList", "noData");
		}
		else 
		{
			mav.addObject("applyList", applyList);
		}
		mav.setViewName("apply/ticket");
		return mav;
	}
	
	// 원서확인 및 취소 상세현황
	@RequestMapping(value="/accept_view", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView accept_view(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		
		String loginId = (String)session.getAttribute("loginId");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", _userId);
		map.put("receiptId", "receipt001"); // TODO : 원서접수에서 가져올 수 있도록 수정예정
		
		ModelAndView mav = new ModelAndView();
		
		List<ApplyDto> list = new ArrayList<>();
		list = applyService.getDetailApplyList(map); // TODO : UserId Session에서 가져올 수 있도록 수정예정
		
		if (list.size() == 0)
		{
			mav.addObject("detailApplyList", "noData");
		}
		else 
		{
			mav.addObject("detailApplyList", list);
		}
		mav.setViewName("apply/accept_view");
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
