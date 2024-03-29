package com.yeosin.apply;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yeosin.board.FileDto;
import com.yeosin.user.UserDto;
import com.yeosin.user.UserService;
import com.yeosin.util.FileController;

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
		List<ExamAndExamzoneRelDto> examLocalList = new ArrayList<ExamAndExamzoneRelDto>();
	  
		// 로그인 정보 있을 때
		if (userInfo != null) 
		{
			examList = applyService.getExamList();
			examLocalList = applyService.getExamLocalList();
	     
			mav.addObject("isReceipt", false);
			mav.addObject("userInfo", userInfo);
			mav.addObject("examListCnt", examList.size());
			mav.addObject("examList", examList);
			mav.addObject("examLocalList", examLocalList);
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
	
	// 해당 시험에 접수한 이력이 있는지 확인
	@RequestMapping(value="/isReceiptExam", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> IsReceiptExam(@RequestParam Map<String, Object> requestMap, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");	
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		userInfo = userService.getLoginUserInfo(userInfo);
		
		// AJAX에서 넘어온 데이터
		Map<String, Object> paremterMap = new HashMap<String, Object>();
		paremterMap.put("userId", requestMap.get("userId"));
		paremterMap.put("examId", requestMap.get("examId"));
		
		// AJAX로 넘겨줄 데이터
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("receiptCount", applyService.getIsReceipt(paremterMap));
		
		return resultMap;
	}
	
	// 원서접수2(환불규정에 대한 동의 View)
	@RequestMapping(value="/apply2", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView AgreeRefundByApplyView(@RequestParam("examId") String examId, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
		if (userInfo != null) 
		{
			mav.addObject("examId", examId);
			mav.addObject("local", request.getParameter("local"));
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
		
		if (userInfo != null) 
		{		
			ExamDto examInfo = applyService.getExamInfo2(request.getParameter("examId"));
			List<SubjectDto> subjectInfo = applyService.getSujectListByExamId(request.getParameter("examId"));
			
			mav.addObject("examInfo", examInfo);
			mav.addObject("subjectInfo", subjectInfo);
			mav.addObject("userInfo", userInfo);
			mav.addObject("local", request.getParameter("local"));
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
	@RequestMapping(value="/isCompleteEdu", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> IsCompleteEdu(@RequestParam Map<String, Object> requestMap, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");	
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
		// AJAX에서 넘어온 데이터
		Map<String, Object> paremterMap = new HashMap<String, Object>();
		paremterMap.put("userId", userInfo.getUserId());		
		paremterMap.put("userName", requestMap.get("userName"));
		paremterMap.put("gender", requestMap.get("gender"));
		paremterMap.put("birthDate", requestMap.get("birthDate"));
		paremterMap.put("eduNum", requestMap.get("eduNum"));
		paremterMap.put("examId", requestMap.get("examId"));
		paremterMap.put("examDate", requestMap.get("examDate"));
		paremterMap.put("subjectId", requestMap.get("subjectId"));
		
		// AJAX로 넘겨줄 데이터
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("isPassEdu", applyService.getIsCompleteEdu(paremterMap));
		resultMap.put("isValidCertDate", applyService.getIsValidCertDate(paremterMap));
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
		paremterMap.put("local", requestMap.get("local"));
		paremterMap.put("examZoneDetail", requestMap.get("examZoneDetail"));
		
		// AJAX로 넘겨줄 데이터
		List<ExamZoneDto> examZoneList = new ArrayList<ExamZoneDto>();
		examZoneList = applyService.getExamZoneList(paremterMap);
		
//		for(int i = 0 ; i < examZoneList.size(); i++)
//		{
//			String FileId = examZoneList.get(i).getExamZoneMap();
//			String localFileName = applyService.getLocalFileName(FileId);
//			//String examZoneMap = request.getServletContext().getRealPath("/resources/examzoneFile/" + localFileName);
//			String examZoneMap = "/upload/examzoneFile/" + localFileName;
//			examZoneList.get(i).setExamZoneMap(examZoneMap);
//		}
		
		return examZoneList;
	}

	// 팝업 이미지 보여주기
	@RequestMapping(value="/searchImageView", method=RequestMethod.GET)
	@ResponseBody
	public void searchImageView(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{		
		FileDto fileInfo = this.applyService.getFileInfo(request.getParameter("fileId"));
        File file = new File(fileInfo.getFileURL() + fileInfo.getLocalFileName());
        byte[] imageData = Files.readAllBytes(file.toPath());
        response.setContentType("image/jpeg");
        response.getOutputStream().write(imageData);
	}

	// 고사장 잔여좌석 조회
	@RequestMapping(value="/CheckLeftOverSeat", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> CheckLeftOverSeat(@RequestParam Map<String, Object> requestMap, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
      
		// JSP에서 넘어온 데이터
		Map<String, Object> paremterMap = new HashMap<String, Object>();
		paremterMap.put("examId", requestMap.get("examId"));
		paremterMap.put("examZoneId", requestMap.get("examZoneId"));
      
		// AJAX로 넘겨줄 데이터
		Map<String, Object> resultMap = new HashMap<String, Object>();
      	resultMap.put("seat", applyService.getLeftOverSeat(paremterMap));
      
      	return resultMap;
	}
	
	// 원서접수4(고사장 및 시험영역 선택 View)
	@RequestMapping(value="/apply4", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView ExamZoneAndSubjectView(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");

		if (userInfo != null) 
		{
			String isPassEdu = "N";
            String isValidCertDate = "N";
            Map<String, Object> eduInfoMap = new HashMap<String, Object>();
            eduInfoMap.put("userId", userInfo.getUserId());
            eduInfoMap.put("userName", request.getParameter("userName"));
            eduInfoMap.put("gender", request.getParameter("gender"));
            eduInfoMap.put("birthDate", request.getParameter("birthDate"));
            eduInfoMap.put("eduNum", request.getParameter("eduNum"));
            eduInfoMap.put("examId", request.getParameter("examId"));
            eduInfoMap.put("examDate", request.getParameter("examDate"));
            eduInfoMap.put("subjectId", request.getParameter("subjectType"));
            isPassEdu = this.applyService.getIsCompleteEdu(eduInfoMap);
            isValidCertDate = this.applyService.getIsValidCertDate((Map)eduInfoMap);
            if ("Y".equals(isPassEdu) && "Y".equals(isValidCertDate)) {
                ExamDto examInfo = this.applyService.getExamInfo2(request.getParameter("examId"));
                Map<String, Object> paremterMap = new HashMap<String, Object>();
                paremterMap.put("examId", request.getParameter("examId"));
                paremterMap.put("local", request.getParameter("local"));
                List<ExamZoneDto> examZoneDetailList = (List<ExamZoneDto>)this.applyService.getExamDetailListByLocal((Map)paremterMap);
                mav.addObject("examZoneDtailList", (Object)examZoneDetailList);
                mav.addObject("examInfo", (Object)examInfo);
                mav.addObject("userInfo", (Object)userInfo);
                mav.addObject("subjectId", (Object)request.getParameter("subjectType"));
                mav.setViewName("apply/apply4");
            }
            else {
                ExamDto examInfo = this.applyService.getExamInfo2(request.getParameter("examId"));
                List<SubjectDto> subjectInfo = (List<SubjectDto>)this.applyService.getSujectListByExamId(request.getParameter("examId"));
                String resultCd = "N".equals(isPassEdu) ? "1" : ("N".equals(isValidCertDate) ? "2" : "3");
                mav.addObject("isAlert", (Object)true);
                mav.addObject("resultCd", (Object)resultCd);
                mav.addObject("examInfo", (Object)examInfo);
                mav.addObject("subjectInfo", (Object)subjectInfo);
                mav.addObject("userInfo", (Object)userInfo);
                mav.addObject("local", (Object)request.getParameter("local"));
                mav.setViewName("apply/apply3");
            }
		}
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		
		return mav;
	}
	
	// 현재 접수중인 시험이 현재 접수기간인 시험인지 유효성 체크
	@RequestMapping(value="/IsValidExam", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> IsValidExam(@RequestParam Map<String, Object> requestMap, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
      
		String examId = String.valueOf(requestMap.get("examId"));
		String validExamId;
		boolean isValidExam = true; 
		
		List<ExamDto> examList = new ArrayList<>();
		examList = applyService.getExamList();
		
		if (examList.size() > 0) validExamId = examList.get(0).getExamId();
		else validExamId = "None";
		
		if (!examId.equals(validExamId)) isValidExam = false;
						
		// AJAX로 넘겨줄 데이터
		Map<String, Object> resultMap = new HashMap<String, Object>();
      	resultMap.put("isValidExam", isValidExam);
      
      	return resultMap;
	}
	
	// 원서접수5(접수최종확인 및 결제직전 View)
	@RequestMapping(value="/apply5", method=RequestMethod.POST)
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
			String subjectName = applyService.getSubjectName(request.getParameter("subjectRadio"));
			
			String merchantKey 		= "1q8Rl7lwsYz1YaneFJ/mUIwNgh9y/12OcHoMVtR0CqnVnUf5WAPGxF95+jOo29PhSl1RGjSxnzhRB3xvmFEK7w=="; // 상점키
			String merchantID 		= "kmama0001m"; 				// 상점아이디
			String price 			= examInfo.getExamCost(); 						// 결제상품금액
			String moid 			= "moid"+getRamdomPassword();
			
			String ediDate 			= getyyyyMMddHHmmss();	
			String hashString 		= this.encrypt(ediDate + merchantID + price + merchantKey);
			System.out.println(">>>>>>>>>>>>>>>>hashString : " + hashString);
			mav.addObject("ediDate", ediDate);
			mav.addObject("hashString", hashString);
			
			session.setAttribute("sessionExamInfo", examInfo);
			System.out.println(">>> : " + ((ExamDto)session.getAttribute("sessionExamInfo")).getExamId());
			ApplyDto applyInfo = new ApplyDto();
			applyInfo.setCertId(request.getParameter("eduNum"));
			applyInfo.setExamZoneId(request.getParameter("examZoneId"));
			applyInfo.setSubjectId(request.getParameter("subjectId"));
			session.setAttribute("sessionApplyInfo", applyInfo);
			
			mav.addObject("examZoneName", examZoneName);
			mav.addObject("subjectName", subjectName);
			mav.addObject("examInfo", examInfo);
			mav.addObject("applyInfo", applyInfo);
			mav.addObject("userInfo", userInfo);
			mav.addObject("moid", moid);
			mav.setViewName("apply/apply5");
		}
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		
		return mav;
	}
	
	// 원서접수6(최종접수 및 결제 View)
	@RequestMapping(value="/apply6", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView ReceiptAndPaymentView(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		System.out.println("############# ReceiptAndPayment Start ###############");
		response.setCharacterEncoding("UTF-8");	
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
		if (userInfo != null) 
		{
			// 1. 결제하기 전 해당 시험에 결제한 이력이 있으면 저장을 막는다.
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userId", userInfo.getUserId());
			parameterMap.put("examId", request.getParameter("examId"));
			int receiptCount = applyService.getIsReceipt(parameterMap);
			System.out.println(">>> apply6 ReceiptAndPaymentView userId : " + userInfo.getUserId());
			System.out.println(">>> apply6 ReceiptAndPaymentView examId : " + request.getParameter("examId"));
			System.out.println(">>> apply6 ReceiptAndPaymentView receiptCount : " + receiptCount);
			
			if (receiptCount > 0)
			{
				mav.addObject("isReceipt", true);
				mav.addObject("userInfo", userInfo);
				mav.addObject("examListCnt", applyService.getExamList().size());
				mav.addObject("examList", applyService.getExamList());
				mav.addObject("examLocalList", applyService.getExamLocalList());
				mav.setViewName("apply/apply");	
			}
			else 
			{
				ExamDto examDto = new ExamDto();
                examDto.setExamId(request.getParameter("examId"));
                examDto.setExamCost(request.getParameter("Amt"));
                if ("N".equals(this.applyService.getAmtValidCheck(examDto))) {
                    mav.addObject("resultCode", (Object)null);
                    mav.addObject("isSuccess", (Object)"N");
                    mav.setViewName("apply/apply6");
                    return mav;
                }
                
				// 2. 접수번호를 생성하기 위해 MAX값을 가져온다.
				long newMaxReceiptNumber = Long.parseLong(applyService.getMaxReceiptNumber()) + 1;
				String newMaxReceiptNumberStr = "LPBQ" + String.valueOf(newMaxReceiptNumber);
				String newStudentCode = String.valueOf(newMaxReceiptNumber);
				String nineLenthStudentCode = newStudentCode.substring(4);
				System.out.println(">>> apply6 ReceiptAndPaymentView newMaxReceiptNumberStr : " + newMaxReceiptNumberStr);
				System.out.println(">>> apply6 ReceiptAndPaymentView nineLenthStudentCode : " + nineLenthStudentCode);

				// 3. 접수테이블에 저장될 값을 ApplyDto에 넣는다.(TODO : 결제정보 추가 Insert 필요)
				ApplyDto insertApplyDto = new ApplyDto();
				insertApplyDto.setReceiptId(newMaxReceiptNumberStr);
				insertApplyDto.setUserId(userInfo.getUserId());
				insertApplyDto.setExamId(request.getParameter("examId"));
				insertApplyDto.setCertId(request.getParameter("eduNum"));
				insertApplyDto.setExamZoneId(request.getParameter("exmaZoneId"));
				insertApplyDto.setStudentCode(nineLenthStudentCode);
				insertApplyDto.setSubjectId(request.getParameter("subjectId"));
				insertApplyDto.setCicode(userInfo.getCiCode());
				System.out.println(">>> apply6 ReceiptAndPaymentView CertId : " + request.getParameter("eduNum"));
				System.out.println(">>> apply6 ReceiptAndPaymentView subjectId : " + request.getParameter("subjectId"));
				
				int result = applyService.setReceiptInfo(insertApplyDto);
				System.out.println(">>> apply6 ReceiptAndPaymentView setReceiptInfo result : " + result);
				Map<String,String> resultMap = new HashMap<>();
				if (result > 0)
				{	
					try 
					{
						// 결제 시작 (트랜잭션 처리 후 순서 변경 예정)
						resultMap = this.payResult(session, request, response, resultMap);
						System.out.println(">>> apply6 ReceiptAndPaymentView ResultCode : " + resultMap.get("ResultCode"));
						if(!("3001".equals(resultMap.get("ResultCode")) || "4000".equals(resultMap.get("ResultCode")))) {
							// 결제실패시 해당 접수번호로 등록된 데이터 삭제
							int delCnt = applyService.setDeleteReceiptInfo(newMaxReceiptNumberStr);
							System.out.println(">>> setDeleteReceiptInfo delCnt : " + delCnt);
							mav.addObject("resultCode", resultMap.get("ResultCode"));
							mav.addObject("isSuccess", "N");
							mav.setViewName("apply/apply6");
							return mav;
						}
						
						String paymentMethod = "";
						if("CARD".equals(resultMap.get("PayMethod"))) {
							paymentMethod = "카드";
						}else if("BANK".equals(resultMap.get("PayMethod"))){
							paymentMethod = "계좌이체";
						}
						
						insertApplyDto.setPaymentMethod(paymentMethod);
						insertApplyDto.setExamFee(resultMap.get("Amt"));
						insertApplyDto.setPaymentId(resultMap.get("TID"));
						insertApplyDto.setPaymentMoid(resultMap.get("Moid"));
						
						int payResult = applyService.setPaymentInfo(insertApplyDto);
						System.out.println(">>> apply6 ReceiptAndPaymentView payResult : " + payResult);
						if(payResult==0) {
							mav.addObject("isSuccess", "N");
							mav.setViewName("apply/apply6");
							return mav;
						}
						mav.addObject("isSuccess", "Y");
						mav.addObject("examId", request.getParameter("examId"));
						mav.addObject("receiptId", newMaxReceiptNumberStr);
						mav.addObject("studentCode", nineLenthStudentCode);
						mav.addObject("userInfo", userInfo);
						mav.setViewName("apply/apply6");	
					}
					catch (Exception e)
					{
						Map<String,String> cancelResultMap = new HashMap<>();
						if("3001".equals(resultMap.get("ResultCode")) || "4000".equals(resultMap.get("ResultCode"))) {
							cancelResultMap.put("receiptId", newMaxReceiptNumberStr);
							cancelResultMap.put("userId", userInfo.getUserId());
							cancelResultMap.put("Amt", resultMap.get("Amt"));
							cancelResultMap.put("Tid", resultMap.get("TID"));
							cancelResultMap.put("isRollback", "Y");
							Thread.sleep(2000);
							cancelResultMap = this.payCancelResult(session, request, response, cancelResultMap);
							System.out.println(">>> payCancelResult isRollback error : " + e.getMessage());
							System.out.println(">>> receiptId : " + newMaxReceiptNumberStr +", userId : " + userInfo.getUserId());
							mav.addObject("isRefund", "Y");
						}
						// 오류발생시 해당 접수번호로 등록된 데이터 삭제
						applyService.setDeleteReceiptInfo(newMaxReceiptNumberStr);
						mav.addObject("isSuccess", "N");
						mav.setViewName("apply/apply6");
						System.out.println(">>> Exception PayResult : " + e.getMessage());
					}	
				}
				else 
				{
					mav.addObject("isSuccess", "N");
					mav.setViewName("apply/apply6");
				}	
			}
		}
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		
		System.out.println("############# ReceiptAndPayment End ###############");
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
			pageMaker.setTotalCount(applyService.countAcceptListTotal(userInfo.getUserId()));
			applyDto.setUserId(userInfo.getUserId());
			
			List<ApplyDto> applyList = new ArrayList<>();
			applyList = applyService.getAcceptList(applyDto);
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
			pageMaker.setTotalCount(applyService.countTicketListTotal(userInfo.getUserId()));
			applyDto.setUserId(userInfo.getUserId());
			
			List<ApplyDto> applyList = new ArrayList<>();
			applyList = applyService.getTicketList(applyDto);
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

	// 팝업 이미지 보여주기
	@RequestMapping(value="/examZoneImageView", method=RequestMethod.GET)
	@ResponseBody
	public void examZoneImageView(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{		
		String url = request.getParameter("fileUrl");
		String fileName = request.getParameter("localFileName");
		File file = new File(url + fileName);
		byte[] imageData = Files.readAllBytes(file.toPath());
		response.setContentType("image/jpeg");
		response.getOutputStream().write(imageData);
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
			String localFileName = applyService.getLocalFileName(applyInfo.getExamZoneDto().getExamZoneMap());
			FileDto fileInfo = applyService.getFileInfo(applyInfo.getExamZoneDto().getExamZoneMap());
			//String examZoneMap = request.getServletContext().getRealPath("/resources/examzoneFile/" + localFileName);
			String examZoneMap ="/upload/examzoneFile/" + localFileName;
			mav.addObject("fileInfo", fileInfo);
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
	@RequestMapping(value="/cancel", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView cancel(@RequestParam("receiptId") String receiptId, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
      
		if (userInfo != null)
		{
			Map<String,String> resultMap = new HashMap<>();
	      	resultMap.put("receiptId", receiptId);
	      	resultMap.put("userId", userInfo.getUserId());
			resultMap = this.payCancelResult(session, request, response, resultMap);
			resultMap.put("ResultCode", "2001");
			resultMap.put("ResultMsg", "결제취소성공");
			resultMap.put("CancelDate", "20220211");
			resultMap.put("CancelTime", "013423");

	      	// 환불성공
	      	if ("2001".equals(resultMap.get("ResultCode")) || "2211".equals(resultMap.get("ResultCode"))) 
	      	{
	      		int result = applyService.setCancelReceipt(resultMap);
	      		
	      		// 업데이트 성공
	      		if (result > 0)
	      		{
	      			mav.addObject("isSuccess", "Y");
		    	  	mav.setViewName("apply/cancel");        			
	      		}
	      		// 업데이트 실패
	      		else 
	      		{
	      			mav.addObject("isSuccess", "N");
		    	  	mav.setViewName("apply/cancel");  	      			
	      		}		    		   
	      	}
	      	// 환불실패
	      	else 
	      	{
	      		mav.addObject("isSuccess", "N");
	    	  	mav.setViewName("apply/cancel");   		    	  
	      	}		
			 	  
		}
		// 로그인 유저 세션 없음
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");  	    	  
		}

		return mav;
	}
	
	// 해당 접수건이 이미 취소된 상태인지 확인
	@RequestMapping(value="/isCancelReceipt", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> isCancelReceipt(@RequestParam Map<String, Object> requestMap, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");	
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		userInfo = userService.getLoginUserInfo(userInfo);
		
		// AJAX에서 넘어온 데이터
		Map<String, Object> paremterMap = new HashMap<String, Object>();
		paremterMap.put("userId", userInfo.getUserId());		
		paremterMap.put("receiptId", requestMap.get("receiptId"));
		
		// AJAX로 넘겨줄 데이터
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("isCancelReceipt", applyService.getIsCancelReceipt(paremterMap));
		
		return resultMap;
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
	public ModelAndView certificate(ApplyDto applyDto, HttpSession session, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
		if(userInfo != null)
		{
			mav.addObject("applyDto", applyDto);
			mav.setViewName("state/certificate");
		}
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		return mav;
	}
	
	public String encrypt(String strData){
		String passACL = null;
		MessageDigest md = null;
		try{
			md = MessageDigest.getInstance("SHA-256");
			md.reset();
			md.update(strData.getBytes());
			byte[] raw = md.digest();
			passACL = encodeHex(raw);
		}catch(Exception e){
			System.out.print("암호화 에러" + e.toString());
		}
		return passACL;
	}
	
	public final synchronized String getyyyyMMddHHmmss(){
		SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
		return yyyyMMddHHmmss.format(new Date());
	}
	
	public String encodeHex(byte [] b){
		char [] c = Hex.encodeHex(b);
		return new String(c);
	}
	
	MessageDigest md;
	String strSRCData = "";
	String strENCData = "";
	String strOUTData = "";
	
	public Map<String,String> payResult(HttpSession session, HttpServletRequest request, HttpServletResponse response, Map<String,String> resultMap) throws Exception {
	   request.setCharacterEncoding("utf-8"); 
	   /*
	   ****************************************************************************************
	   * <인증 결과 파라미터>
	   ****************************************************************************************
	   */
	   String authResultCode 	= (String)request.getParameter("AuthResultCode"); 	// 인증결과 : 0000(성공)
	   String authResultMsg 	= (String)request.getParameter("AuthResultMsg"); 	// 인증결과 메시지
	   String nextAppURL 		= (String)request.getParameter("NextAppURL"); 		// 승인 요청 URL
	   String txTid 			= (String)request.getParameter("TxTid"); 			// 거래 ID
	   String authToken 		= (String)request.getParameter("AuthToken"); 		// 인증 TOKEN
	   String payMethod 		= (String)request.getParameter("PayMethod"); 		// 결제수단
	   String mid 				= (String)request.getParameter("MID"); 				// 상점 아이디
	   String moid 			= (String)request.getParameter("Moid"); 			// 상점 주문번호
	   String amt 				= (String)request.getParameter("Amt"); 				// 결제 금액
	   String reqReserved 		= (String)request.getParameter("ReqReserved"); 		// 상점 예약필드
	   String netCancelURL 	= (String)request.getParameter("NetCancelURL"); 	// 망취소 요청 URL
	   
	   if("mo".equals(resultMap.get("mode"))) {
		   authResultCode = resultMap.get("authResultCode");
		   authResultMsg = resultMap.get("authResultMsg");
		   nextAppURL = resultMap.get("nextAppURL");
		   txTid = resultMap.get("txTid");
		   authToken = resultMap.get("authToken");
		   payMethod = resultMap.get("payMethod");
		   mid = resultMap.get("mid");
		   moid = resultMap.get("moid");
		   reqReserved = resultMap.get("reqReserved");
		   netCancelURL = resultMap.get("netCancelURL");
	   }
	   
	   System.out.println(">>> payResult authResultCode : " + authResultCode);
	   System.out.println(">>> payResulto txTid : " + txTid);
	   System.out.println(">>> payResult payMethod : " + payMethod);
	   System.out.println(">>> payResult moid : " + moid);
	   //String authSignature = (String)request.getParameter("Signature");			// Nicepay에서 내려준 응답값의 무결성 검증 Data

	   /*  
	   ****************************************************************************************
	   * Signature : 요청 데이터에 대한 무결성 검증을 위해 전달하는 파라미터로 허위 결제 요청 등 결제 및 보안 관련 이슈가 발생할 만한 요소를 방지하기 위해 연동 시 사용하시기 바라며 
	   * 위변조 검증 미사용으로 인해 발생하는 이슈는 당사의 책임이 없음 참고하시기 바랍니다.
	   ****************************************************************************************
	    */
	   
	   String authSignature = request.getParameter("Signature");
	   String merchantKey 		= "1q8Rl7lwsYz1YaneFJ/mUIwNgh9y/12OcHoMVtR0CqnVnUf5WAPGxF95+jOo29PhSl1RGjSxnzhRB3xvmFEK7w=="; // 상점키
	   String authComparisonSignature = this.encrypt(authToken + mid + amt + merchantKey);
	   
	   //인증 응답 Signature = hex(sha256(AuthToken + MID + Amt + MerchantKey)
	   //String authComparisonSignature = sha256Enc.encrypt(authToken + mid + amt + merchantKey);

	   /*
	   ****************************************************************************************
	   * <승인 결과 파라미터 정의>
	   * 샘플페이지에서는 승인 결과 파라미터 중 일부만 예시되어 있으며, 
	   * 추가적으로 사용하실 파라미터는 연동메뉴얼을 참고하세요.
	   ****************************************************************************************
	   */
	   String ResultCode 	= ""; String ResultMsg 	= ""; String PayMethod 	= "";
	   String GoodsName 	= ""; String Amt 		= ""; String TID 		= ""; 
	   //String Signature = ""; String paySignature = "";


	   /*
	   ****************************************************************************************
	   * <인증 결과 성공시 승인 진행>
	   ****************************************************************************************
	   */
	   String resultJsonStr = "";
	   if(authResultCode.equals("0000") && authSignature.equals(authComparisonSignature)){
		   /*
		    ****************************************************************************************
		    * <해쉬암호화> (수정하지 마세요)
		    * SHA-256 해쉬암호화는 거래 위변조를 막기위한 방법입니다. 
		    ****************************************************************************************
		    */
		   String ediDate			= this.getyyyyMMddHHmmss();
		   String signData 		= this.encrypt(authToken + mid + amt + ediDate + merchantKey);

		   /*
		    ****************************************************************************************
		    * <승인 요청>
		    * 승인에 필요한 데이터 생성 후 server to server 통신을 통해 승인 처리 합니다.
		    ****************************************************************************************
		    */
		   StringBuffer requestData = new StringBuffer();
		   requestData.append("TID=").append(txTid).append("&");
		   requestData.append("AuthToken=").append(authToken).append("&");
		   requestData.append("MID=").append(mid).append("&");
		   requestData.append("Amt=").append(amt).append("&");
		   requestData.append("EdiDate=").append(ediDate).append("&");
		   requestData.append("CharSet=").append("utf-8").append("&");
		   requestData.append("SignData=").append(signData);

		   resultJsonStr = connectToServer(requestData.toString(), nextAppURL);

		   HashMap resultData = new HashMap();
		   boolean paySuccess = false;
		   if("9999".equals(resultJsonStr)){
			   /*
			    *************************************************************************************
			    * <망취소 요청>
			    * 승인 통신중에 Exception 발생시 망취소 처리를 권고합니다.
			    *************************************************************************************
			    */
			   StringBuffer netCancelData = new StringBuffer();
			   requestData.append("&").append("NetCancel=").append("1");
			   String cancelResultJsonStr = connectToServer(requestData.toString(), netCancelURL);

			   HashMap cancelResultData = jsonStringToHashMap(cancelResultJsonStr);
			   ResultCode = (String)cancelResultData.get("ResultCode");
			   ResultMsg = (String)cancelResultData.get("ResultMsg");
			   /*Signature = (String)cancelResultData.get("Signature");
	   		String CancelAmt = (String)cancelResultData.get("CancelAmt");
	   		paySignature = sha256Enc.encrypt(TID + mid + CancelAmt + merchantKey);*/
		   }else{
			   resultData = jsonStringToHashMap(resultJsonStr);
			   ResultCode 	= (String)resultData.get("ResultCode");	// 결과코드 (정상 결과코드:3001)
			   ResultMsg 	= (String)resultData.get("ResultMsg");	// 결과메시지
			   PayMethod 	= (String)resultData.get("PayMethod");	// 결제수단
			   GoodsName   = (String)resultData.get("GoodsName");	// 상품명
			   Amt       	= (String)resultData.get("Amt");		// 결제 금액
			   TID       	= (String)resultData.get("TID");		// 거래번호
			   // Signature : Nicepay에서 내려준 응답값의 무결성 검증 Data
			   // 가맹점에서 무결성을 검증하는 로직을 구현하여야 합니다.
			   /*Signature = (String)resultData.get("Signature");
	   		paySignature = sha256Enc.encrypt(TID + mid + Amt + merchantKey);*/

			   /*
			    *************************************************************************************
			    * <결제 성공 여부 확인>
			    *************************************************************************************
			    */
			   if(PayMethod != null){
				   if(PayMethod.equals("CARD")){
					   if(ResultCode.equals("3001")) paySuccess = true; // 신용카드(정상 결과코드:3001)       	
				   }else if(PayMethod.equals("BANK")){
					   if(ResultCode.equals("4000")) paySuccess = true; // 계좌이체(정상 결과코드:4000)	
				   }else if(PayMethod.equals("CELLPHONE")){
					   if(ResultCode.equals("A000")) paySuccess = true; // 휴대폰(정상 결과코드:A000)	
				   }else if(PayMethod.equals("VBANK")){
					   if(ResultCode.equals("4100")) paySuccess = true; // 가상계좌(정상 결과코드:4100)
				   }else if(PayMethod.equals("SSG_BANK")){
					   if(ResultCode.equals("0000")) paySuccess = true; // SSG은행계좌(정상 결과코드:0000)
				   }else if(PayMethod.equals("CMS_BANK")){
					   if(ResultCode.equals("0000")) paySuccess = true; // 계좌간편결제(정상 결과코드:0000)
				   }
			   }
		   }
	   }else if (authSignature.equals(authComparisonSignature)) {
		   ResultCode = authResultCode;
		   ResultMsg = authResultMsg;
	   }
	   else {
		   System.out.println("\uc778\uc99d \uc751\ub2f5 Signature : " + authSignature);
		   System.out.println("\uc778\uc99d \uc0dd\uc131 Signature : " + authComparisonSignature);
	   }
	   resultMap.put("ResultCode", ResultCode);
	   resultMap.put("ResultMsg", ResultMsg);
	   resultMap.put("PayMethod", PayMethod);
	   resultMap.put("Amt", Amt);
	   resultMap.put("TID", TID);
	   resultMap.put("Moid", moid);
	   
	   return resultMap;
   }
	
	//server to server 통신
	public String connectToServer(String data, String reqUrl) throws Exception{
		HttpURLConnection conn 		= null;
		BufferedReader resultReader = null;
		PrintWriter pw 				= null;
		URL url 					= null;
		
		int statusCode = 0;
		StringBuffer recvBuffer = new StringBuffer();
		try{
			url = new URL(reqUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(15000);
			conn.setReadTimeout(25000);
			conn.setDoOutput(true);
			
			pw = new PrintWriter(conn.getOutputStream());
			pw.write(data);
			pw.flush();
			
			statusCode = conn.getResponseCode();
			resultReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			for(String temp; (temp = resultReader.readLine()) != null;){
				recvBuffer.append(temp).append("\n");
			}
			
			if(!(statusCode == HttpURLConnection.HTTP_OK)){
				throw new Exception();
			}
			
			return recvBuffer.toString().trim();
		}catch (Exception e){
			return "9999";
		}finally{
			recvBuffer.setLength(0);
			
			try{
				if(resultReader != null){
					resultReader.close();
				}
			}catch(Exception ex){
				resultReader = null;
			}
			
			try{
				if(pw != null) {
					pw.close();
				}
			}catch(Exception ex){
				pw = null;
			}
			
			try{
				if(conn != null) {
					conn.disconnect();
				}
			}catch(Exception ex){
				conn = null;
			}
		}
	}
	
	//JSON String -> HashMap 변환
	private static HashMap jsonStringToHashMap(String str) throws Exception{
		HashMap dataMap = new HashMap();
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(str);
			JSONObject jsonObject = (JSONObject)obj;

			Iterator<String> keyStr = jsonObject.keySet().iterator();
			while(keyStr.hasNext()){
				String key = keyStr.next();
				Object value = jsonObject.get(key);
				
				dataMap.put(key, value);
			}
		}catch(Exception e){
			
		}
		return dataMap;
	}
	
	public String getRamdomPassword() { 
		char[] charSet = new char[] {'0','1','2','3','4','5','6','7','8','9'}; 
		StringBuffer sb = new StringBuffer(); 
		SecureRandom sr = new SecureRandom(); 
		sr.setSeed(new Date().getTime()); 
		int idx = 0; 
		int len = charSet.length; 
		for (int i=0; i<10; i++) {
			idx = sr.nextInt(len); // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다. 
			sb.append(charSet[idx]); 
		} 
		return sb.toString(); 
	}
	
	public Map<String,String> payCancelResult(HttpSession session, HttpServletRequest request, HttpServletResponse response, Map<String,String> resultMap) throws Exception {
		request.setCharacterEncoding("utf-8"); 

		/*
		 ****************************************************************************************
		 * <취소요청 파라미터>
		 * 취소시 전달하는 파라미터입니다.
		 * 샘플페이지에서는 기본(필수) 파라미터만 예시되어 있으며, 
		 * 추가 가능한 옵션 파라미터는 연동메뉴얼을 참고하세요.
		 ****************************************************************************************
		 */
		String tid 					= (String)request.getParameter("TID");	// 거래 ID
		String cancelAmt 			= (String)request.getParameter("CancelAmt");	// 취소금액
		String partialCancelCode 	= (String)request.getParameter("PartialCancelCode"); 	// 부분취소여부
		String mid 					= "kmama0001m";	// 상점 ID
		String moid					= "moid"+getRamdomPassword();	// 주문번호
		String cancelMsg 			= "고객요청";	// 취소사유

		if("Y".equals(resultMap.get("isRollback"))) {
			tid = (String)resultMap.get("Tid");	// 취소금액
			cancelAmt = (String)resultMap.get("Amt");	// 취소금액
			partialCancelCode = "0";	// 취소금액
		}
		/*
		 ****************************************************************************************
		 * <해쉬암호화> (수정하지 마세요)
		 * SHA-256 해쉬암호화는 거래 위변조를 막기위한 방법입니다. 
		 ****************************************************************************************
		 */
		String merchantKey 		= "1q8Rl7lwsYz1YaneFJ/mUIwNgh9y/12OcHoMVtR0CqnVnUf5WAPGxF95+jOo29PhSl1RGjSxnzhRB3xvmFEK7w=="; // 상점키
		String ediDate			= getyyyyMMddHHmmss();
		String signData 		= this.encrypt(mid + cancelAmt + ediDate + merchantKey);
		System.out.println(">>> tid : " + tid);
		System.out.println(">>> cancelAmt : " + cancelAmt);
		System.out.println(">>> partialCancelCode : " + partialCancelCode);
		System.out.println(">>> mid : " + mid);
		System.out.println(">>> moid : " + moid);
		System.out.println(">>> cancelMsg : " + cancelMsg);
		System.out.println(">>> merchantKey : " + merchantKey);
		System.out.println(">>> ediDate : " + ediDate);
		System.out.println(">>> signData : " + signData);
		/*
		 ****************************************************************************************
		 * <취소 요청>
		 * 취소에 필요한 데이터 생성 후 server to server 통신을 통해 취소 처리 합니다.
		 * 취소 사유(CancelMsg) 와 같이 한글 텍스트가 필요한 파라미터는 euc-kr encoding 처리가 필요합니다.
		 ****************************************************************************************
		 */
		StringBuffer requestData = new StringBuffer();
		requestData.append("TID=").append(tid).append("&");
		requestData.append("MID=").append(mid).append("&");
		requestData.append("Moid=").append(moid).append("&");
		requestData.append("CancelAmt=").append(cancelAmt).append("&");
		requestData.append("CancelMsg=").append(URLEncoder.encode(cancelMsg, "euc-kr")).append("&");
		requestData.append("PartialCancelCode=").append(partialCancelCode).append("&");
		requestData.append("EdiDate=").append(ediDate).append("&");
		requestData.append("CharSet=").append("utf-8").append("&");
		requestData.append("SignData=").append(signData);
		String resultJsonStr = connectToServer(requestData.toString(), "https://webapi.nicepay.co.kr/webapi/cancel_process.jsp");

		/*
		 ****************************************************************************************
		 * <취소 결과 파라미터 정의>
		 * 샘플페이지에서는 취소 결과 파라미터 중 일부만 예시되어 있으며, 
		 * 추가적으로 사용하실 파라미터는 연동메뉴얼을 참고하세요.
		 ****************************************************************************************
		 */
		String ResultCode 	= ""; String ResultMsg 	= ""; String CancelAmt 	= "";
		String CancelDate 	= ""; String CancelTime = ""; String TID 		= ""; String Signature = "";

		/*  
		 ****************************************************************************************
		 * Signature : 요청 데이터에 대한 무결성 검증을 위해 전달하는 파라미터로 허위 결제 요청 등 결제 및 보안 관련 이슈가 발생할 만한 요소를 방지하기 위해 연동 시 사용하시기 바라며 
		 * 위변조 검증 미사용으로 인해 발생하는 이슈는 당사의 책임이 없음 참고하시기 바랍니다.
		 ****************************************************************************************
		 */
		//String Signature = ""; String cancelSignature = "";

		if("9999".equals(resultJsonStr)){
			ResultCode 	= "9999";
			ResultMsg	= "통신실패";
		}else{
			HashMap resultData = jsonStringToHashMap(resultJsonStr);
			ResultCode 	= (String)resultData.get("ResultCode");	// 결과코드 (취소성공: 2001, 취소성공(LGU 계좌이체):2211)
			ResultMsg 	= (String)resultData.get("ResultMsg");	// 결과메시지
			CancelAmt 	= (String)resultData.get("CancelAmt");	// 취소금액
			CancelDate 	= (String)resultData.get("CancelDate");	// 취소일
			CancelTime 	= (String)resultData.get("CancelTime");	// 취소시간
			TID 		= (String)resultData.get("TID");		// 거래아이디 TID
			//Signature       	= (String)resultData.get("Signature");
			//cancelSignature = sha256Enc.encrypt(TID + mid + CancelAmt + merchantKey);
		}
		System.out.println(">>> ResultCode : " + ResultCode);
		System.out.println(">>> ResultMsg : " + ResultMsg);
		System.out.println(">>> CancelAmt : " + CancelAmt);
		System.out.println(">>> CancelDate : " + CancelDate);
		System.out.println(">>> CancelTime : " + CancelTime);
		System.out.println(">>> TID : " + TID);
		System.out.println(">>> partialCancelCode : " + partialCancelCode);
		
		resultMap.put("TID", TID);
		resultMap.put("ResultCode", ResultCode);
		resultMap.put("ResultMsg", ResultMsg);
		resultMap.put("CancelDate", CancelDate);
		resultMap.put("CancelTime", CancelTime);
		return resultMap;
	}
	
	@RequestMapping(value="/moRecipt", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView moRecipt(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		System.out.println("############# moRecipt Start ###############");
		response.setCharacterEncoding("UTF-8");	
		ModelAndView mav = new ModelAndView();
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		ExamDto sessionExamInfo = (ExamDto)session.getAttribute("sessionExamInfo");
		ApplyDto sessionApplyInfo = (ApplyDto)session.getAttribute("sessionApplyInfo");
		
		if (userInfo != null) 
		{
			// 1. 결제하기 전 해당 시험에 결제한 이력이 있으면 저장을 막는다.
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userId", userInfo.getUserId());
			parameterMap.put("examId", sessionExamInfo.getExamId());
			int receiptCount = applyService.getIsReceipt(parameterMap);
			System.out.println(">>> moRecipt userId : " + userInfo.getUserId());
			System.out.println(">>> moRecipt examId : " + sessionExamInfo.getExamId());
			System.out.println(">>> moRecipt receiptCount : " + receiptCount);
			if (receiptCount > 0)
			{
				mav.addObject("isReceipt", true);
				mav.addObject("userInfo", userInfo);
				mav.addObject("examListCnt", applyService.getExamList().size());
				mav.addObject("examList", applyService.getExamList());
				mav.addObject("examLocalList", applyService.getExamLocalList());
				mav.setViewName("apply/apply");	
			}
			else 
			{
				ExamDto examDto = new ExamDto();
                examDto.setExamId(sessionExamInfo.getExamId());
                examDto.setExamCost(request.getParameter("Amt"));
                if ("N".equals(this.applyService.getAmtValidCheck(examDto))) {
                    mav.addObject("resultCode", (Object)null);
                    mav.addObject("isSuccess", (Object)"N");
                    mav.setViewName("apply/apply6");
                    return mav;
                }
                
				// 2. 접수번호를 생성하기 위해 MAX값을 가져온다.
				long newMaxReceiptNumber = Long.parseLong(applyService.getMaxReceiptNumber()) + 1;
				String newMaxReceiptNumberStr = "LPBQ" + String.valueOf(newMaxReceiptNumber);
				String newStudentCode = String.valueOf(newMaxReceiptNumber);

				// 3. 접수테이블에 저장될 값을 ApplyDto에 넣는다.(TODO : 결제정보 추가 Insert 필요)
				ApplyDto insertApplyDto = new ApplyDto();
				insertApplyDto.setReceiptId(newMaxReceiptNumberStr);
				insertApplyDto.setUserId(userInfo.getUserId());
				insertApplyDto.setExamId(sessionExamInfo.getExamId());
				insertApplyDto.setCertId(sessionApplyInfo.getCertId());
				insertApplyDto.setExamZoneId(sessionApplyInfo.getExamZoneId());
				insertApplyDto.setSubjectId(sessionApplyInfo.getSubjectId());
				insertApplyDto.setStudentCode(newStudentCode);
				insertApplyDto.setCicode(userInfo.getCiCode());
				
				System.out.println(">>> examId : " + insertApplyDto.getExamId());
				System.out.println(">>> certId : " + insertApplyDto.getCertId());
				System.out.println(">>> examZoneId : " + insertApplyDto.getExamZoneId());
				System.out.println(">>> newMaxReceiptNumberStr : " + insertApplyDto.getReceiptId());
				System.out.println(">>> newStudentCode : " + insertApplyDto.getStudentCode());
				System.out.println(">>> SubjectId : " + insertApplyDto.getSubjectId());

				insertApplyDto.setPaymentMethod(request.getParameter("PayMethod"));
				insertApplyDto.setPaymentId(request.getParameter("TID"));
				insertApplyDto.setExamFee(request.getParameter("Amt"));
				
				System.out.println(">>> PayMethod : " + insertApplyDto.getPaymentMethod());
				System.out.println(">>> TID : " + insertApplyDto.getPaymentId());
				System.out.println(">>> Amt : " + insertApplyDto.getExamFee());
				
				int result = applyService.setReceiptInfo(insertApplyDto);
				System.out.println(">>>  moRecipt result : " + result);
				if (result > 0)
				{	
					/*
					// 결제 시작 (트랜잭션 처리 후 순서 변경 예정)
					Map<String,String> resultMap = new HashMap<>();
					resultMap = this.payResult(session, request, response, resultMap);
					
					if(!("3001".equals(resultMap.get("ResultCode")) || "4000".equals(resultMap.get("ResultCode")))) {
						mav.addObject("isSuccess", "N");
						mav.setViewName("apply/apply6");
						return mav;
					}
					
					String paymentMethod = "";
					if("CARD".equals(resultMap.get("PayMethod"))) {
						paymentMethod = "카드";
					}else if("BANK".equals(resultMap.get("PayMethod"))){
						paymentMethod = "계좌이체";
					}
					
					insertApplyDto.setPaymentMethod(paymentMethod);
					insertApplyDto.setExamFee(resultMap.get("Amt"));
					insertApplyDto.setPaymentId(resultMap.get("TID"));
					
					int payResult = applyService.setPaymentInfo(insertApplyDto);
					
					if(payResult==0) {
						mav.addObject("isSuccess", "N");
						mav.setViewName("apply/apply6");
						return mav;
					}
					*/
					mav.addObject("isSuccess", "Y");
					mav.addObject("examId", request.getParameter("examId"));
					mav.addObject("receiptId", newMaxReceiptNumberStr);
					mav.addObject("studentCode", newStudentCode);
					mav.addObject("userInfo", userInfo);
					mav.setViewName("apply/apply6");	
				}
				else 
				{
					mav.addObject("isSuccess", "N");
					mav.setViewName("apply/apply6");
				}	
			}
		}
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}
		System.out.println("############# moRecipt End ###############");
		return mav;
	}

	// 원서접수6(최종접수 및 결제 View 모바일)
	@RequestMapping(value="/moReceipt2", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView moRecipt2(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");	
		String mode = request.getParameter("mode");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		Map<String,String> resultMap = new HashMap<>();

		System.out.println("############# moRecipt2 Start ############### " + mode);

		String authResultCode 	= (String)request.getParameter("AuthResultCode"); 	// 인증결과 : 0000(성공)
		String authResultMsg 	= (String)request.getParameter("AuthResultMsg"); 	// 인증결과 메시지
		String nextAppURL 		= (String)request.getParameter("NextAppURL"); 		// 승인 요청 URL
		String txTid 			= (String)request.getParameter("TxTid"); 			// 거래 ID
		String authToken 		= (String)request.getParameter("AuthToken"); 		// 인증 TOKEN
		String payMethod 		= (String)request.getParameter("PayMethod"); 		// 결제수단
		String mid 				= (String)request.getParameter("MID"); 				// 상점 아이디
		String moid 			= (String)request.getParameter("Moid"); 			// 상점 주문번호
		String amt 				= (String)request.getParameter("Amt"); 				// 결제 금액
		String reqReserved 		= (String)request.getParameter("ReqReserved"); 		// 상점 예약필드
		String netCancelURL 	= (String)request.getParameter("NetCancelURL"); 	// 망취소 요청 UR
		String userId			= request.getParameter("userId");
		String examId			= request.getParameter("examId");
		String certId			= request.getParameter("certId");
		String examZoneId 		= request.getParameter("examZoneId");
		String subjectId 		= request.getParameter("subjectId");

		resultMap.put("mode", mode);
		resultMap.put("authResultCode", authResultCode);
		resultMap.put("authResultMsg", authResultMsg);
		resultMap.put("nextAppURL", nextAppURL);
		resultMap.put("txTid", txTid);
		resultMap.put("authToken", authToken);
		resultMap.put("payMethod", payMethod);
		resultMap.put("mid", mid);
		resultMap.put("moid", moid);
		resultMap.put("amt", amt);
		resultMap.put("reqReserved", reqReserved);
		resultMap.put("netCancelURL", netCancelURL);


		ModelAndView mav = new ModelAndView();
		UserDto userInfo = new UserDto();
		userInfo.setUserId(userId);
		userInfo = userService.getLoginUserInfo(userInfo);
		ExamDto examInfo = applyService.getExamInfo(examId);

		if (userInfo != null) 
		{
			session.setAttribute("loginUserInfo",userInfo);
			// 1. 결제하기 전 해당 시험에 결제한 이력이 있으면 저장을 막는다.
			parameterMap.put("userId", userInfo.getUserId());
			parameterMap.put("examId", examInfo.getExamId());
			int receiptCount = applyService.getIsReceipt(parameterMap);
			System.out.println(">>> moRecipt userId : " + userInfo.getUserId());
			System.out.println(">>> moRecipt examId : " + examInfo.getExamId());
			System.out.println(">>> moRecipt receiptCount : " + receiptCount);

			if (receiptCount > 0)
			{
				mav.addObject("isReceipt", true);
				mav.addObject("userInfo", userInfo);
				mav.addObject("examListCnt", applyService.getExamList().size());
				mav.addObject("examList", applyService.getExamList());
				mav.addObject("examLocalList", applyService.getExamLocalList());
				mav.setViewName("apply/apply");	
			}
			else 
			{
				ExamDto examDto = new ExamDto();
                examDto.setExamId(examInfo.getExamId());
                examDto.setExamCost(request.getParameter("Amt"));
                if ("N".equals(this.applyService.getAmtValidCheck(examDto))) {
                    mav.addObject("resultCode", (Object)null);
                    mav.addObject("isSuccess", (Object)"N");
                    mav.setViewName("apply/apply6");
                    return mav;
                }
                
				// 2. 접수번호를 생성하기 위해 MAX값을 가져온다.
				long newMaxReceiptNumber = Long.parseLong(applyService.getMaxReceiptNumber()) + 1;
				String newMaxReceiptNumberStr = "LPBQ" + String.valueOf(newMaxReceiptNumber);
				String newStudentCode = String.valueOf(newMaxReceiptNumber);
				String nineLenthStudentCode = newStudentCode.substring(4);
				System.out.println(">>> moRecipt2 newMaxReceiptNumberStr : " + newMaxReceiptNumberStr);
				System.out.println(">>> moRecipt2 nineLenthStudentCode : " + nineLenthStudentCode);

				// 3. 접수테이블에 저장될 값을 ApplyDto에 넣는다.(TODO : 결제정보 추가 Insert 필요)
				ApplyDto insertApplyDto = new ApplyDto();
				insertApplyDto.setReceiptId(newMaxReceiptNumberStr);
				insertApplyDto.setUserId(userInfo.getUserId());
				insertApplyDto.setExamId(examInfo.getExamId());
				insertApplyDto.setCertId(certId);
				insertApplyDto.setExamZoneId(examZoneId);
				insertApplyDto.setStudentCode(nineLenthStudentCode);
				insertApplyDto.setSubjectId(subjectId);
				insertApplyDto.setCicode(userInfo.getCiCode());
				System.out.println(">>> moRecipt2 CertId : " + certId);
				System.out.println(">>> moRecipt2 subjectId : " + subjectId);

				int result = applyService.setReceiptInfo(insertApplyDto);
				System.out.println(">>> moRecipt2 setReceiptInfo result : " + result);
				if (result > 0)
				{
					try 
					{
						// 결제 시작 (트랜잭션 처리 후 순서 변경 예정)
						resultMap = this.payResult(session, request, response, resultMap);
						System.out.println(">>> moRecipt2 ResultCode : " + resultMap.get("ResultCode"));
						if(!("3001".equals(resultMap.get("ResultCode")) || "4000".equals(resultMap.get("ResultCode")))) {
							// 결제실패시 해당 접수번호로 등록된 데이터 삭제
							int delCnt = applyService.setDeleteReceiptInfo(newMaxReceiptNumberStr);
							System.out.println(">>> moRecipt2 setDeleteReceiptInfo delCnt : " + delCnt);
							mav.addObject("resultCode", resultMap.get("ResultCode"));
							mav.addObject("isSuccess", "N");
							mav.setViewName("apply/apply6");
							return mav;
						}
						String paymentMethod = "";
						if("CARD".equals(resultMap.get("PayMethod"))) {
							paymentMethod = "카드";
						}else if("BANK".equals(resultMap.get("PayMethod"))){
							paymentMethod = "계좌이체";
						}

						insertApplyDto.setPaymentMethod(paymentMethod);
						insertApplyDto.setExamFee(resultMap.get("Amt"));
						insertApplyDto.setPaymentId(resultMap.get("TID"));
						insertApplyDto.setPaymentMoid(resultMap.get("Moid"));

						int payResult = applyService.setPaymentInfo(insertApplyDto);
						System.out.println(">>> apply6 ReceiptAndPaymentView payResult : " + payResult);
						if(payResult==0) {
							mav.addObject("isSuccess", "N");
							mav.setViewName("apply/apply6");
							return mav;
						}
						mav.addObject("isSuccess", "Y");
						mav.addObject("examId", examInfo.getExamId());
						mav.addObject("receiptId", newMaxReceiptNumberStr);
						mav.addObject("studentCode", nineLenthStudentCode);
						mav.addObject("userInfo", userInfo);
						mav.setViewName("apply/apply6");	
					}
					catch (Exception e)
					{
						Map<String,String> cancelResultMap = new HashMap<>();
						if("3001".equals(resultMap.get("ResultCode")) || "4000".equals(resultMap.get("ResultCode"))) {
							cancelResultMap.put("receiptId", newMaxReceiptNumberStr);
							cancelResultMap.put("userId", userInfo.getUserId());
							cancelResultMap.put("Amt", resultMap.get("Amt"));
							cancelResultMap.put("Tid", resultMap.get("TID"));
							cancelResultMap.put("isRollback", "Y");
							Thread.sleep(2000);
							cancelResultMap = this.payCancelResult(session, request, response, cancelResultMap);
							System.out.println(">>> payCancelResult isRollback error : " + e.toString());
							System.out.println(">>> receiptId : " + newMaxReceiptNumberStr +", userId : " + userInfo.getUserId());
							mav.addObject("isRefund", "Y");
						}
						// 오류발생시 해당 접수번호로 등록된 데이터 삭제
						applyService.setDeleteReceiptInfo(newMaxReceiptNumberStr);
						mav.addObject("isSuccess", "N");
						mav.setViewName("apply/apply6");
						System.out.println(">>> Exception PayResult : " + e.getMessage());
					}	
				}
				else 
				{
					mav.addObject("isSuccess", "N");
					mav.setViewName("apply/apply6");
				}	
			}
		}
		else 
		{
			mav.addObject("isAlert", true);
			mav.setViewName("member/login");
		}

		System.out.println("############# ReceiptAndPayment End ###############");
		return mav;
	}
}
