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

import web.activity.service.ActivitiService;
import web.activity.service.impl.ActivitiServiceImpl;
import web.activity.vo.Activiti;

@WebServlet("activity/SearchActivities")
public class SearchActivitiesController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ActivitiService service;
	
	@Override
	public void init() throws ServletException {
		try {
			service = new ActivitiServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Activiti> Activities = service.searchAll();
		Gson gson = new Gson();
		resp.getWriter().write(gson.toJson(Activities));
		
		
		
	}
}
