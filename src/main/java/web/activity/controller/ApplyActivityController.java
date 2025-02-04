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
		
		
//		String activityName = req.getParameter("activityName"); // 活動名稱
//		Integer supplierId = Integer.parseInt(req.getParameter("supplierId")); // 供應商編號
//		String address = req.getParameter("address"); // 活動地址
//		Integer unitPrice = Integer.parseInt(req.getParameter("unitPrice")); // 單位價格
//		Integer minParticipants = Integer.parseInt(req.getParameter("minParticipants")); // 最小參加人數
//		Integer maxParticipants = Integer.parseInt(req.getParameter("maxParticipants")); // 最大參加人數
//		String description = req.getParameter("description"); // 活動描述
//		String category = req.getParameter("category"); // 活動類別
//		String startDateTime = req.getParameter("startDateTime"); // 開始時間
//		String endDateTime = req.getParameter("endDateTime"); // 結束時間
//		Integer status = Integer.parseInt(req.getParameter("status")); // 狀態 (0: 暫定 1: 確定)
//		String note = req.getParameter("note"); // 備註
//		Integer approved = Integer.parseInt(req.getParameter("approved")); // 審核狀態 (0: 未審核 1: 已通過)
//		String city = req.getParameter("city"); // 城市
//		String district = req.getParameter("district"); // 地區
//		Integer inventoryCount = Integer.parseInt(req.getParameter("inventoryCount")); // 庫存數量
//		String inventoryUpdateTime = req.getParameter("inventoryUpdateTime"); // 庫存更新時間
//		String createdTime = req.getParameter("createdTime"); // 建立時間
//		String latitude = req.getParameter("latitude"); // 緯度
//		String longitude = req.getParameter("longitude"); // 經度
//		String ticketsActivateTime = req.getParameter("ticketsActivateTime"); // 票券啟動時間
//		String ticketsExpiredTime = req.getParameter("ticketsExpiredTime"); // 票券過期時間
		
// 		實例化		
//		Activity activity = new Activity();
//		Timestamp testTimestamp = Timestamp.valueOf("2025-12-31 23:59:59");
//		
		
//		activity.setActivityName("test");  // 設定活動名稱
//		activity.setSupplierId(103);  // 設定供應商編號
//		activity.setAddress("test");  // 設定活動地址
//		activity.setUnitPrice(100);  // 設定單位價格
//		activity.setMinParticipants(100);  // 設定最小參加人數
//		activity.setMaxParticipants(100);  // 設定最大參加人數
//		activity.setDescription("test");  // 設定活動描述
//		activity.setCategory("test");  // 設定活動類別
//		activity.setStartDateTime(testTimestamp);  // 設定開始時間
//		activity.setEndDateTime(testTimestamp);  // 設定結束時間
//		activity.setStatus(0);  // 設定狀態
//		activity.setNote("test");  // 設定備註
//		activity.setApproved(1);  // 設定審核狀態
//		activity.setCity("test");  // 設定城市
//		activity.setDistrict("test");  // 設定地區
//		activity.setInventoryCount(12);  // 設定庫存數量
//		activity.setInventoryUpdateTime(testTimestamp);  // 設定庫存更新時間
//		activity.setCreatedTime(testTimestamp);  // 設定建立時間
//		activity.setLatitude("test");  // 設定緯度
//		activity.setLongitude("test");  // 設定經度
//		activity.setTicketsActivateTime(testTimestamp);  // 設定票券啟動時間
//		activity.setTicketsExpiredTime(testTimestamp);  // 設定票券過期時間
		
		StringBuilder jsonBuilder = new StringBuilder();
	    String line;
	    while ((line = req.getReader().readLine()) != null) {
	        jsonBuilder.append(line);
	    }
	    String jsonString = jsonBuilder.toString();

	    // 打印接收到的 JSON 資料
	    System.out.println("Received JSON: " + jsonString);

		Gson gson = new Gson();
		Activity activity = new Activity();
		
		System.out.println(req.getReader().toString());
		
		
		try {
            // 解析 JSON 並將其轉換為 Activity 物件
            activity = gson.fromJson(req.getReader(), Activity.class);
            System.out.println(activity);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            sendErrorResponse(resp, "Invalid JSON format");
            return;
        }

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
		resp.getWriter().write(respBody.toString());
			
	}
	// 用來返回錯誤訊息的輔助方法
    private void sendErrorResponse(HttpServletResponse resp, String message) throws IOException {
        JsonObject errorResponse = new JsonObject();
        errorResponse.addProperty("successful", false);
        errorResponse.addProperty("errMsg", message);
        resp.getWriter().write(errorResponse.toString());
    }
}
