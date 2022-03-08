package com.yeosin.admin;

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

import com.yeosin.board.BoardDto;
import com.yeosin.board.FileDto;
import com.yeosin.board.PageMaker;
import com.yeosin.user.UserDto;

@Controller
public class BoardManageController {
	
	@Autowired
	private BoardManageService boardManageService;
	
	// 공지사항 
	@RequestMapping(value = "/boardNotice", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardNotice(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			List<BoardDto> noticeList  = boardManageService.getBoardList(boardDto);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setBoardDto(boardDto);
			pageMaker.setTotalCount(boardManageService.countBoardListTotal(boardDto));
			
			for(int i = 0 ; i < noticeList.size(); i++)
			{
				noticeList.get(i).setPage(pageMaker.getBoardDto().getPage());
				noticeList.get(i).setFileDto(boardManageService.getFileInfo(noticeList.get(i).getBoardId()));
			}

			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("boardDto", boardDto);
			mav.addObject("noticeList", noticeList);
			mav.setViewName("admin/board_notice");
		}
		return mav;
	}
	
	//공지사항 추가화면
	@RequestMapping(value = "/boardNoticeInput", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardNoticeInput(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			
			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.setViewName("admin/board_notice_input");
		}
		return mav;
	}
	
	//공지사항 추가하기
	@RequestMapping(value = "/boardNoticeInput_action", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardNoticeInput_action(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			
			//board 추가
			int MaxboardIdNumber = boardManageService.getMaxBoardId() + 1;
			String newboardId = "board" + String.valueOf(MaxboardIdNumber);
			boardDto.setBoardId(newboardId);

			int MaxboardSequenceNumber = boardManageService.getMaxBoardSequence(boardDto) + 1;
			boardDto.setBoardSequence(MaxboardSequenceNumber);

			boardDto.setUserId(userInfo.getUserId());

			boardManageService.insertBoardInfo(boardDto);
			//*board 추가
			
			//File 추가
			String fileName = request.getParameter("fileName");
			int fileSize = Integer.parseInt(request.getParameter("fileSize")) ;
			
			if (!fileName.equals("") && fileName != null) {
				
				int MaxFileIdNumber = boardManageService.getMaxFileId() + 1;
				String newFileId = "FILE" +  String.valueOf(MaxFileIdNumber);
				String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);
			
				FileDto fileDto = new FileDto();

				fileDto.setLocalFileName(fileName);
				fileDto.setRealFileName(fileName);
				fileDto.setBoardId(boardDto.getBoardId());
				fileDto.setFileId(newFileId);
				fileDto.setFileExtsn(fileExtsn);
				fileDto.setFileSize(fileSize);
				boardManageService.insertFileInfo(fileDto);
			}
			//*File 추가
			
			List<BoardDto> noticeList = boardManageService.getBoardList(boardDto);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setBoardDto(boardDto);
			pageMaker.setTotalCount(boardManageService.countBoardListTotal(boardDto));

			for (int i = 0; i < noticeList.size(); i++) {
				noticeList.get(i).setPage(pageMaker.getBoardDto().getPage());
				noticeList.get(i).setFileDto(boardManageService.getFileInfo(noticeList.get(i).getBoardId()));
			}

			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("boardDto", boardDto);
			mav.addObject("noticeList", noticeList);
			mav.setViewName("admin/board_notice");
		}
		return mav;
	}
	
	
	
	//공지사항 수정화면
	@RequestMapping(value = "/boardNoticeRevise", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardNoticeRevise(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

			BoardDto notice = boardManageService.getBoardInfo(boardDto);
			FileDto fileDto = boardManageService.getFileInfo(boardDto.getBoardId());
			String fileName = "";
			
			if(fileDto != null)
			{
				fileName = fileDto.getLocalFileName();
			}
			
			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("notice", notice);
			mav.addObject("boardDto", boardDto);
			mav.addObject("fileName",fileName);
			mav.setViewName("admin/board_notice_revise");
		}
		return mav;
	}
	
	//공지사항 수정하기
	@RequestMapping(value = "/boardNoticeRevise_action", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardNoticeRevise_action(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			
			//게시판 수정
			boardManageService.setBoardInfo(boardDto);
			
			//파일 수정
			FileDto getFileDto = boardManageService.getFileInfo(boardDto.getBoardId());
			
			String fileName = request.getParameter("fileName");
			int fileSize = Integer.parseInt(request.getParameter("fileSize")) ;
			
		
			if (getFileDto != null) //파일등록이 이미 되어 있을때 
			{
				//다른 파일로 교체 할때
				if (!fileName.equals("") && fileName != null && !getFileDto.getLocalFileName().equals(fileName)) {
					FileDto setFileDto = new FileDto();
					
					String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);

					setFileDto.setLocalFileName(fileName);
					setFileDto.setRealFileName(fileName);
					setFileDto.setFileExtsn(fileExtsn);
					setFileDto.setBoardId(boardDto.getBoardId());
					setFileDto.setFileSize(fileSize);

					boardDto.setFileDto(setFileDto);
					
					boardManageService.setFileInfo(setFileDto);
				}
				else if(fileName.equals("") || fileName == null)
				{
					boardManageService.deleteFileInfo(boardDto.getBoardId());
				}
				
			}
			else  //파일등록이 안되어 있을때
			{
				if (!fileName.equals("") && fileName != null) //파일을 새로 등록할때
				{
					int MaxFileIdNumber = boardManageService.getMaxFileId() + 1;
					String newFileId = "FILE" +  String.valueOf(MaxFileIdNumber);
					String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);
				
					FileDto newfileDto = new FileDto();

					newfileDto.setLocalFileName(fileName);
					newfileDto.setRealFileName(fileName);
					newfileDto.setBoardId(boardDto.getBoardId());
					newfileDto.setFileId(newFileId);
					newfileDto.setFileExtsn(fileExtsn);
					newfileDto.setFileSize(fileSize);
					boardManageService.insertFileInfo(newfileDto);
				}
				
			}
			//*파일 수정
			
			List<BoardDto> noticeList  = boardManageService.getBoardList(boardDto);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setBoardDto(boardDto);
			pageMaker.setTotalCount(boardManageService.countBoardListTotal(boardDto));
			
			for(int i = 0 ; i < noticeList.size(); i++)
			{
				noticeList.get(i).setPage(pageMaker.getBoardDto().getPage());
				noticeList.get(i).setFileDto(boardManageService.getFileInfo(noticeList.get(i).getBoardId()));
			}

			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("boardDto", boardDto);
			mav.addObject("noticeList", noticeList);
			mav.setViewName("admin/board_notice");
		}
		return mav;
	}
	
	// 공지사항 삭제하기
	@RequestMapping(value = "/boardNoticeDelete_action", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardNoticeDelete_action(BoardDto boardDto, @RequestParam(value = "boardCheck") List<String> requestArray, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			// 체크한 고사장에 대한 삭제로직

			boardManageService.deleteBoardList(requestArray);
			boardManageService.deleteFileList(requestArray);

			List<BoardDto> noticeList = boardManageService.getBoardList(boardDto);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setBoardDto(boardDto);
			pageMaker.setTotalCount(boardManageService.countBoardListTotal(boardDto));

			for (int i = 0; i < noticeList.size(); i++) {
				noticeList.get(i).setPage(pageMaker.getBoardDto().getPage());
				noticeList.get(i).setFileDto(boardManageService.getFileInfo(noticeList.get(i).getBoardId()));
			}

			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("boardDto", boardDto);
			mav.addObject("noticeList", noticeList);
			mav.setViewName("admin/board_notice");
		}
		return mav;
	}
	

	//시험 자료실
	@RequestMapping(value = "/boardLibrary", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardLibrary(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
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
			List<BoardDto> libraryList  = boardManageService.getBoardList(boardDto);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setBoardDto(boardDto);
			pageMaker.setTotalCount(boardManageService.countBoardListTotal(boardDto));
			
			for(int i = 0 ; i < libraryList.size(); i++)
			{
				libraryList.get(i).setPage(pageMaker.getBoardDto().getPage());
				libraryList.get(i).setFileDto(boardManageService.getFileInfo(libraryList.get(i).getBoardId()));
			}

			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("boardDto", boardDto);
			mav.addObject("libraryList", libraryList);
			mav.setViewName("admin/board_library");
		}
		return mav;
	}
	
	//시험 자료실 추가화면
	@RequestMapping(value = "/boardLibraryInput", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardLibraryInput(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			
			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.setViewName("admin/board_library_input");
		}
		return mav;
	}
	
	//시험 자료실 추가하기
	@RequestMapping(value = "/boardLibraryInput_action", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardLibraryInput_action(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

			// board 추가
			int MaxboardIdNumber = boardManageService.getMaxBoardId() + 1;
			String newboardId = "board" + String.valueOf(MaxboardIdNumber);
			boardDto.setBoardId(newboardId);

			int MaxboardSequenceNumber = boardManageService.getMaxBoardSequence(boardDto) + 1;
			boardDto.setBoardSequence(MaxboardSequenceNumber);

			boardDto.setUserId(userInfo.getUserId());

			boardManageService.insertBoardInfo(boardDto);
			// *board 추가

			// File 추가
			String fileName = request.getParameter("fileName");
			int fileSize = Integer.parseInt(request.getParameter("fileSize")) ;

			if (!fileName.equals("") && fileName != null) {

				int MaxFileIdNumber = boardManageService.getMaxFileId() + 1;
				String newFileId = "FILE" + String.valueOf(MaxFileIdNumber);
				String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);

				FileDto fileDto = new FileDto();

				fileDto.setLocalFileName(fileName);
				fileDto.setRealFileName(fileName);
				fileDto.setBoardId(boardDto.getBoardId());
				fileDto.setFileId(newFileId);
				fileDto.setFileExtsn(fileExtsn);
				fileDto.setFileSize(fileSize);
				boardManageService.insertFileInfo(fileDto);
			}
			// *File 추가

			List<BoardDto> libraryList = boardManageService.getBoardList(boardDto);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setBoardDto(boardDto);
			pageMaker.setTotalCount(boardManageService.countBoardListTotal(boardDto));

			for (int i = 0; i < libraryList.size(); i++) {
				libraryList.get(i).setPage(pageMaker.getBoardDto().getPage());
				libraryList.get(i).setFileDto(boardManageService.getFileInfo(libraryList.get(i).getBoardId()));
			}

			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("boardDto", boardDto);
			mav.addObject("libraryList", libraryList);
			mav.setViewName("admin/board_library");
		}
		return mav;
	}
	
	//시험 자료실  수정화면
	@RequestMapping(value = "/boardLibraryRevise", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardLibraryRevise(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			BoardDto library = boardManageService.getBoardInfo(boardDto);
			FileDto fileDto = boardManageService.getFileInfo(boardDto.getBoardId());
			String fileName = "";
			
			if(fileDto != null)
			{
				fileName = fileDto.getLocalFileName();
			}
			
			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("library", library);
			mav.addObject("boardDto", boardDto);
			mav.addObject("fileName",fileName);
			mav.setViewName("admin/board_library_revise");
		}
		return mav;
	}
	
	//시험 자료실 수정하기
	@RequestMapping(value = "/boardLibraryRevise_action", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardLibraryRevise_action(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

			// 게시판 수정
			boardManageService.setBoardInfo(boardDto);

			// 파일 수정
			FileDto getFileDto = boardManageService.getFileInfo(boardDto.getBoardId());

			String fileName = request.getParameter("fileName");
			int fileSize = Integer.parseInt(request.getParameter("fileSize")) ;

			if (getFileDto != null) // 파일등록이 이미 되어 있을때
			{
				// 다른 파일로 교체 할때
				if (!fileName.equals("") && fileName != null && !getFileDto.getLocalFileName().equals(fileName)) {
					FileDto setFileDto = new FileDto();

					String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);

					setFileDto.setLocalFileName(fileName);
					setFileDto.setRealFileName(fileName);
					setFileDto.setFileExtsn(fileExtsn);
					setFileDto.setBoardId(boardDto.getBoardId());
					setFileDto.setFileSize(fileSize);

					boardDto.setFileDto(setFileDto);

					boardManageService.setFileInfo(setFileDto);
				} else if (fileName.equals("") || fileName == null) {
					boardManageService.deleteFileInfo(boardDto.getBoardId());
				}

			} else // 파일등록이 안되어 있을때
			{
				if (!fileName.equals("") && fileName != null) // 파일을 새로 등록할때
				{
					int MaxFileIdNumber = boardManageService.getMaxFileId() + 1;
					String newFileId = "FILE" + String.valueOf(MaxFileIdNumber);
					String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);

					FileDto newfileDto = new FileDto();

					newfileDto.setLocalFileName(fileName);
					newfileDto.setRealFileName(fileName);
					newfileDto.setBoardId(boardDto.getBoardId());
					newfileDto.setFileId(newFileId);
					newfileDto.setFileExtsn(fileExtsn);
					newfileDto.setFileSize(fileSize);
					boardManageService.insertFileInfo(newfileDto);
				}

			}
			// *파일 수정

			List<BoardDto> libraryList = boardManageService.getBoardList(boardDto);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setBoardDto(boardDto);
			pageMaker.setTotalCount(boardManageService.countBoardListTotal(boardDto));

			for (int i = 0; i < libraryList.size(); i++) {
				libraryList.get(i).setPage(pageMaker.getBoardDto().getPage());
				libraryList.get(i).setFileDto(boardManageService.getFileInfo(libraryList.get(i).getBoardId()));
			}

			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("boardDto", boardDto);
			mav.addObject("libraryList", libraryList);
			mav.setViewName("admin/board_library");
		}
		return mav;
	}
	
	//시험 자료실 삭제하기
	@RequestMapping(value = "/boardLibraryDelete_action", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardLibraryDelete_action(BoardDto boardDto,@RequestParam(value = "boardCheck") List<String> requestArray, HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			// 체크한 고사장에 대한 삭제로직
			boardManageService.deleteBoardList(requestArray);
			boardManageService.deleteFileList(requestArray);

			List<BoardDto> libraryList = boardManageService.getBoardList(boardDto);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setBoardDto(boardDto);
			pageMaker.setTotalCount(boardManageService.countBoardListTotal(boardDto));

			for (int i = 0; i < libraryList.size(); i++) {
				libraryList.get(i).setPage(pageMaker.getBoardDto().getPage());
				libraryList.get(i).setFileDto(boardManageService.getFileInfo(libraryList.get(i).getBoardId()));
			}

			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("boardDto", boardDto);
			mav.addObject("libraryList", libraryList);
			mav.setViewName("admin/board_library");
		}
		return mav;
	}

	// 자주하는 질문
	@RequestMapping(value = "/boardQuestion", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardQuestion(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
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
			
			boardDto.setPerPageNum(5);
			
			List<BoardDto> questionList  = boardManageService.getBoardList(boardDto);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setBoardDto(boardDto);
			pageMaker.setTotalCount(boardManageService.countBoardListTotal(boardDto));
			
			for(int i = 0 ; i < questionList.size(); i++)
			{
				questionList.get(i).setPage(pageMaker.getBoardDto().getPage());
				questionList.get(i).setFileDto(boardManageService.getFileInfo(questionList.get(i).getBoardId()));
			}

			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("boardDto", boardDto);
			mav.addObject("questionList", questionList);
			mav.setViewName("admin/board_question");
		}
		return mav;
	}
	
	// 자주하는 질문 등록화면
	@RequestMapping(value = "/boardQuestionInput", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardQuestionInput(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			
			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.setViewName("admin/board_question_input");
		}
		return mav;
	}
	
	// 자주하는 질문 등록하기
	@RequestMapping(value = "/boardQuestionInput_action", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardQuestionInput_action(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		
			int MaxboardIdNumber = boardManageService.getMaxBoardId() + 1;
			String newboardId = "board" + String.valueOf(MaxboardIdNumber);
			boardDto.setBoardId(newboardId);
			
			int MaxboardSequenceNumber = boardManageService.getMaxBoardSequence(boardDto) + 1;
			boardDto.setBoardSequence(MaxboardSequenceNumber);
			
			boardDto.setUserId(userInfo.getUserId());
			
			boardManageService.insertBoardInfo(boardDto);
			
			boardDto.setPerPageNum(5);
			
			List<BoardDto> questionList  = boardManageService.getBoardList(boardDto);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setBoardDto(boardDto);
			pageMaker.setTotalCount(boardManageService.countBoardListTotal(boardDto));
			
			for(int i = 0 ; i < questionList.size(); i++)
			{
				questionList.get(i).setPage(pageMaker.getBoardDto().getPage());
				questionList.get(i).setFileDto(boardManageService.getFileInfo(questionList.get(i).getBoardId()));
			}
			
			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("boardDto", boardDto);
			mav.addObject("questionList", questionList);
			mav.setViewName("admin/board_question");
		}
		return mav;
	}
	
	// 자주하는 질문 수정화면
	@RequestMapping(value = "/boardQuestionRevise", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardQuestionRevise(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			mav.addObject("boardDto", boardDto);
			
			BoardDto question = boardManageService.getBoardInfo(boardDto);
			
			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("question", question);
			mav.addObject("boardDto", boardDto);
			mav.setViewName("admin/board_question_revise");
		}
		return mav;
	}
	
	//자주하는 질문 수정하기
	@RequestMapping(value = "/boardQuestionRevise_action", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardQuestionRevise_action(BoardDto boardDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			boardManageService.setBoardInfo(boardDto);
			
			boardDto.setPerPageNum(5);
			
			List<BoardDto> questionList  = boardManageService.getBoardList(boardDto);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setBoardDto(boardDto);
			pageMaker.setTotalCount(boardManageService.countBoardListTotal(boardDto));
			
			for(int i = 0 ; i < questionList.size(); i++)
			{
				questionList.get(i).setPage(pageMaker.getBoardDto().getPage());
				questionList.get(i).setFileDto(boardManageService.getFileInfo(questionList.get(i).getBoardId()));
			}

			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("boardDto", boardDto);
			mav.addObject("questionList", questionList);
			mav.setViewName("admin/board_question");
		}
		return mav;
	}
	
	//자주하는 질문 삭제하기
	@RequestMapping(value = "/boardQuestionDelete_action", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boardQuestionDelete_action(BoardDto boardDto, @RequestParam(value="boardCheck") List<String> requestArray, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			// 체크한 고사장에 대한 삭제로직
			
		
			boardManageService.deleteBoardList(requestArray);
		
			boardDto.setPerPageNum(5);
			
			List<BoardDto> questionList  = boardManageService.getBoardList(boardDto);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setBoardDto(boardDto);
			pageMaker.setTotalCount(boardManageService.countBoardListTotal(boardDto));
			
			for(int i = 0 ; i < questionList.size(); i++)
			{
				questionList.get(i).setPage(pageMaker.getBoardDto().getPage());
				questionList.get(i).setFileDto(boardManageService.getFileInfo(questionList.get(i).getBoardId()));
			}

			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("boardDto", boardDto);
			mav.addObject("questionList", questionList);
			mav.setViewName("admin/board_question");
		}
		return mav;
	}

}
