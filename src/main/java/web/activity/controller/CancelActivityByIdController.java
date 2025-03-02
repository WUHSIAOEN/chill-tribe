package web.activity.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.activity.service.ActivityService;
import web.activity.service.impl.ActivityServiceImpl;
import web.activity.vo.Activities;

@WebServlet("/activity/cancelActivity")
public class CancelActivityByIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	呼叫　Service 以便使用它的方法
	private ActivityService service;

	@Override
	public void init() throws ServletException {
		System.out.println("Initializing UpdateActivityController...");
		service = new ActivityServiceImpl(); // 初始化 service
		System.out.println("Service initialized successfully: " + service);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();

		Activities cancelledActivity = gson.fromJson(req.getReader(), Activities.class);
		service.cancelById(cancelledActivity);
		

	}
}
