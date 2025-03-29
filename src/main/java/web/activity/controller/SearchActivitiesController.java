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
import com.google.gson.GsonBuilder;

import web.activity.service.ActivityService2;
import web.activity.service.impl.ActivityServiceImpl2;
import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;

@WebServlet("/activity/SearchActivities")
public class SearchActivitiesController extends HttpServlet{

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
		String actname = req.getParameter("search-activity-name");
		String category = req.getParameter("category");
		String region = req.getParameter("region");
		if(category.equals("all")) {
			category = "";
		}
		if(region.equals("all")) {
			region = "";
		}
		List<Activities> indexActivityCard = service.searchActivityByFilter(actname, category, region);
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd HH:mm:ss")
		        .create();
		resp.getWriter().write(gson.toJson(indexActivityCard));
	}

}
