package web.activity.service;

import java.util.List;

import web.activity.vo.Activity2;
//import web.activity.vo.ActivityImage;
import web.activity.vo.ActivityImage;

public interface ActivityService2 {
		// 首頁查詢所有活動
		List<Activity2> searchAll();
		
		// 首頁查詢所有活動圖片
		List<ActivityImage> searchAllActivityImages();
}
