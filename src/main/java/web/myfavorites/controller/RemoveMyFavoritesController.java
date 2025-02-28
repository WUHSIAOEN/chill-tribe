package web.myfavorites.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.myfavorites.service.impl.MyFavoritesServiceImpl;
import web.myfavorites.vo.MyFavorites;

@WebServlet("/myfavorites/removemyfavorites")
public class RemoveMyFavoritesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MyFavoritesServiceImpl service;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Initializing RemoveMyFavoritesController...");
		try {
			service = new MyFavoritesServiceImpl(); // 初始化 service
			System.out.println("Service initialized successfully: " + service);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new ServletException("Service initialization failed", e);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Gson gson = new Gson();
		
		MyFavorites myFavorites = gson.fromJson(req.getReader(), MyFavorites.class);
		service.removeFavoritesById(myFavorites.getActivity_id());
		System.out.println(myFavorites.getActivity_id());
	}
}