package com.yeosin.board;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class BoardService {
	
	@Autowired	
	private BoardDao BoardDao;
	
	@Autowired
	private FileDao FileDao;
	
	@ResponseBody
	public BoardDto viewBoard(BoardDto board) {
		
		return BoardDao.viewBoard(board);
	}

}
