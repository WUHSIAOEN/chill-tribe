package web.activity.service.impl;

import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.activity.dao.ActivityDao;
import web.activity.dao.ActivitySearchDao;
import web.activity.dao.impl.ActivitySearchDaoImpl;
import web.activity.service.ActivityService2;
import web.activity.vo.Activities;
import web.activity.vo.Activity2;
import web.activity.vo.ActivityImage;


@Service
@Transactional
public class ActivityServiceImpl2 implements ActivityService2 {
	
	// 以前的轉完Spring 就可以拿掉了
	private ActivitySearchDao activitySearchDao;
	
	@Autowired
	private ActivityDao actDao;

	public ActivityServiceImpl2() throws NamingException {
		activitySearchDao = new ActivitySearchDaoImpl();
	}

	@Override
	public List<Activity2> searchAll() {
		return activitySearchDao.selectAllActivities();
	}

	@Override
	public List<ActivityImage> searchAllActivityImages() {
		return activitySearchDao.selectAllActivityImages();
	}

	@Override
	public List<Activities> searchActivityByStart() {

		// 先查Activity 從近到遠
		List<Activities> activities = activitySearchDao.selectActivityOrderByStart();

		// 用Activities 的activityID 去查詢Activity image
		for (Activities activityCard : activities) {
			List<ActivityImage> activityImages = activitySearchDao
					.selectActivityImageById(activityCard.getActivityId());
			activityCard.setActivityImages(activityImages);
		}
		return activities;
	}

	@Override
	public List<Activities> searchActivityByFilter(String actname, String category, String region) {

		List<Activities> activityCards = activitySearchDao.selectByNameCatgoryRegion(actname, category, region);
		for (Activities activityCard : activityCards) {
			List<ActivityImage> activityImages = activitySearchDao
					.selectActivityImageById(activityCard.getActivityId());
			activityCard.setActivityImages(activityImages);
		}
		return activityCards;
	}
	
	
	
	
}
