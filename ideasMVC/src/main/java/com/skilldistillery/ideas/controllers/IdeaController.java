package com.skilldistillery.ideas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.ideas.data.CommentDAO;
import com.skilldistillery.ideas.data.IdeaDAO;
import com.skilldistillery.ideas.data.ProfileDAO;
import com.skilldistillery.ideas.data.UserDAO;

@Controller
public class IdeaController {
	
	@Autowired
	private CommentDAO commentDao;
	@Autowired
	private IdeaDAO ideaDao;
	@Autowired
	private ProfileDAO profileDao;
	@Autowired
	private UserDAO userDao;

	
	// Home page
	@RequestMapping(path="index.do", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
//		List<Idea> ideaList = ideaDao.something();//SOMETHING
	//	mv.addObject("ideaList", ideaList);
		mv.setViewName("WEB-INF/views/index.jsp");
		
		  return mv;
	}
	
	
	
	
	
}
