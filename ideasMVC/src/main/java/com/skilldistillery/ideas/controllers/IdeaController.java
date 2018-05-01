package com.skilldistillery.ideas.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.skilldistillery.ideas.data.CommentDAO;
import com.skilldistillery.ideas.data.IdeaDAO;
import com.skilldistillery.ideas.data.ProfileDAO;
import com.skilldistillery.ideas.data.UserDAO;
import com.skilldistillery.ideasjpa.entities.Comment;
import com.skilldistillery.ideasjpa.entities.Idea;
import com.skilldistillery.ideasjpa.entities.Profile;
import com.skilldistillery.ideasjpa.entities.User;
import com.skilldistillery.ideasjpa.entities.UserDTO;

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
	@RequestMapping(path = "index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		List<Idea> ideaList = ideaDao.showAllIdeas();
		for (Idea idea : ideaList) {
			idea = ideaDao.assignLikes(idea);
		}
		mv.addObject("ideaList", ideaList);
		mv.setViewName("WEB-INF/views/index.jsp");

		return mv;
	}

	@RequestMapping(path = "sorting.do")
	public ModelAndView sortIdeasIndex(String sortChoice) {
		ModelAndView mv = new ModelAndView();
		List<Idea> ideaList = ideaDao.showAllIdeas();
		switch (sortChoice) {
		case "newest":
			ideaDao.sortIdeasByDateNewFirst(ideaList);
			break;
		case "oldest":
			ideaDao.sortIdeasByDateOldFirst(ideaList);
			break;
		case "like":
			ideaDao.sortByLikes(ideaList);
			break;
		case "dislike":
			ideaDao.sortByDisikes(ideaList);
			break;
		case "controversy":
			ideaDao.sortByContreversy(ideaList);
			break;
		case "username":
			ideaDao.sortByUsername(ideaList);
			break;
		}
		for (Idea idea : ideaList) {
			idea = ideaDao.assignLikes(idea);
		}
		mv.addObject("ideaList", ideaList);
		mv.setViewName("WEB-INF/views/index.jsp");

		return mv;
	}

	@RequestMapping(path = "sortComments.do")
	public ModelAndView sortComments(String sortChoice, int ideaId) {
		ModelAndView mv = new ModelAndView();
		List<Comment> commentList = commentDao.showCommentsByIdea(ideaId);
		switch (sortChoice) {
		case "newest":
			commentDao.sortCommentsByDateNewFirst(commentList);
			break;
		case "oldest":
			commentDao.sortCommentsByDateOldFirst(commentList);
			break;
		case "like":
			commentDao.sortByLikes(commentList);
			break;
		case "dislike":
			commentDao.sortByDisikes(commentList);
			break;
		case "controversy":
			commentDao.sortByContreversy(commentList);
			break;

		}
		Idea idea = ideaDao.showIdea(ideaId);
		idea = ideaDao.assignLikes(idea);
		mv.addObject("idea", idea);
		mv.addObject("comments", commentList);
		mv.setViewName("WEB-INF/views/idea.jsp");

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
			idea = ideaDao.assignLikes(idea);
			mv.addObject("idea", idea);
			mv.setViewName("WEB-INF/views/idea.jsp");
		}
		return mv;
	}

	@RequestMapping(path = "postIdea.do", method = RequestMethod.POST)
	public ModelAndView createIdea(String name, String content, int profileId, RedirectAttributes redir,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Idea createdIdea = new Idea();
		createdIdea.setName(name);
		createdIdea.setContent(content);
		createdIdea.setActive(true);
		Profile profile = profileDao.showProfile(profileId);
		ideaDao.create(createdIdea, profile);

		if (createdIdea.getId() == 0) {
			mv.addObject("message", "Idea not created!");

			mv.setViewName("WEB-INF/views/idea.jsp");
		} else {
			redir.addFlashAttribute("message", "Idea Created!");
			redir.addFlashAttribute("idea", createdIdea);
			mv.setViewName("redirect:redirectIdea.do");
		}

		return mv;
	}

	@RequestMapping(path = "likeIdea.do", method = RequestMethod.GET)
	public ModelAndView likeIdea(int iid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Idea idea = ideaDao.showIdea(iid);
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			mv.addObject("message", "Must be logged in to vote");
			List<Idea> ideaList = ideaDao.showAllIdeas();
			mv.addObject("ideaList", ideaList);
			mv.setViewName("index.do");
			return mv;

		}
		Profile profile = user.getProfile();
		try {
			ideaDao.createLike(idea, profile, true);
		} catch (Exception e) {
			ideaDao.updateLike(idea, profile, true);
		}
		mv.setViewName("index.do");
		return mv;
	}

	@RequestMapping(path = "dislikeIdea.do", method = RequestMethod.GET)
	public ModelAndView dislikeIdea(int iid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Idea idea = ideaDao.showIdea(iid);
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			mv.addObject("message", "Must be logged in to vote");
			List<Idea> ideaList = ideaDao.showAllIdeas();
			mv.addObject("ideaList", ideaList);
			mv.setViewName("WEB-INF/views/index.jsp");
			return mv;

		}
		Profile profile = user.getProfile();
		try {
			ideaDao.createLike(idea, profile, false);
		} catch (Exception e) {
			ideaDao.updateLike(idea, profile, false);
		}
		mv.setViewName("index.do");
		return mv;
	}

	@RequestMapping(path = "likeIdeaFromIdea.do", method = RequestMethod.GET)
	public ModelAndView likeIdeaFromIdea(int iid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Idea idea = ideaDao.showIdea(iid);
		List<Comment> comments = commentDao.showCommentsByIdea(iid);
		mv.addObject("comments", comments);
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			mv.addObject("message", "Must be logged in to vote");
			idea = ideaDao.assignLikes(idea);
			mv.addObject("idea", idea);
			mv.setViewName("WEB-INF/views/idea.jsp");
			return mv;

		}
		Profile profile = user.getProfile();
		try {
			ideaDao.createLike(idea, profile, true);
		} catch (Exception e) {
			ideaDao.updateLike(idea, profile, true);
		}
		idea = ideaDao.assignLikes(idea);
		mv.addObject("idea", idea);
		mv.setViewName("WEB-INF/views/idea.jsp");
		return mv;
	}

	@RequestMapping(path = "dislikeIdeaFromIdea.do", method = RequestMethod.GET)
	public ModelAndView dislikeIdeaFromIdea(int iid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Idea idea = ideaDao.showIdea(iid);
		List<Comment> comments = commentDao.showCommentsByIdea(iid);
		mv.addObject("comments", comments);
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			mv.addObject("message", "Must be logged in to vote");
			Integer likes = ideaDao.getLikes(idea);
			idea.setLikes(likes);
			Integer dislikes = ideaDao.getDislikes(idea);
			idea.setDislikes(dislikes);
			mv.addObject("idea", idea);
			mv.setViewName("WEB-INF/views/idea.jsp");
			return mv;

		}
		Profile profile = user.getProfile();
		try {
			ideaDao.createLike(idea, profile, false);
		} catch (Exception e) {
			ideaDao.updateLike(idea, profile, false);
		}
		idea = ideaDao.assignLikes(idea);
		mv.addObject("idea", idea);
		mv.setViewName("WEB-INF/views/idea.jsp");
		return mv;
	}

	@RequestMapping(path = "redirectIdea.do", method = RequestMethod.GET)
	public ModelAndView createdIdea(Idea idea, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		idea = ideaDao.assignLikes(idea);
		mv.addObject("idea", idea);
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
		UserDTO userDTO = new UserDTO();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/create.jsp");
		mv.addObject("userDTO", userDTO);
		return mv;
	}

	@RequestMapping(path = "toIdea.do", method = RequestMethod.GET)
	public ModelAndView goToIdea(@RequestParam(name = "iid") Integer ideaId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Idea idea = ideaDao.showIdea(ideaId);
		idea = ideaDao.assignLikes(idea);
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
		for (Idea idea : profileIdeas) {
			idea = ideaDao.assignLikes(idea);
		}
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
			session.setAttribute("loggedInUser", user);
			mv.addObject("user", user);
			mv.setViewName("index.do");
			return mv;
		}
	}

	@RequestMapping(path = "postComment.do", method = RequestMethod.POST)
	public ModelAndView postComment(String content, int ideaId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User user = (User) session.getAttribute("loggedInUser");
		Profile profile = user.getProfile();
		Idea idea = ideaDao.showIdea(ideaId);
		Comment comment = new Comment();
		comment.setContent(content);
		commentDao.create(comment, profile, idea);
		List<Comment> comments = commentDao.showCommentsByIdea(ideaId);
		mv.addObject("comments", comments);
		idea = ideaDao.assignLikes(idea);
		mv.addObject("idea", idea);
		mv.setViewName("WEB-INF/views/idea.jsp");
		return mv;
	}

	@RequestMapping(path = "logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("logoutMessage", "Logged out succesfully");
		session.removeAttribute("loggedInUser");
		mv.setViewName("index.do");
		return mv;
	}

	@RequestMapping(path = "createUser.do", method = RequestMethod.POST)
	public ModelAndView creatingUser(@Valid UserDTO userDTO, Errors errors, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		if (errors.getErrorCount() != 0) {

			mv.setViewName("WEB-INF/views/create.jsp");
			return mv;
		}

		if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
			mv.addObject("passwordMessage", "Passwwords did not match");
			mv.setViewName("WEB-INF/views/create.jsp");
			return mv;
		}
		if (!userDao.checkForExistingEmail(userDTO.getEmail())
				&& !userDao.checkForExistingUsername(userDTO.getUsername())) {
			User user = new User();
			user.setUsername(userDTO.getUsername());
			user.setEmail(userDTO.getEmail());
			user.setPassword(userDTO.getPassword());
			user.setActive(true);
			user.setAdmin(false);
			userDao.create(user);
			profileDao.makeActive(user.getProfile().getId());
			session.setAttribute("loggedInUser", user);
			mv.setViewName("redirect:toSettings.do");
		} else {
			mv.addObject("createUserMessage", "This email or username already exists");
			mv.setViewName("WEB-INF/views/create.jsp");
			return mv;
		}
		mv.setViewName("redirect:toSettings.do");
		return mv;
	}

	@RequestMapping(path = "toSettings.do", method = RequestMethod.GET)
	public ModelAndView directToSettings(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User userToUpdateProfile = (User) session.getAttribute("loggedInUser");
		Profile profileToUpdate = userToUpdateProfile.getProfile();
		mv.addObject("profile", profileToUpdate);
		mv.setViewName("WEB-INF/views/settings.jsp");
		return mv;
	}

	@RequestMapping(path = "update.do", method = RequestMethod.POST)
	public ModelAndView updateSettings(@RequestParam(name = "username") String newUsername,
			@RequestParam(name = "password") String newPassword, @RequestParam(name = "email") String newEmail,
			@RequestParam(name = "profilePic") String newProfilePic, @RequestParam(name = "bio") String newBio,
			HttpSession session) {
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
		} else {
			mv.addObject("message", "You do not have permission to deactivate this profile");
		}

		mv.addObject("pid", profile.getId());
		mv.setViewName("toProfile.do");
		return mv;
	}

	@RequestMapping(path = "deactivateIdea.do", method = RequestMethod.GET)
	public ModelAndView deactivateIdea(@RequestParam(name = "pid") Integer profileId,
			@RequestParam(name = "iid") Integer ideaId, HttpSession session) {
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
		} else {
			System.out.println("in deactivate idea.do");
			mv.addObject("message", "You do not have permission to deactivate this Idea");
		}
		mv.addObject("iid", ideaId);
		mv.setViewName("toIdea.do");
		return mv;
	}

	@RequestMapping(path = "deactivateComment.do", method = RequestMethod.GET)
	public ModelAndView deactivateComment(@RequestParam(name = "cid") Integer commentId,
			@RequestParam(name = "iid") Integer ideaId, HttpSession session) {
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
		} else {
			session.setAttribute("message", "You do not have permission to deactivate this comment");
		}
		mv.addObject("iid", ideaId);
		mv.addObject("cid", comment.getId());
		mv.setViewName("toIdea.do");
		return mv;
	}
}
