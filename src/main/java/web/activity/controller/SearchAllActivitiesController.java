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
import web.activity.vo.Activity2;

@WebServlet("/activity/SearchAllActivities")
public class SearchAllActivitiesController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ActivityService2 service;
		try {
			service = new ActivityServiceImpl2();
			List<Activity2> activities = service.searchAll();
			Gson gson = new Gson();
			resp.getWriter().write(gson.toJson(activities));
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
