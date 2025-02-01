package web.activity.service.impl;

import java.util.List;

import javax.naming.NamingException;

import web.activity.dao.ActivitySearchDao;
import web.activity.dao.impl.ActivitySearchDaoImpl;
import web.activity.service.ActivityService2;
//import web.activity.vo.ActivityImage;
import web.activity.vo.Activity2;
import web.activity.vo.ActivityImage;

public class ActivityServiceImpl2 implements ActivityService2{
private ActivitySearchDao activitySearchDao;
	
	public ActivityServiceImpl2() throws NamingException{
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
}
