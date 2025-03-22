package web.order.dao.impl;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import web.order.dao.OrderDao;
import web.order.vo.Orders;

@Repository
public class OrderDaoImpl implements OrderDao{
	
		
	@PersistenceContext
	private Session session;

	
	// 不須付款的新增訂單
	@Override
	public int insert(Orders order) {
		session.persist(order);
		return 1;
	}


	@Override
	public Orders selectOrderbyId(Integer orderId) {
		Orders order = session.get(Orders.class, orderId);
		return order;
	}
	
	
	
	

	

	
	
}
