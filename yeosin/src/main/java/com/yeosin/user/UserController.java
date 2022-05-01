package com.yeosin.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yeosin.admin.BoardManageService;
import com.yeosin.admin.UserManageService;
import com.yeosin.apply.ApplyDto;
import com.yeosin.apply.ApplyService;
import com.yeosin.apply.ExamDto;
import com.yeosin.board.BoardDto;
import com.yeosin.board.BoardService;
import com.yeosin.board.PopupDto;
import com.yeosin.util.CheckPlus;
import com.yeosin.util.EncryptUtils;
import com.yeosin.util.StringEncrypter;

@Controller
public class UserController {
	
	private static final String key = "475650367892931796908kpc#$174122";
	private static final String vector = "8322400710346kpc";
	
	@Autowired	
	private UserService userService;
	
	@Autowired	
	private ApplyService applyService;
	
	@Autowired	
	private BoardService boardService;
	
	@Autowired	
	private UserManageService userManageService;
	
	@Autowired
	private BoardManageService boardManageService;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView index(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();

		ExamDto examInfo = applyService.getExamInfoForMain();
		List<BoardDto> noticeList = boardService.getNoticeListForMain();
		List<PopupDto> popupList = boardManageService.getPopupListForMain();

		mav.addObject("examInfo", examInfo);
		mav.addObject("noticeList", noticeList);
		mav.addObject("popupList", popupList);
		mav.setViewName("/index");
		return mav;
	}
	
	// 로그인 처리
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public String login(UserDto user, HttpSession session, HttpServletResponse response) throws Exception {
	   String result = "";
	   response.setCharacterEncoding("UTF-8");
	   user.setPassword(EncryptUtils.getSha256(user.getPassword()));
		System.out.println(" >>> user.getPassword() : " + user.getPassword());

		// 사용자 정보 조회
		UserDto userInfo = userService.getLoginUserInfo(user);
		
		if (userInfo != null) {
			// 세션추가
			
			if (userInfo.getUserStatus().equals("C"))
				result = "C";
			else if (userInfo.getUserStatus().equals("U")) {
				userService.UpdateLastConnectDate(user);
				
				result = "U";
				session.setAttribute("loginUserInfo", userInfo);
			} else if (userInfo.getUserStatus().equals("S")) {
				userService.UpdateLastConnectDate(user);
				
				result = "S";
				session.setAttribute("loginUserInfo", userInfo);
				session.setMaxInactiveInterval(3600);
				session.setAttribute("sessionTime", System.currentTimeMillis());
			}

		} else {

		}

		return result;
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
	
	// 본인 인증으로 id 찾기
	@RequestMapping(value="/find_id_cert", method=RequestMethod.POST)
	@ResponseBody
	public void find_id_cert(UserDto userDto, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		System.out.println(">>> find_id_cert ciCode : " + userDto.getCiCode());
		String userId = userService.findUserIdByCert(userDto);
		System.out.println(">>> find_id_cert userId : " + userId);
	
		response.getWriter().print(userId);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	// 본인 인증으로 id 찾기
		@RequestMapping(value="/popup", method=RequestMethod.GET)
		@ResponseBody
		public ModelAndView popup(String type, String data, HttpSession session, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			ModelAndView mav = new ModelAndView();
			String sessionid= (String)session.getAttribute("loginId");
			
			mav.addObject("type", type);
			mav.addObject("data", data);
			mav.setViewName("member/popup");
			return mav;
		}
	
	// 본인 인증으로 신규 비밀번호 발급
	@RequestMapping(value="/find_pwd_cert", method=RequestMethod.POST)
	@ResponseBody
	public void find_pwd_cert(UserDto userDto, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		String result = "null";
		UserDto userInfo = new UserDto();
		System.out.println(">>> find_id_cert ciCode : " + userDto.getCiCode());
		String userId = userService.findUserIdByCert(userDto);
		String password = getRamdomPwd(); //랜덤값
		System.out.println(">>> find_id_cert userId : " + userId);
		System.out.println(">>> find_id_cert password : " + password);
		
		userInfo.setUserId(userId);
		userInfo.setPassword(EncryptUtils.getSha256(password));
		
		int cnt= userService.updateUserPassword(userInfo);
		System.out.println(">>> find_id_cert cnt : " + cnt);
		
		if(cnt>0) result = password;
		userService.updateUserStatus(userInfo);
		
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	@RequestMapping(value="/find_id_ok", method=RequestMethod.GET)
	@ResponseBody
	public void find_id_ok(UserDto userDto, HttpSession session, HttpServletResponse response) throws Exception {
		String result = "null";
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
		System.out.println(">>> find_id_ok id : " + userDto.getUserId());
		int cnt = userService.findUserInfoCnt(userDto);
		if(cnt>1) {
			result = "duple";
		}else {
			String userId = userService.findUserId(userDto);
			result = userId;
		}
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
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
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	@ResponseBody
	public void logout(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		session.invalidate(); // 세션 초기화;
		response.getWriter().print(true);
		response.getWriter().flush();
		response.getWriter().close();
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
		user.setUserStatus("U");
		// 사용자 정보 조회
		System.out.println(">>> doJoin ciCode : " + user.getCiCode());
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
		String result = "false";
		response.setCharacterEncoding("UTF-8");
		UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		userInfo.setPassword(EncryptUtils.getSha256(user.getPassword()));

		userInfo = userService.getLoginUserInfo(userInfo);
		if(userInfo != null ) {
			result = "true";
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

	// 인증
	@RequestMapping(value = "/doOpenCert")
	@ResponseBody
	public void doOpenCert(String sAuthType, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		CheckPlus certUtil = new CheckPlus();
		String result = certUtil.getEncData(session, sAuthType);
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	// 인증 성공
		@RequestMapping(value = "/successCheckPlus")
		@ResponseBody
		public ModelAndView successCheckPlus(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception {
			
			response.setCharacterEncoding("UTF-8");
			ModelAndView mav = new ModelAndView();
			String sessionid = (String) session.getAttribute("loginId");
			CheckPlus certUtil = new CheckPlus();
			String result = certUtil.successCheckPlus(request, session);
			mav.addObject("result", result);
			mav.setViewName("member/join2");
			return mav;
		}
		
		// 인증
		@RequestMapping(value = "/doOpenCertForIpin")
		@ResponseBody
		public void doOpenCertForIpin(HttpSession session, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			CheckPlus certUtil = new CheckPlus();
			String result = certUtil.getEncDataForIpin(session);
			response.getWriter().print(result);
			response.getWriter().flush();
			response.getWriter().close();
		}
		
		@RequestMapping(value="/withdrawal", method=RequestMethod.GET)
		@ResponseBody
		public ModelAndView withdrawal(HttpSession session, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			ModelAndView mav = new ModelAndView();
			UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
		
			if(userInfo != null) {
				mav.addObject("userId", userInfo.getUserId());
				mav.setViewName("myroom/withdrawal");
			}else {
				mav.setViewName("member/login");
			}
			
			return mav;
		}
		
		// 사용자 정보 탈퇴
		@RequestMapping(value="/doWithdrawal", method=RequestMethod.POST)
		@ResponseBody
		public void doWithdrawal(String userId, HttpSession session, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			
			// 사용자 정보 탈퇴
			int cnt = userService.withdrawUser(userId);
			if(cnt>0) session.removeAttribute("loginUserInfo");
			response.getWriter().print(cnt);
			response.getWriter().flush();
			response.getWriter().close();
		}
		
		// 수료번호 api 호출
		@RequestMapping(value = "/callSyncCertIdApi")
		@ResponseBody
		public void callSyncCertIdApi(String startDate, String endDate, HttpSession session, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
			if(!"S".equals(userInfo.getUserStatus())) return;
			String rst = "";
			String url = "https://www.educrefia.or.kr/restapi/PassUsers?passStartDate=" + startDate + "&passEndDate=" + endDate;
			System.out.println(">>>>> callSyncCertIdApi url : " + url);
			try
			{
				URL clsUrl = new URL(url);
				HttpURLConnection clsConn = (HttpURLConnection)clsUrl.openConnection();
				clsConn.setRequestMethod("GET"); clsConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
				clsConn.setDoOutput(true);
				clsConn.connect();
				BufferedReader clsInput = new BufferedReader(new InputStreamReader(clsConn.getInputStream()));
				
				String inputLine = ""; 
				while((inputLine=clsInput.readLine())!=null){
					rst+=inputLine;
				}
				clsInput.close();
				JSONObject jo=new JSONObject(rst);
				rst=jo.getString("result");
			}catch(Exception e){ System.out.println(e);
			}
			System.out.println("aaa");
			JSONObject resultObj=new JSONObject(rst);
			String status = (String) resultObj.get("status");
			String statusMsg = (String) resultObj.get("statusMsg");
			String totalCount = resultObj.get("totalCount").toString();
			JSONArray items = (JSONArray) resultObj.get("items");
			
			StringEncrypter ecvrypterAES256 = new StringEncrypter(key, vector);
			EduCompletionHisDto eduCompletionHis = new EduCompletionHisDto();
			eduCompletionHis.setApiSyncId();
			System.out.println(">>>>> callSyncCertIdApi ApiSyncId : " + eduCompletionHis.getApiSyncId());

			List<EduCompletionDto> eduCompletionList = new ArrayList<EduCompletionDto>();
			for(int i=0; i<items.length(); i++){            
                JSONObject item = (JSONObject) items.get(i);
                EduCompletionDto eduCompletionInfo = new EduCompletionDto();
                String gender = ("1".equals(ecvrypterAES256.decrypt((String)item.get("user_sex")))) ? "남":"여";
                String birthDate = ecvrypterAES256.decrypt((String)item.get("user_birth"));
                if(birthDate.length()==8) {
                	birthDate = birthDate.substring(0,4)+"-"+birthDate.substring(4,6)+"-"+birthDate.substring(6,8);
                }else if(birthDate.length()==6) {
                	birthDate = "YY"+birthDate.substring(0,2)+"-"+birthDate.substring(2,4)+"-"+birthDate.substring(4,6);
                }
                eduCompletionInfo.setUserId("");
                eduCompletionInfo.setEduUserId((String)item.get("user_id"));
                eduCompletionInfo.setUserName(ecvrypterAES256.decrypt((String)item.get("user_name")));
                eduCompletionInfo.setBirthDate(birthDate);
                eduCompletionInfo.setGender(gender);
                eduCompletionInfo.setCertId((String)item.get("diploma_no"));
                eduCompletionInfo.setSubject((String)item.get("process_cd"));
                
                eduCompletionInfo.setApiSyncId(eduCompletionHis.getApiSyncId());
                eduCompletionList.add(eduCompletionInfo);
                /*
                if("Y".equals(userService.getEduCompletionInfo(eduCompletionInfo))) {
					eduCompletionInfo.setUpApiSyncId(eduCompletionHis.getApiSyncId());
					userService.updateEduComepletionInfo(eduCompletionInfo);
				}else {
					eduCompletionInfo.setApiSyncId(eduCompletionHis.getApiSyncId());
					userService.insertEduComepletionInfo(eduCompletionInfo);
				}
				*/
			}
			
			System.out.println("eduCompletionInfo.size1 : " + eduCompletionList.size() );
			userService.insertAndUpdateEduComepletionInfo(eduCompletionList);
						
			eduCompletionHis.setPassStartDate(startDate);
			eduCompletionHis.setPassEndDate(endDate);
			eduCompletionHis.setApiSyncType("M");
			
			userManageService.insertEduCompletionHis(eduCompletionHis);
			
			response.getWriter().print(totalCount);
			response.getWriter().flush();
			response.getWriter().close();
		}
		
		// 휴면계정 및 탈퇴계정에 대한 이메일/문자처리
		public void executeEmailAndSMS(UserDto userDto, String type, String order)
		{
			//  Email
			/*
				instId		[smallint]: 인스턴스 메일의 ID (협회에서 안내 예정)
				name		[varchar(50)]: 수신자명
				email   	[varchar(50)]: 수신자 email 주소
				subsValue	[text]: 인스턴스 메일에 포함시킬 변수값 (값1|값2|값3|값4...)
			 */
			//  SMS
			/*
				tranPhone    [varchar(15)]: 수신자 휴대전화 번호
				tranCallback [varchar(15)]: 발신자 휴대전화 번호
				tran_status  [varchar(1)]: sms 발송상태
				tran_date    [datetime]: 전송일자(현재시간)
				tranMsg      [varchar(255)]: sms 내용(db 사이즈는 255지만 문자 내용은 80바이트로 제한됨)
				tranEtc1     [varchar(64)]: sms 인스턴스 번호(협회에서 안내)
				tranEtc3     [varchar(64)]: 기타값
			 */
			Date today = new Date();
			String emailQuery = "";
			String emailDescription = "";
			String smsQuery = "";
			String smsDescription = "";
			
			// 휴면
			if (type.equals("dormantAccountProcessing"))
			{
				// 1차, 2차 안내처리
				if (order.equals("first") || order.equals("second"))
				{
					// 사용안함
					emailDescription = "장기 미사용 계정 휴면계정 전환 처리 안내" + System.lineSeparator() + 
									System.lineSeparator() + 
									"<#=SUB1#>님 안녕하세요," + System.lineSeparator() + 
									System.lineSeparator() + 
									"회원님의 개인정보보호를 위해 1년이상 장기 미사용 계정은" + System.lineSeparator() +
									"관계법령에 따라 휴면계정으로 전환(개인정보 분리 및 별도보관) 처리할 예정입니다." + System.lineSeparator() + 
									System.lineSeparator() + 
									"휴면계정 전환 후에는 서비스 이용이 불가하오니," + System.lineSeparator() + 
									"계속 이용하시고자 할 경우에는 전환일 전까지" + System.lineSeparator() + 
									"홈페이지에 접속하여 로그인 해주시기 바랍니다." + System.lineSeparator() + 
									System.lineSeparator() + 
									"휴면계정 전환 후 2년 이내에는 별도 본인확인 절차를 통해서 계정 복원 및 사용이 가능하며," + System.lineSeparator() + 
									"2년 이내 복원하지 않을 경우에는 자동 탈퇴 처리되오니 이용에 유의하시기 바랍니다." + System.lineSeparator() + 
									System.lineSeparator() + 
									"1. 휴면계정 전환일 : <#=SUB2#>" + System.lineSeparator() + 
									"2. 분리보관 정보 : 회원가입시 수집된 개인정보" + System.lineSeparator() + 
									"3. 관계법령 : 정보통신망 이용촉진 및 정보보호 등에 관한 법률 제29조" + System.lineSeparator();
					
					emailQuery = "INSERT INTO TB_INST_INPUT (instId, name, email, subs_value)"
							+ "VALUES ('164', "
							+ "'" + userDto.getUserName() + "'," 
							+ "'" + userDto.getEmailAddress() + "'," 
							+ "'" + userDto.getUserName() + "|" + userDto.getThird_schedulDate() + "'" 
							+ ")";
					
					smsDescription = userDto.getUserName() + "님의 계정이 장기 미사용에 따라" + System.lineSeparator() + 
								userDto.getThird_schedulDate() + "에 휴면계정으로 전환 처리될 예정입니다." + System.lineSeparator() +
								"계속 이용하시고자 할 경우 처리일 전까지 로그인 해주시기 바랍니다." + System.lineSeparator() +
								"https://www.lpcrefia.or.kr" + System.lineSeparator();
					
					smsQuery = "INSERT INTO EM_TRAN(tran_phone, tran_callback, tran_status, tran_date, tran_msg, tran_etc1, tranEtc3)" 
							+ "VALUES (" + "'" + userDto.getPhoneNumber() + "'," 
							+ "'" + "000-000-0000" + "'," // 발신자 휴대전화번호 가이드 안됨
							+ "'" + "1" + "',"
							+ "'" + today + "',"
							+ "'" + smsDescription + "',"
							+ "'" + "10071" + "',"
							+ "'" + "" + "'"
							+ ")";
				}
				// 3차 안내처리
				else 
				{
					// 사용안함
					emailDescription = "장기 미사용 계정 휴면계정 전환 처리 안내" + System.lineSeparator() + 
							System.lineSeparator() + 
							"<#=SUB1#>님 안녕하세요," + System.lineSeparator() + 
							System.lineSeparator() + 
							"회원님의 개인정보보호를 위해 1년이상 장기 미사용 계정은" + System.lineSeparator() +
							"관계법령에 따라 휴면계정으로 전환(개인정보 분리 및 별도보관) 처리되었습니다." + System.lineSeparator() + 
							System.lineSeparator() + 
							"휴면계정 전환 후 2년 이내에는 별도 본인확인 절차를 통해서 계정 복원 및 사용이 가능하며," + System.lineSeparator() + 
							"2년 이내 복원하지 않을 경우에는 자동 탈퇴 처리되오니 이용에 유의하시기 바랍니다." + System.lineSeparator() + 
							System.lineSeparator() + 
							"1. 휴면계정 전환일 : <#=SUB2#>" + System.lineSeparator() + 
							"2. 분리보관 정보 : 회원가입시 수집된 개인정보" + System.lineSeparator() + 
							"3. 관계법령 : 정보통신망 이용촉진 및 정보보호 등에 관한 법률 제29조" + System.lineSeparator();
			
					emailQuery = "INSERT INTO TB_INST_INPUT (instId, name, email, subs_value)"
							+ "VALUES ('164', " 
							+ "'" + userDto.getUserName() + "'," 
							+ "'" + userDto.getEmailAddress() + "'," 
							+ "'" + userDto.getUserName() + "|" + userDto.getThird_schedulDate() + "'" 
							+ ")";
					
					smsDescription = userDto.getUserName() + "님의 계정이 장기 미사용에 따라" + System.lineSeparator() + 
								userDto.getThird_schedulDate() + "에 휴면계정으로 전환 처리되었습니다." + System.lineSeparator() +
								"https://www.lpcrefia.or.kr" + System.lineSeparator();
					
					smsQuery = "INSERT INTO EM_TRAN(tran_phone, tran_callback, tran_status, tran_date, tran_msg, tran_etc1, tranEtc3)" 
							+ "VALUES (" + "'" + userDto.getPhoneNumber() + "'," 
							+ "'" + "000-000-0000" + "'," // 발신자 휴대전화번호 가이드 안됨
							+ "'" + "1" + "',"
							+ "'" + today + "',"
							+ "'" + smsDescription + "',"
							+ "'" + "10071" + "',"
							+ "'" + "" + "'"
							+ ")";				
				}
			}
			// 탈퇴
			else 
			{
				// 1차, 2차 안내처리
				if (order.equals("first") || order.equals("second"))
				{
					// 사용안함
					emailDescription = "장기 미사용 계정 개인정보 파기(탈퇴) 처리 안내" + System.lineSeparator() + 
							System.lineSeparator() + 
							"<#=SUB1#>님 안녕하세요," + System.lineSeparator() + 
							System.lineSeparator() + 
							"회원님의 개인정보보호를 위해 3년이상(휴면계정 전환 후 2년 이상) 장기 미사용 계정은" + System.lineSeparator() +
							"관계법령에 따라 개인정보를 파기(탈퇴) 처리할 예정입니다." + System.lineSeparator() + 
							System.lineSeparator() + 
							"개인정보 파기(탈퇴 처리) 후에는 서비스 이용이 불가하오니," + System.lineSeparator() + 
							"계속 이용하시고자 할 경우에는 개인정보 파기일 전까지" + System.lineSeparator() + 
							"홈페이지에 접속하여 본인확인 절차를 통해서 계정을 복원하여 주시기 바랍니다." + System.lineSeparator() + 
							System.lineSeparator() + 
							"1. 개인정보 파기일 : <#=SUB2#>" + System.lineSeparator() + 
							"2. 파기 정보 : 회원가입시 수집된 개인정보" + System.lineSeparator() + 
							"3. 관계법령 : 정보통신망 이용촉진 및 정보보호 등에 관한 법률 제29조" + System.lineSeparator();
			
					emailQuery = "INSERT INTO TB_INST_INPUT (instId, name, email, subs_value)"
							+ "VALUES ('164', " 
							+ "'" + userDto.getUserName() + "'," 
							+ "'" + userDto.getEmailAddress() + "'," 
							+ "'" + userDto.getUserName() + "|" + userDto.getThird_schedulDate() + "'" 
							+ ")";
					
					smsDescription = userDto.getUserName() + "님의 계정이 장기 미사용에 따라" + System.lineSeparator() + 
								userDto.getThird_schedulDate() +"에 개인정보 파기(탈퇴) 처리될 예정입니다." + System.lineSeparator() +
								"계속 이용하시고자 할 경우 처리일 전까지 복원하여 주시기 바랍니다." + System.lineSeparator() +
								"https://www.lpcrefia.or.kr" + System.lineSeparator();
					
					smsQuery = "INSERT INTO EM_TRAN(tran_phone, tran_callback, tran_status, tran_date, tran_msg, tran_etc1, tranEtc3)" 
							+ "VALUES (" + "'" + userDto.getPhoneNumber() + "'," 
							+ "'" + "000-000-0000" + "'," // 발신자 휴대전화번호 가이드 안됨
							+ "'" + "1" + "',"
							+ "'" + today + "',"
							+ "'" + smsDescription + "',"
							+ "'" + "10071" + "',"
							+ "'" + "" + "'"
							+ ")";						
				}
				// 3차 안내처리
				else 
				{
					// 사용안함
					emailDescription = "장기 미사용 계정 개인정보 파기(탈퇴) 처리 안내" + System.lineSeparator() + 
							System.lineSeparator() + 
							"<#=SUB1#>님 안녕하세요," + System.lineSeparator() + 
							System.lineSeparator() + 
							"회원님의 개인정보보호를 위해 3년이상(휴면계정 전환 후 2년 이상) 장기 미사용 계정은" + System.lineSeparator() +
							"관계법령에 따라 개인정보가 파기(탈퇴) 처리되었습니다." + System.lineSeparator() + 
							System.lineSeparator() + 
							"1. 개인정보 파기일 : <#=SUB2#>" + System.lineSeparator() + 
							"2. 파기 정보 : 회원가입시 수집된 개인정보" + System.lineSeparator() + 
							"3. 관계법령 : 정보통신망 이용촉진 및 정보보호 등에 관한 법률 제29조" + System.lineSeparator();
			
					emailQuery = "INSERT INTO TB_INST_INPUT (instId, name, email, subs_value)"
							+ "VALUES ('164', " 
							+ "'" + userDto.getUserName() + "'," 
							+ "'" + userDto.getEmailAddress() + "'," 
							+ "'" + userDto.getUserName() + "|" + userDto.getThird_schedulDate() + "'" 
							+ ")";
					
					smsDescription = userDto.getUserName() + "님의 계정이 장기 미사용에 따라" + System.lineSeparator() + 
								userDto.getThird_schedulDate() + "에 개인정보 파기(탈퇴) 처리되었습니다." + System.lineSeparator() +
								"https://www.lpcrefia.or.kr" + System.lineSeparator();
					
					smsQuery = "INSERT INTO EM_TRAN(tran_phone, tran_callback, tran_status, tran_date, tran_msg, tran_etc1, tranEtc3)" 
							+ "VALUES (" + "'" + userDto.getPhoneNumber() + "'," 
							+ "'" + "000-000-0000" + "'," // 발신자 휴대전화번호 가이드 안됨
							+ "'" + "1" + "',"
							+ "'" + today + "',"
							+ "'" + smsDescription + "',"
							+ "'" + "10071" + "',"
							+ "'" + "" + "'"
							+ ")";						
				}				
			}
		}
		
		// 휴면계정 및 탈퇴계정 처리 (2022-05-01 tkyoo - 이메일/문자 적용하여 수정)
		@Scheduled(cron = "0 0 0 * * *")
		public void dormantAccountProcessing() throws Exception 
		{
			// 오늘날짜(yyyy-MM-dd)
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			String todayStr = format.format(today);
			
			/*
			/////////////////////////////////// 휴면계정 전환로직 ///////////////////////////////////
			// 휴면계정 전환 대상 유저 리스트
			List<UserDto> targetUserList1 = userManageService.getDormantAccountProcessingList();
			
			// 휴면계정 전환 대상 유저 처리
			for (int i = 0; i < targetUserList1.size(); i++)
			{
				String firstDate = targetUserList1.get(i).getFirst_schedulDate();
				String secondDate = targetUserList1.get(i).getSecond_schedulDate();
				String thirdDate = targetUserList1.get(i).getThird_schedulDate();
				
				// 1차 처리예정 안내 (이메일, 문자)
				if (todayStr.equals(firstDate))
				{
					executeEmailAndSMS(targetUserList1.get(i), "dormantAccountProcessing", "first");
				}
				// 2차 처리예정 안내 (이메일, 문자)
				else if (todayStr.equals(secondDate))
				{
					executeEmailAndSMS(targetUserList1.get(i), "dormantAccountProcessing", "second");
				}
				// 3차 처리완료 안내 (이메일, 문자)
				else if (todayStr.equals(thirdDate))
				{
					executeEmailAndSMS(targetUserList1.get(i), "dormantAccountProcessing", "third");
					userService.dormantAccountProcessing(); // 최근 접속일 1년 지나서 휴면으로 업데이트 처리
				}
			}
			
			/////////////////////////////////// 탈퇴계정 전환로직 ///////////////////////////////////
			// 탈퇴계정 전환 대상 유저 리스트
			List<UserDto> targetUserList2 = userManageService.getSecessionDateProcessingList();
			
			// 탈퇴계정 전환 대상 유저 처리
			for (int i = 0; i < targetUserList2.size(); i++)
			{
				String firstDate = targetUserList2.get(i).getFirst_schedulDate();
				String secondDate = targetUserList2.get(i).getSecond_schedulDate();
				String thirdDate = targetUserList2.get(i).getThird_schedulDate();
				
				// 1차 처리예정 안내 (이메일, 문자)
				if (todayStr.equals(firstDate))
				{
					executeEmailAndSMS(targetUserList2.get(i), "secessionDateProcessing", "first");
				}
				// 2차 처리예정 안내 (이메일, 문자)
				else if (todayStr.equals(secondDate))
				{
					executeEmailAndSMS(targetUserList2.get(i), "secessionDateProcessing", "second");
				}
				// 3차 처리완료 안내 (이메일, 문자)
				else if (todayStr.equals(thirdDate))
				{
					executeEmailAndSMS(targetUserList2.get(i), "secessionDateProcessing", "third");
					userService.secessionDateProcessing(); // 휴면으로 전환된 후 2년 지나서 탈퇴로 업데이트 처리(최근 접속일 3년 지난것들)
				}
			}
			*/
			
			userService.dormantAccountProcessing(); // 최근 접속일 1년 지나서 휴면으로 처리
			userService.secessionDateProcessing(); // 휴면으로 전환된 후 2년 지나서 탈퇴로 처리(최근 접속일 3년 지난것들)
		}

		// 수료번호 api 호출
		@Scheduled(cron = "0 0 7 * * *")
		public void callSyncCertIdApiForSchedule() throws Exception {
			Calendar calToday = Calendar.getInstance();
			calToday.setTime(new Date());
	        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        String endDate = format.format(calToday.getTime());
	        calToday.add(Calendar.DATE, -2);
	        String startDate = format.format(calToday.getTime());
	        
			String rst = "";
			
			String url = "https://www.educrefia.or.kr/restapi/PassUsers?passStartDate=" + startDate + "&passEndDate=" + endDate;
			System.out.println(">>>>> callSyncCertIdApi url : " + url);
			try
			{
				URL clsUrl = new URL(url);
				HttpURLConnection clsConn = (HttpURLConnection)clsUrl.openConnection();
				clsConn.setRequestMethod("GET"); clsConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
				clsConn.setDoOutput(true);
				clsConn.connect();
				BufferedReader clsInput = new BufferedReader(new InputStreamReader(clsConn.getInputStream()));

				String inputLine = ""; 
				while((inputLine=clsInput.readLine())!=null){
					rst+=inputLine;
				}
				clsInput.close();
				JSONObject jo=new JSONObject(rst);
				rst=jo.getString("result");
			}catch(Exception e){ System.out.println(e);
			}
			
			JSONObject resultObj=new JSONObject(rst);
			String status = (String) resultObj.get("status");
			String statusMsg = (String) resultObj.get("statusMsg");
			String totalCount = resultObj.get("totalCount").toString();
			JSONArray items = (JSONArray) resultObj.get("items");

			StringEncrypter ecvrypterAES256 = new StringEncrypter(key, vector);
			EduCompletionHisDto eduCompletionHis = new EduCompletionHisDto();
			eduCompletionHis.setApiSyncId();
			System.out.println(">>>>> callSyncCertIdApi ApiSyncId : " + eduCompletionHis.getApiSyncId());
			
			List<EduCompletionDto> eduCompletionList = new ArrayList<EduCompletionDto>();
			for(int i=0; i<items.length(); i++){            
				JSONObject item = (JSONObject) items.get(i);
				EduCompletionDto eduCompletionInfo = new EduCompletionDto();
				String gender = ("1".equals(ecvrypterAES256.decrypt((String)item.get("user_sex")))) ? "남":"여";
				String birthDate = ecvrypterAES256.decrypt((String)item.get("user_birth"));
                if(birthDate.length()==8) {
                	birthDate = birthDate.substring(0,4)+"-"+birthDate.substring(4,6)+"-"+birthDate.substring(6,8);
                }else if(birthDate.length()==6) {
                	birthDate = "YY"+birthDate.substring(0,2)+"-"+birthDate.substring(2,4)+"-"+birthDate.substring(4,6);
                }
                
				eduCompletionInfo.setUserId("");
				eduCompletionInfo.setEduUserId((String)item.get("user_id"));
				eduCompletionInfo.setUserName(ecvrypterAES256.decrypt((String)item.get("user_name")));
				eduCompletionInfo.setBirthDate(birthDate);
				eduCompletionInfo.setGender(gender);
				eduCompletionInfo.setCertId((String)item.get("diploma_no"));
				eduCompletionInfo.setSubject((String)item.get("process_cd"));

				eduCompletionInfo.setApiSyncId(eduCompletionHis.getApiSyncId());
                eduCompletionList.add(eduCompletionInfo);
                /*
				if("Y".equals(userService.getEduCompletionInfo(eduCompletionInfo))) {
					eduCompletionInfo.setUpApiSyncId(eduCompletionHis.getApiSyncId());
					userService.updateEduComepletionInfo(eduCompletionInfo);
				}else {
					eduCompletionInfo.setApiSyncId(eduCompletionHis.getApiSyncId());
					userService.insertEduComepletionInfo(eduCompletionInfo);
				}
				*/
			}
			
			System.out.println("eduCompletionInfo.size1 : " + eduCompletionList.size() );
			userService.insertAndUpdateEduComepletionInfo(eduCompletionList);
						
			eduCompletionHis.setPassStartDate(startDate);
			eduCompletionHis.setPassEndDate(endDate);
			eduCompletionHis.setApiSyncType("S");
			
			userManageService.insertEduCompletionHis(eduCompletionHis);
		}
		
		public String getRamdomPwd() { 
			char[] charSet = new char[] {'0','1','2','3','4','5','6','7','8','9'}; 
			StringBuffer sb = new StringBuffer(); 
			SecureRandom sr = new SecureRandom(); 
			sr.setSeed(new Date().getTime()); 
			int idx = 0; 
			int len = charSet.length; 
			for (int i=0; i<5; i++) {
				idx = sr.nextInt(len); // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다. 
				sb.append(charSet[idx]); 
			} 
			return "lpcrefia" + sb.toString() + "!"; 
		}
		
		public String getRamdomPassword() { 
			char[] charSet = new char[] {'0','1','2','3','4','5','6','7','8','9',
					'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
					'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
					'!','@','#','$','%','^','&' }; 
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
		
		// id 중복체크
		@RequestMapping(value="/checkDiCode", method=RequestMethod.POST)
		@ResponseBody
		public void checkDiCode(UserDto userdto, HttpSession session, HttpServletResponse response) throws Exception {
			System.out.println(">>> checkDiCode ciCode : " + userdto.getCiCode());
			String result = userService.getUserByCIDI(userdto);
			response.getWriter().print(result);
			response.getWriter().flush();
			response.getWriter().close();
		}

		// 합격자리스트 API 호출 RETURN
		@RequestMapping(value = "/callApiSelectPassUser")
		@ResponseBody
		public void callApiSelectPassUser(String examDate, HttpSession session, HttpServletResponse response) throws Exception {
			if(!examDate.contains("2022-")) {
				response.setCharacterEncoding("UTF-8"); 
				response.setContentType("text/html;charset=UTF-8"); 
				response.getWriter().print("2022년도 데이터만 조회됩니다");
				return;
			}
			JSONObject result = new JSONObject();
			result.put("status", "200");
			result.put("statusMsg", "Success");
			StringEncrypter ecvrypterAES256 = new StringEncrypter(key, vector);
			
			if(examDate == null || "".equals(examDate)) {
				System.out.println("examDate 가 없습니다.");
				result.put("status", "401");
				result.put("statusMsg", "Unauthorized");
			}else if(!"-".equals(examDate.substring(4,5)) || !"-".equals(examDate.substring(7,8))) {
				System.out.println("형식이 맞지 않습니다.");
				result.put("status", "401");
				result.put("statusMsg", "Unauthorized");
			}
			
			List<ApplyDto> passUserList = (ArrayList<ApplyDto>) applyService.selectPassUser(examDate);
			try {
				JSONArray passUserArray = new JSONArray();
				for(int i=0; i<passUserList.size(); i++){

					JSONObject passUser= new JSONObject();
					passUser.put("row_num", i+1);
					passUser.put("user_name", ecvrypterAES256.encrypt(passUserList.get(i).getUserDto().getUserName()));
					passUser.put("user_birth", ecvrypterAES256.encrypt(passUserList.get(i).getUserDto().getBirthDate()));
					passUser.put("user_sex", ecvrypterAES256.encrypt(passUserList.get(i).getUserDto().getGender()));
					passUser.put("diploma_no", passUserList.get(i).getCertId());
					passUser.put("exam_date", passUserList.get(i).getExamDto().getGradeStartDate());
					passUser.put("exam_cert_no", passUserList.get(i).getGradeDto().getPassCertId());
					passUserArray.put(passUser);
				}
				result.put("items", passUserArray);
				result.put("totalCount", passUserList.size());
			} catch(Exception e) {
				System.out.println(e.toString());
				result.put("status", "401");
				result.put("statusMsg", "Unauthorized");
			} finally {
				String jsonString = JSONValue.toJSONString(result); 
				response.setCharacterEncoding("UTF-8"); 
				response.setContentType("text/html;charset=UTF-8"); 
				response.getWriter().print(jsonString);
			}
		}
}
