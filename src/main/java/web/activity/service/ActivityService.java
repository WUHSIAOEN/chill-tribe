package web.activity.service;

import java.util.List;

import web.activity.vo.Activity;

public interface ActivityService {
	
	// 這裡可以是字串，或其他型態，因為它代表servlet回傳的東西，像是訊息。
	String apply(Activity activity);
	// 複數型態，找到活動
	List<Activity> findAll();
}
