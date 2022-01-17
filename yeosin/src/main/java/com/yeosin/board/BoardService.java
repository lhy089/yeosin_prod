package com.yeosin.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired	
	private BoardDao BoardDao;
	
	@Autowired
	private FileDao FileDao;
	

	

}
