package com.yeosin.board;

import java.util.List;
import java.util.Map;

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
		
		if(boardDto.getSearchType() == null)
			boardDto.setSearchType("");
		
		if(boardDto.getSearchWord() == null)
			boardDto.setSearchWord("");
		
		if(boardDto.getCategory() == null)
			boardDto.setCategory("");
		
		return boardDao.getBoardList(boardDto);
	}
	
	
	public BoardDto getBoardInfo(BoardDto boardDto) throws Exception{
		boardDao.updateHitCnt(boardDto);
		return boardDao.getBoardInfo(boardDto);
	}
	
	public BoardDto getPreviousBoardInfo(BoardDto boardDto) throws Exception{
		return boardDao.getPreviousBoardInfo(boardDto);
	}
	
	public BoardDto getNextBoardInfo(BoardDto boardDto) throws Exception{
		return boardDao.getNextBoardInfo(boardDto);
	}
	
	public List<BoardDto> getNoticeListForMain() throws Exception{
		return boardDao.getNoticeListForMain();
	}
	
	public int getMaxBoardSequence(String boardType) throws Exception{
		return boardDao.getMaxBoardSequence(boardType);
	}
	
	public int getMinBoardSequence(String boardType) throws Exception{
		return boardDao.getMinBoardSequence(boardType);
	}
	
	public int countBoardListTotal(BoardDto boardDto) {
		
		if(boardDto.getSearchType() == null)
			boardDto.setSearchType("");
		
		if(boardDto.getSearchWord() == null)
			boardDto.setSearchWord("");
		
		if(boardDto.getCategory() == null)
			boardDto.setCategory("");
		
		return boardDao.countBoardListTotal(boardDto);
	}
}
