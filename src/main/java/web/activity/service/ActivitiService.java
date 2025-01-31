package web.activity.service;

import java.util.List;

import web.activity.vo.Activiti;

public interface ActivitiService {
	
	
	// 首頁查詢所有活動
	List<Activiti> searchAll();
	
}
