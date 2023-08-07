package com.yeosin.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@RequestMapping(value="/join1", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView join1(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
        ModelAndView mav = new ModelAndView();
        mav.addObject("result", (Object)"");
        mav.setViewName("member/join1");
        return mav;
	}
	
	@RequestMapping(value="/join2", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView join2(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
        ModelAndView mav = new ModelAndView();
        mav.addObject("result", (Object)"");
        mav.setViewName("member/join2");
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
		System.out.println(">>> find_id_cert REQ_SEQ : " + (String)session.getAttribute("REQ_SEQ"));
		System.out.println(">>> find_id_cert RES_SEQ : " + (String)session.getAttribute("RES_SEQ"));
		if(userDto.getDiCode() !=null && userDto.getDiCode().equals((String)session.getAttribute("REQ_SEQ"))) {
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
		}
		
		session.removeAttribute("REQ_SEQ");
		System.out.println(">>> find_id_cert REQ_SEQ : " + (String)session.getAttribute("REQ_SEQ"));
		 
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
	@RequestMapping(value="/doJoin1", method=RequestMethod.POST)
	@ResponseBody
	public void doJoin1(UserDto user, HttpSession session, HttpServletResponse response) throws Exception {
		List<Object> userResult = new ArrayList<Object>();
        response.setCharacterEncoding("UTF-8");
        user.setPassword(EncryptUtils.getSha256(user.getPassword()));
        user.setUserStatus("U");
        System.out.println(">>> doJoin ciCode : " + user.getCiCode());
        int cnt = this.userService.insertUserInfo(user);
        if (cnt > 0) {
            userResult.add(this.userService.getLoginUserInfo(user));
        }
        new Gson().toJson((Object)userResult, (Appendable)response.getWriter());
	}

	// 회원가입 처리
	@RequestMapping(value="/doJoin", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView doJoin(UserDto user, HttpSession session, HttpServletResponse response) throws Exception {
		List<Object> userResult = new ArrayList<Object>();
        response.setCharacterEncoding("UTF-8");
        ModelAndView mav = new ModelAndView(); //
        String pattern1 = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&/])[A-Za-z[0-9]$@$!%*#?&/]{6,20}$";
        String pattern2 = "^[A-Za-z[0-9]]{6,20}$";
        String pattern3 = "^[[0-9]$@$!%*#?&/]{6,20}$";
        String pattern4 = "^[[A-Za-z]$@$!%*#?&/]{6,20}$";
        String patternId = "^[a-z]{1}[a-z0-9]{5,19}$";
        
        System.out.println(">>>>> doJoin user.getPassword() : " + user.getPassword());
        
        
        user.setUserId(user.getUserId().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
        if (!user.getUserId().matches(patternId) || (!user.getPassword().matches(pattern1) && !user.getPassword().matches(pattern2) && !user.getPassword().matches(pattern3) && !user.getPassword().matches(pattern4))) {
            mav.addObject("result", (Object)"N");
            mav.setViewName("member/joinFinish");
        }
        else if ("Y".equals(this.userService.getUserByCIANDDI(user))) {
            mav.addObject("result", (Object)"E");
            mav.setViewName("member/joinFinish");
        }
        else {
            user.setPassword(EncryptUtils.getSha256(user.getPassword()));
            user.setUserStatus("U");
            System.out.println(">>> doJoin ciCode : " + user.getCiCode());
            final int cnt = this.userService.insertUserInfo(user);
            if (cnt > 0) {
                mav.addObject("userInfo", (Object)this.userService.getLoginUserInfo(user));
                mav.setViewName("member/joinFinish");
            }
        }
        return mav;
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
	
	@RequestMapping(value = { "/pwdForWithdrawal" }, method = { RequestMethod.GET })
    @ResponseBody
    public ModelAndView pwdForWithdrawal(HttpSession session, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        ModelAndView mav = new ModelAndView();
        String sessionid = (String)session.getAttribute("loginId");
        mav.addObject("result", "");
        mav.setViewName("myroom/pwdForWithdrawal");
        return mav;
    }
	
	// 
	@RequestMapping(value="/doCheckPwd", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView doCheckPwd(UserDto user, HttpSession session, HttpServletResponse response) throws Exception {
		String result = "false";
        response.setCharacterEncoding("UTF-8");
        ModelAndView mav = new ModelAndView();
        UserDto userInfo = (UserDto)session.getAttribute("loginUserInfo");
        if(user.getPassword() == null) user.setPassword("lpcrefia1234!");
        userInfo.setPassword(EncryptUtils.getSha256(user.getPassword()));
        userInfo = this.userService.getLoginUserInfo(userInfo);
        if (userInfo != null) {
            mav.addObject("userInfo", userInfo);
            mav.addObject("result", "Y");
            mav.setViewName("myroom/change");
            return mav;
        }
        mav.addObject("result", "N");
        mav.setViewName("myroom/pwd");
        return mav;
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
		String sessionUserId = ((UserDto)session.getAttribute("loginUserInfo")).getUserId();
		if (!sessionUserId.equals(user.getUserId())) {
			response.getWriter().print(0);
		}
		String pattern1 = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{6,20}$";
		String pattern2 = "^[A-Za-z[0-9]]{6,20}$";
		String pattern3 = "^[[0-9]$@$!%*#?&]{6,20}$";
		String pattern4 = "^[[A-Za-z]$@$!%*#?&]{6,20}$";
		String patternId = "^[a-z]{1}[a-z0-9]{5,19}$";
		
		System.out.println(">>>>> doChange user.getPassword() : " + user.getPassword());
		
		user.setUserId(user.getUserId().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
		if (!user.getUserId().matches(patternId) || (!user.getPassword().matches(pattern1) && !user.getPassword().matches(pattern2) && !user.getPassword().matches(pattern3) && !user.getPassword().matches(pattern4))) {
			response.getWriter().print(0);
		}
		else {
			if (user.getPassword() != null && !"".equals(user.getPassword())) {
				user.setPassword(EncryptUtils.getSha256(user.getPassword()));
			}
			if (user.getOrgPwd() != null && !"".equals(user.getOrgPwd())) {
				user.setOrgPwd(EncryptUtils.getSha256(user.getOrgPwd()));
			}
			final int cnt = this.userService.updateUserInfo(user);
			response.getWriter().print(cnt);
		}
		response.getWriter().flush();
		response.getWriter().close();
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
		public void doWithdrawal(UserDto user, HttpSession session, HttpServletResponse response) throws Exception {
	        response.setCharacterEncoding("UTF-8");
	        UserDto userDto = (UserDto)session.getAttribute("loginUserInfo");
	        user.setUserId(userDto.getUserId());
	        user.setPassword(EncryptUtils.getSha256(user.getPassword()));
	        int cnt = this.userService.withdrawUser(user);
	        if (cnt > 0) {
	            session.removeAttribute("loginUserInfo");
	        }
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
                eduCompletionInfo.setCertDate((String)item.get("pass_date") +" 00:00:00");
                
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
					smsDescription = "장기 미접속 계정처리 방침에 따라 휴면계정 전환 예정입니다." + System.lineSeparator() + 
							"https://www.lpcrefia.or.kr" + System.lineSeparator();
					
					Map<String,String> smsData = new HashMap<>();
					smsData.put("tran_phone", userDto.getPhoneNumber());
					smsData.put("tran_msg", smsDescription);
					smsData.put("tran_etc1", "10071");
					this.sendSms(smsData);
					
					Map<String,String> mailData = new HashMap<>();
					mailData.put("instId", "165");
					mailData.put("name", userDto.getUserName());
					mailData.put("email", userDto.getEmailAddress());
					mailData.put("subs_value", userDto.getUserName() + "|" + userDto.getThird_schedulDate()); 
					this.sendEmail(mailData);					
				}
				// 3차 안내처리
				else 
				{	
					smsDescription = "장기 미접속 계정처리 방침에 따라 휴면계정 전환 되었습니다." + System.lineSeparator() + 
								"https://www.lpcrefia.or.kr" + System.lineSeparator();
					
					Map<String,String> smsData = new HashMap<>();
					smsData.put("tran_phone", userDto.getPhoneNumber());
					smsData.put("tran_msg", smsDescription);
					smsData.put("tran_etc1", "10072");
					this.sendSms(smsData);
					
					Map<String,String> mailData = new HashMap<>();
					mailData.put("instId", "166");
					mailData.put("name", userDto.getUserName());
					mailData.put("email", userDto.getEmailAddress());
					mailData.put("subs_value", userDto.getUserName() + "|" + userDto.getThird_schedulDate()); 
					this.sendEmail(mailData);				
				}
			}
			// 탈퇴
			else 
			{
				// 1차, 2차 안내처리
				if (order.equals("first") || order.equals("second"))
				{
					smsDescription = "장기 미접속 계정처리 방침에 따라 탈퇴 처리 예정입니다." + System.lineSeparator() + 
								"https://www.lpcrefia.or.kr" + System.lineSeparator();
					
					Map<String,String> smsData = new HashMap<>();
					smsData.put("tran_phone", userDto.getPhoneNumber());
					smsData.put("tran_msg", smsDescription);
					smsData.put("tran_etc1", "10073");
					this.sendSms(smsData);
					
					Map<String,String> mailData = new HashMap<>();
					mailData.put("instId", "167");
					mailData.put("name", userDto.getUserName());
					mailData.put("email", userDto.getEmailAddress());
					mailData.put("subs_value", userDto.getUserName() + "|" + userDto.getThird_schedulDate()); 
					this.sendEmail(mailData);					
				}
				// 3차 안내처리
				else 
				{				
					smsDescription = "장기 미접속 계정처리 방침에 따라 탈퇴(파기) 처리되었습니다." + System.lineSeparator() + 
								"https://www.lpcrefia.or.kr" + System.lineSeparator();
					
					Map<String,String> smsData = new HashMap<>();
					smsData.put("tran_phone", userDto.getPhoneNumber());
					smsData.put("tran_msg", smsDescription);
					smsData.put("tran_etc1", "10074");
					this.sendSms(smsData);
					
					Map<String,String> mailData = new HashMap<>();
					mailData.put("instId", "168");
					mailData.put("name", userDto.getUserName());
					mailData.put("email", userDto.getEmailAddress());
					mailData.put("subs_value", userDto.getUserName() + "|" + userDto.getThird_schedulDate()); 
					this.sendEmail(mailData);					
				}				
			}
		}
		
		// 휴면계정 및 탈퇴계정 처리 (2022-05-01 tkyoo - 이메일/문자 적용하여 수정)
		@Scheduled(cron = "0 05 0 * * *")
		public void dormantAccountProcessing() throws Exception 
		{
			// 오늘날짜(yyyy-MM-dd)
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			String todayStr = format.format(today);
			
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
					userService.dormantAccountProcessingByOneUser(targetUserList1.get(i).getUserId());
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
					userService.secessionDateProcessingByOneUser(targetUserList2.get(i).getUserId());
				}
			}
			
			//userService.dormantAccountProcessing(); // 최근 접속일 1년 지나서 휴면으로 처리
			//userService.secessionDateProcessing(); // 휴면으로 전환된 후 2년 지나서 탈퇴로 처리(최근 접속일 3년 지난것들)
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
				eduCompletionInfo.setCertDate((String)item.get("pass_date") +" 00:00:00");

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
		@RequestMapping(value = { "/checkCiDiCode" }, method = { RequestMethod.POST })
	    @ResponseBody
	    public ModelAndView checkCiDiCode(UserDto userdto, HttpSession session, HttpServletResponse response) throws Exception {
	        System.out.println(">>> checkDiCode ciCode : " + userdto.getCiCode());
	        ModelAndView mav = new ModelAndView();
	        String result = this.userService.getUserByCIDI(userdto);
	        if (!userdto.getFindType().equals(this.userService.insertCertInfo(userdto.getCiCode() + userdto.getDiCode()))) {
	            mav.addObject("noCert", (Object)"Y");
	            mav.setViewName("/member/join2");
	        }
	        else if ("N".equals(result)) {
	            String birthDate = userdto.getBirthDate().substring(0, 4) + "-" + userdto.getBirthDate().substring(4, 6) + "-" + userdto.getBirthDate().substring(6, 8);
	            String gender = "0".equals(userdto.getGender()) ? "\uc5ec" : "\ub0a8";
	            mav.addObject("isCert", (Object)"Y");
	            mav.addObject("birthDate", (Object)birthDate);
	            mav.addObject("gender", (Object)gender);
	            mav.addObject("userInfo", (Object)userdto);
	            mav.setViewName("/member/join3");
	        }
	        else {
	            mav.addObject("existUser", (Object)"Y");
	            mav.setViewName("/member/join2");
	        }
	        return mav;
	    }

		// 합격자리스트 API 호출 RETURN
		@RequestMapping(value = "/callApiSelectPassUser")
		@ResponseBody
		public void callApiSelectPassUser(String examDate, HttpSession session, HttpServletResponse response) throws Exception {
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
		
		@RequestMapping(value="/sendUserId", method=RequestMethod.GET)
		@ResponseBody
		public String checkUserInfoForFindUser(UserDto userData, HttpSession session, HttpServletResponse response) throws Exception {
			UserDto userInfo = userService.findUserInfo(userData);
			int result = 0;
			Map<String,String> data = new HashMap<>();
			
			if(userInfo != null) {
				if("H".equals(userData.getFindType())) {
					data.put("tran_phone", userInfo.getPhoneNumber());
					data.put("tran_msg", "[여신금융협회] 회원님의 아이디는 [" + userInfo.getUserId() + "] 입니다.");
					data.put("tran_etc1", "10075");
					result = this.sendSms(data);
				}
				else if("M".equals(userData.getFindType())) {
					data.put("instId", "169");
					data.put("name", userInfo.getUserName());
					data.put("email", userInfo.getEmailAddress());
					data.put("subs_value", userInfo.getUserId()); 
					result = this.sendEmail(data);
				}
				return "SUCCESS";
			}else {
				return "FAILED";
			}
		}
		
		@RequestMapping(value="/sendUserPwd", method=RequestMethod.GET)
		@ResponseBody
		public String sendUserPwd(UserDto userData, HttpSession session, HttpServletResponse response) throws Exception {
			UserDto userInfo = userService.findUserInfo(userData);
			int result = 0;
			Map<String,String> data = new HashMap<>();
			String password = this.getRamdomPwd();
			
			if(userInfo != null) {
				if("H".equals(userData.getFindType())) {
					data.put("tran_phone", userInfo.getPhoneNumber());
					data.put("tran_msg", "[여신금융협회] 비밀번호가 갱신 되었습니다. 회원님의 임시 비밀번호는 " + password + " 입니다.");
					data.put("tran_etc1", "10076");
					result = this.sendSms(data);
				}
				else if("M".equals(userData.getFindType())) {
					data.put("instId", "170");
					data.put("name", userInfo.getUserName());
					data.put("email", userInfo.getEmailAddress());
					data.put("subs_value", password); 
					result = this.sendEmail(data);
				}
				
				if(result>0) {
					userInfo.setPassword(EncryptUtils.getSha256(password));
					userService.updateUserPassword(userInfo);
				}
				return "SUCCESS";
			}else {
				return "FAILED";
			}
		}
		
		public int sendSms(Map<String,String> smsData){
			Connection con = null;
			PreparedStatement pstmt = null;
			int r = 0;
			
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url = "sqlserver://122.175.78.211:1433;DatabaseName=CEWEB";
			String user = "cemms";
			String pw = "cemms1234";
			
			/*String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://119.205.221.175:3306/yeosin";
			String user = "root";
			String pw = "hyong123";*/
			
			String sql = "INSERT INTO EM_TRAN (tran_phone, tran_callback, tran_status, tran_date, tran_msg, tran_etc1, tranEtc3) values(?, ?, ?, getdate(), ?, ?, ?)"; 
			
			try { 
				Class.forName(driver); 
				con = DriverManager.getConnection(url, user, pw); 
				pstmt = con.prepareStatement(sql); 

				pstmt.setString(1, smsData.get("tran_phone")); 
				pstmt.setString(2, "02-2011-0770"); //발신자번호
				pstmt.setString(3, "1");
				pstmt.setString(4, smsData.get("tran_msg"));
				pstmt.setString(5, smsData.get("tran_etc1"));
				pstmt.setString(6, "");
			
				r = pstmt.executeUpdate();
				System.out.println("변경된 row : " + r); 
				
			} catch (SQLException e) { 
				System.out.println("[SQL Error : " + e.getMessage() + "]"); 
			} catch (ClassNotFoundException e1) { 
				System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]"); 
			} finally { 
				try { 
					pstmt.close(); 
				} catch (SQLException e) { e.printStackTrace(); 
				} 
			} 
			if (con != null) { 
				try { con.close(); 
				} catch (SQLException e) { e.printStackTrace(); } 
			} 
			
			return r;
		}
		
		public int sendEmail(Map<String,String> mailData){
			Connection con = null;
			PreparedStatement pstmt = null;
			int r = 0;
			
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url = "sqlserver://122.175.78.211:1433;DatabaseName=CEWEB";
			String user = "cemms";
			String pw = "cemms1234";
			
			/*String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://119.205.221.175:3306/yeosin";
			String user = "root";
			String pw = "hyong123";*/
			
			String sql = "INSERT INTO TB_INST_INPUT (instId, name, email, subs_value) VALUES (?,?,?,?)";
			
			try { 
				Class.forName(driver); 
				con = DriverManager.getConnection(url, user, pw); 
				pstmt = con.prepareStatement(sql); 

				pstmt.setString(1, mailData.get("instId")); 
				pstmt.setString(2, mailData.get("name"));
				pstmt.setString(3, mailData.get("email"));
				pstmt.setString(4, mailData.get("subs_value"));
				
				r = pstmt.executeUpdate(); 
				System.out.println("변경된 row : " + r); 
				
			} catch (SQLException e) { 
				System.out.println("[SQL Error : " + e.getMessage() + "]"); 
			} catch (ClassNotFoundException e1) { 
				System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]"); 
			} finally { 
				try { 
					pstmt.close(); 
				} catch (SQLException e) { e.printStackTrace(); 
				} 
			} 
			if (con != null) { 
				try { con.close(); 
				} catch (SQLException e) { e.printStackTrace(); } 
			} 
			
			return r;
		}
}
