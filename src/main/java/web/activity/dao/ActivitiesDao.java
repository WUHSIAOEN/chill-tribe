package web.activity.dao;

public interface ActivitiesDao {

	// 依活動ID 及活動票券庫存數量來更新庫存值
	Integer updateActInventoryById(Integer activityId, Integer count);

}
