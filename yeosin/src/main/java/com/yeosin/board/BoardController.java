package com.yeosin.board;

import java.util.ArrayList;
import java.util.List;

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

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	//공지사항 리스트
	@RequestMapping(value="/notice", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView notice(@RequestParam("boardType") String boardType , HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		
		ModelAndView mav = new ModelAndView();	
	
		List<BoardDto> noticeList = new ArrayList<>();
		noticeList = boardService.getBoardList(boardType);
		
		mav.addObject("noticeList", noticeList);		
		mav.setViewName("notice/notice");
		return mav;
	}
	
	//공지사항 자세히보기
	@RequestMapping(value="/notice_view", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView notice_view(BoardDto boardDto)  throws Exception {
		ModelAndView mav = new ModelAndView();
		
		BoardDto noticeInfo = new BoardDto();
		
		noticeInfo = boardService.getBoardInfo(boardDto);
		
		mav.addObject("noticeInfo", noticeInfo);
		mav.setViewName("notice/notice_view");
		return mav;
	}
	
	//자주하는 질문
	@RequestMapping(value="/question", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView question(@RequestParam("boardType") String boardType , HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		
		ModelAndView mav = new ModelAndView();	
	
		List<BoardDto> noticeList = new ArrayList<>();
		noticeList = boardService.getBoardList(boardType);
		
		mav.addObject("questionList", noticeList);		
		mav.setViewName("notice/question");
		return mav;
	}
	
	//시험자료실 리스트
	@RequestMapping(value="/library", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView library(@RequestParam("boardType") String boardType , HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
			
		ModelAndView mav = new ModelAndView();	
		
		List<BoardDto> libraryList = new ArrayList<>();
		libraryList = boardService.getBoardList(boardType);
			
		mav.addObject("libraryList", libraryList);		
		mav.setViewName("guide/library");
		return mav;
	}
	
	//시험자료실 자세히보기
	@RequestMapping(value="/library_view", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView library_view(BoardDto boardDto)  throws Exception {
		ModelAndView mav = new ModelAndView();
			
		BoardDto libraryInfo = new BoardDto();
			
		libraryInfo = boardService.getBoardInfo(boardDto);
			
		mav.addObject("libraryInfo", libraryInfo);
		mav.setViewName("guide/library_view");
		return mav;
	}
	
	//평가소개
	@RequestMapping(value="/examGuide", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView examIntro()  
	{
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("guide/exam");
		return mav;
	}
	
	//응시안내
	@RequestMapping(value="/registerGuide", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView registerIntro()  
	{
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("guide/register");
		return mav;
	}
}
