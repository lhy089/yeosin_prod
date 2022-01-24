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

import com.yeosin.user.UserDto;
import com.yeosin.user.UserService;

@Controller
public class ApplyController {
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private UserService userService;
	
	// 원서접수
	@RequestMapping(value="/apply", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView apply(HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		ModelAndView mav = new ModelAndView();
		List<ExamDto> examList = new ArrayList<>();
		// 로그인 정보 있을 때
		if(userInfo != null) {
			// 시험 리스트 ??
			examList = applyService.getExamList();
			mav.addObject("examListCnt", examList.size());
			mav.addObject("examList", examList);
			mav.setViewName("apply/apply");
		}else { // 로그인 정보 없을 때
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		
		return mav;
	}
	
	// 원서접수2
	@RequestMapping(value="/apply2", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView apply2(@RequestParam("examId") String examId, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		//examId 받고 넘기기
		mav.addObject("examId", examId);
		mav.setViewName("apply/apply2");
		return mav;
	}
	
	
	// 원서접수3
	@RequestMapping(value="/apply3", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView apply3(@RequestParam("examId") String examId, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
			
		userInfo = userService.getLoginUserInfo(userInfo);
		ExamDto examInfo = applyService.getExamInfo(examId);
		
		mav.addObject("examInfo", examInfo);
		mav.addObject("userInfo", userInfo);
		mav.setViewName("apply/apply3");
		return mav;
	}

	// 원서접수4(로그인한 유저의 교육증 수료여부 체크)
	@RequestMapping(value="/apply4", method=RequestMethod.GET)
	@ResponseBody
	public void apply4(@RequestParam("eduNum") String eduNum, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		userInfo = userService.getLoginUserInfo(userInfo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userInfo.getUserId());
		map.put("eduNum", eduNum);
		String isPassEdu = applyService.getIsCompleteEdu(map);

		response.getWriter().print(isPassEdu);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	// 원서확인 및 취소
	@RequestMapping(value="/accept", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView accept(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		ModelAndView mav = new ModelAndView();
		List<ApplyDto> applyList = new ArrayList<>();

		if(userInfo != null) {
			applyList = applyService.getApplyList(userInfo.getUserId());
			mav.addObject("applyList", applyList);
			mav.setViewName("apply/accept");
		}else {
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		return mav;
	}
	
	// 수험표 출력
	@RequestMapping(value="/ticket", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView ticket(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		List<ApplyDto> applyList = new ArrayList<>();
		
		
		if(userInfo != null) {
			applyList = applyService.getApplyList(userInfo.getUserId());
			mav.addObject("applyListCnt", applyList.size());
			mav.addObject("applyList", applyList);
			mav.setViewName("apply/ticket");
		}else {
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		
		return mav;
	}
	
	// 원서확인 및 취소 상세현황
	@RequestMapping(value="/accept_view", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView accept_view(@RequestParam("receiptId") String receiptId, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userInfo.getUserId());
		map.put("receiptId", receiptId); // TODO : 원서접수에서 가져올 수 있도록 수정예정
		
		ModelAndView mav = new ModelAndView();
		
		ApplyDto applyInfo = applyService.getDetailApplyInfo(map); // TODO : UserId Session에서 가져올 수 있도록 수정예정
		
		mav.addObject("applyInfo", applyInfo);
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
	public ModelAndView ticket_view(@RequestParam("receiptId") String receiptId, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userInfo.getUserId());
		map.put("receiptId", receiptId);
		
		ApplyDto applyInfo = applyService.getDetailApplyInfo(map); // TODO : UserId Session에서 가져올 수 있도록 수정예정
		
		mav.addObject("applyInfo", applyInfo);
		mav.setViewName("apply/ticket_view");
		return mav;
	}
	
	@RequestMapping(value="/ticket_print", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView ticket_print(@RequestParam("receiptId") String receiptId, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userInfo.getUserId());
		map.put("receiptId", receiptId);
		
		ApplyDto applyInfo = applyService.getDetailApplyInfo(map); // TODO : UserId Session에서 가져올 수 있도록 수정예정
		
		mav.addObject("applyInfo", applyInfo);
		mav.setViewName("apply/ticket_print");
		return mav;
	}
	
	@RequestMapping(value="/cancel", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView cancel(@RequestParam("receiptId") String receiptId, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
	
		mav.addObject("result", "");
		mav.setViewName("redirect:/www/apply/cancel.jsp");
		return mav;
	}
	
	@RequestMapping(value="/result", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView result(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		List<ApplyDto> resultList = new ArrayList<>();
		if(userInfo != null) {
			resultList = applyService.getExamResult(userInfo.getUserId());
			mav.addObject("resultListCnt", resultList.size());
			mav.addObject("resultList", resultList);
			mav.setViewName("state/result");
		}else {
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/certificate", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView certificate(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
	
//		mav.addObject("result", "");
		mav.setViewName("state/certificate");
		return mav;
	}
}
