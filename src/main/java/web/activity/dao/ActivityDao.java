package web.activity.dao;

import java.util.List;

import web.activity.vo.Activity;
import web.member.vo.Member;

public interface ActivityDao{
	// 新增是活動整數
	int insert(Activity activity);
	// 
	List<Activity> selectAll();
	
	int update(Activity activity);
	
	int deletById(Integer id);

}
