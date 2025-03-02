package web.activity.service;

import java.util.List;

import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;

public interface ActivityService {
	
	// 新增活動
	Activities apply(Activities activities);
	// String apply(Activities activity);
	// 新增活動圖片
	String addImages(Activities activity, List<ActivityImage> list);

	// 更新活動
	Activities edit(Activities activities);
	// String update(Activities activity);

	// 改變活動的狀態-移除活動
	String cancelById(Activities activity);

	// 刪除活動
	boolean removeById(Integer id);

	// 複數活動
	List<Activities> findAllActivity();

	// 單一活動
	Activities findActivityById(Integer id);
}
