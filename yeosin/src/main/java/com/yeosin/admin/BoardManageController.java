package com.yeosin.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardManageController {
	
	 @Autowired
	  private BoardManageService boardManageService;
	
	//공지사항
	   @RequestMapping(value="/boardNotice", method=RequestMethod.GET)
	   @ResponseBody
	   public ModelAndView boardNotice()  
	   {
	      ModelAndView mav = new ModelAndView();      
	      mav.setViewName("admin/board_notice");
	      return mav;
	   }
	   
	   //시험 자료실
	   @RequestMapping(value="/boardLibrary", method=RequestMethod.GET)
	   @ResponseBody
	   public ModelAndView boardLibrary()  
	   {
	      ModelAndView mav = new ModelAndView();      
	      mav.setViewName("admin/board_library");
	      return mav;
	   }
	   
	   //자주하는 질문
	   @RequestMapping(value="/boardQuestion", method=RequestMethod.GET)
	   @ResponseBody
	   public ModelAndView boardQuestion()  
	   {
	      ModelAndView mav = new ModelAndView();      
	      mav.setViewName("admin/board_question");
	      return mav;
	   }

}
