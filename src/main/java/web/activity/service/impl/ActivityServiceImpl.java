package web.activity.service.impl;

import java.sql.Timestamp;

import javax.naming.NamingException;

import web.activity.dao.ActivityDao;
import web.activity.dao.impl.ActivityDaoImpl;
import web.activity.service.ActivityService;
import web.activity.vo.Activity;

public class ActivityServiceImpl implements ActivityService{
	private ActivityDao dao;
	
	public ActivityServiceImpl() throws NamingException {
		dao = new ActivityDaoImpl();
	}

	@Override
	public String apply(Activity activity) {
		Integer activityId = activity.getActivityId();
		String activityPrefix = activity.getActivityPrefix(); // 活動代碼前綴
		String activityName = activity.getActivityName(); // 活動名稱
		Integer supplierId = activity.getSupplierId(); // 供應商編號
		String address = activity.getAddress(); // 活動地址
		Integer unitPrice = activity.getUnitPrice(); // 單位價格
		Integer minParticipants = activity.getMinParticipants(); // 最小參加人數
		Integer maxParticipants = activity.getMaxParticipants(); // 最大參加人數
		String description = activity.getDescription(); // 活動描述
		String category = activity.getCategory(); // 活動類別
		Timestamp startDateTime = activity.getStartDateTime(); // 開始時間
		Timestamp endDateTime = activity.getEndDateTime(); // 結束時間
		Integer status = activity.getStatus(); // 狀態 (0: 暫定 1: 確定)
		String note = activity.getNote(); // 備註
		Integer approved = activity.getApproved(); // 審核狀態 (0: 未審核 1: 已通過)
		String city = activity.getCity(); // 城市
		String district = activity.getDistrict(); // 地區
		Integer inventoryCount = activity.getInventoryCount(); // 庫存數量
		Timestamp inventoryUpdateTime = activity.getInventoryUpdateTime(); // 庫存更新時間
		Timestamp createdTime = activity.getCreatedTime(); // 建立時間
		String latitude = activity.getLatitude(); // 緯度
		String longitude = activity.getLongitude(); // 經度
		Timestamp ticketsActivateTime = activity.getTicketsActivateTime(); // 票券啟動時間
		Timestamp ticketsExpiredTime = activity.getTicketsExpiredTime(); // 票券過期時間
		
		int resultCount = dao.apply(activity);
		
		return resultCount > 0 ? null : "發生錯誤，請聯絡客服";		

	}

}
