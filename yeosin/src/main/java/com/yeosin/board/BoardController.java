package com.yeosin.board;

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
	public ModelAndView notice(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("notice/notice");
		return mav;
	}
	
	//공지사항 자세히보기
	@RequestMapping(value="/notice_view", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView notice_view(BoardDto boardDto){
		ModelAndView mav = new ModelAndView();
		
		//BoardDto boardInfo = boardService.viewBoard(boardDto);
		//mav.addObject(boardInfo); // Dto 담는 방법
		mav.setViewName("notice/notice_view");
		return mav;
	}
	
	//자주하는 질문
	@RequestMapping(value="/question", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView question(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/question");
		return mav;
	}
	
	
}
