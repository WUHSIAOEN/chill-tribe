package web.activity.dao;

import web.activity.vo.Activity;

public interface ActivityDao{
	// 新增是活動整數
	int insert(Activity activity);
	// 查詢活動名字
	Activity selectByActivityName(String activityName);

}
