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

import web.activity.service.ActivityService;
import web.activity.service.impl.ActivityServiceImpl;
import web.activity.vo.Activity;
import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;
import web.member.vo.Member;

@WebServlet("/activity/findAll")
public class FindAllActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActivityService service;
	
	public void init() throws ServletException {
		try {
			service = new ActivityServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Activity> activities = service.findAll();
		Gson gson = new Gson();
		resp.getWriter().write(gson.toJson(activities));
	}
	
}
