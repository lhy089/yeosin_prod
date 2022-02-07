package com.yeosin.apply;

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
public class ApplyController {
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private UserService userService;
	
	// 원서접수(접수가능한 시험 리스트 View)
	@RequestMapping(value="/apply", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView ExamListByApplyView(HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		ModelAndView mav = new ModelAndView();
		List<ExamDto> examList = new ArrayList<>();
		
		// 로그인 정보 있을 때
		if (userInfo != null) 
		{
			examList = applyService.getExamList();
			mav.addObject("examListCnt", examList.size());
			mav.addObject("examList", examList);
			mav.setViewName("apply/apply");
		} 
		// 로그인 정보 없을 때
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		
		return mav;
	}
	
	// 원서접수2(환불규정에 대한 동의 View)
	@RequestMapping(value="/apply2", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView AgreeRefundByApplyView(@RequestParam("examId") String examId, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
		if (userInfo != null) 
		{
			mav.addObject("examId", examId);
			mav.setViewName("apply/apply2");
		}
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		
		return mav;
	}
		
	// 원서접수3(개인정보 및 교육수료정보 입력 View)
	@RequestMapping(value="/apply3", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView EduInfoByApplyView(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
			
		userInfo = userService.getLoginUserInfo(userInfo);
		
		if (userInfo != null) 
		{
			ExamDto examInfo = applyService.getExamInfo(request.getParameter("examId"));
			mav.addObject("examInfo", examInfo);
			mav.addObject("userInfo", userInfo);
			mav.setViewName("apply/apply3");
		}
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		
		return mav;
	}

	// 로그인한 유저의 교육증 수료여부 체크
	@RequestMapping(value="/isCompleteEdu", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> IsCompleteEdu(@RequestParam Map<String, Object> requestMap, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");	
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		userInfo = userService.getLoginUserInfo(userInfo);
		
		// JSP에서 넘어온 데이터
		Map<String, Object> paremterMap = new HashMap<String, Object>();
		paremterMap.put("userId", userInfo.getUserId());
		paremterMap.put("eduNum", requestMap.get("eduNum"));
		
		// AJAX로 넘겨줄 데이터
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("isPassEdu", applyService.getIsCompleteEdu(paremterMap));
		resultMap.put("examId", requestMap.get("examId"));
		
		return resultMap;
	}
	
	// 고사장 검색
	@RequestMapping(value="/SearchExamZone", method=RequestMethod.GET)
	@ResponseBody
	public List<ExamZoneDto> SearchExamZone(@RequestParam Map<String, Object> requestMap, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		
		// JSP에서 넘어온 데이터
		Map<String, Object> paremterMap = new HashMap<String, Object>();
		paremterMap.put("examId", requestMap.get("examId"));
		paremterMap.put("examZoneDetail", requestMap.get("examZoneDetail"));
		
		// AJAX로 넘겨줄 데이터
		List<ExamZoneDto> examZoneList = new ArrayList<ExamZoneDto>();
		examZoneList = applyService.getExamZoneList(paremterMap);
		
		return examZoneList;
	}
	
	// 원서접수4(고사장 및 시험영역 선택 View)
	@RequestMapping(value="/apply4", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView ExamZoneAndSubjectView(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
			
		userInfo = userService.getLoginUserInfo(userInfo);
		if (userInfo != null) 
		{
			ExamDto examInfo = applyService.getExamInfo(request.getParameter("examId"));
			List<ExamZoneDto> examZoneDetailList = applyService.getExamDetailList(examInfo.getExamId());
			mav.addObject("examZoneDtailList", examZoneDetailList);
			mav.addObject("examInfo", examInfo);
			mav.addObject("userInfo", userInfo);
			mav.setViewName("apply/apply4");
		}
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		
		return mav;
	}
	
	// 원서접수5(접수최종확인 및 결제직전 View)
	@RequestMapping(value="/apply5", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView ApplyResultCheckView(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");	
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
		if (userInfo != null) 
		{
			ExamDto examInfo = applyService.getExamInfo(request.getParameter("examId"));
			String examZoneName = applyService.getExamZoneName(request.getParameter("exmaZoneRadio"));
			mav.addObject("examZoneName", examZoneName);
			mav.addObject("examInfo", examInfo);
			mav.addObject("userInfo", userInfo);
			mav.setViewName("apply/apply5");
		}
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		
		return mav;
	}
	
	// 원서접수 확인 및 취소
	@RequestMapping(value="/accept", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView accept(ApplyDto applyDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		ModelAndView mav = new ModelAndView();

		if (userInfo != null) 
		{
			ApplyPageMaker pageMaker = new ApplyPageMaker();
			pageMaker.setApplyDto(applyDto);
			pageMaker.setTotalCount(applyService.countApplyListTotal(userInfo.getUserId()));
			applyDto.setUserId(userInfo.getUserId());
			
			List<ApplyDto> applyList = new ArrayList<>();
			applyList = applyService.getApplyList(applyDto);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("applyList", applyList);
			mav.addObject("applyDto", applyDto);
			mav.setViewName("apply/accept");
		} 
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		return mav;
	}
	
	// 원서접수 확인 및 취소 상세현황
	@RequestMapping(value="/accept_view", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView accept_view(@RequestParam("receiptId") String receiptId, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userInfo.getUserId());
		map.put("receiptId", receiptId); 
		
		ModelAndView mav = new ModelAndView();
		
		ApplyDto applyInfo = applyService.getDetailApplyInfo(map);
		
		mav.addObject("applyInfo", applyInfo);
		mav.setViewName("apply/accept_view");
		return mav;
	}
	
	// 수험표 출력
	@RequestMapping(value="/ticket", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView ticket(ApplyDto applyDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
		if (userInfo != null) 
		{
			ApplyPageMaker pageMaker = new ApplyPageMaker();
			pageMaker.setApplyDto(applyDto);
			pageMaker.setTotalCount(applyService.countApplyListTotal(userInfo.getUserId()));
			applyDto.setUserId(userInfo.getUserId());
			
			List<ApplyDto> applyList = new ArrayList<>();
			applyList = applyService.getApplyList(applyDto);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("applyList", applyList);
			mav.addObject("applyListCnt", applyList.size());
			mav.addObject("applyDto", applyDto);
			mav.setViewName("apply/ticket");
		} 
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		
		return mav;
	}
	
	// 수험표 출력 상세
	@RequestMapping(value="/ticket_view", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView ticket_view(@RequestParam("receiptId") String receiptId, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
		if (userInfo != null) 
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userInfo.getUserId());
			map.put("receiptId", receiptId);
			
			ApplyDto applyInfo = applyService.getDetailApplyInfo(map); // TODO : 교육수료증 api 구현 후 수정필요
			
			mav.addObject("applyInfo", applyInfo);
			mav.setViewName("apply/ticket_view");
		}
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");			
		}
		
		return mav;
	}
	
	// 수험표 출력 프린트
	@RequestMapping(value="/ticket_print", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView ticket_print(@RequestParam("receiptId") String receiptId, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
		if (userInfo != null) 
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userInfo.getUserId());
			map.put("receiptId", receiptId);
			
			ApplyDto applyInfo = applyService.getDetailApplyInfo(map);
			
			mav.addObject("applyInfo", applyInfo);
			mav.setViewName("apply/ticket_print");			
		}
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");				
		}

		return mav;
	}
	
	// 환불안내
	@RequestMapping(value="/refund", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView refund(HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
	
		if (userInfo != null) 
		{
			mav.setViewName("apply/refund");			
		}
		else
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");			
		}

		return mav;
	}
	
	// 접수취소(TODO : 구현필요)
	@RequestMapping(value="/cancel", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView cancel(@RequestParam("receiptId") String receiptId, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
		if (userInfo != null) 
		{
			mav.addObject("result", "");
			mav.setViewName("apply/cancel");			
		}
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");				
		}

		return mav;
	}
	
	// 원서접수 현황
	@RequestMapping(value="/receipt", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView receipt(ApplyDto applyDto, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		ModelAndView mav = new ModelAndView();

		if (userInfo != null) 
		{
			ApplyPageMaker pageMaker = new ApplyPageMaker();
			pageMaker.setApplyDto(applyDto);
			pageMaker.setTotalCount(applyService.countApplyReceptionStatusListTotal(userInfo.getUserId()));
			applyDto.setUserId(userInfo.getUserId());
			
			List<ApplyDto> applyList = new ArrayList<>();
			applyList = applyService.getApplyReceptionStatusList(applyDto);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("applyList", applyList);
			mav.addObject("applyDto", applyDto);
			mav.setViewName("state/receipt");
		} 
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		
		return mav;
	}
	
	// 응시결과
	@RequestMapping(value="/result", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView result(HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
		if (userInfo != null) 
		{
			List<ApplyDto> resultList = new ArrayList<>();
			resultList = applyService.getExamResult(userInfo.getUserId());
			mav.addObject("resultListCnt", resultList.size());
			mav.addObject("resultList", resultList);
			mav.setViewName("state/result");
		}
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		
		return mav;
	}
	
	// 자격인증서 발급(TODO : 화면 필요여부 회의필요)
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
