package web.shoppingcart.controller;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.shoppingcart.service.ShoppingCartService;
import web.shoppingcart.service.impl.ShoppingCartServiceImpl;
import web.shoppingcart.vo.ShoppingCart;

@WebServlet("/shoppingCart/findAllProducts")
public class GetProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShoppingCartService service;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Initializing AddToCartController...");
		try {
			service = new ShoppingCartServiceImpl(); // 初始化 service
			System.out.println("Service initialized successfully: " + service);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new ServletException("Service initialization failed", e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy/MM/dd HH:mm:ss")
				.create();
		
		List<ShoppingCart> products = service.findAllProducts();
		resp.getWriter().write(gson.toJson(products));
	}
	
}
