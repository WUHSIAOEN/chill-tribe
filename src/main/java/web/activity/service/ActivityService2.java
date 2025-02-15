package web.activity.service;

import java.util.List;

import web.activity.vo.Activities;
import web.activity.vo.Activity2;
//import web.activity.vo.ActivityImage;
import web.activity.vo.ActivityImage;
import web.activity.vo.IndexActivityCard;

public interface ActivityService2 {
		// 首頁查詢所有活動
		List<Activity2> searchAll();
		
		// 首頁查詢所有活動圖片
		List<ActivityImage> searchAllActivityImages();
		
		// 首頁查詢活動卡片 - 根據最近開始排序(顯示6 筆)
		List<Activities> searchActivityByStart();
		
		// 查詢活動卡片 - 根據搜尋條件
		List<Activities> searchActivityByFilter(String actname, String category, String region);
}
