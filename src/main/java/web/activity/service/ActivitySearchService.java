package web.activity.service;

import java.util.List;

import web.activity.vo.Activities;

public interface ActivitySearchService {
	
//	搜尋時間最近的活動
	List<Activities> GetLatestActivities();
	
//	依照活動類別搜尋
	List<Activities> FilterByActivityCity(String city);

}
