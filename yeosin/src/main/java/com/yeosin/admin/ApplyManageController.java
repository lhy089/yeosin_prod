package com.yeosin.admin;

import java.io.File;
import java.util.Base64;
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

import com.yeosin.apply.ApplyDto;
import com.yeosin.apply.ApplyPageMaker;
import com.yeosin.apply.ExamDto;
import com.yeosin.apply.ExamDtoPageMaker;
import com.yeosin.apply.ExamZoneDto;
import com.yeosin.apply.ExamZoneDtoPageMaker;
import com.yeosin.apply.SubjectDto;
import com.yeosin.user.UserDto;
import com.yeosin.util.ImageSaveUtil;

@Controller
public class ApplyManageController {
   
   @Autowired
   private ApplyManageService applyManageService;
   
   // 원서접수 리스트 조회(원서별)
   @RequestMapping(value="/manage_status_doc", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView ApplyListByDocument(ApplyDto applyDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
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
		   // 조회조건 콤보박스 데이터
		   List<ExamZoneDto> localList = applyManageService.getConditionLocalList();
		   List<SubjectDto> subjectList = applyManageService.getConditionSubjectList();
		   
		   // 페이징 데이터 준비(페이지당 데이터 목록수)
		   int pagePerNum = 10;
		   if (request.getParameterMap().containsKey("onePageDataCountCondition")) 
		   {
			   if (request.getParameter("onePageDataCountCondition") != null 
					   && !request.getParameter("onePageDataCountCondition").trim().isEmpty())
			   {
				   pagePerNum = Integer.parseInt(request.getParameter("onePageDataCountCondition"));
			   }
		   }
		   applyDto.setPerPageNum(pagePerNum);
		   
		   Map<String, Object> parameterMap = new HashMap<String, Object>();
		   parameterMap.put("textCondition", request.getParameter("textCondition"));
		   parameterMap.put("localCondition", request.getParameter("localCondition"));
		   parameterMap.put("subjectCondition", request.getParameter("subjectCondition"));
		   parameterMap.put("pageStart", applyDto.getPageStart());
		   parameterMap.put("perPageNum", applyDto.getPerPageNum());

		   // 접수 리스트 데이터
		   List<ApplyDto> applyListByDocument = applyManageService.getApplyListByDocument(parameterMap);

		   // 페이징 하기위한 데이터
		   ApplyPageMaker pageMaker = new ApplyPageMaker();
		   pageMaker.setApplyDto(applyDto);
		   pageMaker.setTotalCount(applyManageService.getApplyListByDocumentCount(parameterMap));			
			
		   mav.addObject("localList", localList);
		   mav.addObject("subjectList", subjectList);
		   mav.addObject("applyListByDocument", applyListByDocument);
		   mav.addObject("textCondition", request.getParameter("textCondition"));
		   mav.addObject("localCondition", request.getParameter("localCondition"));
		   mav.addObject("subjectCondition", request.getParameter("subjectCondition"));
		   mav.addObject("pageCondition", request.getParameter("onePageDataCountCondition"));
		   mav.addObject("pageMaker", pageMaker);
		   mav.addObject("applyDto", applyDto);
		   mav.setViewName("admin/manage_status_doc"); 
	   }
	   return mav;
   }
   
   // 원서접수 리스트 조회(고사장별)
   @RequestMapping(value="/manage_status_site", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView ApplyListByExamZone(ExamZoneDto examZoneDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception
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
		   // 조회조건 콤보박스 데이터
		   List<ExamZoneDto> localList = applyManageService.getConditionLocalList();
		   List<ExamDto> examYearList = applyManageService.getConditionExamYearList();
		
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
		   examZoneDto.setPerPageNum(pagePerNum);
		     
		   Map<String, Object> parameterMap = new HashMap<String, Object>();
		   parameterMap.put("textCondition", request.getParameter("textCondition"));
		   parameterMap.put("localCondition", request.getParameter("localCondition"));
		   parameterMap.put("examYearCondition", request.getParameter("examYearCondition"));
		   parameterMap.put("pageStart", examZoneDto.getPageStart());
		   parameterMap.put("perPageNum", examZoneDto.getPerPageNum());
		     
		   // 접수 리스트 데이터
		   List<ApplyDto> applyListByExamZone = applyManageService.getApplyListByExamZone(parameterMap);
		
		   // 페이징 하기위한 데이터
		   ExamZoneDtoPageMaker pageMaker = new ExamZoneDtoPageMaker();
		   pageMaker.setExamZoneDto(examZoneDto);
		   pageMaker.setTotalCount(applyManageService.getApplyListByExamZoneCount(parameterMap));         
		     
		   mav.addObject("localList", localList);
		   mav.addObject("examYearList", examYearList);
		   mav.addObject("applyListByExamZone", applyListByExamZone);
		   mav.addObject("textCondition", request.getParameter("textCondition"));
		   mav.addObject("localCondition", request.getParameter("localCondition"));
		   mav.addObject("examYearCondition", request.getParameter("examYearCondition"));
		   mav.addObject("pageCondition", request.getParameter("onePageDataCountCondition"));
		   mav.addObject("pageMaker", pageMaker);
		   mav.addObject("examZoneDto", examZoneDto);
		   mav.setViewName("admin/manage_status_site"); 
	   }
	   return mav;
   }

   	// 좌석배치 확정
	@RequestMapping(value="/SeatConfirmByAjax", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> SeatConfirmByAjax(@RequestParam(value="examZoneCheck[]") List<String> requestArray, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");      
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		boolean isSuccess = false;
		
		// AJAX로 넘겨줄 데이터
		Map<String, Object> resultMap = new HashMap<String, Object>();
      
		if (userInfo == null || !"S".equals(userInfo.getUserStatus())) 
		{
			isSuccess = false;
		}
		else 
		{
			String[] examAndexamZoneIdArr = requestArray.toArray(new String[requestArray.size()]);
			
			// 시험 ID, 고사장 ID로 해당 시험의 고사장에 접수된 인원들에 대해 좌석배치 Update 로직
			for (int i = 0; i < examAndexamZoneIdArr.length; i++)
			{
				String examId = examAndexamZoneIdArr[i].split("\\.")[0];
				String examZoneId = examAndexamZoneIdArr[i].split("\\.")[1];
				
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("examId", examId);
				parameter.put("examZoneId", examZoneId);
				
				List<Object> totalReceiptList = applyManageService.getTotalReceiptIdByExamZone(parameter);

				parameter.put("totalReceiptList", totalReceiptList);
			
				int isUpdateSuccess = applyManageService.setExamZoneSeatConfirm(parameter);
				
				if (isUpdateSuccess == 1) isSuccess = true;
				else isSuccess = false;
			}	
		}
		
		resultMap.put("isSuccess", isSuccess);

		return resultMap;
	}
	
   	// 고사장 저장 or 수정
	@RequestMapping(value="/ExamZoneSaveByAjax", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> ExamZoneSaveByAjax(@RequestParam Map<String, Object> requestMap, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");      
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		boolean isSuccess = false;
		
		// AJAX로 넘겨줄 데이터
		Map<String, Object> resultMap = new HashMap<String, Object>();
      
		if (userInfo == null || !"S".equals(userInfo.getUserStatus())) 
		{
			isSuccess = false;
		}
		else 
		{		
			int isSaveUpdateSuccess = 0;
			//String testPath = "C:\\KakaoTalk_20220225_114652413.png";
			//byte[] imageMap = ImageSaveUtil.imageToByteArray(testPath);
			//byte[] imageMap = ImageSaveUtil.imageToByteArray(String.valueOf(requestMap.get("mapFileFullName"))); requestMap.replace("mapFile", imageMap);
			requestMap.put("mapFile", requestMap.get("mapFileFullName"));
			
			// 저장, 수정에 따라 호출하는 저장로직 다르게 호출
			if (requestMap.get("actionCode").equals("Save"))
			{
				int MaxExamZoneNumber = applyManageService.getMaxExamZoneId() + 1;
				String newExamZoneId = "examzone" + String.valueOf(MaxExamZoneNumber);
				requestMap.replace("examZoneId", newExamZoneId);
				isSaveUpdateSuccess = applyManageService.setExamZoneSave(requestMap);
			}
			else 
			{
				isSaveUpdateSuccess = applyManageService.setExamZoneModify(requestMap);
			}
			
			if (isSaveUpdateSuccess == 1) isSuccess = true;
			else isSuccess = false;
				
		}
		
		resultMap.put("isSuccess", isSuccess);

		return resultMap;
	}
	
   	// 고사장 삭제
	@RequestMapping(value="/ExamZoneDeleteByAjax", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> ExamZoneDeleteByAjax(@RequestParam(value="examZoneCheck[]") List<String> requestArray, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");      
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		boolean isSuccess = false;
		
		// AJAX로 넘겨줄 데이터
		Map<String, Object> resultMap = new HashMap<String, Object>();
      
		if (userInfo == null || !"S".equals(userInfo.getUserStatus())) 
		{
			isSuccess = false;
		}
		else 
		{
			// 체크한 고사장에 대한 삭제로직
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("examZoneList", requestArray);
		
			int isDeleteSuccess = applyManageService.setExamZoneDelete(parameter);
			
			if (isDeleteSuccess == 1) isSuccess = true;
			else isSuccess = false;
		}
		
		resultMap.put("isSuccess", isSuccess);

		return resultMap;
	}
	
	// 고사장 등록
	@RequestMapping(value="/siteRegister", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView ExamZoneRegister(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
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
			// 조회조건 콤보박스 데이터
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("examZoneId", request.getParameter("examZoneId"));
		     
			// 고사장 데이터
			ExamZoneDto examZone = applyManageService.getExamZone(parameterMap);

			mav.addObject("examZone", examZone);
			mav.setViewName("admin/site_register"); 
		}
		return mav;	
	}
   
	// 고사장 리스트
	@RequestMapping(value="/siteList", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView ExamZoneList(ExamZoneDto examZoneDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception  
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
		   	// 조회조건 콤보박스 데이터
		   	Map<String, Object> parameterMap = new HashMap<String, Object>();
		   	parameterMap.put("local", request.getParameter("localCondition"));
		   	List<ExamZoneDto> localList = applyManageService.getConditionLocalList();
		   	List<ExamZoneDto> localDetailList = applyManageService.getLocalDetailListByLocal(parameterMap);
		   	//List<ExamZoneDto> localDetailList = applyManageService.getConditionLocalDetailList();
		
		   	// 페이징 데이터 준비(페이지당 데이터 목록수)
		   	examZoneDto.setPerPageNum(30);
		     
		   	parameterMap.put("textCondition", request.getParameter("textCondition"));
		   	parameterMap.put("localCondition", request.getParameter("localCondition"));
		   	parameterMap.put("localDetailCondition", request.getParameter("localDetailCondition"));
		   	parameterMap.put("pageStart", examZoneDto.getPageStart());
		   	parameterMap.put("perPageNum", examZoneDto.getPerPageNum());
		     
		   	// 고사장 리스트 데이터
		   	List<ExamZoneDto> examZoneList = applyManageService.getExamZoneList(parameterMap);
		
		   	// 페이징 하기위한 데이터
		   	ExamZoneDtoPageMaker pageMaker = new ExamZoneDtoPageMaker();
		   	pageMaker.setExamZoneDto(examZoneDto);
		   	pageMaker.setTotalCount(applyManageService.getExamZoneListCount(parameterMap));         
		     
		   	mav.addObject("localList", localList);
		   	mav.addObject("localDetailList", localDetailList);
		   	mav.addObject("examZoneList", examZoneList);
		   	mav.addObject("textCondition", request.getParameter("textCondition"));
		   	mav.addObject("localCondition", request.getParameter("localCondition"));
		   	mav.addObject("localDetailCondition", request.getParameter("localDetailCondition"));
		   	mav.addObject("pageMaker", pageMaker);
		   	mav.addObject("examZoneDto", examZoneDto);
		   	mav.setViewName("admin/site_list"); 
	   	}
	   	return mav;
   	}
   
	// 지역을 선택했을때, 지역에 해당하는 구 조회
	@RequestMapping(value="/localDetailListByLocal", method=RequestMethod.GET)
	@ResponseBody
	public List<ExamZoneDto> LocalDetailListByLocal(@RequestParam Map<String, Object> requestMap, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");	
		
		// AJAX에서 넘어온 데이터
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("local", requestMap.get("local"));
		
		// AJAX로 넘겨줄 데이터
		List<ExamZoneDto> localDetailList = applyManageService.getLocalDetailListByLocal(parameterMap);
		
		return localDetailList;
	}
   
	// 시험일정관리
	@RequestMapping(value="/manageSchedule", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView manageSchedule(ExamDto examDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();

		UserDto userInfo = (UserDto) session.getAttribute("loginUserInfo");

		if (userInfo == null) {
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		} else if (!"S".equals(userInfo.getUserStatus())) {
			mav.addObject("isAlertNoAuth", true);
			mav.setViewName("main");
		} else {
			// 조회조건 콤보박스 데이터
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("year", request.getParameter("yearCondition"));
			parameterMap.put("examName", request.getParameter("examNameCondition"));
			List<ExamDto> yearList = applyManageService.getConditionExamYearList();
			List<ExamDto> examNameList = applyManageService.getExamNameListByYear(parameterMap);
			List<ExamDto> examDegreeList = applyManageService.getExamDegreeListByExamName(parameterMap);

			// 페이징 데이터 준비(페이지당 데이터 목록수)
			examDto.setPerPageNum(30);

			parameterMap.put("yearCondition", request.getParameter("yearCondition"));
			parameterMap.put("examNameCondition", request.getParameter("examNameCondition"));
			parameterMap.put("degreeCondition", request.getParameter("degreeCondition"));
			parameterMap.put("pageStart", examDto.getPageStart());
			parameterMap.put("perPageNum", examDto.getPerPageNum());

			// 시험 일정 데이터
			List<ExamDto> examList = applyManageService.getExamList(parameterMap);

			// 페이징 하기위한 데이터
			ExamDtoPageMaker pageMaker = new ExamDtoPageMaker();
			pageMaker.setExamDto(examDto);
			pageMaker.setTotalCount(applyManageService.getExamListCount(parameterMap));

			mav.addObject("yearList", yearList);
			mav.addObject("examNameList", examNameList);
			mav.addObject("degreeList", examDegreeList);
			mav.addObject("examList", examList);
			mav.addObject("yearCondition", request.getParameter("yearCondition"));
			mav.addObject("examNameCondition", request.getParameter("examNameCondition"));
			mav.addObject("degreeCondition", request.getParameter("degreeCondition"));
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("examDto", examDto);
			mav.setViewName("admin/manage_schedule");
		}
		return mav;
	}
   
   //시험일정등록
   @RequestMapping(value="/manageRegister", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView manageRegister(HttpSession session, HttpServletRequest request, HttpServletResponse response)  throws Exception
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();

		UserDto userInfo = (UserDto) session.getAttribute("loginUserInfo");

		if (userInfo == null) {
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		} else if (!"S".equals(userInfo.getUserStatus())) {
			mav.addObject("isAlertNoAuth", true);
			mav.setViewName("main");
		} else {
			// 고사장 데이터
			List<ExamZoneDto> examZoneList= applyManageService.getExamZoneListByExamRegister();

			mav.addObject("examZoneList", examZoneList);
			mav.setViewName("admin/manage_register");
		}
		return mav;
	}
   
   
   //채점표리스트
   @RequestMapping(value="/resultList", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView resultList(ApplyDto applyDto, HttpSession session, HttpServletResponse response, HttpServletRequest request)  
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
		  
		   try {
			   String str = request.getParameter("onePageDataCountCondition");
			   
			   int a = Integer.parseInt(request.getParameter("onePageDataCountCondition"));
			   
			   applyDto.setPerPageNum(Integer.parseInt(request.getParameter("onePageDataCountCondition")));
			   
			   Map<String, Object> parameterMap = new HashMap<String, Object>();
			   parameterMap.put("textCondition", request.getParameter("textCondition"));
			   parameterMap.put("localCondition", request.getParameter("localCondition"));
			   parameterMap.put("subjectCondition", request.getParameter("subjectCondition"));
			   parameterMap.put("pageStart", applyDto.getPageStart());
			   parameterMap.put("perPageNum", applyDto.getPerPageNum());
			   
			   List<ApplyDto> socrecardList = applyManageService.getScorecardList(parameterMap);
			   List<ExamZoneDto> localList = applyManageService.getConditionLocalList();
			   List<SubjectDto> subjectList = applyManageService.getConditionSubjectList();
			   
			   // 페이징 하기위한 데이터
			   ApplyPageMaker pageMaker = new ApplyPageMaker();
			   pageMaker.setApplyDto(applyDto);
			   pageMaker.setTotalCount(applyManageService.getScorecardListCount(parameterMap));
			   
				mav.addObject("socrecardList", socrecardList);
				mav.addObject("localList",localList);
				mav.addObject("subjectList", subjectList);
				mav.addObject("applyDto", applyDto);
				
				mav.addObject("textCondition", request.getParameter("textCondition"));
				mav.addObject("localCondition", request.getParameter("localCondition"));
				mav.addObject("subjectCondition", request.getParameter("subjectCondition"));
				mav.addObject("pageCondition", request.getParameter("onePageDataCountCondition"));
				mav.addObject("pageMaker", pageMaker);
				mav.setViewName("admin/result_manage");
		   } catch (Exception e) {
		
		   }
	   }
      
       mav.setViewName("admin/result_list");
      return mav;
   }
   
   //성적처리
   @RequestMapping(value="/resultManage", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView resultManage(HttpSession session, HttpServletResponse response)  
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
		   try {
			   List<ExamDto> examList = applyManageService.getExamListForGradeRegistration();

			   mav.addObject("examList", examList);
			   mav.setViewName("admin/result_manage");
		   } catch(Exception e) {

		   }
	   }
	   return mav; 
   }
   

	// 년도를 선택했을때, 년도에 해당하는 시험명 조회
	@RequestMapping(value = "/examNameConditionByYear", method = RequestMethod.GET)
	@ResponseBody
	public List<ExamDto> ExamNameListByLocal(@RequestParam Map<String, Object> requestMap, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");

		// AJAX에서 넘어온 데이터
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("year", requestMap.get("year"));

		// AJAX로 넘겨줄 데이터
		List<ExamDto> examNameList = applyManageService.getExamNameListByYear(parameterMap);

		return examNameList;
	}

	// 시험명을 선택했을때, 시험명에 해당하는 시험차수 조회
	@RequestMapping(value = "/degreeConditionByExamName", method = RequestMethod.GET)
	@ResponseBody
	public List<ExamDto> ExamDegreeListByExamName(@RequestParam Map<String, Object> requestMap, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");

		// AJAX에서 넘어온 데이터
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("examName", requestMap.get("examName"));

		// AJAX로 넘겨줄 데이터
		List<ExamDto> examDegreeList = applyManageService.getExamDegreeListByExamName(parameterMap);

		return examDegreeList;
	}

	// 시험 삭제
	@RequestMapping(value = "/ExamDeleteByAjax", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> ExamDeleteByAjax(@RequestParam(value = "examCheck[]") List<String> requestArray, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		response.setCharacterEncoding("UTF-8");
		UserDto userInfo = (UserDto) session.getAttribute("loginUserInfo");
		boolean isSuccess = false;

		// AJAX로 넘겨줄 데이터
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (userInfo == null || !"S".equals(userInfo.getUserStatus())) {
			isSuccess = false;
		} else {
			// 체크한 시험일정에 대한 삭제로직
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("examList", requestArray);

			// 삭제_주석처리
			//int isDeleteSuccess = applyManageService.setExamDelete(parameter);
			int isDeleteSuccess = 0;

			if (isDeleteSuccess == 1)
				isSuccess = true;
			else
				isSuccess = false;
		}

		resultMap.put("isSuccess", isSuccess);

		return resultMap;
	}
   
}