package web.order.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import web.order.dao.TicketDao;
import web.order.vo.Ticket;
import web.shoppingcart.vo.ShoppingCart;


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
	public Ticket selctByTicketId(int ticketId) {
		return session.get(Ticket.class, ticketId);
	}


	@Override
	public List<Ticket> selctByOrderId(int orderId) {
		String hql1 = "FROM Ticket WHERE orderId = ?1";
		Query<Ticket> query1 = session.createQuery(hql1, Ticket.class)
				.setParameter(1, orderId);
		
		return query1.getResultList();
	}
	
}
