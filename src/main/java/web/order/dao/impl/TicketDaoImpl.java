package web.order.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import web.order.dao.TicketDao;
import web.order.vo.Ticket;


@Repository
public class TicketDaoImpl implements TicketDao {

	@PersistenceContext
	private Session session;
	
	
	@Override
	public int insert(Ticket ticket) {
		session.persist(ticket);
		return 1;
	}


	@Override
	public Ticket selctByTicketId(Integer ticketId) {
		return session.get(Ticket.class, ticketId);
	}


	@Override
	public List<Ticket> selctByOrderId(Integer orderId) {
		String hql1 = "FROM Ticket WHERE orderId = ?1";		
		return session
				.createQuery(hql1, Ticket.class)
				.setParameter(1, orderId)
				.getResultList();
	}


	@Override
	public int updateOrderStatus(Integer orderId, String status) {
		String hql = "UPDATE Orders SET orderStatus = :orderStatus WHERE orderId = :orderId";
		Query query = session.createQuery(hql)
				.setParameter("orderStatus", status)
				.setParameter("orderId", orderId);
		return query.executeUpdate();
	}
	
}
