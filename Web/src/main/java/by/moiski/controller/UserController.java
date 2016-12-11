package by.moiski.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import by.moiski.dao.entities.User;
import by.moiski.dao.enums.BlackList;
import by.moiski.dao.enums.UserT;
import by.moiski.services.IUserService;
import by.moiski.services.exceptions.ErrorDataAccessServiceExeption;
import by.moiski.services.exceptions.ErrorSavingUserServiceExeption;
import by.moiski.services.exceptions.LoginAlreadyBusyServiceExeption;
import by.moiski.utilits.ConfigurationManager;

@Controller
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	private static final String SESSION_ATTRIBUTE_USER_LOGIN = "userLogin";
	private static final String SESSION_ATTRIBUTE_USER_TYPE = "userType";
	private static final String SESSION_ATTRIBUTE_ADMIN_INFO_MESSAGE = "adminInfoMessage";
	private static final String SESSION_ERROR_ATTRIBUTE = "loginInfoMessage";
	private static final String ATTRIBUTE_ADMIN_PATH = "adminPath";
	private static final String PATH_FRAGMENT_ALL_USERS = "path.fragment.allusers";

	@Autowired
	private IUserService userService;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(path = "/users/getregistrationform", method = RequestMethod.GET)
	public String getRegistrationForm(Model model) {
		return "registration";
	}

	@RequestMapping(path = "/users/addnewuser", method = RequestMethod.POST)
	public String addNewUser(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		logger.info("User is got from registration form: " + user);
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		user.setBlackList(BlackList.N);
		user.setRole(UserT.CLIENT);
		user.setRegistrDate(new Date());
		try {
			userService.saveUserToDataBase(user);
			request.setAttribute("regInfoMessage",
					getMessage("message.registration", Locale.getDefault()));
		} catch (LoginAlreadyBusyServiceExeption e) {
			logger.info("This login is not available:  " + e.getMessage());
			request.setAttribute("regInfoMessage",
					getMessage("message.loginisnotfree", Locale.getDefault()));
			return "registration";
		} catch (ErrorSavingUserServiceExeption e) {
			logger.error("Error saving user to database:" + e.getMessage());
			request.setAttribute("regInfoMessage",
					getMessage("message.errorregistrarion", Locale.getDefault()));
			return "registration";
		}
		return "main";
	}

	@RequestMapping(path = "/users/login", method = RequestMethod.POST)
	public String login(@Valid User user, BindingResult bindingResult, HttpServletRequest request) {
		logger.info("User sent login form: " + user);
		if (bindingResult.hasErrors()) {
			return "main";
		}
		try {
			String login = user.getLogin();
			String password = user.getPassword();
			user = userService.getUserByLoginPassword(login, password);
			if ((user != null) && user.getRole().equals(UserT.ADMIN)) {
				request.getSession().setAttribute(SESSION_ATTRIBUTE_USER_LOGIN, user.getLogin());
				request.getSession().setAttribute(SESSION_ATTRIBUTE_USER_TYPE, UserT.ADMIN);
				return "admin";
			} else if ((user != null) && user.getRole().equals(UserT.CLIENT)) {
				request.getSession().setAttribute(SESSION_ATTRIBUTE_USER_LOGIN, user.getLogin());
				request.getSession().setAttribute(SESSION_ATTRIBUTE_USER_TYPE, UserT.CLIENT);
				return "main";
			} else {
				request.getSession().setAttribute(SESSION_ERROR_ATTRIBUTE, getMessage("message.errorlogin", Locale.getDefault()));
				request.getSession().setAttribute(SESSION_ATTRIBUTE_USER_TYPE, UserT.GUEST);
				return "main";
			}
		} catch (ErrorDataAccessServiceExeption e) {
			logger.info("Error data access" + e.getMessage());
			request.getSession().setAttribute("loginInfoMessage", getMessage("message.errorlogin", Locale.getDefault()));
			return "main";
		}
	}
	
	@RequestMapping(path="/users/all", method = RequestMethod.GET)
	public String getAllUsers(Model model){
		List<User> users = userService.getALLUsers();
		model.addAttribute("usersList", users);
		model.addAttribute(ATTRIBUTE_ADMIN_PATH, ConfigurationManager.getProperty(PATH_FRAGMENT_ALL_USERS));
		return "admin";
	}
	
	@RequestMapping(path="/users/add/remove/blacklist", method = RequestMethod.GET)
	public String blackList(User user, Model model){
		user = userService.getUserById(user.getUserId());
		if (user.getBlackList().equals(BlackList.N)){
			user.setBlackList(BlackList.Y);
			userService.updateUserBlackListStatus(user);
			model.addAttribute(SESSION_ATTRIBUTE_ADMIN_INFO_MESSAGE, getMessage("message.useraddtoblacklist", Locale.getDefault()));
			return "admin";
		} else {
			user.setBlackList(BlackList.N);
			userService.updateUserBlackListStatus(user);
			model.addAttribute(SESSION_ATTRIBUTE_ADMIN_INFO_MESSAGE, getMessage("message.userremovetislacklist", Locale.getDefault()));
			return "admin";
		}
	}

	private String getMessage(String key, Locale locale) {
		return messageSource.getMessage(key, null, locale);
	}

}
