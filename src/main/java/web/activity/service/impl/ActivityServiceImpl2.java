package web.activity.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;

import web.activity.dao.ActivitySearchDao;
import web.activity.dao.impl.ActivitySearchDaoImpl;
import web.activity.service.ActivityService2;
//import web.activity.vo.ActivityImage;
import web.activity.vo.Activity2;
import web.activity.vo.ActivityImage;
import web.activity.vo.IndexActivityCard;

public class ActivityServiceImpl2 implements ActivityService2 {
	private ActivitySearchDao activitySearchDao;

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
	public List<IndexActivityCard> searchActivityByStart() {

		// 先查Activity 從近到遠
		List<IndexActivityCard> activityCards = activitySearchDao.selectActivityOrderByStart();

		// 用IndexActivityCard 的activityID 去查詢Activity image
		for (IndexActivityCard activityCard : activityCards) {
			List<ActivityImage> activityImages = activitySearchDao
					.selectActivityImageById(activityCard.getActivityId());
//			List<ActivityImage> activityImages = activitySearchDao.selectActivityImageById(1);
			List<String> imageNames = new ArrayList<String>();
			List<String> imageBase64 = new ArrayList<String>();
			for (ActivityImage image : activityImages) {
				imageNames.add(image.getImageName());
//				System.out.println(image.getImageName());
				imageBase64.add(image.getImageBase64());
//				System.out.println(image.getImageBase64());
			}
			activityCard.setImageName(imageNames);
			activityCard.setImageBase64(imageBase64);
		}
		return activityCards;
	}
}
