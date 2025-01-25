package web.activity.service;

import web.activity.vo.Activity;

public interface ActivityService {
	
	// 這裡可以是字串，或其他型態，因為它代表servlet回傳的東西，像是訊息。
	String apply(Activity activity);
}
