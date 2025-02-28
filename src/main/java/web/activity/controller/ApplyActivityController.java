package web.activity.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import core.util.CommonUtil;
import web.activity.service.ActivityService;
import web.activity.service.impl.ActivityServiceImpl;
import web.activity.vo.Activities;

@WebServlet("/activity/apply")
public class ApplyActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ActivityService service;
	
//	因為Controller 這層不能直接用@Autowired 注入IoC 容器，還是要透過DL 取得，故未導入Spring MVC 前先將getBean 包在 init()
	@Override
	public void init() throws ServletException {
		service = CommonUtil.getBean(getServletContext(), ActivityService.class);
	}
	
//	================== 基本上以下這段不用了 ==================
//	@Override
//	public void init() throws ServletException {
//	    System.out.println("Initializing ApplyActivityController...");
//	    try {
//	        service = new ActivityServiceImpl(); // 初始化 service
//	        System.out.println("Service initialized successfully: " + service);
//	    } catch (NamingException e) {
//	        e.printStackTrace();
//	        throw new ServletException("Service initialization failed", e);
//	    }
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy/MM/dd HH:mm:ss")
				.create();

		Activities activity = gson.fromJson(req.getReader(), Activities.class);
			
		String errMsg = service.apply(activity);
		System.out.println(errMsg);
		System.out.println(activity);
		
		JsonObject respBody = new JsonObject();
		respBody.addProperty("successful", errMsg == null);
		respBody.addProperty("errMsg", errMsg);
		
		resp.setContentType("application/json");
			
	}
}
