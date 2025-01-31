package web.activity.dao;

import java.util.List;

import web.activity.vo.Activiti;

public interface ActivitySearchDao {
	// 首頁查詢活動
	List<Activiti> selectAllActivities();
	
	
	
	
}
