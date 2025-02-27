package web.activity.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.naming.NamingException;

import web.activity.dao.ActivityDao;
import web.activity.dao.impl.ActivityDaoImpl;
import web.activity.service.ActivityService;
import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;

public class ActivityServiceImpl implements ActivityService {
	private ActivityDao dao;

	public ActivityServiceImpl() throws NamingException {
		dao = new ActivityDaoImpl();
	}
	
	// 申請活動
	@Override
	public String apply(Activities activity) {
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
		Integer approved = activity.getApproved();
		Integer cityId = activity.getCity_id();
		Integer districtId = activity.getDistrict_id();
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
	
	// 插入圖片
	@Override
	public String addImages(Activities activity, List<ActivityImage> list) {
		
		int activityId = dao.insert(activity);
		
		for (ActivityImage image : list) {
			image.setActivityId(activityId);
            int resultCount = dao.insertActivityImage(image);
            
            return resultCount > 0 ? null : "插入圖片發生錯誤，請聯絡客服";
		}
		return null;
	}
	
	// 更新活動
	@Override
	public String update(Activities activity) {
		
//		final Activity oActivity = dao.selectByActivityId(activity.getActivityId());
//		activity.setActivityName(oActivity.getActivityName());
//		activity.setAddress(oActivity.getAddress());
//		activity.setPrecaution(oActivity.getPrecaution());
//		activity.setUnitPrice(oActivity.getUnitPrice());
//		activity.setMinParticipants(oActivity.getMinParticipants());
//		activity.setMaxParticipants(oActivity.getMaxParticipants());
//		activity.setDescription(oActivity.getDescription());
//		activity.setCategory(oActivity.getCategory());
//		activity.setStartDateTime(oActivity.getStartDateTime());
//		activity.setEndDateTime(oActivity.getEndDateTime());
//		activity.setNote(oActivity.getNote());
//		activity.setStatus(oActivity.getStatus());
//		activity.setApproved(oActivity.getApproved());
//		activity.setCityId(oActivity.getCityId());
//		activity.setDistrictId(oActivity.getDistrictId());
//		activity.setInventoryCount(oActivity.getInventoryCount());
//		activity.setInventoryUpdateTime(oActivity.getInventoryUpdateTime());
//		activity.setLatitude(oActivity.getLatitude());
//		activity.setLongitude(oActivity.getLongitude());
//		activity.setTicketsActivateTime(oActivity.getTicketsActivateTime());
//		activity.setTicketsExpiredTime(oActivity.getTicketsExpiredTime());
//		activity.setApproved(oActivity.getApproved());
//		activity.setCreateTime(oActivity.getCreateTime());
		
		int resultCount = dao.update(activity);
		
		activity.setSuccessful(resultCount > 0);
		
		return resultCount > 0 ? null : "發生錯誤，請聯絡客服";
		
	}
	
	// 將活動取消
	@Override
	public String cancelById(Activities activity) {
		
		int resultCount = dao.updateteCancel(activity);
		
		activity.setSuccessful(resultCount > 0);
		
		return resultCount > 0 ? null : "發生錯誤，請聯絡客服";
	}

	// 刪除
	@Override
	public boolean removeById(Integer id) {
		if (id == null) {
			return false;
		}
		return dao.deletActivityById(id) > 0;
	}

	// 查詢所有活動
	@Override
	public List<Activities> findAllActivity() {
		return dao.selectAllActivity();
	}

	// 查詢單一活動
	@Override
	public Activities findActivityById(Integer id) {
		return dao.selectByActivityId(id);
	}


}
