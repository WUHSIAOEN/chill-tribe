package web.activity.dao;

import java.util.List;

import web.activity.vo.Activities;
import web.activity.vo.Activity;

public interface ActivityDao{
	// 新增是活動，回傳整數
	int insert(Activities activity);
	// 更新活動
	int update(Activities activity);
	// 刪除單一活動
	int deletActivityById(Integer id);
	// 查詢所有活動
	List<Activity> selectAllActivity();
	// 只查詢單一活動ID
	Activity selectByActivityId(Integer id);

}
