package web.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.activity.dao.ActivitiesDao;
import web.activity.service.ActivitySearchService;
import web.activity.vo.Activities;

@Service
@Transactional
public class ActivitySearchServiceImpl implements ActivitySearchService {
	
	@Autowired
	private ActivitiesDao actDao;

	@Override
	public List<Activities> GetLatestActivities() {
		return actDao.selectAllActivitiesOrderByStartTime();
	}

}
