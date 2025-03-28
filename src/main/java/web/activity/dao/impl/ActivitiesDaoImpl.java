package web.activity.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import web.activity.dao.ActivitiesDao;
import web.activity.vo.Activities;

@Repository
public class ActivitiesDaoImpl implements ActivitiesDao{
	
	@PersistenceContext
	private Session session;
	
//	更新活動庫存
	@Override
	public Integer updateActInventoryById(Integer activityId, Integer count) {
		int result = session
				.createQuery("UPDATE Activities SET inventoryCount = :inventoryCount WHERE activityId = :activityId")
				.setParameter("inventoryCount", count)
				.setParameter("activityId", activityId)
				.executeUpdate();
		
		return result;
	}

	@Override
	public List<Activities> selectAllActivitiesOrderByStartTime() {
		return session
				.createQuery("FROM Activities", Activities.class)
				.setFirstResult(0)
				.setMaxResults(6)
				.getResultList();
	}

	@Override
	public List<Activities> selectActivitiesByCategory(String city) {
		
		return session
				.createQuery("FROM Activities WHERE city = :city", Activities.class)
				.setParameter("category", city)
				.getResultList();
	}
	
	
	
	

}
