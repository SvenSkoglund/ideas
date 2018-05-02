package com.skilldistillery.ideas.controllers;

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
	public ModelAndView index(String ideaKeyword) {
		ModelAndView mv = new ModelAndView();
		List<Idea> ideaList = ideaDao.showAllIdeas();
		for (Idea idea : ideaList) {
			idea = ideaDao.assignLikes(idea);
		}
		mv.addObject("ideaKeyword", ideaKeyword);
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
		mv.addObject("sortChoice", sortChoice);
		mv.addObject("ideaList", ideaList);
		mv.setViewName("WEB-INF/views/index.jsp");

		return mv;
	}

	@RequestMapping(path = "searchSorting.do")
	public ModelAndView searchSorting(String sortChoice, String ideaKeyword) {
		ModelAndView mv = new ModelAndView();
		List<Idea> ideaList = ideaDao.searchIdea(ideaKeyword);
		if (sortChoice != null) {
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
		}
		for (Idea idea : ideaList) {
			idea = ideaDao.assignLikes(idea);
		}
		mv.addObject("sortChoice", sortChoice);
		mv.addObject("ideaKeyword", ideaKeyword);
		mv.addObject("ideaList", ideaList);
		mv.setViewName("WEB-INF/views/index.jsp");

		return mv;
	}

	@RequestMapping(path = "sortComments.do")
	public ModelAndView sortComments(String sortChoice, int ideaId) {
		ModelAndView mv = new ModelAndView();
		List<Comment> comments = commentDao.showCommentsByIdea(ideaId);
		switch (sortChoice) {
		case "newest":
			commentDao.sortCommentsByDateNewFirst(comments);
			break;
		case "oldest":
			commentDao.sortCommentsByDateOldFirst(comments);
			break;
		case "like":
			commentDao.sortByLikes(comments);
			break;
		case "dislike":
			commentDao.sortByDisikes(comments);
			break;
		case "controversy":
			commentDao.sortByContreversy(comments);
			break;

		}
		Idea idea = ideaDao.showIdea(ideaId);
		idea = ideaDao.assignLikes(idea);
		mv.addObject("idea", idea);
		for (Comment c : comments) {
			c = commentDao.assignLikes(c);
		}
		mv.addObject("comments", comments);
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
			mv.addObject("ideaNotDeletedMessage", "Idea not deleted!");
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
		Idea idea = new Idea();
		idea.setName(name);
		idea.setContent(content);
		idea.setActive(true);
		Profile profile = profileDao.showProfile(profileId);
		ideaDao.create(idea, profile);

		if (idea.getId() == 0) {
			mv.addObject("message", "Idea not created!");

			mv.setViewName("WEB-INF/views/idea.jsp");
		} else {
			redir.addFlashAttribute("ideaCreatedMessage", "Idea Created!");
			redir.addFlashAttribute("idea", idea);
			mv.setViewName("redirect:redirectIdea.do");
		}

		return mv;
	}

	@RequestMapping(path = "likeIdea.do", method = RequestMethod.GET)
	public ModelAndView likeIdea(int iid, String ideaKeyword, String sortChoice, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Idea idea = ideaDao.showIdea(iid);
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			mv.addObject("mustBeLoggedInMessage", "Must be logged in to vote");
			List<Idea> ideaList = ideaDao.showAllIdeas();
			mv.addObject("sortChoice", sortChoice);
			mv.addObject("ideaKeyword", ideaKeyword);
			mv.addObject("ideaList", ideaList);
			if (ideaKeyword != null || !ideaKeyword.equals("")) {
				mv.setViewName("searchSorting.do");
			} else if (sortChoice != null || !sortChoice.equals("")) {
				mv.setViewName("sorting.do");
			} else {
				mv.setViewName("index.do");
			}
			return mv;

		}
		Profile profile = user.getProfile();
		try {
			ideaDao.createLike(idea, profile, true);
		} catch (Exception e) {
			ideaDao.updateLike(idea, profile, true);
		}
		if (ideaKeyword.equals("") || ideaKeyword == null) {
			mv.addObject("ideaKeyword", ideaKeyword);
			mv.addObject("sortChoice", sortChoice);
			mv.setViewName("sorting.do");
		} else {
			mv.addObject("sortChoice", sortChoice);
			mv.addObject("ideaKeyword", ideaKeyword);
			mv.setViewName("searchSorting.do");
		}
		return mv;
	}

	@RequestMapping(path = "dislikeIdea.do", method = RequestMethod.GET)
	public ModelAndView dislikeIdea(int iid, String ideaKeyword, String sortChoice, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Idea idea = ideaDao.showIdea(iid);
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			mv.addObject("mustBeLoggedInMessage", "Must be logged in to vote");
			List<Idea> ideaList = ideaDao.showAllIdeas();
			mv.addObject("sortChoice", sortChoice);
			mv.addObject("ideaKeyword", ideaKeyword);
			mv.addObject("ideaList", ideaList);
			if (ideaKeyword != null || !ideaKeyword.equals("")) {
				mv.setViewName("searchSorting.do");
			} else if (sortChoice != null || !sortChoice.equals("")) {
				mv.setViewName("sorting.do");
			} else {
				mv.setViewName("index.do");
			}
			return mv;

		}
		Profile profile = user.getProfile();
		try {
			ideaDao.createLike(idea, profile, false);
		} catch (Exception e) {
			ideaDao.updateLike(idea, profile, false);
		}
		if (ideaKeyword.equals("") || ideaKeyword == null) {
			mv.addObject("ideaKeyword", ideaKeyword);
			mv.addObject("sortChoice", sortChoice);
			mv.setViewName("sorting.do");
		} else {
			mv.addObject("sortChoice", sortChoice);
			mv.addObject("ideaKeyword", ideaKeyword);
			mv.setViewName("searchSorting.do");
		}
		return mv;
	}

	@RequestMapping(path = "likeComment.do", method = RequestMethod.GET)
	public ModelAndView likeComment(int cid, int iid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Comment comment = commentDao.showComment(cid);
		Idea idea = ideaDao.showIdea(iid);
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			mv.addObject("mustBeLoggedInMessage", "Must be logged in to vote");
			idea = ideaDao.assignLikes(idea);
			mv.addObject("idea", idea);
			List<Comment> comments = commentDao.showCommentsByIdea(iid);
			for (Comment c : comments) {
				c = commentDao.assignLikes(c);
			}
			mv.addObject("comments", comments);
			mv.setViewName("WEB-INF/views/idea.jsp");
			return mv;

		}
		Profile profile = user.getProfile();
		try {
			commentDao.createLike(comment, profile, true);
		} catch (Exception e) {
			commentDao.updateLike(comment, profile, true);
		}
		comment = commentDao.assignLikes(comment);
		idea = ideaDao.assignLikes(idea);
		mv.addObject("idea", idea);
		List<Comment> comments = commentDao.showCommentsByIdea(iid);
		for (Comment c : comments) {
			c = commentDao.assignLikes(c);
		}

		mv.addObject("comments", comments);
		mv.setViewName("WEB-INF/views/idea.jsp");
		return mv;
	}

	@RequestMapping(path = "dislikeComment.do", method = RequestMethod.GET)
	public ModelAndView dislikeComment(int cid, int iid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Comment comment = commentDao.showComment(cid);
		Idea idea = ideaDao.showIdea(iid);
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			mv.addObject("mustBeLoggedInMessage", "Must be logged in to vote");
			idea = ideaDao.assignLikes(idea);
			mv.addObject("idea", idea);
			List<Comment> comments = commentDao.showCommentsByIdea(iid);
			for (Comment c : comments) {
				c = commentDao.assignLikes(c);
			}
			mv.addObject("comments", comments);
			mv.setViewName("WEB-INF/views/idea.jsp");
			return mv;

		}
		Profile profile = user.getProfile();
		try {
			commentDao.createLike(comment, profile, false);
		} catch (Exception e) {
			commentDao.updateLike(comment, profile, false);
		}
		comment = commentDao.assignLikes(comment);
		idea = ideaDao.assignLikes(idea);
		mv.addObject("idea", idea);
		List<Comment> comments = commentDao.showCommentsByIdea(iid);
		for (Comment c : comments) {
			c = commentDao.assignLikes(c);
		}
		mv.addObject("comments", comments);
		mv.setViewName("WEB-INF/views/idea.jsp");
		return mv;
	}

	@RequestMapping(path = "likeIdeaFromIdea.do", method = RequestMethod.GET)
	public ModelAndView likeIdeaFromIdea(int iid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Idea idea = ideaDao.showIdea(iid);
		List<Comment> comments = commentDao.showCommentsByIdea(iid);
		for (Comment c : comments) {
			c = commentDao.assignLikes(c);
		}
		mv.addObject("comments", comments);
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			mv.addObject("mustBeLoggedInMessage", "Must be logged in to vote");
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
		for (Comment c : comments) {
			c = commentDao.assignLikes(c);
		}
		mv.addObject("comments", comments);
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			mv.addObject("mustBeLoggedInMessage", "Must be logged in to vote");
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
	public ModelAndView redirectIdea(Idea idea, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		System.out.println(idea);
		if (idea.getId() == 0) {
			mv.setViewName("index.do");
			return mv;
		}
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
		for (Comment comment : comments) {
			comment = commentDao.assignLikes(comment);
		}
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
			mv.addObject("accountNotFoundMessage", "Account not found");
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
		Idea idea = ideaDao.showIdea(ideaId);
		if (user == null) {
			mv.addObject("mustBeLoggedInMessage", "You must be logged in to comment!!");
		} else {
			Profile profile = user.getProfile();
			Comment comment = new Comment();

			comment.setContent(content);
			commentDao.create(comment, profile, idea);

		}
		List<Comment> comments = commentDao.showCommentsByIdea(ideaId);
		for (Comment comment : comments) {
			comment = commentDao.assignLikes(comment);
		}
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
		session.invalidate();
		mv.setViewName("index.do");
		return mv;
	}

	@RequestMapping(path = "createUser.do", method = RequestMethod.POST)
	public ModelAndView creatingUser(@Valid UserDTO userDTO, Errors errors, HttpSession session) {
		User user = new User();
		ModelAndView mv = new ModelAndView();

		if (errors.getErrorCount() != 0) {

			mv.setViewName("WEB-INF/views/create.jsp");
			return mv;
		}

		if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
			mv.addObject("passwordMessage", "Passwords did not match");
			mv.setViewName("WEB-INF/views/create.jsp");
			return mv;
		}
		if (!userDao.checkForExistingEmail(userDTO.getEmail())
				&& !userDao.checkForExistingUsername(userDTO.getUsername())) {
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
		mv.addObject("pid", user.getProfile().getId());
		mv.setViewName("redirect:toSettings.do");
		return mv;
	}

	@RequestMapping(path = "toSettings.do", method = RequestMethod.GET)
	public ModelAndView directToSettings(HttpSession session, int pid) {
		ModelAndView mv = new ModelAndView();
		Profile profileToUpdate = profileDao.showProfile(pid);
		mv.addObject("profile", profileToUpdate);
		mv.setViewName("WEB-INF/views/settings.jsp");
		return mv;
	}

	@RequestMapping(path = "update.do", method = RequestMethod.POST)
	public ModelAndView updateSettings(@Valid User user, Errors errors,
			@RequestParam(name = "profilePic") String newProfilePic, @RequestParam(name = "bio") String newBio, int pid,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		System.out.println(user);
		if (errors.getErrorCount() != 0) {
			user = profileDao.showProfile(pid).getUser();
			mv.addObject("pid", user.getProfile().getId());
			mv.addObject("profile", user.getProfile());
			mv.addObject("invalidSettingsMessage", "Username or password is of invalid length");
			mv.setViewName("WEB-INF/views/settings.jsp");
			return mv;
		}
		User userUpdatingProfile = profileDao.showProfile(pid).getUser();
		userUpdatingProfile.setUsername(user.getUsername());
		userUpdatingProfile.setPassword(user.getPassword());
		userUpdatingProfile.setEmail(user.getEmail());
		try {
			userDao.update(userUpdatingProfile);
		} catch (Exception e) {
			user = profileDao.showProfile(pid).getUser();
			mv.addObject("pid", user.getProfile().getId());
			mv.addObject("profile", user.getProfile());
			mv.addObject("invalidSettingsMessage", "Username or Email is already taken");
			mv.setViewName("WEB-INF/views/settings.jsp");
			return mv;
		}
		Profile profileToUpdate = userUpdatingProfile.getProfile();
		profileToUpdate.setProfilePic(newProfilePic);
		if (profileToUpdate.getProfilePic().equals("") || profileToUpdate.getProfilePic() == ""
				|| profileToUpdate.getProfilePic() == null) {
			profileToUpdate.setProfilePic(
					"https://www.mybenshop.com/wp-content/uploads/2017/09/Rodin-the-Thinker-Sculpture-Medium-Figurine-Sandstone-Color-500x500.jpg");
		}
		profileToUpdate.setBio(newBio);
		profileDao.update(profileToUpdate);

		mv.setViewName("redirect:index.do");
		return mv;
	}

	@RequestMapping(path = "deactivateProfile.do", method = RequestMethod.GET)
	public ModelAndView deactivateProfile(@RequestParam(name = "pid") Integer profileId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Profile profile = profileDao.showProfile(profileId);
		User loggedInUser = profile.getUser();
		if (loggedInUser != null) {
			Profile profileLoggedIn = loggedInUser.getProfile();

			if (profileLoggedIn.getId() == profile.getId() || profileLoggedIn.getUser().isAdmin()) {
				mv.addObject("profileDeActivatedMessage", "Profile De-Activated");
				profileDao.makeInactive(profileId);
			} else {
				mv.addObject("noPermissionDeActivateMessage", "You do not have permission to deactivate this profile");
			}
		} else {
			mv.addObject("noPermissionDeActivateMessage", "You do not have permission to deactivate this profile");
		}

		mv.addObject("pid", profile.getId());
		mv.setViewName("toProfile.do");
		return mv;
	}

	@RequestMapping(path = "activateProfile.do", method = RequestMethod.GET)
	public ModelAndView activateProfile(@RequestParam(name = "pid") Integer profileId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Profile profile = profileDao.showProfile(profileId);
		User loggedInUser = profile.getUser();
		if (loggedInUser != null) {
			Profile profileLoggedIn = loggedInUser.getProfile();

			if (profileLoggedIn.getId() == profile.getId() || profileLoggedIn.getUser().isAdmin()) {
				mv.addObject("activaedMessage", "Profile Activated");
				profileDao.makeActive(profileId);
			} else {
				mv.addObject("noPermissionActivateMessage", "You do not have permission to activate this profile");
			}
		} else {
			mv.addObject("noPermissionActivateMessage", "You do not have permission to activate this profile");
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
				mv.addObject("deactivatedIdeaMessage", "Idea De-Activated");
				ideaDao.makeInactive(ideaId);
			} else {
				mv.addObject("noPermDeactivateIdeaMessage", "You do not have permission to deactivate this Idea");
			}
		} else {
			mv.addObject("noPermDeactivateIdeaMessage", "You do not have permission to deactivate this Idea");
		}
		mv.addObject("iid", ideaId);
		mv.setViewName("toIdea.do");
		return mv;
	}

	@RequestMapping(path = "activateIdea.do", method = RequestMethod.GET)
	public ModelAndView activateIdea(@RequestParam(name = "pid") Integer profileId,
			@RequestParam(name = "iid") Integer ideaId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Profile profile = profileDao.showProfile(profileId);
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
			Profile profileLoggedIn = loggedInUser.getProfile();
			if (profileLoggedIn.getId() == profile.getId() || profileLoggedIn.getUser().isAdmin()) {
				mv.addObject("activatedIdeaMessage", "Idea Activated");
				ideaDao.makeActive(ideaId);
			} else {
				mv.addObject("noPermActivateIdeaMessage", "You do not have permission to activate this Idea");
			}
		} else {
			mv.addObject("noPermActivateIdeaMessage", "You do not have permission to activate this Idea");
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

			if (profileLoggedIn.getId() == comment.getProfile().getId() || profileLoggedIn.getUser().isAdmin()) {
				mv.addObject("deactivateMessage", "Comment De-Activated");
				commentDao.makeInactive(commentId);
			} else {
				mv.addObject("noPermDeactivateCommentMessage", "You do not have permission to deactivate this comment");
			}
		} else {
			mv.addObject("noPermDeactivateCommentMessage", "You do not have permission to deactivate this comment");
		}
		mv.addObject("iid", ideaId);
		mv.addObject("cid", comment.getId());
		mv.setViewName("toIdea.do");
		return mv;
	}

	@RequestMapping(path = "search.do", method = RequestMethod.GET)
	public ModelAndView search(String ideaKeyword, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<Idea> foundIdeas = ideaDao.searchIdea(ideaKeyword);
		if (foundIdeas == null) {
			mv.addObject("message", "Search found no results");
			mv.setViewName("index.do");
			return mv;
		}
		for (Idea idea : foundIdeas) {
			idea = ideaDao.assignLikes(idea);
		}
		mv.addObject("ideaKeyword", ideaKeyword);
		mv.addObject("ideaList", foundIdeas);
		mv.setViewName("WEB-INF/views/index.jsp");
		return mv;
	}
}
