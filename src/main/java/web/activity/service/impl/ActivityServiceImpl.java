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
		String activityPrefix = activity.getActivityPrefix(); 
		String activityName = activity.getActivityName();
		Integer supplierId = activity.getSupplierId();
		String address = activity.getAddress();
		Integer unitPrice = activity.getUnitPrice();
		Integer minParticipants = activity.getMinParticipants();
		Integer maxParticipants = activity.getMaxParticipants();
		String description = activity.getDescription();
		String precaution = activity.getPrecaution();
		String category = activity.getCategory();
		Timestamp startDateTime = activity.getStartDateTime();
		Timestamp endDateTime = activity.getEndDateTime();
		Integer status = activity.getStatus();
		String note = activity.getNote();
		Boolean approved = activity.getApproved(); 
		Integer cityId = activity.getCityId(); 
		Integer districtId = activity.getDistrictId();
		Integer inventoryCount = activity.getInventoryCount();
		Timestamp inventoryUpdateTime = activity.getInventoryUpdateTime();
		Timestamp createdTime = activity.getCreateTime();
		String latitude = activity.getLatitude();
		String longitude = activity.getLongitude();
		Timestamp ticketsActivateTime = activity.getTicketsActivateTime();
		Timestamp ticketsExpiredTime = activity.getTicketsExpiredTime();

		int resultCount = dao.insert(activity);

		return resultCount > 0 ? null : "發生錯誤，請聯絡客服";

	}

	@Override
	public Activity edit(Activity activity) {
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
