package com.yeosin.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardManageService {

	@Autowired	
	private BoardManageDao boardManageDao;
}
