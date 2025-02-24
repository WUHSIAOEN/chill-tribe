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
import com.google.gson.JsonObject;

import web.activity.service.ActivityService;
import web.activity.service.impl.ActivityServiceImpl;
import web.activity.vo.Activities;

@WebServlet("/activity/applyimages")
public class ApplyActivityImagesController extends HttpServlet{
private static final long serialVersionUID = 1L;
	
//	呼叫　Service
	private ActivityService service;
	
	@Override
	public void init() throws ServletException {
	    System.out.println("Initializing ApplyActivityImagesController...");
	    try {
	        service = new ActivityServiceImpl(); // 初始化 service
	        System.out.println("Service initialized successfully: " + service);
	    } catch (NamingException e) {
	        e.printStackTrace();
	        throw new ServletException("Service initialization failed", e);
	    }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy/MM/dd HH:mm:ss")
				.create();

		Activities activity = gson.fromJson(req.getReader(), Activities.class);
			
		String errMsg = service.addImages(activity);
		System.out.println(errMsg);
		System.out.println(activity);
		
		JsonObject respBody = new JsonObject();
		respBody.addProperty("successful", errMsg == null);
		respBody.addProperty("errMsg", errMsg);
		
		resp.setContentType("application/json");
			
	}
}
