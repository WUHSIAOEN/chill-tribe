// 測試用，先不用 JSON
// 回傳的值我先用 String and Integer ，

package web.activity.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.activity.service.ActivityService;
import web.activity.service.impl.ActivityServiceImpl;
import web.activity.vo.Activity;

@WebServlet("/activity/apply")
public class ApplyActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	呼叫　Service:
	
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
		
//		Integer activityId = Integer.parseInt(req.getParameter("activityId")); // 活動編號
//		String activityPrefix = req.getParameter("activityPrefix"); // 活動代碼前綴
		String activityName = req.getParameter("activityName"); // 活動名稱
//		Integer supplierId = Integer.parseInt(req.getParameter("supplierId")); // 供應商編號
		String address = req.getParameter("address"); // 活動地址
		Integer unitPrice = Integer.parseInt(req.getParameter("unitPrice")); // 單位價格
		Integer minParticipants = Integer.parseInt(req.getParameter("minParticipants")); // 最小參加人數
//		Integer maxParticipants = Integer.parseInt(req.getParameter("maxParticipants")); // 最大參加人數
		String description = req.getParameter("description"); // 活動描述
		String category = req.getParameter("category"); // 活動類別
//		String startDateTime = req.getParameter("startDateTime"); // 開始時間
//		String endDateTime = req.getParameter("endDateTime"); // 結束時間
//		Integer status = Integer.parseInt(req.getParameter("status")); // 狀態 (0: 暫定 1: 確定)
		String note = req.getParameter("note"); // 備註
//		Integer approved = Integer.parseInt(req.getParameter("approved")); // 審核狀態 (0: 未審核 1: 已通過)
		String city = req.getParameter("city"); // 城市
		String district = req.getParameter("district"); // 地區
//		Integer inventoryCount = Integer.parseInt(req.getParameter("inventoryCount")); // 庫存數量
//		String inventoryUpdateTime = req.getParameter("inventoryUpdateTime"); // 庫存更新時間
//		String createdTime = req.getParameter("createdTime"); // 建立時間
//		String latitude = req.getParameter("latitude"); // 緯度
//		String longitude = req.getParameter("longitude"); // 經度
//		String ticketsActivateTime = req.getParameter("ticketsActivateTime"); // 票券啟動時間
//		String ticketsExpiredTime = req.getParameter("ticketsExpiredTime"); // 票券過期時間
		
		Activity activity = new Activity();
		
//		activity.setActivityId(activityId);  // 設定活動編號
//		activity.setActivityPrefix(activityPrefix);  // 設定活動代碼前綴
		activity.setActivityName(activityName);  // 設定活動名稱
//		activity.setSupplierId(supplierId);  // 設定供應商編號
		activity.setAddress(address);  // 設定活動地址
		activity.setUnitPrice(unitPrice);  // 設定單位價格
		activity.setMinParticipants(minParticipants);  // 設定最小參加人數
//		activity.setMaxParticipants(maxParticipants);  // 設定最大參加人數
		activity.setDescription(description);  // 設定活動描述
		activity.setCategory(category);  // 設定活動類別
//		activity.setStartDateTime(startDateTime);  // 設定開始時間
//		activity.setEndDateTime(endDateTime);  // 設定結束時間
//		activity.setStatus(status);  // 設定狀態
		activity.setNote(note);  // 設定備註
//		activity.setApproved(approved);  // 設定審核狀態
		activity.setCity(city);  // 設定城市
		activity.setDistrict(district);  // 設定地區
//		activity.setInventoryCount(inventoryCount);  // 設定庫存數量
//		activity.setInventoryUpdateTime(inventoryUpdateTime);  // 設定庫存更新時間
//		activity.setCreatedTime(createdTime);  // 設定建立時間
//		activity.setLatitude(latitude);  // 設定緯度
//		activity.setLongitude(longitude);  // 設定經度
//		activity.setTicketsActivateTime(ticketsActivateTime);  // 設定票券啟動時間
//		activity.setTicketsExpiredTime(ticketsExpiredTime);  // 設定票券過期時間
		
		String errMsg = service.apply(activity);
		System.out.println(errMsg);
		
			
	}
}
