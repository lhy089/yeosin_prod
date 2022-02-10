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
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yeosin.apply.ApplyService;
import com.yeosin.apply.ExamDto;
import com.yeosin.board.BoardDto;
import com.yeosin.board.BoardService;
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
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView index(HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();

		ExamDto examInfo = applyService.getExamInfoForMain();
		List<BoardDto> noticeList = boardService.getNoticeListForMain();

		mav.addObject("examInfo", examInfo);
		mav.addObject("noticeList", noticeList);
		mav.setViewName("/index");
		return mav;
	}
	
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
	
	// 본인 인증으로 id 찾기
	@RequestMapping(value="/find_id_cert", method=RequestMethod.GET)
	@ResponseBody
	public void find_id_cert(UserDto userDto, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		
		String userId = userService.findUserIdByCert(userDto);
	
		response.getWriter().print(userId);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	// 본인 인증으로 신규 비밀번호 발급
	@RequestMapping(value="/find_pwd_cert", method=RequestMethod.GET)
	@ResponseBody
	public void find_pwd_cert(UserDto userDto, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		String result = "null";
		UserDto userInfo = new UserDto();
		
		String userId = userService.findUserIdByCert(userDto);
		String password = getRamdomPassword(); //랜덤값
		
		userInfo.setUserId(userId);
		userInfo.setPassword(EncryptUtils.getSha256(password));
		
		int cnt= userService.updateUserPassword(userInfo);
	
		if(cnt>0) result = password;
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	@RequestMapping(value="/find_id_ok", method=RequestMethod.GET)
	@ResponseBody
	public void find_id_ok(UserDto userDto, HttpSession session, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		String sessionid= (String)session.getAttribute("loginId");
		
		String userId = userService.findUserId(userDto);
	
		response.getWriter().print(userId);
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

			for(int i=0; i<items.length(); i++){            
                JSONObject item = (JSONObject) items.get(i);
                EduCompletionDto eduCompletionInfo = new EduCompletionDto();
                String gender = ("1".equals(ecvrypterAES256.decrypt((String)item.get("user_sex")))) ? "남":"여";
                String birthDate = ecvrypterAES256.decrypt((String)item.get("user_birth"));
                if(birthDate.length()==8) {
                	birthDate = birthDate.substring(0,4)+"-"+birthDate.substring(4,6)+"-"+birthDate.substring(6,8);
                }else if(birthDate.length()==6) {
                	birthDate = "19"+birthDate.substring(0,2)+"-"+birthDate.substring(2,4)+"-"+birthDate.substring(4,6);
                }
                eduCompletionInfo.setUserId("");
                eduCompletionInfo.setEduUserId((String)item.get("user_id"));
                eduCompletionInfo.setUserName(ecvrypterAES256.decrypt((String)item.get("user_name")));
                eduCompletionInfo.setBirthDate(birthDate);
                eduCompletionInfo.setGender(gender);
                eduCompletionInfo.setCertId((String)item.get("diploma_no"));
                eduCompletionInfo.setSubject((String)item.get("process_cd"));
                
                if("Y".equals(userService.getEduCompletionInfo(eduCompletionInfo))) {
                	userService.updateEduComepletionInfo(eduCompletionInfo);
                }else {
                	userService.insertEduComepletionInfo(eduCompletionInfo);
                }
            }
			
			response.getWriter().print(totalCount);
			response.getWriter().flush();
			response.getWriter().close();
		}
		
		// 수료번호 api 호출
		@Scheduled(cron = "0 10 01 * * *")
		public void callSyncCertIdApiForSchedule() throws Exception {
			Calendar calToday = Calendar.getInstance();
			calToday.setTime(new Date());
	        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        String endDate = format.format(calToday.getTime());
	        calToday.add(Calendar.DATE, -3);
	        String startDate = format.format(calToday.getTime());
	        
			String rst = "";
			
			String url = "https://www.educrefia.or.kr/restapi/PassUsers?passStartDate=" + startDate + "&passEndDate=" + endDate;
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

			for(int i=0; i<items.length(); i++){            
				JSONObject item = (JSONObject) items.get(i);
				EduCompletionDto eduCompletionInfo = new EduCompletionDto();
				String gender = ("1".equals(ecvrypterAES256.decrypt((String)item.get("user_sex")))) ? "남":"여";
				String birthDate = ecvrypterAES256.decrypt((String)item.get("user_birth"));
                if(birthDate.length()==8) {
                	birthDate = birthDate.substring(0,4)+"-"+birthDate.substring(4,6)+"-"+birthDate.substring(6,8);
                }else if(birthDate.length()==6) {
                	birthDate = "19"+birthDate.substring(0,2)+"-"+birthDate.substring(2,4)+"-"+birthDate.substring(4,6);
                }
                
				eduCompletionInfo.setUserId("");
				eduCompletionInfo.setEduUserId((String)item.get("user_id"));
				eduCompletionInfo.setUserName(ecvrypterAES256.decrypt((String)item.get("user_name")));
				eduCompletionInfo.setBirthDate(birthDate);
				eduCompletionInfo.setGender(gender);
				eduCompletionInfo.setCertId((String)item.get("diploma_no"));
				eduCompletionInfo.setSubject((String)item.get("process_cd"));

				if("Y".equals(userService.getEduCompletionInfo(eduCompletionInfo))) {
					userService.updateEduComepletionInfo(eduCompletionInfo);
				}else {
					userService.insertEduComepletionInfo(eduCompletionInfo);
				}
			}
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
	
}
