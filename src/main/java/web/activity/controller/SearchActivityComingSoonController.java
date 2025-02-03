package web.activity.controller;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.activity.service.ActivityService2;
import web.activity.service.impl.ActivityServiceImpl2;
import web.activity.vo.IndexActivityCard;

@WebServlet("/activity/SearchActivityComingSoon")
public class SearchActivityComingSoonController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private ActivityService2 service;
	
	public void init() throws ServletException {
		try {
			service = new ActivityServiceImpl2();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<IndexActivityCard> indexActivityCard = service.searchActivityByStart();
		Gson gson = new Gson();
		resp.getWriter().write(gson.toJson(indexActivityCard));
	}
	
	
}
