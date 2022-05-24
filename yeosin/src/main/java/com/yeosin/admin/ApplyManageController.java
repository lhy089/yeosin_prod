package com.yeosin.admin;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.yeosin.apply.ApplyDto;
import com.yeosin.apply.ApplyPageMaker;
import com.yeosin.apply.ExamAndExamzoneRelDto;
import com.yeosin.apply.ExamAndSubjectRelDto;
import com.yeosin.apply.ExamDto;
import com.yeosin.apply.ExamDtoPageMaker;
import com.yeosin.apply.ExamZoneDto;
import com.yeosin.apply.ExamZoneDtoPageMaker;
import com.yeosin.apply.GradeDto;
import com.yeosin.apply.SubjectDto;
import com.yeosin.board.FileDto;
import com.yeosin.user.EduCompletionDto;
import com.yeosin.user.UserDto;
import com.yeosin.user.UserPageMaker;
import com.yeosin.util.ExcelUtil;
import com.yeosin.util.ImageSaveUtil;

@Controller
public class ApplyManageController {
   
   @Autowired
   private ApplyManageService applyManageService;
   
   @Autowired
   private UserManageService userManageService;
   
   @Autowired
   private BoardManageService boardManageService;
   
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
		   List<ExamDto> examDegreeList = applyManageService.getConditionExamYearAndDegreeList();
		   List<ApplyDto> isCancelList = applyManageService.getConditionIsCancelList();
		   
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
		   applyDto.setPerPageNum(pagePerNum);
		   
		   Map<String, Object> parameterMap = new HashMap<String, Object>();
		   parameterMap.put("textCondition", request.getParameter("textCondition"));
		   parameterMap.put("localCondition", request.getParameter("localCondition"));
		   parameterMap.put("subjectCondition", request.getParameter("subjectCondition"));
		   parameterMap.put("examDegreeCondition", request.getParameter("examDegreeCondition"));
		   parameterMap.put("isCancelCondition", request.getParameter("isCancelCondition"));
		   parameterMap.put("pageStart", applyDto.getPageStart());
		   parameterMap.put("perPageNum", applyDto.getPerPageNum());

		   // 빈 문자열일때 null로 치환
		   for (Entry<String, Object> entrySet : parameterMap.entrySet()) 
		   {
			   if (entrySet.getValue() == null) continue;
			   if (entrySet.getValue().equals("")) entrySet.setValue(null);
		   }
		   
		   // 접수 리스트 데이터
		   List<ApplyDto> applyListByDocument = applyManageService.getApplyListByDocument(parameterMap);

		   // 페이징 하기위한 데이터
		   ApplyPageMaker pageMaker = new ApplyPageMaker();
		   pageMaker.setApplyDto(applyDto);
		   pageMaker.setTotalCount(applyManageService.getApplyListByDocumentCount(parameterMap));
		   
		   // 엑셀 다운로드용 데이터
		   parameterMap.replace("pageStart", 0);
		   parameterMap.replace("perPageNum", applyDto.getPage() * applyDto.getPerPageNum());
		   List<ApplyDto> applyListByDocumentAndExcel = applyManageService.getApplyListByDocument(parameterMap);
			
		   mav.addObject("localList", localList);
		   mav.addObject("subjectList", subjectList);
		   mav.addObject("examDegreeList", examDegreeList);
		   mav.addObject("isCancelList", isCancelList);
		   mav.addObject("applyListByDocument", applyListByDocument);
		   mav.addObject("applyListByDocumentAndExcel", applyListByDocumentAndExcel);
		   mav.addObject("textCondition", request.getParameter("textCondition"));
		   mav.addObject("localCondition", request.getParameter("localCondition"));
		   mav.addObject("subjectCondition", request.getParameter("subjectCondition"));
		   mav.addObject("examDegreeCondition", request.getParameter("examDegreeCondition"));
		   mav.addObject("isCancelCondition", request.getParameter("isCancelCondition"));
		   mav.addObject("pageCondition", pagePerNum);
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
		   List<ExamDto> examDegreeList = applyManageService.getConditionExamYearAndDegreeList();
		   //List<ExamDto> examYearList = applyManageService.getConditionExamYearList();
		
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
		   parameterMap.put("examDegreeCondition", request.getParameter("examDegreeCondition"));
		   parameterMap.put("pageStart", examZoneDto.getPageStart());
		   parameterMap.put("perPageNum", examZoneDto.getPerPageNum());
		     
		   // 빈 문자열일때 null로 치환
		   for (Entry<String, Object> entrySet : parameterMap.entrySet()) 
		   {
			   if (entrySet.getValue() == null) continue;
			   if (entrySet.getValue().equals("")) entrySet.setValue(null);
		   }
		   
		   // 접수 리스트 데이터
		   List<ExamZoneDto> applyListByExamZone = applyManageService.getApplyListByExamZone(parameterMap);
		
		   // 페이징 하기위한 데이터
		   ExamZoneDtoPageMaker pageMaker = new ExamZoneDtoPageMaker();
		   pageMaker.setExamZoneDto(examZoneDto);
		   pageMaker.setTotalCount(applyManageService.getApplyListByExamZoneCount(parameterMap));  
		   
		   // 엑셀 다운로드용 데이터
		   parameterMap.replace("pageStart", 0);
		   parameterMap.replace("perPageNum", examZoneDto.getPage() * examZoneDto.getPerPageNum());
		   List<ExamZoneDto> applyListByExamZoneAndExcel = applyManageService.getApplyListByExamZone(parameterMap);
		     
		   mav.addObject("localList", localList);
		   mav.addObject("examDegreeList", examDegreeList);
		   mav.addObject("applyListByExamZone", applyListByExamZone);
		   mav.addObject("applyListByExamZoneAndExcel", applyListByExamZoneAndExcel);
		   mav.addObject("textCondition", request.getParameter("textCondition"));
		   mav.addObject("localCondition", request.getParameter("localCondition"));
		   mav.addObject("examDegreeCondition", request.getParameter("examDegreeCondition"));
		   mav.addObject("pageCondition", pagePerNum);
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
				ExamZoneDto examZoneDto = applyManageService.getExamZoneByExamRoomCntAndExamRoomUserCnt(parameter);
				int examRoomCnt = Integer.parseInt(examZoneDto.getExamRoomCnt());
				int examRoomUserCnt = Integer.parseInt(examZoneDto.getExamRoomUserCnt());
				
				List<Object> seatNumberList = new ArrayList<Object>();
				
				complete :
				for (int j = 1; j <= examRoomCnt; j++)
				{
					for (int k = 1; k <= examRoomUserCnt; k++)
					{
						String seatNumber = j + "-" + k;
						seatNumberList.add(seatNumber);
						if (totalReceiptList.size() == seatNumberList.size()) break complete;
					}					
				}		
							
				Iterator<Object> iter = seatNumberList.iterator();
				for (int j = 0; j < totalReceiptList.size(); j++)
				{
					if (String.valueOf(totalReceiptList.get(j)).contains("|")) continue;
					totalReceiptList.set(j, totalReceiptList.get(j) + "|" + iter.next());
				}	
				
				parameter.put("totalReceiptList", totalReceiptList); // 좌석배치 대상 접수번호 + 좌석번호 합쳐서 보내기
			
				int isUpdateSuccess = applyManageService.setExamZoneSeatConfirm(parameter);
				
				if (isUpdateSuccess == 1) isSuccess = true;
				else isSuccess = false;
			}	
		}
		
		resultMap.put("isSuccess", isSuccess);

		return resultMap;
	}
	
   	// 고사장 저장 or 수정(Ajax 버전)
	@RequestMapping(value="/ExamZoneSaveByAjax", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ExamZoneSaveByAjax(@RequestParam Map<String, Object> requestMap, HttpSession session, HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws Exception 
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
			// 저장인지 업데이트인지 판별
			String actionCode = null;
			System.out.println(requestMap.get("examZoneId"));
			
			if (requestMap.get("examZoneId") == null || String.valueOf(requestMap.get("examZoneId")).isEmpty() || requestMap.get("examZoneId").equals("null"))
				actionCode = "Save";
			else 
				actionCode = "Update";
			// 새로운 고사장 ID 채번
			int MaxExamZoneNumber = applyManageService.getMaxExamZoneId() + 1;
			String newExamZoneId = "examzone" + String.valueOf(MaxExamZoneNumber); 
			String examZoneId = null;
			
			if (actionCode.equals("Save")) examZoneId = newExamZoneId;
			else examZoneId = String.valueOf(requestMap.get("examZoneId"));
				
			/////////////////////////// 파일 관련 프로세스 처리 ///////////////////////////
			FileDto getFileDto = applyManageService.getExamZoneFileInfo(String.valueOf(requestMap.get("fileId")));
			
			String fileName = String.valueOf(file.getOriginalFilename()); // 파일 다이얼로그로 등록
			String checkFileName = request.getParameter("fileName"); // 파일명 텍스트
			int fileSize = (int)file.getSize();

			// 파일이 이미 등록되어 있을때(수정, 삭제)
			if (getFileDto != null) 
			{
				// 수정
				if (!checkFileName.equals("") && checkFileName != null) 
				{
					if (!getFileDto.getRealFileName().equals(checkFileName))
					{
						String LocalFileName = Long.toString(System.currentTimeMillis()) + "_" + file.getOriginalFilename();
						//String examZonePath = "C:\\apache-tomcat-8.5.75\\upload\\examZoneFile\\"; // 로컬버전
						//String examZonePath = "/usr/local/lib/apache-tomcat-8.5.9/webapps/upload/examZoneFile/"; // 119 배포버전
						String examZonePath = "Z:\\apache-tomcat-8.5.75\\upload\\examZoneFile\\"; // 운영배포버전
						System.out.println(">>>> : " +examZonePath);
						File copyFile = new File(examZonePath, LocalFileName);
							
						if (!new File(examZonePath).exists()) 
						{
							new File(examZonePath).mkdirs();
						}
								
						FileCopyUtils.copy(file.getBytes(), copyFile);	
						
						FileDto updateFileDto = new FileDto();	
						String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);
						requestMap.put("examZoneMap", String.valueOf(requestMap.get("fileId")));
		
						updateFileDto.setFileId(String.valueOf(requestMap.get("fileId")));
						updateFileDto.setLocalFileName(LocalFileName);
						updateFileDto.setRealFileName(fileName);
						updateFileDto.setFileExtsn(fileExtsn);
						updateFileDto.setBoardId(examZoneId);
						updateFileDto.setFileSize(fileSize);
						updateFileDto.setFileURL(examZonePath);
						
						applyManageService.updateExamZoneMapFileInfo(updateFileDto);
					}
				}
				// 삭제
				else if (checkFileName.equals("") || checkFileName == null)
				{
					applyManageService.deleteExamZoneMapFileInfo(String.valueOf(requestMap.get("fileId")));
					requestMap.replace("fileId", null);
				}
				
			}
			// 파일등록이 안되어 있을때(저장)
			else 
			{
				// 저장
				if (!checkFileName.equals("") && checkFileName != null) // 파일을 새로 등록할때
				{
					String LocalFileName = Long.toString(System.currentTimeMillis()) + "_" + file.getOriginalFilename();
					//String examZonePath = "C:\\apache-tomcat-8.5.75\\upload\\examZoneFile\\"; // 로컬버전
					//String examZonePath = "/usr/local/lib/apache-tomcat-8.5.9/webapps/upload/examZoneFile/"; // 119 배포버전
					String examZonePath = "Z:\\apache-tomcat-8.5.75\\upload\\examZoneFile\\"; // 운영배포버전
					
					File copyFile = new File(examZonePath, LocalFileName);
					
					if (!new File(examZonePath).exists()) 
					{
						new File(examZonePath).mkdirs();
					}
						
					FileCopyUtils.copy(file.getBytes(), copyFile);
					
					int MaxFileIdNumber = boardManageService.getMaxFileId() + 1;
					String newFileId = "FILE" +  String.valueOf(MaxFileIdNumber);
					String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);
					requestMap.put("fileId", newFileId);
				
					FileDto fileDto = new FileDto();

					fileDto.setLocalFileName(LocalFileName);
					fileDto.setRealFileName(fileName);
					fileDto.setBoardId(examZoneId);
					fileDto.setFileId(newFileId);
					fileDto.setFileExtsn(fileExtsn);
					fileDto.setFileSize(fileSize);
					fileDto.setFileURL(examZonePath);
					
					applyManageService.saveExamZoneMapFileInfo(fileDto);
				}			
			}

			/////////////////////////// 파일 관련 프로세스 처리 끝 ///////////////////////////
			
			// 저장일때
			if (actionCode.equals("Save"))
			{
				requestMap.replace("examZoneId", examZoneId);
				isSaveUpdateSuccess = applyManageService.setExamZoneSave(requestMap);
			}
			// 수정일때
			else 
			{
				isSaveUpdateSuccess = applyManageService.setExamZoneModify(requestMap);
				
				String openExamId = applyManageService.getOpenReceiptExamId();
	            
	            if (openExamId != null && !openExamId.isEmpty())
	            {
	               Map<String, Object> updateMap = new HashMap<String, Object>();
	               updateMap.put("examZoneId", examZoneId);
	               updateMap.put("examId", openExamId);
	               updateMap.put("examRoomCnt", requestMap.get("examRoomCnt"));
	               updateMap.put("examRoomUserCnt", requestMap.get("examRoomUserCnt"));
	               
	               applyManageService.modifyExamAndExamZoneRel(updateMap);
	            }
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
		   	
			// 엑셀 다운로드용 데이터
			parameterMap.replace("pageStart", 0);
			parameterMap.replace("perPageNum", examZoneDto.getPage() * examZoneDto.getPerPageNum());
			List<ExamZoneDto> examZoneListByExcel = applyManageService.getExamZoneList(parameterMap);
		     
		   	mav.addObject("localList", localList);
		   	mav.addObject("localDetailList", localDetailList);
		   	mav.addObject("examZoneList", examZoneList);
		   	mav.addObject("examZoneListByExcel", examZoneListByExcel);
		   	mav.addObject("textCondition", request.getParameter("textCondition"));
		   	mav.addObject("localCondition", request.getParameter("localCondition"));
		   	mav.addObject("localDetailCondition", request.getParameter("localDetailCondition"));
		   	mav.addObject("pageCondition", request.getParameter("onePageDataCountCondition"));
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
			int pagePerNum = 30;
			if (request.getParameterMap().containsKey("onePageDataCountCondition")) 
			{
				if (request.getParameter("onePageDataCountCondition") != null 
						&& !request.getParameter("onePageDataCountCondition").trim().isEmpty())
				{
					pagePerNum = Integer.parseInt(request.getParameter("onePageDataCountCondition"));
				}
			}
			examDto.setPerPageNum(pagePerNum);

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
			
			// 엑셀 다운로드용 데이터
			parameterMap.replace("pageStart", 0);
			parameterMap.replace("perPageNum", examDto.getPage() * examDto.getPerPageNum());
			List<ExamDto> examListByExcel = applyManageService.getExamList(parameterMap);

			mav.addObject("yearList", yearList);
			mav.addObject("examNameList", examNameList);
			mav.addObject("degreeList", examDegreeList);
			mav.addObject("examList", examList);
			mav.addObject("examListByExcel", examListByExcel);
			mav.addObject("yearCondition", request.getParameter("yearCondition"));
			mav.addObject("examNameCondition", request.getParameter("examNameCondition"));
			mav.addObject("degreeCondition", request.getParameter("degreeCondition"));
			mav.addObject("pageCondition", request.getParameter("onePageDataCountCondition"));
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("examDto", examDto);
			mav.setViewName("admin/manage_schedule");
		}
		return mav;
	}
   
   //시험일정등록화면
   @RequestMapping(value="/manageRegister", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView manageRegister(@RequestParam("examId") String examId, HttpSession session, HttpServletRequest request, HttpServletResponse response)  throws Exception
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
			List<ExamZoneDto> examZoneList = applyManageService.getExamZoneListByExamRegister();
			List<SubjectDto> subjectList = applyManageService.getSubjectListByExamRegister();
			// 기존 시험 데이터
			ExamDto examDto = applyManageService.getExamInfo(examId);		
			List<ExamAndExamzoneRelDto> examZoneListByExamId = applyManageService.getExamZoneListByExamModify(examId);
			for (ExamAndExamzoneRelDto examZoneRel : examZoneListByExamId) 
			{
				for (ExamZoneDto examZone : examZoneList)
				{
					if (examZoneRel.getExamzoneId().equals(examZone.getExamZoneId()))
					{
						examZone.setExamAndExamzoneRelDto(examZoneRel);
					}
				}
			}			
			List<ExamAndSubjectRelDto> subjectListByExamId = applyManageService.getSubjectListByExamModify(examId);
			for (ExamAndSubjectRelDto subjectRel : subjectListByExamId) 
			{
				for (SubjectDto subject : subjectList)
				{
					if (subjectRel.getSubjectId().equals(subject.getSubjectId()))
					{
						subject.setExamAndSubjectRelDto(subjectRel);
					}
				}
			}
			
			mav.addObject("alertResult" , false);
			mav.addObject("alertError", false);
			mav.addObject("examZoneList", examZoneList);
			mav.addObject("subjectList", subjectList);
			mav.addObject("examDto", examDto);
			mav.setViewName("admin/manage_register");
		}
		return mav;
	}
   
 //시험일정등록하기
   @RequestMapping(value="/manageRegister_action", method=RequestMethod.GET)
   @ResponseBody
   public void manageRegister_action(ExamDto examDto, HttpSession session, HttpServletRequest request, HttpServletResponse response)  throws Exception
   {
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=euc-kr");
      PrintWriter out = response.getWriter();
      //ModelAndView mav = new ModelAndView();

      UserDto userInfo = (UserDto) session.getAttribute("loginUserInfo");

      if (userInfo == null) {
         out.println("<script>alert('로그인 후 이용 가능합니다.');location.href='member/login'</script>");
         out.flush();
         //mav.addObject("isAlert", true);
         //mav.setViewName("member/login");
      } else if (!"S".equals(userInfo.getUserStatus())) {
         out.println("<script>alert('권한이 없습니다..');location.href='/index'</script>");
         out.flush();
         //mav.addObject("isAlertNoAuth", true);
         //mav.setViewName("main");
      } else {
         
         // 시험 ID가 안넘어왔다면 등록처리
         if (request.getParameter("examId") == null || request.getParameter("examId").trim().isEmpty())
         {
            int MaxExamNumber = applyManageService.getMaxExamId() + 1;
            String newExamId = "exam" + String.valueOf(MaxExamNumber);
            
            examDto.setExamId(newExamId);
            examDto.setExamName("대출성 상품 판매대리·중개업자 등록 자격인증 평가");
            examDto.setExamType("자격시험");
            examDto.setIsApproval("Y");
            examDto.setIsPracticalExam("Y");
            examDto.setGradeEndDate(examDto.getGradeStartDate());
            examDto.setPeriod("0");
            examDto.setGradeStatus("N");
            examDto.setReceiptStartDate(examDto.getReceiptStartDate() + " " + request.getParameter("receiptStartTime"));
            examDto.setReceiptEndDate(examDto.getReceiptEndDate() + " " + request.getParameter("receiptEndTime"));
            
            boolean alertResult = false;
            boolean alertError = true;
               
            if(request.getParameterValues("subjectId") != null && request.getParameterValues("examZoneId") != null)
            {
            
               Map<String, Object> parameterMap = new HashMap<String, Object>();
               parameterMap.put("subjectId", request.getParameterValues("subjectId"));
               parameterMap.put("examId", newExamId);
            
               applyManageService.registerExam(examDto);
               applyManageService.registerExamAndSubjectRel(parameterMap);
               
               for (int i =0; i < request.getParameterValues("examZoneId").length; i++ )
               {
                  Map<String, Object> insertRelMap = new HashMap<String, Object>();
                  insertRelMap.put("examId", newExamId);
                  insertRelMap.put("examZoneId", request.getParameterValues("examZoneId")[i]);
                  insertRelMap.put("examRoomCnt", request.getParameterValues("examRoomCntList")[0].split("/")[i]);
                  insertRelMap.put("examRoomUserCnt", request.getParameterValues("examRoomUserCntList")[0].split("/")[i]);
                  
                   applyManageService.registerExamAndExamZoneRel(insertRelMap);           
               }   
               
               alertResult = true;
               alertError = false;
            }
            
            List<ExamZoneDto> examZoneList= applyManageService.getExamZoneListByExamRegister();
            List<SubjectDto> subjectList = applyManageService.getSubjectListByExamRegister();
            
            //mav.addObject("alertResult" , alertResult);
            //mav.addObject("alertError",alertError);
            //mav.addObject("examZoneList", examZoneList);
            //mav.addObject("subjectList", subjectList);
            //mav.setViewName("redirect:/manageRegister?examId="+newExamId);   
                        
            if(alertResult)
            {
               out.println("<script>alert('시험일정이 등록되었습니다.');location.href='/manageRegister?examId="+newExamId+"'</script>");
               out.flush();   
               //mav.setViewName("redirect:/manageRegister?examId="+request.getParameter("examId"));   
            }
            else
            {               
               out.println("<script>alert('오류로 인하여 시험일정이 등록되지 않았습니다.');location.href='/manageSchedule' </script>");
               out.flush();
               //mav.setViewName("redirect:/manageSchedule");   
            }         
         }
         // 시험 ID가 넘어왔다면 수정처리
         else 
         {      
            // 구현예정
            examDto.setExamId(request.getParameter("examId"));
            examDto.setExamName("대출성 상품 판매대리·중개업자 등록 자격인증 평가");
            examDto.setExamType("자격시험");
            examDto.setIsApproval("Y");
            examDto.setIsPracticalExam("Y");
            examDto.setGradeEndDate(examDto.getGradeStartDate());
            examDto.setPeriod("0");
            examDto.setGradeStatus("N");
            examDto.setReceiptStartDate(request.getParameter("receiptStartDate") + " " + request.getParameter("receiptStartTime"));
            examDto.setReceiptEndDate(request.getParameter("receiptEndDate") + " " + request.getParameter("receiptEndTime"));
            
            boolean alertResult = false;
            boolean alertError = true;
               
            if(request.getParameterValues("subjectId") != null && request.getParameterValues("examZoneId") != null)
            {
            
               Map<String, Object> parameterMap = new HashMap<String, Object>();
               parameterMap.put("examId", request.getParameter("examId"));
               parameterMap.put("subjectId", request.getParameterValues("subjectId"));
               parameterMap.put("examZoneId",  request.getParameterValues("examZoneId"));

               applyManageService.modifyExam(examDto);
               applyManageService.deleteExamAndExamZoneRel(parameterMap);
               
               for (int i =0; i < request.getParameterValues("examZoneId").length; i++ )
               {
                  Map<String, Object> insertRelMap = new HashMap<String, Object>();
                  insertRelMap.put("examId", request.getParameter("examId"));
                  insertRelMap.put("examZoneId", request.getParameterValues("examZoneId")[i]);
                  insertRelMap.put("examRoomCnt", request.getParameterValues("examRoomCntList")[0].split("/")[i]);
                  insertRelMap.put("examRoomUserCnt", request.getParameterValues("examRoomUserCntList")[0].split("/")[i]);
                  
                   applyManageService.registerExamAndExamZoneRel(insertRelMap);           
               }
               
               applyManageService.deleteExamAndSubjectRel(parameterMap);
               applyManageService.registerExamAndSubjectRel(parameterMap);
               
               alertResult = true;
               alertError = false;
            }
            
            List<ExamZoneDto> examZoneList= applyManageService.getExamZoneListByExamRegister();
            List<SubjectDto> subjectList = applyManageService.getSubjectListByExamRegister();
            List<ExamAndExamzoneRelDto> examZoneListByExamId = applyManageService.getExamZoneListByExamModify(request.getParameter("examId"));
            List<ExamAndSubjectRelDto> subjectListByExamId = applyManageService.getSubjectListByExamModify(request.getParameter("examId"));
            
            //mav.addObject("alertResult" , alertResult);
            //mav.addObject("alertError",alertError);
            //mav.addObject("examZoneList", examZoneList);
            //mav.addObject("subjectList", subjectList);
            //mav.addObject("examZoneListModify", examZoneListByExamId);
            //mav.addObject("subjectListModify", subjectListByExamId);
            //mav.addObject("examId", request.getParameter("examId"));
            
            if(alertResult)
            {
               out.println("<script>alert('시험일정이 등록되었습니다.');location.href='/manageRegister?examId="+request.getParameter("examId")+"'</script>");
               out.flush();   
               //mav.setViewName("redirect:/manageRegister?examId="+request.getParameter("examId"));   
            }
            else
            {               
               out.println("<script>alert('오류로 인하여 시험일정이 등록되지 않았습니다.');location.href='/manageSchedule' </script>");
               out.flush();
               //mav.setViewName("redirect:/manageSchedule");   
            }                        
         }
      }
      //return mav;
   }
   
   //채점표리스트_차수페이지 
   @RequestMapping(value="/resultListIntro", method=RequestMethod.GET)
   @ResponseBody
   public ModelAndView resultListIntro(ExamDto examDto, HttpSession session, HttpServletResponse response, HttpServletRequest request)  
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
				   examDto.setPerPageNum(Integer.parseInt(request.getParameter("onePageDataCountCondition")));
				   
				   // 조회조건 콤보박스 데이터
					Map<String, Object> parameterMap = new HashMap<String, Object>();
					parameterMap.put("year", request.getParameter("yearCondition"));
					parameterMap.put("examName", request.getParameter("examNameCondition"));
					List<ExamDto> yearList = applyManageService.getConditionExamYearList();
					List<ExamDto> examNameList = applyManageService.getExamNameListByYear(parameterMap);
					List<ExamDto> examDegreeList = applyManageService.getExamDegreeListByExamName(parameterMap);

					parameterMap.put("yearCondition", request.getParameter("yearCondition"));
					parameterMap.put("examNameCondition", request.getParameter("examNameCondition"));
					parameterMap.put("degreeCondition", request.getParameter("degreeCondition"));
					parameterMap.put("pageStart", examDto.getPageStart());
					parameterMap.put("perPageNum", examDto.getPerPageNum());

					// 시험 일정 데이터
					List<ExamDto> examList = applyManageService.getExamList(parameterMap);
					List<String> isProcessList = new ArrayList<String>();
					
					for(int i = 0 ; i < examList.size(); i++)
					{
						
						String str = examList.get(i).getExamId();
						int GradeCnt = applyManageService.getGradeCntByExamId(examList.get(i).getExamId());
						
						if(GradeCnt > 0)
							isProcessList.add("처리");
						else
							isProcessList.add("미처리");
						
					}
					
					// 페이징 하기위한 데이터
					ExamDtoPageMaker pageMaker = new ExamDtoPageMaker();
					pageMaker.setExamDto(examDto);
					pageMaker.setTotalCount(applyManageService.getExamListCount(parameterMap));

					mav.addObject("yearList", yearList);
					mav.addObject("examNameList", examNameList);
					mav.addObject("degreeList", examDegreeList);
					mav.addObject("examList", examList);
					mav.addObject("isProcessList",isProcessList);
					mav.addObject("yearCondition", request.getParameter("yearCondition"));
					mav.addObject("examNameCondition", request.getParameter("examNameCondition"));
					mav.addObject("degreeCondition", request.getParameter("degreeCondition"));
					mav.addObject("pageCondition", request.getParameter("onePageDataCountCondition"));
					mav.addObject("pageMaker", pageMaker);
					mav.addObject("examDto", examDto);
					mav.setViewName("admin/result_list_intro");
			   } catch (Exception e) {
			
			   }
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
			   applyDto.setPerPageNum(Integer.parseInt(request.getParameter("onePageDataCountCondition")));
			   
			   Map<String, Object> parameterMap = new HashMap<String, Object>();
			   parameterMap.put("textCondition", request.getParameter("textCondition"));
			   parameterMap.put("localCondition", request.getParameter("localCondition"));
			   parameterMap.put("subjectCondition", request.getParameter("subjectCondition"));
			   parameterMap.put("isPassCondition", request.getParameter("isPassCondition"));
			   parameterMap.put("examId", request.getParameter("examId"));
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
				mav.addObject("isPassCondition", request.getParameter("isPassCondition"));
				mav.addObject("examId", request.getParameter("examId"));
				mav.addObject("pageCondition", request.getParameter("onePageDataCountCondition"));
				mav.addObject("pageMaker", pageMaker);
				mav.setViewName("admin/result_list");
		   } catch (Exception e) {
		
		   }
	   }
      
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
		boolean isSuccess = true;
		boolean isContainApply = false;

		// AJAX로 넘겨줄 데이터
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (userInfo == null || !"S".equals(userInfo.getUserStatus())) 
		{
			isSuccess = false;
		} 
		else 
		{
			// 체크한 시험일정에 대한 삭제로직
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("examList", requestArray);

			// 해당 시험에 접수한 데이터가 1건이라도 존재하는지 확인	
			String isApply = "N";
			for (int i = 0; i < requestArray.size(); i++)
			{
				String result = applyManageService.getContainApply(requestArray.get(i));
				if (result.equals("Y"))
				{
					isApply = result;
					break;
				}
			}
			
			// 시험 리스트에 에 접수된 데이터가 한개도 없다면 삭제진행
			int isDeleteSuccess = 0;
			if (isApply.equals("N"))
			{
				isDeleteSuccess = applyManageService.setExamDelete(parameter);
				if (isDeleteSuccess == 1) isSuccess = true;
				else isSuccess = false;
			}
			else 
			{
				isContainApply = true;
			}
		}

		resultMap.put("isSuccess", isSuccess);
		resultMap.put("isContainApply", isContainApply);

		return resultMap;
	}
	
	// 시험 차수별리스트
	@RequestMapping(value="/manageExamDegreeList", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView manageExamDegreeList(ExamDto examDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
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
			int pagePerNum = 30;
			if (request.getParameterMap().containsKey("onePageDataCountCondition")) 
			{
				if (request.getParameter("onePageDataCountCondition") != null 
						&& !request.getParameter("onePageDataCountCondition").trim().isEmpty())
				{
					pagePerNum = Integer.parseInt(request.getParameter("onePageDataCountCondition"));
				}
			}
			examDto.setPerPageNum(pagePerNum);

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
			
			// 엑셀 다운로드용 데이터
			parameterMap.replace("pageStart", 0);
			parameterMap.replace("perPageNum", examDto.getPage() * examDto.getPerPageNum());
			List<ExamDto> examListByExcel = applyManageService.getExamList(parameterMap);

			mav.addObject("yearList", yearList);
			mav.addObject("examNameList", examNameList);
			mav.addObject("degreeList", examDegreeList);
			mav.addObject("examList", examList);
			mav.addObject("examListByExcel", examListByExcel);
			mav.addObject("yearCondition", request.getParameter("yearCondition"));
			mav.addObject("examNameCondition", request.getParameter("examNameCondition"));
			mav.addObject("degreeCondition", request.getParameter("degreeCondition"));
			mav.addObject("pageCondition", pagePerNum);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("examDto", examDto);
			mav.setViewName("admin/examDegreeList");
		}
		
		return mav;
	}
	@RequestMapping(value="/excelDownload", method=RequestMethod.POST)
	@ResponseBody
	public void excelDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String column = request.getParameter("columns") == null ? "" : request.getParameter("columns");
			String menuId = request.getParameter("menuId");
			String data = "";
			List<String> excel = new ArrayList<String>();

			if("resultListIntro".equals(menuId) || "examDegreeList".equals(menuId) || "manageSchedule".equals(menuId)) {
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("yearCondition", request.getParameter("yearConditionForExcel"));
				parameterMap.put("examNameCondition", request.getParameter("examNameConditionForExcel"));
				parameterMap.put("degreeCondition", request.getParameter("degreeConditionForExcel"));
				parameterMap.put("pageStart", 0);
				parameterMap.put("perPageNum", Integer.parseInt(request.getParameter("onePageDataCountConditionForExcel")) * Integer.parseInt(request.getParameter("pageForExcel")));

				// 시험 일정 데이터
				List<ExamDto> examList = applyManageService.getExamList(parameterMap);

				for(int i = 0 ; i < examList.size(); i++)
				{
					ExamDto dto = examList.get(i);
					String examInfo = "";
					String[] exam = new String[column.split(",").length];
					exam[0] = Integer.toString(i+1);
					exam[1] = dto.getExamYear();
					exam[2] = dto.getExamName();
					exam[3] = dto.getExamDegree();
					exam[4] = dto.getExamDate();
					if("resultListIntro".equals(menuId)) {
						int GradeCnt = applyManageService.getGradeCntByExamId(dto.getExamId());
						exam[5] = (GradeCnt > 0) ? "처리" : "미처리";
						exam[6] = "";
					}else {
						exam[5] = "";
					}
					
					examInfo = String.join("▒", exam);
					excel.add(examInfo);
				}

				data = String.join("▧", excel);
				
			}else if("resultList".equals(menuId)) {
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("textCondition", request.getParameter("textConditionForExcel"));
				parameterMap.put("localCondition", request.getParameter("localConditionForExcel"));
				parameterMap.put("subjectCondition", request.getParameter("subjectConditionForExcel"));
				parameterMap.put("isPassCondition", request.getParameter("isPassConditionForExcel"));
				parameterMap.put("examId", request.getParameter("examId"));
				parameterMap.put("pageStart", 0);
				parameterMap.put("perPageNum", Integer.parseInt(request.getParameter("onePageDataCountConditionForExcel")) * Integer.parseInt(request.getParameter("pageForExcel")));

				List<ApplyDto> socrecardList = applyManageService.getScorecardList(parameterMap);

				for(int i = 0 ; i < socrecardList.size(); i++)
				{
					ApplyDto dto = socrecardList.get(i);
					String socrecardInfo = "";
					String[] socrecard = new String[column.split(",").length];
					//고사장,수험번호,성명,생년월일,성별,유형,좌석번호,점수,합격여부
					socrecard[0] = dto.getExamZoneDto().getExamZoneName();
					socrecard[1] = dto.getStudentCode();
					socrecard[2] = dto.getUserDto().getUserName();
					socrecard[3] = dto.getUserDto().getBirthDate();
					socrecard[4] = dto.getUserDto().getGender();
					socrecard[5] = dto.getSubjectDto().getSubjectName();
					socrecard[6] = dto.getSeatNumber();
					socrecard[7] = String.valueOf(dto.getGradeDto().getAllScore());
					socrecard[8] = dto.getGradeDto().getIsPass();
					socrecardInfo = String.join("▒", socrecard);
					excel.add(socrecardInfo);
				}
				data = String.join("▧", excel);
				
			}else if("statusDoc".equals(menuId)) {
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("textCondition", request.getParameter("textConditionForExcel"));
				parameterMap.put("localCondition", request.getParameter("localConditionForExcel"));
				parameterMap.put("subjectCondition", request.getParameter("subjectConditionForExcel"));
				parameterMap.put("isCancelCondition", request.getParameter("isCancelConditionForExcel"));
				parameterMap.put("examDegreeCondition", request.getParameter("examDegreeConditionForExcel"));
				parameterMap.put("pageStart", 0);
				parameterMap.put("perPageNum", Integer.parseInt(request.getParameter("onePageDataCountConditionForExcel")) * Integer.parseInt(request.getParameter("pageForExcel")));

				// 빈 문자열일때 null로 치환
				for (Entry<String, Object> entrySet : parameterMap.entrySet()) 
				{
					if (entrySet.getValue() == null) continue;
					if (entrySet.getValue().equals("")) entrySet.setValue(null);
				}
				
				List<ApplyDto> applyListByDocument = applyManageService.getApplyListByDocument(parameterMap);

				for(int i = 0 ; i < applyListByDocument.size(); i++)
				{
					ApplyDto dto = applyListByDocument.get(i);
					String applyInfo = "";
					String[] apply = new String[column.split(",").length];
					//고사장,수험번호,성명,생년월일,성별,유형,좌석번호,점수,합격여부
					apply[0] = Integer.toString(i+1);
					apply[1] = dto.getExamDto().getExamDegree();
					apply[2] = dto.getStudentCode();
					apply[3] = dto.getUserDto().getUserName();
					apply[4] = dto.getUserId();
					apply[5] = dto.getUserDto().getBirthDate();
					apply[6] = dto.getUserDto().getPhoneNumber();
					apply[7] = dto.getPaymentMethod();
					apply[8] = dto.getExamZoneDto().getLocal();
					apply[9] = dto.getExamZoneDto().getExamZoneName();
					apply[10] = dto.getSeatNumber();
					apply[11] = dto.getSubjectDto().getSubjectName();
					apply[12] = dto.getUserDto().getGender();
					apply[13] = dto.getReceiptDate();
					apply[14] = dto.getExamDto().getExamDate();
					apply[15] = dto.getIsCancel();
					applyInfo = String.join("▒", apply);
					excel.add(applyInfo);
				}
				data = String.join("▧", excel);
			
			}else if("statusSite".equals(menuId)) {
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("textCondition", request.getParameter("textConditionForExcel"));
				parameterMap.put("localCondition", request.getParameter("localConditionForExcel"));
				parameterMap.put("examDegreeCondition", request.getParameter("examDegreeConditionForExcel"));
				parameterMap.put("pageStart", 0);
				parameterMap.put("perPageNum", Integer.parseInt(request.getParameter("onePageDataCountConditionForExcel")) * Integer.parseInt(request.getParameter("pageForExcel")));

				// 빈 문자열일때 null로 치환
				for (Entry<String, Object> entrySet : parameterMap.entrySet()) 
				{
					if (entrySet.getValue() == null) continue;
					if (entrySet.getValue().equals("")) entrySet.setValue(null);
				}
				
				List<ExamZoneDto> applyListByExamZone = applyManageService.getApplyListByExamZoneForExcel(parameterMap);

				for(int i = 0 ; i < applyListByExamZone.size(); i++)
				{
					ExamZoneDto dto = applyListByExamZone.get(i);
					String applyInfo = "";
					String[] apply = new String[column.split(",").length];
					//고사장,수험번호,성명,생년월일,성별,유형,좌석번호,점수,합격여부
					apply[0] = Integer.toString(i+1);
					apply[1] = dto.getExamDto().getExamDegree();
					apply[2] = dto.getExamDto().getExamName();
					apply[3] = dto.getLocal();
					apply[4] = dto.getExamZoneName();
					apply[5] = dto.getExamTotalUserCnt();
					apply[6] = dto.getReceiptSeat();
					apply[7] = dto.getLeftOverSeat();
					apply[8] = dto.getApplyDto().getReceiptDate();
					apply[9] = dto.getExamDto().getExamDate();
					applyInfo = String.join("▒", apply);
					excel.add(applyInfo);
				}
				data = String.join("▧", excel);
				
			}else if("siteList".equals(menuId)) {
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("textCondition", request.getParameter("textConditionForExcel"));
				parameterMap.put("localCondition", request.getParameter("localConditionForExcel"));
				parameterMap.put("localDetailCondition", request.getParameter("localDetailConditionForExcel"));
				parameterMap.put("pageStart", 0);
				parameterMap.put("perPageNum", Integer.parseInt(request.getParameter("onePageDataCountConditionForExcel")) * Integer.parseInt(request.getParameter("pageForExcel")));

				List<ExamZoneDto> examZoneList = applyManageService.getExamZoneList(parameterMap);

				for(int i = 0 ; i < examZoneList.size(); i++)
				{
					ExamZoneDto dto = examZoneList.get(i);
					String examZoneInfo = "";
					String[] examZone = new String[column.split(",").length];
					examZone[0] = Integer.toString(i+1);
					examZone[1] = dto.getLocal();
					examZone[2] = dto.getLocalDetail();
					examZone[3] = dto.getExamZoneName();
					examZone[4] = dto.getExamRoomCnt();
					examZone[5] = dto.getExamRoomUserCnt();
					examZone[6] = dto.getExamTotalUserCnt();
					examZone[7] = dto.getExamZoneMap();
					examZone[8] = "";
					examZoneInfo = String.join("▒", examZone);
					excel.add(examZoneInfo);
				}
				data = String.join("▧", excel);
				
			}else if("memberCourseView".equals(menuId)) {
				Map<String, Object> parameterMap = new HashMap<String, Object>();
		    	parameterMap.put("apiSyncId", request.getParameter("apiSyncIdForExcel"));
		    	parameterMap.put("searchWord", request.getParameter("searchWordForExcel"));
		    	parameterMap.put("gender", request.getParameter("genderForExcel"));
		    	parameterMap.put("subject", request.getParameter("subjectForExcel"));
		    	parameterMap.put("pageStart", 0);
		    	parameterMap.put("perPageNum", Integer.parseInt(request.getParameter("onePageDataCountConditionForExcel")) * Integer.parseInt(request.getParameter("pageForExcel")));

		    	List<EduCompletionDto> eduCompletionList = new ArrayList<EduCompletionDto>();
				eduCompletionList = userManageService.getEduCompletionList(parameterMap);

				for(int i = 0 ; i < eduCompletionList.size(); i++)
				{
					EduCompletionDto dto = eduCompletionList.get(i);
					String eduCompletionInfo = "";
					String[] eduCompletion = new String[column.split(",").length];
					eduCompletion[0] = Integer.toString(i+1);
					eduCompletion[1] = dto.getUserName();
					eduCompletion[2] = dto.getBirthDate();
					eduCompletion[3] = dto.getGender();
					eduCompletion[4] = dto.getSubject();
					eduCompletion[5] = dto.getCertId();
					eduCompletionInfo = String.join("▒", eduCompletion);
					excel.add(eduCompletionInfo);
				}
				data = String.join("▧", excel);
				
			}else if("memberInfo".equals(menuId)) {
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("searchEmailType", request.getParameter("searchEmailTypeForExcel"));
				parameterMap.put("searchSMSType", request.getParameter("searchSMSTypeForExcel"));
				parameterMap.put("searchWord", request.getParameter("searchWordForExcel"));
				parameterMap.put("generalUser", request.getParameter("generalUserForExcel") == null? "N" : request.getParameter("generalUserForExcel"));
				parameterMap.put("dormancyUser", request.getParameter("dormancyUserForExcel") == null? "N" : request.getParameter("dormancyUserForExcel"));
				parameterMap.put("secessionUser", request.getParameter("secessionUserForExcel") == null? "N" : request.getParameter("secessionUserForExcel"));
				parameterMap.put("pageStart", 0);
				parameterMap.put("perPageNum", Integer.parseInt(request.getParameter("onePageDataCountConditionForExcel")) * Integer.parseInt(request.getParameter("pageForExcel")));
	
				List<UserDto> userList = userManageService.getUserInfo(parameterMap);

				for(int i = 0 ; i < userList.size(); i++)
				{
					UserDto dto = userList.get(i);
					String UserInfo = "";
					String[] User = new String[column.split(",").length];
					User[0] = Integer.toString(i+1);
					User[1] = dto.getUserStatus();
					User[2] = dto.getUserName();
					User[3] = dto.getUserId();
					User[4] = dto.getGender();
					User[5] = dto.getJoinDate();
					User[6] = dto.getBirthDate();
					User[7] = dto.getCallNumber();
					User[8] = dto.getPhoneNumber();
					User[9] = dto.getEmailAddress();
					User[10] = "";
					UserInfo = String.join("▒", User);
					excel.add(UserInfo);
				}
				data = String.join("▧", excel);
				
			}

			String fileName = request.getParameter("fileName");
			HSSFWorkbook wb = ExcelUtil.excelDownloadByDetailList(column, data);
			ExcelUtil.downLoadFile(wb, fileName, request, response);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}