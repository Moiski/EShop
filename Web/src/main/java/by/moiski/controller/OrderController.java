package by.moiski.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.moiski.dao.entities.Order;
import by.moiski.services.IOrderService;
import by.moiski.services.exceptions.ErrorAddOrderServiceExeption;
import by.moiski.services.exceptions.ErrorCartUserIsEmptyServiceExeption;
import by.moiski.utilits.MessageManager;

@Controller
public class OrderController {

	private static Logger logger = Logger.getLogger(OrderController.class);
	
	private static final String SESSION_ATTRIBUTE_USER_LOGIN = "userLogin";
	private static final String SESSION_ATTRIBUTE_ORDER_INFO_MESSAGE = "orderInfoMessage";
	private static final String REQUEST_PARAMETR_USER_ORDERS = "orderlistuser";

	@Autowired
	private IOrderService orderService;

	@RequestMapping(path = "/add/order", method = RequestMethod.POST)
	public String order(HttpSession session, HttpServletRequest request) {
		logger.info("Add order to data base: " + getClass().getName());
		String login = (String) session.getAttribute(SESSION_ATTRIBUTE_USER_LOGIN);
		try {
			orderService.addOrder(login);
		} catch (ErrorCartUserIsEmptyServiceExeption e) {
			logger.info("User cart is empty ");
			request.setAttribute(SESSION_ATTRIBUTE_ORDER_INFO_MESSAGE, MessageManager.getProperty("message.cart.is.empty"));
			return "order";
		} catch (ErrorAddOrderServiceExeption e) {
			logger.error("Error adding order to data base: " + OrderController.class, e);
			request.setAttribute(SESSION_ATTRIBUTE_ORDER_INFO_MESSAGE, MessageManager.getProperty("message.error.added.order"));
			return "order";
		}
		request.setAttribute(SESSION_ATTRIBUTE_ORDER_INFO_MESSAGE, MessageManager.getProperty("message.add.order"));
		return "order";
	}
	
	@RequestMapping(path="/orders", method=RequestMethod.GET)
	public String order(Model model, HttpSession session){		
		String login = (String) session.getAttribute(SESSION_ATTRIBUTE_USER_LOGIN);
		List<Order> ordersList = orderService.getAlluserOrders(login);
		model.addAttribute(REQUEST_PARAMETR_USER_ORDERS, ordersList);
		return "order";
	}

}
