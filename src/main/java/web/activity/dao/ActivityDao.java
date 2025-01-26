package web.activity.dao;

import web.activity.vo.Activity;

public interface ActivityDao{
	// 新增是整數
	int apply(Activity activity);
	// 查詢
	Activity selectByActivityName(String activity);

}
