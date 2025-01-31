package web.activity.service.impl;

import java.util.List;

import javax.naming.NamingException;

import web.activity.dao.ActivitySearchDao;
import web.activity.dao.impl.ActivitySearchDaoImpl;
import web.activity.service.ActivitiService;
import web.activity.vo.Activiti;

public class ActivitiServiceImpl implements ActivitiService{
	
	private ActivitySearchDao activitySearchDao;
	
	public ActivitiServiceImpl() throws NamingException{
		activitySearchDao = new ActivitySearchDaoImpl();
	}

	// 首頁查詢所有活動
	@Override
	public List<Activiti> searchAll() {
		return activitySearchDao.selectAllActivities();
	}
	
	
	
	
	
}
