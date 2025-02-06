package web.activity.service;

import java.util.List;

import web.activity.vo.Activity;

public interface ActivityService {
	
	// 這裡可以是字串，或其他型態，因為它代表servlet回傳的東西，像是訊息。
	String apply(Activity activity);
	// 更新活動
	Activity edit(Activity activity);
	// 刪除活動
	boolean removeById(Integer id);
	// 複數活動
	List<Activity> findAll();
	// 單一活動
	Activity findById(Integer id);
}
