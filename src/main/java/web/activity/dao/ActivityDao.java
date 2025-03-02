package web.activity.dao;

import java.util.List;

import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;

public interface ActivityDao {
	// 新增是活動，回傳整數
	int insert(Activities activities);

	// 新增活動圖片
	int insertActivityImage(ActivityImage activityImage);

	// 修改活動
	int update(Activities activities);
	
	// 改變活動的狀態-移除活動
	int updateteCancel(Activities activity);

	// 刪除單一活動
	int deletActivityById(Integer id);

	// 查詢所有活動
	List<Activities> selectAllActivity();

	// 只查詢單一活動ID
	Activities selectByActivityId(Integer id);

}
