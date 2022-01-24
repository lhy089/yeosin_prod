package com.yeosin.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeosin.apply.ApplyDto;

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
	
	public List<BoardDto> getBoardList(String getBoardType) throws Exception {
		return boardDao.getBoardList(getBoardType);
	}
	
	
	public BoardDto getBoardInfo(BoardDto boardDto) throws Exception{
		boardDao.updateHitCnt(boardDto);
		return boardDao.getBoardInfo(boardDto);
	}
	
	public List<BoardDto> getNoticeListForMain() throws Exception{
		return boardDao.getNoticeListForMain();
	}
	
	public List<BoardDto> getBoardListBySearch(BoardDto boardDto) throws Exception{
		return boardDao.getBoardListBySearch(boardDto);
	}
}
