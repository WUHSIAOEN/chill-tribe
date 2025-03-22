package web.order.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import web.order.dao.OrderDao;
import web.order.vo.Orders;
import web.shoppingcart.vo.ShoppingCart;

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


	@Override
	public List<Orders> selectOrdersByMemberId(Integer memberId) {
		String hql1 = "FROM Orders WHERE memberId = ?1";
		Query<Orders> query1 = session.createQuery(hql1, Orders.class)
				.setParameter(1, memberId);
		return query1.getResultList();
	}
	
	
	
	

	

	
	
}
