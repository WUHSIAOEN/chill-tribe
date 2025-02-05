// 測試用，先不用 JSON
// 回傳的值我先用 String and Integer ，

package web.activity.controller;

import java.io.IOException;
import java.sql.Timestamp;

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

@WebServlet("/activity/apply")
public class ApplyActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	呼叫　Service 以便使用它的方法，來處理業務邏輯（例如處理來自前端的資料）。
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		StringBuilder jsonBuilder = new StringBuilder();
//	    String line;
//	    while ((line = req.getReader().readLine()) != null) {
//	        jsonBuilder.append(line);
//	    }
//	    String jsonString = jsonBuilder.toString();
//
//	    // 打印接收到的 JSON 資料
//	    System.out.println("Received JSON: " + jsonString);

		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy/MM/dd HH:mm:ss")
				.create();
//		Activity activity = new Activity();
		
//		System.out.println(req.getReader().toString());
		
		
//		try {
            // 解析 JSON 並將其轉換為 Activity 物件
            Activity activity = gson.fromJson(req.getReader(), Activity.class);
            System.out.println(activity + "It's here");
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            sendErrorResponse(resp, "Invalid JSON format");
//            return;
//        }

        // 確保 Activity 物件不為 null
        if (activity == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            sendErrorResponse(resp, "Activity data is missing");
            return;
        }
		
//	 	在 doPost() 中，根據前端傳過來的資料創建 activity 物件，並調用 service.apply(activity) 方法。		
		String errMsg = service.apply(activity);
		System.out.println(errMsg);
		System.out.println(activity);
//		
		JsonObject respBody = new JsonObject();
		respBody.addProperty("successful", errMsg == null);
		respBody.addProperty("errMsg", errMsg);
		
		resp.setContentType("application/json");
//		resp.getWriter().write(respBody.toString());
			
	}
	// 用來返回錯誤訊息的輔助方法
    private void sendErrorResponse(HttpServletResponse resp, String message) throws IOException {
        JsonObject errorResponse = new JsonObject();
        errorResponse.addProperty("successful", false);
        errorResponse.addProperty("errMsg", message);
        resp.getWriter().write(errorResponse.toString());
    }
}
