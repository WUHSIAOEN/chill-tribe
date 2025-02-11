package web.activity.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

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
	
	// 申請活動
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
	
	// 更新活動
	@Override
	public Activity update(Integer id) {
		
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
		
//		int resultCount = dao.update(activity);
//		
//		activity.setSuccessful(resultCount > 0);
//		
//		activity.setMessage(resultCount > 0 ? null : "發生錯誤，請聯繫專員");
//		
		return null;
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
	public List<Activity> findAllActivity() {
		return dao.selectAllActivity();
	}

	// 查詢單一活動
	@Override
	public Activity findActivityById(Integer id) {
		return dao.selectByActivityId(id);
	}

}
