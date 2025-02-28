package web.activity.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.activity.dao.ActivityDao;
import web.activity.dao.impl.ActivityDaoImpl;
import web.activity.service.ActivityService;
import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
	
	
//	========= 原生寫法 - 若確定此類別內的所有Service 方法已可透過Spring 注入取得DAO 即可砍掉以下程式 =========
	private ActivityDao actdao;

	public ActivityServiceImpl() throws NamingException {
		actdao = new ActivityDaoImpl();
	}
//	========= 原生寫法 - 若確定此類別內的所有Service 方法已可透過Spring 注入取得DAO 即可砍掉以下程式 =========
	
//	========= Spring 注入 DAO =========
	@Autowired
	private ActivityDao dao;

	// 申請活動上架
	@Override
	public String apply(Activities activity) {
		int resultCount = dao.insert(activity);
		return resultCount > 0 ? null : "發生錯誤，請聯絡客服";
	}

	// 插入圖片
	@Override
	public String addImages(Activities activity, List<ActivityImage> list) {

		int activityId = actdao.insert(activity);

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

		int resultCount = actdao.update(activity);

		activity.setSuccessful(resultCount > 0);

		return resultCount > 0 ? null : "發生錯誤，請聯絡客服";

	}

	// 將活動取消
	@Override
	public String cancelById(Activities activity) {

		int resultCount = actdao.updateteCancel(activity);

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
