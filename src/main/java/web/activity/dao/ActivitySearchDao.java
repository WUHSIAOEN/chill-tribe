package web.activity.dao;

import java.util.List;

import web.activity.vo.Activity2;
//import web.activity.vo.ActivityImage;

public interface ActivitySearchDao {
	// 首頁查詢活動
	List<Activity2> selectAllActivities();
	// 查詢所有的活動圖片回來
//	List<ActivityImage> selectAllActivityImages();
		
}
