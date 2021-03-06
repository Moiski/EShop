package by.moiski.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.moiski.dao.entities.Product;
import by.moiski.services.ICartService;
import by.moiski.services.exceptions.ErrorAddProductToCartServiceExeption;
import by.moiski.services.exceptions.ErrorDeleteProductFromCartServiceExeption;

@Controller
public class CartController {
	
	private static Logger logger = Logger.getLogger(CartController.class);
	
	private static final String SESSION_ATTRIBUTE_USER_LOGIN = "userLogin";
	private static final String SESSION_ATTRIBUTE_USER_CART = "cartlistuser";
	private static final String SESSION_ATTRIBUTE_CART_INFO_MESSAGE = "cartInfoMessage";
	private static final String REQUEST_PARAMETR_PRODUCT_ID = "productId";
	
	@Autowired
	private ICartService cartService;
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(path="/add/cart", method=RequestMethod.POST)
	public String addOrder (Product product, HttpServletRequest request, HttpSession session, Locale locale){
		logger.info("Add product to cart" + getClass().getName());
		String login = (String) session.getAttribute(SESSION_ATTRIBUTE_USER_LOGIN);
		try {
			cartService.addToCartProduct(login, product);
			session.setAttribute(SESSION_ATTRIBUTE_CART_INFO_MESSAGE, getMessage("message.product.add", locale));
		} catch (ErrorAddProductToCartServiceExeption e) {
			logger.error("Error adding product to cart" + CartController.class);
			session.setAttribute(SESSION_ATTRIBUTE_CART_INFO_MESSAGE, getMessage("message.error.product.add", locale));
		}
		return "products";
	}
	
	@RequestMapping(path="/cart", method=RequestMethod.GET)
	public String cart (HttpSession session){
		String login = (String) session.getAttribute(SESSION_ATTRIBUTE_USER_LOGIN);
		List <Product> cartlist = cartService.getCartUser(login);
		session.setAttribute(SESSION_ATTRIBUTE_USER_CART, cartlist);	
		return "cart";
	}
	
	@RequestMapping(path="/cart/delete/product", method=RequestMethod.POST)
	public String deleteProduct(HttpServletRequest request, HttpSession session, Locale locale){
		logger.info("Delete product from user cart: " + getClass().getName());
		String login = (String) session.getAttribute(SESSION_ATTRIBUTE_USER_LOGIN);
		Long productId = Long.parseLong(request.getParameter(REQUEST_PARAMETR_PRODUCT_ID));
		try {
			cartService.removeToCartProduct(login, productId);
			request.setAttribute(SESSION_ATTRIBUTE_CART_INFO_MESSAGE, getMessage("message.remove.product.from.cart", locale));
		} catch (ErrorDeleteProductFromCartServiceExeption e) {
			logger.error("Error deleted product from cart " + CartController.class, e);
			request.setAttribute(SESSION_ATTRIBUTE_CART_INFO_MESSAGE, getMessage("message.error.delete.product.from.cart", locale));
		}
		return "products";
	}
	
	private String getMessage(String key, Locale locale) {
		return messageSource.getMessage(key, null, locale);
	}

}
