package com.yeosin.admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yeosin.apply.ApplyDto;
import com.yeosin.apply.ApplyPageMaker;
import com.yeosin.apply.ExamDto;
import com.yeosin.apply.ExamZoneDto;
import com.yeosin.apply.SubjectDto;
import com.yeosin.board.BoardDto;
import com.yeosin.board.FileDto;
import com.yeosin.board.PageMaker;
import com.yeosin.board.PopupDto;
import com.yeosin.board.PopupDtoPageMaker;
import com.yeosin.user.UserDto;
import com.yeosin.util.FileController;

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
	@RequestMapping(value = "/boardNoticeInput_action", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView boardNoticeInput_action(BoardDto boardDto, MultipartFile file, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			
			if (!fileName.equals("") && fileName != null && file.getSize() != 0) {
				String LocalFileName = Long.toString(System.currentTimeMillis()) + "_" + file.getOriginalFilename();
				String boardPath = request.getServletContext().getRealPath("/resources/boardFile");
				
				File copyFile = new File(boardPath, LocalFileName);
					
				if(!new File(boardPath).exists()) {
					new File(boardPath).mkdirs();
				}
					
				FileCopyUtils.copy(file.getBytes(), copyFile);
					
				int MaxFileIdNumber = boardManageService.getMaxFileId() + 1;
				String newFileId = "FILE" +  String.valueOf(MaxFileIdNumber);
				String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);
			
				FileDto fileDto = new FileDto();

				fileDto.setLocalFileName(LocalFileName);
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
				fileName = fileDto.getRealFileName();
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
	@RequestMapping(value = "/boardNoticeRevise_action", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView boardNoticeRevise_action(BoardDto boardDto, MultipartFile file, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
				if (!fileName.equals("") && fileName != null && file.getSize() != 0) {
					String LocalFileName = Long.toString(System.currentTimeMillis()) + "_" + file.getOriginalFilename();
					String boardPath = request.getServletContext().getRealPath("/resources/boardFile");
				
					File copyFile = new File(boardPath, LocalFileName);
						
					if(!new File(boardPath).exists()) {
						new File(boardPath).mkdirs();
					}
							
					FileCopyUtils.copy(file.getBytes(), copyFile);
						
					FileDto setFileDto = new FileDto();
						
					String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);
	
					setFileDto.setLocalFileName(LocalFileName);
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
				if (!fileName.equals("") && fileName != null && file.getSize() != 0) //파일을 새로 등록할때
				{
					String LocalFileName = Long.toString(System.currentTimeMillis()) + "_" + file.getOriginalFilename();
					String boardPath = request.getServletContext().getRealPath("/resources/boardFile");
					
					File copyFile = new File(boardPath, LocalFileName);
					
					if(!new File(boardPath).exists()) {
						new File(boardPath).mkdirs();
					}
						
					FileCopyUtils.copy(file.getBytes(), copyFile);
					
					int MaxFileIdNumber = boardManageService.getMaxFileId() + 1;
					String newFileId = "FILE" +  String.valueOf(MaxFileIdNumber);
					String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);
				
					FileDto newfileDto = new FileDto();

					newfileDto.setLocalFileName(LocalFileName);
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
	@RequestMapping(value = "/boardLibraryInput_action", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView boardLibraryInput_action(BoardDto boardDto, MultipartFile file, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

			if (!fileName.equals("") && fileName != null && file.getSize() != 0) {
				String LocalFileName = Long.toString(System.currentTimeMillis()) + "_" + file.getOriginalFilename();
				String boardPath = request.getServletContext().getRealPath("/resources/boardFile");
				
				File copyFile = new File(boardPath, LocalFileName);
				
				if(!new File(boardPath).exists()) {
					new File(boardPath).mkdirs();
				}
					
				FileCopyUtils.copy(file.getBytes(), copyFile);

				int MaxFileIdNumber = boardManageService.getMaxFileId() + 1;
				String newFileId = "FILE" + String.valueOf(MaxFileIdNumber);
				String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);

				FileDto fileDto = new FileDto();

				fileDto.setLocalFileName(LocalFileName);
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
				fileName = fileDto.getRealFileName();
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
	@RequestMapping(value = "/boardLibraryRevise_action", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView boardLibraryRevise_action(BoardDto boardDto, MultipartFile file, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
				if (!fileName.equals("") && fileName != null && file.getSize() != 0) {
					
					String LocalFileName = Long.toString(System.currentTimeMillis()) + "_" + file.getOriginalFilename();
					String boardPath = request.getServletContext().getRealPath("/resources/boardFile");
					
					File copyFile = new File(boardPath, LocalFileName);
					
					if(!new File(boardPath).exists()) {
						new File(boardPath).mkdirs();
					}
						
					FileCopyUtils.copy(file.getBytes(), copyFile);
					
					FileDto setFileDto = new FileDto();

					String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);

					setFileDto.setLocalFileName(LocalFileName);
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
				if (!fileName.equals("") && fileName != null && file.getSize() != 0) // 파일을 새로 등록할때
				{
					
					String LocalFileName = Long.toString(System.currentTimeMillis()) + "_" + file.getOriginalFilename();
					String boardPath = request.getServletContext().getRealPath("/resources/boardFile");
					
					File copyFile = new File(boardPath, LocalFileName);
					
					if(!new File(boardPath).exists()) {
						new File(boardPath).mkdirs();
					}
						
					FileCopyUtils.copy(file.getBytes(), copyFile);
					
					int MaxFileIdNumber = boardManageService.getMaxFileId() + 1;
					String newFileId = "FILE" + String.valueOf(MaxFileIdNumber);
					String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);

					FileDto newfileDto = new FileDto();

					newfileDto.setLocalFileName(LocalFileName);
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

   	// 팝업 등록, 수정
	@RequestMapping(value="/PopupSaveByAjax", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> PopupSaveByAjax(@RequestParam Map<String, Object> requestMap, HttpSession session, HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws Exception 
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
			
			if (requestMap.get("popupId") == null || String.valueOf(requestMap.get("popupId")).isEmpty() || requestMap.get("popupId").equals("null"))
				actionCode = "Save";
			else 
				actionCode = "Update";
			// 새로운 팝업 ID 채번
			int MaxPopupNumber = boardManageService.getMaxPopupId() + 1;
			String newPopupId = "popup" + String.valueOf(MaxPopupNumber); 
			String popupId = null;
			
			if (actionCode.equals("Save")) popupId = newPopupId;
			else popupId = String.valueOf(requestMap.get("popupId"));
				
			/////////////////////////// 파일 관련 프로세스 처리 ///////////////////////////	
			if (file != null)
			{
				FileDto getFileDto = boardManageService.getPopupFileInfo(String.valueOf(requestMap.get("file")));
				
				String fileName = String.valueOf(file.getOriginalFilename()); // 파일 다이얼로그로 등록
				String checkFileName = request.getParameter("fileName"); // 파일명 텍스트
				int fileSize = (int)file.getSize();

				// 파일이 이미 등록되어 있을때(수정, 삭제)
				if (getFileDto != null) 
				{
					// 수정
					if (!checkFileName.equals("") && checkFileName != null && file.getSize() != 0) 
					{
						String LocalFileName = Long.toString(System.currentTimeMillis()) + "_" + file.getOriginalFilename();
						String popupPath = request.getServletContext().getRealPath("/resources/popupFile");
						
						File copyFile = new File(popupPath, LocalFileName);
							
						if (!new File(popupPath).exists()) 
						{
							new File(popupPath).mkdirs();
						}
								
						FileCopyUtils.copy(file.getBytes(), copyFile);	
						
						FileDto updateFileDto = new FileDto();	
						String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);
						requestMap.put("fileId", String.valueOf(requestMap.get("fileId")));
		
						updateFileDto.setFileId(String.valueOf(requestMap.get("fileId")));
						updateFileDto.setLocalFileName(LocalFileName);
						updateFileDto.setRealFileName(fileName);
						updateFileDto.setFileExtsn(fileExtsn);
						updateFileDto.setBoardId(popupId);
						updateFileDto.setFileSize(fileSize);

						boardManageService.updatePopupFileInfo(updateFileDto);
					}
					// 삭제
					else if (checkFileName.equals("") || checkFileName == null)
					{
						boardManageService.deletePopupFileInfo(String.valueOf(requestMap.get("fileId")));
						requestMap.replace("fileId", null);
					}
					
				}
				// 파일등록이 안되어 있을때(저장)
				else 
				{
					// 저장
					if (!checkFileName.equals("") && checkFileName != null && file.getSize() != 0) // 파일을 새로 등록할때
					{
						String LocalFileName = Long.toString(System.currentTimeMillis()) + "_" + file.getOriginalFilename();
						String popupPath = request.getServletContext().getRealPath("/resources/popupFile");
						
						File copyFile = new File(popupPath, LocalFileName);
						
						if (!new File(popupPath).exists()) 
						{
							new File(popupPath).mkdirs();
						}
							
						FileCopyUtils.copy(file.getBytes(), copyFile);
						
						int MaxFileIdNumber = boardManageService.getMaxFileId() + 1;
						String newFileId = "FILE" +  String.valueOf(MaxFileIdNumber);
						String fileExtsn = fileName.substring(fileName.lastIndexOf('.') + 1);
						requestMap.put("fileId", newFileId);
					
						FileDto fileDto = new FileDto();

						fileDto.setLocalFileName(LocalFileName);
						fileDto.setRealFileName(fileName);
						fileDto.setBoardId(popupId);
						fileDto.setFileId(newFileId);
						fileDto.setFileExtsn(fileExtsn);
						fileDto.setFileSize(fileSize);
						boardManageService.savePopupFileInfo(fileDto);
					}			
				}	
			}
			/////////////////////////// 파일 관련 프로세스 처리 끝 ///////////////////////////
			
			// 저장일때
			if (actionCode.equals("Save"))
			{
				requestMap.replace("popupId", popupId);
				isSaveUpdateSuccess = boardManageService.setPopupSave(requestMap);
			}
			// 수정일때
			else 
			{
				isSaveUpdateSuccess = boardManageService.setPopupModify(requestMap);
			}

			if (isSaveUpdateSuccess == 1) isSuccess = true;
			else isSuccess = false;				
		}
		
		resultMap.put("isSuccess", isSuccess);

		return resultMap;
	}
	
   	// 팝업 삭제
	@RequestMapping(value="/PopupDeleteByAjax", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> PopupDeleteByAjax(@RequestParam(value="popupCheck[]") List<String> requestArray, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
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
			// 체크한 팝업에 대한 삭제로직
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("popupList", requestArray);
		
			int isDeleteSuccess = boardManageService.setPopupDelete(parameter);
			
			if (isDeleteSuccess == 1) isSuccess = true;
			else isSuccess = false;
		}
		
		resultMap.put("isSuccess", isSuccess);

		return resultMap;
	}
	
	// 팝업 리스트 화면
	@RequestMapping(value = "/PopupList", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView PopupList(PopupDto popupDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			// 페이징 데이터 준비(페이지당 데이터 목록수)
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("pageStart", popupDto.getPageStart());
			parameterMap.put("perPageNum", popupDto.getPerPageNum());

			// 팝업 리스트
			List<PopupDto> popupList = boardManageService.getPopupList(parameterMap);

			// 페이징 하기위한 데이터
			PopupDtoPageMaker pageMaker = new PopupDtoPageMaker();
			pageMaker.setPopupDto(popupDto);
			pageMaker.setTotalCount(boardManageService.getPopupListCount(parameterMap));
			
			for(int i = 0 ; i < popupList.size(); i++)
			{
				popupList.get(i).setPage(pageMaker.getPopupDto().getPage());
				popupList.get(i).setFileDto(boardManageService.getFileInfoByFileId(popupList.get(i).getFileId()));
			}

			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("popupList", popupList);
			mav.addObject("pageMaker", pageMaker);
			mav.addObject("popupDto", popupDto);
			mav.setViewName("admin/board_pop");
		}
		return mav;
	}
	
	// 팝업 추가 화면
	@RequestMapping(value = "/PopupInput", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView PopupInput(PopupDto popupDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();

		UserDto userInfo = (UserDto) session.getAttribute("loginUserInfo");

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
			mav.addObject("popupDto", popupDto);
			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.setViewName("admin/board_pop_input");
		}
		return mav;
	}

	// 팝업 수정 화면
	@RequestMapping(value = "/PopupRevise", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView PopupRevise(PopupDto popupDto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();

		UserDto userInfo = (UserDto) session.getAttribute("loginUserInfo");

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
			PopupDto popupInfo = boardManageService.getPopupInfo(popupDto);
			FileDto fileInfo = boardManageService.getFileInfoByFileId(popupInfo.getFileId());

			mav.addObject("isAlert", false);
			mav.addObject("isAlertNoAuth", false);
			mav.addObject("popupInfo", popupInfo);
			mav.addObject("fileInfo", fileInfo);
			mav.setViewName("admin/board_pop_revise");
		}
		return mav;
	}

}
