package web.activity.controller;

import java.io.IOException;

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

@WebServlet("/activity/apply2")
public class ApplyActivityController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ActivityService service;
		service = new ActivityServiceImpl();
		Gson gson = new Gson();
//			System.out.println(req.getReader());
		Activity activity = gson.fromJson(req.getReader(), Activity.class);
		System.out.println(activity.getActivityName());

	}

}
