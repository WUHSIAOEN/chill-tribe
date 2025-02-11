package web.activity.dao;

import java.util.List;

import web.activity.vo.Activity;
import web.member.vo.Member;

public interface ActivityDao{
	// 新增是活動，回傳整數
	int insert(Activity activity);
	
	int update(Activity activity);
	
	int deletById(Integer id);
	
	// 查詢所有活動
	List<Activity> selectAllActivity();
	
	// 只查詢單一活動ID
	Activity selectByActivityId(Integer id);

}
