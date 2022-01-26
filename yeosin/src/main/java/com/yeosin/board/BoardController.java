package com.yeosin.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	//공지사항 리스트
	@RequestMapping(value="/notice")
	@ResponseBody
	public ModelAndView notice(BoardDto boardDto , HttpServletResponse response) throws Exception 
	{
		ModelAndView mav = new ModelAndView();	
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setBoardDto(boardDto);
		pageMaker.setTotalCount(boardService.countBoardListTotal(boardDto));
		
		List<BoardDto> noticeList = new ArrayList<>();
		noticeList = boardService.getBoardList(boardDto);

		mav.addObject("pageMaker", pageMaker);
		mav.addObject("noticeList", noticeList);	
		mav.addObject("boardDto", boardDto);
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
		int minNoticeSequence = boardService.getMinBoardSequence(boardDto.getBoardType());
		int maxNoticeSequence = boardService.getMaxBoardSequence(boardDto.getBoardType());
		noticeInfo.setPage(boardDto.getPage());
		
		mav.addObject("noticeInfo", noticeInfo);
		mav.addObject("minNoticeSequence", minNoticeSequence);
		mav.addObject("maxNoticeSequence", maxNoticeSequence);
		mav.setViewName("notice/notice_view");
		return mav;
	}
	
	//공지사항 이전
	@RequestMapping(value="/notice_previous")
	@ResponseBody
	public ModelAndView notice_previous(BoardDto boardDto) throws Exception{
		
		ModelAndView mav = new ModelAndView();

		BoardDto noticeInfo = new BoardDto();		
		noticeInfo = boardService.getPreviousBoardInfo(boardDto);
		int minNoticeSequence = boardService.getMinBoardSequence(boardDto.getBoardType()); 
		
		mav.addObject("noticeInfo", noticeInfo);
		mav.addObject("minNoticeSequence", minNoticeSequence);
		mav.setViewName("notice/notice_view");
		return mav;	
	}
	
	//공지사항 다음
	@RequestMapping(value="/notice_next")
	@ResponseBody
	public ModelAndView notice_next(BoardDto boardDto) throws Exception{
		
		ModelAndView mav = new ModelAndView();

		BoardDto noticeInfo = new BoardDto();		
		noticeInfo = boardService.getNextBoardInfo(boardDto);
		int maxNoticeSequence = boardService.getMaxBoardSequence(boardDto.getBoardType());
		
		mav.addObject("noticeInfo", noticeInfo);
		mav.addObject("maxNoticeSequence", maxNoticeSequence);
		mav.setViewName("notice/notice_view");
		return mav;	
	}
	
	
	//자주하는 질문
	@RequestMapping(value="/question")
	@ResponseBody
	public ModelAndView question(BoardDto boardDto , HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
		
		ModelAndView mav = new ModelAndView();	
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setBoardDto(boardDto);
		pageMaker.setTotalCount(boardService.countBoardListTotal(boardDto));
	
		List<BoardDto> questionList = new ArrayList<>();
		questionList = boardService.getBoardList(boardDto);
		
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("questionList", questionList);	
		mav.addObject("boardDto", boardDto);
		mav.setViewName("notice/question");
		return mav;
	}
	
	//시험자료실 리스트
	@RequestMapping(value="/library")
	@ResponseBody
	public ModelAndView library(BoardDto boardDto , HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding("UTF-8");
			
		ModelAndView mav = new ModelAndView();	
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setBoardDto(boardDto);
		pageMaker.setTotalCount(boardService.countBoardListTotal(boardDto));
		
		List<BoardDto> libraryList = new ArrayList<>();
		libraryList = boardService.getBoardList(boardDto);
			
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("libraryList", libraryList);	
		mav.addObject("boardDto", boardDto);
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
		int minLibrarySequence = boardService.getMinBoardSequence(boardDto.getBoardType()); 
		int maxLibrarySequence = boardService.getMaxBoardSequence(boardDto.getBoardType());
		libraryInfo.setPage(boardDto.getPage());
			
		mav.addObject("minLibrarySequence", minLibrarySequence);
		mav.addObject("maxLibrarySequence", maxLibrarySequence);
		mav.addObject("libraryInfo", libraryInfo);
		mav.setViewName("guide/library_view");
		return mav;
	}
	
	//시험자료실 검색하기
	@RequestMapping(value="/library_search")
	@ResponseBody
	public ModelAndView library_search(BoardDto boardDto) throws Exception{
			
		ModelAndView mav = new ModelAndView();
		String searchType = boardDto.getSearchType();
			
		List<BoardDto> libraryList = new ArrayList<>();
		libraryList = boardService.getBoardList(boardDto);
			
		mav.addObject("libraryList", libraryList);
		mav.addObject("searchType", searchType);
		mav.setViewName("guide/library");
		return mav;
	}
	
	//시험자료실 이전
	@RequestMapping(value="/library_previous")
	@ResponseBody
	public ModelAndView library_previous(BoardDto boardDto) throws Exception{
		
		ModelAndView mav = new ModelAndView();

		BoardDto libraryInfo = new BoardDto();		
		libraryInfo = boardService.getPreviousBoardInfo(boardDto);
		int minLibrarySequence = boardService.getMinBoardSequence(boardDto.getBoardType()); 
		
		mav.addObject("libraryInfo", libraryInfo);
		mav.addObject("minLibrarySequence", minLibrarySequence);
		mav.setViewName("guide/library_view");
		return mav;	
	}
	
	//시험자료실 다음
	@RequestMapping(value="/library_next")
	@ResponseBody
	public ModelAndView library_next(BoardDto boardDto) throws Exception{
			
		ModelAndView mav = new ModelAndView();

		BoardDto libraryInfo = new BoardDto();		
		libraryInfo = boardService.getNextBoardInfo(boardDto);
		int maxLibrarySequence = boardService.getMaxBoardSequence(boardDto.getBoardType());
			
		mav.addObject("libraryInfo", libraryInfo);
		mav.addObject("maxLibrarySequence", maxLibrarySequence);
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
