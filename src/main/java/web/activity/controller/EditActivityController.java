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

public class EditActivityController {
	@WebServlet("/member/edit")
	public class editController extends HttpServlet{
		private static final long serialVersionUID = 1L;
		private ActivityService service;
		
		@Override
		public void init() throws ServletException {
			try {
				service = new ActivityServiceImpl();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Gson gson = new Gson();
			Activity activity = gson.fromJson(req.getReader(), Activity.class);
			
			activity = service.edit(activity);
			
			resp.setContentType("application/json");
			resp.getWriter().write(gson.toJson(activity));
		}
	}
}
