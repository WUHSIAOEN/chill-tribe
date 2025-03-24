package web.activity.dao;

import java.util.List;

import web.activity.vo.Activities;

public interface ActivitiesDao {

	// 依活動ID 及活動票券庫存數量來更新庫存值
	Integer updateActInventoryById(Integer activityId, Integer count);
	
	// 查詢即將開始的活動
	List<Activities> selectAllActivitiesOrderByStartTime();

}
