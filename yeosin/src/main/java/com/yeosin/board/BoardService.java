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
		List<BoardDto> getBoardList = boardDao.getBoardList(boardDto);
		
		for(int i = 0 ; i < getBoardList.size(); i++)
			getBoardList.get(i).setContents(getBoardList.get(i).getContents().replaceAll("\r\n", "<br>").replaceAll(" ","&nbsp"));
		
		return getBoardList;

	}
	
	
	public BoardDto getBoardInfo(BoardDto boardDto) throws Exception{
		boardDao.updateHitCnt(boardDto);
		BoardDto getBoardDto = boardDao.getBoardInfo(boardDto);
		getBoardDto.setContents(getBoardDto.getContents().replaceAll("\r\n", "<br>").replaceAll(" ","&nbsp"));
		
		return getBoardDto;
	}
	
	public List<BoardDto> getNoticeListForMain() throws Exception{
		return boardDao.getNoticeListForMain();
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
	
	public FileDto getFileInfo(String fileId){
		return boardDao.getFileInfo(fileId);
	}
}
