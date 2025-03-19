package web.activity.dao;

import java.util.List;

import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;
import web.activity.vo.Comment;

public interface ActivityDao {
	// 新增活動文字
	int insert(Activities activities);

	// 新增活動圖片
	int insertImages(List<ActivityImage> images, int activityId);
	
	int updateImages(List<ActivityImage> images, int activityId);
	
	// 新增活動評論
	int insertComments(List<Comment> comments, int activityId);

	// 修改活動
	int update(Activities activities);
	
	// 改變活動的狀態-移除活動
	int statusCancel(Integer id);

	// 刪除單一活動
	int deleteActivityById(Integer id);

	// 查詢所有活動
	List<Activities> selectAll();

	// 只查詢單一活動ID
	Activities selectByActivityId(Integer id);

}
