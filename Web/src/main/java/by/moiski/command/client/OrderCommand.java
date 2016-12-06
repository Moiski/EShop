package by.moiski.command.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import by.moiski.command.ActionCommand;
import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.Order;
import by.moiski.dao.entities.OrderDetail;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.User;
import by.moiski.dao.enums.OrderState;
import by.moiski.services.impl.CartServiceImpl;
import by.moiski.services.impl.OrderServiceImpl;
import by.moiski.services.impl.UserServiceImpl;
import by.moiski.utilits.ConfigurationManager;
import by.moiski.utilits.MessageManager;

public class OrderCommand implements ActionCommand {
	
	private static Logger logger = Logger.getLogger(OrderCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String login = (String) request.getSession().getAttribute("userLogin");
		try {
			List<Product> cartProductList = CartServiceImpl.getInstance().getCartUser(login);
			User user = UserServiceImpl.getInstance().getUserIdByLogin(login);
			List<Cart> cartUser = CartServiceImpl.getInstance().getCartByUserId(user.getUserId());
			if (cartProductList.isEmpty()){
				request.setAttribute("orderInfoMessage", MessageManager.getProperty("message.cartisempty"));
				page = ConfigurationManager.getProperty("path.page.main");
				return page;
			} else {
				Order order = new Order();
				OrderDetail orderDetail = new OrderDetail();
				double sum = 0;
				List <OrderDetail> orderDetails = new ArrayList<>(); 
				for (Product product : cartProductList) {
					orderDetail.setProduct(product);
					orderDetail.setCount(cartProductList.size());
					orderDetails.add(orderDetail);
					sum = sum + product.getPrice();
				}
				order.setUser(user);
				order.setAmountPurchase(sum);		
				order.setOrderState(OrderState.OPEN);
				order.setOrderDetails(orderDetails);
				OrderServiceImpl.getInstance().saveOrderToDataBase(order);
				for (Cart cart : cartUser) {
					CartServiceImpl.getInstance().deleteCartUser(cart);
				}
			}
		} catch (HibernateException e) {
			logger.error("Error creating order " + OrderCommand.class, e);
			request.setAttribute("orderInfoMessage", MessageManager.getProperty("message.erroraddedorder"));
		}
		request.setAttribute("orderInfoMessage", MessageManager.getProperty("message.orderadded"));
		page = ConfigurationManager.getProperty("path.page.main");
		return page;
	}

}
