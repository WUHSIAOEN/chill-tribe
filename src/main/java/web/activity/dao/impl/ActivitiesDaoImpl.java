package web.activity.dao.impl;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import web.activity.dao.ActivitiesDao;

@Repository
public class ActivitiesDaoImpl implements ActivitiesDao{
	
	@PersistenceContext
	private Session session;

	@Override
	public Integer updateActInventoryById(Integer activityId, Integer count) {
		int result = session
				.createQuery("UPDATE Activities SET inventoryCount = :inventoryCount WHERE activityId = :activityId")
				.setParameter("inventoryCount", count)
				.setParameter("activityId", activityId)
				.executeUpdate();
		
		return result;
	}

}
