package web.activity.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.naming.NamingException;

import web.activity.dao.ActivityDao;
import web.activity.dao.impl.ActivityDaoImpl;
import web.activity.service.ActivityService;
import web.activity.vo.Activity;

public class ActivityServiceImpl implements ActivityService {
	private ActivityDao dao;

	public ActivityServiceImpl() throws NamingException {
		dao = new ActivityDaoImpl();
	}

	@Override
	public String apply(Activity activity) {
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

		int resultCount = dao.insert(activity);

		return resultCount > 0 ? null : "發生錯誤，請聯絡客服";

	}

	@Override
	public Activity edit(Activity activity) {
		// 檢查開始時間與結束時間是否合法（結束時間必須在開始時間之後）
	    if (activity.getStartDateTime() != null && activity.getEndDateTime() != null) {
	        if (activity.getStartDateTime().after(activity.getEndDateTime())) {
//	            activity.setSuccessful(false);
//	            activity.setMessage("活動結束時間不能早於開始時間");
	            return activity;
	        }
	    }

	    // 檢查活動名稱長度是否符合要求
	    String activityName = activity.getActivityName();
	    if (activityName == null || activityName.isEmpty()) {
//	        activity.setSuccessful(false);
//	        activity.setMessage("活動名稱不可為空");
	        return activity;
	    }

	    // 檢查單位價格是否合理（假設價格必須大於0）
	    if (activity.getUnitPrice() != null && activity.getUnitPrice() < 0) {
//	        activity.setSuccessful(false);
//	        activity.setMessage("單位價格必須大於 0");
	        return activity;
	    }

	    // 檢查最小參加人數和最大參加人數是否合法（最小參加人數應小於最大參加人數）
	    if (activity.getMinParticipants() != null && activity.getMaxParticipants() != null) {
	        if (activity.getMinParticipants() > activity.getMaxParticipants()) {
//	            activity.setSuccessful(false);
//	            activity.setMessage("最小參加人數不能大於最大參加人數");
	            return activity;
	        }
	    }
	    
	    int resultCount = dao.update(activity);
	    // 要新增一個判斷 resultCount 的程式碼
		return activity;
	}

	// 刪除
	@Override
	public boolean removeById(Integer id) {
		if (id == null) {
			return false;
		}
		return dao.deletById(id) > 0;
	}

	// 查詢
	@Override
	public List<Activity> findAll() {
		return dao.selectAll();
	}

}
