package web.myfavorites.controller;

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

import web.myfavorites.service.MyFavoritesService;
import web.myfavorites.service.impl.MyFavoritesServiceImpl;
import web.myfavorites.vo.MyFavorites;
import web.shoppingcart.vo.ShoppingCart;

@WebServlet("/myfavorites/findallmyfavorites")
public class GetMyFavoritesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyFavoritesService service;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Initializing AddToCartController...");
		try {
			service = new MyFavoritesServiceImpl(); // 初始化 service
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
		
		List<MyFavorites> myFavorites = service.findAllFavorites();
		resp.getWriter().write(gson.toJson(myFavorites));
	}
	
}
