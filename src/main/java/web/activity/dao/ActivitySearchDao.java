package web.activity.dao;

import java.util.List;

import web.activity.vo.Activities;
import web.activity.vo.Activity2;
import web.activity.vo.ActivityImage;

public interface ActivitySearchDao {
	// 首頁查詢活動
	List<Activity2> selectAllActivities();
	// 查詢所有的活動圖片回來-所有欄位
	List<ActivityImage> selectAllActivityImages();
	
	// 查詢首頁活動卡片欄位-根據開始時間排序limit 6
	List<Activities> selectActivityOrderByStart();
	
	// 查詢活動圖片
	List<ActivityImage> selectActivityImageById(Integer activityId);
	
	// 活動首頁三種條件查詢
	List<Activities> selectByNameCatgoryRegion(String actname, String catgory, String region);
	
	
	
}
