package com.yeosin.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired	
	private BoardDao boardDao;
	
	@Autowired
	private FileDao FileDao;
	
	/*@ResponseBody
	public BoardDto viewBoard(BoardDto board) {
		
		return boardDao.viewBoard(board);
	}*/
	
	public List<BoardDto> getBoardList(BoardDto boardDto) throws Exception {
		
		boardDto = changeNullToEmpty(boardDto);
		return boardDao.getBoardList(boardDto);
	}
	
	
	public BoardDto getBoardInfo(BoardDto boardDto) throws Exception{
		boardDao.updateHitCnt(boardDto);
		return boardDao.getBoardInfo(boardDto);
	}
	
	public BoardDto getPreviousBoardInfo(BoardDto boardDto) throws Exception{
		
		boardDto = changeNullToEmpty(boardDto);
		return boardDao.getPreviousBoardInfo(boardDto);
	}
	
	public BoardDto getNextBoardInfo(BoardDto boardDto) throws Exception{
		
		boardDto = changeNullToEmpty(boardDto);
		return boardDao.getNextBoardInfo(boardDto);
	}
	
	public List<BoardDto> getNoticeListForMain() throws Exception{
		return boardDao.getNoticeListForMain();
	}
	
	public int getMaxBoardSequence(BoardDto boardDto) throws Exception{
		
		//boardDto = changeNullToEmpty(boardDto);
		int pageMaxBoardSequence = -1;
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setBoardDto(boardDto);
		pageMaker.setTotalCount(countBoardListTotal(boardDto));
		
		List<BoardDto> noticeList = new ArrayList<>();
		noticeList = getBoardList(boardDto);
		
		pageMaxBoardSequence = noticeList.get(pageMaker.getBoardDto().getPerPageNum()-1).getBoardSequence();
		
		return pageMaxBoardSequence;
	}
	
	public int getMinBoardSequence(BoardDto boardDto) throws Exception{
		
		int pageMinBoardSequence = -1;
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setBoardDto(boardDto);
		pageMaker.setTotalCount(countBoardListTotal(boardDto));
		
		List<BoardDto> noticeList = new ArrayList<>();
		noticeList = getBoardList(boardDto);
		
		pageMinBoardSequence = noticeList.get(0).getBoardSequence();
		
		return pageMinBoardSequence;
	}
	
	public HashMap<String, Object> getBoardSequence(BoardDto boardDto) throws Exception{
		
		HashMap<String, Object> hashMap = new HashMap<>();
		int pageMinBoardSequence = -1;
		int pageMaxBoardSequence = -1;
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setBoardDto(boardDto);
		pageMaker.setTotalCount(countBoardListTotal(boardDto));
		
		List<BoardDto> noticeList = new ArrayList<>();
		noticeList = getBoardList(boardDto);
		
		pageMinBoardSequence = noticeList.get(0).getBoardSequence();
		pageMaxBoardSequence = noticeList.get(noticeList.size()-1).getBoardSequence();
		
		int preBoardSequence = -1;
		int nextBoardSequence = -1;
		
		int i = -1;
		while(true) {
			i++;
			
			if(noticeList.get(i).getBoardSequence() == boardDto.getBoardSequence()) {	
				if(i - 1 >= 0)
					preBoardSequence = noticeList.get(i - 1).getBoardSequence();
				if(i + 1 <= noticeList.size()-1)
					nextBoardSequence = noticeList.get(i + 1).getBoardSequence();
				break;
			}
		}
		
		hashMap.put("pageMinBoardSequence", pageMinBoardSequence);
		hashMap.put("pageMaxBoardSequence", pageMaxBoardSequence);
		hashMap.put("preBoardSequence", preBoardSequence);
		hashMap.put("nextBoardSequence", nextBoardSequence);
		
		return hashMap;
		
	}
	
	public int countBoardListTotal(BoardDto boardDto) {
		
		boardDto = changeNullToEmpty(boardDto);
		return boardDao.countBoardListTotal(boardDto);
	}
	
	
	public BoardDto changeNullToEmpty(BoardDto boardDto) {
		
		if(boardDto.getSearchType() == null)
			boardDto.setSearchType("");
		
		if(boardDto.getSearchWord() == null)
			boardDto.setSearchWord("");
		
		if(boardDto.getCategory() == null)
			boardDto.setCategory("");
		
		return boardDto;
		
	}
}
