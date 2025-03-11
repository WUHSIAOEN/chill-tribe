package web.activity.service;

import java.util.List;

import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;
import web.activity.vo.Comment;

public interface ActivityService {
	
	// 新增活動
	Activities apply(Activities activities);
	// String apply(Activities activity);
	
	// 新增活動圖片
	boolean addImages(List<ActivityImage> images, int activityId);
	
	// 新增留言
	boolean addComments(List<Comment> comments, int activityId);

	// 更新活動
	Activities edit(Activities activities);
	// String update(Activities activity);

	// 改變活動的狀態-移除活動
	boolean cancel(Integer id);

	// 刪除活動
	boolean remove(Integer id);

	// 複數活動
	List<Activities> findAll();

	// 單一活動
	Activities findActivityById(Integer id);
}
