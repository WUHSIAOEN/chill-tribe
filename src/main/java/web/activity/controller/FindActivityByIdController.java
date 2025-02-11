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
import com.google.gson.JsonObject;

import web.activity.service.ActivityService;
import web.activity.service.impl.ActivityServiceImpl;
import web.activity.vo.Activity;
import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;
import web.member.vo.Member;

@WebServlet("/activity/findActivityById")
public class FindActivityByIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActivityService service;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Initializing ApplyActivityController...");
	    try {
	        service = new ActivityServiceImpl(); // 初始化 service
	        System.out.println("Service initialized successfully: " + service);
	    } catch (NamingException e) {
	        e.printStackTrace();
	        throw new ServletException("Service initialization failed", e);
	    }
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String idParam = req.getParameter("id");

	    Gson gson = new GsonBuilder()
	            .setDateFormat("yyyy/MM/dd HH:mm:ss")
	            .create();

	    try {
	        if (idParam != null) {
	            Integer id = Integer.parseInt(idParam);
	            Activity activity = service.findActivityById(id);
	            resp.getWriter().write(gson.toJson(activity));
	        } else {
	        	// jsonobject
	        	JsonObject respBody = new JsonObject();
	    		respBody.addProperty("errMsg", "沒有ID");
	            resp.getWriter().write(gson.toJson(respBody));
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
