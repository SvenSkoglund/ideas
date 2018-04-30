package com.skilldistillery.ideas.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.skilldistillery.ideasjpa.entities.Comment;
import com.skilldistillery.ideasjpa.entities.Idea;
import com.skilldistillery.ideasjpa.entities.Profile;
import com.skilldistillery.ideasjpa.entities.User;

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

	// @RequestMapping(path = "update.do", method = RequestMethod.POST)
	// public ModelAndView updateIdea(@RequestParam(name = "idea") Idea idea) {
	// ModelAndView mv = new ModelAndView();
	// Idea oldIdea = em.find(Idea.class, idea.getId());
	// Idea newIdea = ideaDao.update(idea);
	//
	// if (oldIdea.equals(newIdea)) {
	// mv.addObject("message", "Idea not updated!");
	// mv.addObject("idea", newIdea);
	// mv.setViewName("WEB-INF/views/idea.jsp");
	// } else {
	// mv.addObject("message", "Idea Updated!");
	// mv.addObject("idea", newIdea);
	// mv.setViewName("WEB-INF/views/idea.jsp");
	// }
	//
	// return mv;
	// }

	@RequestMapping(path = "postIdea.do", method = RequestMethod.POST)
	public ModelAndView createIdea(@RequestParam(name = "idea") Idea idea, RedirectAttributes redir,
			HttpSession session) {
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

	@RequestMapping(path = "redirectIdea.do", method = RequestMethod.GET)
	public ModelAndView createdIdea(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/idea.jsp");
		return mv;
	}

	@RequestMapping(path = "toPostIdea.do", method = RequestMethod.GET)
	public ModelAndView goToPostIdea(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/postIdea.jsp");
		return mv;
	}

	@RequestMapping(path = "toToLogin.do", method = RequestMethod.GET)
	public ModelAndView goToLogin(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/login.jsp");
		return mv;
	}

	@RequestMapping(path = "toCreateAccount.do", method = RequestMethod.GET)
	public ModelAndView goToCreateAccount(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/create.jsp");
		return mv;
	}

	@RequestMapping(path = "toIdea.do", method = RequestMethod.GET)
	public ModelAndView goToIdea(@RequestParam(name = "iid") Integer ideaId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Idea idea = ideaDao.showIdea(ideaId);
		mv.addObject("idea", idea);
		List<Comment> comments = commentDao.showCommentsByIdea(ideaId);
		mv.addObject("comments", comments);
		mv.setViewName("WEB-INF/views/idea.jsp");
		return mv;
	}

	@RequestMapping(path = "toProfile.do", method = RequestMethod.GET)
	public ModelAndView goToProfile(@RequestParam(name = "pid") Integer profileId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Profile profile = profileDao.showProfile(profileId);
		mv.addObject("profile", profile);
		List<Idea> profileIdeas = ideaDao.showIdeasByProfile(profileId);
		mv.addObject("ideas", profileIdeas);
		int size = profileIdeas.size();
		mv.addObject("size", size);

		mv.setViewName("WEB-INF/views/profile.jsp");
		return mv;
	}

	@RequestMapping(path = "login.do", method = RequestMethod.POST)
	public ModelAndView login(String username, String password, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User user = userDao.findUserByUsernameAndPassword(username, password);
		if (user == null) {
			mv.addObject("message", "Account not found");
			mv.setViewName("WEB-INF/views/login.jsp");
			return mv;
		} else {
			List<Idea> ideaList = ideaDao.showAllIdeas();
			mv.addObject("ideaList", ideaList);
			session.setAttribute("loggedInUser", user);
			mv.addObject("user", user);
			mv.setViewName("WEB-INF/views/index.jsp");
			return mv;
		}
	}

	@RequestMapping(path = "logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("logoutMessage", "Logged out succesfully");
		session.removeAttribute("loggedInUser");
		mv.setViewName("index.do");
		return mv;
	}
	
	@RequestMapping(path = "createUser.do", method = RequestMethod.GET)
	public ModelAndView creatingUser(String username, String email, String password, String confirmPassword, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(!password.equals(confirmPassword)) {
			mv.addObject("passwordMessage", "Passwwords did not match");
			mv.setViewName("WEB-INF/views/create.jsp");
			return mv;
		}
		if (!userDao.checkForExistingEmail(email) && !userDao.checkForExistingUsername(username)) {
			User user = new User();
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setActive(true);
			user.setAdmin(false);
			userDao.create(user);
			profileDao.makeActive(user.getProfile().getId());
			session.setAttribute("loggedInUser", user);
			mv.setViewName("redirect:toSettings.do");
		}
		else {
			mv.addObject("createUserMessage", "This email or username already exists");
			mv.setViewName("WEB-INF/views/create.jsp");
			return mv;
		}
		mv.setViewName("redirect:toSettings.do");
		return mv;
	}
	
	@RequestMapping(path="toSettings.do", method = RequestMethod.GET)
	public ModelAndView directToSettings(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User userToUpdateProfile = (User) session.getAttribute("loggedInUser");
		Profile profileToUpdate = userToUpdateProfile.getProfile();
		mv.addObject("profile", profileToUpdate);
		mv.setViewName("WEB-INF/views/settings.jsp");
		return mv;
	}
	
	@RequestMapping(path="update.do", method = RequestMethod.POST)
	public ModelAndView updateSettings(@RequestParam(name="username") String newUsername, @RequestParam(name="password") String newPassword, @RequestParam(name="email") String newEmail, @RequestParam(name="profilePic") String newProfilePic, @RequestParam(name="bio") String newBio, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		User userUpdatingProfile = (User) session.getAttribute("loggedInUser");
		userUpdatingProfile.setUsername(newUsername);
		userUpdatingProfile.setPassword(newPassword);
		userUpdatingProfile.setEmail(newEmail);
		userDao.update(userUpdatingProfile);
		
		Profile profileToUpdate = userUpdatingProfile.getProfile();
		profileToUpdate.setProfilePic(newProfilePic);
		profileToUpdate.setBio(newBio);
		profileDao.update(profileToUpdate);
		
		
		mv.setViewName("redirect:index.do");
		return mv;
	}

	@RequestMapping(path = "deactivateProfile.do", method = RequestMethod.GET)
	public ModelAndView deactivateProfile(@RequestParam(name = "pid") Integer profileId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Profile profile = profileDao.showProfile(profileId);
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
		Profile profileLoggedIn = loggedInUser.getProfile();

			if (profileLoggedIn.getId() == profile.getId() || profileLoggedIn.getUser().isAdmin()) {
				mv.addObject("message", "Profile De-Activated");
				profileDao.makeInactive(profileId);
			} else {
				mv.addObject("message", "You do not have permission to deactivate this profile");
			}
		}else {
			mv.addObject("message", "You do not have permission to deactivate this profile");
		}

		mv.addObject("pid", profile.getId());
		mv.setViewName("toProfile.do");
		return mv;
	}
	
	@RequestMapping(path = "deactivateIdea.do", method = RequestMethod.GET)
	public ModelAndView deactivateIdea(@RequestParam(name = "pid") Integer profileId, @RequestParam(name = "iid") Integer ideaId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Profile profile = profileDao.showProfile(profileId);
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
		Profile profileLoggedIn = loggedInUser.getProfile();
			if (profileLoggedIn.getId() == profile.getId() || profileLoggedIn.getUser().isAdmin()) {
				mv.addObject("message", "Idea De-Activated");
				ideaDao.makeInactive(ideaId);
			} else {
				System.out.println("in deactivate idea.do");
				mv.addObject("message", "You do not have permission to deactivate this Idea");
			}
		}else {
			System.out.println("in deactivate idea.do");
			mv.addObject("message", "You do not have permission to deactivate this Idea");
		}
		mv.addObject("iid", ideaId);
		mv.setViewName("toIdea.do");
		return mv;
	}
	
	@RequestMapping(path = "deactivateComment.do", method = RequestMethod.GET)
	public ModelAndView deactivateComment(@RequestParam(name = "cid") Integer commentId, @RequestParam(name = "iid") Integer ideaId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Comment comment = commentDao.showComment(commentId);
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
		Profile profileLoggedIn = loggedInUser.getProfile();

		System.out.println("in deactivate comment.do");
			if (profileLoggedIn.getId() == comment.getProfile().getId() || profileLoggedIn.getUser().isAdmin()) {
				session.setAttribute("message", "Comment De-Activated");
				commentDao.makeInactive(commentId);
			} else {
				session.setAttribute("message", "You do not have permission to deactivate this comment");
			}
		}else {
			session.setAttribute("message", "You do not have permission to deactivate this comment");
		}
		mv.addObject("iid", ideaId);
		mv.addObject("cid", comment.getId());
		mv.setViewName("toIdea.do");
		return mv;
	}
}
