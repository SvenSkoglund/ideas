package com.skilldistillery.ideas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.ideas.data.CommentDAO;
import com.skilldistillery.ideas.data.IdeaDAO;
import com.skilldistillery.ideas.data.ProfileDAO;
import com.skilldistillery.ideas.data.UserDAO;
import com.skilldistillery.ideasjpa.entities.Idea;

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
	@RequestMapping(path = "index.do", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		 List<Idea> ideaList = ideaDao.showAllIdeas();
		 mv.addObject("ideaList", ideaList);
		mv.setViewName("WEB-INF/views/index.jsp");

		return mv;
	}

	@RequestMapping(path = "destoryIdea.do", method = RequestMethod.POST)
	public ModelAndView deleteIdea(@RequestParam(name = "idea") Idea idea) {
		ModelAndView mv = new ModelAndView();
		Boolean result = ideaDao.destroy(idea);
		if (result) {
			mv.setViewName("redirect:index.do");
		} else {
			mv.addObject("message", "Idea not deleted!");
			mv.addObject("idea", idea);
			mv.setViewName("WEB-INF/views/idea.jsp");
		}
		return mv;
	}

//	@RequestMapping(path = "update.do", method = RequestMethod.POST)
//	public ModelAndView updateIdea(@RequestParam(name = "idea") Idea idea) {
//		ModelAndView mv = new ModelAndView();
//		Idea oldIdea = em.find(Idea.class, idea.getId());
//		Idea newIdea = ideaDao.update(idea);
//
//		if (oldIdea.equals(newIdea)) {
//			mv.addObject("message", "Idea not updated!");
//			mv.addObject("idea", newIdea);
//			mv.setViewName("WEB-INF/views/idea.jsp");
//		} else {
//			mv.addObject("message", "Idea Updated!");
//			mv.addObject("idea", newIdea);
//			mv.setViewName("WEB-INF/views/idea.jsp");
//		}
//
//		return mv;
//	}

	@RequestMapping(path = "postIdea.do", method = RequestMethod.POST)
	public ModelAndView createIdea(@RequestParam(name = "idea") Idea idea, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		Idea createdIdea = ideaDao.create(idea);

		if (createdIdea.getId() == 0) {
			mv.addObject("message", "Idea not created!");

			mv.setViewName("WEB-INF/views/idea.jsp");
		} else {
			redir.addAttribute("idea", createdIdea);
			redir.addFlashAttribute("message", "Idea Created!");
			mv.setViewName("redirect:redirectIdea.do");
		}

		return mv;
	}

	@RequestMapping(path="redirectIdea.do", method = RequestMethod.GET)
	public ModelAndView createdIdea() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/idea.jsp");
		return mv;
	}
//	@RequestMapping(path="toPostIdea.do", method = RequestMethod.GET)
//	public ModelAndView goToPostIdea() {
//		 ModelAndView mv = new  ModelAndView();
//		 mv.setViewName("WEB-INF/views/postIdea.jsp");
//		 return mv;
//	}
//	@RequestMapping(path="toToLogin.do", method = RequestMethod.GET)
//	public ModelAndView goToLogin() {
//		ModelAndView mv = new  ModelAndView();
//		mv.setViewName("WEB-INF/views/login.jsp");
//		return mv;
//	}
	@RequestMapping(path="toCreateAccount.do", method = RequestMethod.GET)
	public ModelAndView goToCreateAccount() {
		ModelAndView mv = new  ModelAndView();
		mv.setViewName("WEB-INF/views/create.jsp");
		return mv;
	}
	@RequestMapping(path="toIdea.do", method = RequestMethod.GET)
	public ModelAndView goToIdea(@RequestParam(name="iid") Integer ideaId) {
		ModelAndView mv = new  ModelAndView();
		Idea idea = ideaDao.showIdea(ideaId);
		mv.addObject("comments", idea.getComments());
		mv.setViewName("WEB-INF/views/idea.jsp");
		return mv;
	}
}
