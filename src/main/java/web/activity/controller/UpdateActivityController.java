package web.activity.controller;


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
import web.activity.vo.Activity;
import web.member.vo.Member;

@WebServlet("/activity/updateActivity")
public class UpdateActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	呼叫　Service 以便使用它的方法
	private ActivityService service;
	
	@Override
	public void init() throws ServletException {
	    System.out.println("Initializing UpdateActivityController...");
	    try {
	        service = new ActivityServiceImpl(); // 初始化 service
	        System.out.println("Service initialized successfully: " + service);
	    } catch (NamingException e) {
	        e.printStackTrace();
	        throw new ServletException("Service initialization failed", e);
	    }
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
		
		String idParam = req.getParameter("id");
		
		Gson gson = new GsonBuilder()
	            .setDateFormat("yyyy/MM/dd HH:mm:ss")
	            .create();
		
		try {
			if (idParam != null) {
				Integer id = Integer.parseInt(idParam);
				Activity updatedActivity = gson.fromJson(req.getReader(), Activity.class);
				updatedActivity = service.update(updatedActivity);
				resp.setContentType("application/json");
	            resp.getWriter().write(gson.toJson(updatedActivity));
			} else {
				JsonObject respBody = new JsonObject();
	    		respBody.addProperty("errMsg", "沒有ID");
	            resp.getWriter().write(gson.toJson(respBody));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
