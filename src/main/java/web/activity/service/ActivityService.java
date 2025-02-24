package web.activity.service;

import java.util.List;

import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;

public interface ActivityService {
	
	// 這裡可以是字串，或其他型態，
	// 因為它代表servlet回傳的東西，具體的訊息，告訴使用者操作是成功還是失敗
	String apply(Activities activity);
	// 新增活動圖片
	String addImages(Activities activity);
	// 更新活動
	String update(Activities activity);
	// 刪除活動
	boolean removeById(Integer id);
	// 複數活動
	List<Activities> findAllActivity();
	// 單一活動
	Activities findActivityById(Integer id);
}
