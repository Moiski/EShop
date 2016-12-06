package by.moiski.command.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.moiski.command.ActionCommand;
import by.moiski.dao.entities.Order;
import by.moiski.dao.entities.User;
import by.moiski.services.impl.OrderServiceImpl;
import by.moiski.services.impl.UserServiceImpl;
import by.moiski.utilits.ConfigurationManager;

public class ShowUserOrdersCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {

		String login = (String) request.getSession().getAttribute("userLogin");
		User user = UserServiceImpl.getInstance().getUserIdByLogin(login);
		Long userId = user.getUserId();
		List<Order> ordersList = OrderServiceImpl.getInstance().getAlluserOrders(userId);
		request.setAttribute("orderlistuser", ordersList);
		String page = ConfigurationManager.getProperty("path.page.orders");
		return page;
	}

}
