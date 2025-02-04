package core;

import java.util.List;

import javax.naming.NamingException;

import web.activity.dao.ActivitySearchDao;
import web.activity.dao.impl.ActivitySearchDaoImpl;
import web.activity.service.impl.ActivityServiceImpl2;
import web.activity.vo.Activity;
import web.activity.vo.ActivityImage;
import web.activity.vo.IndexActivityCard;

public class Main {

	public static void main(String[] args) throws NamingException {
		// 測試回傳
//		List<ActivityImage> activityImages = new ActivitySearchDaoImpl().selectActivityImageById(1);
//		for(ActivityImage activityImage : activityImages) {
//			System.out.println(activityImage.getImageBase64());
//		}
		ActivityServiceImpl2 as2 = new ActivityServiceImpl2();
		as2.searchActivityByStart();
		
	}	
}


